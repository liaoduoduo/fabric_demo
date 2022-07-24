package com.ldy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldy.common.R;
import com.ldy.entity.Intelligence;
import com.ldy.entity.User;
import com.ldy.service.IntelligenceService;
import com.ldy.service.UserService;
import com.ldy.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractException;
import org.hyperledger.fabric.gateway.Network;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
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
    private UserService userService;

    @Autowired
    private IntelligenceService intelligenceService;

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
        //从数据库中查出来的user对象
        User one = userService.getOne(queryWrapper);

        //3、如果没有查询到则返回登录失败结果
        if (one == null) {
            return R.error("登录失败");
        }

        //4、密码比对，如果不一致则返回登录失败结果
        if (!one.getPassword().equals(password)) {
            return R.error("登录失败");
        }

        //5、查看状态，如果为已禁用状态，则返回员工已禁用结果
        if (one.getStatus() == 0) {
            return R.error("账号已禁用");
        }

        //6、登录成功，将id存入Session并返回登录成功结果
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
    public R<Page<UserVo>> page(int page, int pageSize, String name) {
        Page<UserVo> userPage = userService.pageQuery(page, pageSize, name);
        return R.success(userPage);
    }

    @ApiOperation("新增用户")
    @PostMapping
    public R<String> add(@RequestBody User user) throws ContractException, InterruptedException, TimeoutException {
        log.info("新增用户信息：{}", user);
        //设置统一初始密码,需要进行MD5加密处理(先不加密了)
        user.setPassword("123456");
        userService.save(user);
        log.info("刚刚插入的用户主键为：{}", user.getId());
        //存储到区块链中，调用链码：saveUser
        /*byte[] invokeResult = userService.saveByBlockChain(user);
        log.info("存储到区块的用户数据：{}",invokeResult);*/

        return R.success("新增用户成功！");
    }

    @PutMapping
    public R<String> update(@RequestBody User user) {
        //如果要停用某个用户
        if (user.getStatus() == 0) {
            //判断该用户有没有发布情报，如果有不允许停用，必须先停用并删除情报
            LambdaQueryWrapper<Intelligence> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Intelligence::getUserId, user.getId());
            int count = intelligenceService.count(queryWrapper);
            if (count > 0) {
                return R.error("该用户有正在发售的情报，不允许停用！");
            }
            //判断该用户有没有加入研判任务，如果有不允许停用，必须先退出
            //...
        }
        userService.updateById(user);
        //将修改后的用户存储到区块链中
/*        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId,user.getId());
        User one = userService.getOne(queryWrapper);
        one.setStatus(user.getStatus());
        userService.saveByBlockChain(one);*/
        return R.success("修改用户信息成功");
    }

    @GetMapping("/list")
    public R<List<UserVo>> list() {
        return R.success(userService.listUserVo());
    }

    @DeleteMapping
    public R<String> delete(Long id) {
        userService.removeById(id);
        return R.success("删除成功");
    }

    @GetMapping("/{id}")
    public R<User> queryUserById(@PathVariable Long id) {
        User user = userService.getById(id);
        return R.success(user);
    }


}
