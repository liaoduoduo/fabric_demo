package com.ldy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.entity.Token;
import com.ldy.mapper.TokenMapper;
import com.ldy.service.TokenService;
import org.springframework.stereotype.Service;

/**
 * @Author ldy
 * @Date 2022/7/14 11:12
 * @ClassName TokenServiceImpl
 * @Description TODO
 * @Version v1.0
 */
@Service
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token> implements TokenService {
}
