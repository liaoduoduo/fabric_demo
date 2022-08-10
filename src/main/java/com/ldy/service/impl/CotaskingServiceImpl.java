package com.ldy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.common.BaseContext;
import com.ldy.common.R;
import com.ldy.dto.CotaskingDto;
import com.ldy.entity.Cotasking;
import com.ldy.entity.CotaskingIntelligence;
import com.ldy.mapper.CotaskingIntelligenceMapper;
import com.ldy.mapper.CotaskingMapper;
import com.ldy.service.ICotaskingService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sunqing
 * @since 2022-07-24
 */
@Service
public class CotaskingServiceImpl extends ServiceImpl<CotaskingMapper, Cotasking> implements ICotaskingService {

    @Autowired
    CotaskingMapper cotaskingMapper;
    @Autowired
    CotaskingIntelligenceMapper cotaskingIntelligenceMapper;

    @Override
    public R<String> addCotakAndBindIntelligences(CotaskingDto cotaskingDto) {
        Cotasking cotasking = new Cotasking();
        cotasking.setName(cotaskingDto.getName());
        cotasking.setDescription(cotaskingDto.getDescription());
        cotasking.setStatus(1);
        cotasking.setFinished(0);
        cotasking.setDeleted(0);
        int save = cotaskingMapper.insert(cotasking);
        Long cotaskingId = cotasking.getId();
        Integer integer;
        if (save > 0) {
            List<CotaskingIntelligence> cotaskingIntelligences = new ArrayList<>();
            String regex = ",";
            String[] split = cotaskingDto.getIntelligenceIds().split(regex);
            for (String intelligenceId : split) {
                CotaskingIntelligence cotaskingIntelligence = new CotaskingIntelligence();
                cotaskingIntelligence.setCotaskingId(cotaskingId);
                cotaskingIntelligence.setIntelligenceId(Long.parseLong(intelligenceId));
                cotaskingIntelligence.setDeleted(0);
                cotaskingIntelligences.add(cotaskingIntelligence);
            }
            integer = cotaskingIntelligenceMapper.addBatchCotaskingIntelligence(cotaskingIntelligences);
        } else {
            return R.error("添加失败");
        }

        return integer > 0 ? R.success("添加成功") : R.error("添加失败");
    }
}
