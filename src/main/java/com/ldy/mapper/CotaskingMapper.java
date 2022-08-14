package com.ldy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ldy.dto.CotaskingDto;
import com.ldy.entity.Cotasking;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sunqing
 * @since 2022-07-24
 */
@Mapper
public interface CotaskingMapper extends BaseMapper<Cotasking> {

    Boolean addCotakAndBindIntelligences(CotaskingDto cotaskingDto);
}
