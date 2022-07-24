package com.ldy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldy.common.R;
import com.ldy.entity.Intelligence;
import com.ldy.vo.IntelligenceVo;

import java.util.List;

/**
 * @Author ldy
 * @Date 2022/7/1 17:20
 * @ClassName IntelligenceService
 * @Description 情报业务层
 * @Version v1.0
 */
public interface IntelligenceService extends IService<Intelligence> {

    void batchStatus(Integer status, List<Long> ids);

    void saveByUserIntelligence(Intelligence intelligence);

    R<String> buy(Long intelligenceId);

    Page<IntelligenceVo> pageQuery(int page, int pageSize, String name);

    R<String> deleteBatch(List<Long> ids);

}
