# 数据库说明 
版本mysql5.7

```sql
/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.27 : Database - heima_mm2
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`heima_mm2` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `heima_mm2`;

/*Table structure for table `ss_dept` */

DROP TABLE IF EXISTS `ss_dept`;

CREATE TABLE `ss_dept` (
  `dept_id` varchar(40) NOT NULL,
  `dept_name` varchar(50) DEFAULT NULL,
  `parent_id` varchar(40) DEFAULT NULL,
  `state` decimal(6,0) DEFAULT NULL COMMENT '1代表启用，0代表停用，默认为1',
  PRIMARY KEY (`dept_id`) USING BTREE,
  KEY `SYS_C005596` (`parent_id`) USING BTREE,
  CONSTRAINT `SYS_C005596` FOREIGN KEY (`parent_id`) REFERENCES `ss_dept` (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `ss_dept` */

insert  into `ss_dept`(`dept_id`,`dept_name`,`parent_id`,`state`) values ('100','五邑大学','100102103','1'),('100101','智能制造学部','100','1'),('100101101','电子信息工程','100101','1'),('100101102','通信工程','100101','1'),('100102','纺织材料与工程学院','100','1'),('100102101','现代纺织技术与应用','100102','1'),('100102102','针织品设计与服装','100102','1'),('100102103','非织造材料与工程','100102','1');

/*Table structure for table `ss_module` */

DROP TABLE IF EXISTS `ss_module`;

CREATE TABLE `ss_module` (
  `module_id` varchar(40) NOT NULL,
  `parent_id` varchar(40) DEFAULT NULL,
  `parent_name` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `is_leaf` decimal(11,0) DEFAULT NULL,
  `ico` varchar(20) DEFAULT NULL,
  `cpermission` varchar(20) DEFAULT NULL,
  `curl` varchar(200) DEFAULT NULL,
  `ctype` decimal(11,0) DEFAULT NULL COMMENT '0 主菜单/1 左侧菜单/2按钮/3 链接/4 状态',
  `state` decimal(11,0) DEFAULT NULL COMMENT '1启用0停用',
  `belong` varchar(100) DEFAULT NULL COMMENT '按钮时，可以标识其归属，\\n            查询某页面按钮时就用此属性查询',
  `remark` varchar(100) DEFAULT NULL,
  `order_no` decimal(11,0) DEFAULT NULL,
  PRIMARY KEY (`module_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `ss_module` */

insert  into `ss_module`(`module_id`,`parent_id`,`parent_name`,`name`,`is_leaf`,`ico`,`cpermission`,`curl`,`ctype`,`state`,`belong`,`remark`,`order_no`) values ('1',NULL,NULL,'平台系统管理','0',NULL,'平台系统管理','system','0','1','1','system','1'),('101','1','平台系统管理','部门管理','1',NULL,'部门管理','system/dept?operation=list','1','1','1','system','12'),('102','1','平台系统管理','用户管理','1',NULL,'用户管理','system/user?operation=list','1','1','1','system','13'),('103','1','平台系统管理','角色管理','1',NULL,'角色管理','system/role?operation=list','1','1','1','system','14'),('104','1','平台系统管理','模块管理','1',NULL,'模块管理','system/module?operation=list','1','1','1','system','15'),('105','1','平台系统管理','系统日志管理','1',NULL,'系统日志管理','system/sysLog?operation=list','1','1','1','system','16'),('2',NULL,NULL,'题库管理','0',NULL,'题库管理','store','0','1','1','store','2'),('201','2','题库管理','题目学科管理','1',NULL,'题目学科管理','store/course?operation=list','1','1','1','store','21'),('202','2','题库管理','题目类型管理','1',NULL,'题目类型管理','store/catalog?operation=list','1','1','1','store','22'),('203','2','题库管理','学校管理','1',NULL,'企业管理','store/company?operation=list','1','1','1',NULL,'11'),('204','2','题库管理','题目管理','1',NULL,'题目管理','store/question?operation=list','1','1','1','store','23'),('205','2','题库管理','题目审核日志','1',NULL,'题目审核日志','store/examineLog?operation=list','1','1','1','front','24'),('3',NULL,NULL,'会员管理','0',NULL,'会员管理','front','0','1','1','front','3'),('301','3','会员管理','会员账号管理','1',NULL,'会员账号管理','front/member?operation=list','1','1','1','front','31'),('302','3','会员管理','会员答题管理','1',NULL,'会员答题管理','front/examinationPaper?operation=list','1','1','1','front','32');

/*Table structure for table `ss_role` */

DROP TABLE IF EXISTS `ss_role`;

CREATE TABLE `ss_role` (
  `role_id` varchar(40) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `order_no` decimal(11,0) DEFAULT NULL,
  `create_by` varchar(40) DEFAULT NULL,
  `create_dept` varchar(40) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(40) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `ss_role` */

insert  into `ss_role`(`role_id`,`name`,`remark`,`order_no`,`create_by`,`create_dept`,`create_time`,`update_by`,`update_time`) values ('4028a1c34ec2e5c8014ec2ebf8430001','系统维护管理员','日常系统维护管理人员，账户异常处理，忘记密码重置等等','2',NULL,NULL,'2015-09-11 16:59:44',NULL,'2015-07-25 09:55:21'),('4028a1c34ec2e5c8014ec2ec38cc0002','题目审核员','负责题目审核，包括题干和选项','3',NULL,NULL,'2015-09-11 16:59:47',NULL,'2015-07-25 09:55:37'),('4028a1cd4ee2d9d6014ee2df4c6a0000','题目录入员','负责题目录入','4',NULL,NULL,'2015-09-11 16:59:49',NULL,'2015-07-31 14:49:21'),('4028a1cd4ee2d9d6014ee2df4c6a0001','超级管理员（慎用）','sysadmin，超级管理员，为系统最高权限','1',NULL,NULL,'2015-09-11 16:58:57',NULL,'2015-07-20 00:00:00');

/*Table structure for table `ss_role_module` */

DROP TABLE IF EXISTS `ss_role_module`;

CREATE TABLE `ss_role_module` (
  `role_id` varchar(255) NOT NULL DEFAULT '',
  `module_id` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`role_id`,`module_id`) USING BTREE,
  KEY `FK_Reference_6` (`module_id`) USING BTREE,
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`role_id`) REFERENCES `ss_role` (`role_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`module_id`) REFERENCES `ss_module` (`module_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `ss_role_module` */

insert  into `ss_role_module`(`role_id`,`module_id`) values ('4028a1c34ec2e5c8014ec2ebf8430001','1'),('4028a1cd4ee2d9d6014ee2df4c6a0001','1'),('4028a1c34ec2e5c8014ec2ebf8430001','101'),('4028a1cd4ee2d9d6014ee2df4c6a0001','101'),('4028a1c34ec2e5c8014ec2ebf8430001','102'),('4028a1cd4ee2d9d6014ee2df4c6a0001','102'),('4028a1c34ec2e5c8014ec2ebf8430001','103'),('4028a1cd4ee2d9d6014ee2df4c6a0001','103'),('4028a1c34ec2e5c8014ec2ebf8430001','104'),('4028a1cd4ee2d9d6014ee2df4c6a0001','104'),('4028a1c34ec2e5c8014ec2ebf8430001','105'),('4028a1cd4ee2d9d6014ee2df4c6a0001','105'),('4028a1c34ec2e5c8014ec2ec38cc0002','2'),('4028a1cd4ee2d9d6014ee2df4c6a0000','2'),('4028a1cd4ee2d9d6014ee2df4c6a0001','2'),('4028a1c34ec2e5c8014ec2ec38cc0002','201'),('4028a1cd4ee2d9d6014ee2df4c6a0001','201'),('4028a1c34ec2e5c8014ec2ec38cc0002','202'),('4028a1cd4ee2d9d6014ee2df4c6a0001','202'),('4028a1c34ec2e5c8014ec2ec38cc0002','203'),('4028a1cd4ee2d9d6014ee2df4c6a0001','203'),('4028a1c34ec2e5c8014ec2ec38cc0002','204'),('4028a1cd4ee2d9d6014ee2df4c6a0000','204'),('4028a1cd4ee2d9d6014ee2df4c6a0001','204'),('4028a1c34ec2e5c8014ec2ec38cc0002','205'),('4028a1cd4ee2d9d6014ee2df4c6a0001','205'),('4028a1cd4ee2d9d6014ee2df4c6a0001','3'),('4028a1cd4ee2d9d6014ee2df4c6a0001','301'),('4028a1cd4ee2d9d6014ee2df4c6a0001','302');

/*Table structure for table `ss_role_user` */

DROP TABLE IF EXISTS `ss_role_user`;

CREATE TABLE `ss_role_user` (
  `user_id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`) USING BTREE,
  KEY `SYS_C005471` (`user_id`) USING BTREE,
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`user_id`) REFERENCES `ss_user` (`user_id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`role_id`) REFERENCES `ss_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `ss_role_user` */

insert  into `ss_role_user`(`user_id`,`role_id`) values ('19f197c3-fed9-4259-b11c-cd0c35d9419f','4028a1cd4ee2d9d6014ee2df4c6a0000'),('564abd78-4114-466c-b74d-eb9bd9b09804','4028a1c34ec2e5c8014ec2ebf8430001'),('896aab72-0a13-4efa-bb9d-ec8a5715fd95','4028a1cd4ee2d9d6014ee2df4c6a0001'),('cf0015d3-bcdd-433b-889d-808b53b72640','4028a1c34ec2e5c8014ec2ebf8430001'),('e0de22fe-2c50-4216-ad75-ed0494d2dc92','4028a1cd4ee2d9d6014ee2df4c6a0001');

/*Table structure for table `ss_sys_log` */

DROP TABLE IF EXISTS `ss_sys_log`;

CREATE TABLE `ss_sys_log` (
  `id` varchar(40) NOT NULL,
  `user_name` varchar(30) DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `method` varchar(100) DEFAULT NULL,
  `action` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `ss_sys_log` */

insert  into `ss_sys_log`(`id`,`user_name`,`ip`,`time`,`method`,`action`) values ('05111F64939940F9B9B447BB96D48E48','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:29:42','main','http://localhost:8998/heima_mm_advance/login?operation=main'),('05690FAB0B1F4F4BBDB21A2E62FCD9C9','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:31:39','home','http://localhost:8998/heima_mm_advance/login?operation=home'),('168969F2F5D14414852ACE2403A03EC7','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:48:22','list','http://localhost:8998/heima_mm_advance/front/examinationPaper?operation=list'),('1748D18A6A3E4849900FC64A17397D8D','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:35:00','list','http://localhost:8998/heima_mm_advance/front/member?operation=list'),('18316481980649A9A61F45E363257664','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:26:50','main','http://localhost:8998/heima_mm_advance/login?operation=main'),('191E6F4A215142EDB8B99090B544D6B0','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:53:43','home','http://localhost:8998/heima_mm_advance/login?operation=home'),('1E5DBD41DB9846658F9DF9B76E402442','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:32:49','home','http://localhost:8998/heima_mm_advance/login?operation=home'),('2CE3CD948F604855B099185FA99E84E5','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:30:04','list','http://localhost:8998/heima_mm_advance/front/member?operation=list'),('2FE7AFE61EAB4D0FA24F784DECB82CB6','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:33:46','main','http://localhost:8998/heima_mm_advance/login?operation=main'),('3DC60C1A0F6845BF86F8D8D3F2B66518','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:50:21','list','http://localhost:8998/heima_mm_advance/front/examinationPaper?operation=list'),('412B19EC6A834D21ADC11A1F680C65F4','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:47:46','list','http://localhost:8998/heima_mm_advance/front/examinationPaper?operation=list'),('431E346C921141FDAA29B529616A5A53','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:29:43','home','http://localhost:8998/heima_mm_advance/login?operation=home'),('4DF810ADB6044C00BD5BDD780D178BBB','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:29:16','main','http://localhost:8998/heima_mm_advance/login?operation=main'),('4E6368731D1543F5B47AF63DC24BA59B','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:48:19','main','http://localhost:8998/heima_mm_advance/login?operation=main'),('5AD73295CBC3488D9600A6D0F338881A','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:32:49','main','http://localhost:8998/heima_mm_advance/login?operation=main'),('5E629D7517894AF1A5BF0AA86B79F3D0','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:29:14','list','http://localhost:8998/heima_mm_advance/front/member?operation=list'),('6231196850BA4F45971E12E85026B3C6','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:33:47','list','http://localhost:8998/heima_mm_advance/front/member?operation=list'),('62BEF4C066FB43F6A44B956B685F6D40','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:29:15','list','http://localhost:8998/heima_mm_advance/front/member?operation=list'),('638AB8899CCF47DD87A979923439D488','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:48:20','home','http://localhost:8998/heima_mm_advance/login?operation=home'),('63A701AB7C8A49A2B58377970E3191BB','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:44:10','main','http://localhost:8998/heima_mm_advance/login?operation=main'),('655C81D38BDB4B948118459C2C5C8C57','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:47:46','list','http://localhost:8998/heima_mm_advance/front/examinationPaper?operation=list'),('70317416BFEE4A35A828D8D07D4268A3','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:47:45','list','http://localhost:8998/heima_mm_advance/front/examinationPaper?operation=list'),('759B3EF9C8AE460A85BCEDD7F8C85D6A','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:34:58','main','http://localhost:8998/heima_mm_advance/login?operation=main'),('7B0858DAD155457CBE048F4954AAF46D','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:46:43','home','http://localhost:8998/heima_mm_advance/login?operation=home'),('7CE55CD87E2E4428A035D11BF8FADC96','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:53:42','main','http://localhost:8998/heima_mm_advance/login?operation=main'),('7F6595CC631A4B1E9CD743927573C64A','cgx','0:0:0:0:0:0:0:1','2020-03-08 23:07:10','main','http://localhost:8998/heima_mm_advance/login?operation=main'),('8A02C282356E417984C1C7A2BCF85BBF','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:29:48','list','http://localhost:8998/heima_mm_advance/front/member?operation=list'),('8B7F03B3F5984F67A359E8AB9C9DAFA0','cgx','0:0:0:0:0:0:0:1','2020-03-08 23:07:11','home','http://localhost:8998/heima_mm_advance/login?operation=home'),('8D428B860AF3436B83B18FA6570E0808','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:46:44','list','http://localhost:8998/heima_mm_advance/front/examinationPaper?operation=list'),('8E5298BE68774A67A4168F384E1253AA','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:53:46','list','http://localhost:8998/heima_mm_advance/front/examinationPaper?operation=list'),('8E8CD9DB5F8B4AC695859DB0586A4FFE','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:44:12','home','http://localhost:8998/heima_mm_advance/login?operation=home'),('8ED73A4397A5407AA9ECD642C23832A2','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:26:52','list','http://localhost:8998/heima_mm_advance/front/member?operation=list'),('95AC19AFAAF7485C99890E2CF8ABCDA2','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:47:48','list','http://localhost:8998/heima_mm_advance/front/examinationPaper?operation=list'),('9B13BABABF6A464D99038EBE1BD2656A','cgx','0:0:0:0:0:0:0:1','2020-03-08 23:05:32','main','http://localhost:8998/heima_mm_advance/login?operation=main'),('AA0E3945A92E426186AF0D534B13EBA9','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:46:41','main','http://localhost:8998/heima_mm_advance/login?operation=main'),('B10005ED75B54BDD90C1072DB1867326','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:50:19','main','http://localhost:8998/heima_mm_advance/login?operation=main'),('B11EEE3CDE9C4D2B981E450D4DCECC27','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:29:45','list','http://localhost:8998/heima_mm_advance/front/member?operation=list'),('BA001942C0D84CFDA9A72EBE73B0CE00','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:34:58','home','http://localhost:8998/heima_mm_advance/login?operation=home'),('C2DB2E1F0168418383BF22FB0F287A3D','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:48:21','list','http://localhost:8998/heima_mm_advance/front/examinationPaper?operation=list'),('CA2F23397F8149A7B437CD1F90233E80','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:32:51','list','http://localhost:8998/heima_mm_advance/front/member?operation=list'),('CE21816164834192BAF76BC8F72940AF','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:29:16','home','http://localhost:8998/heima_mm_advance/login?operation=home'),('DE6C59368269451B8BC0FC907BDDC4F7','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:31:38','main','http://localhost:8998/heima_mm_advance/login?operation=main'),('DF5DE609E164406FAFEEA7DB890EE8B1','cgx','0:0:0:0:0:0:0:1','2020-03-08 23:05:34','home','http://localhost:8998/heima_mm_advance/login?operation=home'),('DFDA93B5560144C988D5685E53FAC9C4','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:31:41','list','http://localhost:8998/heima_mm_advance/front/member?operation=list'),('EBB73660B64142E1889BE0C315E1AE07','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:33:46','home','http://localhost:8998/heima_mm_advance/login?operation=home'),('EF07E7E705454893B17AF8CE3655961F','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:29:18','list','http://localhost:8998/heima_mm_advance/front/member?operation=list'),('F57CE400AE324C68B0C41534F4FF7CEB','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:26:51','home','http://localhost:8998/heima_mm_advance/login?operation=home'),('F63A34DA21C549FFB3C9329121E0F26A','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:50:20','home','http://localhost:8998/heima_mm_advance/login?operation=home'),('F63E315A985040128F2DFAFC074FDF27','cgx','0:0:0:0:0:0:0:1','2020-03-08 17:29:14','list','http://localhost:8998/heima_mm_advance/front/member?operation=list');

/*Table structure for table `ss_user` */

DROP TABLE IF EXISTS `ss_user`;

CREATE TABLE `ss_user` (
  `user_id` varchar(40) NOT NULL,
  `email` varchar(60) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL COMMENT '不能重复,可为中文',
  `station` varchar(20) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL COMMENT 'shiro MD5密码32位',
  `state` decimal(11,0) DEFAULT NULL COMMENT '1启用0停用',
  `manager_id` varchar(40) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `telephone` varchar(11) DEFAULT NULL,
  `birthday` varchar(20) DEFAULT NULL,
  `degree` int(2) DEFAULT NULL,
  `salary` decimal(10,0) DEFAULT NULL,
  `join_date` varchar(20) DEFAULT NULL,
  `order_no` int(3) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `dept_id` varchar(40) DEFAULT NULL,
  `dept_name` varchar(40) DEFAULT NULL,
  `create_by` varchar(40) DEFAULT NULL COMMENT '登录人编号',
  `create_dept` varchar(40) DEFAULT NULL COMMENT '登录人所属部门编号',
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(40) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  KEY `SYS_C005467` (`dept_id`) USING BTREE,
  CONSTRAINT `SYS_C005467` FOREIGN KEY (`dept_id`) REFERENCES `ss_dept` (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `ss_user` */

insert  into `ss_user`(`user_id`,`email`,`user_name`,`station`,`password`,`state`,`manager_id`,`gender`,`telephone`,`birthday`,`degree`,`salary`,`join_date`,`order_no`,`remark`,`dept_id`,`dept_name`,`create_by`,`create_dept`,`create_time`,`update_by`,`update_time`) values ('19f197c3-fed9-4259-b11c-cd0c35d9419f','test@itcast.cn','张老师','管理员','4QrcOUm6Wau+VuBX8g+IPg==','1',NULL,'1','13800138024','1988-07-26 00:00:00',4,'20000','2013-02-20 00:00:00',0,'貌美如花','100101','集团总部',NULL,NULL,'2020-07-02 12:04:28',NULL,NULL),('564abd78-4114-466c-b74d-eb9bd9b09804','test@wuyi.com','刘老师',NULL,'CY9rzUYh03PK3k6DJie09g==','1',NULL,'0','12345678910','2021-11-29 00:00:00',NULL,NULL,'2021-12-06 00:00:00',NULL,NULL,'100102',NULL,NULL,NULL,NULL,NULL,NULL),('896aab72-0a13-4efa-bb9d-ec8a5715fd95','test@itcast.cn','赵老师',NULL,'CY9rzUYh03PK3k6DJie09g==','1',NULL,'0','12345678910','2021-11-29 00:00:00',NULL,NULL,'2021-12-01 00:00:00',NULL,NULL,'100101102',NULL,NULL,NULL,NULL,NULL,NULL),('cf0015d3-bcdd-433b-889d-808b53b72640','mz@itheima.com','毛老师','总经理','gdyb21LQTcIANtvYMT7QVQ==','1',NULL,'1','13800138022','1984-08-13 00:00:00',4,'500000','2010-01-05 00:00:00',1,'貌美如花','100','集团总部',NULL,NULL,'2016-12-06 11:35:25',NULL,'1970-01-01 08:00:00'),('e0de22fe-2c50-4216-ad75-ed0494d2dc92','cgx@itheima.com','李老师','部门经理','gdyb21LQTcIANtvYMT7QVQ==','1',NULL,'0','13800138023','1981-01-13 00:00:00',1,'20000','2011-09-16 00:00:00',2,'貌美如花','100102101','集团总部',NULL,NULL,'2016-12-06 11:35:25',NULL,'1970-01-01 08:00:00');

/*Table structure for table `st_catalog` */

DROP TABLE IF EXISTS `st_catalog`;

CREATE TABLE `st_catalog` (
  `id` varchar(100) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL COMMENT '状态\r\n            0 启用\r\n            1 禁用',
  `remark` varchar(2000) DEFAULT NULL,
  `order_no` int(11) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_dept` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `course_id` varchar(100) DEFAULT NULL,
  `course_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_FK_course_catalog` (`course_id`) USING BTREE,
  CONSTRAINT `FK_FK_course_catalog` FOREIGN KEY (`course_id`) REFERENCES `st_course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `st_catalog` */

insert  into `st_catalog`(`id`,`name`,`state`,`remark`,`order_no`,`create_by`,`create_dept`,`create_time`,`update_by`,`update_time`,`course_id`,`course_name`) values ('1','Java基础','1','java基础知识',1,NULL,NULL,NULL,NULL,NULL,'1','Java'),('2','JavaWeb','1','java语言进行web开发',1,NULL,NULL,NULL,NULL,NULL,'1','Java'),('3','Spring MVC','1','框架开发',1,NULL,NULL,NULL,NULL,NULL,'1','Java'),('5','Python基础','1','基础',1,NULL,NULL,NULL,NULL,NULL,'2','Python'),('6','函数编程','1','java8新特性',1,NULL,NULL,NULL,NULL,NULL,'1','Java'),('A057DB3313D74653A638CF03001366FF','基础课程目录','1','Python相关题目',1,NULL,NULL,NULL,NULL,NULL,'2','Python');

/*Table structure for table `st_company` */

DROP TABLE IF EXISTS `st_company`;

CREATE TABLE `st_company` (
  `id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `expiration_date` datetime DEFAULT NULL COMMENT '到期时间',
  `address` varchar(255) DEFAULT NULL COMMENT '公司地址',
  `license_id` varchar(255) DEFAULT NULL COMMENT '营业执照-图片',
  `representative` varchar(255) DEFAULT NULL COMMENT '法人代表',
  `phone` varchar(255) DEFAULT NULL COMMENT '公司电话',
  `company_size` varchar(255) DEFAULT NULL COMMENT '公司规模',
  `industry` varchar(255) DEFAULT NULL COMMENT '所属行业',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `state` int(2) DEFAULT '1' COMMENT '状态',
  `city` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

/*Data for the table `st_company` */

insert  into `st_company`(`id`,`name`,`expiration_date`,`address`,`license_id`,`representative`,`phone`,`company_size`,`industry`,`remarks`,`state`,`city`) values ('1','五邑大学',NULL,'广东省江门市迎宾大道中99号','公办大学','马老师','010-88888888','11349','综合类','',1,'江门'),('10','中山大学',NULL,'广州市海珠区新港西路135号','公办大学','刘老师','027-55555555','10558','综合类','',1,'广州'),('12','武汉大学',NULL,'湖北省武汉市武昌区珞珈山街道八一路299号','公办大学','李老师','027-6666666','10486','综合类','',1,'武汉'),('13','广东海洋大学',NULL,'广东省湛江市麻章区海大路1号','公办大学','赵老师','027-33333333','10566','农林类','',1,'湛江'),('14','北京师范大学珠海分校',NULL,'广东省珠海市香洲区唐家湾镇金凤路18号','独立学院','高老师','010-22222222','13177','综合类','',1,'珠海'),('17','汕头大学',NULL,'广东省汕头市大学路243号','公办大学','王老师','010-8208820','10560','综合类','',1,'汕头');

/*Table structure for table `st_course` */

DROP TABLE IF EXISTS `st_course`;

CREATE TABLE `st_course` (
  `id` varchar(100) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL COMMENT '是否显示\r\n            0 显示\r\n            1 不显示',
  `remark` varchar(2000) DEFAULT NULL,
  `order_no` int(11) DEFAULT NULL COMMENT '排序编号',
  `create_by` varchar(50) DEFAULT NULL,
  `create_dept` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='学科表\r\n';

/*Data for the table `st_course` */

insert  into `st_course`(`id`,`name`,`state`,`remark`,`order_no`,`create_by`,`create_dept`,`create_time`,`update_by`,`update_time`) values ('1','Java','1','目前最火的学科',1,NULL,NULL,NULL,NULL,NULL),('2','Python','1','时下很流行的学科',0,NULL,NULL,NULL,NULL,NULL),('3','大数据','1','很有钱景的学科',0,NULL,NULL,NULL,NULL,NULL),('36D5AAD93E4D4AE79D61DE980753B1FA','Go','1','当前很火的学科',NULL,NULL,NULL,NULL,NULL,NULL),('4','Php','1','怎么翻译呢',0,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `st_examine_log` */

DROP TABLE IF EXISTS `st_examine_log`;

CREATE TABLE `st_examine_log` (
  `id` varchar(100) NOT NULL,
  `comments` varchar(100) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL COMMENT '审核状态\r\n            0 待审核\r\n            1 已审核\r\n            2 已拒绝',
  `question_id` varchar(100) NOT NULL,
  `user_id` varchar(100) DEFAULT NULL COMMENT '审核人ID',
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_dept` varchar(50) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_FK_examine_question` (`question_id`) USING BTREE,
  CONSTRAINT `FK_FK_examine_question` FOREIGN KEY (`question_id`) REFERENCES `st_question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='审核日志表';

/*Data for the table `st_examine_log` */

insert  into `st_examine_log`(`id`,`comments`,`status`,`question_id`,`user_id`,`create_time`,`create_by`,`create_dept`,`update_by`,`update_time`) values ('01406E951164496CBA7A29FC91DF5246','1112527','1','01406E951164496CBA7A29FC91DF5246',NULL,NULL,NULL,NULL,NULL,NULL),('1','没有选项','-1','1',NULL,'2020-03-07 12:18:01','e0de22fe-2c50-4216-ad75-ed0494d2dc92','100101',NULL,NULL);

/*Table structure for table `st_question` */

DROP TABLE IF EXISTS `st_question`;

CREATE TABLE `st_question` (
  `picture` varchar(2000) DEFAULT NULL,
  `id` varchar(100) NOT NULL,
  `catalog_id` varchar(100) NOT NULL COMMENT '方向id',
  `catalog_name` varchar(100) DEFAULT NULL,
  `course_id` varchar(100) DEFAULT NULL COMMENT '冗余设计，为了提高数据提取',
  `course_name` varchar(100) DEFAULT NULL,
  `number` varchar(20) DEFAULT NULL COMMENT '试题编号',
  `subject` varchar(200) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL COMMENT '题目类型： 1. 单选  2. 多选 5. 简答',
  `difficulty` varchar(10) DEFAULT NULL COMMENT '难度： \r\n            1 简单\r\n            2 一般\r\n            3 困难',
  `analysis` varchar(2000) DEFAULT NULL COMMENT '答案解析',
  `analysis_video` varchar(1000) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `is_classic` varchar(10) DEFAULT NULL COMMENT '是否精选题目\r\n            0 不是\r\n            1 是',
  `state` varchar(10) DEFAULT NULL COMMENT '题目状态\r\n            0 待发布（待审核、已拒绝）\r\n            1 已发布（已审核）\r\n            2 已下架（已审核）',
  `review_status` varchar(10) DEFAULT NULL COMMENT '0 待审核\r\n            1 已审核\r\n            2 已拒绝',
  `create_by` varchar(100) DEFAULT NULL,
  `create_dept` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `company_id` varchar(50) DEFAULT NULL COMMENT '企业id',
  `company_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_FK_question_catalog` (`catalog_id`) USING BTREE,
  KEY `FK_FK_question_course` (`course_id`) USING BTREE,
  KEY `FK_Reference_16` (`company_id`) USING BTREE,
  CONSTRAINT `FK_FK_question_catalog` FOREIGN KEY (`catalog_id`) REFERENCES `st_catalog` (`id`),
  CONSTRAINT `FK_FK_question_course` FOREIGN KEY (`course_id`) REFERENCES `st_course` (`id`),
  CONSTRAINT `FK_Reference_16` FOREIGN KEY (`company_id`) REFERENCES `st_company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='试题表';

/*Data for the table `st_question` */

insert  into `st_question`(`picture`,`id`,`catalog_id`,`catalog_name`,`course_id`,`course_name`,`number`,`subject`,`type`,`difficulty`,`analysis`,`analysis_video`,`remark`,`is_classic`,`state`,`review_status`,`create_by`,`create_dept`,`create_time`,`update_by`,`update_time`,`company_id`,`company_name`) values (NULL,'01406E951164496CBA7A29FC91DF5246','1','Java基础','1','Java',NULL,'下面语句中，正确的是','1','1','测试','I:\\javaee_core\\itheima_mm\\target\\itheima_mm-1.0-SNAPSHOT\\uploads/1_1/2915105DF2A64AD799989AB90C7D26BE_课改反馈20200225.png','这是一道测试试题','0','1','1','e0de22fe-2c50-4216-ad75-ed0494d2dc92','100101','2020-03-06 00:38:30',NULL,NULL,'1','北京淘宝网'),(NULL,'1','2','Java基础','1','Java','1','下面关于方法的说法，错误的是','1','2','测试2','','这是一道测试试题2','1','1','1','1',NULL,'2019-08-08 00:00:00',NULL,NULL,'10','北京淘宝网'),(NULL,'3a63fef2-b070-4765-b93b-45aedb8e62e1','1',NULL,NULL,NULL,NULL,'JAVA语言中，在类定义时使用final关键字修饰，是指这个类','1','2','基础中的基础',NULL,'java入门','1','1','0',NULL,NULL,'2021-12-19 10:00:19',NULL,NULL,'10',NULL),(NULL,'b05d10c8-a774-4534-a0fe-375e6a082df0','2',NULL,NULL,NULL,NULL,'1219test','1','1','1219test',NULL,'1219test','1','1','0',NULL,NULL,'2021-12-19 16:01:57',NULL,NULL,'10',NULL),(NULL,'b4b4542a-e58a-457a-b956-bee32413d072','1',NULL,NULL,NULL,NULL,'下面选项中，（）是Java 关键字','2','3','测试3',NULL,'这是一道测试试题3','1','1','1',NULL,NULL,'2021-12-17 14:56:23',NULL,NULL,'12',NULL);

/*Table structure for table `st_question_item` */

DROP TABLE IF EXISTS `st_question_item`;

CREATE TABLE `st_question_item` (
  `id` varchar(100) NOT NULL,
  `question_id` varchar(100) DEFAULT NULL COMMENT '隶属题目',
  `content` varchar(200) DEFAULT NULL,
  `picture` varchar(1000) DEFAULT NULL,
  `is_right` varchar(10) DEFAULT NULL COMMENT '是否正确答案\r\n            0 不是\r\n            1 是',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_question_id` (`question_id`) USING BTREE,
  CONSTRAINT `FK_question_id` FOREIGN KEY (`question_id`) REFERENCES `st_question` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='试题选项';

/*Data for the table `st_question_item` */

insert  into `st_question_item`(`id`,`question_id`,`content`,`picture`,`is_right`) values ('2b805d94-158d-4000-a121-abb4c47f479e','b4b4542a-e58a-457a-b956-bee32413d072','continue','','1'),('51f67044-5ce2-42b7-8e0b-074aecc4010c','01406E951164496CBA7A29FC91DF5246','boolean b=”true”;','','0'),('7398e7ff-be21-4a56-b513-4a48ca13c4b9','b05d10c8-a774-4534-a0fe-375e6a082df0','true','','1'),('79602144-5c2a-432c-9241-951e10d626ad','3a63fef2-b070-4765-b93b-45aedb8e62e1','在子类方法中不能被调用','','0'),('7f8e5afa-0c33-483c-8a3b-25d74f98f972','b4b4542a-e58a-457a-b956-bee32413d072','then','','0'),('83b1e489-10bb-4df0-bab3-b262ce3fc2c9','b4b4542a-e58a-457a-b956-bee32413d072','new','','1'),('86c05dd0-24fe-4098-8a4a-63bb50a39dc1','1','如果程序定义了一个或多个构造方法，在创建对象时，也可以用系统自  动生成空的构造方法','','1'),('8aaad337-767f-4524-b083-9b9045eaebcb','b05d10c8-a774-4534-a0fe-375e6a082df0','f3','','0'),('9da9c7fe-f7a6-49fa-9384-0a9a89a65ba8','3a63fef2-b070-4765-b93b-45aedb8e62e1','不能被子类的方法覆盖','','0'),('ab6083fc-04cc-4d9b-9862-1f816ef310f2','b05d10c8-a774-4534-a0fe-375e6a082df0','f1','','0'),('ae34abd9-2a61-418b-8098-2545a8184280','01406E951164496CBA7A29FC91DF5246','char c=”A”;','','0'),('ae4d605e-af12-4150-8aa0-5f9497f0bb67','01406E951164496CBA7A29FC91DF5246','double x=2.5f;','','1'),('ba93bbd8-a585-4f6e-aaff-6f732fd8c95b','1','Java 中的方法参数传递时传值调用，而不是地址调用','','0'),('d2a33cae-c71b-4e69-adef-419af1643f83','1','方法体是对方法的实现，包括变量声明和Java 的合法语句','','0'),('d726eee2-5165-48d2-925b-9202a43b2d31','3a63fef2-b070-4765-b93b-45aedb8e62e1','能被别的程序自由调用','','0'),('dd84581b-e436-45ed-9164-bbae775c7933','1','类的私有方法不能被其子类直接访问','','0'),('eefbf657-9df1-4a62-9595-4b596a9817d6','01406E951164496CBA7A29FC91DF5246','float y=0.8d;','','0'),('f86d0e36-8065-4e89-b445-218ebd66a238','3a63fef2-b070-4765-b93b-45aedb8e62e1','不能被继承','','1'),('f9a046da-bb0b-4e89-9c28-674445ad67c1','b05d10c8-a774-4534-a0fe-375e6a082df0','f2','','0'),('fe3b6465-9363-44d6-ab4d-8fd11a9c1015','b4b4542a-e58a-457a-b956-bee32413d072','PUBLIC','','0');

/*Table structure for table `tr_examination_paper` */

DROP TABLE IF EXISTS `tr_examination_paper`;

CREATE TABLE `tr_examination_paper` (
  `create_time` datetime DEFAULT NULL,
  `score` float DEFAULT NULL,
  `id` varchar(100) NOT NULL,
  `member_id` varchar(100) DEFAULT NULL,
  `state` int(11) DEFAULT NULL COMMENT '存储用户答案选项',
  `apply_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='会员试卷表';

/*Data for the table `tr_examination_paper` */

insert  into `tr_examination_paper`(`create_time`,`score`,`id`,`member_id`,`state`,`apply_time`) values (NULL,NULL,'3ff8862b-6520-4f34-9342-d3edb9bfbab7','f3745c86-acb2-41c6-871e-0f8a160ef031',1,'2021-12-19 15:21:26'),(NULL,NULL,'7e8d5665-66cc-433c-8562-b00c6055294c','f3745c86-acb2-41c6-871e-0f8a160ef031',1,'2021-12-19 15:00:56'),(NULL,NULL,'90400f18-be0b-46f2-bd1c-404fa9ef9d86','f3745c86-acb2-41c6-871e-0f8a160ef031',1,'2021-12-19 15:54:04'),(NULL,NULL,'9307ed8c-c383-4f25-888b-d9eeb9a7dea3','f3745c86-acb2-41c6-871e-0f8a160ef031',1,'2021-12-19 14:38:40'),(NULL,NULL,'d4924480-d2b3-4ba3-9e56-bb4a157da480','f3745c86-acb2-41c6-871e-0f8a160ef031',1,'2021-12-19 13:29:57'),(NULL,NULL,'ffd05074-50bb-4ae0-bb49-180be99c764d','f3745c86-acb2-41c6-871e-0f8a160ef031',1,'2021-12-19 16:13:57');

/*Table structure for table `tr_member` */

DROP TABLE IF EXISTS `tr_member`;

CREATE TABLE `tr_member` (
  `id` varchar(100) NOT NULL,
  `nick_name` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telephone` varchar(50) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `register_date` datetime DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='会员表\r\n';

/*Data for the table `tr_member` */

insert  into `tr_member`(`id`,`nick_name`,`password`,`gender`,`birthday`,`email`,`telephone`,`address`,`register_date`,`state`) values ('1','黑马小孩','4QrcOUm6Wau+VuBX8g+IPg==','male','1995-07-20 17:27:30','xiaohai@163.com','13567890123','北京','2020-03-02 17:28:13','1'),('2','初入江湖','4QrcOUm6Wau+VuBX8g+IPg==','male','1995-07-20 17:27:30','test@sina.com','13254789021','山东','2020-03-02 17:28:13','0'),('c63d4e8e-0d1c-4358-85c9-d8d8a85c604b','jimmy','Z088LBqKb5BGHopm+1VQug==','男','2021-12-19 00:00:00','4@5.6','456456','456456','2021-12-19 00:28:29','1'),('df4a7039-4deb-4a9a-8bd7-682eb882dd88','wwww','gdyb21LQTcIANtvYMT7QVQ==','男','2021-12-16 00:00:00','1@1.1','11','22','2021-12-16 17:14:17','1'),('f3745c86-acb2-41c6-871e-0f8a160ef031','jock','gdyb21LQTcIANtvYMT7QVQ==','男','2021-12-16 00:00:00','1@2.3','123123','123123','2021-12-16 15:59:31','1');

/*Table structure for table `tr_member_question` */

DROP TABLE IF EXISTS `tr_member_question`;

CREATE TABLE `tr_member_question` (
  `id` varchar(100) NOT NULL,
  `question_id` varchar(100) DEFAULT NULL,
  `examinationpaper_id` varchar(100) DEFAULT NULL,
  `answer_result` varchar(100) DEFAULT NULL COMMENT '存储用户答案选项',
  `right_answer` varchar(100) DEFAULT NULL COMMENT '是否收藏：0.否 1.是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='会员答题表';

/*Data for the table `tr_member_question` */

insert  into `tr_member_question`(`id`,`question_id`,`examinationpaper_id`,`answer_result`,`right_answer`) values ('049c9c36-3624-495d-bd65-3fd2a6707cd6','3a63fef2-b070-4765-b93b-45aedb8e62e1','7e8d5665-66cc-433c-8562-b00c6055294c','79602144-5c2a-432c-9241-951e10d626ad',NULL),('05273f0a-d912-4701-9002-368be3a23266','b4b4542a-e58a-457a-b956-bee32413d072','ffd05074-50bb-4ae0-bb49-180be99c764d','2b805d94-158d-4000-a121-abb4c47f479e,7f8e5afa-0c33-483c-8a3b-25d74f98f972',NULL),('294d3947-b1ba-4b5c-9c09-c6ca6b626ddf','b05d10c8-a774-4534-a0fe-375e6a082df0','ffd05074-50bb-4ae0-bb49-180be99c764d','7398e7ff-be21-4a56-b513-4a48ca13c4b9',NULL),('2e2bbaea-2dd8-4bbe-9271-ccedc3f7f577','01406E951164496CBA7A29FC91DF5246','d4924480-d2b3-4ba3-9e56-bb4a157da480','ae4d605e-af12-4150-8aa0-5f9497f0bb67',NULL),('33b92b6a-3bba-47e8-a9d6-a4a62435b947','1','9307ed8c-c383-4f25-888b-d9eeb9a7dea3','86c05dd0-24fe-4098-8a4a-63bb50a39dc1',NULL),('42072178-9da7-4773-b951-d8a27278a386','3a63fef2-b070-4765-b93b-45aedb8e62e1','ffd05074-50bb-4ae0-bb49-180be99c764d','d726eee2-5165-48d2-925b-9202a43b2d31',NULL),('56ac83c2-fb9f-4500-b0d5-509493cabb46','3a63fef2-b070-4765-b93b-45aedb8e62e1','d4924480-d2b3-4ba3-9e56-bb4a157da480','79602144-5c2a-432c-9241-951e10d626ad',NULL),('63982be8-d4ca-440f-beda-0d934044e683','3a63fef2-b070-4765-b93b-45aedb8e62e1','3ff8862b-6520-4f34-9342-d3edb9bfbab7','d726eee2-5165-48d2-925b-9202a43b2d31',NULL),('646c4d78-7c3c-47aa-a199-cb10ae0d2934','1','3ff8862b-6520-4f34-9342-d3edb9bfbab7','86c05dd0-24fe-4098-8a4a-63bb50a39dc1',NULL),('6b29e8ca-69b0-4656-8b78-dc150321b176','1','d4924480-d2b3-4ba3-9e56-bb4a157da480','86c05dd0-24fe-4098-8a4a-63bb50a39dc1',NULL),('6cc32ad6-7111-4e8d-a74e-334d0f480e6e','3a63fef2-b070-4765-b93b-45aedb8e62e1','9307ed8c-c383-4f25-888b-d9eeb9a7dea3','79602144-5c2a-432c-9241-951e10d626ad',NULL),('6d567414-f9ae-4ffe-b474-cc1872a203b4','01406E951164496CBA7A29FC91DF5246','9307ed8c-c383-4f25-888b-d9eeb9a7dea3','ae4d605e-af12-4150-8aa0-5f9497f0bb67',NULL),('7735364c-4cf8-442a-a94d-c5bad27e1b90','01406E951164496CBA7A29FC91DF5246','3ff8862b-6520-4f34-9342-d3edb9bfbab7','51f67044-5ce2-42b7-8e0b-074aecc4010c',NULL),('77c983fb-fa27-4386-8c41-5f175257d12c','1','ffd05074-50bb-4ae0-bb49-180be99c764d','86c05dd0-24fe-4098-8a4a-63bb50a39dc1',NULL),('7a1a6ddb-8976-4598-8573-0ef8f2056d68','3a63fef2-b070-4765-b93b-45aedb8e62e1','90400f18-be0b-46f2-bd1c-404fa9ef9d86','d726eee2-5165-48d2-925b-9202a43b2d31',NULL),('8b733174-bfbb-42b0-b354-1fd2104cad71','1','90400f18-be0b-46f2-bd1c-404fa9ef9d86','ba93bbd8-a585-4f6e-aaff-6f732fd8c95b',NULL),('b1f5c538-4c60-4519-8068-a1e2334382b2','01406E951164496CBA7A29FC91DF5246','ffd05074-50bb-4ae0-bb49-180be99c764d','51f67044-5ce2-42b7-8e0b-074aecc4010c',NULL),('ba7e904b-af1e-4495-8920-37aa98cfc5e4','1','7e8d5665-66cc-433c-8562-b00c6055294c','86c05dd0-24fe-4098-8a4a-63bb50a39dc1',NULL),('d55f540e-57b7-4926-9fcf-7de3a2b2b77a','b4b4542a-e58a-457a-b956-bee32413d072','9307ed8c-c383-4f25-888b-d9eeb9a7dea3','2b805d94-158d-4000-a121-abb4c47f479e,83b1e489-10bb-4df0-bab3-b262ce3fc2c9',NULL),('d875cfed-e522-4070-b98c-85a6e6f5249e','01406E951164496CBA7A29FC91DF5246','7e8d5665-66cc-433c-8562-b00c6055294c','ae4d605e-af12-4150-8aa0-5f9497f0bb67',NULL),('e8ff9eef-c7fa-4c18-8842-afa722f6ccac','b4b4542a-e58a-457a-b956-bee32413d072','d4924480-d2b3-4ba3-9e56-bb4a157da480','2b805d94-158d-4000-a121-abb4c47f479e,83b1e489-10bb-4df0-bab3-b262ce3fc2c9',NULL),('f0a0efd5-e2e6-45df-a907-ae102b54d767','b4b4542a-e58a-457a-b956-bee32413d072','7e8d5665-66cc-433c-8562-b00c6055294c','2b805d94-158d-4000-a121-abb4c47f479e,83b1e489-10bb-4df0-bab3-b262ce3fc2c9',NULL),('f0cacc5f-ccc5-4e31-9bcb-6e03b3c5e42d','01406E951164496CBA7A29FC91DF5246','90400f18-be0b-46f2-bd1c-404fa9ef9d86','51f67044-5ce2-42b7-8e0b-074aecc4010c',NULL),('f0f2f4d2-0cb7-49c7-aa6f-6f1e5acdafe2','b4b4542a-e58a-457a-b956-bee32413d072','90400f18-be0b-46f2-bd1c-404fa9ef9d86','2b805d94-158d-4000-a121-abb4c47f479e,7f8e5afa-0c33-483c-8a3b-25d74f98f972',NULL),('fbb09aa5-4c35-45d1-b282-c24c9738f9f1','b4b4542a-e58a-457a-b956-bee32413d072','3ff8862b-6520-4f34-9342-d3edb9bfbab7','7f8e5afa-0c33-483c-8a3b-25d74f98f972,2b805d94-158d-4000-a121-abb4c47f479e',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

```
