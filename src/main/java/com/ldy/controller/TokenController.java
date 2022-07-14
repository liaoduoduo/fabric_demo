package com.ldy.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldy.common.R;
import com.ldy.dto.TokenDto;
import com.ldy.entity.Token;
import com.ldy.entity.User;
import com.ldy.service.TokenService;
import com.ldy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ldy
 * @Date 2022/7/14 11:12
 * @ClassName TokenController
 * @Description Token 控制器
 * @Version v1.0
 */
@RestController
@RequestMapping("/token")
@Slf4j
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @GetMapping("/page")
    public R<Page<TokenDto>> page(int page, int pageSize, String name) {
        Page<Token> tokenPage = new Page<>(page, pageSize);
        Page<TokenDto> tokenDtoPage = new Page<>();
        tokenService.page(tokenPage,null);
        BeanUtils.copyProperties(tokenPage, tokenDtoPage, "records");
        List<TokenDto> list = new ArrayList<>();
        List<Token> records = tokenPage.getRecords();
        log.info("token分页查询结果为：{}",records);
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
        return R.success(tokenDtoPage);
    }

    @GetMapping("/{id}")
    public R<Token> getTokenById(@PathVariable Long id) {
        return R.success(tokenService.getById(id));
    }

}
