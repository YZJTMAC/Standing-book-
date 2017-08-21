package cn.teacheredu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.NeedDealProcessEntity;
import cn.teacheredu.entity.ProcessEntity;
import cn.teacheredu.form.QueryTermsForm;
import cn.teacheredu.mapping.NeedDealProcessMapper;
import cn.teacheredu.mapping.ProcessMapper;
import cn.teacheredu.service.ProcessService;
import cn.teacheredu.service.UserService;
import cn.teacheredu.utils.PageUtils;
import cn.teacheredu.utils.SystemConst;
import cn.teacheredu.vo.NeedDealProcessQueryVo;
import cn.teacheredu.vo.ProcessEntityQueryVo;

@Service
public class ProcessServiceImpl implements ProcessService {

	@Autowired
	private ProcessMapper processMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private NeedDealProcessMapper needDealProcessMapper;
	
	@Override
	public ProcessEntity getProcessById(Integer processId) throws Exception {
		return this.processMapper.selectByPrimaryKey(processId);
	}

	@Override
	public List<ProcessEntity> getProcessByVo(ProcessEntityQueryVo vo) throws Exception {
		return this.processMapper.selectByExample(vo);
	}

	@Override
	public int insertProcessEntity(ProcessEntity processEntity) throws Exception {
		this.processMapper.insertSelective(processEntity);
		if (processEntity.getId() != null && processEntity.getId() != 0 && processEntity.getStatus() != 0) {
			NeedDealProcessEntity needDealProcessEntity = new NeedDealProcessEntity();
			needDealProcessEntity.setProcessId(processEntity.getId());
			needDealProcessEntity.setProcessName(processEntity.getTitle());
			needDealProcessEntity.setUserId(processEntity.getCurrStepUserId());
			needDealProcessEntity.setCreateUserId(processEntity.getCreateUserId());
			needDealProcessEntity.setCreateUserName(processEntity.getCreateUserName());
			needDealProcessEntity.setCreateTime(processEntity.getLastStepTime());
			needDealProcessEntity.setStartTime(processEntity.getStartTime());
			needDealProcessEntity.setType(processEntity.getType());
			needDealProcessEntity.setTimeLimit(processEntity.getTotalTime());
			this.needDealProcessMapper.insertSelective(needDealProcessEntity);
		}
		return processEntity.getId();
	}

	@Override
	public long getCountByVo(ProcessEntityQueryVo vo) throws Exception {
		return this.processMapper.countByExample(vo);
	}

	@Override
	public int updateProcess(ProcessEntity processEntity) throws Exception {
		return this.processMapper.updateByPrimaryKeySelective(processEntity);
	}

	@Override
	public int deteleProcessById(Integer processId) throws Exception {
		return this.processMapper.deleteByPrimaryKey(processId);
	}

	@Override
	public PageUtils<NeedDealProcessEntity> getNeedProcessByUserId(Integer userId, Integer curPage, Integer pageSize) throws Exception {
		PageUtils<NeedDealProcessEntity> pageUtil = null;
		if (userId == null || curPage == null || pageSize == null){
			pageUtil = new PageUtils<NeedDealProcessEntity>(new ArrayList<NeedDealProcessEntity>(), 0, pageSize, curPage);
		} else {
			NeedDealProcessQueryVo vo = new NeedDealProcessQueryVo();
			vo.setOrderProperty("id");
			vo.setOrderType(SystemConst.SQL_ORDERTYPE_DESC);
			vo.setPaged(true);
			vo.setPageSize(pageSize);
			vo.setCurPage(curPage);
			vo.setStartPosition((curPage - 1) * pageSize);
			vo.setUserId(userId);
			vo.setFinished(0);
			
			List<NeedDealProcessEntity> list = this.needDealProcessMapper.selectByExample(vo);
			long total = this.needDealProcessMapper.countByExample(vo);
			pageUtil = new PageUtils<NeedDealProcessEntity>(list, total, pageSize, curPage);
		}
		return pageUtil;
	}
	
	@Override
	public PageUtils<ProcessEntity> getNeedSendProcessByUserId(Integer userId, Integer status, Integer curPage, Integer pageSize) throws Exception {
		PageUtils<ProcessEntity> pageUtil = null;
		if (userId == null || curPage == null || pageSize == null){
			pageUtil = new PageUtils<ProcessEntity>(new ArrayList<ProcessEntity>(), 0, pageSize, curPage);
		} else {
			ProcessEntityQueryVo vo = new ProcessEntityQueryVo();
			vo.setOrderProperty("id");
			vo.setOrderType(SystemConst.SQL_ORDERTYPE_DESC);
			vo.setPaged(true);
			vo.setPageSize(pageSize);
			vo.setCurPage(curPage);
			vo.setStartPosition((curPage - 1) * pageSize);
			vo.setCreateUserId(userId);
			vo.setStatus(status);// 待发-1  已发-2
			List<ProcessEntity> list = this.getProcessByVo(vo);
			long total = this.getCountByVo(vo);
			if (status == -2) {
				this.userService.dealList(list, "currStepUserId");//赋值当前待办人的真实姓名
			}
			pageUtil = new PageUtils<ProcessEntity>(list, total, pageSize, curPage);
		}
		return pageUtil;
	}

	@Override
	public PageUtils<NeedDealProcessEntity> getNeedDealByForm(QueryTermsForm form) throws Exception {
		PageUtils<NeedDealProcessEntity> pageUtil = null;
		if (form.getUserId() == null){
			pageUtil = new PageUtils<NeedDealProcessEntity>(new ArrayList<NeedDealProcessEntity>(), 0, form.getPageSize(), form.getCurrPage());
		} else {
			NeedDealProcessQueryVo vo = new NeedDealProcessQueryVo();
			vo.setOrderProperty("id");
			vo.setOrderType(SystemConst.SQL_ORDERTYPE_DESC);
			vo.setPaged(true);
			vo.setPageSize(form.getPageSize());
			vo.setCurPage(form.getCurrPage());
			vo.setStartPosition((form.getCurrPage() - 1) * form.getPageSize());
			vo.setUserId(form.getUserId());
			vo.setFinished(0);
			
			if (form.getType() == 1 && StringUtils.isNotBlank(form.getValue())) { //通过标题查询
				vo.setProcessName(form.getValue().trim());
			} else if (form.getType() == 2 && StringUtils.isNotBlank(form.getValue())) { //通过发起人查询
				vo.setCreateUserName(form.getValue().trim());
			} else if (form.getType() == 3) { //通过发起时间查询
				vo.setFromDate(form.getStartDate());
				vo.setToDate(form.getEndDate());
				vo.setStartTime(new Date());//这时间没有什么用，就是为了区别查询条件是哪个
			} else if (form.getType() == 4) { //通过接收时间查询
				vo.setFromDate(form.getStartDate());
				vo.setToDate(form.getEndDate());
				vo.setCreateTime(new Date());//这时间没有什么用，就是为了区别查询条件是哪个
			}
			
			List<NeedDealProcessEntity> list = this.needDealProcessMapper.selectByExample(vo);
			long total = this.needDealProcessMapper.countByExample(vo);
			pageUtil = new PageUtils<NeedDealProcessEntity>(list, total, form.getPageSize(), form.getCurrPage());
		}
		return pageUtil;
	}

	@Override
	public PageUtils<ProcessEntity> getAlreadySendByForm(QueryTermsForm form) throws Exception {
		PageUtils<ProcessEntity> pageUtil = null;
		if (form.getUserId() == null){
			pageUtil = new PageUtils<ProcessEntity>(new ArrayList<ProcessEntity>(), 0, form.getPageSize(), form.getCurrPage());
		} else {
			ProcessEntityQueryVo vo = new ProcessEntityQueryVo();
			vo.setOrderProperty("id");
			vo.setOrderType(SystemConst.SQL_ORDERTYPE_DESC);
			vo.setPaged(true);
			vo.setPageSize(form.getPageSize());
			vo.setCurPage(form.getCurrPage());
			vo.setStartPosition((form.getCurrPage() - 1) * form.getPageSize());
			vo.setCreateUserId(form.getUserId());
			vo.setStatus(-2);
			if(form.getProjectId()!=null){
				vo.setCreateUserId(null);//按项目查已发事项，就不能set发起人ID了
				vo.setProjectId(form.getProjectId());
			}
			if (form.getType() == 1 && StringUtils.isNotBlank(form.getValue())) { //通过标题查询
				vo.setTitle(form.getValue().trim());
			} else if (form.getType() == 2) { //通过发起时间查询
				vo.setFromDate(form.getStartDate());
				vo.setToDate(form.getEndDate());
				vo.setStartTime(new Date());//这时间没有什么用，就是为了区别查询条件是哪个
			} 
			
			List<ProcessEntity> list = this.getProcessByVo(vo);
			long total = this.getCountByVo(vo);
			this.userService.dealList(list, "currStepUserId");
			pageUtil = new PageUtils<ProcessEntity>(list, total, form.getPageSize(), form.getCurrPage());
		}
		return pageUtil;
	}

	@Override
	public PageUtils<ProcessEntity> getNeedSendByForm(QueryTermsForm form) throws Exception {
		PageUtils<ProcessEntity> pageUtil = null;
		if (form.getUserId() == null){
			pageUtil = new PageUtils<ProcessEntity>(new ArrayList<ProcessEntity>(), 0, form.getPageSize(), form.getCurrPage());
		} else {
			ProcessEntityQueryVo vo = new ProcessEntityQueryVo();
			vo.setOrderProperty("id");
			vo.setOrderType(SystemConst.SQL_ORDERTYPE_DESC);
			vo.setPaged(true);
			vo.setPageSize(form.getPageSize());
			vo.setCurPage(form.getCurrPage());
			vo.setStartPosition((form.getCurrPage() - 1) * form.getPageSize());
			vo.setCreateUserId(form.getUserId());
			vo.setStatus(-1);// <3
			if(form.getProjectId()!=null){
				vo.setCreateUserId(null);//按项目查待发事项，就不能set发起人ID了
				vo.setProjectId(form.getProjectId());
			}
			if (form.getType() == 1 && StringUtils.isNotBlank(form.getValue())) { //通过标题查询
				vo.setTitle(form.getValue().trim());
			} else if (form.getType() == 2) { //通过创建时间查询
				vo.setFromDate(form.getStartDate());
				vo.setToDate(form.getEndDate());
				vo.setCreateTime(new Date());//这时间没有什么用，就是为了区别查询条件是哪个
			} else if (form.getType() == 3 && StringUtils.isNotBlank(form.getValue())) { //通过状态查询
				vo.setStatus(Integer.parseInt(form.getValue()));
			}
			
			List<ProcessEntity> list = this.getProcessByVo(vo);
			long total = this.getCountByVo(vo);
			pageUtil = new PageUtils<ProcessEntity>(list, total, form.getPageSize(), form.getCurrPage());
		}
		return pageUtil;
	}

	@Override
	public boolean getIsCanApplyProjectChange(Integer projectId) throws Exception {
		if(projectId == null)
			return false;
		ProcessEntityQueryVo vo = new ProcessEntityQueryVo();
		vo.setProjectId(projectId);
		vo.setStatus(3);
		vo.setTableName(SystemConst.TABLE_PROJECTCHANGE);
		List<ProcessEntity> list = this.getProcessByVo(vo);
		if (list.size() > 0 ) {
			return false;
		}
		return true;
	}
	
	

}
