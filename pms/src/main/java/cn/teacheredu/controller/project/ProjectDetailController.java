package cn.teacheredu.controller.project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.teacheredu.controller.Base2Controller;
import cn.teacheredu.entity.CompanyEntity;
import cn.teacheredu.entity.DepartmentEntity;
import cn.teacheredu.entity.ProcessHistoryEntity;
import cn.teacheredu.entity.ProjectBudgetEntity;
import cn.teacheredu.entity.ProjectEntity;
import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.form.QueryTermsForm;
import cn.teacheredu.service.CompanyService;
import cn.teacheredu.service.DepartmentService;
import cn.teacheredu.service.OrganizationService;
import cn.teacheredu.service.PaymentInvoiceService;
import cn.teacheredu.service.PaymentService;
import cn.teacheredu.service.ProcessHistoryService;
import cn.teacheredu.service.ProjectBudgetService;
import cn.teacheredu.service.ProjectService;
import cn.teacheredu.service.UserService;
import cn.teacheredu.utils.CommonUtils;
import cn.teacheredu.utils.MyMathUtil;
import cn.teacheredu.utils.PageUtils;
import cn.teacheredu.utils.SystemConst;
import cn.teacheredu.vo.CompanyQueryVo;

@Controller
@RequestMapping(value = "/project")
public class ProjectDetailController extends Base2Controller{
	private static Logger logger = LoggerFactory.getLogger(ProjectDetailController.class);
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private ProjectBudgetService projectBudgetService;
	@Autowired
	private ProcessHistoryService processHistoryService;
	@Autowired
	private PaymentInvoiceService paymentInvoiceService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private CompanyService companyService;
	
	/**
	 * 我的项目页面 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public String myProjectPage(ModelMap modelMap, HttpServletRequest request) throws Exception{
		List<String> psList =  (List<String>) modelMap.get("psList");
		if (!CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_MY_PROJECT, psList)) {
			modelMap.put("tipMsg", "抱歉...你没有查看我的项目的权限");
			return "tip";
		}
		//1.列出基础条件中的部门列表
		List<DepartmentEntity> dmListQuery = null;
		boolean hasOtherPri = CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_QUERY_OTHERPRIVINCE, psList);
		boolean hasCompany = CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_QUERY_COMPANY, psList);
		if (hasOtherPri || hasCompany) { //用户拥有查询所有项目或者查询指定公司的项目的权限，这个时候部门列表要给用户列出所有的业务部门（渠道服务中心）
			dmListQuery = this.departmentService.getLocalDepartmentList("4");//部门类型，0 研发 1 教务 2 商务 3 财务 4 办事处、分公司、子公司 5 总经理办公室 6 分管部门
			DepartmentEntity dm = new DepartmentEntity();
			dm.setDmName("全部");
			dm.setProvince(-8);//我们用-8代表查询全部省份的项目，查询project时前端页面如果传来的是-8那sql的where里就不用拼province这列了
			dmListQuery.add(0, dm);
		} else { //只要不是查询全部部门的，那就是只查询自己部门的
			dmListQuery = (List<DepartmentEntity>) modelMap.get("dmList");
		}
		modelMap.put("dmListQuery", dmListQuery);
		//2.列出基础条件中的公司列表
		List<CompanyEntity> companyList = new ArrayList<CompanyEntity>();
		if (hasOtherPri) {//如果用户的权限是查询所有的项目，那么要给用户列出所有的公司
			companyList = this.companyService.getCompanyByVo(new CompanyQueryVo());
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
		
		//3.查询用户能不能填写基础条件中的发起人
		boolean hasOtherPer = CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_QUERY_OTHERPERSION, psList);
		if (hasOtherPri || hasOtherPer || hasCompany) {
			modelMap.put("changeUser", true);//可以输入发起人筛选项目
		} else {
			modelMap.put("changeUser", false);//只能查询自己发起的项目，就不能对发起人这个查询条件做任何输入
		}
		
		return "project/myProject";
	}
	
	/**
	 * 我的项目页面列表数据
	 */
	@RequestMapping(value="/myProjectDataList",method=RequestMethod.GET)
	public String myProjectDataListPage(QueryTermsForm form, ModelMap modelMap, HttpServletRequest request) throws Exception{
		@SuppressWarnings("unchecked")
		List<String> psList =  (List<String>) modelMap.get("psList");
		boolean hasOtherPri = CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_QUERY_OTHERPRIVINCE, psList);
		boolean hasOtherPer = CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_QUERY_OTHERPERSION, psList);
		boolean hasCompany = CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_QUERY_COMPANY, psList);
		if (!(hasOtherPri || hasOtherPer || hasCompany)) {//只能查询自己发起的项目
			UserEntity user = (UserEntity) modelMap.get("user");
			form.setUserId(user.getId());
		} else {
			List<String> companyList = new ArrayList<String>();//定义一个list存放用户要查询哪些公司的项目
			if ("all".equals(form.getCompany())) { //如果用户要查询他所能查询的所有公司的项目
				if (hasCompany) { //用户只能查询指定的公司
					for (String perms : psList) {
						if (perms.contains(SystemConst.PERMISSION_QUERY_COMPANY) && !perms.equals(SystemConst.PERMISSION_QUERY_COMPANY)) {
							CompanyEntity companyEntity = this.companyService.getCompanyById(Integer.parseInt(perms.substring(SystemConst.PERMISSION_QUERY_COMPANY.length()+1)));
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
		PageUtils<ProjectEntity> projectList = this.projectService.getProjectListByForm(form);
		modelMap.put("projectList", projectList);
		return "project/myProjectDataList";
	}
	
	/**
	 * 项目详情页
	 */
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public String showProjectDetailPage(Integer id, ModelMap modelMap, HttpServletRequest request) throws Exception{
		UserEntity user = (UserEntity) modelMap.get("user");
		logger.info("user " + user.getLoginName() + " view project " + id);
		@SuppressWarnings("unchecked")
		List<String> psList =  (List<String>) modelMap.get("psList");
		if (!CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_MY_PROJECT, psList)) {
			modelMap.put("tipMsg", "抱歉...你没有查看我的项目的权限");
			return "tip";
		}
		ProjectEntity project=projectService.getProjectById(id);
		if (project == null) {
			modelMap.put("tipMsg", "缺少参数，进入页面失败。");
			return "tip";
		}
		modelMap.put("project", project);
		
		String provincial = this.organizationService.getNameById(project.getProvincial());
		modelMap.put("provincial", provincial);
		String city = this.organizationService.getNameById(project.getCity());
		modelMap.put("city", city);
		String county = this.organizationService.getNameById(project.getCounty());
		modelMap.put("county", county);
		
		int len = provincial.length() + city.length() + county.length();
		String name = project.getName().substring(len, project.getName().length() - 2);
		modelMap.put("name", name);
		ProjectBudgetEntity pb = this.projectBudgetService.getProjectBudgetByProjectId(project.getId());
		modelMap.put("pb", pb);
		
		//预算表计算
		//合作经费
		BigDecimal qb = MyMathUtil.div(MyMathUtil.addStringToBigDecimal(project.getProFundCity(),project.getProFundCounty(),project.getProFundOther(),project.getProFundProvincial()), new BigDecimal("100.00"), 4); 
		BigDecimal hb = MyMathUtil.div(MyMathUtil.addStringToBigDecimal(project.getLaterFundCity(),project.getLaterFundCounty(),project.getLaterFundOther(),project.getLaterFundProvincial()), new BigDecimal("100.00"), 4); 
		BigDecimal qj = MyMathUtil.mul(project.getTotalMoney(),qb);
		BigDecimal hj = MyMathUtil.mul(project.getTotalMoney(),hb);
		modelMap.put("hj", hj);
		modelMap.put("qj", qj);
		//项目收入
		BigDecimal xmsr = MyMathUtil.sub(project.getTotalMoney(), qj);
		modelMap.put("xmsr", xmsr);
		//会议成本
		BigDecimal hycb = MyMathUtil.addBigDecimal(pb.getExpertCostBudget(),pb.getTransportCostBudget(),pb.getAccomCostBudget(),pb.getFeteCostBudget(),pb.getOfficeCostBudget(),pb.getRentalCostBudget(),pb.getInvestCostBudget(),pb.getOtherCostBudget());
		modelMap.put("hycb", hycb);
		//劳务费用
		BigDecimal lwfy = MyMathUtil.addBigDecimal(pb.getExpertLabourBudget(),pb.getCounsellorLabourBudget());
		modelMap.put("lwfy", lwfy);
		//项目成本
		BigDecimal xmcb = MyMathUtil.addBigDecimal(hj,hycb,lwfy);
		modelMap.put("xmcb", xmcb);
		//毛利
		BigDecimal ml = MyMathUtil.sub(xmsr, xmcb);
		modelMap.put("ml", ml);
		//毛利率
		if(project.getTotalMoney().compareTo(new BigDecimal("0"))!=0){
			BigDecimal mll = MyMathUtil.mul(MyMathUtil.div(ml, xmsr, 4), new BigDecimal("100"));
			modelMap.put("mll",  mll+"%");
		}else{
			modelMap.put("mll", "0.0000");
		}
		
		UserEntity projectCreateUse= userService.getUserEntityById(project.getUserId());
		modelMap.put("projectCreateUserName",projectCreateUse.getRealname());
		// 流程的审批记录
		List<ProcessHistoryEntity> phList = this.processHistoryService.getProcessHistoryByProjectId(id);

		// 流程审批对应步骤
		ProcessHistoryEntity ph = null;
		for (Iterator<ProcessHistoryEntity> it = phList.iterator(); it.hasNext();) {
			ph = it.next();
			if (ph.getStepNote() != null) {
				modelMap.put("ph_"+ ph.getStepNote().toString(), ph);
			}
			if (ph.getType() != 1) {//只查审批
				it.remove();
			}
		}
		modelMap.put("phList", phList);
		
		// 未开票款
		BigDecimal ykp = this.paymentInvoiceService.getTotalInvoiceByProjectId(id);//已开票
		BigDecimal ydk = this.paymentService.getRealPayTotalByProjectId(id);//总到款
		BigDecimal wkp = MyMathUtil.sub(ydk, ykp);
		modelMap.put("wkp", wkp);
		// 未到帐款
		BigDecimal wdz = MyMathUtil.sub(project.getTotalMoney(), ydk);
		modelMap.put("wdz", wdz);
		return "project/projectDetail";
		
	}
}
