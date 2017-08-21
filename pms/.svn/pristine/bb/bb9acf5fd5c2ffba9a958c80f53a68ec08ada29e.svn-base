package cn.teacheredu.service;

import java.util.List;

import cn.teacheredu.entity.ProcessHistoryEntity;
import cn.teacheredu.form.QueryTermsForm;
import cn.teacheredu.utils.PageUtils;
import cn.teacheredu.vo.ProcessHistoryQueryVo;

public interface ProcessHistoryService {

	ProcessHistoryEntity getProcessHistoryById(Integer processHistoryId) throws Exception;

	List<ProcessHistoryEntity> getProcessHistoryByVo(ProcessHistoryQueryVo vo) throws Exception;

	int insertProcessHistoryEntity(ProcessHistoryEntity processHistoryEntity) throws Exception;

	long getCountByVo(ProcessHistoryQueryVo vo) throws Exception;

	int updateProcessHistory(ProcessHistoryEntity processHistoryEntity) throws Exception;

	int deteleProcessHistoryById(Integer processHistoryId) throws Exception;
	
	/**
	 * 通过用户ID查询其已办事项
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	PageUtils<ProcessHistoryEntity> getAleadyDoneByUserId(Integer userId, Integer curPage, Integer pageSize) throws Exception;
	
	/**
	 * 通过流程ID得到流程日志
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<String> getProcessLogById(Integer id) throws Exception;

	/**
	 * 通过form查询其已办事项
	 * @param form
	 * @return
	 * @throws Exception
	 */
	PageUtils<ProcessHistoryEntity> getAlreadyDealByForm(QueryTermsForm form) throws Exception;
	
	/**
	 * 通过流程ID得到流程的审批记录
	 * @param proId
	 * @return
	 * @throws Exception
	 */
	List<ProcessHistoryEntity> getProcessHistoryByProcessId(Integer proId) throws Exception;
	
	/**
	 * 通过项目ID得到项目确认表流程的审批记录
	 * @param projId
	 * @return
	 * @throws Exception
	 */
	List<ProcessHistoryEntity> getProcessHistoryByProjectId(Integer projId) throws Exception;
	
}
