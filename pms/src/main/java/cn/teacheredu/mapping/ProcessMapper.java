package cn.teacheredu.mapping;

import java.util.List;

import cn.teacheredu.entity.ProcessEntity;
import cn.teacheredu.vo.ProcessEntityQueryVo;

public interface ProcessMapper {
	long countByExample(ProcessEntityQueryVo vo) throws Exception;

	int deleteByPrimaryKey(Integer id) throws Exception;

	int insertSelective(ProcessEntity record) throws Exception;

	List<ProcessEntity> selectByExample(ProcessEntityQueryVo vo) throws Exception;

	ProcessEntity selectByPrimaryKey(Integer id) throws Exception;

	int updateByPrimaryKeySelective(ProcessEntity record) throws Exception;

	int updateUpdateSummary() throws Exception;

}