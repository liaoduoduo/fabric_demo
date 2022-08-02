package com.ldy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldy.entity.Token;
import com.ldy.vo.TokenVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author ldy
 * @Date 2022/7/24 17:04
 * @ClassName TokenMappper
 * @Description TODO
 * @Version v1.0
 */
@Mapper
public interface TokenMapper extends BaseMapper<Token> {
    Page<TokenVo> pageByName(Page<TokenVo> tokenVoPage, String name);
}
