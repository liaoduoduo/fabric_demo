package com.ldy.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ldy.common.R;
import com.ldy.entity.TaskDetail;
import com.ldy.entity.UserTask;
import com.ldy.service.ITaskDetailService;
import com.ldy.service.IUserTaskService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sunqing
 * @since 2022-07-24
 */
@RestController
@RequestMapping("/task-detail")
public class TaskDetailController {
    @Autowired
    ITaskDetailService taskDetailService;

    @ApiOperation("开卷--填写答案")
    @PostMapping("/getMoney")
    public R<String> getMoney(@RequestBody TaskDetail taskDetail) {
        taskDetail.setDeleted(0);
        taskDetail.setSubmitTime(LocalDateTime.now());
        boolean save = taskDetailService.save(taskDetail);
        return save ? R.success("填写完成") : R.error("添加失败");
    }

    @ApiOperation("查看答案")
    @GetMapping("getTaskDetailByid/{id}")
    public R<TaskDetail> getTaskDetailByid(@PathVariable Long id) {
        LambdaQueryWrapper<TaskDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TaskDetail::getUserTaskId, id).orderByDesc(TaskDetail::getUpdateTime);
        TaskDetail taskDetail = taskDetailService.getOne(queryWrapper);
        return R.success(taskDetail);
    }

/*    @ApiOperation("修改答案")
    @PutMapping("/updateTaskDetail")
    public R<String> updateTaskDetail(@RequestBody TaskDetail taskDetail) {
        boolean b = taskDetailService.updateById(taskDetail);
        return b ? R.success("修改完成") : R.error("修改失败");
    }*/
}

