/*
 Navicat Premium Data Transfer

 Source Server         : 118.31.52.244@MySQL8
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 118.31.52.244:3306
 Source Schema         : winter

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 27/02/2022 16:40:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mon_log
-- ----------------------------
DROP TABLE IF EXISTS `mon_log`;
CREATE TABLE `mon_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `method` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `request_method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` tinyint NULL DEFAULT NULL,
  `result` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `exception` varchar(1003) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mon_log
-- ----------------------------
INSERT INTO `mon_log` VALUES (39, '导出用户', 'org.winter.admin.controller.system.UserController.exportExcel()', 'GET', '/system/user/export', 'admin', '119.103.29.112', '湖北省武汉市', 1, 'null', NULL, '2021-03-16 15:39:32');
INSERT INTO `mon_log` VALUES (40, '导出用户', 'org.winter.admin.controller.system.UserController.exportExcel()', 'GET', '/system/user/export', 'admin', '119.103.29.112', '湖北省武汉市', 1, 'null', NULL, '2021-03-16 15:39:59');
INSERT INTO `mon_log` VALUES (41, '更新角色', 'org.winter.admin.controller.system.RoleController.update()', 'PUT', '/system/role', 'admin', '119.103.29.112', '湖北省武汉市', 1, '{\"code\":200,\"message\":\"更新成功！\",\"data\":null}', NULL, '2021-03-17 11:54:21');
INSERT INTO `mon_log` VALUES (42, '更新角色', 'org.winter.admin.controller.system.RoleController.update()', 'PUT', '/system/role', 'admin', '119.103.29.112', '湖北省武汉市', 1, '{\"code\":200,\"message\":\"更新成功！\",\"data\":null}', NULL, '2021-03-17 11:54:46');
INSERT INTO `mon_log` VALUES (43, '强退用户', 'org.winter.admin.controller.monitor.OnlineController.delete()', 'DELETE', '/monitor/online/12ca05de-be1f-4905-a08d-8a8f948383b4', 'admin', '118.31.52.244', '浙江省杭州市', 1, '{\"code\":200,\"message\":\"强退成功！\",\"data\":null}', NULL, '2021-05-04 08:27:49');
INSERT INTO `mon_log` VALUES (47, '更新用户', 'org.winter.admin.controller.system.UserController.update()', 'PUT', '/system/user', 'admin', '118.31.52.244', '浙江省杭州市', 1, '{\"code\":200,\"message\":\"更新成功！\",\"data\":null}', NULL, '2021-05-18 15:35:20');
INSERT INTO `mon_log` VALUES (48, '更新用户', 'org.winter.admin.controller.system.UserController.update()', 'PUT', '/system/user', 'admin', '118.31.52.244', '浙江省杭州市', 1, '{\"code\":200,\"message\":\"更新成功！\",\"data\":null}', NULL, '2021-05-18 15:35:28');
INSERT INTO `mon_log` VALUES (49, '更新用户', 'org.winter.admin.controller.system.UserController.update()', 'PUT', '/system/user', 'admin', '118.31.52.244', '浙江省杭州市', 1, '{\"code\":200,\"message\":\"更新成功！\",\"data\":null}', NULL, '2021-05-19 19:54:15');
INSERT INTO `mon_log` VALUES (50, '导出用户', 'org.winter.admin.controller.system.UserController.exportExcel()', 'GET', '/system/user/export', 'admin', '118.31.52.244', '浙江省杭州市', 1, 'null', NULL, '2021-05-19 19:54:24');
INSERT INTO `mon_log` VALUES (51, '更新角色', 'org.winter.admin.controller.system.RoleController.update()', 'PUT', '/system/role', 'admin', '118.31.52.244', '浙江省杭州市', 1, '{\"code\":200,\"message\":\"更新成功！\",\"data\":null}', NULL, '2021-05-19 19:55:46');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint NULL DEFAULT NULL,
  `sort` int NULL DEFAULT NULL,
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `component` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permission` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, 1, 'M', 'System', '系统管理', '/system', 'Layout', 'el-icon-setting', NULL, '2021-01-19 12:47:58', '2021-01-19 12:48:04');
INSERT INTO `sys_menu` VALUES (2, 0, 2, 'M', 'Monitor', '监控管理', '/monitor', 'Layout', 'el-icon-view', NULL, '2021-01-19 12:48:00', '2021-01-19 12:48:06');
INSERT INTO `sys_menu` VALUES (3, 1, 1, 'M', 'User', '用户管理', 'user', 'system/user/index', 'el-icon-user-solid', NULL, '2021-01-19 12:48:09', '2021-01-19 12:48:11');
INSERT INTO `sys_menu` VALUES (4, 1, 2, 'M', 'Role', '角色管理', 'role', 'system/role/index', 'el-icon-user', NULL, '2021-01-19 12:48:14', '2021-01-19 12:48:42');
INSERT INTO `sys_menu` VALUES (5, 1, 3, 'M', 'Menu', '菜单管理', 'menu', 'system/menu/index', 'el-icon-menu', NULL, '2021-01-19 12:48:17', '2021-01-19 12:48:17');
INSERT INTO `sys_menu` VALUES (6, 2, 1, 'M', 'Online', '在线用户', 'online', 'monitor/online/index', 'el-icon-s-custom', NULL, '2021-01-19 12:48:25', '2021-01-19 12:48:25');
INSERT INTO `sys_menu` VALUES (7, 2, 2, 'M', 'Log', '操作日志', 'log', 'monitor/log/index', 'el-icon-tickets', NULL, '2021-01-19 12:48:28', '2021-01-19 12:48:28');
INSERT INTO `sys_menu` VALUES (8, 2, 3, 'M', 'Server', '服务监控', 'server', 'monitor/server/index', 'el-icon-s-platform', NULL, '2021-01-19 12:48:31', '2021-01-19 12:48:31');
INSERT INTO `sys_menu` VALUES (9, 3, 1, 'P', NULL, '用户查询', NULL, NULL, NULL, 'system:user:query', '2021-01-29 17:23:16', '2021-01-29 17:23:18');
INSERT INTO `sys_menu` VALUES (10, 3, 2, 'P', NULL, '用户新增', NULL, NULL, NULL, 'system:user:add', '2021-01-29 17:24:56', '2021-01-29 17:25:04');
INSERT INTO `sys_menu` VALUES (11, 3, 3, 'P', NULL, '用户编辑', NULL, NULL, NULL, 'system:user:edit', '2021-01-29 17:24:59', '2021-01-29 17:25:07');
INSERT INTO `sys_menu` VALUES (12, 3, 4, 'P', NULL, '用户删除', NULL, NULL, NULL, 'system:user:delete', '2021-01-29 17:25:01', '2021-01-29 17:25:09');
INSERT INTO `sys_menu` VALUES (13, 3, 5, 'P', NULL, '用户导出', NULL, NULL, NULL, 'system:user:export', '2021-01-29 17:26:24', '2021-01-29 17:26:29');
INSERT INTO `sys_menu` VALUES (14, 3, 6, 'P', NULL, '用户导入', NULL, NULL, NULL, 'system:user:import', '2021-01-29 17:26:27', '2021-01-29 17:26:31');
INSERT INTO `sys_menu` VALUES (15, 4, 1, 'P', NULL, '角色查询', NULL, NULL, NULL, 'system:role:query', '2021-01-29 17:26:27', '2021-01-29 17:26:27');
INSERT INTO `sys_menu` VALUES (16, 4, 2, 'P', NULL, '角色新增', NULL, NULL, NULL, 'system:role:add', '2021-01-29 17:26:27', '2021-01-29 17:26:27');
INSERT INTO `sys_menu` VALUES (17, 4, 3, 'P', NULL, '角色编辑', NULL, NULL, NULL, 'system:role:edit', '2021-01-29 17:26:27', '2021-01-29 17:26:27');
INSERT INTO `sys_menu` VALUES (18, 4, 4, 'P', NULL, '角色删除', NULL, NULL, NULL, 'system:role:delete', '2021-01-29 17:26:27', '2021-01-29 17:26:27');
INSERT INTO `sys_menu` VALUES (19, 4, 5, 'P', NULL, '角色导出', NULL, NULL, NULL, 'system:role:export', '2021-01-29 17:26:27', '2021-01-29 17:26:27');
INSERT INTO `sys_menu` VALUES (20, 5, 1, 'P', NULL, '菜单查询', NULL, NULL, NULL, 'system:menu:query', '2021-03-04 13:56:42', '2021-03-04 13:56:42');
INSERT INTO `sys_menu` VALUES (21, 5, 2, 'P', NULL, '菜单新增', NULL, NULL, NULL, 'system:menu:add', '2021-03-04 17:21:28', '2021-03-04 18:48:47');
INSERT INTO `sys_menu` VALUES (22, 5, 3, 'P', NULL, '菜单编辑', NULL, NULL, NULL, 'system:menu:edit', '2021-03-04 19:00:14', '2021-03-04 19:00:14');
INSERT INTO `sys_menu` VALUES (23, 5, 4, 'P', NULL, '菜单删除', NULL, NULL, '', 'system:menu:delete', '2021-03-04 19:00:36', '2021-03-04 19:01:03');
INSERT INTO `sys_menu` VALUES (24, 6, 1, 'P', NULL, '在线查询', NULL, NULL, NULL, 'monitor:online:query', '2021-03-04 19:10:01', '2021-03-04 19:10:42');
INSERT INTO `sys_menu` VALUES (25, 6, 2, 'P', NULL, '强制退出', NULL, NULL, NULL, 'monitor:online:kickout', '2021-03-04 19:13:24', '2021-03-04 19:13:24');
INSERT INTO `sys_menu` VALUES (26, 7, 1, 'P', NULL, '操作查询', NULL, NULL, NULL, 'monitor:log:query', '2021-03-04 19:17:18', '2021-03-04 19:18:21');
INSERT INTO `sys_menu` VALUES (27, 7, 2, 'P', NULL, '操作删除', NULL, NULL, NULL, 'monitor:log:delete', '2021-03-04 19:18:14', '2021-03-04 19:18:14');
INSERT INTO `sys_menu` VALUES (28, 7, 3, 'P', NULL, '操作导出', NULL, NULL, NULL, 'monitor:log:export', '2021-03-04 19:18:48', '2021-03-04 19:18:48');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scope` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'admin', '2021-01-19 12:04:20', '2021-03-17 11:54:21');
INSERT INTO `sys_role` VALUES (2, '用户', 'user', '2021-01-21 12:04:52', '2021-05-19 19:56:22');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint NOT NULL,
  `menu_id` bigint NOT NULL,
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE,
  INDEX `FKf3mud4qoc7ayew8nml4plkevo`(`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (1, 4);
INSERT INTO `sys_role_menu` VALUES (2, 4);
INSERT INTO `sys_role_menu` VALUES (1, 5);
INSERT INTO `sys_role_menu` VALUES (2, 5);
INSERT INTO `sys_role_menu` VALUES (1, 6);
INSERT INTO `sys_role_menu` VALUES (2, 6);
INSERT INTO `sys_role_menu` VALUES (1, 7);
INSERT INTO `sys_role_menu` VALUES (2, 7);
INSERT INTO `sys_role_menu` VALUES (1, 8);
INSERT INTO `sys_role_menu` VALUES (2, 8);
INSERT INTO `sys_role_menu` VALUES (1, 9);
INSERT INTO `sys_role_menu` VALUES (2, 9);
INSERT INTO `sys_role_menu` VALUES (1, 10);
INSERT INTO `sys_role_menu` VALUES (1, 11);
INSERT INTO `sys_role_menu` VALUES (1, 12);
INSERT INTO `sys_role_menu` VALUES (2, 12);
INSERT INTO `sys_role_menu` VALUES (1, 13);
INSERT INTO `sys_role_menu` VALUES (2, 13);
INSERT INTO `sys_role_menu` VALUES (1, 14);
INSERT INTO `sys_role_menu` VALUES (2, 14);
INSERT INTO `sys_role_menu` VALUES (1, 15);
INSERT INTO `sys_role_menu` VALUES (2, 15);
INSERT INTO `sys_role_menu` VALUES (1, 16);
INSERT INTO `sys_role_menu` VALUES (2, 16);
INSERT INTO `sys_role_menu` VALUES (1, 17);
INSERT INTO `sys_role_menu` VALUES (1, 18);
INSERT INTO `sys_role_menu` VALUES (1, 19);
INSERT INTO `sys_role_menu` VALUES (2, 19);
INSERT INTO `sys_role_menu` VALUES (1, 20);
INSERT INTO `sys_role_menu` VALUES (2, 20);
INSERT INTO `sys_role_menu` VALUES (1, 21);
INSERT INTO `sys_role_menu` VALUES (2, 21);
INSERT INTO `sys_role_menu` VALUES (1, 22);
INSERT INTO `sys_role_menu` VALUES (1, 23);
INSERT INTO `sys_role_menu` VALUES (1, 24);
INSERT INTO `sys_role_menu` VALUES (2, 24);
INSERT INTO `sys_role_menu` VALUES (1, 25);
INSERT INTO `sys_role_menu` VALUES (1, 26);
INSERT INTO `sys_role_menu` VALUES (2, 26);
INSERT INTO `sys_role_menu` VALUES (1, 27);
INSERT INTO `sys_role_menu` VALUES (1, 28);
INSERT INTO `sys_role_menu` VALUES (2, 28);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nickname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_enabled` tinyint NULL DEFAULT NULL,
  `is_deleted` tinyint NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'admin', '$2a$10$blF39h18nGKzf9hWlqa9v.H5421jWIiqMTW2KW6ctQ.wtGwe0VUE2', '男', 'http://zhang-hao.oss-cn-beijing.aliyuncs.com/zhang-hao/winter/avatar/87e0cd89-7580-4414-af30-843bbe5f39e8.png', '15588888888', 'admin@apple.com', 1, 0, '2021-01-23 16:05:34', '2021-05-18 15:35:28');
INSERT INTO `sys_user` VALUES (2, 'zhanghao', '张昊', '$2a$10$BMB4I7kbtObh6P4E1mwG3Oz/gDD95tOJqy9HbcEhT03l6sPlWPjBO', '男', 'http://zhang-hao.oss-cn-beijing.aliyuncs.com/zhang-hao/winter/avatar/177cf1d2-e239-4787-964a-c627d4b61da4.jpg', '17720585703', '785896760@qq.com', 1, 0, '2021-01-19 12:07:05', '2021-05-19 19:55:55');
INSERT INTO `sys_user` VALUES (10, '孙岩', '岩岩岩', '$2a$10$gKdGzOnyT/MwJmM5NH13P.umM0MeL5zsFTD1MyH/qmkz/UjyB9sCK', '男', 'https://zhang-hao.oss-cn-beijing.aliyuncs.com/winter/avatar/default_avatar.jpg', '12121212121', 'sy,919@qq.com', 1, 1, '2021-05-17 22:35:33', '2021-05-17 22:35:33');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `FKhh52n8vd4ny9ff4x9fb8v65qx`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (1, 2);
INSERT INTO `sys_user_role` VALUES (2, 2);

SET FOREIGN_KEY_CHECKS = 1;
