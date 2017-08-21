package cn.teacheredu.mapping;

import java.util.List;

import cn.teacheredu.entity.ProjectBudgetEntity;
import cn.teacheredu.vo.ProjectBudgetQueryVo;

public interface ProjectBudgetMapper {
	long countByExample(ProjectBudgetQueryVo vo) throws Exception;

	int deleteByPrimaryKey(Integer id) throws Exception;

	int insertSelective(ProjectBudgetEntity record) throws Exception;

	List<ProjectBudgetEntity> selectByExample(ProjectBudgetQueryVo vo) throws Exception;

	ProjectBudgetEntity selectByPrimaryKey(Integer id) throws Exception;

	int updateByPrimaryKeySelective(ProjectBudgetEntity record) throws Exception;

}