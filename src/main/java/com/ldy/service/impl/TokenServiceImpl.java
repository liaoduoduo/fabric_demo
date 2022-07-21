package com.ldy.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.common.R;
import com.ldy.dto.TokenDto;
import com.ldy.entity.Token;
import com.ldy.entity.User;
import com.ldy.mapper.TokenMapper;
import com.ldy.service.TokenService;
import com.ldy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ldy
 * @Date 2022/7/14 11:12
 * @ClassName TokenServiceImpl
 * @Description TODO
 * @Version v1.0
 */
@Slf4j
@Service
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token> implements TokenService {


    @Autowired
    private UserService userService;

    @Override
    public Page<TokenDto> pageQuery(int page, int pageSize, String name) {
        Page<Token> tokenPage = new Page<>(page, pageSize);
        Page<TokenDto> tokenDtoPage = new Page<>();
        this.page(tokenPage);
        BeanUtils.copyProperties(tokenPage, tokenDtoPage, "records");
        List<TokenDto> list = new ArrayList<>();
        List<Token> records = tokenPage.getRecords();
        log.info("token分页查询结果为：{}", records);
        for (Token record : records) {
            TokenDto tokenDto = new TokenDto();
            BeanUtils.copyProperties(record, tokenDto);
            User user = userService.getById(record.getUserId());
            tokenDto.setName(user.getName());
            list.add(tokenDto);
        }
        if (name != null) {
            list.removeIf(tokenDto -> !tokenDto.getName().contains(name));
        }
        tokenDtoPage.setRecords(list);
        return tokenDtoPage;
    }

    @Override
    public R<String> deleteBatch(List<Long> ids) {
        List<Token> tokens = this.listByIds(ids);
        for (Token token : tokens) {
            if (token.getStatus() == 1) {
                return R.error("停用token才能删除！");
            }
        }
        this.deleteBatch(ids);
        return R.success("删除成功");
    }
}
