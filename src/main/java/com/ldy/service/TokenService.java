package com.ldy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldy.common.R;
import com.ldy.dto.TokenDto;
import com.ldy.entity.Token;

import java.util.List;

/**
 * @Author ldy
 * @Date 2022/7/14 11:11
 * @ClassName TokenService
 * @Description TODO
 * @Version v1.0
 */
public interface TokenService extends IService<Token> {
    R<String> deleteBatch(List<Long> ids);

    Page<TokenDto> pageQuery(int page, int pageSize, String name);
}
