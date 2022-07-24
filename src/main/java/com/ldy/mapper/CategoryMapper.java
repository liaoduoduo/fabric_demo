package com.ldy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ldy.entity.IntelligenceCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author ldy
 * @Date 2022/7/4 16:05
 * @ClassName CategoryMapper
 * @Description TODO
 * @Version v1.0
 */
@Mapper
public interface CategoryMapper extends BaseMapper<IntelligenceCategory> {
}
