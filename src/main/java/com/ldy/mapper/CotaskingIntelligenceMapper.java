package com.ldy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ldy.entity.CotaskingIntelligence;
import com.ldy.vo.CotaskIntelligenceVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sunqing
 * @since 2022-07-24
 */
@Mapper
public interface CotaskingIntelligenceMapper extends BaseMapper<CotaskingIntelligence> {
    List<CotaskIntelligenceVo> getIntelligencesInCotask(Long id);

    Integer addBatchCotaskingIntelligence(List<CotaskingIntelligence> list);
}
