
--1. project表增加数据，下面这个sql是例子，这些字段必须填，详情去看数据库表的字段备注
INSERT INTO `project` (`user_id`,`name`,`serial_number`,`type`,`format`,`provincial`,`city`,`county`,`charge_area`,`start_date`,`end_date`,`expected_num`,`charge_standard`,`cooper_name`,`pro_fund_provincial`,`pro_fund_city`,`pro_fund_county`,`pro_fund_other`,`later_fund_provincial`,`later_fund_city`,`later_fund_county`,`later_fund_other`,`collect_money_company`,`total_money`,`gen_time`,`status`) 
	VALUES (1,'广东省梅州市2012年初中教师全员培训项目','NT1244140001',2,1,2149,0,0,-1,'2012-06-01 08:00:00','2012-12-31 08:00:00',9556,150.00,'梅州市教师继续教育指导中心','0','0','0','0','0','0','0','43.38','北京教培师训网络科技股份有限公司',1433400.00,'2017-04-26 10:00:00',2);
--2. 编码表增加数据，时间可以任意，编码是上面的serial_number.substring(0,10)
INSERT INTO `serialnum`(`serial_num`,`gen_time`) VALUES ('NT12441400','2017-04-26 10:00:00');
--3. 项目预算表增加一条数据，只填项目ID即可
INSERT INTO `project_budget`(`project_id`) VALUES (3);

--4. 项目有没有到款？有的话按年份分别导入到payment表，知道具体几月几号的到款那就填1月1日，下面的例子导入了两条到款
INSERT INTO `payment` (`num`,`year`,`month`,`tra_time`,`amount`,`province`,`city`,`county`,`advance_pay`,`project_id`,`company`,`has_invoice`) 
	VALUES (1,2012,1,'2012-01-01 08:00:00',711750.00,2149,0,0,0,3,'北京教培师训网络科技股份有限公司',0);
INSERT INTO `payment` (`num`,`year`,`month`,`tra_time`,`amount`,`province`,`city`,`county`,`advance_pay`,`project_id`,`company`,`has_invoice`) 
	VALUES (1,2013,1,'2013-01-01 08:00:00',549499.00,2149,0,0,0,3,'北京教培师训网络科技股份有限公司',0);

--5. 项目的开票记录，都认为是机打发票，数量为1
INSERT INTO `payment_invoice` (`project_id`,`invoice_id`,`payment_id`,`unit_price`,`count`,`invoice_machine`,`result`,`invoice_date`)
	VALUES (3,NULL,0,1241249.00,1,1241249.00,1,'2012-01-01 08:00:00');
--6. 项目的申请经费记录
INSERT INTO `funds` (`type`,`project_id`,`apply_amount`,`create_time`,`won_pay`)
	VALUES (0,3,538447.00,'2014-01-01 08:00:00',1);
--7. 如果project的endDate.before(现在时刻),需要往项目完结表中插入一条数据，否则不需要
INSERT INTO `project_end` (`uid`,`project_id`,`real_total_money`,`real_total_pay`,`real_total_invoice`,`create_time`,`note`)
	VALUES (1,3,1433400.00, 1261249.00,1241249.00,'2012-12-31 08:00:00','此条为导入的旧项目数据');