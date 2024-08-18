/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : neighborgether

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 14/08/2024 14:17:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for announcements
-- ----------------------------
DROP TABLE IF EXISTS `announcements`;
CREATE TABLE `announcements`  (
  `AnnouncementID` int NOT NULL AUTO_INCREMENT,
  `Title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `PublishDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Attachment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`AnnouncementID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of announcements
-- ----------------------------

-- ----------------------------
-- Table structure for complaintsandsuggestions
-- ----------------------------
DROP TABLE IF EXISTS `complaintsandsuggestions`;
CREATE TABLE `complaintsandsuggestions`  (
  `ComplaintID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NULL DEFAULT NULL,
  `Content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `SubmissionDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Status` enum('Pending','InProgress','Resolved') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `StaffID` int NULL DEFAULT NULL,
  PRIMARY KEY (`ComplaintID`) USING BTREE,
  INDEX `UserID`(`UserID` ASC) USING BTREE,
  INDEX `StaffID`(`StaffID` ASC) USING BTREE,
  CONSTRAINT `complaintsandsuggestions_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `complaintsandsuggestions_ibfk_2` FOREIGN KEY (`StaffID`) REFERENCES `users` (`UserID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of complaintsandsuggestions
-- ----------------------------
INSERT INTO `complaintsandsuggestions` VALUES (1, 1, 'Noise complaint', '2024-08-14 14:15:27', 'Pending', NULL);

-- ----------------------------
-- Table structure for payments
-- ----------------------------
DROP TABLE IF EXISTS `payments`;
CREATE TABLE `payments`  (
  `PaymentID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NULL DEFAULT NULL,
  `PaymentType` enum('Water','Electricity','Property') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Amount` decimal(10, 2) NOT NULL,
  `Status` enum('Pending','Completed') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `PaymentDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`PaymentID`) USING BTREE,
  INDEX `UserID`(`UserID` ASC) USING BTREE,
  CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payments
-- ----------------------------
INSERT INTO `payments` VALUES (1, 1, 'Water', 100.50, 'Completed', '2024-08-14 14:15:27');

-- ----------------------------
-- Table structure for repairmanagement
-- ----------------------------
DROP TABLE IF EXISTS `repairmanagement`;
CREATE TABLE `repairmanagement`  (
  `ManagementID` int NOT NULL AUTO_INCREMENT,
  `RepairID` int NULL DEFAULT NULL,
  `StaffID` int NULL DEFAULT NULL,
  `Progress` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `UpdateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ManagementID`) USING BTREE,
  INDEX `RepairID`(`RepairID` ASC) USING BTREE,
  INDEX `StaffID`(`StaffID` ASC) USING BTREE,
  CONSTRAINT `repairmanagement_ibfk_1` FOREIGN KEY (`RepairID`) REFERENCES `repairs` (`RepairID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `repairmanagement_ibfk_2` FOREIGN KEY (`StaffID`) REFERENCES `users` (`UserID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repairmanagement
-- ----------------------------

-- ----------------------------
-- Table structure for repairs
-- ----------------------------
DROP TABLE IF EXISTS `repairs`;
CREATE TABLE `repairs`  (
  `RepairID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NULL DEFAULT NULL,
  `FacilityType` enum('Plumbing','Electrical','Other') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `SubmissionDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Status` enum('Pending','InProgress','Fixed') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `StaffID` int NULL DEFAULT NULL,
  PRIMARY KEY (`RepairID`) USING BTREE,
  INDEX `UserID`(`UserID` ASC) USING BTREE,
  INDEX `StaffID`(`StaffID` ASC) USING BTREE,
  CONSTRAINT `repairs_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `repairs_ibfk_2` FOREIGN KEY (`StaffID`) REFERENCES `users` (`UserID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repairs
-- ----------------------------
INSERT INTO `repairs` VALUES (1, 1, 'Plumbing', 'Leaking pipe', NULL, '2024-08-14 14:15:27', 'Pending', NULL);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `UserID` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Role` enum('Owner','Staff') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ContactInfo` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `CreatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`UserID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'owner1', 'encrypted_password', 'Owner', 'owner1@example.com', '2024-08-14 14:15:27');
INSERT INTO `users` VALUES (2, 'staff1', 'encrypted_password', 'Staff', 'staff1@example.com', '2024-08-14 14:15:27');

-- ----------------------------
-- Table structure for vehicles
-- ----------------------------
DROP TABLE IF EXISTS `vehicles`;
CREATE TABLE `vehicles`  (
  `VehicleID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NULL DEFAULT NULL,
  `LicensePlate` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `VehicleType` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `RegistrationDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`VehicleID`) USING BTREE,
  INDEX `UserID`(`UserID` ASC) USING BTREE,
  CONSTRAINT `vehicles_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vehicles
-- ----------------------------
INSERT INTO `vehicles` VALUES (1, 1, 'ABC123', 'Sedan', '2024-08-14 14:15:27');

SET FOREIGN_KEY_CHECKS = 1;
