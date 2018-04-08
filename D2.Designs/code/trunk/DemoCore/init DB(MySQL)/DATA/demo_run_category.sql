/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.41
Source Server Version : 50635
Source Host           : 192.168.1.41:3306
Source Database       : coaches

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2017-03-31 10:27:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for demo_run_category
-- ----------------------------
DROP TABLE IF EXISTS `demo_run_category`;
CREATE TABLE `demo_run_category` (
  `rowId` varchar(40) NOT NULL COMMENT '行记录唯一标识',
  `xname` varchar(40) DEFAULT '' COMMENT '类目名称',
  `propValCode` varchar(40) DEFAULT '' COMMENT '特征值编码',
  `sort` int(5) DEFAULT '0' COMMENT '排序',
  `state` int(5) DEFAULT '0' COMMENT '状态',
  `createTime` int(11) DEFAULT '0' COMMENT '创建时间',
  `updateTime` int(11) DEFAULT '0' COMMENT '最近操作时间',
  PRIMARY KEY (`rowId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='示例 运行的类目';

-- ----------------------------
-- Records of demo_run_category
-- ----------------------------

-- ----------------------------
-- Table structure for demo_run_list_IP
-- ----------------------------
DROP TABLE IF EXISTS `demo_run_list_IP`;
CREATE TABLE `demo_run_list_IP` (
  `rowId` varchar(40) NOT NULL COMMENT '行记录唯一标识',
  `IP` varchar(20) DEFAULT '' COMMENT 'IP',
  `createTime` int(11) DEFAULT '0' COMMENT '创建时间',
  `updateTime` int(11) DEFAULT '0' COMMENT '最近操作时间',
  PRIMARY KEY (`rowId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='示例 名单';

-- ----------------------------
-- Records of demo_run_list_IP
-- ----------------------------

-- ----------------------------
-- Table structure for demo_run_order_main
-- ----------------------------
DROP TABLE IF EXISTS `demo_run_order_main`;
CREATE TABLE `demo_run_order_main` (
  `rowId` varchar(40) NOT NULL COMMENT '行记录唯一标识',
  `xcode` varchar(20) DEFAULT '' COMMENT '编码',
  `orderNum` varchar(25) NOT NULL DEFAULT '' COMMENT '订单号',
  `areaCode` varchar(40) NOT NULL DEFAULT '0' COMMENT '区域唯一标识',
  `userCode` varchar(40) NOT NULL DEFAULT '' COMMENT '用户唯一标识',
  `serviceCode` varchar(40) NOT NULL DEFAULT '' COMMENT '服务唯一标识',
  `goodsCode` varchar(40) NOT NULL COMMENT '货物唯一标识',
  `dealPrice` double(15,2) DEFAULT NULL COMMENT '交易成交价格',
  `state` int(5) DEFAULT '0' COMMENT '状态',
  `createTime` int(11) DEFAULT '0' COMMENT '创建时间',
  `updateTime` int(11) DEFAULT '0' COMMENT '最近操作时间',
  PRIMARY KEY (`rowId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='示例 运行的订单主体';

-- ----------------------------
-- Records of demo_run_order_main
-- ----------------------------
INSERT INTO `demo_run_order_main` VALUES ('0', 'order', '000000', '0', '0', '0', '0', '9999999999999.99', '0', '0', '0');

-- ----------------------------
-- Table structure for demo_run_rel_sku_GF
-- ----------------------------
DROP TABLE IF EXISTS `demo_run_rel_sku_GF`;
CREATE TABLE `demo_run_rel_sku_GF` (
  `rowId` varchar(40) NOT NULL COMMENT '行记录唯一标识',
  `parentCode` varchar(40) DEFAULT '' COMMENT '父级',
  `sonCode` varchar(40) DEFAULT '' COMMENT '子级',
  `sort` int(5) NOT NULL DEFAULT '0' COMMENT '排序',
  `state` int(5) DEFAULT '0' COMMENT '状态',
  `createTime` int(11) DEFAULT '0' COMMENT '创建时间',
  `updateTime` int(11) DEFAULT '0' COMMENT '最近操作时间',
  PRIMARY KEY (`rowId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='示例 运行的商品与图片关系';

-- ----------------------------
-- Records of demo_run_rel_sku_GF
-- ----------------------------
INSERT INTO `demo_run_rel_sku_GF` VALUES ('0', '0', '0', '0', '0', '0', '0');
INSERT INTO `demo_run_rel_sku_GF` VALUES ('1', '1', '1', '0', '1', '0', '0');

-- ----------------------------
-- Table structure for demo_run_rel_sku_KV
-- ----------------------------
DROP TABLE IF EXISTS `demo_run_rel_sku_KV`;
CREATE TABLE `demo_run_rel_sku_KV` (
  `rowId` varchar(40) NOT NULL COMMENT '行记录唯一标识',
  `parentCode` varchar(40) DEFAULT '' COMMENT '父级',
  `sonCode` varchar(40) DEFAULT '' COMMENT '子级',
  `sort` int(5) NOT NULL DEFAULT '0' COMMENT '排序',
  `state` int(5) DEFAULT '0' COMMENT '状态',
  `createTime` int(11) DEFAULT '0' COMMENT '创建时间',
  `updateTime` int(11) DEFAULT '0' COMMENT '最近操作时间',
  PRIMARY KEY (`rowId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='示例 运行的特征关系';

-- ----------------------------
-- Records of demo_run_rel_sku_KV
-- ----------------------------
INSERT INTO `demo_run_rel_sku_KV` VALUES ('0', '0', '0', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for demo_run_rel_sku_PG
-- ----------------------------
DROP TABLE IF EXISTS `demo_run_rel_sku_PG`;
CREATE TABLE `demo_run_rel_sku_PG` (
  `rowId` varchar(40) NOT NULL COMMENT '行记录唯一标识',
  `parentCode` varchar(40) DEFAULT '' COMMENT '父级',
  `sonCode` varchar(40) DEFAULT '' COMMENT '子级',
  `sort` int(5) NOT NULL DEFAULT '0' COMMENT '排序',
  `state` int(5) DEFAULT '0' COMMENT '状态',
  `createTime` int(11) DEFAULT '0' COMMENT '创建时间',
  `updateTime` int(11) DEFAULT '0' COMMENT '最近操作时间',
  PRIMARY KEY (`rowId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='示例 运行的产品与商品关系';

-- ----------------------------
-- Records of demo_run_rel_sku_PG
-- ----------------------------
INSERT INTO `demo_run_rel_sku_PG` VALUES ('0', '0', '0', '0', '0', '0', '0');
INSERT INTO `demo_run_rel_sku_PG` VALUES ('1', '0', '1', '1', '1', '0', '0');

-- ----------------------------
-- Table structure for demo_run_rel_sku_sale
-- ----------------------------
DROP TABLE IF EXISTS `demo_run_rel_sku_sale`;
CREATE TABLE `demo_run_rel_sku_sale` (
  `rowId` varchar(40) NOT NULL COMMENT '行记录唯一标识',
  `goodsCode` varchar(40) DEFAULT '' COMMENT '商品编码',
  `propKeyCode` varchar(40) DEFAULT '' COMMENT '特征量编码',
  `propValCode` varchar(40) DEFAULT '' COMMENT '特征值编码',
  `state` int(5) DEFAULT '0' COMMENT '状态',
  `createTime` int(11) DEFAULT '0' COMMENT '创建时间',
  `updateTime` int(11) DEFAULT '0' COMMENT '最近操作时间',
  PRIMARY KEY (`rowId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='示例 运行的销售关系';

-- ----------------------------
-- Records of demo_run_rel_sku_sale
-- ----------------------------
INSERT INTO `demo_run_rel_sku_sale` VALUES ('0', '0', '0', '0', '1', '0', '0');
INSERT INTO `demo_run_rel_sku_sale` VALUES ('1', '0', '1', '1', '1', '0', '0');

-- ----------------------------
-- Table structure for demo_run_rel_state_KV
-- ----------------------------
DROP TABLE IF EXISTS `demo_run_rel_state_KV`;
CREATE TABLE `demo_run_rel_state_KV` (
  `rowId` varchar(40) NOT NULL COMMENT '行记录唯一标识',
  `parentCode` varchar(40) DEFAULT '' COMMENT '父级',
  `sonCode` varchar(40) DEFAULT '' COMMENT '子级',
  `sort` int(5) NOT NULL DEFAULT '0' COMMENT '排序',
  `state` int(5) DEFAULT '0' COMMENT '状态',
  `createTime` int(11) DEFAULT '0' COMMENT '创建时间',
  `updateTime` int(11) DEFAULT '0' COMMENT '最近操作时间',
  PRIMARY KEY (`rowId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='示例 运行的状态码关系';

-- ----------------------------
-- Records of demo_run_rel_state_KV
-- ----------------------------
INSERT INTO `demo_run_rel_state_KV` VALUES ('0', '0', '0', '0', '1', '0', '0');
INSERT INTO `demo_run_rel_state_KV` VALUES ('1', '1', '1', '0', '1', '0', '0');

-- ----------------------------
-- Table structure for demo_run_secret_grade
-- ----------------------------
DROP TABLE IF EXISTS `demo_run_secret_grade`;
CREATE TABLE `demo_run_secret_grade` (
  `rowId` varchar(40) NOT NULL COMMENT '行记录唯一标识',
  `level` int(11) DEFAULT '0' COMMENT '等级水平',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  `createTime` int(11) DEFAULT '0' COMMENT '创建时间',
  `updateTime` int(11) DEFAULT '0' COMMENT '最近操作时间',
  PRIMARY KEY (`rowId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='示例 运行的保密等级表\r\nSecurity Level 10 or above required';

-- ----------------------------
-- Records of demo_run_secret_grade
-- ----------------------------
INSERT INTO `demo_run_secret_grade` VALUES ('50297730f4b911e6a3c6080027851f2c', '0', '见习级', '0', '0');
INSERT INTO `demo_run_secret_grade` VALUES ('667d897bf4b911e6a3c6080027851f2c', '1', '路人级', '0', '0');
INSERT INTO `demo_run_secret_grade` VALUES ('66d35bb9f4b911e6a3c6080027851f2c', '2', '路人级', '0', '0');
INSERT INTO `demo_run_secret_grade` VALUES ('672757d2f4b911e6a3c6080027851f2c', '3', '路人级', '0', '0');
INSERT INTO `demo_run_secret_grade` VALUES ('67756cfbf4b911e6a3c6080027851f2c', '4', '公众机密级', '0', '0');
INSERT INTO `demo_run_secret_grade` VALUES ('67c75d90f4b911e6a3c6080027851f2c', '5', '普通机密级', '0', '0');
INSERT INTO `demo_run_secret_grade` VALUES ('68129919f4b911e6a3c6080027851f2c', '6', '绝密级', '0', '0');
INSERT INTO `demo_run_secret_grade` VALUES ('685783c3f4b911e6a3c6080027851f2c', '7', '绝密级', '0', '0');
INSERT INTO `demo_run_secret_grade` VALUES ('68ba5a49f4b911e6a3c6080027851f2c', '8', '绝密黑箱级', '0', '0');
INSERT INTO `demo_run_secret_grade` VALUES ('72b950c6f4b911e6a3c6080027851f2c', '9', '绝密黑箱级', '0', '0');
INSERT INTO `demo_run_secret_grade` VALUES ('f2ac5331f4b811e6a3c6080027851f2c', '10', '绝密黑箱阅后即焚级', '0', '0');

-- ----------------------------
-- Table structure for demo_run_sku_goods
-- ----------------------------
DROP TABLE IF EXISTS `demo_run_sku_goods`;
CREATE TABLE `demo_run_sku_goods` (
  `rowId` varchar(40) NOT NULL COMMENT '行记录唯一标识',
  `xname` varchar(64) DEFAULT '' COMMENT '商品名称',
  `alias` varchar(64) DEFAULT '' COMMENT '商品扩展名称',
  `xcode` varchar(64) DEFAULT '' COMMENT '商品编码',
  `basicPrice` double(15,2) NOT NULL DEFAULT '0.00' COMMENT '基本价格',
  `stock` int(11) NOT NULL DEFAULT '0' COMMENT '库存',
  `description` varchar(500) DEFAULT '' COMMENT '商家描述',
  `state` int(5) DEFAULT '0' COMMENT '状态',
  `createTime` int(11) DEFAULT '0' COMMENT '创建时间',
  `updateTime` int(11) DEFAULT '0' COMMENT '最近操作时间',
  PRIMARY KEY (`rowId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='示例 运行的商品';

-- ----------------------------
-- Records of demo_run_sku_goods
-- ----------------------------
INSERT INTO `demo_run_sku_goods` VALUES ('0', 'good', 'goods', '0000', '9999999999999.99', '0', '2333', '0', '0', '0');
INSERT INTO `demo_run_sku_goods` VALUES ('1', 'g', 's', '0000ssss', '0.00', '0', '2333ssss', '1', '0', '0');

-- ----------------------------
-- Table structure for demo_run_sku_goods_file
-- ----------------------------
DROP TABLE IF EXISTS `demo_run_sku_goods_file`;
CREATE TABLE `demo_run_sku_goods_file` (
  `rowId` varchar(40) NOT NULL COMMENT '行记录唯一标识',
  `link` varchar(500) DEFAULT '' COMMENT '链接',
  `state` int(5) DEFAULT '0' COMMENT '状态',
  `createTime` int(11) DEFAULT '0' COMMENT '创建时间',
  `updateTime` int(11) DEFAULT '0' COMMENT '最近操作时间',
  PRIMARY KEY (`rowId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='示例 运行的商品文件';

-- ----------------------------
-- Records of demo_run_sku_goods_file
-- ----------------------------
INSERT INTO `demo_run_sku_goods_file` VALUES ('0', 'link', '0', '0', '0');
INSERT INTO `demo_run_sku_goods_file` VALUES ('1', 'link1', '1', '0', '0');

-- ----------------------------
-- Table structure for demo_run_sku_product
-- ----------------------------
DROP TABLE IF EXISTS `demo_run_sku_product`;
CREATE TABLE `demo_run_sku_product` (
  `rowId` varchar(40) NOT NULL COMMENT '行记录唯一标识',
  `xname` varchar(64) DEFAULT '' COMMENT '产品名称',
  `xcode` varchar(64) DEFAULT '' COMMENT '产品编码',
  `state` int(5) DEFAULT '0' COMMENT '状态',
  `createTime` int(11) DEFAULT '0' COMMENT '创建时间',
  `updateTime` int(11) DEFAULT '0' COMMENT '最近操作时间',
  PRIMARY KEY (`rowId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='示例 运行的产品';

-- ----------------------------
-- Records of demo_run_sku_product
-- ----------------------------
INSERT INTO `demo_run_sku_product` VALUES ('0', 'prod', '00000', '1', '0', '0');

-- ----------------------------
-- Table structure for demo_run_sku_property_key
-- ----------------------------
DROP TABLE IF EXISTS `demo_run_sku_property_key`;
CREATE TABLE `demo_run_sku_property_key` (
  `rowId` varchar(40) NOT NULL COMMENT '行记录唯一标识',
  `xname` varchar(64) DEFAULT '' COMMENT '产品名称',
  `state` int(5) DEFAULT '0' COMMENT '状态',
  `createTime` int(11) DEFAULT '0' COMMENT '创建时间',
  `updateTime` int(11) DEFAULT '0' COMMENT '最近操作时间',
  PRIMARY KEY (`rowId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='示例 运行的特征量';

-- ----------------------------
-- Records of demo_run_sku_property_key
-- ----------------------------
INSERT INTO `demo_run_sku_property_key` VALUES ('0', '量', '1', '0', '0');
INSERT INTO `demo_run_sku_property_key` VALUES ('1', '量1', '1', '0', '0');

-- ----------------------------
-- Table structure for demo_run_sku_property_value
-- ----------------------------
DROP TABLE IF EXISTS `demo_run_sku_property_value`;
CREATE TABLE `demo_run_sku_property_value` (
  `rowId` varchar(40) NOT NULL COMMENT '行记录唯一标识',
  `xname` varchar(64) DEFAULT '' COMMENT '产品名称',
  `state` int(5) DEFAULT '0' COMMENT '状态',
  `createTime` int(11) DEFAULT '0' COMMENT '创建时间',
  `updateTime` int(11) DEFAULT '0' COMMENT '最近操作时间',
  PRIMARY KEY (`rowId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='示例 运行的特征值';

-- ----------------------------
-- Records of demo_run_sku_property_value
-- ----------------------------
INSERT INTO `demo_run_sku_property_value` VALUES ('0', '值', '1', '0', '0');
INSERT INTO `demo_run_sku_property_value` VALUES ('1', '值1', '1', '0', '0');

-- ----------------------------
-- Table structure for demo_run_state_code
-- ----------------------------
DROP TABLE IF EXISTS `demo_run_state_code`;
CREATE TABLE `demo_run_state_code` (
  `rowId` varchar(40) NOT NULL COMMENT '行记录唯一标识',
  `xcode` varchar(20) DEFAULT '' COMMENT '编码',
  `msg` varchar(20) DEFAULT '' COMMENT '信息',
  `createTime` int(11) DEFAULT '0' COMMENT '创建时间',
  `updateTime` int(11) DEFAULT '0' COMMENT '最近操作时间',
  PRIMARY KEY (`rowId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='示例 运行的状态编码表';

-- ----------------------------
-- Records of demo_run_state_code
-- ----------------------------

-- ----------------------------
-- Table structure for demo_run_voucher
-- ----------------------------
DROP TABLE IF EXISTS `demo_run_voucher`;
CREATE TABLE `demo_run_voucher` (
  `rowId` varchar(40) NOT NULL COMMENT '行记录唯一标识',
  `xcode` varchar(20) DEFAULT '' COMMENT '编码',
  `state` int(2) DEFAULT '0' COMMENT '状态',
  `createTime` int(11) DEFAULT '0' COMMENT '创建时间',
  `updateTime` int(11) DEFAULT '0' COMMENT '最近操作时间',
  PRIMARY KEY (`rowId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='示例 运行的优惠券表';

-- ----------------------------
-- Records of demo_run_voucher
-- ----------------------------
