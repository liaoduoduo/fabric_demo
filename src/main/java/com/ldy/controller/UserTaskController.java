package com.ldy.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldy.common.BaseContext;
import com.ldy.common.R;
import com.ldy.entity.*;
import com.ldy.service.*;
import com.ldy.vo.TaskVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
        return userTaskService.saveUserTask(id);
    }

    @ApiOperation("查询当前用户的所有接单")
    @GetMapping("/getUserTaskByUserId")
    public R<Page<TaskVo>> getUserTaskByUserId(@RequestParam(value = "page", defaultValue = "1") int page,
                                               @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                                               @RequestParam("name") String name) {
        Page<TaskVo> taskVoPage = new Page<>(page, pageSize);
        Long userId = BaseContext.getCurrentId();
        return R.success(userTaskService.getUserTaskByUserId(taskVoPage, name, userId));
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

