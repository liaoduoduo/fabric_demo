package com.ldy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.entity.CotaskingIntelligence;
import com.ldy.entity.Intelligence;
import com.ldy.mapper.CotaskingIntelligenceMapper;
import com.ldy.service.ICotaskingIntelligenceService;
import com.ldy.vo.CotaskIntelligenceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sunqing
 * @since 2022-07-24
 */
@Service
public class CotaskingIntelligenceServiceImpl extends ServiceImpl<CotaskingIntelligenceMapper, CotaskingIntelligence> implements ICotaskingIntelligenceService {

    @Autowired
    CotaskingIntelligenceMapper cotaskingIntelligenceMapper;

    @Override
    public List<CotaskIntelligenceVo> getIntelligencesInCotask(Long cid) {
        return cotaskingIntelligenceMapper.getIntelligencesInCotask(cid);
    }
}
