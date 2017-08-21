package cn.teacheredu.mapping;

import java.util.List;

import cn.teacheredu.entity.ProjectChangeEntity;
import cn.teacheredu.vo.ProjectChangeQueryVo;

public interface ProjectChangeMapper {
	long countByExample(ProjectChangeQueryVo vo) throws Exception;

	int deleteByPrimaryKey(Integer id) throws Exception;

	int insertSelective(ProjectChangeEntity record) throws Exception;

	List<ProjectChangeEntity> selectByExample(ProjectChangeQueryVo vo) throws Exception;

	ProjectChangeEntity selectByPrimaryKey(Integer id) throws Exception;

	int updateByPrimaryKeySelective(ProjectChangeEntity record) throws Exception;

}