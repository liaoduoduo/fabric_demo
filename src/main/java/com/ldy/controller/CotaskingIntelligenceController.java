package com.ldy.controller;

import com.ldy.common.R;
import com.ldy.entity.CotaskingIntelligence;
import com.ldy.service.ICotaskingIntelligenceService;
import com.ldy.service.IntelligenceService;
import com.ldy.vo.CotaskIntelligenceVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
@RequestMapping("/cotasking-intelligence")
public class CotaskingIntelligenceController {

    @Autowired
    ICotaskingIntelligenceService cotaskingIntelligenceService;

    @ApiOperation("查询协同任务中的情报")
    @GetMapping("/getIntelligencesInCotask/{id}")
    public R<List<CotaskIntelligenceVo>> getIntelligencesByCotask(@PathVariable("id") Long id) {
        if (id == 0) {
            return R.error("查询失败");
        }
        List<CotaskIntelligenceVo> list = cotaskingIntelligenceService.getIntelligencesInCotask(id);
        return R.success(list);
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

