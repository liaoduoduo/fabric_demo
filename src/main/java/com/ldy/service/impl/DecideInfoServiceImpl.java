package com.ldy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.entity.DecideInfo;
import com.ldy.entity.Task;
import com.ldy.mapper.DecideInfoMapper;
import com.ldy.mapper.TaskMapper;
import com.ldy.service.IDecideInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sunqing
 * @since 2022-07-24
 */
@Service
public class DecideInfoServiceImpl extends ServiceImpl<DecideInfoMapper, DecideInfo> implements IDecideInfoService {

    @Autowired
    TaskMapper taskMapper;
    @Autowired
    DecideInfoMapper decideInfoMapper;

    @Override
    public List<String> getTaskDetailFiledByTaskId(Long id) {

        Task task = taskMapper.selectById(id);
        Long decideInfoCategoryId = task.getDecideInfoCategoryId();
        LambdaQueryWrapper<DecideInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DecideInfo::getDecideInfoCategoryId, decideInfoCategoryId).select(DecideInfo::getName, DecideInfo::getNameZh);
        List<DecideInfo> decideInfos = decideInfoMapper.selectList(queryWrapper);
        List<String> fileds = new ArrayList<>();
        for (DecideInfo decideInfo : decideInfos) {
            fileds.add(decideInfo.getName());
        }
        return fileds;
    }
}
