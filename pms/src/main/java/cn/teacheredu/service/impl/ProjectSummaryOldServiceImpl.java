package cn.teacheredu.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.ProjectSummaryOldEntity;
import cn.teacheredu.mapping.ProjectSummaryOldMapper;
import cn.teacheredu.service.ProjectSummaryOldService;
import cn.teacheredu.vo.ProjectSummaryOldQueryVo;

@Service
public class ProjectSummaryOldServiceImpl implements ProjectSummaryOldService {
	
	@Autowired
	private ProjectSummaryOldMapper projectSummaryOldMapper;

	@Override
	public ProjectSummaryOldEntity getProjectSummaryOldById(Integer psoId) throws Exception {
		return this.projectSummaryOldMapper.selectByPrimaryKey(psoId);
	}

	@Override
	public List<ProjectSummaryOldEntity> getProjectSummaryOldByVo(ProjectSummaryOldQueryVo vo) throws Exception {
		return this.projectSummaryOldMapper.selectByExample(vo);
	}

	@Override
	public int insertProjectSummaryOldEntity(ProjectSummaryOldEntity psoEntity) throws Exception {
		this.projectSummaryOldMapper.insertSelective(psoEntity);
		return psoEntity.getId();
	}

	@Override
	public long getCountByVo(ProjectSummaryOldQueryVo vo) throws Exception {
		return this.projectSummaryOldMapper.countByExample(vo);
	}

	@Override
	public int updateProjectSummaryOld(ProjectSummaryOldEntity psoEntity) throws Exception {
		return this.projectSummaryOldMapper.updateByPrimaryKeySelective(psoEntity);
	}

	@Override
	public int deteleProjectSummaryOldById(Integer psoId) throws Exception {
		return this.projectSummaryOldMapper.deleteByPrimaryKey(psoId);
	}
	

}
