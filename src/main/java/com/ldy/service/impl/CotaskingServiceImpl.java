package com.ldy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.common.BaseContext;
import com.ldy.common.R;
import com.ldy.dto.CotaskingDto;
import com.ldy.entity.*;
import com.ldy.mapper.*;
import com.ldy.service.ICotaskingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sunqing
 * @since 2022-07-24
 */
@Slf4j
@Service
public class CotaskingServiceImpl extends ServiceImpl<CotaskingMapper, Cotasking> implements ICotaskingService {

    @Autowired
    CotaskingMapper cotaskingMapper;
    @Autowired
    CotaskingIntelligenceMapper cotaskingIntelligenceMapper;
    @Autowired
    UserTaskMapper userTaskMapper;
    @Autowired
    TaskMapper taskMapper;
    @Autowired
    TokenMapper tokenMapper;
    @Autowired
    TokenLogMapper tokenLogMapper;

    @Override
    public R<String> addCotakAndBindIntelligences(CotaskingDto cotaskingDto) {
        Cotasking cotasking = new Cotasking();
        cotasking.setName(cotaskingDto.getName());
        cotasking.setDescription(cotaskingDto.getDescription());
        cotasking.setStatus(1);
        cotasking.setLiAn(0);
        cotasking.setDeleted(0);
        int save = cotaskingMapper.insert(cotasking);
        Long cotaskingId = cotasking.getId();
        Integer integer;
        if (save > 0) {
            List<CotaskingIntelligence> cotaskingIntelligences = new ArrayList<>();
            String regex = ",";
            String[] split = cotaskingDto.getIntelligenceIds().split(regex);
            for (String intelligenceId : split) {
                CotaskingIntelligence cotaskingIntelligence = new CotaskingIntelligence();
                cotaskingIntelligence.setCotaskingId(cotaskingId);
                cotaskingIntelligence.setIntelligenceId(Long.parseLong(intelligenceId));
                cotaskingIntelligence.setDeleted(0);
                cotaskingIntelligences.add(cotaskingIntelligence);
            }
            integer = cotaskingIntelligenceMapper.addBatchCotaskingIntelligence(cotaskingIntelligences);
        } else {
            return R.error("添加失败");
        }

        return integer > 0 ? R.success("添加成功") : R.error("添加失败");
    }

    @Override
    public R<String> removeCotaskByIds(Long[] ids) {
        // 查询协同任务中是否存在被人接单的研判任务
        Integer integer = userTaskMapper.selectUserTaskCountInCotasking(Arrays.asList(ids));
        if (integer > 0) {
            return R.error("协同任务中存在用户接单，禁止撤销");
        }
        // 逻辑删除协同任务
        int i = cotaskingMapper.deleteBatchIds(Arrays.asList(ids));
        // 逻辑删除协同任务中的未被人接单的研判任务,同时需要退还已冻结的Token值
        LambdaQueryWrapper<Task> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Task::getCotaskingId, ids);
        List<Task> tasks = taskMapper.selectList(queryWrapper);
        // Integer taskCount = taskMapper.selectCount(queryWrapper);
        // 记录总冻结的Token值
        BigDecimal totalBlockToken = new BigDecimal(0);
        if (tasks.size() > 0) {
            // 遍历已生成的研判任务，并退还已冻结的Token值
            for (Task task : tasks) {
                totalBlockToken = totalBlockToken.add(task.getToken());
            }
            // 获取当前用户的Token钱包，
            Token token = tokenMapper.selectTokenValueByUserId(BaseContext.getCurrentId());
            BigDecimal blockToken = token.getBlockToken().subtract(totalBlockToken);
            token.setBlockToken(blockToken);
            BigDecimal currentToken = token.getCurrentToken().add(totalBlockToken);
            token.setCurrentToken(currentToken);
            // 保存新钱包Token值
            tokenMapper.updateById(token);
            // 新增Token变化记录
            TokenLog tokenLog = new TokenLog();
            tokenLog.setTokenId(token.getId());
            tokenLog.setCurrentChange(totalBlockToken);
            tokenLog.setBlockChange(totalBlockToken.negate());
            tokenLog.setCurrentToken(currentToken);
            tokenLog.setBlockToken(blockToken);
            tokenLog.setContent("删除协同任务"+ids+"后退还已生成悬赏任务所冻结的Token");
            // 新增Token变化记录
            tokenLogMapper.insert(tokenLog);
            taskMapper.delete(queryWrapper);
        }
        // 逻辑删除协同任务中的情报
        LambdaQueryWrapper<CotaskingIntelligence> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(CotaskingIntelligence::getCotaskingId, ids);
        Integer intelligenceCount = cotaskingIntelligenceMapper.selectCount(wrapper);
        if (intelligenceCount > 0) {
            cotaskingIntelligenceMapper.delete(wrapper);
        }
        return i > 0 ? R.success("撤销成功") : R.error("撤销失败");
    }

    @Override
    public R<String> updateStatus(Long[] ids, Integer status) {
        // 1. 设置研判任务的状态
        LambdaQueryWrapper<Cotasking> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(Cotasking::getId, ids);
        Cotasking cotasking = new Cotasking();
        cotasking.setStatus(status);
        int update = cotaskingMapper.update(cotasking, lambdaQueryWrapper);
        // 2. 修改研判任务其下的研判任务的id
        // 2.1 判断该协同任务中是否有研判任务
        LambdaQueryWrapper<Task> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Task::getCotaskingId, ids);
        Integer taskCount = taskMapper.selectCount(queryWrapper);
        if (taskCount > 0) {
            Task task = new Task();
            task.setStatus(status);
            taskMapper.update(task, queryWrapper);
        }
        return update > 0 ? R.success("状态修改成功") : R.error("状态修改失败");
    }
}
