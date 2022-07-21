package com.ldy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractException;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.sdk.Peer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.EnumSet;
import java.util.concurrent.TimeoutException;

/**
 * @Author ldy
 * @Date 2022/6/24 15:30
 * @ClassName FirstController
 * @Description 控制类
 * @Version v1.0
 */


@Api(tags = "测试链码操作")
@Slf4j
//@RestController
public class FirstController {

    //@Resource
    private Contract contract;

    //@Resource
    private Network network;


    @ApiOperation("根据id获取用户实体")
    @GetMapping("/getUser")
    public String getUser(String userId) throws ContractException {
        byte[] queryAResultBefore = contract.evaluateTransaction("getUser",userId);
        return new String(queryAResultBefore, StandardCharsets.UTF_8);
    }

    @ApiOperation("添加用户")
    @GetMapping("/addUser")
    public String addUser(String userId, String userName, String money) throws ContractException, InterruptedException, TimeoutException {
        byte[] invokeResult = contract.createTransaction("addUser")
                .setEndorsingPeers(network.getChannel().getPeers(EnumSet.of(Peer.PeerRole.ENDORSING_PEER)))
                .submit(userId, userName, money);
        String txId = new String(invokeResult, StandardCharsets.UTF_8);
        return txId;
    }

    @ApiOperation("查询所有用户")
    @GetMapping("/queryAll")
    public String queryAll() throws ContractException {
        byte[] queryAResultBefore = contract.evaluateTransaction("queryAll");
        return new String(queryAResultBefore, StandardCharsets.UTF_8);
    }

    @ApiOperation("获取某一id用户的历史操作")
    @GetMapping("/getHistory")
    public String getHistory(String userId) throws ContractException {
        byte[] queryAResultBefore = contract.evaluateTransaction("getHistory", userId);
        return new String(queryAResultBefore, StandardCharsets.UTF_8);
    }

    @ApiOperation("根据id获取资产")
    @GetMapping("/readAsset")
    public String readAsset(String userId) throws ContractException {
        byte[] queryAResultBefore = contract.evaluateTransaction("readAsset", userId);
        log.info(new String(queryAResultBefore, StandardCharsets.UTF_8));
        return new String(queryAResultBefore, StandardCharsets.UTF_8);
    }

    @ApiOperation("获取所有资产")
    @GetMapping("/getAllAssets")
    public String getAllAssets() throws ContractException {
        byte[] queryAResultBefore = contract.evaluateTransaction("getAllAssets");
        log.info(new String(queryAResultBefore, StandardCharsets.UTF_8));
        return new String(queryAResultBefore, StandardCharsets.UTF_8);
    }

    @ApiOperation("初始化账本")
    @GetMapping("/initLedger")
    public String initLedger() throws ContractException, InterruptedException, TimeoutException {
        byte[] invokeResult = contract.createTransaction("initLedger")
                .setEndorsingPeers(network.getChannel().getPeers(EnumSet.of(Peer.PeerRole.ENDORSING_PEER)))
                .submit();
        log.info(new String(invokeResult, StandardCharsets.UTF_8));
        return new String(invokeResult, StandardCharsets.UTF_8);
    }


}
