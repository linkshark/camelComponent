/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 100605
 Source Host           : localhost:3307
 Source Schema         : component

 Target Server Type    : MySQL
 Target Server Version : 100605
 File Encoding         : 65001

 Date: 27/05/2022 16:08:20
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade`
(
    `id`      int(11) NOT NULL AUTO_INCREMENT,
    `garde`   int(11) DEFAULT NULL,
    `name`    varchar(255) DEFAULT NULL,
    `subject` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of grade
-- ----------------------------
BEGIN;
INSERT INTO `grade` (`id`, `garde`, `name`, `subject`)
VALUES (1, 100, '小A', '语文');
INSERT INTO `grade` (`id`, `garde`, `name`, `subject`)
VALUES (2, 80, '小A', '数学');
INSERT INTO `grade` (`id`, `garde`, `name`, `subject`)
VALUES (3, 30, '小B', '美术');
INSERT INTO `grade` (`id`, `garde`, `name`, `subject`)
VALUES (4, 99, '小B', '语文');
COMMIT;

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`
(
    `id`   int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of test
-- ----------------------------
BEGIN;
INSERT INTO `test` (`id`, `name`)
VALUES (1, '小A');
INSERT INTO `test` (`id`, `name`)
VALUES (2, '小B');
INSERT INTO `test` (`id`, `name`)
VALUES (3, '小C');
INSERT INTO `test` (`id`, `name`)
VALUES (4, 'shark');
COMMIT;

SET
FOREIGN_KEY_CHECKS = 1;
