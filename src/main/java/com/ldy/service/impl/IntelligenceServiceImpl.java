package com.ldy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.mapper.IntelligenceMapper;
import com.ldy.entity.Intelligence;
import com.ldy.service.IntelligenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ldy
 * @Date 2022/7/1 17:20
 * @ClassName IntelligenceServiceImpl
 * @Description 情报业务实现类
 * @Version v1.0
 */
@Service
public class IntelligenceServiceImpl extends ServiceImpl<IntelligenceMapper, Intelligence> implements IntelligenceService {
    @Autowired
    private IntelligenceMapper intelligenceMapper;

    @Override
    public Intelligence queryLatestIntelligence(Long userId) {
        return intelligenceMapper.queryLatestIntelligence(userId);
    }

    @Override
    public void batchStatus(Integer status, List<Long> ids) {
        for (Long id : ids) {
            Intelligence intelligence = this.getById(id);
            intelligence.setStatus(status);
            this.updateById(intelligence);
        }
    }
}
