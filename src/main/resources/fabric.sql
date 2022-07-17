-- MySQL dump 10.13  Distrib 5.7.19, for Win64 (x86_64)
--
-- Host: localhost    Database: fabric
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '分类名称',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '顺序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_category_name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='菜品及套餐分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1543871717372342274,'毒品',1,'2022-07-04 16:17:56','2022-07-04 16:17:56',1,1),(1545406145148084225,'政治',2,'2022-07-08 21:55:12','2022-07-08 21:55:12',1,1),(1545671867707187202,'军事',3,'2022-07-09 15:31:05','2022-07-09 15:31:05',1,1),(1545671909037858817,'外交',4,'2022-07-09 15:31:15','2022-07-09 15:31:15',1,1);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `intelligence`
--

DROP TABLE IF EXISTS `intelligence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `intelligence` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '情报名',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `category_id` bigint(20) NOT NULL COMMENT '情报分类id',
  `file_hash` varchar(200) NOT NULL COMMENT '情报hash',
  `price` int(10) NOT NULL COMMENT '情报价格',
  `image` varchar(200) NOT NULL COMMENT 'imgurl',
  `description` varchar(400) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '描述信息',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '0 停售 1 起售',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `intelligence`
--

LOCK TABLES `intelligence` WRITE;
/*!40000 ALTER TABLE `intelligence` DISABLE KEYS */;
INSERT INTO `intelligence` VALUES (1543877927232356353,'全国公安禁毒部门毒品分析技能大比武',1,1543871717372342274,'QmS4ustL54uo8FzR9455qaxZwuMiUhyvMcX9Ba8nUH4uVv',250,'3754351a-7a5e-4711-9ddd-bf55ebf1c715.png','全国公安禁毒部门毒品分析技能大比武决赛成功举办',1,'2022-07-04 16:42:36','2022-07-14 11:23:02',1,1),(1545407078284255233,'安倍晋三遭枪击去世',1,1545406145148084225,'QmWn3UyLomQi1bswpXkiZeAmzjB8Er2irfVUSeohoGLSSw ',250,'1339125b-6da4-4542-9300-18c5abecbc9c.png','日本前首相安倍晋三在奈良市进行演讲时遭枪击，因伤重不治身亡，终年67岁。',1,'2022-07-08 21:58:54','2022-07-14 11:22:57',1,1);
/*!40000 ALTER TABLE `intelligence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `amount` int(10) NOT NULL COMMENT '账户余额',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '0 停售 1 起售',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (124123,1,100,1,'2022-07-12 16:50:13','2022-07-12 16:50:13',1,1);
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `username` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '密码',
  `phone` varchar(11) COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `sex` varchar(2) COLLATE utf8_bin NOT NULL COMMENT '性别',
  `id_number` varchar(18) COLLATE utf8_bin NOT NULL COMMENT '身份证号',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态 0:禁用，1:正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='员工信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'管理员','admin','123456','13812312312','1','110101199001010047',1,'2021-05-06 17:20:07','2021-05-10 02:24:09',1,1),(1530117579706761217,'廖多越','ldy','123456','15216195884','1','360700199900000000',1,'2022-05-27 17:23:54','2022-05-27 17:23:54',1,1),(1531192761317855233,'安德拉斯','qwe','123456','17273646279','0','327828188839237722',1,'2022-05-30 16:36:17','2022-05-31 18:24:06',1,1),(1534543388534116354,'阿斯顿','qweqw','123456','13533443423','1','356367776762762',1,'2022-06-08 22:30:29','2022-06-08 22:30:29',1,1),(1540610747350966273,'asda','ldy2','123456','15216195883','1','372163199805050075',1,'2022-06-25 16:20:00','2022-06-29 10:24:07',1530117579706761217,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_intelligence`
--

DROP TABLE IF EXISTS `user_intelligence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_intelligence` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `intelligence_id` bigint(20) NOT NULL COMMENT '情报id',
  `file_hash` varchar(200) NOT NULL COMMENT '情报hash',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_intelligence`
--

LOCK TABLES `user_intelligence` WRITE;
/*!40000 ALTER TABLE `user_intelligence` DISABLE KEYS */;
INSERT INTO `user_intelligence` VALUES (1546778945794146305,1530117579706761217,1545407078284255233,'QmWn3UyLomQi1bswpXkiZeAmzjB8Er2irfVUSeohoGLSSw ','2022-07-12 16:50:13','2022-07-12 16:50:13',1530117579706761217,1530117579706761217);
/*!40000 ALTER TABLE `user_intelligence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'fabric'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-17 13:01:19
