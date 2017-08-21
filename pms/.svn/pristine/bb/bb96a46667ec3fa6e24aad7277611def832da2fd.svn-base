package cn.teacheredu.controller.project;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.teacheredu.controller.Base3Controller;
import cn.teacheredu.entity.AttachmentEntity;
import cn.teacheredu.entity.CompanyEntity;
import cn.teacheredu.entity.DepartmentEntity;
import cn.teacheredu.entity.OrganizationEntity;
import cn.teacheredu.entity.ProcessEntity;
import cn.teacheredu.entity.ProcessStepEntity;
import cn.teacheredu.entity.ProjectBudgetEntity;
import cn.teacheredu.entity.ProjectEntity;
import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.form.CommonForm;
import cn.teacheredu.form.ProjectForm;
import cn.teacheredu.service.AttachmentService;
import cn.teacheredu.service.CompanyService;
import cn.teacheredu.service.DepartmentService;
import cn.teacheredu.service.OrganizationService;
import cn.teacheredu.service.ProcessService;
import cn.teacheredu.service.ProcessStepService;
import cn.teacheredu.service.ProjectBudgetService;
import cn.teacheredu.service.ProjectService;
import cn.teacheredu.service.SystemLogService;
import cn.teacheredu.utils.CommonUtils;
import cn.teacheredu.utils.R;
import cn.teacheredu.utils.SystemConst;
import cn.teacheredu.vo.CompanyQueryVo;

@Controller
@RequestMapping(value = "/project")
public class ProjectController extends Base3Controller{
	private static Logger logger = LoggerFactory.getLogger(ProjectController.class);
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private ProjectBudgetService projectBudgetService;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private ProcessService processService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ProcessStepService processStepService;
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addProjectPage(ModelMap modelMap, HttpServletRequest request) throws Exception{
		UserEntity user = (UserEntity) modelMap.get("user");
		@SuppressWarnings("unchecked")
		List<String> psList =  (List<String>) modelMap.get("psList");
		if (!CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_CREATE_PROJECT, psList)) {
			modelMap.put("tipMsg", "抱歉...你没有新建项目的权限");
			return "tip";
		}
		logger.info("=====/project/addPage=====" + user.getLoginName());
		Date now = new Date();
		modelMap.put("title", "项目确认表-"+user.getRealname()+"-"+DateFormatUtils.format(now, "yyyy.MM.dd HH.mm"));
		modelMap.put("writeTime", DateFormatUtils.format(now, "yyyy-MM-dd"));
		
		//查询所有省份
		List<OrganizationEntity> provinceList = this.organizationService.getListByParentId(0);
		modelMap.put("provinceList", provinceList);
		//查询所有公司
		List<CompanyEntity> companyList = this.companyService.getCompanyByVo(new CompanyQueryVo());
		modelMap.put("companyList", companyList);
		return "project/addProject";
		
	}
	
	@RequestMapping(value="/editAndSend",method=RequestMethod.GET)
	public String editProjectPage(Integer procId, ModelMap modelMap, HttpServletRequest request) throws Exception{
		UserEntity user = (UserEntity) modelMap.get("user");
		@SuppressWarnings("unchecked")
		List<String> psList =  (List<String>) modelMap.get("psList");
		if (!CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_CREATE_PROJECT, psList)) {
			modelMap.put("tipMsg", "抱歉...你没有新建项目的权限");
			return "tip";
		}
		
		ProcessEntity process = this.processService.getProcessById(procId);
		ProjectEntity project = this.projectService.getProjectById(process ==null?null: process.getProjectId());
		if (project == null || process == null) {
			modelMap.put("tipMsg", "参数有误");
			return "tip";
		}
		if (!process.getCreateUserId().equals(user.getId())) {
			modelMap.put("tipMsg", "出错了：您没有权限修改此表单，请联系管理员。");
			return "tip";
		}
		modelMap.put("project", project);
		modelMap.put("process", process);
		
		modelMap.put("title", process.getTitle());
		modelMap.put("writeTime", DateFormatUtils.format(project.getWriteTime(), "yyyy-MM-dd"));
		
		//查询所有省份
		List<OrganizationEntity> provinceList = this.organizationService.getListByParentId(0);
		modelMap.put("provinceList", provinceList);
		if (project.getCity() !=null) {
			
		}
		
		
		// 流程的附件
		List<AttachmentEntity> atList = this.attachmentService.getAttachmentByProcessId(procId);
		modelMap.put("atList", atList);
		modelMap.put("isEdit", true);
		//return "project/addProject";
		modelMap.put("tipMsg", "重新提交功能暂未开发，请新建表单去提交");
		return "tip";
	}
	
	
	
	@RequestMapping(value="/ajaxAddProject",method=RequestMethod.POST)
	public @ResponseBody R ajaxAddProject(@RequestBody ProjectForm project, ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		UserEntity user = (UserEntity) model.get("user");
		String validResult = this.validateProject(project);
		if (!"success".equals(validResult)) {
			return R.error(validResult);
		}
		
		ProjectEntity projectEntity = new ProjectEntity();
		BeanUtils.copyProperties(project, projectEntity);
		projectEntity.setUserId(user.getId());
		//重新计算项目名称
		String name = this.organizationService.generateProjecctNameByIds(project.getProvincial(),project.getCity(), project.getCounty(),project.getName());
		projectEntity.setName(name);
		//计算项目编码
		projectEntity.setSerialNumber(this.organizationService.generateSerialNameByIds(project.getProvincial(), project.getCity(), project.getCounty(), project.getFormat(), project.getTrainObject()));
		//计算所属大区
		@SuppressWarnings("unchecked")
		List<DepartmentEntity> dmList = (List<DepartmentEntity>) model.get("dmList");
		if (dmList != null) {
			DepartmentEntity departmentEntity = dmList.get(0);
			Integer prov = departmentEntity.getProvince();
			if (prov != null) {  //申请项目的人所在部门必须配置所在省份
				if (prov < 0) {
					projectEntity.setChargeArea(prov);
				} else {
					DepartmentEntity parent = this.departmentService.getDepartmentById(departmentEntity.getParentId());
					if (parent != null) { //申请项目的人所在部门必须配置部门的上级部门属于哪个分管大区
						projectEntity.setChargeArea(parent.getProvince() < 0 ? parent.getProvince() : null);
					}
				}
			}
		}
		projectEntity.setGenTime(new Date());
		projectEntity.setStatus((byte)0);
		int pid = this.projectService.insertProjectEntity(projectEntity);
		if (pid > 0) {
			ProjectBudgetEntity pb = new ProjectBudgetEntity();
			BeanUtils.copyProperties(project, pb);
			pb.setProjectId(pid);
			this.projectBudgetService.insertProjectBudgetEntity(pb);
			this.systemLogService.saveSystemLog(1, projectEntity.toString(), user.getId());
			return R.ok().put("objectId", pid).put("projectId", pid);
		} else {
			return R.error("未知原因");
		}
		
	}
	@RequestMapping(value="/ajaxAddProcess",method=RequestMethod.POST)
	public @ResponseBody R ajaxAddProcess(@RequestBody CommonForm common, ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		UserEntity user = (UserEntity) model.get("user");
		if (common == null || common.getObjectId() == null) {
			return R.error("未获取到项目ID参数,请联系管理员做数据处理");
		} else {
			//流程表添加一条数据，先算出当前待办人
			if (StringUtils.isBlank(common.getProcessName())) {
				return R.error("未获取到流程标题参数，请联系管理员做数据处理");
			}
			@SuppressWarnings("unchecked")
			List<DepartmentEntity> dmList = (List<DepartmentEntity>) model.get("dmList");
			if (dmList == null) {
				return R.error("未得到您的部门信息，请联系管理员做数据处理");
			}
			
			DepartmentEntity departmentEntity = dmList.get(0);
			ProcessStepEntity stepEntity = this.processStepService.getProcessStepByDefineInfo(SystemConst.TABLE_PROJECT, departmentEntity.getId(), null);
			if(stepEntity == null || StringUtils.isBlank(String.valueOf(stepEntity.getUserId()))){
				return R.error("未获取到您的流程步骤配置信息，请联系管理员做数据处理");
			}
			
			ProcessEntity processEntity = new ProcessEntity();
			processEntity.setCreateUserId(user.getId());
			processEntity.setStartTime(new Date());
			processEntity.setCreateTime(new Date());
			processEntity.setCurrStepUserId(stepEntity.getUserId());
			processEntity.setLastStepUserId(user.getId());
			processEntity.setLastStepTime(new Date());
			processEntity.setTitle(common.getProcessName());
			processEntity.setProjectId(common.getObjectId());
			processEntity.setObjectId(common.getObjectId());
			processEntity.setTableName(SystemConst.TABLE_PROJECT);
			processEntity.setType(stepEntity.getType() == null ? 1 : stepEntity.getType()); //审批流程类型
			processEntity.setStatus(3);//进行中
			processEntity.setCreateUserName(user.getRealname());
			processEntity.setStepId(stepEntity.getId());
			Integer id = this.processService.insertProcessEntity(processEntity);
			if (id == null || id == 0) {
				return R.error("未知原因");
			} else {
				this.systemLogService.saveSystemLog(1, processEntity.toString(), user.getId());
				//有附件的话保存附件
				this.attachmentService.updateAttachmentByIdAndName(common.getAttachIds(), SystemConst.TABLE_PROCESS, id);
			}
		}
		return R.ok();
	}
	
	private String validateProject(ProjectForm project) {
		if (StringUtils.isBlank(project.getName()))
			return "项目名称不能为空";
		if (project.getType() == 0)
			return "您没有选择项目类型";
		if (project.getFormat() == 0)
			return "您没有选择培训形式";
		if (project.getProvincial() == 0)
			return "您没有选择项目所在省份";
		if (StringUtils.isBlank(project.getTrainObject()))
			return "培训对象不能为空";
		if (StringUtils.isBlank(project.getTrainPlatform()))
			return "培训平台不能为空";
		if (StringUtils.isBlank(project.getName()))
			return "项目名称不能为空";
		if (project.getStartDate() == null)
			return "您没有选择培训开始日期";
		if (project.getEndDate() == null)
			return "您没有选择培训结束日期";
		if (project.getRealNum() == null)
			return "预估人数不能为空";
		if (project.getChargeStandard() == null)
			return "收费标准不能为空";
		if (project.getStudyTime() == null)
			return "学时不能为空";
		if (StringUtils.isBlank(project.getCooperName()))
			return "合作单位名称不能为空";
		if (StringUtils.isBlank(project.getCooperAddr()))
			return "合作单位地址不能为空";
		if (StringUtils.isBlank(project.getCooperHeadNameFirst()) && StringUtils.isBlank(project.getCooperHeadNameSecond()))
			return "合作单位负责人和联系人至少有一个要填写完整";
		if (StringUtils.isBlank(project.getProFundProvincial()))
			return "项目前期合作经费省级所占比例不能为空，没有就填0";
		if (StringUtils.isBlank(project.getProFundCity()))
			return "项目前期合作经费市级所占比例不能为空，没有就填0";
		if (StringUtils.isBlank(project.getProFundCounty()))
			return "项目前期合作经费县级所占比例不能为空，没有就填0";
		if (StringUtils.isBlank(project.getProFundOther()))
			return "项目前期合作经费其它所占比例不能为空，没有就填0";
		if (StringUtils.isBlank(project.getLaterFundProvincial()))
			return "项目后期合作经费省级所占比例不能为空，没有就填0";
		if (StringUtils.isBlank(project.getLaterFundCity()))
			return "项目后期合作经费市级所占比例不能为空，没有就填0";
		if (StringUtils.isBlank(project.getLaterFundCounty()))
			return "项目后期合作经费县级所占比例不能为空，没有就填0";
		if (StringUtils.isBlank(project.getLaterFundOther()))
			return "项目后期合作经费其他所占比例不能为空，没有就填0";
		if (StringUtils.isBlank(project.getProtocolName()))
			return "项目协议书名称不能为空";
		if (project.getProtocolTime() == null)
			return "您没有选择签署日期";
		if (project.getCollectMoneyDate() == null)
			return "您没有选择预计到款日期";
		if (StringUtils.isBlank(project.getCollectMoneyCompany()))
			return "到款单位名称不能为空";
		if (project.getWriteTime() == null)
			return "填表日期有误";
		return "success";
	}
	
	@RequestMapping(value="/ajaxEndProject",method=RequestMethod.POST)
	public @ResponseBody R ajaxEndProject(@RequestBody CommonForm common, ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		UserEntity user = (UserEntity) model.get("user");
		if (common.getProjectId() == null) {
			return R.error("项目ID参数有误");
		}
		ProjectEntity project = this.projectService.getProjectById(common.getProjectId());
		@SuppressWarnings("unchecked")
		List<DepartmentEntity> dmList = (List<DepartmentEntity>) model.get("dmList");
		if (dmList == null || dmList.size() == 0) {
			return R.error("未查询到您的部门信息，设置失败。");
		}
		if (!user.getId().equals(project.getUserId()) && !user.getId().toString().equals(dmList.get(0).getDirectorId())) {
			return R.error("抱歉，只有这个项目的发起人和他的领导才可以设置项目为待完结状态。");
		}
		if (project.getStatus() == 2) {
			return R.error("本项目已经是结束状态了。");
		}
		project.setStatus(new Byte("4"));
		int i = this.projectService.updateProject(project);
		if (i > 0) {
			this.systemLogService.saveSystemLog(2, project.toString(), user.getId());
			return R.ok();
		} else {
			return R.error("未知原因");
		}
	}
}
