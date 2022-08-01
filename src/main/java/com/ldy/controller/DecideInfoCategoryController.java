package com.ldy.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ldy.common.R;
import com.ldy.entity.DecideInfoCategory;
import com.ldy.service.IDecideInfoCategoryService;
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
@RequestMapping("/decide-info-category")
public class DecideInfoCategoryController {
    @Autowired
    IDecideInfoCategoryService decideInfoCategoryService;

    @ApiOperation("根据研判任务分类获取研判后信息的分类")
    @GetMapping("/getDecideInfoCategoryByTaskCategoryId/{id}")
    public R<List<DecideInfoCategory>> getDecideInfoCategoryByTaskCategoryId(@PathVariable("id") Long tcId) {

        LambdaQueryWrapper<DecideInfoCategory> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(DecideInfoCategory::getTaskCategoryId, tcId);

        List<DecideInfoCategory> list = decideInfoCategoryService.list(queryWrapper);
        return R.success(list);
    }
}

