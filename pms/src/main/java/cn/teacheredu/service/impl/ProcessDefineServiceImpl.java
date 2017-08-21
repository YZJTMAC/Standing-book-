package cn.teacheredu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.ProcessDefineEntity;
import cn.teacheredu.mapping.ProcessDefineMapper;
import cn.teacheredu.service.ProcessDefineService;
import cn.teacheredu.vo.ProcessDefineQueryVo;

@Service
public class ProcessDefineServiceImpl implements ProcessDefineService {
	@Autowired
	private ProcessDefineMapper processDefineMapper;
	
	@Override
	public ProcessDefineEntity getProcessDefineById(Integer id) throws Exception {
		return this.processDefineMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ProcessDefineEntity> getProcessDefineByVo(ProcessDefineQueryVo vo) throws Exception {
		return this.processDefineMapper.selectByExample(vo);
	}

	@Override
	public int insertProcessDefineEntity(ProcessDefineEntity entity) throws Exception {
		return this.processDefineMapper.insertSelective(entity);
	}

	@Override
	public long getCountByVo(ProcessDefineQueryVo vo) throws Exception {
		return this.processDefineMapper.countByExample(vo);
	}

	@Override
	public int updateProcessDefine(ProcessDefineEntity entity) throws Exception {
		return this.processDefineMapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public int deteleProcessDefineById(Integer id) throws Exception {
		return this.processDefineMapper.deleteByPrimaryKey(id);
	}
}
