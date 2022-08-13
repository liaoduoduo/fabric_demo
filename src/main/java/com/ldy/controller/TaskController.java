package com.ldy.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldy.common.BaseContext;
import com.ldy.common.R;
import com.ldy.dto.TaskDto;
import com.ldy.entity.Task;
import com.ldy.entity.Token;
import com.ldy.entity.User;
import com.ldy.service.ITaskService;
import com.ldy.service.IUserTaskService;
import com.ldy.service.TokenService;
import com.ldy.service.UserService;
import com.ldy.vo.TaskVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 这里认为所有的任务都是悬赏任务，公不公开在于策略是否为空
 * </p>
 *
 * @author sunqing
 * @since 2022-07-24
 */
@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    ITaskService taskService;

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @Autowired
    IUserTaskService userTaskService;

    @ApiOperation("根据协同任务新增悬赏任务")
    @PostMapping("/addTask")
    @Transactional
    public R<String> addTask(@RequestBody Task task) {
        return taskService.saveTaskAndBlockToken(task);
    }

    @ApiOperation("根据协同任务查询对应的悬赏任务")
    @GetMapping("getTaskByCotaskId/{id}")
    public R<List<TaskVo>> getTaskByCotaskId(@PathVariable("id") Long id) {
        return R.success(taskService.getTaskWithCategoryByCotaskId(id));
    }

    @ApiOperation("获取悬赏任务分页，根据用户做定制,有很大的提升空间，这里为了简便，仅把单位作为策略")
    @GetMapping("/getRewardTaskPage")
    public R<Page<Task>> getRewardTaskPage(@RequestParam(value = "page", defaultValue = "1") int page,
                                           @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                                           @RequestParam("name") String name) {
        Long userId = BaseContext.getCurrentId();
        User user = userService.getById(userId);
        Page<Task> taskPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Task> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Task::getName, name)
                .ne(Task::getFinished, 1)
                .eq(Task::getStatus, 1)
                .or().eq(Task::getPolicy, user.getUnit());
        taskPage = taskService.page(taskPage, queryWrapper);
        return R.success(taskPage);
    }

    @ApiOperation("任务撤销,同时需要退还冻结token")
    @DeleteMapping("/deleteTask")
    @Transactional
    public R<String> deleteTaskById(@RequestBody Long[] ids) {
        if (ids.length < 1) {
            return R.error("数据传递异常");
        }
        return taskService.removeTaskByIds(ids);
    }

    @ApiOperation("悬赏任务增加悬赏")
    @PutMapping("/updateToken")
    @Transactional
    public R<String> updateToken(@RequestBody TaskDto taskDto) {
        return taskService.updateToken(taskDto);
    }

    @ApiOperation("悬赏任务上下架,下架不等同于删除，不涉及Token")
    @PutMapping("/updateStatus")
    public R<String> updateStatus(@RequestBody TaskDto taskDto) {
        boolean update;
        LambdaUpdateWrapper<Task> taskLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        taskLambdaUpdateWrapper.set(Task::getStatus, taskDto.getNewStatus());
        taskLambdaUpdateWrapper.in(Task::getId, taskDto.getIds());
        update = taskService.update(taskLambdaUpdateWrapper);
        return update ? R.success("状态修改成功") : R.error("状态修改失败");
    }

    @ApiOperation("悬赏任务常规信息修改")
    @PutMapping("/updateTask")
    public R<String> updateTask(@RequestBody Task task) {
        boolean result = taskService.updateById(task);
        return result ? R.success("修改成功") : R.error("修改失败");
    }

    @ApiOperation("根据任务id查看任务详情")
    @GetMapping("/getTaskById/{id}")
    public R<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getById(id);
        return R.success(task);
    }

}

