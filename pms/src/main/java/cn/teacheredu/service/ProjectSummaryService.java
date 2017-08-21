package cn.teacheredu.service;

import cn.teacheredu.entity.ProjectSummariesEntity;
import cn.teacheredu.form.QueryTermsForm;
import cn.teacheredu.utils.PageUtils;

/**
 * 项目汇总表
 */
public interface ProjectSummaryService {

	/**
	 * 根据form查询项目汇总集合（分页）
	 * @param queryForm
	 * @throws Exception
	 */
	PageUtils<ProjectSummariesEntity> getProjectSummaryList(QueryTermsForm queryForm) throws Exception;

}
