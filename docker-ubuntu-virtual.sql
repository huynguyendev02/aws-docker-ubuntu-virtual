/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 100425
 Source Host           : localhost:3306
 Source Schema         : docker-ubuntu-virtual

 Target Server Type    : MySQL
 Target Server Version : 100425
 File Encoding         : 65001

 Date: 03/12/2022 12:49:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for container
-- ----------------------------
DROP TABLE IF EXISTS `container`;
CREATE TABLE `container`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `nameContainer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `imageId` int NULL DEFAULT NULL,
  `userId` int NULL DEFAULT NULL,
  `cpus` float NOT NULL,
  `ram` float NOT NULL,
  `ipSSH` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `serverId` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `imageId`(`imageId` ASC) USING BTREE,
  INDEX `userId`(`userId` ASC) USING BTREE,
  INDEX `serverId`(`serverId` ASC) USING BTREE,
  CONSTRAINT `container_ibfk_1` FOREIGN KEY (`imageId`) REFERENCES `image` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `container_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `container_ibfk_3` FOREIGN KEY (`serverId`) REFERENCES `server` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `nameImage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for server
-- ----------------------------
DROP TABLE IF EXISTS `server`;
CREATE TABLE `server`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `ipServer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for snapshot
-- ----------------------------
DROP TABLE IF EXISTS `snapshot`;
CREATE TABLE `snapshot`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `nameSnapshot` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `imageId` int NULL DEFAULT NULL,
  `serverId` int NULL DEFAULT NULL,
  `userId` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `imageId`(`imageId` ASC) USING BTREE,
  INDEX `serverId`(`serverId` ASC) USING BTREE,
  INDEX `userId`(`userId` ASC) USING BTREE,
  CONSTRAINT `snapshot_ibfk_1` FOREIGN KEY (`imageId`) REFERENCES `image` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `snapshot_ibfk_2` FOREIGN KEY (`serverId`) REFERENCES `server` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `snapshot_ibfk_3` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
