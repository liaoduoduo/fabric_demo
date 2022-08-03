package com.ldy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.entity.TokenLog;
import com.ldy.mapper.TokenLogMapper;
import com.ldy.service.TokenLogService;
import org.springframework.stereotype.Service;

/**
 * @Author ldy
 * @Description TODO
 * @Version v1.0
 * @Date 2022/8/3 15:24
 */
@Service
public class TokenLogServiceImpl extends ServiceImpl<TokenLogMapper, TokenLog> implements TokenLogService {
    @Override
    public Page<TokenLog> getTokenLog(int page, int pageSize, Long id) {
        Page<TokenLog> tokenLogPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<TokenLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TokenLog::getTokenId, id).orderByDesc(TokenLog::getCreateTime);
        this.page(tokenLogPage, queryWrapper);
        return tokenLogPage;
    }
}
