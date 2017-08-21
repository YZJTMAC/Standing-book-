package cn.teacheredu.controller.project;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
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
import cn.teacheredu.entity.ProcessStepEntity;
import cn.teacheredu.entity.ProjectEntity;
import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.form.CommonForm;
import cn.teacheredu.form.ProjectChangeForm;
import cn.teacheredu.service.CompanyService;
import cn.teacheredu.service.ProcessService;
import cn.teacheredu.service.ProcessStepService;
import cn.teacheredu.service.ProjectChangeService;
import cn.teacheredu.service.ProjectService;
import cn.teacheredu.utils.CommonUtils;
import cn.teacheredu.utils.R;
import cn.teacheredu.utils.SystemConst;
import cn.teacheredu.vo.CompanyQueryVo;

@Controller
@RequestMapping(value = "/projectChange")
public class ProjectChangeController<E> extends Base3Controller {
	private static Logger logger = LoggerFactory.getLogger(ProjectChangeController.class);

	@Autowired
	private ProjectChangeService projectChangeService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private ProcessService processService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ProcessStepService processStepService;
	
	/**
	 * 到信息变更页面
	 * @param modelMap
	 * @param request
	 * @return
	 * @throws Exception 
	 * @throws  
	 */
	@RequestMapping(value = "/change", method = RequestMethod.GET)
	public String changeProjectPage(Integer projectId, ModelMap modelMap, HttpServletRequest request) throws Exception {
		UserEntity user = (UserEntity) modelMap.get("user");
		@SuppressWarnings("unchecked")
		List<String> psList = (List<String>) modelMap.get("psList");
		if (!CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_CHANGE_PROJECT, psList)) {
			modelMap.put("tipMsg", "抱歉...你没有变更项目信息的权限");
			return "tip";
		}
		logger.info("=====/project/changePage=====" + user.getLoginName());
		ProjectEntity projectEntity = projectService.getProjectById(projectId);
		if (projectEntity == null) {
			modelMap.put("tipMsg", "参数有误，进入页面失败。");
			return "tip";
		}
		@SuppressWarnings("unchecked")
		List<DepartmentEntity> dmList = (List<DepartmentEntity>) modelMap.get("dmList");
		if (dmList == null || dmList.size() == 0) {
			modelMap.put("tipMsg", "未查询到您的部门信息，进入页面失败。");
			return "tip";
		}
		if (!user.getId().equals(projectEntity.getUserId()) && !user.getId().toString().equals(dmList.get(0).getDirectorId())) {
			modelMap.put("tipMsg", "抱歉，只有本项目发起人和他的领导才可以变更项目的信息。");
			return "tip";
		}
		if (projectEntity.getStatus() >= 2) {
			modelMap.put("tipMsg", "本项目已结束或待结束状态，不可以申请信息变更了。");
			return "tip";
		}
		if (!this.processService.getIsCanApplyProjectChange(projectId)) {
			modelMap.put("tipMsg", "本项目当前存在项目信息变更流程，请等其审批结束后再提交新的信息变更申请。");
			return "tip";
		}
		//查询所有公司
		List<CompanyEntity> companyList = this.companyService.getCompanyByVo(new CompanyQueryVo());
		modelMap.put("companyList", companyList);
		modelMap.put("project", projectEntity);
		Date now = new Date();
		modelMap.put("title", "信息变更表-" + user.getRealname() + "-" + DateFormatUtils.format(now, "yyyy.MM.dd HH.mm"));
		return "project/changeProject";
	}

	/**
	 * 添加流程并修改项目信息-提交
	 * @param pcForm
	 * @param modelMap
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ajaxAddProcessAndChangeProject", method = RequestMethod.POST)
	public @ResponseBody R ajaxAddProcessAndChangeProject(@RequestBody ProjectChangeForm pcForm,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response) throws Exception{
		UserEntity user = (UserEntity) modelMap.get("user");
		CommonForm common = pcForm.getCommon();
		Integer projectId = pcForm.getProjectId();
		if (projectId == null) {
			return R.error("缺少项目ID参数，请联系管理员");
		}
		// 流程表添加一条数据，先算出当前待办人
		if (StringUtils.isBlank(common.getProcessName())) {
			return R.error("未获取到流程标题参数，请联系管理员");
		}
		@SuppressWarnings("unchecked")
		List<DepartmentEntity> dmList = (List<DepartmentEntity>) modelMap.get("dmList");
		if (dmList == null) {
			return R.error("未得到您的部门信息，请联系管理员");
		}
		DepartmentEntity departmentEntity = dmList.get(0);
		ProcessStepEntity stepEntity = this.processStepService.getProcessStepByDefineInfo(SystemConst.TABLE_PROJECTCHANGE, departmentEntity.getId(), null);
		if(stepEntity == null || StringUtils.isBlank(String.valueOf(stepEntity.getUserId()))){
			return R.error("未获取到您的流程步骤配置信息，请联系管理员做数据处理");
		}
		
		pcForm.setUserId(user.getId());
		pcForm.setUserName(user.getRealname());
		pcForm.setCurrStepUserId(stepEntity.getUserId());
		pcForm.setType(stepEntity.getType() == null ? 1 : stepEntity.getType()); //审批流程类型
		pcForm.setStepId(stepEntity.getId());
		
		this.projectChangeService.saveProjectChangeAndProcess(pcForm);
		
		return R.ok();
	}
	
}
