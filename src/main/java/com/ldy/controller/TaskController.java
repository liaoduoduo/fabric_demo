package com.ldy.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldy.common.BaseContext;
import com.ldy.common.R;
import com.ldy.entity.Task;
import com.ldy.entity.Token;
import com.ldy.entity.User;
import com.ldy.service.ITaskService;
import com.ldy.service.IUserTaskService;
import com.ldy.service.TokenService;
import com.ldy.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * <p>
 * 这里认为所有的任务都是悬赏任务，公不公开在于策略是否为空
 * </p>
 *
 * @author sunqing
 * @since 2022-07-24
 */
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

    @ApiOperation("根据协同任务新增研判任务")
    @PostMapping("/addTask")
    @Transactional
    public R<String> addTask(@RequestBody Task task) {

        // 需要判断用户的token值是否足够支付该任务的悬赏，并进行冻结
        Long currentUser = BaseContext.getCurrentId();
        User user = userService.getById(currentUser);
        Token token = tokenService.getById(user.getTokenId());
        int compare = task.getToken().compareTo(token.getCurrentToken());
        boolean save;
        if (compare > 0) {
            return R.error("已有Token值不足以支付该任务");
        }
        token.setCurrentToken(token.getCurrentToken().subtract(task.getToken()));
        token.setBlockToken(token.getBlockToken().add(task.getToken()));
        tokenService.updateById(token);
        if (task.getOpen() > 0) {
            task.setPolicy(null);
        }
        task.setStatus(1);
        task.setFinished(0);
        task.setEvaluation("");
        task.setDeleted(0);
        save = taskService.save(task);
        return save ? R.success("添加成功") : R.error("添加失败");
    }

    @ApiOperation("根据协同任务查询对应的研判任务")
    @GetMapping("getTaskByCotaskId/{id}")
    public R<Page<Task>> getTaskByCotaskId(@RequestParam(value = "page", defaultValue = "1") int page,
                                           @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                                           @PathVariable("id") Long id) {
        Page<Task> taskPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Task> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Task::getCotaskingId, id);
        taskPage = taskService.page(taskPage, queryWrapper);
        return R.success(taskPage);
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
        queryWrapper.like(name != null, Task::getName, name).ne(Task::getFinished, 1);
        queryWrapper.and(i -> i.eq(Task::getStatus, 1).eq(Task::getOpen, 1).or().eq(Task::getPolicy, user.getUnit()));
        taskPage = taskService.page(taskPage, queryWrapper);
        return R.success(taskPage);
    }

    @ApiOperation("任务撤销")
    @DeleteMapping("/deleteTask")
    public R<String> deleteTaskById(@RequestParam("ids") Long[] ids) {
        boolean result = false;
        if (ids.length == 1) {
            result = taskService.removeById(ids[0]);
        }
        if (ids.length > 1) {
            result = taskService.removeByIds(Arrays.asList(ids));
        }
        return result ? R.success("删除成功") : R.error("删除失败");
    }

    @ApiOperation("研判任务常规信息修改")
    @PutMapping("/updateTask")
    public R<String> updateTask(@RequestBody Task task) {
        boolean result = taskService.updateById(task);
        return result ? R.success("修改成功") : R.error("修改失败");
    }

    @ApiOperation("研判任务增加悬赏")
    @PutMapping("/updateToken")
    @Transactional
    public R<String> updateToken(@RequestBody Task oldTask) {
        Long userId = BaseContext.getCurrentId();
        User user = userService.getById(userId);
        Token token = tokenService.getById(user.getTokenId());
        Task task = taskService.getById(oldTask.getId());
        BigDecimal oldToken = task.getToken();
        BigDecimal newToken = oldTask.getToken();
        int i = oldToken.compareTo(newToken);
        if (i == 0) {
            return R.success("未改变悬赏值");
        }
        if (i < 0) {
            BigDecimal num = newToken.subtract(oldToken);
            if (num.compareTo(token.getCurrentToken()) > 0) {
                return R.error("超过已有token值");
            }
            token.setCurrentToken(token.getCurrentToken().subtract(num));
            token.setBlockToken(token.getBlockToken().add(num));
        } else {
            BigDecimal num = oldToken.subtract(newToken);
            token.setBlockToken(token.getBlockToken().subtract(num));
            token.setCurrentToken(token.getCurrentToken().add(num));
        }
        tokenService.updateById(token);
        task.setToken(newToken);
        boolean save = taskService.updateById(task);
        return save ? R.success("成功添加") : R.error("添加异常");
    }

    @ApiOperation("根据任务id查看任务详情")
    @GetMapping("/getTaskById/{id}")
    public R<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getById(id);
        return R.success(task);
    }

}

