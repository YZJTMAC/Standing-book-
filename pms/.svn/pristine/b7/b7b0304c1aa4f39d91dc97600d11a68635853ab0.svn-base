package cn.teacheredu.service;

import java.util.List;

import cn.teacheredu.entity.ProcessStepEntity;
import cn.teacheredu.vo.ProcessStepQueryVo;

/**
 * 流程步骤
 */
public interface ProcessStepService {
	ProcessStepEntity getProcessStepById(Integer id) throws Exception;
	
	List<ProcessStepEntity> getProcessStepListByVo(ProcessStepQueryVo vo) throws Exception;
	
	int insertProcessStepEntity(ProcessStepEntity entity) throws Exception;
	
	long getCountByVo(ProcessStepQueryVo vo) throws Exception;
	
	int updateProcessStep(ProcessStepEntity entity) throws Exception;
	
	int deteleProcessStepById(Integer id) throws Exception;
	
	/**
	 * 查询步骤的下一步待办人
	 * @param tableName
	 * @param dmId
	 * @param beginStatus
	 * @return
	 * @throws Exception
	 */
	ProcessStepEntity getProcessStepByDefineInfo(String tableName, Integer dmId, String beginStatus) throws Exception;
	
	
	/**
	 * 查询部门是否在流程配置中
	 * @param dmId
	 * @return
	 * @throws Exception
	 */
	List<ProcessStepEntity> getProcessStepListByDmId(Integer dmId) throws Exception;
}
