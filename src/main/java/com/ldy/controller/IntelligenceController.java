package com.ldy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldy.common.BaseContext;
import com.ldy.common.R;
import com.ldy.dto.IntelligenceDto;
import com.ldy.pojo.Intelligence;
import com.ldy.service.CategoryService;
import com.ldy.service.IntelligenceService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author ldy
 * @Date 2022/7/1 17:30
 * @ClassName IntelligenceController
 * @Description 情报数据控制层
 * @Version v1.0
 */
@RestController
@RequestMapping("/intelligence")
public class IntelligenceController {

    @Resource
    private IntelligenceService intelligenceService;

    @Resource
    private CategoryService categoryService;

    /**
     * @param page
     * @param pageSize
     * @param name
     * @return com.ldy.common.R<com.baomidou.mybatisplus.extension.plugins.pagination.Page < com.ldy.pojo.Intelligence>>
     * @description 分页查询
     * @date 2022/7/4 16:31
     */
    @GetMapping("/page")
    public R<Page<IntelligenceDto>> page(int page, int pageSize, String name) {
        Page<Intelligence> intelligencePage = new Page<>(page, pageSize);
        Page<IntelligenceDto> intelligenceDtoPage = new Page<>();
        LambdaQueryWrapper<Intelligence> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Intelligence::getName, name).orderByDesc(Intelligence::getUpdateTime);
        intelligenceService.page(intelligencePage, queryWrapper);
        //对象拷贝
        BeanUtils.copyProperties(intelligencePage, intelligenceDtoPage, "records");
        List<Intelligence> records = intelligencePage.getRecords();
        List<IntelligenceDto> list = new ArrayList<>();
        for (Intelligence record : records) {
            IntelligenceDto intelligenceDto = new IntelligenceDto();
            BeanUtils.copyProperties(record, intelligenceDto);
            Long categoryId = record.getCategoryId();
            if (categoryId != null) {
                intelligenceDto.setCategoryName(categoryService.getById(categoryId).getName());
                list.add(intelligenceDto);
            }
        }
        intelligenceDtoPage.setRecords(list);
        return R.success(intelligenceDtoPage);
    }

    /**
     * @param intelligenceDto
     * @return com.ldy.common.R<java.lang.String>
     * @description 新增情报
     * @date 2022/7/4 16:34
     */
    @PostMapping
    public R<String> save(@RequestBody IntelligenceDto intelligenceDto) {
        intelligenceDto.setUserId(BaseContext.getCurrentId());
        intelligenceService.save(intelligenceDto);
        //保存到区块链中，key：id
        return R.success("新增情报成功");
    }


}
