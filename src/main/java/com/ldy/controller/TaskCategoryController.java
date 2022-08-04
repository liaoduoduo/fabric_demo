package com.ldy.controller;


import com.ldy.common.R;
import com.ldy.entity.TaskCategory;
import com.ldy.service.ITaskCategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sunqing
 * @since 2022-07-24
 */
@RestController
@RequestMapping("/task-category")
public class TaskCategoryController {

    @Autowired
    ITaskCategoryService taskCategoryService;

    @ApiOperation("获取所有的研判任务分类")
    @GetMapping("/getAllCategory")
    public R<List<TaskCategory>> getAllCategory() {
        List<TaskCategory> list = taskCategoryService.list();
        return R.success(list);
    }
}

