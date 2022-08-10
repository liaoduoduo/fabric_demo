package com.ldy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ldy.common.R;
import com.ldy.dto.CotaskingIntelligenceDto;
import com.ldy.entity.CotaskingIntelligence;
import com.ldy.vo.CotaskIntelligenceVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sunqing
 * @since 2022-07-24
 */
public interface ICotaskingIntelligenceService extends IService<CotaskingIntelligence> {

    List<CotaskIntelligenceVo> getIntelligencesInCotask(Long id);

    R<String> addBatchCotaskingIntelligence(CotaskingIntelligenceDto cotaskingIntelligenceDto);
}
