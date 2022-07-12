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
        //获取当前登录用户id
        Long userId = BaseContext.getCurrentId();

        //分页查询用户发布的情报
        Page<Intelligence> intelligencePage = new Page<>(page, pageSize);
        Page<IntelligenceDto> intelligenceDtoPage = new Page<>();
        LambdaQueryWrapper<Intelligence> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.like(name != null, Intelligence::getName, name).eq(Intelligence::getUserId, userId);
        intelligenceService.page(intelligencePage, queryWrapper1);

        //查询用户已购买情报表
        LambdaQueryWrapper<UserIntelligence> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(UserIntelligence::getUserId, userId);
        List<UserIntelligence> UserIntelligenceList = userIntelligenceService.list(queryWrapper2);

        //根据查询用户已购买情报表获得的UserIntelligenceList
        //获取用户已购买情报的实体类
        //将实体类追加到分页查询结果（records）中
        for (UserIntelligence userIntelligence : UserIntelligenceList) {
            Long intelligenceId = userIntelligence.getIntelligenceId();
            Intelligence intelligence = intelligenceService.getById(intelligenceId);
            List<Intelligence> records = intelligencePage.getRecords();
            records = new ArrayList<>(records);
            records.add(intelligence);
            intelligencePage.setRecords(records);
        }

        //对象拷贝，records属性除外
        BeanUtils.copyProperties(intelligencePage, intelligenceDtoPage, "records");

        //创建intelligenceDto数据传输对象列表
        List<IntelligenceDto> list = new ArrayList<>();

        //遍历每一条记录
        for (Intelligence record : intelligencePage.getRecords()) {
            IntelligenceDto intelligenceDto = new IntelligenceDto();

            //将该Intelligence记录拷贝到intelligenceDto中
            BeanUtils.copyProperties(record, intelligenceDto);

            //将分类名，数据来源，追加到intelligenceDto中
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
