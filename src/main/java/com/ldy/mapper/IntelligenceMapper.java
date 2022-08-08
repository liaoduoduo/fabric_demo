package com.ldy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldy.dto.IntelligencePageDto;
import com.ldy.entity.Intelligence;
import com.ldy.vo.IntelligenceVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author ldy
 * @Date 2022/7/1 17:19
 * @ClassName IntelligenceMapper
 * @Description 情报数据访问映射
 * @Version v1.0
 */
@Mapper
public interface IntelligenceMapper extends BaseMapper<Intelligence> {
    Page<IntelligenceVo> pageQuery(Page<IntelligenceVo> intelligenceVoPage, IntelligencePageDto intelligencePageDto);
}
