package com.ldy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldy.common.BaseContext;
import com.ldy.common.R;
import com.ldy.dto.IntelligenceDto;
import com.ldy.entity.Intelligence;
import com.ldy.service.CategoryService;
import com.ldy.service.IntelligenceService;
import com.ldy.service.UserIntelligenceService;
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

    @Resource
    private UserIntelligenceService userIntelligenceService;

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

    /**
     * @param id
     * @return com.ldy.common.R<com.ldy.pojo.Intelligence>
     * @description 根据id获取情报，用于修改情报时，根据id查询回显数据到修改页面
     * @date 2022/7/10 17:03
     */
    @GetMapping("/{id}")
    public R<Intelligence> queryIntelligenceById(@PathVariable Long id) {
        Intelligence intelligence = intelligenceService.getById(id);
        return R.success(intelligence);
    }


    /**
     * @param intelligence
     * @return com.ldy.common.R<java.lang.String>
     * @description 修改情报
     * @date 2022/7/11 16:57
     */
    @PutMapping
    public R<String> update(Intelligence intelligence) {
        intelligenceService.updateById(intelligence);
        /*Long intelligenceId = intelligence.getId();
        LambdaQueryWrapper<UserIntelligence> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserIntelligence::getIntelligenceId, intelligenceId);
        int count = userIntelligenceService.count(queryWrapper);
        if (count > 1) {
            //说明除了情报发布者，还有其他用户购买了该情报，此时不允许修改情报了
            return R.error("该情报已经有人获取过，无法修改情报！")
        }*/
        return R.success("修改情报成功");
    }

    @PostMapping("/buy")
    public R<String> buy(Long id) {
/*        UserIntelligence userIntelligence = new UserIntelligence();
        userIntelligence.setUserId(BaseContext.getCurrentId());
        userIntelligence.setIntelligenceId(id);
        userIntelligenceService.save(userIntelligence);*/
        return R.success("购买成功");
    }


}
