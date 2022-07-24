package com.ldy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.common.BaseContext;
import com.ldy.common.R;
import com.ldy.dto.TokenDto;
import com.ldy.entity.Token;
import com.ldy.entity.User;
import com.ldy.mapper.TokenMapper;
import com.ldy.service.TokenService;
import com.ldy.service.UserService;
import com.ldy.vo.TokenVo;
import com.ldy.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ldy
 * @Date 2022/7/24 17:05
 * @ClassName TokenServiceImpl
 * @Description TODO
 * @Version v1.0
 */
@Slf4j
@Service
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token> implements TokenService {

    @Autowired
    private TokenMapper tokenMapper;

    @Autowired
    private UserService userService;

    @Override
    public Page<TokenVo> pageByName(int page, int pageSize, String name) {
        Page<TokenVo> tokenVoPage = tokenMapper.pageByName(new Page<>(page, pageSize), name);
        Long userId = BaseContext.getCurrentId();
        //如果不是管理员，那就只能看到自己的token账户信息
        if (userId != 1) {
            List<TokenVo> records = tokenVoPage.getRecords();
            for (TokenVo record : records) {
                Long tokenId = record.getId();
                LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(User::getTokenId, tokenId);
                User user = userService.getOne(queryWrapper);
                if (user.getId().equals(userId)) {
                    ArrayList<TokenVo> oneResult = new ArrayList<>();
                    oneResult.add(record);
                    tokenVoPage.setRecords(oneResult);
                }
            }
        }
        return tokenVoPage;
    }

    @Override
    public UserVo getUserByTokenId(Long id) {
        Token token = this.getById(id);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getTokenId, token.getId());
        User user = userService.getOne(queryWrapper);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }

    @Override
    public TokenVo getTokeVoById(Long id) {
        Token token = this.getById(id);

        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getTokenId, token.getId()));

        TokenVo tokenVo = new TokenVo();
        BeanUtils.copyProperties(token, tokenVo);
        tokenVo.setUserId(user.getId());
        return tokenVo;
    }


    @Override
    @Transactional
    public boolean saveByUser(TokenDto tokenDto) {
        User user = userService.getById(tokenDto.getUserId());
        if (user.getTokenId()!=null) {
            return false;
        }
        Token token = new Token();
        token.setStatus(tokenDto.getStatus());
        token.setCurrentToken(tokenDto.getCurrentToken());
        //设置默认密码
        token.setPassword("123456");
        this.save(token);

        User updateUser = new User();
        updateUser.setId(tokenDto.getUserId());
        updateUser.setTokenId(token.getId());
        userService.updateById(updateUser);
        return true;
    }

    @Override
    public void updateTokenStatus(List<Long> ids, int status) {
        List<Token> tokens = this.listByIds(ids);
        for (Token token : tokens) {
            token.setStatus(status);
        }
        this.updateBatchById(tokens);
    }
}


