package cn.teacheredu.service;

import java.util.List;

import cn.teacheredu.entity.NeedDealProcessEntity;
import cn.teacheredu.entity.ProcessEntity;
import cn.teacheredu.vo.NeedDealProcessQueryVo;


public interface NeedDealProcessService {

	NeedDealProcessEntity getNeedDealProcessById(Integer id) throws Exception;
	
	List<NeedDealProcessEntity> getNeedDealProcessByVo(NeedDealProcessQueryVo vo) throws Exception;
	
	int insertNeedDealProcessEntity(NeedDealProcessEntity needDealProcessEntity) throws Exception;
	
	int updateNeedDealProcess(NeedDealProcessEntity needDealProcessEntity) throws Exception;
	
	int deteleNeedDealProcessById(Integer id) throws Exception;
	
	long getCountByVo(NeedDealProcessQueryVo vo) throws Exception;
	
	/**
	 * 根据流程添加待办
	 * @param processEntity
	 * @return
	 * @throws Exception
	 */
	int insertNeedDealProcessEntity(ProcessEntity processEntity) throws Exception;
	
	/**
	 * 为某一个部门下所有人员添加待办事项      一般用于添加类型为查阅的事项(非审批)
	 * @param dmType 部门类型，详见数据库字段说明
	 * @param level 公司ID，0为总部。由于现在流程还没定，暂时都是传0。例如：dmType 传3 level 传0  那么只有属于总部财务部的人能收到财务部需要知会的事项
	 * @param processEntity 具体要添加的事项
	 * @throws Exception
	 */
	void saveProcessForDepartment(Integer dmType,String level, ProcessEntity processEntity) throws Exception;
}
