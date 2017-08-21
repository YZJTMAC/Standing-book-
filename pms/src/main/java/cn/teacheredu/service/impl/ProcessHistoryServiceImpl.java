package cn.teacheredu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.ProcessEntity;
import cn.teacheredu.entity.ProcessHistoryEntity;
import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.form.QueryTermsForm;
import cn.teacheredu.mapping.NeedDealProcessMapper;
import cn.teacheredu.mapping.ProcessHistoryMapper;
import cn.teacheredu.service.ProcessHistoryService;
import cn.teacheredu.service.ProcessService;
import cn.teacheredu.service.UserService;
import cn.teacheredu.utils.PageUtils;
import cn.teacheredu.utils.SystemConst;
import cn.teacheredu.vo.ProcessEntityQueryVo;
import cn.teacheredu.vo.ProcessHistoryQueryVo;

@Service
public class ProcessHistoryServiceImpl implements ProcessHistoryService {

	@Autowired
	private ProcessHistoryMapper processHistoryMapper;
	@Autowired
	private ProcessService processService;
	@Autowired
	private UserService userService;
	@Autowired
	private NeedDealProcessMapper needDealProcessMapper;
	
	
	@Override
	public ProcessHistoryEntity getProcessHistoryById(Integer processHistoryId) throws Exception {
		return this.processHistoryMapper.selectByPrimaryKey(processHistoryId);
	}

	@Override
	public List<ProcessHistoryEntity> getProcessHistoryByVo(ProcessHistoryQueryVo vo) throws Exception {
		return this.processHistoryMapper.selectByExample(vo);
	}

	@Override
	public int insertProcessHistoryEntity(ProcessHistoryEntity processHistoryEntity) throws Exception {
		this.processHistoryMapper.insertSelective(processHistoryEntity);
		this.needDealProcessMapper.updateFinishedById(processHistoryEntity.getNeedId());
		return processHistoryEntity.getId();
	}

	@Override
	public long getCountByVo(ProcessHistoryQueryVo vo) throws Exception {
		return this.processHistoryMapper.countByExample(vo);
	}

	@Override
	public int updateProcessHistory(ProcessHistoryEntity processHistoryEntity) throws Exception {
		return this.processHistoryMapper.updateByPrimaryKeySelective(processHistoryEntity);
	}

	@Override
	public int deteleProcessHistoryById(Integer processHistoryId) throws Exception {
		return this.processHistoryMapper.deleteByPrimaryKey(processHistoryId);
	}

	@Override
	public PageUtils<ProcessHistoryEntity> getAleadyDoneByUserId(Integer userId, Integer curPage, Integer pageSize)
			throws Exception {
		PageUtils<ProcessHistoryEntity> pageUtil = null;
		if (userId == null || curPage == null || pageSize == null){
			pageUtil = new PageUtils<ProcessHistoryEntity>(new ArrayList<ProcessHistoryEntity>(), 0, pageSize, curPage);
		} else {
			ProcessHistoryQueryVo vo = new ProcessHistoryQueryVo();
			vo.setOrderProperty("id");
			vo.setOrderType(SystemConst.SQL_ORDERTYPE_DESC);
			vo.setPaged(true);
			vo.setPageSize(pageSize);
			vo.setCurPage(curPage);
			vo.setStartPosition((curPage - 1) * pageSize);
			vo.setUserId(userId);
			List<ProcessHistoryEntity> list = this.getProcessHistoryByVo(vo);
			for (ProcessHistoryEntity processHistoryEntity : list) {
				if (processHistoryEntity.getProcessId() != null) {
					processHistoryEntity.setProcessEntity(this.processService.getProcessById(processHistoryEntity.getProcessId()));
				}
			}
			long total = this.getCountByVo(vo);
			pageUtil = new PageUtils<ProcessHistoryEntity>(list, total, pageSize, curPage);
		}
		return pageUtil;
	}

	@Override
	public List<String> getProcessLogById(Integer id) throws Exception {
		List<String> logList = new ArrayList<String>();
		int i = 1;
		ProcessEntity process = this.processService.getProcessById(id);
		if (process != null) {
			UserEntity user = this.userService.getUserEntityById(process.getCreateUserId());//流程发起人
			logList.add(i++ + ". " + DateFormatUtils.format(process.getStartTime(), "yyyy-MM-dd HH:mm:ss") 
				+ " [" + user.getRealname() + "] 提交了《"+process.getTitle()+"》");
			
			
			ProcessHistoryQueryVo vo = new ProcessHistoryQueryVo();
			vo.setOrderProperty("deal_time");
			vo.setOrderType(SystemConst.SQL_ORDERTYPE_ASC);
			vo.setProcessId(id);
			List<ProcessHistoryEntity> ph = this.getProcessHistoryByVo(vo);
			for (ProcessHistoryEntity processHistoryEntity : ph) {
				UserEntity dealUser = this.userService.getUserEntityById(processHistoryEntity.getUserId());//流程处理人
				StringBuilder sb = new StringBuilder();
				sb.append(i++ + ". ");
				sb.append(DateFormatUtils.format(processHistoryEntity.getDealTime(), "yyyy-MM-dd HH:mm:ss"));
				sb.append(" ["+dealUser.getRealname()+"] ");
				if (processHistoryEntity.getType() == 1) { //审批
					sb.append("审批结果：" + processHistoryEntity.getDealResult());
				} else if (processHistoryEntity.getType() == 2){ //知会
					sb.append("已查阅");
				} else if (processHistoryEntity.getType() == 3) { //编辑
					sb.append("添加了项目链接");
				}
				logList.add(sb.toString());
			}
		}
		return logList;
	}

	@Override
	public PageUtils<ProcessHistoryEntity> getAlreadyDealByForm(QueryTermsForm form) throws Exception {
		PageUtils<ProcessHistoryEntity> pageUtil = null;
		if (form.getUserId() == null){
			pageUtil = new PageUtils<ProcessHistoryEntity>(new ArrayList<ProcessHistoryEntity>(), 0, form.getPageSize(), form.getCurrPage());
		} else {
			ProcessHistoryQueryVo vo = new ProcessHistoryQueryVo();
			vo.setOrderProperty("id");
			vo.setOrderType(SystemConst.SQL_ORDERTYPE_DESC);
			vo.setPaged(true);
			vo.setPageSize(form.getPageSize());
			vo.setCurPage(form.getCurrPage());
			vo.setStartPosition((form.getCurrPage() - 1) * form.getPageSize());
			vo.setUserId(form.getUserId());
			
			if (form.getType() == 1 && StringUtils.isNotBlank(form.getValue())) { //通过标题查询
				vo.setProcessTitle(form.getValue().trim());
				vo.setNeedJoin(true);
			} else if (form.getType() == 3) { //通过发起时间查询
				vo.setFromDate(form.getStartDate());
				vo.setToDate(form.getEndDate());
				vo.setProcessCreateTime(new Date());//这时间没有什么用，就是为了区别查询条件是哪个
				vo.setNeedJoin(true);
			} else if (form.getType() == 2 && StringUtils.isNotBlank(form.getValue())) { //通过发起人查询
				vo.setProcessCreateUserName(form.getValue());
				vo.setNeedJoin(true);
			} else if (form.getType() == 4) { //通过处理时间查询
				vo.setFromDate(form.getStartDate());
				vo.setToDate(form.getEndDate());
				vo.setDealTime(new Date());//这时间没有什么用，就是为了区别查询条件是哪个
			}
			
			List<ProcessHistoryEntity> list = this.getProcessHistoryByVo(vo);
			for (ProcessHistoryEntity processHistoryEntity : list) {
				if (processHistoryEntity.getProcessId() != null) {
					ProcessEntity processEntity = this.processService.getProcessById(processHistoryEntity.getProcessId());
					if (processEntity != null && processEntity.getStatus() == 3) {
						Map<String, Object> map = new HashMap<String, Object>();
						UserEntity userdb = this.userService.getUserEntityById(processEntity.getCurrStepUserId());//当前待办人
						map.put("realNameDB", userdb == null ? "" : userdb.getRealname());
						processEntity.setExtInfo(map);
					}
					processHistoryEntity.setProcessEntity(processEntity);
				}
			}
			long total = this.getCountByVo(vo);
			pageUtil = new PageUtils<ProcessHistoryEntity>(list, total,  form.getPageSize(), form.getCurrPage());
		}
		return pageUtil;
	}

	@Override
	public List<ProcessHistoryEntity> getProcessHistoryByProcessId(Integer proId) throws Exception {
		if (proId == null) {
			return new ArrayList<ProcessHistoryEntity>();
		}
		ProcessHistoryQueryVo vo = new ProcessHistoryQueryVo();
		vo.setOrderProperty("id");
		vo.setOrderType(SystemConst.SQL_ORDERTYPE_ASC);
		vo.setProcessId(proId);
//		vo.setType((byte)1);//只查有关审批的
		List<ProcessHistoryEntity> ph = this.getProcessHistoryByVo(vo);
		for (ProcessHistoryEntity processHistoryEntity : ph) {
			UserEntity dealUser = this.userService.getUserEntityById(processHistoryEntity.getUserId());//流程处理人
			Map<String,	Object> map = new HashMap<String, Object>();
			map.put(SystemConst.USER_REALNAME, dealUser.getRealname());
			processHistoryEntity.setExtInfo(map);
		}
		return ph;
	}

	@Override
	public List<ProcessHistoryEntity> getProcessHistoryByProjectId(Integer projId) throws Exception {
		if (projId != null) {
			ProcessEntityQueryVo vo = new ProcessEntityQueryVo();
			vo.setStatus(4);
			vo.setObjectId(projId);
			vo.setTableName(SystemConst.TABLE_PROJECT);
			List<ProcessEntity> process = this.processService.getProcessByVo(vo);
			if (process != null && process.size() > 0) {
				ProcessEntity proc = process.get(0);
				ProcessHistoryQueryVo vo2 = new ProcessHistoryQueryVo();
				vo2.setOrderProperty("id");
				vo2.setOrderType(SystemConst.SQL_ORDERTYPE_ASC);
				vo2.setProcessId(proc.getId());
//				vo2.setType((byte)1);//只查有关审批的
				List<ProcessHistoryEntity> ph = this.getProcessHistoryByVo(vo2);
				for (ProcessHistoryEntity processHistoryEntity : ph) {
					UserEntity dealUser = this.userService.getUserEntityById(processHistoryEntity.getUserId());//流程处理人
					Map<String,	Object> map = new HashMap<String, Object>();
					map.put(SystemConst.USER_REALNAME, dealUser.getRealname());
					processHistoryEntity.setExtInfo(map);
				}
				return ph;
			}
		}
		return new ArrayList<ProcessHistoryEntity>();
	}
	
}
