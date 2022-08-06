package com.ldy.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldy.common.BaseContext;
import com.ldy.common.R;
import com.ldy.entity.Cotasking;
import com.ldy.entity.CotaskingIntelligence;
import com.ldy.service.ICotaskingIntelligenceService;
import com.ldy.service.ICotaskingService;
import io.swagger.annotations.ApiOperation;
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
@RestController
@RequestMapping("/cotasking")
public class CotaskingController {

    @Autowired
    ICotaskingService cotaskingService;

    @Autowired
    ICotaskingIntelligenceService cotaskingIntelligenceService;


    @ApiOperation("用于生成协同任务")
    @PostMapping("/save")
    @Transactional
    public R<String> saveCotask(@RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "description", required = false) String description,
                                @RequestParam(value = "intelligenceIds", required = false) Long[] intelligenceIds) {
        if (intelligenceIds.length < 1 || StringUtils.isBlank(name) || StringUtils.isBlank(description)) {
            return R.error("添加异常");
        }
        Cotasking cotasking = new Cotasking();
        cotasking.setName(name);
        cotasking.setDescription(description);
        cotasking.setStatus(1);
        cotasking.setFinished(0);
        cotasking.setDeleted(0);
        boolean save = cotaskingService.save(cotasking);
        Long cotaskingId = cotasking.getId();
        if (save) {
            for (Long intelligenceId : intelligenceIds) {
                CotaskingIntelligence cotaskingIntelligence = new CotaskingIntelligence();
                cotaskingIntelligence.setCotaskingId(cotaskingId);
                cotaskingIntelligence.setIntelligenceId(intelligenceId);
                cotaskingIntelligence.setDeleted(0);
                cotaskingIntelligenceService.save(cotaskingIntelligence);
            }
        }
        return save ? R.success("成功添加") : R.error("添加异常");
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

    @ApiOperation("逻辑删除协同任务")
    @DeleteMapping("/delete")
    public R<String> deleteById(@RequestParam(value = "ids", required = false) Long[] ids) {
        boolean result = false;
        if (ids.length == 1) {
            result = cotaskingService.removeById(ids[0]);
        }
        if (ids.length > 1) {
            result = cotaskingService.removeByIds(Arrays.asList(ids));
        }
        return result ? R.success("删除成功") : R.error("删除失败");
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

    @ApiOperation("修改协同任务的激活状态")
    @PutMapping("/updateStatus")
    public R<String> updateStatus(@RequestBody Cotasking cotasking) {
        boolean b = cotaskingService.updateById(cotasking);
        return b ? R.success("修改成功") : R.error("修改失败");
    }

    @ApiOperation("修改协同任务的完成状态，并设置不可更改--未激活")
    @PutMapping("/updateFinished/{id}")
    public R<String> updateFinished(@PathVariable("id") Long id) {
        Cotasking cotasking = new Cotasking();
        cotasking.setId(id);
        cotasking.setStatus(0);
        cotasking.setFinished(1);
        boolean b = cotaskingService.updateById(cotasking);
        return b ? R.success("修改成功") : R.error("修改失败");
    }


}

