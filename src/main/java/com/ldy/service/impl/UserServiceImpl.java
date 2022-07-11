package com.ldy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.mapper.UserMapper;
import com.ldy.entity.User;
import com.ldy.service.UserService;
import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractException;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.sdk.Peer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.EnumSet;
import java.util.concurrent.TimeoutException;

/**
 * @Author ldy
 * @Date 2022/6/25 15:29
 * @ClassName UserServiceImpl
 * @Description 用户业务实现类
 * @Version v1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private Contract contract;

    @Resource
    private Network network;

    /**
     * @param user 用户实体类
     * @return byte[]
     * @description 新增用户数据，保存在区块链中
     * @date 2022/6/25 16:31
     */
    @Override
    public byte[] saveByBlockChain(User user) {
        byte[] invokeResult = null;
        try {
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
        }
        return invokeResult;
    }
}
