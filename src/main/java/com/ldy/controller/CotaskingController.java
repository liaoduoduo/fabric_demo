package com.ldy.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldy.common.R;
import com.ldy.entity.Cotasking;
import com.ldy.entity.CotaskingIntelligence;
import com.ldy.entity.Intelligence;
import com.ldy.entity.User;
import com.ldy.service.ICotaskingIntelligenceService;
import com.ldy.service.ICotaskingService;
import com.ldy.service.IntelligenceService;
import com.ldy.service.UserService;
import com.ldy.vo.CotaskingVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

    @Autowired
    private UserService userService;

    @Autowired
    private IntelligenceService intelligenceService;

    @ApiOperation("用于生成协同任务")
    @PostMapping("/save")
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
    public R<Page<CotaskingVo>> getPage(@RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                        String name) {
        Page<Cotasking> cotaskPage = new Page<>(page, pageSize);
        // 当查询所有值时，将queryWrapper = null
        LambdaQueryWrapper<Cotasking> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!StringUtils.isBlank(name), Cotasking::getName, name).orderByDesc(Cotasking::getUpdateTime);
        cotaskingService.page(cotaskPage, queryWrapper);
        Page<CotaskingVo> cotaskVoPage = new Page<>();
        BeanUtils.copyProperties(cotaskPage, cotaskVoPage, "records");
        List<CotaskingVo> list = new ArrayList<>();

        for (Cotasking record : cotaskPage.getRecords()) {
            CotaskingVo cotaskingVo = new CotaskingVo();
            BeanUtils.copyProperties(record, cotaskingVo);
            Long createUserId = record.getCreateUser();
            String user = userService.getById(createUserId).getName();
            cotaskingVo.setUser(user);
            list.add(cotaskingVo);
        }
        System.out.println(list);
        cotaskVoPage.setRecords(list);
        return R.success(cotaskVoPage);
    }

    @ApiOperation("逻辑删除协同任务")
    @DeleteMapping("/delete")
    public R<String> deleteById(@RequestParam(value = "intelligenceIds", required = false) Long[] intelligenceIds) {
        boolean result = false;
        if (intelligenceIds.length == 1) {
            result = cotaskingService.removeById(intelligenceIds[0]);

        }
        if (intelligenceIds.length > 1) {
            result = cotaskingService.removeByIds(Arrays.asList(intelligenceIds));
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

    @ApiOperation("获取协同任务详细信息以及包含的情报")
    @GetMapping("/getById")
    public R<CotaskingVo> getInfoById(Long id) {
        Cotasking cotask = cotaskingService.getById(id);
        LambdaQueryWrapper<CotaskingIntelligence> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CotaskingIntelligence::getCotaskingId, id);
        List<CotaskingIntelligence> cotaskingIntelligences = cotaskingIntelligenceService.list(queryWrapper);
        CotaskingVo cotaskingVo = new CotaskingVo();
        BeanUtils.copyProperties(cotask, cotaskingVo);
        ArrayList<String> list = new ArrayList<>();
        for (CotaskingIntelligence cotaskingIntelligence : cotaskingIntelligences) {
            Long intelligenceId = cotaskingIntelligence.getIntelligenceId();
            Intelligence intelligence = intelligenceService.getById(intelligenceId);
            String name = intelligence.getName();
            list.add(name);
        }
        cotaskingVo.setIntelligenceNames(list);
        return R.success(cotaskingVo);
    }


}

