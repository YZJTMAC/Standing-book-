package cn.teacheredu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.teacheredu.entity.FundsEntity;
import cn.teacheredu.entity.PaymentEntity;
import cn.teacheredu.entity.PaymentInvoiceEntity;
import cn.teacheredu.entity.ProjectBudgetEntity;
import cn.teacheredu.entity.ProjectEndEntity;
import cn.teacheredu.entity.ProjectEntity;
import cn.teacheredu.entity.ProjectSummariesEntity;
import cn.teacheredu.entity.ProjectSummaryOldEntity;
import cn.teacheredu.entity.SerialnumEntity;
import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.form.TestForm;
import cn.teacheredu.service.FundsService;
import cn.teacheredu.service.OrganizationService;
import cn.teacheredu.service.PaymentInvoiceService;
import cn.teacheredu.service.PaymentService;
import cn.teacheredu.service.ProjectBudgetService;
import cn.teacheredu.service.ProjectEndService;
import cn.teacheredu.service.ProjectService;
import cn.teacheredu.service.ProjectSummaryOldService;
import cn.teacheredu.service.SerialnumService;
import cn.teacheredu.service.UserService;
import cn.teacheredu.utils.ExportExcelUtil2;
import cn.teacheredu.utils.FilePathUtil;
import cn.teacheredu.utils.ImportExcelUtil;
import cn.teacheredu.utils.MyMathUtil;
import cn.teacheredu.utils.PageUtils;
import cn.teacheredu.utils.R;
import cn.teacheredu.utils.SpyMemcachedManager;
import cn.teacheredu.utils.SystemConst;
import cn.teacheredu.vo.ProjectEntityQueryVo;
import cn.teacheredu.vo.UserQueryVo;

/**
 * 功能测试用，不参与项目业务逻辑
 * @author Zhaojie
 *
 */
@Controller
@RequestMapping(value = "test/")
public class TestController {
	private static Logger logger = LoggerFactory.getLogger(TestController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private SpyMemcachedManager memcachedManager;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private ProjectEndService projectEndService;
	@Autowired
	private PaymentInvoiceService paymentInvoiceService;
	@Autowired
	private FundsService fundsService;
	@Autowired
	private ProjectBudgetService projectBudgetService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private SerialnumService serialnumService;
	@Autowired
	private ProjectSummaryOldService projectSummaryOldService;
	
	
	@RequestMapping("/{id}/showUser")
	public String showUser(@PathVariable String id, ModelMap modelMap ,HttpServletRequest request) throws Exception {
		UserEntity u = userService.getUserEntityById(Integer.parseInt(id));
	
		if(u == null){
			u = new UserEntity();
		}
		modelMap.put("user", u);
		return "showUser";
	}
	
	@RequestMapping("showUser")
	public String showUserEntity(String id, ModelMap modelMap,HttpServletRequest request) throws Exception {
		UserEntity u = userService.getUserEntityById(Integer.parseInt(id));
		if(u == null){
			u = new UserEntity();
		}
		modelMap.put("user", u);
		return "showUser";
	}
	
	@RequestMapping("/showUserExample")
	public String showUsers(Model model){
		return "redirect:/1/showUser.do";
	}
	
    @RequestMapping("/userList")
    public String list(ModelMap model) throws Exception {
    	logger.info("dasdddddddddddd");
    	List<UserEntity> users = userService.getUserEntities(new UserQueryVo());
    	System.out.println("set："+memcachedManager.set("pms_users", users, 100));
    	//System.out.println("set："+memcachedManager.set("pms_users", "哈哈", 100));
    	//System.out.println("delete："+memcachedManager.delete("pms_users"));
    	//System.out.println("add："+memcachedManager.add("pms_users", users, 100));
    	//System.out.println("replace："+memcachedManager.replace("pms_users", "哈哈", 100));
		System.out.println("get："+memcachedManager.get("pms_users"));
    	
        model.put("users", users);
        return "list";
    }
    
    @RequestMapping("/user/{id}")
    public String detail(@PathVariable(value = "id") String id, String test, ModelMap model,HttpServletRequest request,HttpSession session) throws Exception {
        model.put("user", userService.getUserEntityById(Integer.parseInt(id)));
        if ("1".equals(id)) {
        	try {
        		System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        		System.out.println(DateFormatUtils.format(session.getCreationTime(),"yyyy-MM-dd HH:mm:ss"));
        		System.out.println(DateFormatUtils.format(session.getLastAccessedTime(),"yyyy-MM-dd HH:mm:ss"));
        		//int i = 1/0;
//        		int[] arry = {1,2,3};
//        		System.out.println(arry[8]);
			} catch (ArithmeticException e) {
				e.printStackTrace();
			}
		}
        return "detail";
    }

    @RequestMapping("/exportExeclTest")
	public String exportExeclTest(Model model,HttpServletRequest request,HttpServletResponse response){
    	String title = "测试";
    	String[] rowName = {"序号","姓名","性别","年龄","创建时间","银子数"};
    	List<Object[]>  dataList = new ArrayList<Object[]>();
    	// 请注意，每一个Object数据是一行数据，数组中索引为0的都是空，不要放要导出的数据
    	Object[] obj = {"","李深","男","29",new Date(),"36000.20"};
    	Object[] obj2 = {"","宋江","男","45",DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"),"72000"};
    	Object[] obj3 = {"","貂蝉","女","18",DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"),"200000.25"};
    	dataList.add(obj);
    	dataList.add(obj2);
    	dataList.add(obj3);
    	try {
    		//导出xls格式，小数据性能好
    		//new ExportExcelUtil(title, rowName, dataList, request, response).exportData();
    		//导出xlsx格式，大数据性能好，建议使用
			new ExportExcelUtil2(title, rowName, dataList, request, response).exportData();
		} catch (Exception e) {
			model.addAttribute("ex", e);
			return "/error";
		}
		return null;
	}
    @RequestMapping("/exportExeclxxx.do")
    public String exportExecl(Model model,HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
    	String title = "测试";	
    	String[] rowName = SystemConst.EXCEL_ROWS;
    	List<Object[]>  dataList = new ArrayList<Object[]>();
    	// 请注意，每一个Object数据是一行数据，数组中索引为0的都是空，不要放要导出的数据
    	Object[] obj = {"","李深","男","29",new Date(),"36000.20"};
    	Object[] obj2 = {"","宋江","男","45",DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"),"72000"};
    	Object[] obj3 = {"","貂蝉","女","18",DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"),"200000.25"};
    	dataList.add(obj);
    	dataList.add(obj2);
    	dataList.add(obj3);
    	try {
    		//导出xls格式，小数据性能好
    		//new ExportExcelUtil(title, rowName, dataList, request, response).exportData();
    		//导出xlsx格式，大数据性能好，建议使用
    		new ExportExcelUtil2(title, rowName, dataList, request, response).exportData();
    	} catch (Exception e) {
    		model.addAttribute("ex", e);
    		return "/error";
    	}
    	return null;
    }
    @RequestMapping("/ajaxTest")
	public @ResponseBody R ajaxTest(ModelMap model,HttpServletRequest request,HttpServletResponse response){
    	
		return R.ok("hello 你好");
	}
    
    @RequestMapping("/importOldProject")
	public @ResponseBody R importOldProject(String args, ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception{
    	if (StringUtils.isBlank(args)) {
			return R.error("缺少参数");
		}
    	File resultFile = new File(FilePathUtil.getRealFilePath(args));
		if (!resultFile.exists()) {
			return R.error("未能获取到导入文件");
		} else {
			InputStream in = new FileInputStream(resultFile);
			List<List<String>> list = null;
			try {
				list = new ImportExcelUtil().getBankListByExcel(in, args.substring(args.lastIndexOf(".")));
			} catch (Exception e) {
				return R.error("解析Excel内容时出错了："+e.getMessage());
			} finally {
				if (in != null) {
					in.close();
				}
			}
			if (list != null) {
				if (list.size() > 1) {                 
					String[] pattern = new String[]{"yyyy-MM-dd HH:mm:ss","yyyy/MM/dd HH:mm:ss","yyyy-MM-dd","yyyy/MM/dd"};
					for (int i = 1; i < list.size(); i++) {
//						if (i > 3 && i < 16) {
//							continue;
//						}
						List<String> data = list.get(i);
						//System.out.println(data+"===="+data.size());
						Integer num = 0;
						try {
							num = Integer.parseInt(data.get(0));//序号
							if (num < 87) {
								continue;
							}
							String typeStr = data.get(1);
							Integer type = 0;
							if ("国培".equals(typeStr)) {
								type = 1;
							} else {
								type = 2;
							}
							Integer province = this.organizationService.getIdByName(data.get(3));
							if (province == null) {
								return R.error("序号"+num+"之前的项目已成功导入，在导入此序号的项目时没有获取到所属身份信息");
							}
							String xmbh = data.get(4);
							if (StringUtils.isBlank(xmbh) || xmbh.length() < 11) {
								return R.error("序号"+num+"之前的项目已成功导入，在导入此序号的项目时没有获取到项目编号");
							}
							String name = data.get(5);
							if (StringUtils.isBlank(name)) {
								return R.error("序号"+num+"之前的项目已成功导入，在导入此序号的项目时没有获取到项目名称");
							}
							name = data.get(3) + data.get(5) + "项目";
							String hzdw = data.get(6);
							Date startDate = DateUtils.parseDate(data.get(7), pattern);
							Date endDate = DateUtils.parseDate(data.get(8), pattern);
							BigDecimal danjia = new BigDecimal(data.get(10));
							Integer count = Integer.parseInt(data.get(12));
							BigDecimal yuqi = new BigDecimal(data.get(13));
							BigDecimal shiji = new BigDecimal(data.get(14));
							BigDecimal dk12 = new BigDecimal(data.get(15));
							BigDecimal dk13 = new BigDecimal(data.get(16));
							BigDecimal dk14 = new BigDecimal(data.get(17));
							BigDecimal dk15 = new BigDecimal(data.get(18));
							BigDecimal dk16 = new BigDecimal(data.get(19));
							BigDecimal dk17 = new BigDecimal(data.get(20));
							BigDecimal dkTotal = new BigDecimal(data.get(21));
							BigDecimal qqbl = new BigDecimal(StringUtils.isBlank(data.get(26)) ? "0" : data.get(26));
							BigDecimal hqbl = new BigDecimal(StringUtils.isBlank(data.get(27)) ? "0" : data.get(27));
							String qb = MyMathUtil.mul(qqbl, new BigDecimal("100")).toString();
							String hb = MyMathUtil.mul(hqbl, new BigDecimal("100")).toString();
							BigDecimal jf12 = new BigDecimal(StringUtils.isBlank(data.get(30)) ? "0" : data.get(30));
							BigDecimal jf13 = new BigDecimal(StringUtils.isBlank(data.get(31)) ? "0" : data.get(31));
							BigDecimal jf14 = new BigDecimal(StringUtils.isBlank(data.get(32)) ? "0" : data.get(32));
							BigDecimal jf15 = new BigDecimal(StringUtils.isBlank(data.get(33)) ? "0" : data.get(33));
							BigDecimal jf16 = new BigDecimal(StringUtils.isBlank(data.get(34)) ? "0" : data.get(34));
							BigDecimal jf17 = new BigDecimal(StringUtils.isBlank(data.get(35)) ? "0" : data.get(35));
							//BigDecimal jfTotal = new BigDecimal(StringUtils.isBlank(data.get(36)) ? "0" : data.get(36));
							//BigDecimal jfNone = new BigDecimal(StringUtils.isBlank(data.get(37)) ? "0" : data.get(37));
							BigDecimal kp12 = new BigDecimal(StringUtils.isBlank(data.get(38)) ? "0" : data.get(38));
							BigDecimal kp15 = new BigDecimal(StringUtils.isBlank(data.get(39)) ? "0" : data.get(39));
							BigDecimal kp16 = new BigDecimal(StringUtils.isBlank(data.get(40)) ? "0" : data.get(40));
							BigDecimal kp17 = new BigDecimal(StringUtils.isBlank(data.get(41)) ? "0" : data.get(41));
							BigDecimal kpTotal = new BigDecimal(StringUtils.isBlank(data.get(42)) ? "0" : data.get(42));
							
							//1. project添加一条记录
							ProjectEntity project = new ProjectEntity();
							project.setUserId(1);//发起人设置成管理员
							project.setName(name);
							project.setSerialNumber(xmbh);
							project.setType(type.byteValue());
							project.setFormat((byte)1);
							project.setProvincial(province);
							project.setCity(0);
							project.setCounty(0);
							project.setChargeArea(this.getChargeAreaByProvincial(province));
							project.setStartDate(startDate);
							project.setEndDate(endDate);
							project.setExpectedNum(count);
							project.setChargeStandard(danjia);
							project.setCooperName(hzdw);
							project.setProFundProvincial("0");
							project.setProFundCity("0");
							project.setProFundCounty("0");
							project.setProFundOther("0.00".equals(qb)?"0":qb);
							project.setLaterFundProvincial("0");
							project.setLaterFundCity("0");
							project.setLaterFundCounty("0");
							project.setLaterFundOther("0.00".equals(hb)?"0":hb);
							project.setCollectMoneyCompany("北京泰学新心教育科技有限公司");
							project.setTotalMoney(yuqi);
							project.setGenTime(startDate);
							if (endDate.after(new Date())) {
								project.setStatus((byte)1);//进行中的项目
							} else {
								project.setStatus((byte)2);//已结束
							}
							int projectId = this.projectService.insertProjectEntity(project);
							if (projectId > 0) {
								// 2.预算表插入一条空数据
								ProjectBudgetEntity pb = new ProjectBudgetEntity();
								pb.setProjectId(projectId);
								this.projectBudgetService.insertProjectBudgetEntity(pb);
								// 3.编码表插入一条数据
								SerialnumEntity ser = new SerialnumEntity();
								ser.setSerialNum(xmbh.substring(0, 10));
								ser.setGenTime(new Date());
								this.serialnumService.insertSerialnumEntity(ser);
								// 4.插入到款信息
								PaymentEntity payment = new PaymentEntity();
								payment.setNum(1);
								payment.setMonth((byte)1);
								payment.setProvince(province);
								payment.setCity(0);
								payment.setCounty(0);
								payment.setAdvancePay((byte)0);
								payment.setProjectId(projectId);
								payment.setCompany("北京泰学新心教育科技有限公司");
								payment.setHasInvoice(0);
								if (!dk12.equals(BigDecimal.ZERO)) {
									payment.setId(null);
									payment.setAmount(dk12);
									payment.setYear(2012);
									payment.setTraTime(DateUtils.parseDate("2012-01-01 08:00:00", pattern));
									this.paymentService.insertPaymentEntity(payment);
								}
								if (!dk13.equals(BigDecimal.ZERO)) {
									payment.setId(null);
									payment.setAmount(dk13);
									payment.setYear(2013);
									payment.setTraTime(DateUtils.parseDate("2013-01-01 08:00:00", pattern));
									this.paymentService.insertPaymentEntity(payment);
								}
								if (!dk14.equals(BigDecimal.ZERO)) {
									payment.setId(null);
									payment.setAmount(dk14);
									payment.setYear(2014);
									payment.setTraTime(DateUtils.parseDate("2014-01-01 08:00:00", pattern));
									this.paymentService.insertPaymentEntity(payment);
								}
								if (!dk15.equals(BigDecimal.ZERO)) {
									payment.setId(null);
									payment.setAmount(dk15);
									payment.setYear(2015);
									payment.setTraTime(DateUtils.parseDate("2015-01-01 08:00:00", pattern));
									this.paymentService.insertPaymentEntity(payment);
								}
								if (!dk16.equals(BigDecimal.ZERO)) {
									payment.setId(null);
									payment.setAmount(dk16);
									payment.setYear(2016);
									payment.setTraTime(DateUtils.parseDate("2016-01-01 08:00:00", pattern));
									this.paymentService.insertPaymentEntity(payment);
								}
								if (!dk17.equals(BigDecimal.ZERO)) {
									payment.setId(null);
									payment.setAmount(dk17);
									payment.setYear(2017);
									payment.setTraTime(DateUtils.parseDate("2017-01-01 08:00:00", pattern));
									this.paymentService.insertPaymentEntity(payment);
								}
								// 5.插入开票信息
								PaymentInvoiceEntity paymentInvoiceEntity = new PaymentInvoiceEntity();
								paymentInvoiceEntity.setProjectId(projectId);
								paymentInvoiceEntity.setPaymentId(0);
								paymentInvoiceEntity.setCount(1);
								paymentInvoiceEntity.setResult((byte)1);
								if (!kp12.equals(BigDecimal.ZERO)) {
									paymentInvoiceEntity.setUnitPrice(kp12);
									paymentInvoiceEntity.setInvoiceMachine(kp12);
									paymentInvoiceEntity.setInvoiceDate(DateUtils.parseDate("2012-01-01 08:00:00", pattern));
									this.paymentInvoiceService.insertPaymentInvoiceEntity(paymentInvoiceEntity);
								}
								if (!kp15.equals(BigDecimal.ZERO)) {
									paymentInvoiceEntity.setUnitPrice(kp15);
									paymentInvoiceEntity.setInvoiceMachine(kp15);
									paymentInvoiceEntity.setInvoiceDate(DateUtils.parseDate("2015-01-01 08:00:00", pattern));
									this.paymentInvoiceService.insertPaymentInvoiceEntity(paymentInvoiceEntity);
								}
								if (!kp16.equals(BigDecimal.ZERO)) {
									paymentInvoiceEntity.setUnitPrice(kp16);
									paymentInvoiceEntity.setInvoiceMachine(kp16);
									paymentInvoiceEntity.setInvoiceDate(DateUtils.parseDate("2016-01-01 08:00:00", pattern));
									this.paymentInvoiceService.insertPaymentInvoiceEntity(paymentInvoiceEntity);
								}
								if (!kp17.equals(BigDecimal.ZERO)) {
									paymentInvoiceEntity.setUnitPrice(kp17);
									paymentInvoiceEntity.setInvoiceMachine(kp17);
									paymentInvoiceEntity.setInvoiceDate(DateUtils.parseDate("2017-01-01 08:00:00", pattern));
									this.paymentInvoiceService.insertPaymentInvoiceEntity(paymentInvoiceEntity);
								}
								// 6.插入经费申请信息
								FundsEntity funds = new FundsEntity();
								funds.setType((byte)0);
								funds.setProjectId(projectId);
								funds.setWonPay((byte)1);
								if (!jf12.equals(BigDecimal.ZERO)) {
									funds.setApplyamount(jf12);
									funds.setCreateTime(DateUtils.parseDate("2012-01-01 08:00:00", pattern));
									this.fundsService.insertFundsEntity(funds);
								}
								if (!jf13.equals(BigDecimal.ZERO)) {
									funds.setApplyamount(jf13);
									funds.setCreateTime(DateUtils.parseDate("2013-01-01 08:00:00", pattern));
									this.fundsService.insertFundsEntity(funds);
								}
								if (!jf14.equals(BigDecimal.ZERO)) {
									funds.setApplyamount(jf14);
									funds.setCreateTime(DateUtils.parseDate("2014-01-01 08:00:00", pattern));
									this.fundsService.insertFundsEntity(funds);
								}
								if (!jf15.equals(BigDecimal.ZERO)) {
									funds.setApplyamount(jf15);
									funds.setCreateTime(DateUtils.parseDate("2015-01-01 08:00:00", pattern));
									this.fundsService.insertFundsEntity(funds);
								}
								if (!jf16.equals(BigDecimal.ZERO)) {
									funds.setApplyamount(jf16);
									funds.setCreateTime(DateUtils.parseDate("2016-01-01 08:00:00", pattern));
									this.fundsService.insertFundsEntity(funds);
								}
								if (!jf17.equals(BigDecimal.ZERO)) {
									funds.setApplyamount(jf17);
									funds.setCreateTime(DateUtils.parseDate("2017-01-01 08:00:00", pattern));
									this.fundsService.insertFundsEntity(funds);
								}
								// 7. 插入项目完结信息
								if (project.getStatus() == 2) {
									 ProjectEndEntity projectEndEntity = new ProjectEndEntity();
									 projectEndEntity.setProjectId(projectId);
									 projectEndEntity.setUid(1);
									 projectEndEntity.setRealTotalMoney(shiji);
									 projectEndEntity.setRealTotalPay(dkTotal);
									 projectEndEntity.setRealTotalInvoice(kpTotal);
									 projectEndEntity.setNote("此条为导入的旧项目数据");
									 projectEndEntity.setCreateTime(endDate);
									 this.projectEndService.insertProjectEndEntity(projectEndEntity);
								}
								
							} else {
								return R.error("序号"+num+"之前的项目已成功导入，在导入此序号的项目时出错了：未知错误");
							}
							
						} catch (Exception e) {
							e.printStackTrace();
							return R.error("序号"+num+"之前的项目已成功导入，在导入此序号的项目时出错了,可能需要手动处理无效数据，检查一下数据库吧，错误信息："+e.getLocalizedMessage());
						}
					}
				}
				return R.ok("成功");
			}
		}
    	
		return R.error("未知错误");
	}
    
    private Integer getChargeAreaByProvincial(Integer province) {
    	List<Integer> area1 = new ArrayList<Integer>();
    	area1.add(2149);
    	area1.add(2311);
    	area1.add(889);
    	area1.add(2998);
    	area1.add(1501);
    	area1.add(2449);
    	area1.add(503);
    	area1.add(43);
    	area1.add(2844);
    	area1.add(1275);
    	area1.add(1);
    	area1.add(632);
    	area1.add(2743);
    	area1.add(2522);
    	area1.add(1676);
    	List<Integer> area2 = new ArrayList<Integer>();
    	area2.add(867);
    	area2.add(711);
    	area2.add(3080);
    	area2.add(3208);
    	area2.add(2478);
    	area2.add(22);
    	area2.add(1999);
    	area2.add(3374);
    	area2.add(1022);
    	List<Integer> area3 = new ArrayList<Integer>();
    	area3.add(1379);
    	area3.add(1135);
    	area3.add(1870);
    	area3.add(3406);
    	area3.add(381);
    	area3.add(238);
    	if (area1.contains(province)) {
			return -1;
		} else if (area2.contains(province)) {
			return -2;
		} else if (area3.contains(province)) {
			return -3;
		}
		return null;
	}

	@RequestMapping("/testForm")
   	public @ResponseBody R testForm(@RequestBody TestForm test,ModelMap model,HttpServletRequest request,HttpServletResponse response){
       	System.out.println(test.getName());
   		return R.ok("hello 你好");
   	}
	
	@RequestMapping("/importOldProject2")
	public @ResponseBody R importOldProject2(String args, ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception{
    	if (StringUtils.isBlank(args)) {
			return R.error("缺少参数");
		}
    	File resultFile = new File(FilePathUtil.getRealFilePath(args));
		if (!resultFile.exists()) {
			return R.error("未能获取到导入文件");
		} else {
			InputStream in = new FileInputStream(resultFile);
			List<List<String>> list = null;
			try {
				list = new ImportExcelUtil().getBankListByExcel(in, args.substring(args.lastIndexOf(".")));
			} catch (Exception e) {
				return R.error("解析Excel内容时出错了："+e.getMessage());
			} finally {
				if (in != null) {
					in.close();
				}
			}
			if (list != null) {
				if (list.size() > 1) {
					for (int i = 1; i < list.size(); i++) {
						List<String> data = list.get(i);
						//System.out.println(data+"===="+data.size());
						Integer num = 0;
						try {
							num = Integer.parseInt(data.get(0));//序号
//							if (num < 7 || num == 13) {
//								continue;
//							}
							String xmbh = data.get(4);
							if (StringUtils.isBlank(xmbh)) {
								return R.error("序号"+num+"之前的项目已成功导入，在导入此序号的项目时没有获取到项目编号");
							}
							ProjectEntity project = null;
							ProjectEntityQueryVo vo = new ProjectEntityQueryVo();
							vo.setSerialNumber(xmbh);
							List<ProjectEntity> projectEntities = this.projectService.getProjectByVo(vo);
							if (projectEntities.size() != 1) {
								return R.error("序号"+num+"之前的项目已成功导入，在导入此序号的项目时没有获取到项目信息");
							}
							project = projectEntities.get(0);
							Integer projectId = project.getId();
					
							BigDecimal jz12 = new BigDecimal(StringUtils.isBlank(data.get(22)) ? "0" : data.get(22));
							BigDecimal jz16 = new BigDecimal(StringUtils.isBlank(data.get(23)) ? "0" : data.get(23));
							BigDecimal jz17 = new BigDecimal(StringUtils.isBlank(data.get(24)) ? "0" : data.get(24));
							BigDecimal jzcb12 = new BigDecimal(StringUtils.isBlank(data.get(45)) ? "0" : data.get(45));	
							BigDecimal jzcb13 = new BigDecimal(StringUtils.isBlank(data.get(46)) ? "0" : data.get(46));
							BigDecimal jzcb14 = new BigDecimal(StringUtils.isBlank(data.get(47)) ? "0" : data.get(47));
							BigDecimal jzcb15 = new BigDecimal(StringUtils.isBlank(data.get(48)) ? "0" : data.get(48));
							BigDecimal jzcb16 = new BigDecimal(StringUtils.isBlank(data.get(49)) ? "0" : data.get(49));	
							BigDecimal jzcb17 = new BigDecimal(StringUtils.isBlank(data.get(50)) ? "0" : data.get(50));	
							
							ProjectSummaryOldEntity pso = new ProjectSummaryOldEntity();
							pso.setProjectId(projectId);
							pso.setCarryoverTw(jz12);
							pso.setCarryoverSx(jz16);
							pso.setCarryoverSe(jz17);
							pso.setCarryoverCostTw(jzcb12);
							pso.setCarryoverCostTh(jzcb13);
							pso.setCarryoverCostFo(jzcb14);
							pso.setCarryoverCostFf(jzcb15);
							pso.setCarryoverCostSx(jzcb16);
							pso.setCarryoverCostSe(jzcb17);
							
							int r = this.projectSummaryOldService.insertProjectSummaryOldEntity(pso);
							if (r > 0) {
								System.out.println("已完成数据处理，序号为："+num+",项目编码为："+xmbh);
							} else {
								return R.error("序号"+num+"之前的项目已成功导入，在导入此序号的项目时出错了,未知错误");
							}
							
						} catch (Exception e) {
							e.printStackTrace();
							return R.error("序号"+num+"之前的项目已成功导入，在导入此序号的项目时出错了,可能需要手动处理无效数据，检查一下数据库吧，错误信息："+e.getLocalizedMessage());
						}
					}
				}
				return R.ok("成功");
			}
		}
    	
		return R.error("未知错误");
	}
}