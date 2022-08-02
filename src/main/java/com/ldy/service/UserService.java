package com.ldy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldy.entity.User;
import com.ldy.vo.TokenVo;
import com.ldy.vo.UserVo;

import java.util.List;

/**
 * @Author ldy
 * @Date 2022/6/25 15:28
 * @ClassName UserService
 * @Description 用户业务层
 * @Version v1.0
 */
public interface UserService extends IService<User> {
    /**
     * @description 新增用户数据，保存在区块链中
     * @date 2022/6/25 16:31
     * @param user 用户实体类
     * @return byte[]
     */
    byte[] saveByBlockChain(User user);

    Page<UserVo> pageQuery(int page, int pageSize, String name);

    List<UserVo> listUserVo();
}
