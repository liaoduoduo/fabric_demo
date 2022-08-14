package com.ldy.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.common.BaseContext;
import com.ldy.common.R;
import com.ldy.entity.Task;
import com.ldy.entity.TaskDetail;
import com.ldy.entity.UserTask;
import com.ldy.mapper.TaskDetailMapper;
import com.ldy.mapper.TaskMapper;
import com.ldy.mapper.UserTaskMapper;
import com.ldy.service.IUserTaskService;
import com.ldy.vo.TaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sunqing
 * @since 2022-07-24
 */
@Service
public class UserTaskServiceImpl extends ServiceImpl<UserTaskMapper, UserTask> implements IUserTaskService {

    @Autowired
    UserTaskMapper userTaskMapper;
    @Autowired
    TaskMapper taskMapper;
    @Autowired
    TaskDetailMapper taskDetailMapper;

    @Override
    public Page<TaskVo> getUserTaskByUserId(Page<TaskVo> taskVoPage, String name, Long userId) {
        return userTaskMapper.getUserTaskByUserId(taskVoPage, name, userId);
    }

    @Override
    public R<String> saveUserTask(Long id) {

        Long userId = BaseContext.getCurrentId();
        Task task = taskMapper.selectById(id);
        if (Objects.equals(userId, task.getCreateUser())) {
            return R.error("添加异常, 不能接自己的单子");
        }
        UserTask userTask = new UserTask();
        userTask.setTaskId(id);
        userTask.setUserId(userId);
        userTask.setAccepted(0);
        userTask.setContribution(BigDecimal.valueOf(0));
        userTask.setDeleted(0);
        int insert = userTaskMapper.insert(userTask);
        return insert > 0 ? R.success("成功添加") : R.error("添加异常");
    }
}
