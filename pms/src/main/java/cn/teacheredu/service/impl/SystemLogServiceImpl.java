package cn.teacheredu.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.SystemLogEntity;
import cn.teacheredu.mapping.SystemLogMapper;
import cn.teacheredu.service.SystemLogService;
import cn.teacheredu.vo.SystemLogQueryVo;

@Service
public class SystemLogServiceImpl implements SystemLogService{

	@Autowired
	private SystemLogMapper systemLogMapper;
	
	@Override
	public SystemLogEntity getSystemLogById(Integer logId) throws Exception {
		return this.systemLogMapper.selectByPrimaryKey(logId);
	}

	@Override
	public List<SystemLogEntity> getSystemLogVo(SystemLogQueryVo vo) throws Exception {
		return this.systemLogMapper.selectByExample(vo);
	}

	@Override
	public int insertSystemLogEntity(SystemLogEntity systemLogtEntity) throws Exception {
		this.systemLogMapper.insertSelective(systemLogtEntity);
		return systemLogtEntity.getId();
	}

	@Override
	public long getCountByVo(SystemLogQueryVo vo) throws Exception {
		return this.getCountByVo(vo);
	}

	@Override
	public int updateSystemLog(SystemLogEntity systemLogtEntity) throws Exception {
		return this.systemLogMapper.updateByPrimaryKeySelective(systemLogtEntity);
	}

	@Override
	public boolean saveSystemLog(int type, String content, int uid) throws Exception {
		SystemLogEntity log = new SystemLogEntity();
		log.setContent(content);
		log.setType(type);
		log.setUid(uid);
		log.setTime(new Date());
		int result = this.systemLogMapper.insertSelective(log);
		if (result == 0) {
			return false;
		}
		return true;
	}
	
}
