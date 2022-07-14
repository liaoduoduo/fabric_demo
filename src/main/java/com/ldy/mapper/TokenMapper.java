package com.ldy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ldy.entity.Token;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author ldy
 * @Date 2022/7/14 11:10
 * @ClassName TokenMapper
 * @Description TODO
 * @Version v1.0
 */
@Mapper
public interface TokenMapper extends BaseMapper<Token> {
}
