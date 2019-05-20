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

 Date: 20/05/2019 23:38:17
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
  `studentId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `oneGrade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `applyType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `twoGrade` varchar(255) DEFAULT '',
  `threeGrade` varchar(255) DEFAULT '',
  `fourGrade` varchar(255) DEFAULT '',
  `fiveGrade` varchar(255) DEFAULT '',
  `year` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grade
-- ----------------------------
BEGIN;
INSERT INTO `grade` VALUES (00000000002, 'A19150247', '60', '01', '70', '55', '80', '80', '2019');
INSERT INTO `grade` VALUES (00000000003, 'A19150292', '90', '01', '99', '98', '97', '96', '2019');
INSERT INTO `grade` VALUES (00000000004, 'A19150251', '89', '01', '78', '89', '90', '89', '2019');
INSERT INTO `grade` VALUES (00000000005, 'A19150414', '', '01', '', '', '', '', '2019');
INSERT INTO `grade` VALUES (00000000006, 'A19150290', '', '01', '', '', '', '', '2019');
INSERT INTO `grade` VALUES (00000000007, 'A19150289', '', '01', '', '', '', '', '2019');
INSERT INTO `grade` VALUES (00000000008, 'A19150237', '', '01', '', '', '', '', '2019');
INSERT INTO `grade` VALUES (00000000009, 'A19150238', '', '01', '', '', '', '', '2019');
INSERT INTO `grade` VALUES (00000000010, 'A19150239', '', '01', '', '', '', '', '2019');
INSERT INTO `grade` VALUES (00000000011, 'A19150240', '', '01', '', '', '', '', '2019');
INSERT INTO `grade` VALUES (00000000012, 'A19150241', '', '01', '', '', '', '', '2019');
INSERT INTO `grade` VALUES (00000000013, 'A19150242', '', '01', '', '', '', '', '2019');
INSERT INTO `grade` VALUES (00000000014, 'A19150243', '', '02', '', '', '', '', '2019');
INSERT INTO `grade` VALUES (00000000015, 'A19150243', '', '03', '', '', '', '', '2019');
INSERT INTO `grade` VALUES (00000000016, 'A19150244', '', '04', '', '', '', '', '2019');
INSERT INTO `grade` VALUES (00000000017, 'A19150245', '', '05', '', '', '', '', '2019');
INSERT INTO `grade` VALUES (00000000018, 'A19150246', '', '03', '', '', '', '', '2019');
INSERT INTO `grade` VALUES (00000000019, 'A19150246', '', '01', '', '', '', '', '2019');
INSERT INTO `grade` VALUES (00000000020, 'A19150247', '', '01', '', '', '', '', '2019');
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
  `number` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of judges
-- ----------------------------
BEGIN;
INSERT INTO `judges` VALUES (00000000001, 'A19151111', '一号评委', '123', '312241387@qq.com', 'one');
INSERT INTO `judges` VALUES (00000000002, 'A19152222', '二号评委', '123', '312241387@qq.com', 'two');
INSERT INTO `judges` VALUES (00000000003, 'A19153333', '三号评委', '123', '312241387@qq.com', 'three');
INSERT INTO `judges` VALUES (00000000005, 'A19154444', '四号评委', '123', '312241387@qq.com', 'four');
INSERT INTO `judges` VALUES (00000000006, 'A19155555', '五号评委', '123', '312241387@qq.com', 'five');
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
  `path` varchar(255) DEFAULT '',
  `fileName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of scholarship
-- ----------------------------
BEGIN;
INSERT INTO `scholarship` VALUES (00000000051, '01', 'A19150292', '', '成绩优异', '初审通过', '成绩优异', '99.0', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019', '/Users/syl/Public/Scholarship/demo', '20190520153513791.zip');
INSERT INTO `scholarship` VALUES (00000000052, '01', 'A19150251', '', '成绩优异', '初审通过', '成绩优异', '97.0', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019', '/Users/syl/Public/Scholarship/demo', '20190520153513791.zip');
INSERT INTO `scholarship` VALUES (00000000053, '01', 'A19150414', '', '成绩优异', '初审通过', '成绩属实', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019', '/Users/syl/Public/Scholarship/demo', '20190520153513791.zip');
INSERT INTO `scholarship` VALUES (00000000054, '01', 'A19150290', '', '成绩优异', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019', '/Users/syl/Public/Scholarship/demo', '20190520220748020.zip');
INSERT INTO `scholarship` VALUES (00000000055, '01', 'A19150289', '', '成绩优异', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019', '/Users/syl/Public/Scholarship/demo', '20190520222251042.zip');
INSERT INTO `scholarship` VALUES (00000000056, '01', 'A19150237', '', '成绩优异', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019', '/Users/syl/Public/Scholarship/demo', '20190520223118582.zip');
INSERT INTO `scholarship` VALUES (00000000057, '01', 'A19150238', '', '成绩优异', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019', '/Users/syl/Public/Scholarship/demo', '20190520223445183.zip');
INSERT INTO `scholarship` VALUES (00000000058, '01', 'A19150239', '', '成绩优异', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019', '/Users/syl/Public/Scholarship/demo', '20190520223555238.zip');
INSERT INTO `scholarship` VALUES (00000000059, '01', 'A19150240', '', '成绩优异', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019', '/Users/syl/Public/Scholarship/demo', '20190520223704912.zip');
INSERT INTO `scholarship` VALUES (00000000060, '01', 'A19150241', '', '成绩优异', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019', '/Users/syl/Public/Scholarship/demo', '20190520223800586.zip');
INSERT INTO `scholarship` VALUES (00000000061, '01', 'A19150242', '', '成绩优异', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019', '/Users/syl/Public/Scholarship/demo', '20190520223912620.zip');
INSERT INTO `scholarship` VALUES (00000000062, '02', 'A19150243', '', '成绩优异', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019', '/Users/syl/Public/Scholarship/demo', '20190520224514753.zip');
INSERT INTO `scholarship` VALUES (00000000063, '03', 'A19150243', '', '成绩优异', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019', '/Users/syl/Public/Scholarship/demo', '20190520224607194.zip');
INSERT INTO `scholarship` VALUES (00000000064, '04', 'A19150244', '', '成绩优异', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019', '/Users/syl/Public/Scholarship/demo', '20190520224713550.zip');
INSERT INTO `scholarship` VALUES (00000000065, '05', 'A19150245', '', '成绩优异', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019', '/Users/syl/Public/Scholarship/demo', '20190520224811013.zip');
INSERT INTO `scholarship` VALUES (00000000066, '03', 'A19150246', '', '成绩优异', '初审通过', '成绩有效', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019', '/Users/syl/Public/Scholarship/demo', '20190520224905483.zip');
INSERT INTO `scholarship` VALUES (00000000067, '01', 'A19150246', '', '成绩优异', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019', '/Users/syl/Public/Scholarship/demo', '20190520225055758.zip');
INSERT INTO `scholarship` VALUES (00000000068, '01', 'A19150247', '', '成绩优异', '', '', '', '电气与信息学院', '计算机科学与技术', '已提交', '', '2019', '/Users/syl/Public/Scholarship/demo', '20190520225204035.zip');
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
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

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
INSERT INTO `student` VALUES (00000000058, '谢七', '123', 'A19150247', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '团员', '男', '黑龙江省绥化市', '19970516', '3.7', '2.8');
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

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
INSERT INTO `studentApply` VALUES (00000000022, '谢七', '', 'A19150247', '电气与信息学院', '计算机科学与技术', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '共青团员', '男', '黑龙江省绥化市', '19970516', '3.89', '3.67');
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
INSERT INTO `teacher` VALUES (00000000001, '张书夺', 'Z2019', '123', '电气与信息学院');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
