package com.ldy.fabric;

import org.hyperledger.fabric.sdk.*;

import java.nio.file.Path;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

/**
 * @Author ldy
 * @Date 2022/6/25 0:10
 * @ClassName Chaincode
 * @Description Fabric2.2.0 链码的生命周期：打包、安装、审批、同意
 * @Version v1.0
 */
public interface Chaincode {

    /**
     * @description TODO
     * @date 2022/6/25 13:19
     * @param chaincodeLabel 链码名
     * @param chaincodeType 链码类型
     * @param chaincodeSourceLocation 链码文件位置
     * @param chaincodePath java链码默认为空
     * @param metadadataSource 索引文件位置
     * @return org.hyperledger.fabric.sdk.LifecycleChaincodePackage
     */
    LifecycleChaincodePackage createLifecycleChaincodePackage(
            String chaincodeLabel,
            TransactionRequest.Type chaincodeType,
            Path chaincodeSourceLocation,
            String chaincodePath,
            Path metadadataSource
    );


    /**
     * @description 安装链码
     * @date 2022/6/25 0:35
     * @param lifecycleChaincodePackage 打包后的链码
     * @return java.lang.String
     */
    String lifecycleInstallChaincode(
            LifecycleChaincodePackage lifecycleChaincodePackage
    );

    /**
     * @description 审批链码
     * @date 2022/6/25 13:00
     * @param sequence 序列号，初始值为1，后续累加即可
     * @param chaincodeName 链码名
     * @param chaincodeVersion 链码版本号
     * @param chaincodeEndorsementPolicy 链码背书策略，默认为组织或
     * @param chaincodeCollectionConfiguration 私有数据定义
     * @param initRequired 是否初始化，默认为false
     * @param ChaincodePackageID 待审批链码的ID
     * @return java.util.concurrent.CompletableFuture<org.hyperledger.fabric.sdk.BlockEvent.TransactionEvent>
     */
    CompletableFuture<BlockEvent.TransactionEvent> lifecycleApproveChaincodeDefinitionForMyOrg(
            long sequence,
            String chaincodeName,
            String chaincodeVersion,
            LifecycleChaincodeEndorsementPolicy chaincodeEndorsementPolicy,
            ChaincodeCollectionConfiguration chaincodeCollectionConfiguration,
            boolean initRequired,
            String ChaincodePackageID
    );

    /**
     * @description 向通道提交链码
     * @date 2022/6/25 13:03
     * @param definitionSequence 是否初始化，默认为false
     * @param chaincodeName 链码名
     * @param chaincodeVersion 版本号
     * @param chaincodeEndorsementPolicy 背书策略
     * @param chaincodeCollectionConfiguration 私有数据集合
     * @param initRequired 是否需要初始化，默认false
     * @param endorsingPeers 背书节点信息
     * @return java.util.concurrent.CompletableFuture<org.hyperledger.fabric.sdk.BlockEvent.TransactionEvent>
     */
    CompletableFuture<BlockEvent.TransactionEvent> commitChaincodeDefinitionRequest(
            long definitionSequence,
            String chaincodeName,
            String chaincodeVersion,
            LifecycleChaincodeEndorsementPolicy chaincodeEndorsementPolicy,
            ChaincodeCollectionConfiguration chaincodeCollectionConfiguration,
            boolean initRequired,
            Collection<Peer> endorsingPeers
    );

}
