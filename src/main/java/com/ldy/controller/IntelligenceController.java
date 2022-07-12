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
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
        Long userId = BaseContext.getCurrentId();
        intelligenceDto.setUserId(userId);
        intelligenceService.save(intelligenceDto);
        //保存到区块链中，key：id(未完成）
        Intelligence intelligence = intelligenceService.queryLatestIntelligence(userId);
        Long intelligenceId = intelligence.getId();

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
    public R<String> update(@RequestBody Intelligence intelligence) {
        Long intelligenceId = intelligence.getId();
        LambdaQueryWrapper<UserIntelligence> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserIntelligence::getIntelligenceId, intelligenceId);
        int count = userIntelligenceService.count(queryWrapper);
        if (count > 1) {
            //说明除了情报发布者，还有其他用户购买了该情报，此时不允许修改情报了
            return R.error("该情报已经有人获取过，无法修改情报！");
        }
        intelligenceService.updateById(intelligence);
        //在区块链中更新（未完成）
        return R.success("修改情报成功");
    }

    /**
     * @description 购买情报
     * @date 2022/7/12 16:50
     * @param id
     * @return com.ldy.common.R<java.lang.String>
     */
    @PostMapping("/buy")
    public R<String> buy(Long id) {
        //判断用户token余额（未完成）
        //根据该情报价格，扣除token，并存到区块链（未完成）
        log.info(id.toString());
        //获取该情报实体
        Intelligence intelligence = intelligenceService.getById(id);

        //获取当前登录用户id
        Long userId = BaseContext.getCurrentId();

        //判断该情报是否停售
        if (intelligence.getStatus()!=1) {
            return R.error("该情报已停售，无法购买！");
        }

        //判断该用户买的是不是自己发布的情报
        if (intelligence.getUserId().equals(userId)) {
            return R.error("不能购买自己发布的情报！");
        }
        //判断该用户是不是已拥有该情报了
        LambdaQueryWrapper<UserIntelligence> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserIntelligence::getIntelligenceId, intelligence.getId())
                .eq(UserIntelligence::getUserId, userId);

        if (userIntelligenceService.count(queryWrapper) > 0) {
            return R.error("该情报你已拥有！");
        }

        UserIntelligence userIntelligence = new UserIntelligence();
        userIntelligence.setUserId(userId);
        userIntelligence.setIntelligenceId(id);
        String fileHash = intelligence.getFileHash();
        userIntelligence.setFileHash(fileHash);

        //购买成功，保存到数据库中
        userIntelligenceService.save(userIntelligence);

        //与此同时保存到区块链（未完成）
        UserIntelligence newUserIntelligence = userIntelligenceService.queryLatestUserIntelligence(userId);
        //newUserIntelligence为刚刚保存的用户购买情报的一条记录，由mybatis自动装填了主键id
        //根据这个id将newUserIntelligence的剩余字段保存到区块链中
        log.info("刚刚保存的用户拥有的情报记录数据id为：{}", newUserIntelligence.getId());
        return R.success("购买成功");
    }

    @PostMapping("/status/{status}")
    public R<String> editStatus(@PathVariable Integer status, @RequestParam List<Long> ids) {
        intelligenceService.batchStatus(status, ids);
        return R.success("修改状态成功！");
    }


}
