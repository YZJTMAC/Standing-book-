package cn.teacheredu.controller.project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.teacheredu.controller.Base3Controller;
import cn.teacheredu.entity.CompanyEntity;
import cn.teacheredu.entity.DepartmentEntity;
import cn.teacheredu.entity.ProcessEntity;
import cn.teacheredu.entity.ProcessStepEntity;
import cn.teacheredu.entity.ProjectEntity;
import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.form.CommonForm;
import cn.teacheredu.form.ProjectEndForm;
import cn.teacheredu.form.ProjectEndSpecificityForm;
import cn.teacheredu.service.AttachmentService;
import cn.teacheredu.service.CompanyService;
import cn.teacheredu.service.PaymentInvoiceService;
import cn.teacheredu.service.PaymentService;
import cn.teacheredu.service.ProcessService;
import cn.teacheredu.service.ProcessStepService;
import cn.teacheredu.service.ProjectEndService;
import cn.teacheredu.service.ProjectService;
import cn.teacheredu.utils.CommonUtils;
import cn.teacheredu.utils.MyMathUtil;
import cn.teacheredu.utils.R;
import cn.teacheredu.utils.SystemConst;
import cn.teacheredu.vo.CompanyQueryVo;
import cn.teacheredu.vo.ProjectEntityQueryVo;


@Controller
@RequestMapping(value = "/projectEnd")
public class ProjectEndController extends Base3Controller{
	
	private static Logger logger = LoggerFactory.getLogger(ProjectEndController.class);
	
	@Autowired
	private ProjectEndService projectEndService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private PaymentInvoiceService paymentInvoiceService;
	
	@Autowired
	private ProcessService processService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private ProcessStepService processStepService;
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String projectEndPage(ModelMap modelMap, HttpServletRequest request) throws Exception{
		UserEntity user = (UserEntity) modelMap.get("user");
		@SuppressWarnings("unchecked")
		List<String> psList =  (List<String>) modelMap.get("psList");
		if (!CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_CREATE_COMPLETE, psList)) {
			modelMap.put("tipMsg", "抱歉...你没有新建项目完结的权限");
			return "tip";
		}
		logger.info("=====/project/addProjectEnd=====" + user.getLoginName());
		Date now = new Date();
		modelMap.put("title", "项目完结表-"+user.getRealname()+"-"+DateFormatUtils.format(now, "yyyy.MM.dd HH.mm"));
		//项目截止月份
		modelMap.put("projectEndMonth", DateFormatUtils.format(now, "yyyy年MM月"));
		//查询所有公司
		List<CompanyEntity> companyList = this.companyService.getCompanyByVo(new CompanyQueryVo());
		modelMap.put("companyList", companyList);
		return "project/addProjectEnd";
		
	}
	
	/**
	 * 根据公司名称查询其待完结的项目
	 * 现在暂时不能自动完结，需要项目发起人点击项目完结按钮把项目状态置为待完结的状态
	 * 然后省份负责人（发起人的领导）在这里才能查询到这些项目
	 * @param collectMoneyCompany 公司名称
	 * @param modelMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryListProjectList",method=RequestMethod.GET)
	public String queryListProjectList(String collectMoneyCompany, ModelMap modelMap, HttpServletRequest request) throws Exception{
		if (StringUtils.isBlank(collectMoneyCompany)) {
			modelMap.put("projectEntityList", new ArrayList<ProjectEntity>());
		} else {
			@SuppressWarnings("unchecked")
			List<DepartmentEntity> dmList = (List<DepartmentEntity>) modelMap.get("dmList");
			if (dmList.size() > 0) {
				ProjectEntityQueryVo vo = new ProjectEntityQueryVo();
				vo.setCollectMoneyCompany(collectMoneyCompany);
				if (dmList.size() > 1) {
					List<Integer> proList = new ArrayList<Integer>();
					for (DepartmentEntity departmentEntity : dmList) {
						proList.add(departmentEntity.getProvince());
					}
					vo.setProvincialList(proList);
				} else {
					vo.setProvincial(dmList.get(0).getProvince());
				}
				vo.setStatus((byte)4);//查询这个省份下 已经由项目发起人确认项目完结的项目
				List<ProjectEntity> projectList = this.projectService.selectProjectForEnd(vo);
				for (ProjectEntity project : projectList) {
					BigDecimal qb = MyMathUtil.div(MyMathUtil.addStringToBigDecimal(project.getProFundCity(),project.getProFundCounty(),project.getProFundOther(),project.getProFundProvincial()), new BigDecimal("100.00"), 4);
					BigDecimal qj = MyMathUtil.mul(project.getTotalMoney(),qb);
					BigDecimal realTotalPay = this.paymentService.getRealPayTotalByProjectId(project.getId());
					BigDecimal realTotalInvoice = this.paymentInvoiceService.getTotalInvoiceByProjectId(project.getId());
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("qhj", qj);//项目前期合作经费，在算实际总额时会用到
					map.put("realTotalPay", realTotalPay);//总到款数
					map.put("realTotalInvoice", realTotalInvoice);//总开票数
					project.setExtInfo(map);
				}
				modelMap.put("projectEntityList", projectList);
			} else {
				modelMap.put("projectEntityList", new ArrayList<ProjectEntity>());
			}
		}

		return "project/projectTable";
	}
	
	@RequestMapping(value="/ajaxAddProcessAndProjectEnd",method=RequestMethod.POST)
	public @ResponseBody R ajaxAddProcessAndProjectEnd(@RequestBody ProjectEndSpecificityForm psf,ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		UserEntity user = (UserEntity) model.get("user");
		CommonForm common=psf.getCommon();
		List<ProjectEndForm> projectEndArr=psf.getProjectEndArr();
		//流程表添加一条数据，先算出当前待办人
		if (common == null || StringUtils.isBlank(common.getProcessName())) {
			return R.error("未获取到流程标题参数，请联系管理员做数据处理");
		}
		if (projectEndArr == null || projectEndArr.size() == 0) {
			return R.error("未获取到表单数据");
		}
		for (ProjectEndForm projectEndForm : projectEndArr) {
			String validResult = validateProjectEnd(projectEndForm);
			if (!"success".equals(validResult)) {
				return R.error(validResult);
			}
		}
		@SuppressWarnings("unchecked")
		List<DepartmentEntity> dmList = (List<DepartmentEntity>) model.get("dmList");
		if (dmList == null || dmList.size() == 0) {
			return R.error("未得到您的部门信息，请联系管理员做数据处理");
		}
		DepartmentEntity departmentEntity = dmList.get(0);
		ProcessStepEntity stepEntity = this.processStepService.getProcessStepByDefineInfo(SystemConst.TABLE_PROJECTEND, departmentEntity.getId(), null);
		if(stepEntity == null || StringUtils.isBlank(String.valueOf(stepEntity.getUserId()))){
			return R.error("未获取到您的流程步骤配置信息，请联系管理员做数据处理");
		}
		
		Date date = new Date();
		ProcessEntity processEntity = new ProcessEntity();
		processEntity.setCreateUserId(user.getId());
		processEntity.setStartTime(date);
		processEntity.setCreateTime(date);
		processEntity.setCurrStepUserId(stepEntity.getUserId());
		processEntity.setLastStepUserId(user.getId());
		processEntity.setLastStepTime(date);
		processEntity.setTitle(common.getProcessName());
		processEntity.setTableName(SystemConst.TABLE_PROJECTEND);
		//processEntity.setProjectId(null);
		//processEntity.setObjectId(null);
		processEntity.setType(stepEntity.getType() == null ? 1 : stepEntity.getType()); //审批流程类型
		processEntity.setStatus(3);//进行中
		processEntity.setCreateUserName(user.getRealname());
		processEntity.setStepId(stepEntity.getId());
		
//		if (pricessid > 0) {
			//有附件的话保存附件
			for (ProjectEndForm projectEndForm : projectEndArr) {
				processEntity.setProjectId(projectEndForm.getProjectId());
				int pricessid=this.processService.insertProcessEntity(processEntity);
				this.attachmentService.updateAttachmentByIdAndName(common.getAttachIds(), SystemConst.TABLE_PROCESS, pricessid);
				projectEndForm.setProcessId(pricessid);
				projectEndForm.setUid(user.getId());
				projectEndForm.setCreateTime(date);
			}
			this.projectEndService.insertProjectEndBatch(projectEndArr,user.getId());
			return R.ok();
//		} else {
//			return R.error("未知原因");
//		}
	}
	
	private String validateProjectEnd(ProjectEndForm projectEnd ) {
		if(projectEnd.getRealNum()== null){
			return "上线人数不能为空";	
		}
		if(projectEnd.getChargeCount()== null){
			return "收费人数不能为空";	
		}
		if(projectEnd.getLaterPay()==null){
			return "后期汇款金额不能为空";	
		}
		if(projectEnd.getNotTotalPay()==null){
			return "未到款金额不能为空";	
		}
		if(projectEnd.getLaterPay().compareTo(projectEnd.getNotTotalPay())==(-1)){
			if(StringUtils.isBlank(projectEnd.getNote())){;	
				return "备注不能为空";
			}	
		}
		return "success";
	}
}
