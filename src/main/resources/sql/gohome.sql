/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : gohome

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-10-14 20:55:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `origin` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '始发地',
  `destination` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '目的地',
  `departure_date` datetime NOT NULL COMMENT '出发日期',
  `train_number` varchar(0) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '车次',
  `seat` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '坐席',
  `passenger` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '乘车人',
  `passenger_id_card` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '乘车人身份证',
  `price` int(11) NOT NULL DEFAULT '0' COMMENT '抢票价格，单位分',
  `profit` int(11) NOT NULL DEFAULT '0' COMMENT '利润，单位分',
  `complete_date` datetime DEFAULT NULL COMMENT '订单完成时间，包括订单交易成功，订单关闭',
  `portal_user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '接单人员',
  `status` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '抢票状态，0已删除，1抢票中，2交易成功，3交易关闭',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for owner
-- ----------------------------
DROP TABLE IF EXISTS `owner`;
CREATE TABLE `owner` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `wx_account` varchar(255) NOT NULL DEFAULT '' COMMENT '微信账号',
  `wx_nickname` varchar(255) NOT NULL DEFAULT '' COMMENT '微信昵称',
  `gender` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '性别',
  `phone` varchar(30) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '手机号',
  `status` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '业主状态，0已删除，1未删除',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for passenger
-- ----------------------------
DROP TABLE IF EXISTS `passenger`;
CREATE TABLE `passenger` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `id_card` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '旅客身份证号',
  `name` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '旅客名称',
  `status` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '旅客状态，0未删除，1已删除',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for portal_user
-- ----------------------------
DROP TABLE IF EXISTS `portal_user`;
CREATE TABLE `portal_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `gender` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '性别',
  `account` varchar(255) NOT NULL DEFAULT '' COMMENT '账号',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `phone` varchar(30) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '手机号',
  `sid` varchar(255) NOT NULL DEFAULT '' COMMENT '用户验证sid',
  `sid_expire` datetime DEFAULT NULL COMMENT 'sid登录过期时间',
  `status` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '业主状态，0已删除，1未删除',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for rel_owner_order
-- ----------------------------
DROP TABLE IF EXISTS `rel_owner_order`;
CREATE TABLE `rel_owner_order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) unsigned NOT NULL COMMENT '业主表id',
  `order_id` int(11) unsigned NOT NULL COMMENT '订单表id',
  `status` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '状态，0已删除，1未删除',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for rel_robbing_order
-- ----------------------------
DROP TABLE IF EXISTS `rel_robbing_order`;
CREATE TABLE `rel_robbing_order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `robbing_id` int(11) unsigned NOT NULL COMMENT '抢票人id',
  `order_id` int(11) unsigned NOT NULL COMMENT '订单表id',
  `status` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '状态，0已删除，1未删除',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for robbing_ticket_user
-- ----------------------------
DROP TABLE IF EXISTS `robbing_ticket_user`;
CREATE TABLE `robbing_ticket_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '抢票人名称',
  `gender` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '性别',
  `phone` varchar(30) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '手机号',
  `status` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '抢票人状态，0已删除，1未删除',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;
