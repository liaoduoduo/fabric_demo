package com.ldy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldy.entity.TokenLog;

/**
 * @Author ldy
 * @Description TODO
 * @Version v1.0
 * @Date 2022/8/3 15:24
 */
public interface TokenLogService extends IService<TokenLog> {
    Page<TokenLog> getTokenLog(int page, int pageSize, Long id);
}
