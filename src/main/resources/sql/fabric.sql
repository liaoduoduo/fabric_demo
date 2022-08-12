/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:3306
 Source Schema         : fabric

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 12/08/2022 18:40:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cotasking
-- ----------------------------
DROP TABLE IF EXISTS `cotasking`;
CREATE TABLE `cotasking`  (
  `id` bigint(20) NOT NULL COMMENT '协同任务id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '协同任务简要描述',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '协同任务描述',
  `status` int(11) NOT NULL COMMENT '协同任务的状态,1表示激活，0表示未激活',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `li_an` int(11) NOT NULL COMMENT '是否达到立案标准',
  `deleted` int(11) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE,
  INDEX `create_user`(`create_user`) USING BTREE,
  CONSTRAINT `cotasking_ibfk_1` FOREIGN KEY (`create_user`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cotasking
-- ----------------------------
INSERT INTO `cotasking` VALUES (1551956170628100098, '虎门销烟', '林则徐在虎门进行销烟', 1, '2022-07-26 23:42:40', '2022-07-29 18:17:33', 1, 1, 1, 0);
INSERT INTO `cotasking` VALUES (1552606804301815810, '冰毒交易', '分析出本协同任务中的关键信息', 1, '2022-07-28 18:48:03', '2022-07-28 18:48:03', 1, 1, 0, 0);
INSERT INTO `cotasking` VALUES (1552606902649856001, '吸毒举报', '对举报信息进行核查', 1, '2022-07-28 18:48:26', '2022-07-29 17:24:13', 1, 1, 0, 0);
INSERT INTO `cotasking` VALUES (1552607912319492098, '吸毒', '分析出本协同任务中的关键信息', 1, '2022-07-28 18:52:27', '2022-07-28 18:52:27', 1, 1, 0, 1);
INSERT INTO `cotasking` VALUES (1552608123464949762, '毒品查处', '分析出本协同任务中的关键信息', 1, '2022-07-28 18:53:17', '2022-07-28 18:53:17', 1, 1, 0, 0);
INSERT INTO `cotasking` VALUES (1554328728876421122, '202282', '2', 1, '2022-08-02 12:50:22', '2022-08-02 12:50:22', 1554312923220602882, 1554312923220602882, 0, 1);
INSERT INTO `cotasking` VALUES (1554331637580107777, '测试任务', '干活', 1, '2022-08-02 13:01:55', '2022-08-02 13:01:55', 1554312923220602882, 1554312923220602882, 0, 1);
INSERT INTO `cotasking` VALUES (1554332274439012354, '123', '123123', 1, '2022-08-02 13:04:27', '2022-08-02 13:04:27', 1554312923220602882, 1554312923220602882, 0, 1);
INSERT INTO `cotasking` VALUES (1554333395106742273, '12312', 'asdasd', 1, '2022-08-02 13:08:54', '2022-08-02 13:08:54', 1554312923220602882, 1554312923220602882, 0, 1);
INSERT INTO `cotasking` VALUES (1555118193016623106, '喝茶', '1', 1, '2022-08-04 17:07:25', '2022-08-11 15:55:42', 1554312923220602882, 1554312923220602882, 0, 0);
INSERT INTO `cotasking` VALUES (1555783915367596033, '协同任务test', '1', 1, '2022-08-06 13:12:45', '2022-08-06 13:12:45', 1, 1, 0, 0);
INSERT INTO `cotasking` VALUES (1556947031132585986, 'test', '测试任务', 1, '2022-08-09 18:14:34', '2022-08-09 18:14:34', 1554312923220602882, 1554312923220602882, 0, 1);
INSERT INTO `cotasking` VALUES (1556947624500707330, 'test', '测试任务', 1, '2022-08-09 18:16:55', '2022-08-09 18:16:55', 1554312923220602882, 1554312923220602882, 0, 1);
INSERT INTO `cotasking` VALUES (1557258340185268226, 'test1', '测试任务1', 1, '2022-08-10 14:51:35', '2022-08-10 17:07:49', 1554312923220602882, 1554312923220602882, 0, 1);
INSERT INTO `cotasking` VALUES (1557364275188645889, 'test2', '测试任务3', 1, '2022-08-10 21:52:32', '2022-08-10 21:52:32', 1554312923220602882, 1554312923220602882, 0, 1);
INSERT INTO `cotasking` VALUES (1557553106034642946, 'test', '测试任务', 1, '2022-08-11 10:22:53', '2022-08-11 15:38:05', 1554312923220602882, 1554312923220602882, 0, 0);
INSERT INTO `cotasking` VALUES (1557632304392798209, 'test1', '测试任务2', 0, '2022-08-11 15:37:35', '2022-08-11 16:11:34', 1554312923220602882, 1554312923220602882, 0, 1);
INSERT INTO `cotasking` VALUES (1558004786073174017, 'test2', '测试任务3', 1, '2022-08-12 16:17:42', '2022-08-12 16:17:42', 1554312923220602882, 1554312923220602882, 0, 1);
INSERT INTO `cotasking` VALUES (1558014936527929345, 'test1', '测试任务1', 1, '2022-08-12 16:58:02', '2022-08-12 16:58:02', 1554312923220602882, 1554312923220602882, 0, 1);
INSERT INTO `cotasking` VALUES (1558017380267876354, 'test2', 'test2', 1, '2022-08-12 17:07:45', '2022-08-12 17:07:45', 1554312923220602882, 1554312923220602882, 0, 1);
INSERT INTO `cotasking` VALUES (1558023806046486530, 'test1', 'test1', 1, '2022-08-12 17:33:17', '2022-08-12 17:33:17', 1554312923220602882, 1554312923220602882, 0, 1);

-- ----------------------------
-- Table structure for cotasking_intelligence
-- ----------------------------
DROP TABLE IF EXISTS `cotasking_intelligence`;
CREATE TABLE `cotasking_intelligence`  (
  `id` bigint(20) NOT NULL COMMENT '线索id',
  `intelligence_id` bigint(20) NOT NULL COMMENT '情报id',
  `cotasking_id` bigint(20) NOT NULL COMMENT '协同任务id',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `deleted` int(11) NOT NULL DEFAULT 0 COMMENT '逻辑删除。1删除，0可查',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `intelligence_id`(`intelligence_id`) USING BTREE,
  INDEX `cotasking_id`(`cotasking_id`) USING BTREE,
  CONSTRAINT `cotasking_intelligence_ibfk_1` FOREIGN KEY (`intelligence_id`) REFERENCES `intelligence` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cotasking_intelligence_ibfk_2` FOREIGN KEY (`cotasking_id`) REFERENCES `cotasking` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cotasking_intelligence
-- ----------------------------
INSERT INTO `cotasking_intelligence` VALUES (1551956171634733057, 1, 1551956170628100098, '2022-07-26 23:42:40', '2022-07-26 23:42:40', 1, 1, 0);
INSERT INTO `cotasking_intelligence` VALUES (1552606805463638018, 1, 1552606804301815810, '2022-07-28 18:48:03', '2022-07-28 18:48:03', 1, 1, 0);
INSERT INTO `cotasking_intelligence` VALUES (1552606805522358274, 2, 1552606804301815810, '2022-07-28 18:48:03', '2022-07-28 18:48:03', 1, 1, 0);
INSERT INTO `cotasking_intelligence` VALUES (1552606902683410433, 2, 1552606902649856001, '2022-07-28 18:48:26', '2022-07-28 18:48:26', 1, 1, 0);
INSERT INTO `cotasking_intelligence` VALUES (1552607912428544002, 2, 1552607912319492098, '2022-07-28 18:52:27', '2022-07-28 18:52:27', 1, 1, 0);
INSERT INTO `cotasking_intelligence` VALUES (1552607912466292738, 3, 1552607912319492098, '2022-07-28 18:52:27', '2022-07-28 18:52:27', 1, 1, 0);
INSERT INTO `cotasking_intelligence` VALUES (1552608123506892801, 1, 1552608123464949762, '2022-07-28 18:53:17', '2022-07-28 18:53:17', 1, 1, 0);
INSERT INTO `cotasking_intelligence` VALUES (1552608123544641538, 2, 1552608123464949762, '2022-07-28 18:53:17', '2022-07-28 18:53:17', 1, 1, 0);
INSERT INTO `cotasking_intelligence` VALUES (1552608123586584577, 3, 1552608123464949762, '2022-07-28 18:53:17', '2022-07-28 18:53:17', 1, 1, 0);
INSERT INTO `cotasking_intelligence` VALUES (1553279651802193922, 4, 1552608123464949762, '2022-07-30 15:21:42', '2022-07-30 15:21:42', 1, 1, 1);
INSERT INTO `cotasking_intelligence` VALUES (1554328728972890114, 4, 1554328728876421122, '2022-08-02 12:50:22', '2022-08-02 12:50:22', 1554312923220602882, 1554312923220602882, 0);
INSERT INTO `cotasking_intelligence` VALUES (1554331637580107778, 4, 1554331637580107777, '2022-08-02 13:01:55', '2022-08-02 13:01:55', 1554312923220602882, 1554312923220602882, 0);
INSERT INTO `cotasking_intelligence` VALUES (1554332274493538306, 4, 1554332274439012354, '2022-08-02 13:04:27', '2022-08-02 13:04:27', 1554312923220602882, 1554312923220602882, 0);
INSERT INTO `cotasking_intelligence` VALUES (1554333395148685313, 4, 1554333395106742273, '2022-08-02 13:08:54', '2022-08-02 13:08:54', 1554312923220602882, 1554312923220602882, 0);
INSERT INTO `cotasking_intelligence` VALUES (1555118193045983233, 2, 1555118193016623106, '2022-08-04 17:07:25', '2022-08-04 17:07:25', 1554312923220602882, 1554312923220602882, 1);
INSERT INTO `cotasking_intelligence` VALUES (1555118193045983234, 4, 1555118193016623106, '2022-08-04 17:07:25', '2022-08-04 17:07:25', 1554312923220602882, 1554312923220602882, 0);
INSERT INTO `cotasking_intelligence` VALUES (1555783915434704897, 1555783809486585858, 1555783915367596033, '2022-08-06 13:12:45', '2022-08-06 13:12:45', 1, 1, 0);
INSERT INTO `cotasking_intelligence` VALUES (1556947031178723329, 2, 1556947031132585986, '2022-08-09 18:14:34', '2022-08-09 18:14:34', 1554312923220602882, 1554312923220602882, 0);
INSERT INTO `cotasking_intelligence` VALUES (1556947031178723330, 4, 1556947031132585986, '2022-08-09 18:14:34', '2022-08-09 18:14:34', 1554312923220602882, 1554312923220602882, 0);
INSERT INTO `cotasking_intelligence` VALUES (1556947624521678850, 2, 1556947624500707330, '2022-08-09 18:16:55', '2022-08-09 18:16:55', 1554312923220602882, 1554312923220602882, 1);
INSERT INTO `cotasking_intelligence` VALUES (1556947624521678851, 4, 1556947624500707330, '2022-08-09 18:16:55', '2022-08-09 18:16:55', 1554312923220602882, 1554312923220602882, 1);
INSERT INTO `cotasking_intelligence` VALUES (1557258340218822657, 4, 1557258340185268226, '2022-08-10 14:51:35', '2022-08-10 14:51:35', 1554312923220602882, 1554312923220602882, 1);
INSERT INTO `cotasking_intelligence` VALUES (1557330062532009985, 2, 1557258340185268226, '2022-08-10 19:36:35', '2022-08-10 19:36:35', 1554312923220602882, 1554312923220602882, 1);
INSERT INTO `cotasking_intelligence` VALUES (1557364275188645890, 2, 1557364275188645889, '2022-08-10 21:52:32', '2022-08-10 21:52:32', 1554312923220602882, 1554312923220602882, 1);
INSERT INTO `cotasking_intelligence` VALUES (1557553106051420161, 2, 1557553106034642946, '2022-08-11 10:22:53', '2022-08-11 10:22:53', 1554312923220602882, 1554312923220602882, 0);
INSERT INTO `cotasking_intelligence` VALUES (1557553106051420162, 4, 1557553106034642946, '2022-08-11 10:22:53', '2022-08-11 10:22:53', 1554312923220602882, 1554312923220602882, 0);
INSERT INTO `cotasking_intelligence` VALUES (1557632304417964034, 2, 1557632304392798209, '2022-08-11 15:37:35', '2022-08-11 15:37:35', 1554312923220602882, 1554312923220602882, 1);
INSERT INTO `cotasking_intelligence` VALUES (1558004786152865793, 2, 1558004786073174017, '2022-08-12 16:17:42', '2022-08-12 16:17:42', 1554312923220602882, 1554312923220602882, 1);
INSERT INTO `cotasking_intelligence` VALUES (1558004786152865794, 4, 1558004786073174017, '2022-08-12 16:17:42', '2022-08-12 16:17:42', 1554312923220602882, 1554312923220602882, 1);
INSERT INTO `cotasking_intelligence` VALUES (1558014936527929346, 2, 1558014936527929345, '2022-08-12 16:58:02', '2022-08-12 16:58:02', 1554312923220602882, 1554312923220602882, 1);
INSERT INTO `cotasking_intelligence` VALUES (1558014936527929347, 4, 1558014936527929345, '2022-08-12 16:58:02', '2022-08-12 16:58:02', 1554312923220602882, 1554312923220602882, 1);
INSERT INTO `cotasking_intelligence` VALUES (1558017380284653569, 2, 1558017380267876354, '2022-08-12 17:07:45', '2022-08-12 17:07:45', 1554312923220602882, 1554312923220602882, 1);
INSERT INTO `cotasking_intelligence` VALUES (1558023806084235266, 2, 1558023806046486530, '2022-08-12 17:33:17', '2022-08-12 17:33:17', 1554312923220602882, 1554312923220602882, 1);
INSERT INTO `cotasking_intelligence` VALUES (1558023806084235267, 4, 1558023806046486530, '2022-08-12 17:33:17', '2022-08-12 17:33:17', 1554312923220602882, 1554312923220602882, 1);

-- ----------------------------
-- Table structure for decide_info
-- ----------------------------
DROP TABLE IF EXISTS `decide_info`;
CREATE TABLE `decide_info`  (
  `id` bigint(20) NOT NULL COMMENT '研判任务信息id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存储对应判定信息分类的字段，用于查询，比如select a,b,c from A',
  `name_zh` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对应字段的中文',
  `decide_info_category_id` bigint(20) NOT NULL COMMENT '研判任务信息分类id',
  `weight` double NULL DEFAULT NULL COMMENT '对应信息字段的权重',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `deleted` int(11) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `decide_info_category_id`(`decide_info_category_id`) USING BTREE,
  CONSTRAINT `decide_info_ibfk_1` FOREIGN KEY (`decide_info_category_id`) REFERENCES `decide_info_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of decide_info
-- ----------------------------
INSERT INTO `decide_info` VALUES (1, 'Quasi_driving_type', '准驾车型', 16, 0.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (2, 'Registration_number', '注册号', 5, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (3, 'Payment_account', '支付账号', 18, 1.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (4, 'Payment_type', '支付类型', 18, 1.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (5, 'photo', '照片', 17, 0.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (6, 'Account_information', '账号信息', 1, 2.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (7, 'The_reserved_phone', '预留手机', 12, 1, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (8, 'Relationships_with_members_of_society', '与社会成员关系', 7, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (9, 'Relationships_with_family_members', '与家庭成员关系', 15, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (10, 'Valid_term', '有效期限', 16, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (11, 'The_bank_bills', '银行账单', 9, 1, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (12, 'Type_of_bank_card', '银行卡种类', 12, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (13, 'Bank_card_Number', '银行卡卡号', 12, 1, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (14, 'Virtual_identity_type', '虚拟身份类型', 1, 2.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (15, 'How_detailed', '详细程度', 11, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (16, 'Clue_details', '线索详细内容', 11, 2.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (17, 'The_clue_involves', '线索涉及地', 11, 0.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (18, 'Clues_to_the_source', '线索来源', 11, 1, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (19, 'Informant', '线索举报人', 11, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (20, 'Clues_to_the_title', '线索标题', 11, 1, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (21, 'Occupation_of_suspect', '嫌疑人职业', 17, 0.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (22, 'Suspects_pay_for_cell_phone_numbers', '嫌疑人支付手机号码', 18, 1, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (23, 'Name_of_suspect', '嫌疑人姓名', 17, 0.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (24, 'Gender_of_suspect', '嫌疑人性别', 17, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (25, 'Suspect_cell_phone_number', '嫌疑人手机号码', 17, 2.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (26, 'Suspect_id_Number', '嫌疑人身份证号码', 17, 2.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (27, 'Place_of_residence_of_the_suspect', '嫌疑人户籍地', 17, 0.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (28, 'The_address_book', '通讯录', 9, 1, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (29, 'Belongs_to_the_special', '所属专项', 11, 1, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (30, 'Recipient_phone_number', '收件人电话', 3, 0.05, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (31, 'Addressee_address', '收件人地址', 3, 0.05, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (32, 'The_recipient', '收件人', 3, 0.05, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (33, 'Video_data', '视频资料', 9, 1, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (34, 'Whether_the_event_is_real_or_not', '事件是否真实', 2, 1.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (35, 'Drugs_involved', '涉及的毒品', 11, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (36, 'Types_of_drug_related', '涉毒种类', 4, 5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (37, 'Degree_of_suspicion_of_drug_involvement', '涉毒嫌疑度', 10, 5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (38, 'Drug_related_scale', '涉毒规模', 4, 5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (39, 'Drug_related_way', '涉毒方式', 4, 5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (40, 'Types_of_drug_related_crimes', '涉毒犯罪类型', 11, 1, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (41, 'Drug_related_site', '涉毒地点', 4, 5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (42, 'In_the_case_of_information', '涉案信息', 1, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (43, 'Profession_of_social_member', '社会成员职业', 7, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (44, 'The_virtual_identity_of_a_social_member', '社会成员虚拟身份', 7, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (45, 'Name_of_social_member', '社会成员姓名', 7, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (46, 'Gender_of_social_member', '社会成员性别', 7, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (47, 'Cell_phone_number_of_a_social_member', '社会成员手机号码', 7, 0.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (48, 'Social_member_id_card_number', '社会成员身份证号码', 7, 0.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (49, 'Place_of_residence_of_social_member', '社会成员户籍地', 7, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (50, 'To_sign_for_the_time', '签收时间', 3, 0.05, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (51, 'brand', '品牌', 13, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (52, 'The_judgment_basis_of_suspicion', '嫌疑度判断依据', 10, 5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (53, 'Basis_of_authenticity_Judgment', '真实性判断依据', 2, 2.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (54, 'age', '年龄', 17, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (55, 'national', '民族', 17, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (56, 'A_case_of_time', '立案时间', 6, 0.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (57, 'Filing_decision', '立案决定书', 6, 15, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (58, 'Source_reliability', '来源可靠性', 11, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (59, 'Courier_company', '快递公司', 3, 0.05, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (60, 'Courier_number', '快递单号', 3, 0.05, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (61, 'Name_of_opening_bank', '开户行名称', 12, 0.1, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (62, 'Opening_bank_address', '开户行地址', 12, 0.15, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (63, 'To_live', '居住地', 17, 0.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (64, 'Scope_of_business', '经营范围', 5, 0.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (65, 'The_degree_of_emergency', '紧急程度', 11, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (66, 'Family_member_occupation', '家庭成员职业', 15, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (67, 'Family_member_virtual_identity', '家庭成员虚拟身份', 15, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (68, 'Name_of_family_member', '家庭成员姓名', 15, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (69, 'Gender_of_family_members', '家庭成员性别', 15, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (70, 'Family_member_mobile_phone_number', '家庭成员手机号码', 15, 0.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (71, 'Id_card_number_of_family_member', '家庭成员身份证号码', 15, 0.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (72, 'Place_of_residence_of_a_family_member', '家庭成员户籍地', 15, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (73, 'Return_telephone', '寄件人电话', 3, 0.05, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (74, 'Return_address', '寄件人地址', 3, 0.05, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (75, 'The_sender', '寄件人', 3, 0.05, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (76, 'Motor_vehicle_driving_license_number', '机动车驾驶证号', 16, 1.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (77, 'To_get_the_time', '获取时间', 11, 1, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (78, 'The_activity_time', '活动时间', 8, 0.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (79, 'Activity_trajectory_description', '活动轨迹描述', 8, 0.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (80, 'billed', '话单', 9, 1, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (81, 'Check_the_time', '核查时间', 2, 1, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (82, 'Landline_number', '固话号码', 17, 1, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (83, 'Location_of_property', '房产所在地', 14, 1, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (84, 'Real_Estate_Information', '房产基本情况', 14, 1, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (85, 'Property_number', '房产编号', 14, 0.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (86, 'Trafficking_way', '贩运方式', 11, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (87, 'A_time', '发出时间', 3, 0.05, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (88, 'Drug_flow', '毒品流向', 11, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (89, 'Name_of_the_entity', '单位名称', 5, 1, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (90, 'The_unit_address', '单位地址', 5, 0.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (91, 'The_disposal_of_information', '处置信息', 1, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (92, 'Initial_claim_time', '初次申领时间', 16, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (93, 'Set_up_the_date', '成立日期', 5, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (94, 'License_plate_number', '车牌号', 13, 1.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (95, 'Vehicle_color', '车辆颜色', 13, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (96, 'Vehicle_type', '车辆类型', 13, 0.5, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (97, 'Seized_information', '查获信息', 1, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (98, 'Name_of_the_case', '案件名称', 6, 1, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (99, 'The_case_level', '案件等级', 11, 0.25, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);
INSERT INTO `decide_info` VALUES (100, 'The_case_number', '案件编号', 6, 1, '2022-07-31 17:10:55', 1, 1, '2022-07-31 17:10:55', 0);

-- ----------------------------
-- Table structure for decide_info_category
-- ----------------------------
DROP TABLE IF EXISTS `decide_info_category`;
CREATE TABLE `decide_info_category`  (
  `id` bigint(20) NOT NULL COMMENT '研判信息分类id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '研判信息分类名称',
  `task_category_id` bigint(20) NOT NULL COMMENT '研判任务分类id',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `deleted` int(11) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `task_category_id`(`task_category_id`) USING BTREE,
  CONSTRAINT `decide_info_category_ibfk_1` FOREIGN KEY (`task_category_id`) REFERENCES `task_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of decide_info_category
-- ----------------------------
INSERT INTO `decide_info_category` VALUES (1, '嫌疑人虚拟身份信息', 1, '2022-07-31 17:00:17', '2022-07-31 17:00:17', 1, 1, 0);
INSERT INTO `decide_info_category` VALUES (2, '事件真实性核查信息', 1, '2022-07-31 17:00:17', '2022-07-31 17:00:17', 1, 1, 0);
INSERT INTO `decide_info_category` VALUES (3, '物流寄递信息', 2, '2022-07-31 17:00:17', '2022-07-31 17:00:17', 1, 1, 0);
INSERT INTO `decide_info_category` VALUES (4, '涉毒特点', 3, '2022-07-31 17:00:17', '2022-07-31 17:00:17', 1, 1, 0);
INSERT INTO `decide_info_category` VALUES (5, '嫌疑人企业信息', 1, '2022-07-31 17:00:17', '2022-07-31 17:00:17', 1, 1, 0);
INSERT INTO `decide_info_category` VALUES (6, '立案信息', 3, '2022-07-31 17:00:17', '2022-07-31 17:00:17', 1, 1, 0);
INSERT INTO `decide_info_category` VALUES (7, '社会成员信息', 1, '2022-07-31 17:00:17', '2022-07-31 17:00:17', 1, 1, 0);
INSERT INTO `decide_info_category` VALUES (8, '活动轨迹信息', 3, '2022-07-31 17:00:17', '2022-07-31 17:00:17', 1, 1, 0);
INSERT INTO `decide_info_category` VALUES (9, '账单类信息', 2, '2022-07-31 17:00:17', '2022-07-31 17:00:17', 1, 1, 0);
INSERT INTO `decide_info_category` VALUES (10, '涉毒嫌疑度分析研判结果', 2, '2022-07-31 17:00:17', '2022-07-31 17:00:17', 1, 1, 0);
INSERT INTO `decide_info_category` VALUES (11, '线索基本信息', 4, '2022-07-31 17:00:17', '2022-07-31 17:00:17', 1, 1, 0);
INSERT INTO `decide_info_category` VALUES (12, '嫌疑人银行卡信息', 1, '2022-07-31 17:00:17', '2022-07-31 17:00:17', 1, 1, 0);
INSERT INTO `decide_info_category` VALUES (13, '嫌疑人车辆信息', 1, '2022-07-31 17:00:17', '2022-07-31 17:00:17', 1, 1, 0);
INSERT INTO `decide_info_category` VALUES (14, '嫌疑人房产信息', 1, '2022-07-31 17:00:17', '2022-07-31 17:00:17', 1, 1, 0);
INSERT INTO `decide_info_category` VALUES (15, '家庭成员信息', 1, '2022-07-31 17:00:17', '2022-07-31 17:00:17', 1, 1, 0);
INSERT INTO `decide_info_category` VALUES (16, '嫌疑人驾驶证信息', 1, '2022-07-31 17:00:17', '2022-07-31 17:00:17', 1, 1, 0);
INSERT INTO `decide_info_category` VALUES (17, '嫌疑人身份信息', 1, '2022-07-31 17:00:17', '2022-07-31 17:00:17', 1, 1, 0);
INSERT INTO `decide_info_category` VALUES (18, '嫌疑人支付信息', 1, '2022-07-31 17:00:17', '2022-07-31 17:00:17', 1, 1, 0);

-- ----------------------------
-- Table structure for intelligence
-- ----------------------------
DROP TABLE IF EXISTS `intelligence`;
CREATE TABLE `intelligence`  (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `intelligence_category_id` bigint(20) NOT NULL COMMENT '情报分类ID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '情报名',
  `file_hash` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '情报hash',
  `image` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件地址',
  `description` varchar(400) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '描述信息',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '0 停售 1 起售',
  `token` decimal(10, 2) NOT NULL COMMENT '情报售价',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `deleted` int(11) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `intelligence_category_id`(`intelligence_category_id`) USING BTREE,
  INDEX `intelligence_ibfk_2`(`create_user`) USING BTREE,
  CONSTRAINT `intelligence_ibfk_1` FOREIGN KEY (`intelligence_category_id`) REFERENCES `intelligence_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `intelligence_ibfk_2` FOREIGN KEY (`create_user`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '情报表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of intelligence
-- ----------------------------
INSERT INTO `intelligence` VALUES (1, 1, 1, '冰毒', 'test hash', 'test', 'full test ', 1, 2.00, '2022-07-05 00:00:00', '2022-07-07 00:00:00', 1, 1, 0);
INSERT INTO `intelligence` VALUES (2, 1, 1, '人员吸毒', 'test hash', '举报电话录音', '某某时间某人举报他人聚众吸毒', 1, 20.00, '2022-07-28 00:00:00', '2022-07-29 19:17:45', 1, 1, 0);
INSERT INTO `intelligence` VALUES (3, 1, 1, '聚众吸毒', 'test hash', '举报图片', '某某时间某人举报他人聚众吸毒', 1, 30.00, '2022-07-28 00:00:00', '2022-07-29 19:17:45', 1, 1, 0);
INSERT INTO `intelligence` VALUES (4, 1, 1, '摇头丸交易', 'test hash', '举报信息', '某某酒吧出现毒品交易', 1, 20.00, '2022-07-30 00:00:00', '2022-07-30 11:12:13', 1, 1, 0);
INSERT INTO `intelligence` VALUES (1555783319503798274, 1, 1, '禁毒行动', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'a3206cb4-b668-4bab-8c47-1d02fa9c2f9d.png', '按照“两挂双统一”部署，坚持“百日行动”“清源断流”“禁毒安保”相互促进、一体推进，迅速形成严厉打击整治毒品违法犯罪的凌厉攻势和高压态势。一个月来，全国共破获毒品犯罪案件2589起，抓获毒品犯罪嫌疑人3341名，缴获各类毒品1.31吨，有力打击震慑了各类涉毒活动，有效管控遏制了多种涉毒风险。', 1, 100.00, '2022-08-06 13:10:23', '2022-08-06 13:10:23', 1, 1, 0);
INSERT INTO `intelligence` VALUES (1555783809486585858, 1, 1, '禁毒宣传我们在行动', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '5a701393-80e3-4ac6-9b2d-8e885948037b.png', '为贯彻落实省、市关于开展2022年禁毒攻坚年工作部署,我市各地高度重视、迅速响应,开展系列宣传行动,全力推进禁毒攻坚年行动落实落细落地。 ', 1, 99.00, '2022-08-06 13:12:20', '2022-08-06 13:12:20', 1, 1, 0);

-- ----------------------------
-- Table structure for intelligence_category
-- ----------------------------
DROP TABLE IF EXISTS `intelligence_category`;
CREATE TABLE `intelligence_category`  (
  `id` bigint(20) NOT NULL COMMENT '情报分类id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称名称',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `deleted` int(11) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of intelligence_category
-- ----------------------------
INSERT INTO `intelligence_category` VALUES (1, '病毒', '2022-07-24 23:51:00', '2022-07-24 23:52:05', 1, 1, 0);

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
  `id` bigint(20) NOT NULL COMMENT '研判任务id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '研判任务简要需求',
  `cotasking_id` bigint(20) NOT NULL COMMENT '所属协同任务id',
  `task_category_id` bigint(20) NOT NULL COMMENT '任务分类id',
  `decide_info_category_id` bigint(20) NOT NULL COMMENT '待研判任务分类id，与任务分类id作为联合主键，用于确定相应需填写的内容',
  `begin_time` datetime(0) NOT NULL COMMENT '任务开始时间',
  `end_time` datetime(0) NOT NULL COMMENT '任务结束时间',
  `token` decimal(10, 2) NOT NULL COMMENT '任务金额',
  `open` int(11) NOT NULL COMMENT '任务是否公开，作为悬赏',
  `status` int(11) NOT NULL COMMENT '是否激活',
  `policy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '领取策略',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `finished` int(11) NOT NULL COMMENT '是否完成',
  `evaluation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务评价',
  `deleted` int(11) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t1`(`cotasking_id`) USING BTREE,
  INDEX `t2`(`task_category_id`) USING BTREE,
  INDEX `t3`(`decide_info_category_id`) USING BTREE,
  CONSTRAINT `t1` FOREIGN KEY (`cotasking_id`) REFERENCES `cotasking` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t2` FOREIGN KEY (`task_category_id`) REFERENCES `task_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t3` FOREIGN KEY (`decide_info_category_id`) REFERENCES `decide_info_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES (1556213107393638401, '核查虚拟身份', 1555118193016623106, 1, 1, '2022-08-07 12:00:00', '2022-08-09 12:00:00', 0.10, 1, 1, NULL, '2022-08-07 17:38:13', '2022-08-11 15:55:42', 1554312923220602882, 1554312923220602882, 0, '', 0);
INSERT INTO `task` VALUES (1556273955713581057, '房产信息调查', 1555118193016623106, 1, 14, '2022-08-07 12:00:00', '2022-08-13 12:00:00', 0.30, 0, 1, '广东省禁毒厅', '2022-08-07 21:40:00', '2022-08-11 15:55:42', 1554312923220602882, 1554312923220602882, 0, '', 0);
INSERT INTO `task` VALUES (1556275928202477569, '轨迹信息分析', 1555118193016623106, 3, 8, '2022-08-07 12:00:00', '2022-08-20 12:00:00', 1.00, 1, 1, NULL, '2022-08-07 21:47:50', '2022-08-11 15:55:42', 1554312923220602882, 1554312923220602882, 0, '', 0);
INSERT INTO `task` VALUES (1556276641573896193, '账单分析', 1555118193016623106, 2, 9, '2022-08-07 12:00:00', '2022-09-06 12:00:00', 1.10, 0, 1, '广东省禁毒厅', '2022-08-07 21:50:40', '2022-08-11 15:55:42', 1554312923220602882, 1554312923220602882, 0, '', 0);
INSERT INTO `task` VALUES (1556277737361022978, '物流分析', 1555118193016623106, 2, 3, '2022-08-14 12:00:00', '2022-08-19 12:00:00', 0.90, 1, 1, NULL, '2022-08-07 21:55:01', '2022-08-11 15:55:42', 1554312923220602882, 1554312923220602882, 0, '', 0);
INSERT INTO `task` VALUES (1556546706768674817, '社会关系核实', 1555118193016623106, 1, 7, '2022-08-08 12:00:00', '2022-08-12 12:00:00', 0.60, 1, 1, NULL, '2022-08-08 15:43:49', '2022-08-11 15:55:42', 1554312923220602882, 1554312923220602882, 0, '', 0);
INSERT INTO `task` VALUES (1556646729149902850, '确定涉毒特点', 1555118193016623106, 3, 4, '2022-08-07 12:00:00', '2022-08-08 12:00:00', 0.90, 1, 1, NULL, '2022-08-08 22:21:16', '2022-08-11 15:55:42', 1554312923220602882, 1554312923220602882, 0, '', 0);
INSERT INTO `task` VALUES (1556915770489950209, '其他任务', 1555118193016623106, 4, 11, '2022-08-09 12:00:00', '2022-08-12 12:00:00', 0.20, 1, 1, NULL, '2022-08-09 16:10:20', '2022-08-11 15:55:42', 1554312923220602882, 1554312923220602882, 0, '', 0);
INSERT INTO `task` VALUES (1556927789171499010, '家庭成员', 1555118193016623106, 1, 15, '2022-08-09 12:00:00', '2022-09-19 12:00:00', 0.50, 1, 1, NULL, '2022-08-09 16:58:06', '2022-08-11 15:55:42', 1554312923220602882, 1554312923220602882, 0, '', 0);
INSERT INTO `task` VALUES (1556929490398044162, '立案分析', 1555118193016623106, 3, 6, '2022-08-09 12:00:00', '2022-09-04 12:00:00', 0.01, 1, 1, NULL, '2022-08-09 17:04:52', '2022-08-11 15:55:42', 1554312923220602882, 1554312923220602882, 0, '', 0);
INSERT INTO `task` VALUES (1557007624795324417, '真实性核查', 1556947624500707330, 1, 2, '2022-08-09 12:00:00', '2022-08-27 12:00:00', 1.00, 0, 1, '广东省禁毒厅', '2022-08-09 22:15:20', '2022-08-09 22:15:20', 1554312923220602882, 1554312923220602882, 0, '', 1);
INSERT INTO `task` VALUES (1557296744721596418, '核查企业信息', 1557258340185268226, 1, 5, '2022-08-10 12:00:00', '2022-09-19 12:00:00', 0.10, 1, 1, NULL, '2022-08-10 17:24:12', '2022-08-10 17:24:12', 1554312923220602882, 1554312923220602882, 0, '', 1);
INSERT INTO `task` VALUES (1557553361014771714, '物流信息核查', 1557553106034642946, 2, 3, '2022-08-11 12:00:00', '2022-08-18 12:00:00', 3.50, 1, 1, NULL, '2022-08-11 10:23:54', '2022-08-12 18:37:31', 1554312923220602882, 1554312923220602882, 0, '', 0);
INSERT INTO `task` VALUES (1557640821199683586, '账单分析', 1557632304392798209, 2, 9, '2022-08-11 12:00:00', '2022-08-19 12:00:00', 0.20, 1, 0, NULL, '2022-08-11 16:11:26', '2022-08-11 16:11:34', 1554312923220602882, 1554312923220602882, 0, '', 1);
INSERT INTO `task` VALUES (1558005093641486338, 'test', 1558004786073174017, 2, 10, '2022-08-12 12:00:00', '2022-08-25 12:00:00', 0.56, 1, 1, NULL, '2022-08-12 16:18:55', '2022-08-12 16:18:55', 1554312923220602882, 1554312923220602882, 0, '', 1);
INSERT INTO `task` VALUES (1558015164144418817, 'test', 1558014936527929345, 2, 9, '2022-08-12 12:00:00', '2022-08-19 12:00:00', 0.97, 1, 1, NULL, '2022-08-12 16:58:56', '2022-08-12 16:58:56', 1554312923220602882, 1554312923220602882, 0, '', 1);
INSERT INTO `task` VALUES (1558017466888642562, 'test', 1558017380267876354, 2, 10, '2022-08-12 12:00:00', '2022-08-19 12:00:00', 0.97, 1, 1, NULL, '2022-08-12 17:08:05', '2022-08-12 17:08:05', 1554312923220602882, 1554312923220602882, 0, '', 1);
INSERT INTO `task` VALUES (1558024991990214658, 'tsee', 1558023806046486530, 2, 9, '2022-08-12 12:00:00', '2022-08-19 12:00:00', 0.55, 1, 1, NULL, '2022-08-12 17:37:59', '2022-08-12 17:37:59', 1554312923220602882, 1554312923220602882, 0, '', 1);

-- ----------------------------
-- Table structure for task_category
-- ----------------------------
DROP TABLE IF EXISTS `task_category`;
CREATE TABLE `task_category`  (
  `id` bigint(20) NOT NULL COMMENT '任务分类id',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务分类名称',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `deleted` int(11) NOT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task_category
-- ----------------------------
INSERT INTO `task_category` VALUES (1, '信息核查类任务', '2022-07-31 16:26:51', '2022-07-31 16:26:51', 1, 1, 0);
INSERT INTO `task_category` VALUES (2, '涉毒嫌疑度分析类任务', '2022-07-31 16:26:51', '2022-07-31 16:26:51', 1, 1, 0);
INSERT INTO `task_category` VALUES (3, '涉毒情况确定类任务', '2022-07-31 16:26:51', '2022-07-31 16:26:51', 1, 1, 0);
INSERT INTO `task_category` VALUES (4, '其他任务', '2022-07-31 16:26:51', '2022-07-31 16:26:51', 1, 1, 0);

-- ----------------------------
-- Table structure for task_detail
-- ----------------------------
DROP TABLE IF EXISTS `task_detail`;
CREATE TABLE `task_detail`  (
  `id` bigint(20) NOT NULL COMMENT '任务详情id',
  `Quasi_driving_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '准驾车型',
  `Registration_number` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册号',
  `Payment_account` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付账号',
  `Payment_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '照片',
  `Account_information` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '账号信息',
  `The_reserved_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留手机',
  `Relationships_with_members_of_society` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '与社会成员关系',
  `Relationships_with_family_members` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '与家庭成员关系',
  `Valid_term` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '有效期限',
  `The_bank_bills` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '银行账单',
  `Type_of_bank_card` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行卡种类',
  `Bank_card_Number` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行卡卡号',
  `Virtual_identity_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '虚拟身份类型',
  `How_detailed` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细程度',
  `Clue_details` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '线索详细内容',
  `The_clue_involves` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '线索涉及地',
  `Clues_to_the_source` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '线索来源',
  `Informant` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '线索举报人',
  `Clues_to_the_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '线索标题',
  `Occupation_of_suspect` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '嫌疑人职业',
  `Suspects_pay_for_cell_phone_numbers` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '嫌疑人支付手机号码',
  `Name_of_suspect` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '嫌疑人姓名',
  `Gender_of_suspect` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '嫌疑人性别',
  `Suspect_cell_phone_number` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '嫌疑人手机号码',
  `Suspect_id_Number` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '嫌疑人身份证号码',
  `Place_of_residence_of_the_suspect` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '嫌疑人户籍地',
  `The_address_book` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '通讯录',
  `Belongs_to_the_special` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属专项',
  `Recipient_phone_number` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人电话',
  `Addressee_address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人地址',
  `The_recipient` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人',
  `Video_data` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '视频资料',
  `Whether_the_event_is_real_or_not` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事件是否真实',
  `Drugs_involved` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '涉及的毒品',
  `Types_of_drug_related` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '涉毒种类',
  `Degree_of_suspicion_of_drug_involvement` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '涉毒嫌疑度',
  `Drug_related_scale` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '涉毒规模',
  `Drug_related_way` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '涉毒方式',
  `Types_of_drug_related_crimes` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '涉毒犯罪类型',
  `Drug_related_site` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '涉毒地点',
  `In_the_case_of_information` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '涉案信息',
  `Profession_of_social_member` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '社会成员职业',
  `The_virtual_identity_of_a_social_member` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '社会成员虚拟身份',
  `Name_of_social_member` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '社会成员姓名',
  `Gender_of_social_member` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '社会成员性别',
  `Cell_phone_number_of_a_social_member` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '社会成员手机号码',
  `Social_member_id_card_number` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '社会成员身份证号码',
  `Place_of_residence_of_social_member` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '社会成员户籍地',
  `To_sign_for_the_time` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签收时间',
  `brand` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌',
  `The_judgment_basis_of_suspicion` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '嫌疑度判断依据',
  `Basis_of_authenticity_Judgment` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '真实性判断依据',
  `age` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年龄',
  `national` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族',
  `A_case_of_time` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '立案时间',
  `Filing_decision` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '立案决定书',
  `Source_reliability` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '来源可靠性',
  `Courier_company` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '快递公司',
  `Courier_number` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '快递单号',
  `Name_of_opening_bank` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户行名称',
  `Opening_bank_address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户行地址',
  `To_live` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '居住地',
  `Scope_of_business` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经营范围',
  `The_degree_of_emergency` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '紧急程度',
  `Family_member_occupation` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭成员职业',
  `Family_member_virtual_identity` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭成员虚拟身份',
  `Name_of_family_member` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭成员姓名',
  `Gender_of_family_members` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭成员性别',
  `Family_member_mobile_phone_number` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭成员手机号码',
  `Id_card_number_of_family_member` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭成员身份证号码',
  `Place_of_residence_of_a_family_member` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭成员户籍地',
  `Return_telephone` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '寄件人电话',
  `Return_address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '寄件人地址',
  `The_sender` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '寄件人',
  `Motor_vehicle_driving_license_number` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '机动车驾驶证号',
  `To_get_the_time` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '获取时间',
  `The_activity_time` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动时间',
  `Activity_trajectory_description` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动轨迹描述',
  `billed` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '话单',
  `Check_the_time` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '核查时间',
  `Landline_number` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '固话号码',
  `Location_of_property` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '房产所在地',
  `Real_Estate_Information` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '房产基本情况',
  `Property_number` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房产编号',
  `Trafficking_way` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '贩运方式',
  `A_time` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发出时间',
  `Drug_flow` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '毒品流向',
  `Name_of_the_entity` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '单位名称',
  `The_unit_address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位地址',
  `The_disposal_of_information` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处置信息',
  `Initial_claim_time` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '初次申领时间',
  `Set_up_the_date` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '成立日期',
  `License_plate_number` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '车牌号',
  `Vehicle_color` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆颜色',
  `Vehicle_type` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆类型',
  `Seized_information` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '查获信息',
  `Name_of_the_case` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '案件名称',
  `The_case_level` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '案件等级',
  `The_case_number` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '案件编号',
  `deleted` int(11) NOT NULL COMMENT '逻辑删除',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task_detail
-- ----------------------------

-- ----------------------------
-- Table structure for token
-- ----------------------------
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token`  (
  `id` bigint(20) NOT NULL COMMENT 'Token主键',
  `current_token` decimal(10, 2) NOT NULL COMMENT '总token',
  `block_token` decimal(10, 2) NOT NULL COMMENT '任务发布冻结token',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '钱包密码',
  `status` int(11) NOT NULL COMMENT '钱包状态',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `deleted` int(11) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of token
-- ----------------------------
INSERT INTO `token` VALUES (1, 10022.00, 0.20, '123456', 1, '2022-07-24 22:05:00', '2022-08-03 17:53:02', 1, 1, 0);
INSERT INTO `token` VALUES (1554313285411336194, 11218.00, 93.00, '123456', 1, '2022-08-02 11:49:00', '2022-08-12 18:37:31', 1, 1554312923220602882, 0);
INSERT INTO `token` VALUES (1554768275022409729, 997.23, 0.00, '123456', 1, '2022-08-03 17:56:58', '2022-08-03 17:59:18', 1, 1, 0);

-- ----------------------------
-- Table structure for token_deal
-- ----------------------------
DROP TABLE IF EXISTS `token_deal`;
CREATE TABLE `token_deal`  (
  `id` bigint(20) NOT NULL COMMENT 'token交易id',
  `from_user` bigint(20) NOT NULL COMMENT '支付方',
  `to_user` bigint(20) NOT NULL COMMENT '收款方',
  `task_id` bigint(20) NULL DEFAULT NULL COMMENT '当收到的是任务时',
  `intelligence_id` bigint(20) NULL DEFAULT NULL COMMENT '当购买的是情报时',
  `value` decimal(10, 5) NOT NULL COMMENT '交易金额',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `deleted` int(11) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of token_deal
-- ----------------------------
INSERT INTO `token_deal` VALUES (1555014669104762881, 1, 2, 1554739782943453186, NULL, 0.50000, '2022-08-04 10:16:03', '2022-08-04 10:16:03', 1, 1, 0);

-- ----------------------------
-- Table structure for token_log
-- ----------------------------
DROP TABLE IF EXISTS `token_log`;
CREATE TABLE `token_log`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `token_id` bigint(20) NOT NULL COMMENT 'tokenid',
  `current_change` decimal(10, 2) NOT NULL COMMENT '未冻结金额变更数目',
  `block_change` decimal(10, 2) NOT NULL COMMENT '冻结金额变更数目',
  `current_token` decimal(10, 2) NOT NULL COMMENT '当前未冻结金额',
  `block_token` decimal(10, 2) NOT NULL COMMENT '当前冻结金额',
  `content` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易原因/内容',
  `create_time` datetime(0) NOT NULL COMMENT '交易/创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `deleted` int(11) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '账户变更记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of token_log
-- ----------------------------
INSERT INTO `token_log` VALUES (1554754392463314946, 1554313285411336194, -11071.00, 0.00, 1230.00, 0.00, '管理员更新token账户', '2022-08-03 17:01:48', '2022-08-03 17:01:48', 1, 1, 0);
INSERT INTO `token_log` VALUES (1554757152378220546, 1554313285411336194, 1.00, 0.00, 1231.00, 0.00, '管理员更新token账户', '2022-08-03 17:12:46', '2022-08-03 17:12:46', 1, 1, 0);
INSERT INTO `token_log` VALUES (1554758474389286913, 1, 1.00, 0.00, 10001.00, 0.20, '管理员更新token账户', '2022-08-03 17:18:01', '2022-08-03 17:18:01', 1, 1, 0);
INSERT INTO `token_log` VALUES (1554758718044794881, 1554313285411336194, -20.00, 0.00, 1211.00, 0.00, '购买情报', '2022-08-03 17:18:59', '2022-08-03 17:18:59', 1554312923220602882, 1554312923220602882, 0);
INSERT INTO `token_log` VALUES (1554758718044794882, 1, 20.00, 0.00, 10021.00, 0.20, '售出情报', '2022-08-03 17:18:59', '2022-08-03 17:18:59', 1554312923220602882, 1554312923220602882, 0);
INSERT INTO `token_log` VALUES (1554765079780552705, 1, 1.00, 0.00, 10022.00, 0.20, '管理员更新token账户', '2022-08-03 17:44:16', '2022-08-03 17:44:16', 1, 1, 0);
INSERT INTO `token_log` VALUES (1554767285258924034, 1, 0.00, 0.00, 10022.00, 0.20, '管理员更新token账户', '2022-08-03 17:53:02', '2022-08-03 17:53:02', 1, 1, 0);
INSERT INTO `token_log` VALUES (1554768275110490114, 1554768275022409729, 997.23, 0.00, 997.23, 0.00, '新建账户分配', '2022-08-03 17:56:58', '2022-08-03 17:56:58', 1, 1, 0);
INSERT INTO `token_log` VALUES (1554769593946468353, 1554313285411336194, 10000.00, 100.00, 11211.00, 100.00, '管理员更新token账户', '2022-08-03 18:02:12', '2022-08-03 18:02:12', 1, 1, 0);
INSERT INTO `token_log` VALUES (1558024991990214659, 1554313285411336194, -0.55, 0.55, 11210.45, 100.55, '新增悬赏任务1558024991990214658', '2022-08-12 17:37:59', '2022-08-12 17:37:59', 1554312923220602882, 1554312923220602882, 0);
INSERT INTO `token_log` VALUES (1558025094490615809, 1554313285411336194, 0.55, -0.55, 11211.00, 100.00, '删除协同任务后退还已生成研判任务的冻结Token', '2022-08-12 17:38:24', '2022-08-12 17:38:24', 1554312923220602882, 1554312923220602882, 0);
INSERT INTO `token_log` VALUES (1558039507855601666, 1554313285411336194, -5.00, 5.00, 11216.00, 95.00, '修改悬赏任务Token值', '2022-08-12 18:35:40', '2022-08-12 18:35:40', 1554312923220602882, 1554312923220602882, 0);
INSERT INTO `token_log` VALUES (1558039973188464641, 1554313285411336194, 2.00, -2.00, 11218.00, 93.00, '修改悬赏任务Token值', '2022-08-12 18:37:31', '2022-08-12 18:37:31', 1554312923220602882, 1554312923220602882, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL COMMENT '用户主键',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `sex` int(11) NOT NULL COMMENT '性别',
  `id_number` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态 0:禁用，1:正常',
  `token_id` bigint(20) NULL DEFAULT NULL COMMENT '用户米币',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `deleted` int(11) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `token_id`(`token_id`) USING BTREE,
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`token_id`) REFERENCES `token` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '管理员', '123456', '', 0, '', '广东省禁毒厅', 1, 1, '2022-07-24 22:05:00', '2022-07-24 22:05:00', 1, 1, 0);
INSERT INTO `user` VALUES (1554312923220602882, 'ldy', 'ldy', '123456', '16601248025', 1, '440000076540765', '湖北工业大学', 1, 1554313285411336194, '2022-08-02 11:47:33', '2022-08-02 11:49:00', 1, 1, 0);
INSERT INTO `user` VALUES (1554767522597810178, 'qwer', '张三', '123456', '17777777777', 1, '123467129461222', '禁毒局', 1, 1554768275022409729, '2022-08-03 17:53:58', '2022-08-03 17:56:58', 1, 1, 0);

-- ----------------------------
-- Table structure for user_intelligence
-- ----------------------------
DROP TABLE IF EXISTS `user_intelligence`;
CREATE TABLE `user_intelligence`  (
  `id` bigint(20) NOT NULL COMMENT '用户与情报流转记录id',
  `from_user_id` bigint(20) NOT NULL COMMENT '上传者id',
  `to_user_id` bigint(20) NULL DEFAULT NULL COMMENT '购买者id',
  `intelligence_id` bigint(20) NOT NULL COMMENT '情报id',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `deleted` int(11) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `from_user_id`(`from_user_id`) USING BTREE,
  INDEX `to_user_id`(`to_user_id`) USING BTREE,
  INDEX `intelligence_id`(`intelligence_id`) USING BTREE,
  CONSTRAINT `user_intelligence_ibfk_1` FOREIGN KEY (`from_user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_intelligence_ibfk_2` FOREIGN KEY (`to_user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_intelligence_ibfk_3` FOREIGN KEY (`intelligence_id`) REFERENCES `intelligence` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_intelligence
-- ----------------------------
INSERT INTO `user_intelligence` VALUES (1554317094468554754, 1, 1554312923220602882, 4, '2022-08-02 12:04:08', '2022-08-02 12:04:08', 1554312923220602882, 1554312923220602882, 0);
INSERT INTO `user_intelligence` VALUES (1554758718044794883, 1, 1554312923220602882, 2, '2022-08-03 17:18:59', '2022-08-03 17:18:59', 1554312923220602882, 1554312923220602882, 0);
INSERT INTO `user_intelligence` VALUES (1555783319566712833, 1, NULL, 1555783319503798274, '2022-08-06 13:10:23', '2022-08-06 13:10:23', 1, 1, 0);
INSERT INTO `user_intelligence` VALUES (1555783809486585859, 1, NULL, 1555783809486585858, '2022-08-06 13:12:20', '2022-08-06 13:12:20', 1, 1, 0);

-- ----------------------------
-- Table structure for user_task
-- ----------------------------
DROP TABLE IF EXISTS `user_task`;
CREATE TABLE `user_task`  (
  `id` bigint(20) NOT NULL COMMENT '用户与任务的记录id',
  `task_id` bigint(20) NOT NULL COMMENT '研判任务id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `task_detail_id` bigint(20) NULL DEFAULT NULL COMMENT '任务详情id',
  `submit_time` datetime(0) NULL DEFAULT NULL COMMENT '提交时间',
  `accepted` int(11) NOT NULL COMMENT '是否被接受',
  `contribution` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '贡献度，每一个任务',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `deleted` int(11) NOT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `task_id`(`task_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `user_task_ibfk_3`(`task_detail_id`) USING BTREE,
  CONSTRAINT `user_task_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_task_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_task_ibfk_3` FOREIGN KEY (`task_detail_id`) REFERENCES `task_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_task
-- ----------------------------
INSERT INTO `user_task` VALUES (1557528057298837505, 1556213107393638401, 1554767522597810178, NULL, NULL, 0, '0', '2022-08-11 08:43:21', '2022-08-11 08:43:21', 1554767522597810178, 1554767522597810178, 0);
INSERT INTO `user_task` VALUES (1557528541036306433, 1556213107393638401, 1, NULL, NULL, 0, '0', '2022-08-11 08:45:16', '2022-08-11 08:45:16', 1, 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
