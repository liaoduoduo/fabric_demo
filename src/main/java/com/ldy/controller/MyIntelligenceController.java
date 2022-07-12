package com.ldy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldy.common.BaseContext;
import com.ldy.common.R;
import com.ldy.dto.IntelligenceDto;
import com.ldy.entity.Intelligence;
import com.ldy.entity.UserIntelligence;
import com.ldy.service.CategoryService;
import com.ldy.service.IntelligenceService;
import com.ldy.service.UserIntelligenceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ldy
 * @Date 2022/7/12 16:58
 * @ClassName MyIntelligenceController
 * @Description 我的情报 控制器
 * @Version v1.0
 */
@RestController
@RequestMapping("/myIntelligence")
public class MyIntelligenceController {

    @Autowired
    private IntelligenceService intelligenceService;

    @Autowired
    private UserIntelligenceService userIntelligenceService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/page")
    public R<Page<IntelligenceDto>> page(int page, int pageSize, String name) {
        Long userId = BaseContext.getCurrentId();
        Page<Intelligence> intelligencePage = new Page<>(page, pageSize);
        Page<IntelligenceDto> intelligenceDtoPage = new Page<>();
        LambdaQueryWrapper<Intelligence> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.like(name != null, Intelligence::getName, name).eq(Intelligence::getUserId, userId);
        intelligenceService.page(intelligencePage, queryWrapper1);

        LambdaQueryWrapper<UserIntelligence> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(UserIntelligence::getUserId, userId);
        List<UserIntelligence> UserIntelligenceList = userIntelligenceService.list(queryWrapper2);

        for (UserIntelligence userIntelligence : UserIntelligenceList) {
            Long intelligenceId = userIntelligence.getIntelligenceId();
            Intelligence intelligence = intelligenceService.getById(intelligenceId);
            List<Intelligence> records = intelligencePage.getRecords();
            records = new ArrayList<>(records);
            records.add(intelligence);
            intelligencePage.setRecords(records);
        }


        BeanUtils.copyProperties(intelligencePage, intelligenceDtoPage, "records");

        List<IntelligenceDto> list = new ArrayList<>();
        for (Intelligence record : intelligencePage.getRecords()) {
            IntelligenceDto intelligenceDto = new IntelligenceDto();
            BeanUtils.copyProperties(record, intelligenceDto);
            intelligenceDto.setCategoryName(categoryService.getById(record.getCategoryId()).getName());

            if (record.getUserId().equals(userId)) {
                intelligenceDto.setOrigin("本人发布");
            } else {
                intelligenceDto.setOrigin("购买他人");
            }
            list.add(intelligenceDto);
        }

        intelligenceDtoPage.setRecords(list);
        return R.success(intelligenceDtoPage);

    }

}
