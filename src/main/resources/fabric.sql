/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.7.19-log : Database - fabric
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fabric` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `fabric`;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

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

/*Data for the table `category` */

insert  into `category`(`id`,`name`,`sort`,`create_time`,`update_time`,`create_user`,`update_user`) values 
(1543871717372342274,'毒品',1,'2022-07-04 16:17:56','2022-07-04 16:17:56',1,1),
(1545406145148084225,'政治',2,'2022-07-08 21:55:12','2022-07-08 21:55:12',1,1),
(1545671867707187202,'军事',3,'2022-07-09 15:31:05','2022-07-09 15:31:05',1,1),
(1545671909037858817,'外交',4,'2022-07-09 15:31:15','2022-07-09 15:31:15',1,1);

/*Table structure for table `intelligence` */

DROP TABLE IF EXISTS `intelligence`;

CREATE TABLE `intelligence` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '情报名',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `category_id` bigint(20) NOT NULL COMMENT '情报分类id',
  `file_hash` varchar(200) NOT NULL COMMENT '情报hash',
  `price` decimal(10,2) NOT NULL COMMENT '情报价格',
  `image` varchar(200) NOT NULL COMMENT 'imgurl',
  `description` varchar(400) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '描述信息',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '0 停售 1 起售',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `intelligence` */

insert  into `intelligence`(`id`,`name`,`user_id`,`category_id`,`file_hash`,`price`,`image`,`description`,`status`,`create_time`,`update_time`,`create_user`,`update_user`) values 
(1543877927232356353,'全国公安禁毒部门毒品分析技能大比武',1,1543871717372342274,'QmS4ustL54uo8FzR9455qaxZwuMiUhyvMcX9Ba8nUH4uVv',250.00,'3754351a-7a5e-4711-9ddd-bf55ebf1c715.png','全国公安禁毒部门毒品分析技能大比武决赛成功举办',1,'2022-07-04 16:42:36','2022-07-14 11:23:02',1,1),
(1545407078284255233,'安倍晋三遭枪击去世',1,1545406145148084225,'QmWn3UyLomQi1bswpXkiZeAmzjB8Er2irfVUSeohoGLSSw ',250.00,'1339125b-6da4-4542-9300-18c5abecbc9c.png','日本前首相安倍晋三在奈良市进行演讲时遭枪击，因伤重不治身亡，终年67岁。',1,'2022-07-08 21:58:54','2022-07-14 11:22:57',1,1);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `username` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '密码',
  `phone` varchar(11) COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `sex` varchar(2) COLLATE utf8_bin NOT NULL COMMENT '性别',
  `id_number` varchar(18) COLLATE utf8_bin NOT NULL COMMENT '身份证号',
  `token` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT 'token余额',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态 0:禁用，1:正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='员工信息';

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`username`,`password`,`phone`,`sex`,`id_number`,`token`,`status`,`create_time`,`update_time`,`create_user`,`update_user`) values 
(1,'管理员','admin','123456','13812312312','1','110101199001010047',1000.00,1,'2021-05-06 17:20:07','2022-07-22 10:36:03',1,1),
(1530117579706761217,'廖多越','ldy','123456','15216195884','1','360700199900000000',200.72,1,'2022-05-27 17:23:54','2022-05-27 17:23:54',1,1),
(1531192761317855233,'安德拉斯','qwe','123456','17273646279','0','327828188839237722',999.00,1,'2022-05-30 16:36:17','2022-05-31 18:24:06',1,1),
(1549934892404502529,'小明','xiaoming','123456','15566778899','1','387979199009078765',748.00,1,'2022-07-21 09:50:49','2022-07-22 17:54:53',1,1549934892404502529);

/*Table structure for table `user_intelligence` */

DROP TABLE IF EXISTS `user_intelligence`;

CREATE TABLE `user_intelligence` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `from_user_id` bigint(20) NOT NULL COMMENT '发布者id',
  `to_user_id` bigint(20) DEFAULT NULL COMMENT '购买者id',
  `intelligence_id` bigint(20) NOT NULL COMMENT '情报id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `user_intelligence` */

insert  into `user_intelligence`(`id`,`from_user_id`,`to_user_id`,`intelligence_id`,`create_time`,`update_time`,`create_user`,`update_user`) values 
(1231,1,NULL,1545407078284255233,'2022-07-22 16:16:30','2022-07-22 16:16:33',1,1),
(1550419096411914242,1,1549934892404502529,1543877927232356353,'2022-07-22 17:54:53','2022-07-22 17:54:53',1549934892404502529,1549934892404502529);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
