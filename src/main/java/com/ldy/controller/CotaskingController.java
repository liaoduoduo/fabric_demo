package com.ldy.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldy.common.BaseContext;
import com.ldy.common.R;
import com.ldy.dto.CotaskingDto;
import com.ldy.entity.Cotasking;
import com.ldy.entity.CotaskingIntelligence;
import com.ldy.service.ICotaskingIntelligenceService;
import com.ldy.service.ICotaskingService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sunqing
 * @since 2022-07-24
 */
@Slf4j
@RestController
@RequestMapping("/cotasking")
public class CotaskingController {

    @Autowired
    ICotaskingService cotaskingService;

    @ApiOperation("用于生成协同任务")
    @PostMapping("/save")
    @Transactional
    public R<String> saveCotask(@RequestBody CotaskingDto cotaskingDto) {
        log.info(cotaskingDto.toString());
        if (StringUtils.isBlank(cotaskingDto.getIntelligenceIds()) || StringUtils.isBlank(cotaskingDto.getName())
                || StringUtils.isBlank(cotaskingDto.getDescription())) {
            return R.error("添加异常");
        }
        return cotaskingService.addCotakAndBindIntelligences(cotaskingDto);
    }

    @ApiOperation("用于分页查询，并兼顾模糊查询")
    @GetMapping("/getPage")
    public R<Page<Cotasking>> getPage(@RequestParam(value = "page", defaultValue = "1") int page,
                                      @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                                      String name) {
        Page<Cotasking> cotaskPage = new Page<>(page, pageSize);
        // 当查询所有值时，将queryWrapper = null
        LambdaQueryWrapper<Cotasking> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Cotasking::getCreateUser, BaseContext.getCurrentId());
        queryWrapper.like(!StringUtils.isBlank(name), Cotasking::getName, name).orderByDesc(Cotasking::getUpdateTime);
        Page<Cotasking> cotaskingPage = cotaskingService.page(cotaskPage, queryWrapper);
        List<Cotasking> records = cotaskingPage.getRecords();
        records.forEach(System.out::println);
        return R.success(cotaskingPage);
    }


    @ApiOperation("根据id查询协同任务详情")
    @GetMapping("/getCotaskById/{id}")
    public R<Cotasking> getCotaskById(@PathVariable Long id) {
        Cotasking cotasking = cotaskingService.getById(id);
        return R.success(cotasking);
    }


    @ApiOperation("修改协同任务的简述与详细描述")
    @PutMapping("/update")
    public R<String> updateCotask(@RequestBody Cotasking cotasking) {
        if (cotasking.getId() == 0) {
            return R.error("修改失败");
        }
        Cotasking cotasking1 = cotaskingService.getById(cotasking.getId());
        if (cotasking1.getStatus() == 0) {
            return R.error("修改失败: 未激活状态");
        }
        boolean result = cotaskingService.updateById(cotasking);
        return result ? R.success("修改成功") : R.error("修改失败");
    }

    @ApiOperation("逻辑删除协同任务,并级联删除其中未被领取的研判任务与解除与情报的关联")
    @DeleteMapping("/delete")
    @Transactional
    public R<String> deleteByIds(@RequestParam(value = "ids", required = false) Long[] ids) {
        if (ids.length < 1) {
            return R.error("未选择协同任务数据");
        }
        return cotaskingService.removeCotaskByIds(ids);
    }

    @ApiOperation("修改协同任务的激活状态，并级联修改其中所有的研判任务，已被领取不受影响")
    @PutMapping("/updateStatus")
    @Transactional
    public R<String> updateStatus(@RequestBody CotaskingDto cotaskingDto) {
        String regex = ",";
        String[] split = cotaskingDto.getIds().split(regex);
        Long[] ids = new Long[split.length];
        int count = 0;
        for (String s : split) {
            ids[count] = Long.parseLong(s);
            count++;
        }
        return cotaskingService.updateStatus(ids, cotaskingDto.getStatus());
    }

    @ApiOperation("修改协同任务达到立案标准，并设置不可更改--未激活")
    @PutMapping("/liAn/{id}")
    public R<String> liAn(@PathVariable("id") Long id) {
        Cotasking cotasking = new Cotasking();
        cotasking.setId(id);
        cotasking.setStatus(0);
        cotasking.setLiAn(1);
        boolean b = cotaskingService.updateById(cotasking);
        return b ? R.success("修改成功") : R.error("修改失败");
    }


}

