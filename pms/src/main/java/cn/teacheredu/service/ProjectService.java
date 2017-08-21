package cn.teacheredu.service;

import java.util.List;

import cn.teacheredu.entity.DepartmentEntity;
import cn.teacheredu.entity.ProjectEntity;
import cn.teacheredu.form.QueryTermsForm;
import cn.teacheredu.utils.PageUtils;
import cn.teacheredu.vo.ProjectEntityQueryVo;

public interface ProjectService {

	ProjectEntity getProjectById(Integer projectId) throws Exception;
	
	List<ProjectEntity> getProjectByVo(ProjectEntityQueryVo vo) throws Exception;
	
	int insertProjectEntity(ProjectEntity projectEntity) throws Exception;
	
	long getCountByVo(ProjectEntityQueryVo vo) throws Exception;
	
	int updateProject(ProjectEntity projectEntity) throws Exception;
	
	int deteleProjectById(Integer projectId) throws Exception;

	/**
	 * 此方法为主页 我的项目 模块所用
	 * 
	 * @param userId 用户ID
	 * @param dmList 部门列表，一个人可能属于多个部门
	 * @param type 类型 1 查询全部 2 查询所在省份的所有项目 3 查询自己发起的 4查询某个分管大区的项目 5查询指定公司的项目
	 * @return
	 * @throws Exception
	 */
	public PageUtils<ProjectEntity> getMyProjectForIndexPage(Integer userId, List<DepartmentEntity> dmList, List<String> companyList, Integer type) throws Exception;

	PageUtils<ProjectEntity> getProjectListByForm(QueryTermsForm form) throws Exception;
	
	/**
	 * 查询某个省份下所有的项目，不分页
	 * @param province
	 * @return
	 * @throws Exception
	 */
	List<ProjectEntity> getProjectByProvince(Integer province) throws Exception;
	
	/**
	 * 查询需要完结的项目
	 */
	List<ProjectEntity> selectProjectForEnd(ProjectEntityQueryVo vo) throws Exception;
	
	/**
	 * 修改项目状态
	 * @param status
	 * @param projectId
	 * @throws Exception
	 */
	void editProjectStatus(Byte status,Integer projectId) throws Exception;
}
