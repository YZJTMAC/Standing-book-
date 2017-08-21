package cn.teacheredu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.ProjectBudgetEntity;
import cn.teacheredu.mapping.ProjectBudgetMapper;
import cn.teacheredu.service.ProjectBudgetService;
import cn.teacheredu.vo.ProjectBudgetQueryVo;

@Service
public class ProjectBudgetServiceImpl implements ProjectBudgetService {

	@Autowired
	private ProjectBudgetMapper projectBudgetMapper;
	
	@Override
	public ProjectBudgetEntity getProjectBudgetById(Integer pbId) throws Exception {
		return this.projectBudgetMapper.selectByPrimaryKey(pbId);
	}

	@Override
	public List<ProjectBudgetEntity> getProjectBudgetByVo(ProjectBudgetQueryVo vo) throws Exception {
		return this.projectBudgetMapper.selectByExample(vo);
	}

	@Override
	public int insertProjectBudgetEntity(ProjectBudgetEntity pbEntity) throws Exception {
		this.projectBudgetMapper.insertSelective(pbEntity);
		return pbEntity.getId();
	}

	@Override
	public long getCountByVo(ProjectBudgetQueryVo vo) throws Exception {
		return this.projectBudgetMapper.countByExample(vo);
	}

	@Override
	public int updateProjectBudget(ProjectBudgetEntity pbEntity) throws Exception {
		return this.projectBudgetMapper.updateByPrimaryKeySelective(pbEntity);
	}

	@Override
	public int deteleProjectBudgetById(Integer pbId) throws Exception {
		return this.projectBudgetMapper.deleteByPrimaryKey(pbId);
	}

	@Override
	public ProjectBudgetEntity getProjectBudgetByProjectId(Integer id) throws Exception {
		if (id == null) {
			return null;
		}
		ProjectBudgetQueryVo vo = new ProjectBudgetQueryVo();
		vo.setProjectId(id);
		List<ProjectBudgetEntity> list = this.getProjectBudgetByVo(vo);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
