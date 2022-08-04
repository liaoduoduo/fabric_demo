package com.ldy.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ldy.common.R;
import com.ldy.entity.DecideInfo;
import com.ldy.entity.Task;
import com.ldy.service.IDecideInfoService;
import com.ldy.service.ITaskService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/decide-info")
public class DecideInfoController {
    @Autowired
    ITaskService taskService;

    @Autowired
    IDecideInfoService decideInfoService;

    @ApiOperation("根据研判任务获取需要填写的内容")
    @GetMapping("/getTaskFieldById/{id}")
    public R<List<DecideInfo>> getTaskFieldById(@PathVariable Long id) {
        Task task = taskService.getById(id);
        Long decideInfoCategoryId = task.getDecideInfoCategoryId();
        LambdaQueryWrapper<DecideInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DecideInfo::getDecideInfoCategoryId, decideInfoCategoryId).select(DecideInfo::getName, DecideInfo::getNameZh);
        List<DecideInfo> list = decideInfoService.list(queryWrapper);
        return R.success(list);
    }



}

