package cn.teacheredu.controller.project;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import cn.teacheredu.controller.Base2Controller;
import cn.teacheredu.entity.CompanyEntity;
import cn.teacheredu.entity.DepartmentEntity;
import cn.teacheredu.entity.OrganizationEntity;
import cn.teacheredu.entity.ProjectEntity;
import cn.teacheredu.entity.ProjectSummariesEntity;
import cn.teacheredu.entity.ProjectYearSummaryEntity;
import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.form.QueryTermsForm;
import cn.teacheredu.service.CompanyService;
import cn.teacheredu.service.DepartmentService;
import cn.teacheredu.service.FundsService;
import cn.teacheredu.service.OrganizationService;
import cn.teacheredu.service.PaymentInvoiceService;
import cn.teacheredu.service.PaymentService;
import cn.teacheredu.service.ProjectBudgetService;
import cn.teacheredu.service.ProjectService;
import cn.teacheredu.service.ProjectSummaryService;
import cn.teacheredu.utils.CommonUtils;
import cn.teacheredu.utils.DateFormatUtil;
import cn.teacheredu.utils.ExportExcelUtil2;
import cn.teacheredu.utils.MyMathUtil;
import cn.teacheredu.utils.PageUtils;
import cn.teacheredu.utils.SystemConst;
import cn.teacheredu.vo.CompanyQueryVo;
import cn.teacheredu.vo.ProjectEntityQueryVo;

@Controller
@RequestMapping(value = "/project")
public class ProjectSummaryController extends Base2Controller{
	
	private static Logger logger = LoggerFactory.getLogger(ProjectSummaryController.class);

	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private ProjectBudgetService projectBudgetService;
	@Autowired
	private PaymentInvoiceService paymentInvoiceService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private FundsService fundsService;
	@Autowired
	private ProjectSummaryService projectSummaryService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private CompanyService companyService;
	
	/**
	 * 项目汇总页面
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/summary",method=RequestMethod.GET)
	public String projectSummaryPage(ModelMap modelMap, HttpServletRequest request) throws Exception{
		List<String> psList =  (List<String>) modelMap.get("psList");
		if (!CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_PROJECT_SUMMARY, psList)) {
			modelMap.put("tipMsg", "抱歉...你没有查看项目汇总的权限");
			return "tip";
		}
		
		//1.列出基础条件中的部门列表
		List<DepartmentEntity> dmListQuery = null;
		boolean hasOtherPri = CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_PROJECT_SUMMARY_OTHERPRIVINCE, psList);
		boolean hasCompany = CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_PROJECT_SUMMARY_COMPANY, psList);
		if (hasOtherPri || hasCompany) { //用户拥有查询所有项目或者查询指定公司的项目的权限，这个时候部门列表要给用户列出所有的业务部门（渠道服务中心）
			dmListQuery = this.departmentService.getLocalDepartmentList("4");//部门类型，0 研发 1 教务 2 商务 3 财务 4 办事处、分公司、子公司 5 总经理办公室 6 分管部门
			DepartmentEntity dm = new DepartmentEntity();
			dm.setDmName("部门名称(全部)");
			dm.setProvince(-8);//我们用-8代表查询全部省份的项目，查询project时前端页面如果传来的是-8那sql的where里就不用拼province这列了
			dmListQuery.add(0, dm);
		} else { //只要不是查询全部部门的，那就是只查询自己部门的
			dmListQuery = (List<DepartmentEntity>) modelMap.get("dmList");
		}
		modelMap.put("dmListQuery", dmListQuery);
		//2.列出基础条件中的公司列表
		List<CompanyEntity> companyList = new ArrayList<CompanyEntity>();
		companyList = this.companyService.getCompanyByVo(new CompanyQueryVo()); 
		if (hasOtherPri) {//如果用户的权限是查询所有的项目，那么要给用户列出所有的公司
		} else if (hasCompany) {//如果用户的权限是查询指定公司的项目，那么要给用户列出他能查询的公司列表
			// 从psList中就能得到，看着：
			for (String perms : psList) {
				if (perms.contains(SystemConst.PERMISSION_QUERY_COMPANY) && !perms.equals(SystemConst.PERMISSION_QUERY_COMPANY)) {
					CompanyEntity companyEntity = this.companyService.getCompanyById(Integer.parseInt(perms.substring(SystemConst.PERMISSION_QUERY_COMPANY.length()+1)));
					if (companyEntity != null)
						companyList.add(companyEntity);
				}
			}
		}
		//   其它情况，比如用户只能查询自己发起的项目或者只能查询本部门所在省份的项目，那么不给他列出公司列表
		//   因为要得到这些人发起的项目都属于哪些公司还需要去project表分类并去重，太麻烦了！
		modelMap.put("companyList", companyList);
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String strYear = sdf.format(new Date());
		int intYear = Integer.parseInt(strYear);
		List<String> years = new ArrayList<String>();
		for(int i = 2012; i <= intYear; i++){
			years.add(i+"");
		}
		modelMap.put("years", years);
		modelMap.put("colNum", intYear - 2012 + 2);
		//查询所有省份
		List<OrganizationEntity> provinceList = this.organizationService.getListByParentId(0);
		modelMap.put("provinceList", provinceList);
		return "project/projectSummary";
	}
	
	/**
	 * 项目汇总页面列表数据
	 */
	@RequestMapping(value="/projectSummaryList",method=RequestMethod.GET)
	public String projectSummaryListPage(QueryTermsForm form, ModelMap modelMap, HttpServletRequest request) throws Exception{
	
		@SuppressWarnings("unchecked")
		
		Map<String,Object> excelParamMap = new HashedMap();
		setForm(form, request);
		excelParamMap.put("form", form);
		excelParamMap.put("modelMap", modelMap);
		request.getSession().setAttribute("excelParamMap",excelParamMap);
		List<String> psList =  (List<String>) modelMap.get("psList");
		if (!CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_PROJECT_SUMMARY, psList)) {
			modelMap.put("tipMsg", "抱歉...你没有查看项目汇总的权限");
			return "tip";
		}
		boolean hasOtherPri = CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_PROJECT_SUMMARY_OTHERPRIVINCE, psList);
		boolean hasOtherPer = CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_PROJECT_SUMMARY_OTHERPERSION, psList);
		boolean hasCompany = CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_PROJECT_SUMMARY_COMPANY, psList);
		if (!(hasOtherPri || hasOtherPer || hasCompany)) {//只能查询自己发起的项目
			UserEntity user = (UserEntity) modelMap.get("user");
			form.setUserId(user.getId());
		} else {
			List<String> companyList = new ArrayList<String>();//定义一个list存放用户要查询哪些公司的项目
			if ("all".equals(form.getCompany())) { //如果用户要查询他所能查询的所有公司的项目
				if (hasCompany) { //用户只能查询指定的公司
					for (String perms : psList) {
						if (perms.contains(SystemConst.PERMISSION_PROJECT_SUMMARY_COMPANY) && !perms.equals(SystemConst.PERMISSION_PROJECT_SUMMARY_COMPANY)) {
							CompanyEntity companyEntity = this.companyService.getCompanyById(Integer.parseInt(perms.substring(SystemConst.PERMISSION_PROJECT_SUMMARY_COMPANY.length()+1)));
							if (companyEntity != null)
								companyList.add(companyEntity.getName());
						}
					}
				}
				//其它情况，用户可以查询全部项目和本部门所属省份的项目，这两个情况的查询“全部”公司都不用在sql中拼公司名称
			} else { //用户要查询某一个公司的项目
				companyList.add(form.getCompany());
			}
			form.setCompanyList(companyList);
		}
		
		form.setPageSize(10);
		PageUtils<ProjectSummariesEntity> projectSummaryList = projectSummaryService.getProjectSummaryList(form);
		modelMap.put("projectSummaryList", projectSummaryList);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String strYear = sdf.format(new Date());
		int intYear = Integer.parseInt(strYear);
		List<String> years = new ArrayList<String>();
		int j = 0;
		for(int i = 2012; i <= intYear; i++){
			j = i- 2000;
			years.add(j+"");//zk1从哪一年开始？
		}
		modelMap.put("years", years);
		modelMap.put("colNum", intYear - 2012 + 2);
		
		
		return "project/projectSummaryList";
	}
	
	/**
	 * 项目汇总导出到Excel
	 */
	@RequestMapping(value="/exportExecl.do",method=RequestMethod.GET)
	public String exportExcel(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map excelParamMap = (Map)request.getSession().getAttribute("excelParamMap");
		QueryTermsForm form = (QueryTermsForm)excelParamMap.get("form");
		ModelMap modelMap = (ModelMap)excelParamMap.get("modelMap");
		List<String> psList =  (List<String>) modelMap.get("psList");
		if (!CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_PROJECT_SUMMARY, psList)) {
			modelMap.put("tipMsg", "抱歉...你没有查看项目汇总的权限");
			return "tip";
		}
		boolean hasOtherPri = CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_PROJECT_SUMMARY_OTHERPRIVINCE, psList);
		boolean hasOtherPer = CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_PROJECT_SUMMARY_OTHERPERSION, psList);
		boolean hasCompany = CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_PROJECT_SUMMARY_COMPANY, psList);
		if (!(hasOtherPri || hasOtherPer || hasCompany)) {//只能查询自己发起的项目
			UserEntity user = (UserEntity) modelMap.get("user");
			form.setUserId(user.getId());
		} else {
			List<String> companyList = new ArrayList<String>();//定义一个list存放用户要查询哪些公司的项目
			if ("all".equals(form.getCompany())) { //如果用户要查询他所能查询的所有公司的项目
				if (hasCompany) { //用户只能查询指定的公司
					for (String perms : psList) {
						if (perms.contains(SystemConst.PERMISSION_PROJECT_SUMMARY_COMPANY) && !perms.equals(SystemConst.PERMISSION_PROJECT_SUMMARY_COMPANY)) {
							CompanyEntity companyEntity = this.companyService.getCompanyById(Integer.parseInt(perms.substring(SystemConst.PERMISSION_PROJECT_SUMMARY_COMPANY.length()+1)));
							if (companyEntity != null)
								companyList.add(companyEntity.getName());
						}
					}
				}
				//其它情况，用户可以查询全部项目和本部门所属省份的项目，这两个情况的查询“全部”公司都不用在sql中拼公司名称
			} else { //用户要查询某一个公司的项目
				companyList.add(form.getCompany());
			}
			form.setCompanyList(companyList);
		}
		
		form.setPageSize(10);
		form.setExcel(true);
		PageUtils<ProjectSummariesEntity> projectSummaryList = projectSummaryService.getProjectSummaryList(form);
		List<Object[]>  dataList = new ArrayList<Object[]>();
		String title = "项目汇总";	
		String[] rowName = null;
    	for(ProjectSummariesEntity projectSummariesEntity:projectSummaryList.getList()){
    		rowName = SystemConst.EXCEL_ROWS;
    		/**
    		 * 通过Linkedlist的特性（双向环状链表），插入删除比较快，实现动态改变数组大小的效率高
    		 */
        	List<String> rowNameList= new LinkedList<String>(Arrays.asList(rowName));  
			List<ProjectYearSummaryEntity> projectYearSummarys = projectSummariesEntity.getProjectYearSummarys();
			Object[] obj = {"",projectSummariesEntity.getProjectCompany(),projectSummariesEntity.getProjectType().toString().equals("1") ?"国培":"地陪",projectSummariesEntity.getProjectYear(),projectSummariesEntity.getProjectProvincial(),
					projectSummariesEntity.getProjectSerialNumber(),projectSummariesEntity.getProjectName(),projectSummariesEntity.getProjectCooperName(),DateFormatUtil.formatForYMD(projectSummariesEntity.getProjectStartDate()),DateFormatUtil.formatForYMD(projectSummariesEntity.getProjectEndDate()),projectSummariesEntity.getProjectTrainPeriod(),
					projectSummariesEntity.getProjectCharge(),projectSummariesEntity.getProjectExpectedNum(),projectSummariesEntity.getProjectLiveNum(),projectSummariesEntity.getPredictTotalAmount(),projectSummariesEntity.getProjectRealTotalAmount(), projectSummariesEntity.getProjectStatus().toString().equals("1")?"正在进行中":"已完结",
					projectSummariesEntity.getProjectProtocolName()==null?"无":projectSummariesEntity.getProjectProtocolName(),projectSummariesEntity.getRealPaymentAmount(),projectSummariesEntity.getPredictLaterPayment(),projectSummariesEntity.getPredictReceiveAmount(),projectSummariesEntity.getRealIncomeAmount(),projectSummariesEntity.getProFundProportion(),projectSummariesEntity.getLaterFundProportion(),projectSummariesEntity.getProFundAmount(),projectSummariesEntity.getLaterFundAmount(),
					projectSummariesEntity.getRealFundAmount(),projectSummariesEntity.getNopayFundAmount(),projectSummariesEntity.getPredictBudget(),projectSummariesEntity.getRealBudgetAmount(),projectSummariesEntity.getRealCostAmount(),projectSummariesEntity.getRealInvoiceAmount(),
					projectSummariesEntity.getNoInvoiceAmount(),projectSummariesEntity.getFundError(),projectSummariesEntity.getNote(),projectSummariesEntity.getUrl()};
			List<Object> objList= new LinkedList<Object>(Arrays.asList(obj));  
			/**
			 * 由于年份不确定（比如现在是2012-2017，明年就是2012-2018，诸如此类），所以需要动态改变excel的的列数
			 * 通过动态改变传入的rowName的数据来达到动态改变列数的目的
			 */
			for(ProjectYearSummaryEntity projectYearSummary:projectYearSummarys){
				Integer year = projectYearSummary.getYear();
				BigDecimal paymentAmount = projectYearSummary.getPaymentamount();
				BigDecimal incomeAmount = projectYearSummary.getIncomeamount();
				BigDecimal fundsAmount = projectYearSummary.getFundsamount();
				BigDecimal budgerAmount = projectYearSummary.getBudgetamount();
				BigDecimal OverAmount = projectYearSummary.getOveramount();
				BigDecimal invoiceAmount = projectYearSummary.getInvoiceamount();
				int index1 = rowNameList.indexOf("项目实际到款总额");
				rowNameList.add(index1, year+"年项目实际到款总额");
				objList.add(index1,paymentAmount);
				int index2 = rowNameList.indexOf("已转收入");
				rowNameList.add(index2, year+"年已转收入");
				objList.add(index2,incomeAmount);
				int index3 = rowNameList.indexOf("已支付经费金额（财务账冲减应付账款）");
				rowNameList.add(index3, year+"年已支付经费金额（财务账冲减应付账款）");
				objList.add(index3,fundsAmount);
				int index4 = rowNameList.indexOf("项目运营成本");
				rowNameList.add(index4, year+"年项目运营成本");
				objList.add(index4,budgerAmount);
				int index5 = rowNameList.indexOf("结转成本");
				rowNameList.add(index5, year+"年结转成本");
				objList.add(index5,OverAmount);
				int index6 = rowNameList.indexOf("开票金额");
				rowNameList.add(index6, year+"年开票金额");
				objList.add(index6,invoiceAmount);
			}
			rowName =  rowNameList.toArray(new String[rowNameList.size()]);
			dataList.add(objList.toArray());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String strYear = sdf.format(new Date());
		int intYear = Integer.parseInt(strYear);
		List<String> years = new ArrayList<String>();
		int j = 0;
		for(int i = 2012; i <= intYear; i++){
			j = i- 2000;
			years.add(j+"");//zk1从哪一年开始？
		}
		modelMap.put("years", years);
		modelMap.put("colNum", intYear - 2012 + 2);
		
	
    	try {
    		//导出xls格式，小数据性能好
    		//new ExportExcelUtil(title, rowName, dataList, request, response).exportData();
    		//导出xlsx格式，大数据性能好，建议使用
    		if(rowName==null){
    			rowName = SystemConst.EXCEL_ROWS;
    		}
    		new ExportExcelUtil2(title,rowName, dataList, request, response).exportData();
    	} catch (Exception e) {
    		model.addAttribute("ex", e);
    		return "/error";
    	}
		return null;
	}
	
	/**
	 * 项目汇总数据计算
	 */
	public String projectSummaryData(Integer id, ModelMap modelMap) throws Exception{
		UserEntity user = (UserEntity) modelMap.get("user");
		logger.info("user " + user.getLoginName() + " project summary " + id);
		ProjectEntityQueryVo vo = new ProjectEntityQueryVo();
		vo.setId(id);
		List<ProjectEntity> projectList = projectService.getProjectByVo(vo);
		for (ProjectEntity project : projectList) {
			
			String company = project.getCollectMoneyCompany(); // 公司名称
			Byte type = project.getType(); // 项目类型
			String provincial = this.organizationService.getNameById(project.getProvincial()); // 省份
			String serialNumber = project.getSerialNumber(); // 项目编号
			String name = project.getName(); // 项目名称
			String cooperName = project.getCooperName(); // 合作单位
			Date startDate = project.getStartDate(); // 培训起始时间
			Date endDate = project.getEndDate(); // 培训截止时间
			int month = CommonUtils.getMonthDiff(endDate, startDate); // 培训期限（月）
			BigDecimal chargeStandard = project.getChargeStandard(); // 培训单价
			Integer expectedNum = project.getExpectedNum(); // 报名人数
			Integer realNum = project.getRealNum(); // 上线人数
			Integer predictAmount = (expectedNum) * (realNum); // 预计项目总额
			// 实际项目总额
			Byte status = project.getStatus(); // 项目运营状态
			String protocolName = project.getProtocolName(); // 是否有协议    null 无  else 有
			BigDecimal qb = MyMathUtil.div(MyMathUtil.addStringToBigDecimal(project.getProFundCity(),project.getProFundCounty(),project.getProFundOther(),project.getProFundProvincial()), new BigDecimal("100.00"), 4); 
			BigDecimal hb = MyMathUtil.div(MyMathUtil.addStringToBigDecimal(project.getLaterFundCity(),project.getLaterFundCounty(),project.getLaterFundOther(),project.getLaterFundProvincial()), new BigDecimal("100.00"), 4); 
			BigDecimal qj = MyMathUtil.mul(project.getTotalMoney(),qb); // 应付经费总额-前期
			BigDecimal hj = MyMathUtil.mul(project.getTotalMoney(),hb); // 应付经费总额-后期
			BigDecimal yzf = fundsService.getTotalWonPayByProject(project.getId()); // 已支付经费
			BigDecimal wzf = MyMathUtil.sub(hj, yzf); // 未支付经费金额
			
			BigDecimal amount = paymentService.getRealPayTotalByProjectId(project.getId()); // 到款总额
			Integer year = 2000; // 年份
			
			// 预计后期到款
			// 应收到款
			// 已转收入
			// 运营成本
			
			BigDecimal ykp = this.paymentInvoiceService.getTotalInvoiceByProjectId(project.getId()); // 已开票
			BigDecimal ydk = this.paymentService.getRealPayTotalByProjectId(id); // 以到款
			BigDecimal wkp = MyMathUtil.sub(ydk, ykp); // 未开发票金额
			BigDecimal wdz = MyMathUtil.sub(project.getTotalMoney(), ydk); // 未到帐款
			// 经费支付异常（已到款应付经费-已付经费）
			
		}
		
		// 返回 项目汇总实体类
		return null;
		
	}
	
	/**
	 * 设置用来筛选汇总项目的请求参数
	 * @param form
	 * @param request
	 * @return form
	 * @author TMACJ
	 */
	public QueryTermsForm setForm(QueryTermsForm form , HttpServletRequest request){
		String proTypeString = request.getParameter("proType");
		Byte proTypeByte = null;
		if("1".equals(proTypeString))proTypeByte = 1;
		else if("2".equals(proTypeString))proTypeByte = 2;
		else if(null == proTypeString)proTypeByte = null;
		form.setProType(proTypeByte);
		if(request.getParameter("years")!=null&&!"".equals(request.getParameter("years")))form.setProYear(Integer.valueOf(request.getParameter("years")));
		if(request.getParameter("province")!=null&&!"".equals(request.getParameter("province")))form.setProvince(Integer.valueOf(request.getParameter("province")));
		if(request.getParameter("proNo")!=null&&!"".equals(request.getParameter("proNo")))form.setProjectNo(request.getParameter("proNo"));
		String trainTypeString = request.getParameter("trainType");
		Byte trainTypeByte = null;
		if("1".equals(trainTypeString)) trainTypeByte = 1;
		else if("2".equals(trainTypeString)) trainTypeByte = 2;
		else if(null == trainTypeByte) trainTypeByte = null;
		form.setTrainType(trainTypeByte);
		String projectStatusString = request.getParameter("projectStatus");
		Byte projectStatusByte = null;
		if("0".equals(projectStatusString)) projectStatusByte = 0;
		else if("1".equals(projectStatusString)) projectStatusByte = 1;
		else if("2".equals(projectStatusString)) projectStatusByte = 2;
		else if("3".equals(projectStatusString)) projectStatusByte = 3;
		else if("4".equals(projectStatusString)) projectStatusByte = 4;
		else if(null == trainTypeByte) projectStatusByte = null;
		form.setStatus(projectStatusByte);
		if(request.getParameter("protoName")!=null&&!"".equals(request.getParameter("protoName")))form.setProtocolName(request.getParameter("protoName"));
		if(request.getParameter("companyName")!=null&&!"".equals(request.getParameter("companyName")))form.setCompany(request.getParameter("companyName"));
		if(request.getParameter("proName")!=null&&!"".equals(request.getParameter("proName")))form.setProjectName(request.getParameter("proName"));
		if(request.getParameter("estimatedAmountLater_min")!=null&&!"".equals(request.getParameter("estimatedAmountLater_min")))form.setEstimatedAmountLater_min(new BigDecimal(request.getParameter("estimatedAmountLater_min")));
		if(request.getParameter("estimatedAmountLater_max")!=null&&!"".equals(request.getParameter("estimatedAmountLater_max")))form.setEstimatedAmountLater_max(new BigDecimal(request.getParameter("estimatedAmountLater_max")));
		if(request.getParameter("layDateStartForQuery")!=null&&!"".equals(request.getParameter("layDateStartForQuery")))form.setStartDate(Timestamp.valueOf(request.getParameter("layDateStartForQuery")+" 00:00:00"));	//原日期格式是"2016-1-1",现在将其转化为"2016-1-1 00:00:00",方便转化为Timestamp类型
		if(request.getParameter("layDateEndForQuery")!=null&&!"".equals(request.getParameter("layDateEndForQuery")))form.setEndDate(Timestamp.valueOf(request.getParameter("layDateEndForQuery")+" 00:00:00"));
		if(request.getParameter("estimatedAmount_min")!=null&&!"".equals(request.getParameter("estimatedAmount_min")))form.setEstimatedAmount_min(new BigDecimal(request.getParameter("estimatedAmount_min")));
		if(request.getParameter("estimatedAmount_max")!=null&&!"".equals(request.getParameter("estimatedAmount_max")))form.setEstimatedAmount_max(new BigDecimal(request.getParameter("estimatedAmount_max")));
		if(request.getParameter("projectDueAmount_min")!=null&&!"".equals(request.getParameter("projectDueAmount_min")))form.setProjectDueAmount_min(new BigDecimal(request.getParameter("projectDueAmount_min")));
		if(request.getParameter("projectDueAmount_max")!=null&&!"".equals(request.getParameter("projectDueAmount_max")))form.setProjectDueAmount_max(new BigDecimal(request.getParameter("projectDueAmount_max")));
		if(request.getParameter("receivableAccounts_min")!=null&&!"".equals(request.getParameter("receivableAccounts_min")))form.setReceivableAccounts_min(new BigDecimal(request.getParameter("receivableAccounts_min")));
		if(request.getParameter("receivableAccounts_max")!=null&&!"".equals(request.getParameter("receivableAccounts_max")))form.setReceivableAccounts_max(new BigDecimal(request.getParameter("receivableAccounts_max")));
		if(request.getParameter("transferredIncome_min")!=null&&!"".equals(request.getParameter("transferredIncome_min")))form.setTransferredIncome_min(new BigDecimal(request.getParameter("transferredIncome_min")));
		if(request.getParameter("transferredIncome_max")!=null&&!"".equals(request.getParameter("transferredIncome_max")))form.setTransferredIncome_max(new BigDecimal(request.getParameter("transferredIncome_max")));
		if(request.getParameter("payableFundsPeriod_min")!=null&&!"".equals(request.getParameter("payableFundsPeriod_min")))form.setPayableFundsPeriod_min(new BigDecimal(request.getParameter("payableFundsPeriod_min")));
		if(request.getParameter("payableFundsPeriod_max")!=null&&!"".equals(request.getParameter("payableFundsPeriod_max")))form.setPayableFundsPeriod_max(new BigDecimal(request.getParameter("payableFundsPeriod_max")));
		if(request.getParameter("payableFundsLater_min")!=null&&!"".equals(request.getParameter("payableFundsLater_min")))form.setPayableFundsLater_min(new BigDecimal(request.getParameter("payableFundsLater_min")));
		if(request.getParameter("payableFundsLater_max")!=null&&!"".equals(request.getParameter("payableFundsLater_max")))form.setPayableFundsLater_max(new BigDecimal(request.getParameter("payableFundsLater_max")));
		if(request.getParameter("completePayableFunds_min")!=null&&!"".equals(request.getParameter("completePayableFunds_min")))form.setCompletePayableFunds_min(new BigDecimal(request.getParameter("completePayableFunds_min")));
		if(request.getParameter("completePayableFunds_max")!=null&&!"".equals(request.getParameter("completePayableFunds_max")))form.setCompletePayableFunds_max(new BigDecimal(request.getParameter("completePayableFunds_max")));
		if(request.getParameter("uncompletePayableFunds_min")!=null&&!"".equals(request.getParameter("uncompletePayableFunds_min")))form.setUncompletePayableFunds_min(new BigDecimal(request.getParameter("uncompletePayableFunds_min")));
		if(request.getParameter("uncompletePayableFunds_max")!=null&&!"".equals(request.getParameter("uncompletePayableFunds_max")))form.setUncompletePayableFunds_max(new BigDecimal(request.getParameter("uncompletePayableFunds_max")));
		return form;
	}
	
}
