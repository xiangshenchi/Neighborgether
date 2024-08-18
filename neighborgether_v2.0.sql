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

 Date: 14/08/2024 15:31:32
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
  `PublishDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Attachment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `UserID` int NULL DEFAULT NULL,
  PRIMARY KEY (`AnnouncementID`) USING BTREE,
  INDEX `UserID`(`UserID` ASC) USING BTREE,
  CONSTRAINT `announcements_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of announcements
-- ----------------------------
INSERT INTO `announcements` VALUES (1, '停水通知', '因设备检修，明日8:00至18:00停水，请各位住户提前做好储水准备。', '2024-08-14 15:30:35', NULL, 3);
INSERT INTO `announcements` VALUES (2, '物业费缴纳提醒', '请各位住户及时缴纳本季度物业费。', '2024-08-14 15:30:35', NULL, 3);

-- ----------------------------
-- Table structure for demand
-- ----------------------------
DROP TABLE IF EXISTS `demand`;
CREATE TABLE `demand`  (
  `DemandID` int NOT NULL AUTO_INCREMENT,
  `DemandType` enum('投诉','建议') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `DemandMsg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `DemandDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UserID` int NULL DEFAULT NULL,
  `StaffID` int NULL DEFAULT NULL,
  `Status` enum('已完成','处理中','待处理') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`DemandID`) USING BTREE,
  INDEX `UserID`(`UserID` ASC) USING BTREE,
  INDEX `StaffID`(`StaffID` ASC) USING BTREE,
  CONSTRAINT `demand_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `demand_ibfk_2` FOREIGN KEY (`StaffID`) REFERENCES `users` (`UserID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demand
-- ----------------------------
INSERT INTO `demand` VALUES (1, '投诉', '公共区域清洁不及时', '2024-08-14 15:30:36', 1, NULL, '处理中');
INSERT INTO `demand` VALUES (2, '建议', '希望增加小区内的绿化面积', '2024-08-14 15:30:36', 1, NULL, '待处理');

-- ----------------------------
-- Table structure for livingpayment
-- ----------------------------
DROP TABLE IF EXISTS `livingpayment`;
CREATE TABLE `livingpayment`  (
  `PaymentID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NULL DEFAULT NULL,
  `PaymentType` enum('水费','电费','物业费','其他') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Amount` decimal(10, 2) NOT NULL,
  `PaymentDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Status` enum('已缴','未缴') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`PaymentID`) USING BTREE,
  INDEX `UserID`(`UserID` ASC) USING BTREE,
  CONSTRAINT `livingpayment_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of livingpayment
-- ----------------------------
INSERT INTO `livingpayment` VALUES (1, 1, '水费', 50.75, '2024-08-14 15:30:35', '已缴');
INSERT INTO `livingpayment` VALUES (2, 1, '电费', 120.00, '2024-08-14 15:30:35', '未缴');
INSERT INTO `livingpayment` VALUES (3, 2, '物业费', 300.00, '2024-08-14 15:30:35', '已缴');

-- ----------------------------
-- Table structure for propertyinfo
-- ----------------------------
DROP TABLE IF EXISTS `propertyinfo`;
CREATE TABLE `propertyinfo`  (
  `PropertyID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NULL DEFAULT NULL,
  `RoomNumber` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `BuildingNumber` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `UnitNumber` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Area` float NOT NULL,
  PRIMARY KEY (`PropertyID`) USING BTREE,
  INDEX `UserID`(`UserID` ASC) USING BTREE,
  CONSTRAINT `propertyinfo_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of propertyinfo
-- ----------------------------
INSERT INTO `propertyinfo` VALUES (1, 1, '101', 'A', '1', 120.5);
INSERT INTO `propertyinfo` VALUES (2, 2, '202', 'B', '2', 98.3);

-- ----------------------------
-- Table structure for repairmanagement
-- ----------------------------
DROP TABLE IF EXISTS `repairmanagement`;
CREATE TABLE `repairmanagement`  (
  `RepairID` int NOT NULL AUTO_INCREMENT,
  `RepairContent` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `RepairDate` date NOT NULL,
  `RepairStatus` enum('已完成','处理中','待处理') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`RepairID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repairmanagement
-- ----------------------------
INSERT INTO `repairmanagement` VALUES (1, '水管破裂', '2024-08-10', '处理中');
INSERT INTO `repairmanagement` VALUES (2, '电梯故障', '2024-08-12', '待处理');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `UserID` int NOT NULL AUTO_INCREMENT,
  `UserName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `PhoneNumber` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Role` enum('Owner','Staff','Admin') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CreatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`UserID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'John Doe', 'password123', '1234567890', 'john.doe@example.com', '123 Main St', 'Owner', '2024-08-14 15:28:28');
INSERT INTO `users` VALUES (2, 'Jane Smith', 'password456', '0987654321', 'jane.smith@example.com', '456 Elm St', 'Staff', '2024-08-14 15:28:28');
INSERT INTO `users` VALUES (3, 'Admin User', 'adminpass', '1122334455', 'admin@example.com', 'Admin Office', 'Admin', '2024-08-14 15:28:28');

-- ----------------------------
-- Table structure for vehicles
-- ----------------------------
DROP TABLE IF EXISTS `vehicles`;
CREATE TABLE `vehicles`  (
  `VehicleID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NULL DEFAULT NULL,
  `LicensePlate` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `VehicleType` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `RegistrationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`VehicleID`) USING BTREE,
  INDEX `UserID`(`UserID` ASC) USING BTREE,
  CONSTRAINT `vehicles_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vehicles
-- ----------------------------
INSERT INTO `vehicles` VALUES (1, 1, 'ABC123', 'Sedan', '2024-08-14 15:30:35');
INSERT INTO `vehicles` VALUES (2, 2, 'XYZ789', 'SUV', '2024-08-14 15:30:35');

-- ----------------------------
-- Table structure for visitors
-- ----------------------------
DROP TABLE IF EXISTS `visitors`;
CREATE TABLE `visitors`  (
  `VisitID` int NOT NULL AUTO_INCREMENT,
  `VisitName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `VisitPhone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `VisitDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `VisitReason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`VisitID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of visitors
-- ----------------------------
INSERT INTO `visitors` VALUES (1, 'Alice Brown', '1230987654', '2024-08-14 15:30:35', '探望家人');
INSERT INTO `visitors` VALUES (2, 'Bob White', '0981234567', '2024-08-14 15:30:35', '送快递');

SET FOREIGN_KEY_CHECKS = 1;
