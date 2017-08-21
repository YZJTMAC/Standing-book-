package cn.teacheredu.service;

import java.util.List;

import cn.teacheredu.entity.ProjectChangeEntity;
import cn.teacheredu.form.ProjectChangeForm;
import cn.teacheredu.vo.ProjectChangeQueryVo;

public interface ProjectChangeService {

	ProjectChangeEntity getProjectChangeById(Integer projectChangeId) throws Exception;

	List<ProjectChangeEntity> getProjectChangeByVo(ProjectChangeQueryVo vo) throws Exception;

	int insertProjectChangeEntity(ProjectChangeEntity projectChangeEntity) throws Exception;

	long getCountByVo(ProjectChangeQueryVo vo) throws Exception;

	int updateProjectChange(ProjectChangeEntity projectChangeEntity) throws Exception;

	int deteleProjectChangeById(Integer projectChangeId) throws Exception;

	void saveProjectChangeAndProcess(ProjectChangeForm pcForm) throws Exception;
}
