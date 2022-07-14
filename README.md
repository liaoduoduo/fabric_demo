# fabric_demo

#### 介绍

#### 软件架构
项目属于前后端分离，前端使用H5页面以及vue框架构建页面，使用springboot搭建
数据层使用的MyBatis与数据库交互，同时将数据存储到fabric中。

系统后台主要实现功能：
用户管理
情报分类管理
情报管理
token管理
...

#### 安装教程

1. 虚拟机搭建并启动test-network区块链网络，进入fabric目录/scripts/fabric-samples/test-network
2. 创建通道 ./network.sh createChannel
3. 安装好测试链码 ./network.sh deployCC -ccn basic -ccp ../asset-transfer-basic/chaincode-go -ccl go
4. 将第一步生成的证书和私钥文件夹 ./organizations/ordererOrganizations 和./organizations/peerOrganizations  拷贝到本项目resources/crypto-config/目录下
5. 搭建好mysql数据库
6. 修改application.yml配置文件中mysql数据库的配置
7. 修改connection.json,将节点url的ip地址修改为自己虚拟机地址
8. 启动项目



