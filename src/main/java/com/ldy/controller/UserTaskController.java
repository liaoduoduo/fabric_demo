package com.ldy.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldy.common.BaseContext;
import com.ldy.common.R;
import com.ldy.entity.*;
import com.ldy.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * 接单
 * </p>
 *
 * @author sunqing
 * @since 2022-07-24
 */
@RestController
@RequestMapping("/user-task")
public class UserTaskController {

    @Autowired
    IUserTaskService userTaskService;

    @Autowired
    ITaskService taskService;

    @Autowired
    ITokenDealService tokenDealService;

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @ApiOperation("接单")
    @PostMapping("/acceptTask/{id}")
    public R<String> acceptTask(@PathVariable Long id) {
        Long userId = BaseContext.getCurrentId();
        Task task = taskService.getById(id);

        if (Objects.equals(userId, task.getCreateUser())) {
            return R.error("添加异常, 不能接自己的单子");
        }

        UserTask userTask = new UserTask();
        userTask.setTaskId(id);
        userTask.setUserId(userId);
        userTask.setAccepted(0);
        userTask.setContribution(BigDecimal.valueOf(0));
        userTask.setSubmitTime(null);
        userTask.setDeleted(0);

        boolean save = userTaskService.save(userTask);
        return save ? R.success("成功添加") : R.error("添加异常");
    }

    @ApiOperation("分页查询我所有的单子")
    @GetMapping("/getPageMyUserTask")
    public R<Page<UserTask>> getPageMyUserTask(@RequestParam(value = "page", defaultValue = "1") int page,
                                               @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                                               @RequestParam("name") String name) {
        Page<UserTask> userTaskPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<UserTask> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserTask::getUserId, BaseContext.getCurrentId());
        // .like(StringUtils.isBlank(name), UserTask::getName, name)
        queryWrapper.orderByDesc(UserTask::getUpdateTime);
        userTaskPage = userTaskService.page(userTaskPage, queryWrapper);
        return R.success(userTaskPage);
    }

    @ApiOperation("不想干了，摆烂")
    @DeleteMapping("/deleteUserTaskById/{id}")
    public R<String> deleteUserTaskById(@PathVariable Long id) {
        boolean b = userTaskService.removeById(id);
        return b ? R.success("摆烂成功") : R.error("摆烂失败");
    }

    @ApiOperation("查询任务领单人员")
    @GetMapping("/getUserTaskById/{id}")
    public R<Page<UserTask>> getUserTaskById(@PathVariable("id") Long id, @RequestParam(value = "page", defaultValue = "1") int page,
                                             @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        LambdaQueryWrapper<UserTask> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserTask::getTaskId, id);
        Page<UserTask> taskPage = new Page<>(page, pageSize);
        taskPage = userTaskService.page(taskPage, queryWrapper);
        return R.success(taskPage);
    }

    @ApiOperation("设置答案被接受,要给钱了")
    @PutMapping("/setAccept")
    @Transactional
    public R<String> setAccept(@RequestParam Long id) {
        UserTask userTask = userTaskService.getById(id);
        userTask.setAccepted(1);
        userTaskService.updateById(userTask);

        Task task = taskService.getById(userTask.getTaskId());
        task.setFinished(1);
        taskService.updateById(task);

        User from = userService.getById(BaseContext.getCurrentId());
        User to = userService.getById(userTask.getUserId());
        Token from_token = tokenService.getById(from.getTokenId());
        Token to_token = tokenService.getById(to.getTokenId());
        from_token.setBlockToken(from_token.getBlockToken().subtract(task.getToken()));
        to_token.setCurrentToken(to_token.getCurrentToken().add(task.getToken()));
        tokenService.updateById(from_token);
        tokenService.updateById(to_token);

        TokenDeal tokenDeal = new TokenDeal();
        tokenDeal.setFromUser(BaseContext.getCurrentId());
        tokenDeal.setToUser(userTask.getUserId());
        tokenDeal.setValue(task.getToken());
        tokenDeal.setTaskId(task.getId());
        tokenDeal.setDeleted(0);
        boolean save = tokenDealService.save(tokenDeal);

        return save ? R.success("修改成功，并成功付钱") : R.error("修改失败");
    }
}

