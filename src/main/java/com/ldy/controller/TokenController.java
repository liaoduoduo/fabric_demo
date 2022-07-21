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
import org.springframework.web.bind.annotation.*;

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

        Page<TokenDto> tokenDtoPage = tokenService.pageQuery(page, pageSize, name);

        return R.success(tokenDtoPage);
    }

    @GetMapping("/{id}")
    public R<Token> getTokenById(@PathVariable Long id) {
        return R.success(tokenService.getById(id));
    }

    //如果参数时放在请求体中，application/json传入后台的话，那么后台要用@RequestBody才能接收到
    @PostMapping
    public R<String> save(@RequestBody Token token) {
        tokenService.save(token);
        return R.success("添加成功");
    }

    @PutMapping
    public R<String> update(@RequestBody Token token) {
        tokenService.updateById(token);
        return R.success("修改成功");
    }

    @PostMapping("/status/{status}")
    public R<String> status(@RequestParam List<Long> ids, @PathVariable int status) {
        List<Token> tokens = tokenService.listByIds(ids);
        for (Token token : tokens) {
            token.setStatus(status);
        }
        tokenService.updateBatchById(tokens);
        return R.success("修改状态成功");
    }

    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids) {
         return tokenService.deleteBatch(ids);
    }

}
