package com.ldy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldy.common.R;
import com.ldy.dto.BuyIntelligenceDto;
import com.ldy.entity.Intelligence;
import com.ldy.entity.UserIntelligence;
import com.ldy.service.IntelligenceService;
import com.ldy.service.UserIntelligenceService;
import com.ldy.vo.IntelligenceVo;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    private UserIntelligenceService userIntelligenceService;


    /**
     * @param page
     * @param pageSize
     * @param name
     * @return com.ldy.common.R<com.baomidou.mybatisplus.extension.plugins.pagination.Page < com.ldy.vo.IntelligenceVo>>
     * @description 分页查询
     * @date 2022/7/22 14:22
     */
    @GetMapping("/page")
    public R<Page<IntelligenceVo>> page(int page, int pageSize, String name) {
        return R.success(intelligenceService.pageQuery(page, pageSize, name));
    }

    /**
     * @param intelligence
     * @return [com.ldy.entity.Intelligence]
     * @description 新增情报
     * @date 2022/7/22 11:34
     */
    @PostMapping
    public R<String> save(@RequestBody Intelligence intelligence) {
        intelligenceService.saveByUserIntelligence(intelligence);
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


    @PostMapping("/buy")
    public R<String> buy(@RequestBody BuyIntelligenceDto buyIntelligenceDto) {
        Long id = buyIntelligenceDto.getId();
        String password = buyIntelligenceDto.getPassword();
        return intelligenceService.buy(id, password);
    }

    @PostMapping("/status/{status}")
    public R<String> editStatus(@PathVariable Integer status, @RequestParam List<Long> ids) {
        intelligenceService.batchStatus(status, ids);
        return R.success("修改状态成功！");
    }

    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids) {
        return intelligenceService.deleteBatch(ids);
    }

}
