package cn.teacheredu.service.batch;

import java.util.Date;
import java.util.List;

import cn.teacheredu.entity.ProjectEntity;

public interface ProjectSummariesBatch {
	/**
	 * 批处理函数
	 * @return
	 * @throws Exception
	 */
	boolean summariesBatch(Date summariesStartDate) throws Exception ;

	/**
	 * 如果summariesStartDate有值，统计从时间起需要统计的项目：从process表查询所有的操作，得到process集合。根据process中的project_id，查询project表。
	 * 如果时间为空,就统计所有的项目：从project表查询所有的，返回project的集合。
	 */
	List<ProjectEntity> summariesProject(Date summariesStartDate) throws Exception;

	/**
	 * 查看当前项目是否已经统计过，如果是新数据，就插入一条记录
	 */
	boolean haveSummaried(List<ProjectEntity> projects) throws Exception;
	
	/**
	 * 更新project的Yearsummary数据
	 */
	boolean updateYearSummary(Integer projectId) throws Exception;
	
	/**
	 * 更新project的Summaries数据
	 */
	boolean updateSummaries(Integer projectId) throws Exception;

	void summariesBatch2() throws Exception;
	
}
