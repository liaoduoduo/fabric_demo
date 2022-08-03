package com.ldy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldy.common.R;
import com.ldy.dto.TokenDto;
import com.ldy.entity.Token;
import com.ldy.entity.User;
import com.ldy.vo.TokenVo;
import com.ldy.vo.UserVo;

import java.util.List;

/**
 * @Author ldy
 * @Date 2022/7/24 17:05
 * @ClassName TokenService
 * @Description TODO
 * @Version v1.0
 */
public interface TokenService extends IService<Token> {
    Page<TokenVo> pageByName(int page, int pageSize, String name);

    UserVo getUserByTokenId(Long id);

    TokenVo getTokeVoById(Long id);

    boolean saveByUser(TokenDto tokenDto);

    void updateTokenStatus(List<Long> ids, int status);

    void updateWithLog(Token token);
}
