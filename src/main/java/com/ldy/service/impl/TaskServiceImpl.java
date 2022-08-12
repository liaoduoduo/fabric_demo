package com.ldy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.common.BaseContext;
import com.ldy.common.R;
import com.ldy.dto.TaskDto;
import com.ldy.entity.*;
import com.ldy.mapper.*;
import com.ldy.service.ITaskService;
import com.ldy.vo.TaskVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
@Service
@Slf4j
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {

    @Autowired
    TaskMapper taskMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    TokenMapper tokenMapper;
    @Autowired
    TokenLogMapper tokenLogMapper;
    @Autowired
    UserTaskMapper userTaskMapper;

    @Override
    public R<String> saveTaskAndBlockToken(Task task) {
        // 需要判断用户的token值是否足够支付该任务的悬赏，并进行冻结
        Token token = tokenMapper.selectTokenValueByUserId(BaseContext.getCurrentId());
        // 首先判断任务设置的悬赏金额是否大于用户当前的可用余额
        int compare = task.getToken().compareTo(token.getCurrentToken());
        if (compare > 0) {
            return R.error("已有Token值不足以支付该任务");
        }
        //当该任务是全公开任务时，不设置接单策略
        if (task.getOpen() > 0) {
            task.setPolicy(null);
        }
        task.setStatus(1);
        task.setFinished(0);
        task.setEvaluation("");
        task.setDeleted(0);
        log.info("任务信息-----" + task);
        int save = taskMapper.insert(task);
        //当用户的可用余额满足该研判任务的悬赏值时，从可用余额中转移到冻结余额中
        BigDecimal currentToken = token.getCurrentToken().subtract(task.getToken());
        token.setCurrentToken(currentToken);
        BigDecimal blockToken = token.getBlockToken().add(task.getToken());
        token.setBlockToken(blockToken);
        tokenMapper.updateById(token);
        // 记录Token变化表
        TokenLog tokenLog = new TokenLog();
        tokenLog.setTokenId(token.getId());
        tokenLog.setContent("新增悬赏任务" + task.getId());
        tokenLog.setCurrentChange(task.getToken().negate());
        tokenLog.setBlockChange(task.getToken());
        tokenLog.setCurrentToken(currentToken);
        tokenLog.setBlockToken(blockToken);
        tokenLog.setDeleted(0);
        tokenLogMapper.insert(tokenLog);
        return save > 0 ? R.success("添加成功") : R.error("添加失败");
    }

    @Override
    public List<TaskVo> getTaskWithCategoryByCotaskId(Long id) {
        return taskMapper.getTaskWithCategoryByCotaskId(id);
    }

    @Override
    public R<String> updateToken(TaskDto taskDto) {
        Long userId = BaseContext.getCurrentId();
        // 1. 查询当前用户所拥有的余额
        Token token = tokenMapper.selectTokenValueByUserId(userId);
        // 2. 获取前端传入要修改悬赏任务的新旧Token值，用于比较
        BigDecimal oldToken = taskDto.getToken();
        BigDecimal newToken = taskDto.getNewToken();
        int i = oldToken.compareTo(newToken);
        if (i == 0) {
            return R.success("未改变悬赏值");
        }
        // 2.1 当旧Token值低于新Token值时
        TokenLog tokenLog = new TokenLog();
        tokenLog.setTokenId(token.getId());
        tokenLog.setContent("修改悬赏任务" + token.getId() + "Token值");
        tokenLog.setDeleted(0);
        if (i < 0) {
            // 2.1.1 获取增加的Token值
            BigDecimal tokenChange = newToken.subtract(oldToken);
            // 2.1.2 与当前用户的可用值进行比较
            if (tokenChange.compareTo(token.getCurrentToken()) > 0) {
                return R.error("超过已有token值");
            }
            // 当新设置的悬赏Token大于旧Token时，当前可用需要减去tokenChange，冻结Token需要加上tokenChange
            tokenLog.setCurrentChange(tokenChange.negate());
            tokenLog.setBlockChange(tokenChange);

            BigDecimal currentToken = token.getCurrentToken().add(tokenChange);
            BigDecimal blockToken = token.getBlockToken().subtract(tokenChange);

            tokenLog.setCurrentToken(currentToken);
            tokenLog.setBlockToken(blockToken);
            // 同步更新钱包内的余额
            token.setCurrentToken(currentToken);
            token.setBlockToken(blockToken);
        } else {
            // 2.2 当旧Token值大于新Token值时
            // 当旧Token值大于新Token值时，当前可用需要加上tokenChange，冻结Token需要减去tokenChange
            BigDecimal tokenChange = oldToken.subtract(newToken);
            tokenLog.setCurrentChange(tokenChange);
            tokenLog.setBlockChange(tokenChange.negate());
            tokenLog.setCurrentToken(token.getCurrentToken().add(tokenChange));
            tokenLog.setBlockToken(token.getBlockToken().subtract(tokenChange));
            // 同步更新钱包内的余额
            token.setCurrentToken(token.getCurrentToken().add(tokenChange));
            token.setBlockToken(token.getBlockToken().subtract(tokenChange));
        }
        // 保存Token钱包的变化，新增Token记录
        tokenMapper.updateById(token);
        tokenLogMapper.insert(tokenLog);
        // 修改任务中的新Token值
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setToken(newToken);
        int save = taskMapper.updateById(task);
        return save > 0 ? R.success("修改成功") : R.error("修改失败");
    }

    @Override
    public R<String> removeTaskByIds(Long[] ids) {
        // 1. 首先判断该悬赏任务是否被人接单
        LambdaQueryWrapper<UserTask> userTaskLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userTaskLambdaQueryWrapper.in(UserTask::getTaskId, ids);
        Integer userTaskCount = userTaskMapper.selectCount(userTaskLambdaQueryWrapper);
        if (userTaskCount > 0) {
            return R.error("悬赏任务已被接单，无法撤销");
        }
        // 2. 获取当前用户钱包
        Token token = tokenMapper.selectTokenValueByUserId(BaseContext.getCurrentId());
        TokenLog tokenLog = new TokenLog();
        tokenLog.setTokenId(token.getId());
        tokenLog.setContent("撤销悬赏任务" + Arrays.toString(ids));
        tokenLog.setDeleted(0);
        int result = 0;
        BigDecimal totalBlockToken = new BigDecimal(0);
        // 3. 判断是单删还是批量
        if (ids.length == 1) {
            Task task = taskMapper.selectById(ids[0]);
            totalBlockToken = task.getToken();
            result = taskMapper.deleteById(ids[0]);
        }
        if (ids.length > 1) {
            LambdaQueryWrapper<Task> taskLambdaQueryWrapper = new LambdaQueryWrapper<>();
            taskLambdaQueryWrapper.in(Task::getId, ids);
            List<Task> tasks = taskMapper.selectList(taskLambdaQueryWrapper);
            for (Task task : tasks) {
                totalBlockToken = totalBlockToken.add(task.getToken());
            }
            result = taskMapper.deleteBatchIds(Arrays.asList(ids));
        }
        token.setCurrentToken(token.getCurrentToken().add(totalBlockToken));
        token.setBlockToken(token.getBlockToken().subtract(totalBlockToken));
        tokenMapper.updateById(token);
        tokenLog.setCurrentChange(totalBlockToken);
        tokenLog.setBlockChange(totalBlockToken.negate());
        tokenLog.setCurrentToken(token.getCurrentToken());
        tokenLog.setBlockToken(token.getBlockToken());
        tokenLogMapper.insert(tokenLog);

        return result > 0 ? R.success("删除成功") : R.error("删除失败");
    }
}
