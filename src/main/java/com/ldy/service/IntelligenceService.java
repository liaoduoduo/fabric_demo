package com.ldy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ldy.entity.Intelligence;

import java.util.List;

/**
 * @Author ldy
 * @Date 2022/7/1 17:20
 * @ClassName IntelligenceService
 * @Description 情报业务层
 * @Version v1.0
 */
public interface IntelligenceService extends IService<Intelligence> {
    Intelligence queryLatestIntelligence(Long userId);

    void batchStatus(Integer status, List<Long> ids);
}
