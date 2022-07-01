package com.ldy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldy.common.BaseContext;
import com.ldy.common.R;
import com.ldy.pojo.User;
import com.ldy.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractException;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.sdk.Peer;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.EnumSet;
import java.util.concurrent.TimeoutException;

/**
 * @Author ldy
 * @Date 2022/6/25 15:25
 * @ClassName UserController
 * @Description 用户控制层
 * @Version v1.0
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "用户控制器")
public class UserController {

    @Resource
    private Contract contract;

    @Resource
    private Network network;

    @Resource
    private UserService userService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public R<User> login(HttpServletRequest request, @RequestBody User user) {
        //1、将页面提交的密码password进行md5加密处理
        String password = user.getPassword();

        //password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2、根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //eq(列名, 值)
        //可以通过Lambda获取数据库列名
        queryWrapper.eq(User::getUsername, user.getUsername());
        //从数据库中查出来的emp对象
        User one = userService.getOne(queryWrapper);

        //3、如果没有查询到则返回登录失败结果
        if (one == null) {
            return R.error("登录失败");
        }

        //4、密码比对，如果不一致则返回登录失败结果
        if (!one.getPassword().equals(password)) {
            return R.error("登录失败");
        }

        //5、查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if (one.getStatus() == 0) {
            return R.error("账号已禁用");
        }

        //6、登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("user", one.getId());
        return R.success(one);
    }

    @ApiOperation("用户登出")
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return R.success("注销成功");
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public R<Page<User>> page(int page, int pageSize, String name) {
        Page<User> userPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, User::getName, name)
                .orderByDesc(User::getUpdateTime);
        userService.page(userPage, queryWrapper);
        return R.success(userPage);
    }

    @ApiOperation("新增用户")
    @PostMapping
    public R<String> add(@RequestBody User user) throws ContractException, InterruptedException, TimeoutException {
        log.info("新增用户信息：{}", user);
        //设置统一初始密码,需要进行MD5加密处理
        user.setPassword("123456");
        userService.save(user);

/*        //存储到区块链中，调用链码：saveUser
        byte[] invokeResult = userService.saveByBlockChain(user);
        log.info("存储到区块的用户数据：{}",invokeResult);*/

        return R.success("新增用户成功！");
    }

    @PutMapping
    public R<String> update(@RequestBody User user) {
        userService.updateById(user);
        //将修改后的用户存储到区块链中
/*        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId,user.getId());
        User one = userService.getOne(queryWrapper);
        one.setStatus(user.getStatus());
        userService.saveByBlockChain(one);*/
        return R.success("修改用户信息成功");
    }


}
