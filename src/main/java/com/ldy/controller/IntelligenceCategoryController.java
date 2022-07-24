package com.ldy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldy.common.R;
import com.ldy.entity.IntelligenceCategory;
import com.ldy.service.IntelligenceCategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author ldy
 * @Date 2022/7/4 16:07
 * @ClassName CategoryController
 * @Description 分类控制器
 * @Version v1.0
 */
@RequestMapping("/intelligenceCategory")
@RestController
public class IntelligenceCategoryController {

    @Resource
    private IntelligenceCategoryService intelligenceCategoryService;

    /**
     * @description 分页查询
     * @date 2022/7/4 16:21
     * @param page
     * @param pageSize
     * @return com.ldy.common.R<com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.ldy.pojo.Category>>
     */
    @GetMapping("/page")
    public R<Page<IntelligenceCategory>> page(int page, int pageSize) {
        Page<IntelligenceCategory> categoryPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<IntelligenceCategory> queryWrapper = new LambdaQueryWrapper<>();
        intelligenceCategoryService.page(categoryPage,queryWrapper);
        return R.success(categoryPage);
    }

    /**
     * @description 新增情报分类
     * @date 2022/7/4 16:21
     * @param intelligenceCategory
     * @return com.ldy.common.R<java.lang.String>
     */
    @PostMapping
    public R<String> addCategory(@RequestBody IntelligenceCategory intelligenceCategory) {
        intelligenceCategoryService.save(intelligenceCategory);
        //存储到区块链中
        //。。。。。。。。。
        return R.success("新增情报分类成功");
    }

    /**
     * @description 根据条件查询分类数据(回显)
     * @date 2022/7/4 16:27
     * @param
     * @return com.ldy.common.R<java.util.List<com.ldy.pojo.Category>>
     */
    @GetMapping("/list")
    public R<List<IntelligenceCategory>> list() {
        //条件构造器
        LambdaQueryWrapper<IntelligenceCategory> queryWrapper = new LambdaQueryWrapper<>();
        //添加排序条件
        queryWrapper.orderByDesc(IntelligenceCategory::getUpdateTime);
        List<IntelligenceCategory> list = intelligenceCategoryService.list(queryWrapper);
        return R.success(list);
    }


}
