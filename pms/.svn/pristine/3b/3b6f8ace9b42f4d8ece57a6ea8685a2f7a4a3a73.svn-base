package cn.teacheredu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.ProjectEndEntity;
import cn.teacheredu.form.ProjectEndForm;
import cn.teacheredu.mapping.ProjectEndMapper;
import cn.teacheredu.service.ProjectEndService;
import cn.teacheredu.service.SystemLogService;
import cn.teacheredu.vo.ProjectEndQueryVo;

@Service
public class ProjectEndServiceImpl implements ProjectEndService {

	@Autowired
	private ProjectEndMapper projectEndMapper;
	
	@Autowired
	private SystemLogService systemLogService;
	
//	@Autowired
//	private ProjectMapper projectMapper;
	
	@Override
	public ProjectEndEntity getProjectEndById(Integer proEndId) throws Exception {
		return this.projectEndMapper.selectByPrimaryKey(proEndId);
	}

	@Override
	public List<ProjectEndEntity> getProjectEndByVo(ProjectEndQueryVo vo) throws Exception {
		return this.projectEndMapper.selectByExample(vo);
	}

	@Override
	public int insertProjectEndEntity(ProjectEndEntity proEndEntity) throws Exception {
		this.projectEndMapper.insertSelective(proEndEntity);
		return proEndEntity.getId();
	}

	@Override
	public long getCountByVo(ProjectEndQueryVo vo) throws Exception {
		return this.projectEndMapper.countByExample(vo);
	}

	@Override
	public int updateProjectEnd(ProjectEndEntity proEndEntity) throws Exception {
		return this.projectEndMapper.updateByPrimaryKeySelective(proEndEntity);
	}

	@Override
	public int deteleProjectEndById(Integer proEndId) throws Exception {
		return this.projectEndMapper.deleteByPrimaryKey(proEndId);
	}

	@Override
	public void insertProjectEndBatch(List<ProjectEndForm> proEndList,Integer uid) throws Exception {
		for (ProjectEndForm projectEndForm : proEndList) {
			ProjectEndEntity projectEnd=new ProjectEndEntity();
			BeanUtils.copyProperties(projectEndForm, projectEnd);
			this.projectEndMapper.insertSelective(projectEnd);
//			ProjectEntity project = this.projectMapper.selectByPrimaryKey(projectEnd.getProjectId());
//			project.setStatus(new Byte("3"));
//			this.projectMapper.updateByPrimaryKeySelective(project);
			this.systemLogService.saveSystemLog(1, projectEnd.toString(), uid);
		}
	}

	@Override
	public List<ProjectEndEntity> getProjectEndByProcessId(Integer processId) throws Exception {
		if (processId == null) {
			return new ArrayList<ProjectEndEntity>();
		}
		ProjectEndQueryVo vo = new ProjectEndQueryVo();
		vo.setProcessId(processId);
		List<ProjectEndEntity> list = this.getProjectEndByVo(vo);
		return list;
	}

}
