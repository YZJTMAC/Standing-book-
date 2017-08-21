package cn.teacheredu.service;

import java.util.List;

import cn.teacheredu.entity.NeedDealProcessEntity;
import cn.teacheredu.entity.ProcessEntity;
import cn.teacheredu.form.QueryTermsForm;
import cn.teacheredu.utils.PageUtils;
import cn.teacheredu.vo.ProcessEntityQueryVo;



public interface ProcessService {

	ProcessEntity getProcessById(Integer processId) throws Exception;
	
	List<ProcessEntity> getProcessByVo(ProcessEntityQueryVo vo) throws Exception;
	
	int insertProcessEntity(ProcessEntity processEntity) throws Exception;
	
	long getCountByVo(ProcessEntityQueryVo vo) throws Exception;
	
	int updateProcess(ProcessEntity processEntity) throws Exception;
	
	int deteleProcessById(Integer processId) throws Exception;
	
	/**
	 * 通过用户ID查询待办事项
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public PageUtils<NeedDealProcessEntity> getNeedProcessByUserId(Integer userId, Integer curPage, Integer pageSize) throws Exception;

	/**
	 * 通过用户ID查询待发、已发事项
	 * @param userId 用户ID
	 * @param status -1待发 -2 已发
	 * @return
	 * @throws Exception
	 */
	PageUtils<ProcessEntity> getNeedSendProcessByUserId(Integer userId, Integer status, Integer curPage, Integer pageSize) throws Exception;
	
	
	/**
	 * 通过form查询待办事项
	 * @param form
	 * @return
	 * @throws Exception
	 */
	PageUtils<NeedDealProcessEntity> getNeedDealByForm(QueryTermsForm form) throws Exception;

	/**
	 * 通过form查询已发事项
	 * @param form
	 * @return
	 * @throws Exception
	 */
	PageUtils<ProcessEntity> getAlreadySendByForm(QueryTermsForm form) throws Exception;

	/**
	 * 通过form查询待发事项
	 * @param queryForm
	 * @return
	 * @throws Exception
	 */
	PageUtils<ProcessEntity> getNeedSendByForm(QueryTermsForm form) throws Exception;
	
	/**
	 * 查询现在是否能提交项目信息变更流程
	 * 如果当前有还没有审批完的项目信息变更流程，则不可以进行申请，必须等上次提交的审批完了才可以再次提交
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	boolean getIsCanApplyProjectChange(Integer projectId) throws Exception;
}
