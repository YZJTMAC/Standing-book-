package cn.teacheredu.mapping;

import java.util.List;

import cn.teacheredu.entity.ProcessHistoryEntity;
import cn.teacheredu.vo.ProcessHistoryQueryVo;

public interface ProcessHistoryMapper {
	long countByExample(ProcessHistoryQueryVo vo) throws Exception;

	int deleteByPrimaryKey(Integer id) throws Exception;

	int insertSelective(ProcessHistoryEntity record) throws Exception;

	List<ProcessHistoryEntity> selectByExample(ProcessHistoryQueryVo vo) throws Exception;

	ProcessHistoryEntity selectByPrimaryKey(Integer id) throws Exception;

	int updateByPrimaryKeySelective(ProcessHistoryEntity record) throws Exception;

}