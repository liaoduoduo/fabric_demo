package com.ldy.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldy.common.R;
import com.ldy.entity.User;
import com.ldy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private UserService userService;

    @GetMapping("/page")
    public R<Page<User>> page(int page, int pageSize, String name) {

        Page<User> userPage = userService.pageQuery(page, pageSize, name);

        return R.success(userPage);
    }

    @GetMapping("/{id}")
    public R<User> getTokenById(@PathVariable Long id) {
        return R.success(userService.getById(id));
    }

    //如果参数时放在请求体中，application/json传入后台的话，那么后台要用@RequestBody才能接收到
    @PostMapping
    public R<String> save(@RequestBody User user) {
        userService.updateById(user);
        return R.success("添加成功");
    }

    @PutMapping
    public R<String> update(@RequestBody User user) {
        userService.updateById(user);
        return R.success("修改成功");
    }

}
