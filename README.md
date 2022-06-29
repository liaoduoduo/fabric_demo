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

1.  搭建并启动test-network区块链网络，创建引用通道 ./network.sh createChannel
2.  安装好测试链码 ./network.sh deployCC -ccn basic -ccp ../asset-transfer-basic/chaincode-go -ccl go
3.  将第一步生成的证书和私钥路径替换到 resources/crypto-config
4.  搭建好mysql数据库
5.  修改application.yml配置文件
6.  启动项目

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request

