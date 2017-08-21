package cn.teacheredu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.LoginLogEntity;
import cn.teacheredu.mapping.LoginLogMapper;
import cn.teacheredu.service.LoginLogService;
import cn.teacheredu.vo.LoginLogQueryVo;

@Service
public class LoginLogServiceImpl implements LoginLogService {
	@Autowired
	private LoginLogMapper loginLogMapper;

	@Override
	public LoginLogEntity getLoginLogById(Integer logId) throws Exception {
		return this.loginLogMapper.selectByPrimaryKey(logId);
	}

	@Override
	public List<LoginLogEntity> getLoginLogsByVo(LoginLogQueryVo vo) throws Exception {
		return this.loginLogMapper.selectByExample(vo);
	}

	@Override
	public int insertLoginLogEntity(LoginLogEntity loginLogEntity) throws Exception {
		this.loginLogMapper.insertSelective(loginLogEntity);
		return loginLogEntity.getId();
	}

	@Override
	public int updateLoginLog(LoginLogEntity loginLogEntity) throws Exception {
		return this.loginLogMapper.updateByPrimaryKeySelective(loginLogEntity);
	}

		
}
