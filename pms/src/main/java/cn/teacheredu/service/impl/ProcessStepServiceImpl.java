package cn.teacheredu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.ProcessStepEntity;
import cn.teacheredu.mapping.ProcessStepMapper;
import cn.teacheredu.service.ProcessStepService;
import cn.teacheredu.vo.ProcessStepQueryVo;

@Service
public class ProcessStepServiceImpl implements ProcessStepService {
	@Autowired
	private ProcessStepMapper processStepMapper;
	
	@Override
	public ProcessStepEntity getProcessStepById(Integer id) throws Exception {
		return this.processStepMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ProcessStepEntity> getProcessStepListByVo(ProcessStepQueryVo vo) throws Exception {
		return this.processStepMapper.selectByExample(vo);
	}

	@Override
	public int insertProcessStepEntity(ProcessStepEntity entity) throws Exception {
		return this.processStepMapper.insertSelective(entity);
	}

	@Override
	public long getCountByVo(ProcessStepQueryVo vo) throws Exception {
		return this.processStepMapper.countByExample(vo);
	}

	@Override
	public int updateProcessStep(ProcessStepEntity entity) throws Exception {
		return this.processStepMapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public int deteleProcessStepById(Integer id) throws Exception {
		return this.processStepMapper.deleteByPrimaryKey(id);
	}

	@Override
	public ProcessStepEntity getProcessStepByDefineInfo(String tableName, Integer dmId, String beginStatus) throws Exception {
		ProcessStepQueryVo vo = new ProcessStepQueryVo();
		vo.setTableName(tableName);
		vo.setDmId(dmId);
		vo.setBeginStatus(beginStatus);
		
		return this.processStepMapper.getProcessStepByVo(vo);
	}

	@Override
	public List<ProcessStepEntity> getProcessStepListByDmId(Integer dmId) throws Exception {
		if (dmId == null) {
			return null;
		}
		
		ProcessStepQueryVo vo = new ProcessStepQueryVo();
		vo.setDmId(dmId);
		return this.processStepMapper.selectByExample(vo);
	}
}
