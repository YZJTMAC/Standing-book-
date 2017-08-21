package cn.teacheredu.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.ProcessEntity;
import cn.teacheredu.entity.ProjectChangeEntity;
import cn.teacheredu.form.CommonForm;
import cn.teacheredu.form.ProjectChangeForm;
import cn.teacheredu.mapping.ProjectChangeMapper;
import cn.teacheredu.service.AttachmentService;
import cn.teacheredu.service.ProcessService;
import cn.teacheredu.service.ProjectChangeService;
import cn.teacheredu.utils.SystemConst;
import cn.teacheredu.vo.ProjectChangeQueryVo;

@Service
public class ProjectChangeServiceImpl implements ProjectChangeService {

	@Autowired
	private ProjectChangeMapper projectChangeMapper;
	@Autowired
	private ProcessService processService;
	@Autowired
	private AttachmentService attachmentService;
	
	@Override
	public ProjectChangeEntity getProjectChangeById(Integer projectChangeId) throws Exception {
		return this.projectChangeMapper.selectByPrimaryKey(projectChangeId);
	}

	@Override
	public List<ProjectChangeEntity> getProjectChangeByVo(ProjectChangeQueryVo vo) throws Exception {
		return this.projectChangeMapper.selectByExample(vo);
	}

	@Override
	public int insertProjectChangeEntity(ProjectChangeEntity projectChangeEntity) throws Exception {
		this.projectChangeMapper.insertSelective(projectChangeEntity);
		return projectChangeEntity.getId();
	}

	@Override
	public long getCountByVo(ProjectChangeQueryVo vo) throws Exception {
		return this.projectChangeMapper.countByExample(vo);
	}

	@Override
	public int updateProjectChange(ProjectChangeEntity projectChangeEntity) throws Exception {
		return this.projectChangeMapper.updateByPrimaryKeySelective(projectChangeEntity);
	}

	@Override
	public int deteleProjectChangeById(Integer projectChangeId) throws Exception {
		return this.projectChangeMapper.deleteByPrimaryKey(projectChangeId);
	}

	@Override
	public void saveProjectChangeAndProcess(ProjectChangeForm pcForm) throws Exception {
		CommonForm common = pcForm.getCommon();
		Date date = new Date();
		ProcessEntity processEntity = new ProcessEntity();
		processEntity.setCreateUserId(pcForm.getUserId());
		processEntity.setStartTime(date);
		processEntity.setCreateTime(date);
		processEntity.setCurrStepUserId(pcForm.getCurrStepUserId());
		processEntity.setLastStepUserId(pcForm.getUserId());
		processEntity.setLastStepTime(date);
		processEntity.setTableName(SystemConst.TABLE_PROJECTCHANGE);
		processEntity.setType(pcForm.getType());//审批流程类型
		processEntity.setStatus(3);//进行中
		processEntity.setCreateUserName(pcForm.getUserName());
		processEntity.setTitle(common.getProcessName());
		processEntity.setObjectId(null);
		processEntity.setProjectId(pcForm.getProjectId());
		processEntity.setStepId(pcForm.getStepId());
		int k = this.processService.insertProcessEntity(processEntity);
		this.attachmentService.updateAttachmentByIdAndName(common.getAttachIds(), SystemConst.TABLE_PROCESS, k);
		ProjectChangeEntity pc = new ProjectChangeEntity();
		pc.setProjectId(pcForm.getProjectId());
		pc.setProcessId(k);
		pc.setUserId(pcForm.getUserId());
		pc.setNote(pcForm.getNote());
		if (pcForm.getExpectedNum() != null) {
			pc.setId(null);
			pc.setColumnName("expected_num");
			pc.setColumnValue(pcForm.getExpectedNum().toString());
			pc.setReason(pcForm.getExpectedNumReason());
			this.insertProjectChangeEntity(pc);
		}
		if (pcForm.getChargeStandard() != null) {
			pc.setId(null);
			pc.setColumnName("charge_standard");
			pc.setColumnValue(pcForm.getChargeStandard().toString());
			pc.setReason(pcForm.getChargeStandardReason());
			this.insertProjectChangeEntity(pc);
		}
		if (pcForm.getStartDate() != null) {
			pc.setId(null);
			pc.setColumnName("start_date");
			pc.setColumnValue(DateFormatUtils.format(pcForm.getStartDate(), "yyyy-MM-dd HH:mm:ss"));
			pc.setReason(pcForm.getStartDateReason());
			this.insertProjectChangeEntity(pc);
		}
		if (pcForm.getEndDate() != null) {
			pc.setId(null);
			pc.setColumnName("end_date");
			pc.setColumnValue(DateFormatUtils.format(pcForm.getEndDate(), "yyyy-MM-dd HH:mm:ss"));
			pc.setReason(pcForm.getEndDateReason());
			this.insertProjectChangeEntity(pc);
		}
		if (StringUtils.isNotBlank(pcForm.getCollectMoneyCompany())) {
			pc.setId(null);
			pc.setColumnName("collect_money_company");
			pc.setColumnValue(pcForm.getCollectMoneyCompany());
			pc.setReason(pcForm.getCollectMoneyCompanyReason());
			this.insertProjectChangeEntity(pc);
		}
		if (StringUtils.isNotBlank(pcForm.getProFundProvincial())) {
			pc.setId(null);
			pc.setColumnName("pro_fund_provincial");
			pc.setColumnValue(pcForm.getProFundProvincial());
			pc.setReason(pcForm.getProFundProvincialReason());
			this.insertProjectChangeEntity(pc);
		}
		if (StringUtils.isNotBlank(pcForm.getProFundCity())) {
			pc.setId(null);
			pc.setColumnName("pro_fund_city");
			pc.setColumnValue(pcForm.getProFundCity());
			pc.setReason(pcForm.getProFundCityReason());
			this.insertProjectChangeEntity(pc);
		}
		if (StringUtils.isNotBlank(pcForm.getProFundCounty())) {
			pc.setId(null);
			pc.setColumnName("pro_fund_county");
			pc.setColumnValue(pcForm.getProFundCounty());
			pc.setReason(pcForm.getProFundCountyReason());
			this.insertProjectChangeEntity(pc);
		}
		if (StringUtils.isNotBlank(pcForm.getProFundOther())) {
			pc.setId(null);
			pc.setColumnName("pro_fund_other");
			pc.setColumnValue(pcForm.getProFundOther());
			pc.setReason(pcForm.getProFundOtherReason());
			this.insertProjectChangeEntity(pc);
		}
		if (StringUtils.isNotBlank(pcForm.getLaterFundProvincial())) {
			pc.setId(null);
			pc.setColumnName("later_fund_provincial");
			pc.setColumnValue(pcForm.getLaterFundProvincial());
			pc.setReason(pcForm.getLaterFundProvincialReason());
			this.insertProjectChangeEntity(pc);
		}
		if (StringUtils.isNotBlank(pcForm.getLaterFundCity())) {
			pc.setId(null);
			pc.setColumnName("later_fund_city");
			pc.setColumnValue(pcForm.getLaterFundCity());
			pc.setReason(pcForm.getLaterFundCityReason());
			this.insertProjectChangeEntity(pc);
		}
		if (StringUtils.isNotBlank(pcForm.getLaterFundCounty())) {
			pc.setId(null);
			pc.setColumnName("later_fund_county");
			pc.setColumnValue(pcForm.getLaterFundCounty());
			pc.setReason(pcForm.getLaterFundCountyReason());
			this.insertProjectChangeEntity(pc);
		}
		if (StringUtils.isNotBlank(pcForm.getLaterFundOther())) {
			pc.setId(null);
			pc.setColumnName("later_fund_other");
			pc.setColumnValue(pcForm.getLaterFundOther());
			pc.setReason(pcForm.getLaterFundOtherReason());
			this.insertProjectChangeEntity(pc);
		}
		
	}

}
