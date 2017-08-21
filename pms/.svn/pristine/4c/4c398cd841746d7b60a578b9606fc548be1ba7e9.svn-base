package cn.teacheredu.mapping;

import cn.teacheredu.entity.ProjectEntity;
import cn.teacheredu.entity.ProjectSummariesEntity;
import cn.teacheredu.vo.ProjectEntityQueryVo;

import java.util.Date;
import java.util.List;

public interface ProjectMapper {
	long countByExample(ProjectEntityQueryVo vo) throws Exception;

	int deleteByPrimaryKey(Integer id) throws Exception;

	int insertSelective(ProjectEntity record) throws Exception;

	List<ProjectEntity> selectByExample(ProjectEntityQueryVo vo) throws Exception;

	ProjectEntity selectByPrimaryKey(Integer id) throws Exception;

	int updateByPrimaryKeySelective(ProjectEntity record) throws Exception;

	List<ProjectEntity> selectProjectForEnd(ProjectEntityQueryVo vo) throws Exception;
	
	/**
	 * 项目汇总，查询需要统计的project的id
	 */
	List<ProjectEntity> summariesProject(Date summariesStartDate) throws Exception;
	/**
	 * 项目汇总，收集project中的数据
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	ProjectSummariesEntity summaryById(Integer id) throws Exception;

	
	/**
	 * 项目汇总，查询需要统计的project的id
	 */
	List<ProjectEntity> summariesProject2() throws Exception;
}