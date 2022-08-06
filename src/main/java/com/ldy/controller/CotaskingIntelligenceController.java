package com.ldy.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldy.common.R;
import com.ldy.entity.CotaskingIntelligence;
import com.ldy.entity.Intelligence;
import com.ldy.service.ICotaskingIntelligenceService;
import com.ldy.service.IntelligenceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sunqing
 * @since 2022-07-24
 */
@RestController
@RequestMapping("/cotasking-intelligence")
public class CotaskingIntelligenceController {

    @Autowired
    ICotaskingIntelligenceService cotaskingIntelligenceService;

    @Autowired
    IntelligenceService intelligenceService;

    @ApiOperation("查询协同任务中的情报")
    @GetMapping("/getIntelligencesInCotask/{id}")
    public R<Map<Long, Intelligence>> getIntelligencesByCotask(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                               @PathVariable("id") Long id) {
        if (id == 0) {
            return R.error("查询失败");
        }
        Page<CotaskingIntelligence> cotaskingIntelligencePage = new Page<>(page, pageSize);
        LambdaQueryWrapper<CotaskingIntelligence> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CotaskingIntelligence::getCotaskingId, id).orderByDesc(CotaskingIntelligence::getCreateTime);
        Page<CotaskingIntelligence> page1 = cotaskingIntelligenceService.page(cotaskingIntelligencePage, queryWrapper);
        Map<Long, Intelligence> intelligenceHashMap = new HashMap<>();

        for (CotaskingIntelligence record : page1.getRecords()) {
            Intelligence intelligence = intelligenceService.getById(record.getIntelligenceId());
            intelligenceHashMap.put(record.getId(), intelligence);
        }
        return R.success(intelligenceHashMap);
    }


    @ApiOperation("删除协同任务中的情报")
    @DeleteMapping("/delete/{id}")
    public R<String> deleteIntelligenceInCotask(@PathVariable("id") Long id) {
        boolean result = cotaskingIntelligenceService.removeById(id);
        return result ? R.success("删除成功") : R.error("删除失败");
    }

    @ApiOperation("为已存在的协同任务中增加情报")
    @PostMapping("/addIntelligenceToCotask")
    public R<String> addIntelligenceToCotask(@RequestParam(value = "intelligenceIds", required = false) Long[] intelligenceIds,
                                             @RequestParam("id") Long id) {

        if (id == 0 || intelligenceIds.length == 0){
            return R.error("添加异常");
        }
        ArrayList<CotaskingIntelligence> cotaskingIntelligences = new ArrayList<>();
        for (Long intelligenceId : intelligenceIds) {
            CotaskingIntelligence cotaskingIntelligence = new CotaskingIntelligence();
            cotaskingIntelligence.setCotaskingId(id);
            cotaskingIntelligence.setIntelligenceId(intelligenceId);
            cotaskingIntelligence.setDeleted(0);
            cotaskingIntelligences.add(cotaskingIntelligence);
        }
        boolean save = cotaskingIntelligenceService.saveBatch(cotaskingIntelligences);
        return save ? R.success("成功添加") : R.error("添加异常");
    }

}

