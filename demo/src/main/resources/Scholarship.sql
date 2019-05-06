/*
 Navicat Premium Data Transfer

 Source Server         : 毕业设计
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : Scholarship

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 06/05/2019 20:08:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `adminId` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
BEGIN;
INSERT INTO `admin` VALUES (00000000001, '教务处', 'admin', 'admin');
COMMIT;

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `scholarshipId` varchar(255) DEFAULT NULL,
  `studnetId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `scholarshipType` varchar(255) DEFAULT NULL,
  `judgesId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grade
-- ----------------------------
BEGIN;
INSERT INTO `grade` VALUES (00000000001, '0', 'A19150292', '90', '0', 'A19151111');
COMMIT;

-- ----------------------------
-- Table structure for judges
-- ----------------------------
DROP TABLE IF EXISTS `judges`;
CREATE TABLE `judges` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `judgesId` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of judges
-- ----------------------------
BEGIN;
INSERT INTO `judges` VALUES (00000000001, 'A19151111', '评委老师', '123', '312241387@qq.com');
COMMIT;

-- ----------------------------
-- Table structure for scholarship
-- ----------------------------
DROP TABLE IF EXISTS `scholarship`;
CREATE TABLE `scholarship` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '分为一等，二等，三等，国家励志，国家助学，对应0，1，2，3，4',
  `studentId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `introduce` varchar(18000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `oneApproval` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `twoApproval` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `college` varchar(255) DEFAULT '',
  `isSave` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `isLand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of scholarship
-- ----------------------------
BEGIN;
INSERT INTO `scholarship` VALUES (00000000011, '01', 'A19150292', '', '学习优异', '初审通过', '成绩优异', '复审通过', '电气与信息学院', '计算机科学与技术', '提交', '', '2019');
INSERT INTO `scholarship` VALUES (00000000013, '01', 'A19150251', '', '学习优异', '初审通过', '成绩优异', '复审通过', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019');
INSERT INTO `scholarship` VALUES (00000000014, '01', 'A19150414', '', '学习优异', '初审通过', '成绩优异', '复审未通过', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019');
INSERT INTO `scholarship` VALUES (00000000015, '01', 'A19150290', '', '学习优异', '初审未通过', '绩点造假', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019');
INSERT INTO `scholarship` VALUES (00000000016, '01', 'A19150289', '', '学习优异', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019');
INSERT INTO `scholarship` VALUES (00000000017, '01', 'A19150237', '', '学习优异', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019');
INSERT INTO `scholarship` VALUES (00000000018, '01', 'A19150238', '', '学习优异', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019');
INSERT INTO `scholarship` VALUES (00000000019, '01', 'A19150239', '', '学习优异', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019');
INSERT INTO `scholarship` VALUES (00000000020, '01', 'A19150240', '', '学习优异', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019');
INSERT INTO `scholarship` VALUES (00000000021, '01', 'A19150241', '', '学习优异', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019');
INSERT INTO `scholarship` VALUES (00000000022, '01', 'A19150242', '', '学习优异', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019');
INSERT INTO `scholarship` VALUES (00000000023, '02', 'A19150243', '', '本人获得校级ACM比赛一等奖', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019');
INSERT INTO `scholarship` VALUES (00000000025, '03', 'A19150243', '', '成绩优异', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019');
INSERT INTO `scholarship` VALUES (00000000046, '04', 'A19150244', '', '学习优异', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019');
INSERT INTO `scholarship` VALUES (00000000047, '05', 'A19150245', '', '学习优异', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019');
INSERT INTO `scholarship` VALUES (00000000048, '03', 'A19150246', '', '本人获得校级acm比赛一等奖', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019');
INSERT INTO `scholarship` VALUES (00000000049, '02', 'A19150292', '', 'jjj', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019');
COMMIT;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `studentId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `college` varchar(255) DEFAULT '',
  `className` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `idNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `telephoneNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `politicalOutlook` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `dateOfBirth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `fGPA` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `sGPA` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES (00000000001, '孙云龙', '123', 'A19150292', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '3.7', '2.8');
INSERT INTO `student` VALUES (00000000002, '李浩然', '123', 'A19150251', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '3.7', '2.8');
INSERT INTO `student` VALUES (00000000003, '罗金柱', '456', 'A19150414', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', 'luojinzhu7576@163.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '3.7', '2.8');
INSERT INTO `student` VALUES (00000000005, '殷珂', '123', 'A19150290', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '3.7', '2.8');
INSERT INTO `student` VALUES (00000000006, '汤敏锐', '123', 'A19150289', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '3.7', '2.8');
INSERT INTO `student` VALUES (00000000007, '菏泽', '123', 'A19150237', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '3.7', '2.8');
INSERT INTO `student` VALUES (00000000008, '姚景柯', '123', 'A19150238', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '3.7', '2.8');
INSERT INTO `student` VALUES (00000000009, '康安达', '123', 'A19150239', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '3.7', '2.8');
INSERT INTO `student` VALUES (00000000010, '沈杰', '123', 'A19150240', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '3.7', '2.8');
INSERT INTO `student` VALUES (00000000011, '范圣琦', '123', 'A19150241', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '3.7', '2.8');
INSERT INTO `student` VALUES (00000000012, '杨东翔', '123', 'A19150242', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '3.7', '2.8');
INSERT INTO `student` VALUES (00000000013, '张三', '123', 'A19150243', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '3.7', '2.8');
INSERT INTO `student` VALUES (00000000014, '李四', '123', 'A19150244', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '3.7', '2.8');
INSERT INTO `student` VALUES (00000000015, '王五', '123', 'A19150245', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '3.7', '2.8');
INSERT INTO `student` VALUES (00000000016, '赵六', '123', 'A19150246', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '3.7', '2.8');
COMMIT;

-- ----------------------------
-- Table structure for studentApply
-- ----------------------------
DROP TABLE IF EXISTS `studentApply`;
CREATE TABLE `studentApply` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `studentId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `college` varchar(255) DEFAULT '',
  `className` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `idNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `telephoneNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `politicalOutlook` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `dateOfBirth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `fGPA` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `sGPA` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of studentApply
-- ----------------------------
BEGIN;
INSERT INTO `studentApply` VALUES (00000000001, '孙云龙', '123', 'A19150292', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '3.7', '2.8');
INSERT INTO `studentApply` VALUES (00000000002, '李浩然', '123', 'A19150251', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '3.6', '2.8');
INSERT INTO `studentApply` VALUES (00000000003, '罗金柱', '123', 'A19150414', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '3.5', '2.8');
INSERT INTO `studentApply` VALUES (00000000005, '殷珂', '123', 'A19150290', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '3.5', '2.7');
INSERT INTO `studentApply` VALUES (00000000006, '汤敏锐', '123', 'A19150289', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '3.3', '2.7');
INSERT INTO `studentApply` VALUES (00000000007, '菏泽', '123', 'A19150237', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '3.3', '2.8');
INSERT INTO `studentApply` VALUES (00000000008, '姚景柯', '123', 'A19150238', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '3.1', '2.8');
INSERT INTO `studentApply` VALUES (00000000009, '康安达', '123', 'A19150239', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '3.0', '2.8');
INSERT INTO `studentApply` VALUES (00000000010, '沈杰', '123', 'A19150240', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '2.9', '2.8');
INSERT INTO `studentApply` VALUES (00000000011, '范圣琦', '123', 'A19150241', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '2.8', '2.8');
INSERT INTO `studentApply` VALUES (00000000012, '杨东翔', '123', 'A19150242', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '2.7', '2.8');
INSERT INTO `studentApply` VALUES (00000000013, '张三', '123', 'A19150243', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '2.6', '2.8');
INSERT INTO `studentApply` VALUES (00000000014, '李四', '123', 'A19150244', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '2.5', '2.8');
INSERT INTO `studentApply` VALUES (00000000015, '王五', '123', 'A19150245', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '2.4', '2.8');
INSERT INTO `studentApply` VALUES (00000000020, '赵六', '', 'A19150246', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '共青团员', '男', '黑龙江省绥化市', '19970516', '4.00', '3.67');
COMMIT;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `teacherId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
BEGIN;
INSERT INTO `teacher` VALUES (00000000001, '张书夺', 'A19150000', '123', '电气与信息学院');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
