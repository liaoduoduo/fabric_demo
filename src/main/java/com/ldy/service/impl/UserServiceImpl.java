package com.ldy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.mapper.UserMapper;
import com.ldy.entity.User;
import com.ldy.service.UserService;
import com.ldy.vo.UserVo;
import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractException;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.sdk.Peer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/**
 * @Author ldy
 * @Date 2022/6/25 15:29
 * @ClassName UserServiceImpl
 * @Description 用户业务实现类
 * @Version v1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

/*    @Resource
    private Contract contract;

    @Resource
    private Network network;*/

    /**
     * @param user 用户实体类
     * @return byte[]
     * @description 新增用户数据，保存在区块链中
     * @date 2022/6/25 16:31
     */
    @Override
    public byte[] saveByBlockChain(User user) {
        byte[] invokeResult = null;
/*        try {
            invokeResult = contract.createTransaction("saveUser")
                    .setEndorsingPeers(network.getChannel().getPeers(EnumSet.of(Peer.PeerRole.ENDORSING_PEER)))
                    //链码参数列表
                    .submit(String.valueOf(user.getId()),
                            user.getUsername(),
                            user.getName(),
                            user.getPassword(),
                            user.getPhone(),
                            user.getSex(),
                            user.getIdNumber(),
                            String.valueOf(user.getStatus()));
        } catch (ContractException | InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }*/
        return invokeResult;
    }

    @Override
    public Page<UserVo> pageQuery(int page, int pageSize, String name) {
        Page<User> userPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, User::getName, name)
                .orderByDesc(User::getUpdateTime);
        this.page(userPage, queryWrapper);
        Page<UserVo> userVoPage = new Page<>();
        BeanUtils.copyProperties(userPage, userVoPage, "records");
        List<User> records = userPage.getRecords();
        List<UserVo> userVos = records.stream().map(item -> {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(item, userVo);
            return userVo;
        }).collect(Collectors.toList());
        userVoPage.setRecords(userVos);
        return userVoPage;
    }

    @Override
    public List<UserVo> listUserVo() {
        List<User> list = this.list();
        List<UserVo> userVos = new ArrayList<>();
        for (User user : list) {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user, userVo);
            userVos.add(userVo);
        }
        return userVos;
    }
}
