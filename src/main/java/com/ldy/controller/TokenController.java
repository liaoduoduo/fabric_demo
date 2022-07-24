package com.ldy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldy.common.BaseContext;
import com.ldy.common.R;
import com.ldy.dto.TokenDto;
import com.ldy.entity.Token;
import com.ldy.entity.User;
import com.ldy.service.TokenService;
import com.ldy.service.UserService;
import com.ldy.vo.TokenVo;
import com.ldy.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @GetMapping("/page")
    public R<Page<TokenVo>> page(int page, int pageSize, String name) {
        return R.success(tokenService.pageByName(page, pageSize, name));
    }

    @GetMapping("/{id}")
    public R<TokenVo> getTokenById(@PathVariable Long id) {
        return R.success(tokenService.getTokeVoById(id));
    }

    //如果参数时放在请求体中，application/json传入后台的话，那么后台要用@RequestBody才能接收到
    @PostMapping
    public R<String> save(@RequestBody TokenDto tokenDto) {
        boolean result = tokenService.saveByUser(tokenDto);
        if (result) {
            return R.success("添加token账户成功");
        }
        return R.error("该用户已拥有token账户！");
    }

    @PutMapping
    public R<String> update(@RequestBody Token token) {
        tokenService.updateById(token);
        return R.success("修改token账户成功");
    }


    @PostMapping("/status/{status}")
    public R<String> updateTokenStatus(@RequestParam List<Long> ids, @PathVariable int status) {
        tokenService.updateTokenStatus(ids, status);
        return R.success("修改token状态成功！");
    }

}
