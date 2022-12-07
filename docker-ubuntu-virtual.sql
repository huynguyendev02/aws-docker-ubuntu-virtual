/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 100427
 Source Host           : localhost:3306
 Source Schema         : ubuntudockercloud

 Target Server Type    : MySQL
 Target Server Version : 100427
 File Encoding         : 65001

 Date: 07/12/2022 17:48:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `nameImage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of image
-- ----------------------------
INSERT INTO `image` VALUES (1, 'ubuntu');
INSERT INTO `image` VALUES (2, 'centos');

-- ----------------------------
-- Table structure for instance
-- ----------------------------
DROP TABLE IF EXISTS `instance`;
CREATE TABLE `instance`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `nameInstance` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `cpus` double NULL DEFAULT NULL,
  `memory` double NULL DEFAULT NULL,
  `port` int NULL DEFAULT NULL,
  `networkId` int NULL DEFAULT NULL,
  `userId` int NULL DEFAULT NULL,
  `imageId` int NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `keyId` int NULL DEFAULT NULL,
  `terminate` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `nameInstance`(`nameInstance` ASC) USING BTREE,
  INDEX `networkId`(`networkId` ASC) USING BTREE,
  INDEX `userId`(`userId` ASC) USING BTREE,
  INDEX `imageId`(`imageId` ASC) USING BTREE,
  INDEX `keyId`(`keyId` ASC) USING BTREE,
  CONSTRAINT `instance_ibfk_1` FOREIGN KEY (`networkId`) REFERENCES `network` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `instance_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `instance_ibfk_3` FOREIGN KEY (`imageId`) REFERENCES `image` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `instance_ibfk_4` FOREIGN KEY (`keyId`) REFERENCES `sshkey` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of instance
-- ----------------------------

-- ----------------------------
-- Table structure for network
-- ----------------------------
DROP TABLE IF EXISTS `network`;
CREATE TABLE `network`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `nameNetwork` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `userId` int NULL DEFAULT NULL,
  `serverId` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userId`(`userId` ASC) USING BTREE,
  INDEX `serverId`(`serverId` ASC) USING BTREE,
  CONSTRAINT `network_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `network_ibfk_2` FOREIGN KEY (`serverId`) REFERENCES `server` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of network
-- ----------------------------

-- ----------------------------
-- Table structure for server
-- ----------------------------
DROP TABLE IF EXISTS `server`;
CREATE TABLE `server`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `ipServer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ipServer`(`ipServer` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of server
-- ----------------------------
INSERT INTO `server` VALUES (2, '54.175.98.141');

-- ----------------------------
-- Table structure for snapshot
-- ----------------------------
DROP TABLE IF EXISTS `snapshot`;
CREATE TABLE `snapshot`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `nameSnapshot` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `userId` int NULL DEFAULT NULL,
  `imageId` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userId`(`userId` ASC) USING BTREE,
  INDEX `imageId`(`imageId` ASC) USING BTREE,
  CONSTRAINT `snapshot_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `snapshot_ibfk_2` FOREIGN KEY (`imageId`) REFERENCES `image` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of snapshot
-- ----------------------------

-- ----------------------------
-- Table structure for sshkey
-- ----------------------------
DROP TABLE IF EXISTS `sshkey`;
CREATE TABLE `sshkey`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `nameKey` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `userId` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userId`(`userId` ASC) USING BTREE,
  CONSTRAINT `sshkey_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sshkey
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (8, 'huynguyen', '123123');
INSERT INTO `user` VALUES (9, 'admin', 'admin');

SET FOREIGN_KEY_CHECKS = 1;
