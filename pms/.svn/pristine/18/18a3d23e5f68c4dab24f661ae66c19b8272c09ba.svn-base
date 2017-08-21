package cn.teacheredu.mapping;

import java.util.List;

import cn.teacheredu.entity.ProcessStepEntity;
import cn.teacheredu.vo.ProcessStepQueryVo;


/**
 * 流程步骤表
 */
public interface ProcessStepMapper {
	long countByExample(ProcessStepQueryVo vo) throws Exception;
	
	int deleteByPrimaryKey(Integer id) throws Exception;

	int insertSelective(ProcessStepEntity entity) throws Exception;

	List<ProcessStepEntity> selectByExample(ProcessStepQueryVo vo) throws Exception;

	ProcessStepEntity selectByPrimaryKey(Integer id) throws Exception;

	int updateByPrimaryKeySelective(ProcessStepEntity entity) throws Exception;
	
	ProcessStepEntity getProcessStepByVo(ProcessStepQueryVo vo) throws Exception;
}
