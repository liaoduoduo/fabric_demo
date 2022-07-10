/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : fabric

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 01/07/2022 16:18:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '性别',
  `id_number` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '身份证号',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态 0:禁用，1:正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '员工信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '管理员', 'admin', '123456', '13812312312', '1', '110101199001010047', 1, '2021-05-06 17:20:07', '2021-05-10 02:24:09', 1, 1);
INSERT INTO `user` VALUES (1530117579706761217, '廖多越', 'ldy', '123456', '15216195884', '1', '360700199900000000', 1, '2022-05-27 17:23:54', '2022-05-27 17:23:54', 1, 1);
INSERT INTO `user` VALUES (1531192761317855233, '安德拉斯', 'qwe', '123456', '17273646279', '0', '327828188839237722', 1, '2022-05-30 16:36:17', '2022-05-31 18:24:06', 1, 1);
INSERT INTO `user` VALUES (1534543388534116354, '阿斯顿', 'qweqw', '123456', '13533443423', '1', '356367776762762', 1, '2022-06-08 22:30:29', '2022-06-08 22:30:29', 1, 1);
INSERT INTO `user` VALUES (1540610747350966273, 'asda', 'ldy2', '123456', '15216195883', '1', '372163199805050075', 1, '2022-06-25 16:20:00', '2022-06-29 10:24:07', 1530117579706761217, 1);

SET FOREIGN_KEY_CHECKS = 1;
