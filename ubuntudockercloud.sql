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

 Date: 10/12/2022 17:47:52
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
  `serverId` int NULL DEFAULT NULL,
  `type` int NULL DEFAULT NULL COMMENT '0: ubuntu 1: centOS',
  `sshMethod` int NULL DEFAULT NULL COMMENT '0: Password 1: Key',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `serverId`(`serverId` ASC) USING BTREE,
  CONSTRAINT `image_ibfk_1` FOREIGN KEY (`serverId`) REFERENCES `server` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of image
-- ----------------------------
INSERT INTO `image` VALUES (1, 'ubuntu', NULL, 1, NULL);
INSERT INTO `image` VALUES (2, 'centos', NULL, 2, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

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
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ipServer`(`ipServer` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of server
-- ----------------------------
INSERT INTO `server` VALUES (1, '54.175.98.141', 'Running');

-- ----------------------------
-- Table structure for snapshot
-- ----------------------------
DROP TABLE IF EXISTS `snapshot`;
CREATE TABLE `snapshot`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `nameSnapshot` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `userId` int NULL DEFAULT NULL,
  `instanceId` int NULL DEFAULT NULL,
  `serverId` int NULL DEFAULT NULL,
  `createdAt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userId`(`userId` ASC) USING BTREE,
  INDEX `serverId`(`serverId` ASC) USING BTREE,
  INDEX `snapshot_ibfk_2`(`instanceId` ASC) USING BTREE,
  CONSTRAINT `snapshot_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `snapshot_ibfk_2` FOREIGN KEY (`instanceId`) REFERENCES `instance` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `snapshot_ibfk_3` FOREIGN KEY (`serverId`) REFERENCES `server` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

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
  `type` int NOT NULL COMMENT '0: admin\r\n1: normal user',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (8, 'huynguyen', '123123', 1);
INSERT INTO `user` VALUES (9, 'admin', 'admin', 0);

SET FOREIGN_KEY_CHECKS = 1;
