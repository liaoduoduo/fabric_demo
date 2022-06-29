package com.ldy.fabric;

import org.hyperledger.fabric.sdk.*;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

/**
 * @Author ldy
 * @Date 2022/6/25 13:05
 * @ClassName ChaincodeImpl
 * @Description 链码生命周期实现类
 * @Version v1.0
 */
public class ChaincodeImpl implements Chaincode {

    Logger logger = Logger.getLogger(ChaincodeImpl.class.getName());
    private HFClient hfClient;
    private Channel channel;

    public ChaincodeImpl(HFClient hfClient, Channel channel) {
        this.hfClient = hfClient;
        this.channel = channel;
    }

    /**
     * @param chaincodeLabel          链码名
     * @param chaincodeType           链码类型
     * @param chaincodeSourceLocation 链码文件位置
     * @param chaincodePath           java链码默认为空
     * @param metadadataSource        索引文件位置
     * @return org.hyperledger.fabric.sdk.LifecycleChaincodePackage
     * @description TODO
     * @date 2022/6/25 13:19
     */
    @Override
    public LifecycleChaincodePackage createLifecycleChaincodePackage(String chaincodeLabel, TransactionRequest.Type chaincodeType, Path chaincodeSourceLocation, String chaincodePath, Path metadadataSource) {
        LifecycleChaincodePackage lifecycleChaincodePackage = null;
        try {
            lifecycleChaincodePackage = LifecycleChaincodePackage.fromSource(chaincodeLabel, chaincodeSourceLocation, chaincodeType, chaincodePath, metadadataSource);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
        return lifecycleChaincodePackage;
    }

    /**
     * @param lifecycleChaincodePackage 打包后的链码
     * @return java.lang.String
     * @description 安装链码
     * @date 2022/6/25 0:35
     */
    @Override
    public String lifecycleInstallChaincode(LifecycleChaincodePackage lifecycleChaincodePackage) {
        LifecycleInstallChaincodeRequest lifecycleInstallChaincodeRequest = hfClient.newLifecycleInstallChaincodeRequest();
        String packageID = null;
        try {
            lifecycleInstallChaincodeRequest.setLifecycleChaincodePackage(lifecycleChaincodePackage);
            lifecycleInstallChaincodeRequest.setProposalWaitTime(360000);
            Collection<LifecycleInstallChaincodeProposalResponse> responses
                    = hfClient.sendLifecycleInstallChaincodeRequest(lifecycleInstallChaincodeRequest, channel.getPeers());
            Collection<ProposalResponse> successful = new LinkedList<>();
            Collection<ProposalResponse> failed = new LinkedList<>();
            for (LifecycleInstallChaincodeProposalResponse response : responses) {
                if (response.getStatus() == ProposalResponse.Status.SUCCESS) {
                    successful.add(response);
                    if (packageID == null) {
                        packageID = response.getPackageId();
                    } else {
                        logger.info("Miss match on what the peers returned back as the packageID");
                    }
                } else {
                    failed.add(response);
                }
            }

            if (failed.size() > 0) {
                ProposalResponse first = failed.iterator().next();
                logger.info("Not enough endorsers for install :" + successful.size() + ".  " + first.getMessage());
            }
        } catch (InvalidArgumentException | ProposalException e) {
            e.printStackTrace();
        }
        return packageID;
    }

    /**
     * @param sequence                         序列号，初始值为1，后续累加即可
     * @param chaincodeName                    链码名
     * @param chaincodeVersion                 链码版本号
     * @param chaincodeEndorsementPolicy       链码背书策略，默认为组织或
     * @param chaincodeCollectionConfiguration 私有数据定义
     * @param initRequired                     是否初始化，默认为false
     * @param chaincodePackageID               待审批链码的ID
     * @return java.util.concurrent.CompletableFuture<org.hyperledger.fabric.sdk.BlockEvent.TransactionEvent>
     * @description 审批链码
     * @date 2022/6/25 13:00
     */
    @Override
    public CompletableFuture<BlockEvent.TransactionEvent> lifecycleApproveChaincodeDefinitionForMyOrg(long sequence, String chaincodeName, String chaincodeVersion, LifecycleChaincodeEndorsementPolicy chaincodeEndorsementPolicy, ChaincodeCollectionConfiguration chaincodeCollectionConfiguration, boolean initRequired, String chaincodePackageID) {
        CompletableFuture<BlockEvent.TransactionEvent> completableFuture = null;

        LifecycleApproveChaincodeDefinitionForMyOrgRequest lifecycleApproveChaincodeDefinitionForMyOrgRequest =
                hfClient.newLifecycleApproveChaincodeDefinitionForMyOrgRequest();
        try {
            lifecycleApproveChaincodeDefinitionForMyOrgRequest.setSequence(sequence);
            lifecycleApproveChaincodeDefinitionForMyOrgRequest.setChaincodeName(chaincodeName);
            lifecycleApproveChaincodeDefinitionForMyOrgRequest.setChaincodeVersion(chaincodeVersion);
            lifecycleApproveChaincodeDefinitionForMyOrgRequest.setInitRequired(initRequired);

            if (null != chaincodeCollectionConfiguration) {
                lifecycleApproveChaincodeDefinitionForMyOrgRequest.setChaincodeCollectionConfiguration(chaincodeCollectionConfiguration);
            }
            if (null != chaincodeEndorsementPolicy) {
                lifecycleApproveChaincodeDefinitionForMyOrgRequest.setChaincodeEndorsementPolicy(chaincodeEndorsementPolicy);
            }

            lifecycleApproveChaincodeDefinitionForMyOrgRequest.setPackageId(chaincodePackageID);

            Collection<LifecycleApproveChaincodeDefinitionForMyOrgProposalResponse> responses
                    = channel.sendLifecycleApproveChaincodeDefinitionForMyOrgProposal(lifecycleApproveChaincodeDefinitionForMyOrgRequest, channel.getPeers());

            for (LifecycleApproveChaincodeDefinitionForMyOrgProposalResponse response : responses) {
                final Peer peer = response.getPeer();
                ChaincodeResponse.Status status = response.getStatus();
                boolean verified = response.isVerified();
                boolean invalid = response.isInvalid();
                logger.info("审批链码:" + peer.toString() + "-----" + status.toString());
            }
            completableFuture = channel.sendTransaction(responses);
        } catch (InvalidArgumentException | ProposalException e) {
            e.printStackTrace();
        }
        return completableFuture;
    }

    /**
     * @param definitionSequence               是否初始化，默认为false
     * @param chaincodeName                    链码名
     * @param chaincodeVersion                 版本号
     * @param chaincodeEndorsementPolicy       背书策略
     * @param chaincodeCollectionConfiguration 私有数据集合
     * @param initRequired                     是否需要初始化，默认false
     * @param endorsingPeers                   背书节点信息
     * @return java.util.concurrent.CompletableFuture<org.hyperledger.fabric.sdk.BlockEvent.TransactionEvent>
     * @description 向通道提交链码
     * @date 2022/6/25 13:03
     */
    @Override
    public CompletableFuture<BlockEvent.TransactionEvent> commitChaincodeDefinitionRequest(long definitionSequence, String chaincodeName, String chaincodeVersion, LifecycleChaincodeEndorsementPolicy chaincodeEndorsementPolicy, ChaincodeCollectionConfiguration chaincodeCollectionConfiguration, boolean initRequired, Collection<Peer> endorsingPeers) {
        CompletableFuture<BlockEvent.TransactionEvent> transactionEventCompletableFuture = null;
        LifecycleCommitChaincodeDefinitionRequest lifecycleCommitChaincodeDefinitionRequest
                = hfClient.newLifecycleCommitChaincodeDefinitionRequest();

        try {
            lifecycleCommitChaincodeDefinitionRequest.setSequence(definitionSequence);
            lifecycleCommitChaincodeDefinitionRequest.setChaincodeName(chaincodeName);
            lifecycleCommitChaincodeDefinitionRequest.setChaincodeVersion(chaincodeVersion);
            if (null != chaincodeEndorsementPolicy) {
                lifecycleCommitChaincodeDefinitionRequest.setChaincodeEndorsementPolicy(chaincodeEndorsementPolicy);
            }
            if (null != chaincodeCollectionConfiguration) {
                lifecycleCommitChaincodeDefinitionRequest.setChaincodeCollectionConfiguration(chaincodeCollectionConfiguration);
            }
            lifecycleCommitChaincodeDefinitionRequest.setInitRequired(initRequired);

            Collection<LifecycleCommitChaincodeDefinitionProposalResponse> responses =
                    channel.sendLifecycleCommitChaincodeDefinitionProposal(lifecycleCommitChaincodeDefinitionRequest, endorsingPeers);

            for (LifecycleCommitChaincodeDefinitionProposalResponse resp : responses) {

                final Peer peer = resp.getPeer();
                ChaincodeResponse.Status status = resp.getStatus();
                boolean verified = resp.isVerified();
                logger.info("提交链码:" + peer.toString() + "-----" + status.toString());

            }
            transactionEventCompletableFuture = channel.sendTransaction(responses);

        } catch (InvalidArgumentException | ProposalException e) {
            e.printStackTrace();
        }
        return transactionEventCompletableFuture;
    }

}

