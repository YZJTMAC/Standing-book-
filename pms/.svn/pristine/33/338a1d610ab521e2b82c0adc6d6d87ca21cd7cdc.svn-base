# Host: 192.168.0.230  (Version 5.5.28-log)
# Date: 2017-04-24 10:43:29
# Generator: MySQL-Front 6.0  (Build 1.121)

CREATE DATABASE IF NOT EXISTS `dsy-pms` DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE `dsy-pms`;

#
# Structure for table "attachment"
#

CREATE TABLE `attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `object_id` int(11) DEFAULT NULL COMMENT '附件对应的流程ID',
  `table_name` varchar(32) DEFAULT NULL COMMENT '附件对应的实体表名，可废弃字段',
  `filetype` varchar(64) DEFAULT NULL COMMENT '附件类型',
  `name` varchar(255) DEFAULT NULL COMMENT '附件名称',
  `url` varchar(255) DEFAULT NULL COMMENT '附件地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `index_table_id` (`table_name`,`object_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='附件信息表';

#
# Structure for table "department"
#

CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级部门ID',
  `director_id` varchar(64) DEFAULT NULL COMMENT '部门主管ID',
  `dm_name` varchar(64) DEFAULT NULL COMMENT '部门名称',
  `dm_type` varchar(20) DEFAULT NULL COMMENT '部门类型，0 研发 1 教务 2 商务 3 财务 4 办事处、分公司、子公司 5 总经理办公室 6 分管部门',
  `level` varchar(255) DEFAULT NULL COMMENT '部门级别，保留字段',
  `province` int(11) DEFAULT NULL COMMENT '所属省份的ID，总部的部门为空就可',
  `description` varchar(255) DEFAULT NULL COMMENT '部门描述',
  `gen_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `index_parent_id` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8 COMMENT='部门表';

#
# Structure for table "funds"
#

CREATE TABLE `funds` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` tinyint(3) DEFAULT NULL COMMENT '类型  1借款 2 报销',
  `project_id` int(11) DEFAULT NULL COMMENT '项目ID',
  `apply_amount` decimal(10,2) DEFAULT NULL COMMENT '本次申请经费的金额',
  `invoice_content` varchar(255) DEFAULT NULL COMMENT '发票内容',
  `invoice_amount` decimal(10,2) DEFAULT NULL COMMENT '发票金额',
  `rec_name` varchar(32) DEFAULT NULL COMMENT '收款账户名称',
  `rec_bank` varchar(255) DEFAULT NULL COMMENT '开户银行',
  `rec_account` varchar(255) DEFAULT NULL COMMENT '收款账号',
  `rec_bank_num` varchar(128) DEFAULT NULL COMMENT '银行行号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `won_pay` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否已支付 0 未支付 1 已支付',
  `note` varchar(255) DEFAULT NULL COMMENT '备注',
  `available` int(1) NOT NULL DEFAULT '0' COMMENT '是否是废弃数据（经费申请被退回的） 0 否 1 是',
  PRIMARY KEY (`id`),
  KEY `index_pid` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经费申请表';

#
# Structure for table "invoice"
#

CREATE TABLE `invoice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '申请人',
  `project_id` int(11) DEFAULT NULL COMMENT '项目ID',
  `type` tinyint(3) DEFAULT NULL COMMENT '类型 1：已到款开发票  2：未到款开发票',
  `post_name` varchar(64) DEFAULT NULL COMMENT '收件人姓名',
  `post_mobile` varchar(64) DEFAULT NULL COMMENT '收件人电话',
  `post_addr` varchar(255) DEFAULT NULL COMMENT '收件人地址',
  `post_company` varchar(255) DEFAULT NULL COMMENT '收件人公司',
  `advance_paydate` datetime DEFAULT NULL COMMENT '提前开发票的情况下承诺汇款日期',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `note` varchar(255) DEFAULT NULL COMMENT '流程的备注',
  PRIMARY KEY (`id`),
  KEY `index_pid` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发票申请表';

#
# Structure for table "login_log"
#

CREATE TABLE `login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  `name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `login_ip` varchar(32) DEFAULT NULL COMMENT '登录IP',
  `verification_code` varchar(20) DEFAULT NULL COMMENT '短信验证码',
  `succeed` int(1) DEFAULT NULL COMMENT '登录是否成功',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录日志表';

#
# Structure for table "menu"
#

CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(64) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(255) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(255) DEFAULT NULL COMMENT '授权',
  `type` int(1) NOT NULL DEFAULT '0' COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(64) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序（保留字段）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

#
# Structure for table "need_deal_process"
#

CREATE TABLE `need_deal_process` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `process_id` int(11) DEFAULT NULL COMMENT '流程ID',
  `process_name` varchar(255) DEFAULT NULL COMMENT '流程标题',
  `user_id` int(11) DEFAULT NULL COMMENT '当前待办人ID',
  `create_user_id` int(11) DEFAULT NULL COMMENT '发起人ID',
  `create_user_name` varchar(64) DEFAULT NULL COMMENT '发起人姓名',
  `start_time` datetime DEFAULT NULL COMMENT '发起时间',
  `type` int(11) DEFAULT NULL COMMENT '待办类型  1 审批 2 查阅  3 编辑',
  `time_limit` int(11) DEFAULT NULL COMMENT '处理期限 单位小时 （保留字段）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间（接收时间）',
  `finished` int(11) DEFAULT '0' COMMENT '是否已办 0 否 1 是',
  PRIMARY KEY (`id`),
  KEY `index_user_id` (`user_id`,`finished`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='待办事项表';

#
# Structure for table "organization"
#

CREATE TABLE `organization` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT 'ID',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级ID 省的上级为0',
  `name` varchar(32) DEFAULT NULL COMMENT '省市县名称',
  `code` varchar(32) DEFAULT NULL COMMENT '地区编码',
  `num` tinyint(3) NOT NULL DEFAULT '1' COMMENT '设计有误，不再使用',
  PRIMARY KEY (`id`),
  KEY `index_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='省市县信息表';

#
# Structure for table "payment"
#

CREATE TABLE `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(11) DEFAULT NULL COMMENT '到款信息中的序号',
  `year` int(11) DEFAULT NULL COMMENT '到款年份',
  `month` tinyint(3) DEFAULT NULL COMMENT '到款月份',
  `serial_number` varchar(128) DEFAULT NULL COMMENT '流水号',
  `tra_time` datetime DEFAULT NULL COMMENT '交易时间',
  `remitter` varchar(64) DEFAULT NULL COMMENT '汇款人姓名',
  `remitter_account` varchar(255) DEFAULT NULL COMMENT '汇款人账号或开户行名称',
  `pay_bank` varchar(255) DEFAULT NULL COMMENT '到账银行',
  `transfer_form` varchar(32) DEFAULT NULL COMMENT '转账形式',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `province` int(11) DEFAULT NULL COMMENT '省',
  `city` int(11) DEFAULT NULL COMMENT '市',
  `county` int(11) DEFAULT NULL COMMENT '县',
  `advance_pay` tinyint(3) DEFAULT NULL COMMENT '是否提前开票回款 1 是 2 否',
  `project_id` int(11) DEFAULT NULL COMMENT '关联项目ID',
  `company` varchar(255) DEFAULT NULL COMMENT '所属公司',
  `has_invoice` int(1) NOT NULL DEFAULT '2' COMMENT '是否已经开票 1 已开票 2 未开票',
  `note` varchar(255) DEFAULT NULL COMMENT '发起人填写的备注',
  `note_yw` varchar(255) DEFAULT NULL COMMENT '业务人员填写的备注',
  PRIMARY KEY (`id`),
  KEY `index_pid` (`project_id`,`advance_pay`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='到款表';

#
# Structure for table "payment_invoice"
#

CREATE TABLE `payment_invoice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目ID',
  `invoice_id` int(11) DEFAULT NULL COMMENT '申请发票记录ID',
  `payment_id` int(11) DEFAULT NULL COMMENT '到款信息ID',
  `invoice_header` varchar(255) DEFAULT NULL COMMENT '发票抬头',
  `invoice_item` varchar(255) DEFAULT NULL COMMENT '发票项目',
  `unit_price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `count` int(11) DEFAULT NULL COMMENT '数量',
  `invoice_machine` decimal(10,2) DEFAULT NULL COMMENT '机打发票金额',
  `invoice_hundred` decimal(10,2) DEFAULT NULL COMMENT '100定额发票金额',
  `invoice_fifty` decimal(10,2) DEFAULT NULL COMMENT '50定额发票金额',
  `invoice_number` varchar(255) DEFAULT NULL COMMENT '定额发票号',
  `note` varchar(255) DEFAULT NULL COMMENT '备注说明',
  `result` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否已开 0 没开 1 已开（现在是流程结束后就算已开）',
  `invoice_date` datetime DEFAULT NULL COMMENT '开票日期',
  `available` int(1) NOT NULL DEFAULT '0' COMMENT '是否是废弃数据（发票申请被退回的） 0 否 1 是',
  PRIMARY KEY (`id`),
  KEY `index_pinvoice_id` (`invoice_id`),
  KEY `index_pid` (`project_id`,`result`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='到款发票对应表';

#
# Structure for table "process"
#

CREATE TABLE `process` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '流程ID',
  `title` varchar(255) DEFAULT NULL COMMENT '流程标题',
  `project_id` int(11) DEFAULT NULL COMMENT '流程对应的项目ID',
  `type` int(1) DEFAULT NULL COMMENT '流程类型 当前步骤类型 1 审批 2 查阅 3 编辑',
  `create_user_id` int(11) DEFAULT NULL COMMENT '发起人',
  `create_user_name` varchar(64) DEFAULT NULL COMMENT '发起人姓名',
  `start_time` datetime DEFAULT NULL COMMENT '发起时间',
  `table_name` varchar(32) DEFAULT NULL COMMENT '流程对应实体的表名',
  `object_id` int(11) DEFAULT NULL COMMENT '流程对应实体的ID,1:n的情况下此列为空即可',
  `total_time` int(11) DEFAULT NULL COMMENT '流程期限，单位：小时，保留字段',
  `last_step_user_id` int(11) DEFAULT NULL COMMENT '上一个已完成的步骤的处理人',
  `last_step_time` datetime DEFAULT NULL COMMENT '上一个步骤完成的时间',
  `curr_step_user_id` int(11) DEFAULT NULL COMMENT '当前处理人 为0就是结束了',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '流程状态 0 草稿 1 已撤销 2 被退回 3 进行中 4 正常结束',
  PRIMARY KEY (`id`),
  KEY `index_daiban_sx` (`curr_step_user_id`,`status`),
  KEY `index_daifa_sx` (`create_user_id`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程表';

#
# Structure for table "process_history"
#

CREATE TABLE `process_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `process_id` int(11) DEFAULT NULL COMMENT '流程ID',
  `user_id` int(11) DEFAULT NULL COMMENT '操作人',
  `type` tinyint(3) DEFAULT NULL COMMENT '类型 1 审批 2 查阅 3 编辑',
  `deal_result` varchar(32) DEFAULT NULL COMMENT '处理结果',
  `deal_opinion` varchar(255) DEFAULT NULL COMMENT '处理意见',
  `deal_time` datetime DEFAULT NULL COMMENT '处理时间',
  `note` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `index_user_id` (`user_id`),
  KEY `index_process_id` (`process_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程历史（日志）表';

#
# Structure for table "project"
#

CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '提交人',
  `name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `serial_number` varchar(64) DEFAULT NULL COMMENT '项目编号',
  `type` tinyint(3) DEFAULT NULL COMMENT '项目类型 1 国培 2 地陪',
  `format` tinyint(3) DEFAULT NULL COMMENT '培训形式  1 在线 2 面授',
  `provincial` int(11) DEFAULT NULL COMMENT '项目所在省份',
  `city` int(11) DEFAULT NULL COMMENT '项目所在城市',
  `county` int(11) DEFAULT NULL COMMENT '项目所属县城',
  `charge_area` int(11) DEFAULT NULL COMMENT '项目所属分管大区代码 -1 分管一区 -2 分管二区 -3 分管三区',
  `train_object` varchar(64) DEFAULT NULL COMMENT '培训对象',
  `train_platform` varchar(64) DEFAULT NULL COMMENT '培训平台',
  `start_date` datetime DEFAULT NULL COMMENT '培训起始日期',
  `end_date` datetime DEFAULT NULL COMMENT '培训结束日期',
  `expected_num` int(11) DEFAULT NULL COMMENT '预估(报名)人数',
  `charge_standard` decimal(10,2) DEFAULT NULL COMMENT '元/人',
  `study_time` int(11) DEFAULT NULL COMMENT '学时',
  `real_num` int(11) DEFAULT NULL COMMENT '实际上线人数',
  `cooper_name` varchar(255) DEFAULT NULL COMMENT '合作单位名称',
  `cooper_addr` varchar(255) DEFAULT NULL COMMENT '合作单位地址',
  `cooper_head_name_first` varchar(64) DEFAULT NULL COMMENT '合作单位负责人姓名',
  `cooper_head_mobile_first` varchar(64) DEFAULT NULL COMMENT '合作单位负责人电话',
  `cooper_head_job_first` varchar(64) DEFAULT NULL COMMENT '合作单位负责人职务',
  `cooper_head_email_first` varchar(64) DEFAULT NULL COMMENT '合作单位负责人邮箱',
  `cooper_head_name_second` varchar(64) DEFAULT NULL COMMENT '合作单位负责人姓名',
  `cooper_head_mobile_second` varchar(64) DEFAULT NULL COMMENT '合作单位负责人电话',
  `cooper_head_job_second` varchar(64) DEFAULT NULL COMMENT '合作单位负责人职务',
  `cooper_head_email_second` varchar(64) DEFAULT NULL COMMENT '合作单位负责人邮箱',
  `pro_fund_provincial` varchar(32) DEFAULT NULL COMMENT '前期经费省级所占比例',
  `pro_fund_city` varchar(32) DEFAULT NULL COMMENT '前期经费市级所占比例',
  `pro_fund_county` varchar(32) DEFAULT NULL COMMENT '前期经费县级所占比例',
  `pro_fund_other` varchar(32) DEFAULT NULL COMMENT '前期经费其它所占比例',
  `later_fund_provincial` varchar(32) DEFAULT NULL COMMENT '后期经费省级所占比例',
  `later_fund_city` varchar(32) DEFAULT NULL COMMENT '后期经费市级所占比例',
  `later_fund_county` varchar(32) DEFAULT NULL COMMENT '后期经费县级所占比例',
  `later_fund_other` varchar(32) DEFAULT NULL COMMENT '后期经费其他所占比例',
  `protocol_name` varchar(255) DEFAULT NULL COMMENT '项目协议书名称',
  `protocol_time` datetime DEFAULT NULL COMMENT '签署日期',
  `collect_money_company` varchar(255) DEFAULT NULL COMMENT '到款单位名称',
  `collect_money_date` datetime DEFAULT NULL COMMENT '预计到款日期',
  `total_money` decimal(10,2) DEFAULT NULL COMMENT '项目总额',
  `write_time` datetime DEFAULT NULL COMMENT '填表日期',
  `gen_time` datetime DEFAULT NULL COMMENT '提交时间',
  `status` tinyint(3) DEFAULT NULL COMMENT '项目状态 0 审批中或草稿 1 进行中 2 已结束 3 正在走完结流程 4 待完结',
  `url` varchar(255) DEFAULT NULL COMMENT '项目主页URL',
  `note` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `index_user_id` (`user_id`,`status`),
  KEY `index_provincial` (`provincial`,`status`),
  KEY `index_charge_area` (`charge_area`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目信息表';

#
# Structure for table "project_budget"
#

CREATE TABLE `project_budget` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目ID',
  `expert_cost_budget` decimal(10,2) DEFAULT NULL COMMENT '专家费用',
  `expert_cost_based` varchar(255) DEFAULT NULL COMMENT '专家费用计算依据',
  `expert_cost_explain` varchar(255) DEFAULT NULL COMMENT '专家费用说明',
  `transport_cost_budget` decimal(10,2) DEFAULT NULL COMMENT '差旅及交通费用',
  `transport_cost_based` varchar(255) DEFAULT NULL COMMENT '差旅及交通计算依据',
  `transport_cost_explain` varchar(255) DEFAULT NULL COMMENT '差旅及交通费用说明',
  `accom_cost_budget` decimal(10,2) DEFAULT NULL COMMENT '食宿费用',
  `accom_cost_based` varchar(255) DEFAULT NULL COMMENT '食宿计算依据',
  `accom_cost_explain` varchar(255) DEFAULT NULL COMMENT '食宿费用说明',
  `fete_cost_budget` decimal(10,2) DEFAULT NULL COMMENT '招待及礼品费用',
  `fete_cost_based` varchar(255) DEFAULT NULL COMMENT '招待及礼品计算依据',
  `fete_cost_explain` varchar(255) DEFAULT NULL COMMENT '招待及礼品费用说明',
  `office_cost_budget` decimal(10,2) DEFAULT NULL COMMENT '办公及资料费用',
  `office_cost_based` varchar(255) DEFAULT NULL COMMENT '办公及资料计算依据',
  `office_cost_explain` varchar(255) DEFAULT NULL COMMENT '办公及资料费用说明',
  `rental_cost_budget` decimal(10,2) DEFAULT NULL COMMENT '场租费',
  `rental_cost_based` varchar(255) DEFAULT NULL COMMENT '场租费计算依据',
  `rental_cost_explain` varchar(255) DEFAULT NULL COMMENT '场租费说明',
  `invest_cost_budget` decimal(10,2) DEFAULT NULL COMMENT '考察费',
  `invest_cost_based` varchar(255) DEFAULT NULL COMMENT '考察费计算依据',
  `invest_cost_explain` varchar(255) DEFAULT NULL COMMENT '考察费说明',
  `other_cost_budget` decimal(10,2) DEFAULT NULL COMMENT '其它杂费',
  `other_cost_based` varchar(255) DEFAULT NULL COMMENT '其他杂费计算依据',
  `other_cost_explain` varchar(255) DEFAULT NULL COMMENT '其他杂费说明',
  `expert_labour_budget` decimal(10,2) DEFAULT NULL COMMENT '专家劳务',
  `expert_labour_based` varchar(255) DEFAULT NULL COMMENT '专家劳务计算依据',
  `expert_labour_explain` varchar(255) DEFAULT NULL COMMENT '专家劳务说明',
  `counsellor_labour_budget` decimal(10,2) DEFAULT NULL COMMENT '辅导员劳务',
  `counsellor_labour_based` varchar(255) DEFAULT NULL COMMENT '辅导员劳务计算依据',
  `counsellor_labour_explain` varchar(255) DEFAULT NULL COMMENT '辅导员劳务说明',
  PRIMARY KEY (`id`),
  KEY `index_pid` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目预算表';

#
# Structure for table "project_change"
#

CREATE TABLE `project_change` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '提交人',
  `project_id` int(11) DEFAULT NULL COMMENT '项目ID',
  `process_id` int(11) DEFAULT NULL COMMENT '流程ID',
  `column_name` varchar(32) DEFAULT NULL COMMENT '被修改的列名',
  `column_value` varchar(255) DEFAULT NULL COMMENT '修改后的内容',
  `reason` varchar(255) DEFAULT NULL COMMENT '修改原因',
  `note` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='项目信息变更记录表';

#
# Structure for table "project_end"
#

CREATE TABLE `project_end` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '业务人员ID',
  `project_id` int(11) DEFAULT NULL COMMENT '项目ID',
  `real_num` int(11) DEFAULT NULL COMMENT '实际上线人数',
  `process_id` int(11) DEFAULT NULL COMMENT '流程id',
  `real_total_money` decimal(10,2) DEFAULT NULL COMMENT '实际项目总额',
  `real_total_pay` decimal(10,2) DEFAULT NULL COMMENT '实际已到款总额',
  `real_total_invoice` decimal(10,2) DEFAULT NULL COMMENT '实际已开票金额',
  `charge_count` int(11) DEFAULT NULL COMMENT '收费人数（业务人员填写）',
  `later_pay` decimal(10,2) DEFAULT NULL COMMENT '后期回款总额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `note` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目完结信息表';

#
# Structure for table "role"
#

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(64) NOT NULL DEFAULT '' COMMENT '角色名称',
  `role_type` char(1) DEFAULT NULL COMMENT '角色类型，保留字段',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父级角色id',
  `gen_time` datetime DEFAULT NULL COMMENT '创建时间',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

#
# Structure for table "role_menu"
#

CREATE TABLE `role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单ID',
  `pm_type` int(2) DEFAULT NULL COMMENT '权限类型',
  PRIMARY KEY (`id`),
  KEY `index_menu_id` (`menu_id`),
  KEY `index_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单（权限）表';

#
# Structure for table "serialnum"
#

CREATE TABLE `serialnum` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serial_num` varchar(32) DEFAULT NULL COMMENT '已经生成过的项目编码',
  `gen_time` datetime DEFAULT NULL COMMENT '生成日期',
  PRIMARY KEY (`id`),
  KEY `index_serial_num` (`serial_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目编码记录表';

#
# Structure for table "shortcut"
#

CREATE TABLE `shortcut` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`),
  KEY `index_uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='快捷方式表';

#
# Structure for table "system_log"
#

CREATE TABLE `system_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(1) DEFAULT NULL COMMENT '类型 0 select 1 save 2 update 3 detele',
  `content` text COMMENT '内容',
  `uid` int(11) DEFAULT NULL COMMENT '操作人',
  `time` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志表';

#
# Structure for table "user"
#

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
  `dm_id` int(11) DEFAULT NULL COMMENT '部门ID,废弃字段，用user_department表保存用户部门信息',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `realname` varchar(64) DEFAULT NULL COMMENT '用户姓名',
  `sex` char(1) DEFAULT NULL COMMENT '1 男 2 女',
  `idcard` varchar(64) DEFAULT NULL COMMENT '身份证号',
  `mobile` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号码',
  `email` varchar(64) DEFAULT NULL COMMENT '电子邮箱',
  `pic` varchar(128) DEFAULT NULL COMMENT '用户头像图片地址',
  `note` varchar(255) DEFAULT NULL COMMENT '备注',
  `gen_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '最近一次的修改时间',
  `available` int(1) NOT NULL DEFAULT '0' COMMENT '是否已被禁用 0 正常 1 已禁用',
  `delete_time` datetime DEFAULT NULL COMMENT '禁用时间',
  PRIMARY KEY (`id`),
  KEY `index_login_name` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

#
# Structure for table "user_department"
#

CREATE TABLE `user_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `dm_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `note` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `index_user_id` (`user_id`),
  KEY `index_dm_id` (`dm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-部门对应关系表';

﻿#
# Structure for table "project_summary"
#

CREATE TABLE `project_summary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目ID',
  `company` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `type` tinyint(3) DEFAULT NULL COMMENT '项目类型 1 国培 2 地陪',
  `year` int(11) DEFAULT NULL COMMENT '年份',
  `province` int(11) DEFAULT NULL COMMENT '省份',
  `serial_number` varchar(64) DEFAULT NULL COMMENT '项目编号',
  `name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `cooper_name` varchar(255) DEFAULT NULL COMMENT '合作单位名称',
  `start_date` datetime DEFAULT NULL COMMENT '培训起始日期',
  `end_date` datetime DEFAULT NULL COMMENT '培训结束日期',
  `course_length` int(11) DEFAULT NULL COMMENT '培训期限（月）',
  `charge_standard` decimal(10,2) DEFAULT NULL COMMENT '培训单价（元/人）',
  `expected_num` int(11) DEFAULT NULL COMMENT '报名人数',
  `real_num` int(11) DEFAULT NULL COMMENT '实际上线人数',
  `predict_amount` decimal(10,2) DEFAULT NULL COMMENT '预计项目总额（单价*上线人数）',
  `practical_amount` decimal(10,2) DEFAULT NULL COMMENT '实际项目总额',
  `protocol` tinyint(3) DEFAULT NULL COMMENT '是否有协议 1 有 2 无',
  `payment_twelve` decimal(10,2) DEFAULT NULL COMMENT '12年到款',
  `payment_thirteen` decimal(10,2) DEFAULT NULL COMMENT '13年到款',
  `payment_fourteen` decimal(10,2) DEFAULT NULL COMMENT '14年到款',
  `payment_fifteen` decimal(10,2) DEFAULT NULL COMMENT '15年到款',
  `payment_sixteen` decimal(10,2) DEFAULT NULL COMMENT '16年到款',
  `payment_seventeen` decimal(10,2) DEFAULT NULL COMMENT '17年到款',
  `payment_eighteen` decimal(10,2) DEFAULT NULL COMMENT '18年到款',
  `payment_nineteen` decimal(10,2) DEFAULT NULL COMMENT '19年到款',
  `payment_twenty` decimal(10,2) DEFAULT NULL COMMENT '20年到款',
  `payment_amount` decimal(10,2) DEFAULT NULL COMMENT '到款合计',
  `predict_later_payment` decimal(10,2) DEFAULT NULL COMMENT '预计后期到款',
  `real_receive_amount` decimal(10,2) DEFAULT NULL COMMENT '应收账款',
  `income_twelve` decimal(10,2) DEFAULT NULL COMMENT '12年累计收入',
  `income_thirteen` decimal(10,2) DEFAULT NULL COMMENT '13年累计收入',
  `income_fourteen` decimal(10,2) DEFAULT NULL COMMENT '14年累计收入',
  `income_fifteen` decimal(10,2) DEFAULT NULL COMMENT '15年累计收入',
  `income_sixteen` decimal(10,2) DEFAULT NULL COMMENT '16年累计收入',
  `income_seventeen` decimal(10,2) DEFAULT NULL COMMENT '17年累计收入',
  `income_eighteen` decimal(10,2) DEFAULT NULL COMMENT '18年累计收入',
  `income_nineteen` decimal(10,2) DEFAULT NULL COMMENT '19年累计收入',
  `income_twenty` decimal(10,2) DEFAULT NULL COMMENT '20年累计收入',
  `income_amount` decimal(10,2) DEFAULT NULL COMMENT '收入合计',
  `pro_fund` varchar(32) DEFAULT NULL COMMENT '经费比例-前期',
  `later_fund` varchar(32) DEFAULT NULL COMMENT '经费比例-后期',
  `pro_fund_amount` decimal(10,2) DEFAULT NULL COMMENT '应付经费总额-前期',
  `later_fund_amount` decimal(10,2) DEFAULT NULL COMMENT '应付经费总额-后期',
  `pay_twelve` decimal(10,2) DEFAULT NULL COMMENT '12年已支付经费金额',
  `pay_thirteen` decimal(10,2) DEFAULT NULL COMMENT '13年已支付经费金额',
  `pay_fourteen` decimal(10,2) DEFAULT NULL COMMENT '14年已支付经费金额',
  `pay_fifteen` decimal(10,2) DEFAULT NULL COMMENT '15年已支付经费金额',
  `pay_sixteen` decimal(10,2) DEFAULT NULL COMMENT '16年已支付经费金额',
  `pay_seventeen` decimal(10,2) DEFAULT NULL COMMENT '17年已支付经费金额',
  `pay_eighteen` decimal(10,2) DEFAULT NULL COMMENT '18年已支付经费金额',
  `pay_nineteen` decimal(10,2) DEFAULT NULL COMMENT '19年已支付经费金额',
  `pay_twenty` decimal(10,2) DEFAULT NULL COMMENT '20年已支付经费金额',
  `pay_amount` decimal(10,2) DEFAULT NULL COMMENT '支付经费合计',
  `no_pay_amount` decimal(10,2) DEFAULT NULL COMMENT '尚未支付经费金额',
  `project_cost_twelve` decimal(10,2) DEFAULT NULL COMMENT '12年项目运营成本',
  `project_cost_thirteen` decimal(10,2) DEFAULT NULL COMMENT '13年项目运营成本',
  `project_cost_fourteen` decimal(10,2) DEFAULT NULL COMMENT '14年项目运营成本',
  `project_cost_fifteen` decimal(10,2) DEFAULT NULL COMMENT '15年项目运营成本',
  `project_cost_sixteen` decimal(10,2) DEFAULT NULL COMMENT '16年项目运营成本',
  `project_cost_seventeen` decimal(10,2) DEFAULT NULL COMMENT '17年项目运营成本',
  `project_cost_eighteen` decimal(10,2) DEFAULT NULL COMMENT '18年项目运营成本',
  `project_cost_nineteen` decimal(10,2) DEFAULT NULL COMMENT '19年项目运营成本',
  `project_cost_twenty` decimal(10,2) DEFAULT NULL COMMENT '20年项目运营成本',
  `project_cost_amount` decimal(10,2) DEFAULT NULL COMMENT '项目运营成本合计',
  `carryover_cost_twelve` decimal(10,2) DEFAULT NULL COMMENT '12年结转成本',
  `carryover_cost_thirteen` decimal(10,2) DEFAULT NULL COMMENT '13年结转成本',
  `carryover_cost_fourteen` decimal(10,2) DEFAULT NULL COMMENT '14年结转成本',
  `carryover_cost_fifteen` decimal(10,2) DEFAULT NULL COMMENT '15年结转成本',
  `carryover_cost_sixteen` decimal(10,2) DEFAULT NULL COMMENT '16年结转成本',
  `carryover_cost_seventeen` decimal(10,2) DEFAULT NULL COMMENT '17年结转成本',
  `carryover_cost_eighteen` decimal(10,2) DEFAULT NULL COMMENT '18年结转成本',
  `carryover_cost_nineteen` decimal(10,2) DEFAULT NULL COMMENT '19年结转成本',
  `carryover_cost_twenty` decimal(10,2) DEFAULT NULL COMMENT '20年结转成本',
  `carryover_cost_amount` decimal(10,2) DEFAULT NULL COMMENT '结转成本合计',
  `invoice_twelve` decimal(10,2) DEFAULT NULL COMMENT '12年开票金额',
  `invoice_thirteen` decimal(10,2) DEFAULT NULL COMMENT '13年开票金额',
  `invoice_fourteen` decimal(10,2) DEFAULT NULL COMMENT '14年开票金额',
  `invoice_fifteen` decimal(10,2) DEFAULT NULL COMMENT '15年开票金额',
  `invoice_sixteen` decimal(10,2) DEFAULT NULL COMMENT '16年开票金额',
  `invoice_seventeen` decimal(10,2) DEFAULT NULL COMMENT '17年开票金额',
  `invoice_eighteen` decimal(10,2) DEFAULT NULL COMMENT '18年开票金额',
  `invoice_nineteen` decimal(10,2) DEFAULT NULL COMMENT '19年开票金额',
  `invoice_twenty` decimal(10,2) DEFAULT NULL COMMENT '20年开票金额',
  `invoice_amount` decimal(10,2) DEFAULT NULL COMMENT '开票金额合计',
  `no_invoice_amount` decimal(10,2) DEFAULT NULL COMMENT '未开发票金额',
  `fund_pay_error` decimal(10,2) DEFAULT NULL COMMENT '经费支付异常提示（已到款应付经费-已付经费）',
  `note` varchar(255) DEFAULT NULL COMMENT '说明',
  `url` varchar(255) DEFAULT NULL COMMENT '项目主页URL',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目汇总表';

﻿#
# Structure for table "company"
#
CREATE TABLE `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '公司名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='公司信息表';


INSERT INTO `user` (`login_name`,`password`,`dm_id`,`role_id`,`realname`,`sex`,`idcard`,`mobile`,`email`,`pic`,`note`,`gen_time`,`update_time`,`available`,`delete_time`) VALUES ('admin','Qpf0SxOVUjUkWySXOZ16kw==',NULL,NULL,'管理员','2','130132198903036754','13312345678','pms@teacheredu.cn',NULL,'后台管理者','2017-02-28 17:25:19','2017-03-20 17:54:48',0,NULL);