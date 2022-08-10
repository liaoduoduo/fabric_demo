package com.ldy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.common.R;
import com.ldy.dto.CotaskingIntelligenceDto;
import com.ldy.entity.CotaskingIntelligence;
import com.ldy.entity.Intelligence;
import com.ldy.mapper.CotaskingIntelligenceMapper;
import com.ldy.service.ICotaskingIntelligenceService;
import com.ldy.vo.CotaskIntelligenceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sunqing
 * @since 2022-07-24
 */
@Service
public class CotaskingIntelligenceServiceImpl extends ServiceImpl<CotaskingIntelligenceMapper, CotaskingIntelligence> implements ICotaskingIntelligenceService {

    @Autowired
    CotaskingIntelligenceMapper cotaskingIntelligenceMapper;

    @Override
    public List<CotaskIntelligenceVo> getIntelligencesInCotask(Long cid) {
        return cotaskingIntelligenceMapper.getIntelligencesInCotask(cid);
    }

    @Override
    public R<String> addBatchCotaskingIntelligence(CotaskingIntelligenceDto cotaskingIntelligenceDto) {
        // 1. 查询当前协同任务中所有的情报，用于避免重复添加
        LambdaQueryWrapper<CotaskingIntelligence> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CotaskingIntelligence::getCotaskingId, cotaskingIntelligenceDto.getCotaskingId());
        List<CotaskingIntelligence> list = cotaskingIntelligenceMapper.selectList(queryWrapper);
        // 2. 分解前端传递的情报ID
        String[] split = cotaskingIntelligenceDto.getIntelligenceIds().split(",");
        List<Long> intelligenceIds = new ArrayList<>();
        for (String s : split) {
            intelligenceIds.add(Long.parseLong(s));
        }
        // 3. 新建一个集合用于记录当前协同任务中已存在的情报ID,并判断
        List<Long> existdIntelligenceIds = new ArrayList<>();
        for (CotaskingIntelligence cotaskingIntelligence : list) {
            if (intelligenceIds.contains(cotaskingIntelligence.getIntelligenceId())) {
                existdIntelligenceIds.add(cotaskingIntelligence.getIntelligenceId());
            }
        }
        if (!existdIntelligenceIds.isEmpty()) {
            return R.error("添加异常, 其中情报" + existdIntelligenceIds + "已存在当前协同任务中");
        }
        // 4. 若情报在协同任务中并没有，即可添加
        List<CotaskingIntelligence> cotaskingIntelligences = new ArrayList<>();
        for (String intelligenceId : split) {
            CotaskingIntelligence cotaskingIntelligence = new CotaskingIntelligence();
            cotaskingIntelligence.setCotaskingId(cotaskingIntelligenceDto.getCotaskingId());
            cotaskingIntelligence.setIntelligenceId(Long.parseLong(intelligenceId));
            cotaskingIntelligence.setDeleted(0);
            cotaskingIntelligences.add(cotaskingIntelligence);
        }
        Integer integer = cotaskingIntelligenceMapper.addBatchCotaskingIntelligence(cotaskingIntelligences);
        return integer > 0 ? R.success("成功添加") : R.error("添加异常");
    }

}
