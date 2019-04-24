/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : Scholarship

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 24/04/2019 19:29:55
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
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `twoApproval` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `isSave` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `isLand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of scholarship
-- ----------------------------
BEGIN;
INSERT INTO `scholarship` VALUES (00000000011, '01', 'A19150292', '', '学习优异', '通过', '通过', '通过', '电气与信息学院', '提交', '', '2019');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES (00000000001, '孙云龙', '123', 'A19150292', '电气与信息学院', '微机1504', '1234567890', '312241387@qq.com', '18404552888', '团员', '男', '北京市', '1997-05-16 00:00:00', NULL, NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of studentApply
-- ----------------------------
BEGIN;
INSERT INTO `studentApply` VALUES (00000000012, '孙云龙', '', 'A19150292', '电气与信息学院', '微机1504', '232301199705161815', '312241387@qq.com', '18404552888', '', '男', '黑龙江省绥化市北林区', '1999-05-16', '4.0', '2.8');
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
INSERT INTO `teacher` VALUES (00000000001, '孙红梅', 'A19150000', '123', '电气与信息学院');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
