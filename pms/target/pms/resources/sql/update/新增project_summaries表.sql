-- 项目费用起止日期记录表
CREATE table project_summaries(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `project_company` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `project_type` tinyint(3) DEFAULT NULL COMMENT '项目类型 1 国培 2 地陪',
  `project_year` int(11) DEFAULT NULL COMMENT '项目年份',
  `project_provincial_id` int(11) DEFAULT NULL COMMENT '项目所在省份',
  `project_provincial` varchar(255) DEFAULT NULL COMMENT '项目所在省份',
  `project_serial_number` varchar(64) DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `project_cooper_name` varchar(255) DEFAULT NULL COMMENT '合作单位名称',
  `project_start_date` datetime DEFAULT NULL COMMENT '培训起始日期',
  `project_end_date` datetime DEFAULT NULL COMMENT '培训结束日期',
  `project_train_period` int(11) DEFAULT NULL COMMENT '培训期限(月,满15天算一个月)',
  `project_charge` decimal(10,2) DEFAULT NULL COMMENT '元/人',
  `project_expected_num` int(11) DEFAULT NULL COMMENT '报名人数',
  `project_live_num` int(11) DEFAULT NULL COMMENT '预估人数',
  `predict_total_amount` decimal(10,2) DEFAULT NULL COMMENT '预计项目总额', --  单价*报名人数
  `project_real_total_amount` decimal(10,2) DEFAULT NULL COMMENT '实际项目总额',-- a=（预计项目总额-前期经费）b=到款金额 a >b ？ a  ： b	（字段计算公式，问题是否兼容系统中项目完结表的实际项目总额字段计算）
  `project_status` tinyint(3) DEFAULT NULL COMMENT '项目状态 0 审批中或草稿 1 进行中 2 已结束 3 正在走完结流程 4 待完结',
  `project_protocol_name` varchar(255) DEFAULT NULL COMMENT '项目协议书名称',
  `real_payment_amount` decimal(10,2) DEFAULT NULL COMMENT '项目实际到款合计',
  `predict_later_payment` decimal(10,2) DEFAULT NULL COMMENT '预计后期到款金额',-- 报名人数*单价-前期经费-已到款金额 	（同预计项目总额的问题）
  `predict_receive_amount` decimal(10,2) DEFAULT NULL COMMENT '应收账款',-- 已结转收入金额-已到款金额
  `real_income_amount` decimal(10,2) DEFAULT NULL COMMENT '已转收入合计',
  `pro_fund_proportion` decimal(10,2) DEFAULT NULL COMMENT '前期经费比例',
  `later_fund_proportion` decimal(10,2) DEFAULT NULL COMMENT '后期经费比例',
  `pro_fund_amount` decimal(10,2) DEFAULT NULL COMMENT '前期应付经费总额',-- 预计项目总额*前期经费比例
  `later_fund_amount` decimal(10,2) DEFAULT NULL COMMENT '后期应付经费总额',-- 预计项目总额*后期经费比例
  `real_fund_amount` decimal(10,2) DEFAULT NULL COMMENT '已支付经费合计',
  `nopay_fund_amount` decimal(10,2) DEFAULT NULL COMMENT '尚未支付经费金额',-- 后期经费比例-已支付经费合计
  `predict_budget` decimal(10,2) DEFAULT NULL COMMENT '运营成本预算合计',
  `real_budget_amount` decimal(10,2) DEFAULT NULL COMMENT '运营成本合计',
  `real_cost_amount` decimal(10,2) DEFAULT NULL COMMENT '结转成本合计',
  `real_invoice_amount` decimal(10,2) DEFAULT NULL COMMENT '开票金额合计',
  `no_invoice_amount` decimal(10,2) DEFAULT NULL COMMENT '未开发票金额',-- 总到款-已开发票合计
  `fund_error` decimal(10,2) DEFAULT NULL COMMENT '经费支付异常提示（已到款应付经费-已付经费）',-- 已到款合计金额*后期经费比例-已支付经费金额
  `url` varchar(255) DEFAULT NULL COMMENT '项目主页URL',
  `note` varchar(255) DEFAULT NULL COMMENT '备注',
  
  -- 其他字段是查询条件
  `project_format` tinyint(3) DEFAULT NULL COMMENT '培训形式  1 在线 2 面授',
  `project_charge_area` int(11) DEFAULT NULL COMMENT '项目所属分管大区代码 -1 分管一区 -2 分管二区 -3 分管三区',
  -- 根据费用的时间，来查询项目
  `income_first_time` datetime DEFAULT NULL COMMENT '第一笔收入时间',	-- √
  `income_last_time` datetime DEFAULT NULL COMMENT '最后一笔收入时间',	-- √
  `over_first_time` datetime DEFAULT NULL COMMENT '第一笔结转成本时间',
  `over_last_time` datetime DEFAULT NULL COMMENT '最后一笔结转成本时间',
  `payment_first_time` datetime DEFAULT NULL COMMENT '第一笔到款时间', 	-- √
  `payment_last_time` datetime DEFAULT NULL COMMENT '最后一笔到款时间',	-- √
  `funds_first_time` datetime DEFAULT NULL COMMENT '第一笔经费时间',	-- √
  `funds_last_time` datetime DEFAULT NULL COMMENT '最后一笔经费时间',	-- √
  `invoice_first_time` datetime DEFAULT NULL COMMENT '第一笔开票时间',
  `invoice_last_time` datetime DEFAULT NULL COMMENT '最后一笔开票时间',
  `budget_first_time` datetime DEFAULT NULL COMMENT '第一笔运营成本申请时间',
  `budget_last_time` datetime DEFAULT NULL COMMENT '最后一笔运营成本申请',
  
  `summary_first_time` datetime DEFAULT NULL COMMENT '统计时间',
  `summary_last_time` datetime DEFAULT NULL COMMENT '最后一次修改时间',
  
  PRIMARY KEY (`id`),
  KEY `index_projectId` (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='项目汇总表';

