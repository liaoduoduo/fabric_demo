package com.ldy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.common.BaseContext;
import com.ldy.common.R;
import com.ldy.entity.Task;
import com.ldy.entity.Token;
import com.ldy.entity.User;
import com.ldy.mapper.TaskMapper;
import com.ldy.mapper.TokenMapper;
import com.ldy.mapper.UserMapper;
import com.ldy.service.ITaskService;
import com.ldy.vo.TaskVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public R<String> saveTaskAndBlockToken(Task task) {
        // 需要判断用户的token值是否足够支付该任务的悬赏，并进行冻结
        Long currentUser = BaseContext.getCurrentId();
        User user = userMapper.selectById(currentUser);
        Token token = tokenMapper.selectById(user.getTokenId());
        // 首先判断任务设置的悬赏金额是否大于用户当前的可用余额
        int compare = task.getToken().compareTo(token.getCurrentToken());
        if (compare > 0) {
            return R.error("已有Token值不足以支付该任务");
        }
        //当用户的可用余额满足该研判任务的悬赏值时，从可用余额中转移到冻结余额中
        token.setCurrentToken(token.getCurrentToken().subtract(task.getToken()));
        token.setBlockToken(token.getBlockToken().add(task.getToken()));
        tokenMapper.updateById(token);
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
        return save > 0 ? R.success("添加成功") : R.error("添加失败");
    }

    @Override
    public List<TaskVo> getTaskWithCategoryByCotaskId(Long id) {
        return taskMapper.getTaskWithCategoryByCotaskId(id);
    }
}
