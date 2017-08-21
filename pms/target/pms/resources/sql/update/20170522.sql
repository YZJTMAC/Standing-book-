-- 导入旧项目数据时,青海的两个项目没有算出所属大区代码,现在手动设置一下

update project set charge_area = -2 where charge_area is null;

-- project表添加第三方合作单位字段,同时修改部分字段表示意义
ALTER TABLE project MODIFY COLUMN expected_num int(11) DEFAULT NULL comment '报名人数';
ALTER TABLE project MODIFY COLUMN real_num int(11) DEFAULT NULL comment '预估人数';
ALTER TABLE project ADD COLUMN third_cooper_name varchar(255) DEFAULT NULL COMMENT '第三方合作单位' AFTER cooper_name;


-- project_end表添加应缴费人数字段,同时修改later_pay注释
ALTER TABLE project_end MODIFY COLUMN later_pay decimal(10,2) DEFAULT NULL COMMENT '应收款金额';
ALTER TABLE project_end ADD COLUMN pay_count int(11) DEFAULT NULL COMMENT '应缴费人数(业务人员填写)' AFTER charge_count;


-- 流程定义表
CREATE TABLE `process_define` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `define_name` varchar(64) DEFAULT NULL COMMENT '流程定义名称',
  `table_name` varchar(32) DEFAULT NULL COMMENT '流程对应实体的表名',
  `limit_time` int(11) DEFAULT NULL COMMENT '流程期限，单位：小时，保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程定义表';

INSERT INTO `process_define` VALUES ('1', '项目确认表', 'project', null); INSERT INTO `process_define` VALUES ('3', '项目信息变更表', 'projectChange', null); INSERT INTO `process_define` VALUES ('5', '项目发票申请表', 'invoice', null); INSERT INTO `process_define` VALUES ('7', '项目经费申请表', 'funds', null); INSERT INTO `process_define` VALUES ('9', '项目完结单', 'project_end', null); INSERT INTO `process_define` VALUES ('11', '项目到款表', 'payment', null); 

-- 流程步骤表
CREATE TABLE `process_step` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `define_id` int(11) DEFAULT NULL COMMENT '流程定义ID',
  `step_name` varchar(64) DEFAULT NULL COMMENT '步骤名称',
  `dm_id` int(11) DEFAULT NULL COMMENT '当前步骤对应部门',
  `user_id` int(11) DEFAULT NULL COMMENT '当前步骤对应部门处理人',
  `next_step_id` int(11) DEFAULT NULL COMMENT '下一个步骤',
  `type` int(1) DEFAULT NULL COMMENT '当前步骤类型 1审批  2查阅  3编辑',
  `begin_status` varchar(1) DEFAULT NULL COMMENT '步骤开始标识  1起始节点',
  `end_status` varchar(1) DEFAULT NULL COMMENT '步骤结束标识  1结束节点',
  `step_note` int(1) DEFAULT NULL COMMENT '当前步骤说明    1办事处、分公司、子公司处理人    2分管部门处理人     3商务部处理人     4总经理办公室处理人    5教务部处理人   6财务部处理人',
  PRIMARY KEY (`id`),
  KEY `index_define_id` (`define_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程步骤表';

INSERT INTO `process_step` VALUES ('1', '1', '总部财务部', '1', '57', null, null, null, null, null); INSERT INTO `process_step` VALUES ('3', '1', '研发部', '3', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('5', '1', '教务教学部', '13', '56', null, '3', null, '1', '5'); INSERT INTO `process_step` VALUES ('7', '1', '商务部', '15', '55', '9', '1', null, null, '3'); INSERT INTO `process_step` VALUES ('9', '1', '总经理办公室', '17', '52', '5', '1', null, null, '4'); INSERT INTO `process_step` VALUES ('11', '1', '分管一部', '19', '52', '7', '1', null, null, '2'); INSERT INTO `process_step` VALUES ('13', '1', '分管二部', '21', '53', '7', '1', null, null, '2'); INSERT INTO `process_step` VALUES ('15', '1', '分管三部', '23', '54', '7', '1', null, null, '2'); INSERT INTO `process_step` VALUES ('17', '1', '广东分公司', '25', '2', '11', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('19', '1', '广西办事处', '27', '4', '11', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('21', '1', '上海中心', '29', '6', '13', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('23', '1', '黑龙江办事处', '31', '7', '13', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('25', '1', '江西分公司', '33', '9', '15', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('27', '1', '安徽分公司', '35', '11', '15', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('29', '1', '江苏办事处', '39', '13', '11', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('31', '1', '西藏办事处', '41', '13', '11', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('33', '1', '山东办事处', '43', '15', '11', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('35', '1', '海南办事处', '45', '17', '11', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('37', '1', '辽宁办事处', '47', '19', '11', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('39', '1', '河北办事处', '49', '21', '11', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('41', '1', '云南办事处', '51', '13', '11', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('43', '1', '福建办事处', '53', '22', '11', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('45', '1', '北京办事处', '55', '24', '11', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('47', '1', '吉林办事处', '57', '26', '11', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('49', '1', '贵州办事处', '59', '28', '11', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('51', '1', '四川子公司', '61', '30', '11', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('53', '1', '河南分公司', '63', '32', '11', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('55', '1', '陕西分公司', '65', '34', '13', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('57', '1', '甘肃办事处', '67', '34', '13', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('59', '1', '重庆分公司', '69', '37', '13', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('61', '1', '天津办事处', '71', '39', '13', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('63', '1', '湖南分公司', '73', '41', '13', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('65', '1', '宁夏办事处', '75', '42', '13', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('67', '1', '浙江分公司', '77', '44', '13', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('69', '1', '湖北分公司', '81', '46', '15', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('71', '1', '新疆自治区 新疆兵团', '83', '46', '15', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('73', '1', '内蒙办事处', '85', '48', '15', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('75', '1', '山西办事处', '87', '50', '15', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('77', '1', '青海办事处', '89', '42', '13', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('79', '1', '教科文化管理部门', '90', '71', null, null, null, null, null); INSERT INTO `process_step` VALUES ('81', '1', '安大国际管理部门', '91', '72', null, null, null, null, null); INSERT INTO `process_step` VALUES ('83', '1', '研究院管理部门', '92', '73', null, null, null, null, null); INSERT INTO `process_step` VALUES ('85', '1', '教培师训财务部', '93', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('87', '1', '泰学新心财务部', '94', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('89', '1', '河南财务部', '95', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('91', '1', '四川财务部', '96', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('93', '1', '教科文化财务部', '97', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('95', '1', '大视野控股财务部', '98', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('97', '1', '东方汇通财务部', '99', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('99', '1', '安大国际财务部', '100', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('101', '1', '研究院财务部', '101', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('127', '3', '总部财务部', '1', '57', null, null, null, null, null); INSERT INTO `process_step` VALUES ('129', '3', '研发部', '3', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('131', '3', '教务教学部', '13', '56', null, null, null, null, null); INSERT INTO `process_step` VALUES ('133', '3', '商务部', '15', '55', '135', '1', null, null, '3'); INSERT INTO `process_step` VALUES ('135', '3', '总经理办公室', '17', '52', null, '1', null, '1', '4'); INSERT INTO `process_step` VALUES ('137', '3', '分管一部', '19', '52', '133', '1', null, null, '2'); INSERT INTO `process_step` VALUES ('139', '3', '分管二部', '21', '53', '133', '1', null, null, '2'); INSERT INTO `process_step` VALUES ('141', '3', '分管三部', '23', '54', '133', '1', null, null, '2'); INSERT INTO `process_step` VALUES ('143', '3', '广东分公司', '25', '2', '137', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('145', '3', '广西办事处', '27', '4', '137', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('147', '3', '上海中心', '29', '6', '139', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('149', '3', '黑龙江办事处', '31', '7', '139', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('151', '3', '江西分公司', '33', '9', '141', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('153', '3', '安徽分公司', '35', '11', '141', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('155', '3', '江苏办事处', '39', '13', '137', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('157', '3', '西藏办事处', '41', '13', '137', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('159', '3', '山东办事处', '43', '15', '137', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('161', '3', '海南办事处', '45', '17', '137', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('163', '3', '辽宁办事处', '47', '19', '137', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('165', '3', '河北办事处', '49', '21', '137', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('167', '3', '云南办事处', '51', '13', '137', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('169', '3', '福建办事处', '53', '22', '137', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('171', '3', '北京办事处', '55', '24', '137', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('173', '3', '吉林办事处', '57', '26', '137', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('175', '3', '贵州办事处', '59', '28', '137', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('177', '3', '四川子公司', '61', '30', '137', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('179', '3', '河南分公司', '63', '32', '137', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('181', '3', '陕西分公司', '65', '34', '139', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('183', '3', '甘肃办事处', '67', '34', '139', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('185', '3', '重庆分公司', '69', '37', '139', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('187', '3', '天津办事处', '71', '39', '139', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('189', '3', '湖南分公司', '73', '41', '139', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('191', '3', '宁夏办事处', '75', '42', '139', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('193', '3', '浙江分公司', '77', '44', '139', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('195', '3', '湖北分公司', '81', '46', '141', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('197', '3', '新疆自治区 新疆兵团', '83', '46', '141', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('199', '3', '内蒙办事处', '85', '48', '141', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('201', '3', '山西办事处', '87', '50', '141', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('203', '3', '青海办事处', '89', '42', '139', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('205', '3', '教科文化管理部门', '90', '71', null, null, null, null, null); INSERT INTO `process_step` VALUES ('207', '3', '安大国际管理部门', '91', '72', null, null, null, null, null); INSERT INTO `process_step` VALUES ('209', '3', '研究院管理部门', '92', '73', null, null, null, null, null); INSERT INTO `process_step` VALUES ('211', '3', '教培师训财务部', '93', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('213', '3', '泰学新心财务部', '94', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('215', '3', '河南财务部', '95', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('217', '3', '四川财务部', '96', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('219', '3', '教科文化财务部', '97', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('221', '3', '大视野控股财务部', '98', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('223', '3', '东方汇通财务部', '99', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('225', '3', '安大国际财务部', '100', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('227', '3', '研究院财务部', '101', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('253', '5', '总部财务部', '1', '57', null, null, null, null, null); INSERT INTO `process_step` VALUES ('255', '5', '研发部', '3', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('257', '5', '教务教学部', '13', '56', null, null, null, null, null); INSERT INTO `process_step` VALUES ('259', '5', '商务部', '15', '55', '261', '1', null, null, '3'); INSERT INTO `process_step` VALUES ('261', '5', '总经理办公室', '17', '52', '337', '1', null, null, '4'); INSERT INTO `process_step` VALUES ('263', '5', '分管一部', '19', '52', '259', '1', null, null, '2'); INSERT INTO `process_step` VALUES ('265', '5', '分管二部', '21', '53', '259', '1', null, null, '2'); INSERT INTO `process_step` VALUES ('267', '5', '分管三部', '23', '54', '259', '1', null, null, '2'); INSERT INTO `process_step` VALUES ('269', '5', '广东分公司', '25', '2', '263', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('271', '5', '广西办事处', '27', '4', '263', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('273', '5', '上海中心', '29', '6', '265', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('275', '5', '黑龙江办事处', '31', '7', '265', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('277', '5', '江西分公司', '33', '9', '267', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('279', '5', '安徽分公司', '35', '11', '267', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('281', '5', '江苏办事处', '39', '13', '263', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('283', '5', '西藏办事处', '41', '13', '263', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('285', '5', '山东办事处', '43', '15', '263', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('287', '5', '海南办事处', '45', '17', '263', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('289', '5', '辽宁办事处', '47', '19', '263', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('291', '5', '河北办事处', '49', '21', '263', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('293', '5', '云南办事处', '51', '13', '263', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('295', '5', '福建办事处', '53', '22', '263', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('297', '5', '北京办事处', '55', '24', '263', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('299', '5', '吉林办事处', '57', '26', '263', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('301', '5', '贵州办事处', '59', '28', '263', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('303', '5', '四川子公司', '61', '30', '263', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('305', '5', '河南分公司', '63', '32', '263', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('307', '5', '陕西分公司', '65', '34', '265', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('309', '5', '甘肃办事处', '67', '34', '265', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('311', '5', '重庆分公司', '69', '37', '265', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('313', '5', '天津办事处', '71', '39', '265', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('315', '5', '湖南分公司', '73', '41', '265', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('317', '5', '宁夏办事处', '75', '42', '265', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('319', '5', '浙江分公司', '77', '44', '265', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('321', '5', '湖北分公司', '81', '46', '267', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('323', '5', '新疆自治区 新疆兵团', '83', '46', '267', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('325', '5', '内蒙办事处', '85', '48', '267', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('327', '5', '山西办事处', '87', '50', '267', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('329', '5', '青海办事处', '89', '42', '265', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('331', '5', '教科文化管理部门', '90', '71', null, null, null, null, null); INSERT INTO `process_step` VALUES ('333', '5', '安大国际管理部门', '91', '72', null, null, null, null, null); INSERT INTO `process_step` VALUES ('335', '5', '研究院管理部门', '92', '73', null, null, null, null, null); INSERT INTO `process_step` VALUES ('337', '5', '教培师训财务部', '93', '64', null, '2', null, '1', '6'); INSERT INTO `process_step` VALUES ('339', '5', '泰学新心财务部', '94', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('341', '5', '河南财务部', '95', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('343', '5', '四川财务部', '96', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('345', '5', '教科文化财务部', '97', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('347', '5', '大视野控股财务部', '98', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('349', '5', '东方汇通财务部', '99', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('351', '5', '安大国际财务部', '100', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('353', '5', '研究院财务部', '101', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('379', '7', '总部财务部', '1', '57', null, null, null, null, null); INSERT INTO `process_step` VALUES ('381', '7', '研发部', '3', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('383', '7', '教务教学部', '13', '56', null, null, null, null, null); INSERT INTO `process_step` VALUES ('385', '7', '商务部', '15', '55', '387', '1', null, null, '3'); INSERT INTO `process_step` VALUES ('387', '7', '总经理办公室', '17', '52', null, '1', null, '1', '4'); INSERT INTO `process_step` VALUES ('389', '7', '分管一部', '19', '52', '385', '1', null, null, '2'); INSERT INTO `process_step` VALUES ('391', '7', '分管二部', '21', '53', '385', '1', null, null, '2'); INSERT INTO `process_step` VALUES ('393', '7', '分管三部', '23', '54', '385', '1', null, null, '2'); INSERT INTO `process_step` VALUES ('395', '7', '广东分公司', '25', '2', '389', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('397', '7', '广西办事处', '27', '4', '389', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('399', '7', '上海中心', '29', '6', '391', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('401', '7', '黑龙江办事处', '31', '7', '391', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('403', '7', '江西分公司', '33', '9', '393', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('405', '7', '安徽分公司', '35', '11', '393', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('407', '7', '江苏办事处', '39', '13', '389', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('409', '7', '西藏办事处', '41', '13', '389', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('411', '7', '山东办事处', '43', '15', '389', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('413', '7', '海南办事处', '45', '17', '389', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('415', '7', '辽宁办事处', '47', '19', '389', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('417', '7', '河北办事处', '49', '21', '389', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('419', '7', '云南办事处', '51', '13', '389', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('421', '7', '福建办事处', '53', '22', '389', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('423', '7', '北京办事处', '55', '24', '389', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('425', '7', '吉林办事处', '57', '26', '389', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('427', '7', '贵州办事处', '59', '28', '389', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('429', '7', '四川子公司', '61', '30', '389', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('431', '7', '河南分公司', '63', '32', '389', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('433', '7', '陕西分公司', '65', '34', '391', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('435', '7', '甘肃办事处', '67', '34', '391', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('437', '7', '重庆分公司', '69', '37', '391', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('439', '7', '天津办事处', '71', '39', '391', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('441', '7', '湖南分公司', '73', '41', '391', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('443', '7', '宁夏办事处', '75', '42', '391', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('445', '7', '浙江分公司', '77', '44', '391', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('447', '7', '湖北分公司', '81', '46', '393', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('449', '7', '新疆自治区 新疆兵团', '83', '46', '393', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('451', '7', '内蒙办事处', '85', '48', '393', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('453', '7', '山西办事处', '87', '50', '393', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('455', '7', '青海办事处', '89', '42', '391', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('457', '7', '教科文化管理部门', '90', '71', null, null, null, null, null); INSERT INTO `process_step` VALUES ('459', '7', '安大国际管理部门', '91', '72', null, null, null, null, null); INSERT INTO `process_step` VALUES ('461', '7', '研究院管理部门', '92', '73', null, null, null, null, null); INSERT INTO `process_step` VALUES ('463', '7', '教培师训财务部', '93', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('465', '7', '泰学新心财务部', '94', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('467', '7', '河南财务部', '95', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('469', '7', '四川财务部', '96', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('471', '7', '教科文化财务部', '97', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('473', '7', '大视野控股财务部', '98', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('475', '7', '东方汇通财务部', '99', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('477', '7', '安大国际财务部', '100', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('479', '7', '研究院财务部', '101', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('505', '9', '总部财务部', '1', '57', null, null, null, null, null); INSERT INTO `process_step` VALUES ('507', '9', '研发部', '3', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('509', '9', '教务教学部', '13', '56', null, null, null, null, null); INSERT INTO `process_step` VALUES ('511', '9', '商务部', '15', '55', '513', '1', null, null, '3'); INSERT INTO `process_step` VALUES ('513', '9', '总经理办公室', '17', '52', null, '1', null, '1', '4'); INSERT INTO `process_step` VALUES ('515', '9', '分管一部', '19', '52', '511', '1', null, null, '2'); INSERT INTO `process_step` VALUES ('517', '9', '分管二部', '21', '53', '511', '1', null, null, '2'); INSERT INTO `process_step` VALUES ('519', '9', '分管三部', '23', '54', '511', '1', null, null, '2'); INSERT INTO `process_step` VALUES ('521', '9', '广东分公司', '25', '2', '515', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('523', '9', '广西办事处', '27', '4', '515', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('525', '9', '上海中心', '29', '6', '517', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('527', '9', '黑龙江办事处', '31', '7', '517', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('529', '9', '江西分公司', '33', '9', '519', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('531', '9', '安徽分公司', '35', '11', '519', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('533', '9', '江苏办事处', '39', '13', '515', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('535', '9', '西藏办事处', '41', '13', '515', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('537', '9', '山东办事处', '43', '15', '515', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('539', '9', '海南办事处', '45', '17', '515', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('541', '9', '辽宁办事处', '47', '19', '515', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('543', '9', '河北办事处', '49', '21', '515', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('545', '9', '云南办事处', '51', '13', '515', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('547', '9', '福建办事处', '53', '22', '515', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('549', '9', '北京办事处', '55', '24', '515', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('551', '9', '吉林办事处', '57', '26', '515', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('553', '9', '贵州办事处', '59', '28', '515', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('555', '9', '四川子公司', '61', '30', '515', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('557', '9', '河南分公司', '63', '32', '515', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('559', '9', '陕西分公司', '65', '34', '517', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('561', '9', '甘肃办事处', '67', '34', '517', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('563', '9', '重庆分公司', '69', '37', '517', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('565', '9', '天津办事处', '71', '39', '517', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('567', '9', '湖南分公司', '73', '41', '517', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('569', '9', '宁夏办事处', '75', '42', '517', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('571', '9', '浙江分公司', '77', '44', '517', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('573', '9', '湖北分公司', '81', '46', '519', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('575', '9', '新疆自治区 新疆兵团', '83', '46', '519', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('577', '9', '内蒙办事处', '85', '48', '519', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('579', '9', '山西办事处', '87', '50', '519', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('581', '9', '青海办事处', '89', '42', '517', '1', null, null, '1'); INSERT INTO `process_step` VALUES ('583', '9', '教科文化管理部门', '90', '71', null, null, null, null, null); INSERT INTO `process_step` VALUES ('585', '9', '安大国际管理部门', '91', '72', null, null, null, null, null); INSERT INTO `process_step` VALUES ('587', '9', '研究院管理部门', '92', '73', null, null, null, null, null); INSERT INTO `process_step` VALUES ('589', '9', '教培师训财务部', '93', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('591', '9', '泰学新心财务部', '94', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('593', '9', '河南财务部', '95', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('595', '9', '四川财务部', '96', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('597', '9', '教科文化财务部', '97', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('599', '9', '大视野控股财务部', '98', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('601', '9', '东方汇通财务部', '99', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('603', '9', '安大国际财务部', '100', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('605', '9', '研究院财务部', '101', null, null, null, null, null, null); INSERT INTO `process_step` VALUES ('631', '11', '商务部', '15', '55', null, '1', '1', null, '3'); INSERT INTO `process_step` VALUES ('633', '11', '广东分公司', '25', '2', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('635', '11', '广西办事处', '27', '4', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('637', '11', '上海中心', '29', '6', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('639', '11', '黑龙江办事处', '31', '7', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('641', '11', '江西分公司', '33', '9', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('643', '11', '安徽分公司', '35', '11', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('645', '11', '江苏办事处', '39', '13', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('647', '11', '西藏办事处', '41', '13', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('649', '11', '山东办事处', '43', '15', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('651', '11', '海南办事处', '45', '17', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('653', '11', '辽宁办事处', '47', '19', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('655', '11', '河北办事处', '49', '21', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('657', '11', '云南办事处', '51', '13', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('659', '11', '福建办事处', '53', '22', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('661', '11', '北京办事处', '55', '24', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('663', '11', '吉林办事处', '57', '26', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('665', '11', '贵州办事处', '59', '28', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('667', '11', '四川子公司', '61', '30', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('669', '11', '河南分公司', '63', '32', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('671', '11', '陕西分公司', '65', '34', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('673', '11', '甘肃办事处', '67', '34', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('675', '11', '重庆分公司', '69', '37', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('677', '11', '天津办事处', '71', '39', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('679', '11', '湖南分公司', '73', '41', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('681', '11', '宁夏办事处', '75', '42', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('683', '11', '浙江分公司', '77', '44', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('685', '11', '湖北分公司', '81', '46', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('687', '11', '新疆自治区 新疆兵团', '83', '46', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('689', '11', '内蒙办事处', '85', '48', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('691', '11', '山西办事处', '87', '50', null, '1', null, '1', '1'); INSERT INTO `process_step` VALUES ('693', '11', '青海办事处', '89', '42', null, '1', null, '1', '1');


-- 流程表添加 当前流程对应步骤字段
ALTER TABLE process ADD COLUMN step_id int(11) DEFAULT NULL COMMENT '当前流程对应步骤Id';

-- 流程审批记录添加审批对应步骤
ALTER TABLE process_history ADD COLUMN step_note int(1) DEFAULT NULL COMMENT '步骤说明      1办事处、分公司、子公司处理人   2分管部门处理人    3商务部处理人    4总经理办公室处理人   5教务部处理人   6财务部处理人';


-- payment_invoice表增加税务识别号字段：taxpayer_num
ALTER TABLE payment_invoice ADD COLUMN taxpayer_num varchar(64) COMMENT '税务识别号';


-- 经费付款表
CREATE TABLE `funds_pay` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `funds_id` int(11) DEFAULT NULL COMMENT '经费申请表的id',
  `pay_time` datetime DEFAULT NULL COMMENT '付款时间',
  `pay_money` decimal(10,2) DEFAULT NULL COMMENT '付款金额',
  `pay_operator_id` int(11) DEFAULT NULL COMMENT '付款操作人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经费付款表';

-- 付款登记表和项目汇总权限
INSERT INTO `menu` VALUES (77,33,'经费付款登记',NULL,'project:my_project:fundPay',2,NULL,93),(79,29,'可以查询全部的项目汇总',NULL,'query:summary:szdm',2,NULL,94),(81,29,'只能查询本部门所在省份的项目汇总',NULL,'query:summary:szfqr',2,NULL,95),(83,29,'只能查询自己发起的项目汇总',NULL,'query:summary:my',2,NULL,96),(85,29,'只能查询指定公司的项目汇总(财务、副总专用)',NULL,'query:summary:company',2,NULL,97),(87,85,'北京教培师训网络科技股份有限公司',NULL,'query:summary:company:1',2,NULL,98),(89,85,'北京泰学新心教育科技有限公司',NULL,'query:summary:company:3',2,NULL,99),(91,85,'北京大视野教育控股有限公司',NULL,'query:summary:company:5',2,NULL,100),(93,85,'北京东方汇通教育科技有限公司',NULL,'query:summary:company:7',2,NULL,101),(95,85,'北京大视野教科文化发展有限公司',NULL,'query:summary:company:9',2,NULL,102),(97,85,'北京京师大视野教育科技研究院',NULL,'query:summary:company:11',2,NULL,103),(99,85,'北京大视野安大国际文化交流有限公司',NULL,'query:summary:company:13',2,NULL,104),(101,85,'河南东师理想教育科技有限公司',NULL,'query:summary:company:15',2,NULL,105),(103,85,'成都东师理想科技有限公司',NULL,'query:summary:company:17',2,NULL,106);