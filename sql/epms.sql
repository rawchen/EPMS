/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50646
 Source Host           : localhost:3306
 Source Schema         : epms

 Target Server Type    : MySQL
 Target Server Version : 50646
 File Encoding         : 65001

 Date: 31/05/2020 13:29:44
*/

-- ----------------------------
-- Table structure for tb_account
-- ----------------------------
DROP TABLE IF EXISTS `tb_account`;
CREATE TABLE `tb_account`  (
  `TIMECARD_ID` int(11) NOT NULL,
  `NAME` varchar(10) NOT NULL,
  `TYPE` varchar(10) NOT NULL,
  `MONEY` varchar(10) NOT NULL,
  PRIMARY KEY (`TIMECARD_ID`) USING BTREE
) ENGINE = InnoDB;

-- ----------------------------
-- Records of tb_account
-- ----------------------------
INSERT INTO `tb_account` VALUES (1, '加班', '发放', '50');
INSERT INTO `tb_account` VALUES (2, '迟到', '扣除', '10');
INSERT INTO `tb_account` VALUES (3, '早退', '扣除', '10');
INSERT INTO `tb_account` VALUES (4, '值班', '发放', '15');

-- ----------------------------
-- Table structure for tb_dept
-- ----------------------------
DROP TABLE IF EXISTS `tb_dept`;
CREATE TABLE `tb_dept`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `NAME` varchar(20) NULL DEFAULT NULL COMMENT '部门名称',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25;

-- ----------------------------
-- Records of tb_dept
-- ----------------------------
INSERT INTO `tb_dept` VALUES (0, '——');
INSERT INTO `tb_dept` VALUES (1, '财务部');
INSERT INTO `tb_dept` VALUES (2, '人事部');
INSERT INTO `tb_dept` VALUES (3, '办公室');
INSERT INTO `tb_dept` VALUES (4, '销售部');
INSERT INTO `tb_dept` VALUES (5, '测试部');
INSERT INTO `tb_dept` VALUES (21, 'fff');
INSERT INTO `tb_dept` VALUES (22, 'ddd');
INSERT INTO `tb_dept` VALUES (24, '77');

-- ----------------------------
-- Table structure for tb_duty
-- ----------------------------
DROP TABLE IF EXISTS `tb_duty`;
CREATE TABLE `tb_duty`  (
  `ID` int(11) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB;

-- ----------------------------
-- Records of tb_duty
-- ----------------------------
INSERT INTO `tb_duty` VALUES (0, '——');
INSERT INTO `tb_duty` VALUES (1, '经理');
INSERT INTO `tb_duty` VALUES (2, '项目经理');
INSERT INTO `tb_duty` VALUES (3, '部门经理');
INSERT INTO `tb_duty` VALUES (4, '员工');

-- ----------------------------
-- Table structure for tb_native_place
-- ----------------------------
DROP TABLE IF EXISTS `tb_native_place`;
CREATE TABLE `tb_native_place`  (
  `ID` int(11) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB;

-- ----------------------------
-- Records of tb_native_place
-- ----------------------------
INSERT INTO `tb_native_place` VALUES (0, '——');
INSERT INTO `tb_native_place` VALUES (1, '福建省');
INSERT INTO `tb_native_place` VALUES (2, '浙江省');
INSERT INTO `tb_native_place` VALUES (3, '四川省');
INSERT INTO `tb_native_place` VALUES (4, '北京市');
INSERT INTO `tb_native_place` VALUES (5, '江苏省');

-- ----------------------------
-- Table structure for tb_person
-- ----------------------------
DROP TABLE IF EXISTS `tb_person`;
CREATE TABLE `tb_person`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RECORD_NUMBER` varchar(20) NOT NULL,
  `DEPT_ID` int(11) NULL DEFAULT 0,
  `DUTY_ID` int(3) NULL DEFAULT 0,
  `NAME` varchar(10) NULL DEFAULT NULL,
  `SEX` char(2) NULL DEFAULT NULL,
  `BIRTHDAY` datetime NULL DEFAULT NULL,
  `PHOTO` char(27) NULL DEFAULT NULL,
  `ID_CARD` varchar(20) NULL DEFAULT NULL,
  `MARRIAGED` char(4) NULL DEFAULT NULL,
  `NATIVE_PLACE_ID` int(11) NULL DEFAULT 0,
  `PARTY_MEMBER` char(2) NULL DEFAULT NULL,
  `SCHOOL_AGE` varchar(10) NULL DEFAULT NULL,
  `SPECIALTY` varchar(40) NULL DEFAULT NULL,
  `FOREIGN_LANGUAGE` varchar(10) NULL DEFAULT NULL,
  `GRADE` varchar(10) NULL DEFAULT NULL,
  `PASSWORD` varchar(30) NULL DEFAULT 'pkA8u6agfASAta9CR2J2mw==',
  `STATE` varchar(6) NULL DEFAULT NULL,
  `ROLE_ID` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17;

-- ----------------------------
-- Records of tb_person
-- ----------------------------
INSERT INTO `tb_person` VALUES (1, 'T00001', 1, 1, '陈晓', '男', '1999-07-09 00:00:00', 'T00001.jpg', '652837465760784534', '未婚', 1, '否', '本科', '计算机', '英语', '四级', 'pkA8u6agfASAta9CR2J2mw==', '正常', 1);
INSERT INTO `tb_person` VALUES (4, 'T00002', 1, 2, '张三', '男', '1988-08-08 00:00:00', 'T00002.jpg', '26526', '已婚', 1, '否', '本科', 'fdsfs', 'fdsf', 'fdsf', 'pkA8u6agfASAta9CR2J2mw==', '', 2);
INSERT INTO `tb_person` VALUES (5, 'T00003', 1, 2, 'ccccc', '女', '1998-10-22 00:00:00', 'T00003.jpg', '1384564555555555', '未婚', 2, '是', '本科', '英语', '', '', 'pkA8u6agfASAta9CR2J2mw==', '', 2);
INSERT INTO `tb_person` VALUES (6, 'T00004', 1, 3, 'ddddd', '男', '1999-05-25 00:00:00', NULL, '123555555555', '未婚', 1, '是', '本科', '计算机', '英语', '4级', 'pkA8u6agfASAta9CR2J2mw==', '', 2);
INSERT INTO `tb_person` VALUES (16, 'T11111', 0, 0, 'ccc', NULL, '1111-11-11 00:00:00', NULL, '', NULL, 0, NULL, '', '', '', '', 'pkA8u6agfASAta9CR2J2mw==', '', 0);

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
  `ID` int(4) NOT NULL,
  `NAME` varchar(20) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES (0, '——');
INSERT INTO `tb_role` VALUES (1, '超级管理员');
INSERT INTO `tb_role` VALUES (2, '普通员工');

-- ----------------------------
-- Table structure for tb_timecard
-- ----------------------------
DROP TABLE IF EXISTS `tb_timecard`;
CREATE TABLE `tb_timecard`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TIMECARD_ID` int(11) NOT NULL,
  `PERSON_ID` varchar(20) NOT NULL,
  `TIMECARD_DATE` datetime NOT NULL,
  `RATIFIER_ID` varchar(20) NOT NULL,
  `EXPLAINS` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7;

-- ----------------------------
-- Records of tb_timecard
-- ----------------------------
INSERT INTO `tb_timecard` VALUES (1, 1, 'T00002', '2020-05-29 00:00:00', 'T00001', '张三2020.05.29加班说明');
INSERT INTO `tb_timecard` VALUES (2, 1, 'T00002', '2020-05-30 18:53:33', 'T00001', 'fffff');
INSERT INTO `tb_timecard` VALUES (3, 1, 'T00001', '2020-05-30 23:19:20', 'T00001', 'www');
INSERT INTO `tb_timecard` VALUES (4, 1, 'T00001', '2020-05-30 23:19:20', 'T00001', '');
INSERT INTO `tb_timecard` VALUES (5, 1, 'T00001', '2020-05-30 23:22:03', 'T00001', '123');
INSERT INTO `tb_timecard` VALUES (6, 2, 'T00001', '2020-05-30 23:22:03', 'T00001', '123');
