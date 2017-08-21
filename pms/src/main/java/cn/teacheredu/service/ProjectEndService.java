package cn.teacheredu.service;

import java.util.List;

import cn.teacheredu.entity.ProjectEndEntity;
import cn.teacheredu.form.ProjectEndForm;
import cn.teacheredu.vo.ProjectEndQueryVo;

/**
 * 项目完结信息表
 * @author teacheredu
 *
 */
public interface ProjectEndService {

	ProjectEndEntity getProjectEndById(Integer proEndId) throws Exception;
	
	List<ProjectEndEntity> getProjectEndByVo(ProjectEndQueryVo vo) throws Exception;
	
	int insertProjectEndEntity(ProjectEndEntity proEndEntity) throws Exception;
	
	long getCountByVo(ProjectEndQueryVo vo) throws Exception;
	
	int updateProjectEnd(ProjectEndEntity proEndEntity) throws Exception;
	
	int deteleProjectEndById(Integer proEndId) throws Exception;
	
	
	/**
	 * 批量插入的方法,并插入日志
	 * @param proEndList
	 * @param uid 用户id
	 * @throws Exception
	 */
	void insertProjectEndBatch(List<ProjectEndForm> proEndList,Integer uid) throws Exception;
	
	/**
	 * 通过流程ID获取表单数据
	 * @param processId
	 * @return
	 * @throws Exception
	 */
	List<ProjectEndEntity> getProjectEndByProcessId(Integer processId) throws Exception;
}
