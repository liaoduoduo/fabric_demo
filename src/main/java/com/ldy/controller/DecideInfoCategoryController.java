package com.ldy.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ldy.common.R;
import com.ldy.entity.DecideInfoCategory;
import com.ldy.entity.TaskCategory;
import com.ldy.service.IDecideInfoCategoryService;
import com.ldy.service.ITaskCategoryService;
import com.ldy.vo.TaskCategoryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sunqing
 * @since 2022-07-24
 */
@RestController
@RequestMapping("/decide-info-category")
public class DecideInfoCategoryController {
    @Autowired
    IDecideInfoCategoryService decideInfoCategoryService;

    @Autowired
    ITaskCategoryService taskCategoryService;

    @ApiOperation("根据研判任务分类获取研判后信息的分类")
    @GetMapping("/getDecideInfoCategoryByTaskCategoryId/{id}")
    public R<List<DecideInfoCategory>> getDecideInfoCategoryByTaskCategoryId(@PathVariable("id") Long tcId) {

        LambdaQueryWrapper<DecideInfoCategory> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(DecideInfoCategory::getTaskCategoryId, tcId);

        List<DecideInfoCategory> list = decideInfoCategoryService.list(queryWrapper);
        return R.success(list);
    }

    @ApiOperation("根据研判任务分类获取研判后信息的分类")
    @GetMapping("/getDecideInfoCategoryWithTaskCategory")
    public R<List<TaskCategoryVo>> getDecideInfoCategoryWithTaskCategory() {
        List<TaskCategoryVo> taskCategoryVos = new ArrayList<>();
        List<TaskCategory> list = taskCategoryService.list();
        for (TaskCategory taskCategory : list) {
            TaskCategoryVo taskCategoryVo = new TaskCategoryVo();
            taskCategoryVo.setId(taskCategory.getId());
            taskCategoryVo.setName(taskCategory.getName());
            taskCategoryVos.add(taskCategoryVo);
        }
        for (TaskCategoryVo taskCategoryVo : taskCategoryVos) {
            LambdaQueryWrapper<DecideInfoCategory> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(DecideInfoCategory::getTaskCategoryId, taskCategoryVo.getId());
            taskCategoryVo.setChildren(decideInfoCategoryService.list(queryWrapper));
        }
        return R.success(taskCategoryVos);
    }
}

