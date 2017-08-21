package cn.teacheredu.service;

import java.util.List;

import cn.teacheredu.entity.ProjectBudgetEntity;
import cn.teacheredu.vo.ProjectBudgetQueryVo;

/**
 * 项目预算表
 * 
 * @author teacheredu
 *
 */
public interface ProjectBudgetService {

	ProjectBudgetEntity getProjectBudgetById(Integer pbId) throws Exception;

	List<ProjectBudgetEntity> getProjectBudgetByVo(ProjectBudgetQueryVo vo) throws Exception;

	int insertProjectBudgetEntity(ProjectBudgetEntity pbEntity) throws Exception;

	long getCountByVo(ProjectBudgetQueryVo vo) throws Exception;

	int updateProjectBudget(ProjectBudgetEntity pbEntity) throws Exception;

	int deteleProjectBudgetById(Integer pbId) throws Exception;

	ProjectBudgetEntity getProjectBudgetByProjectId(Integer id) throws Exception;
}
