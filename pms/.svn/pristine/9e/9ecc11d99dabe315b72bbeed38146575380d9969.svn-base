package cn.teacheredu.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.DepartmentEntity;
import cn.teacheredu.entity.NeedDealProcessEntity;
import cn.teacheredu.entity.ProcessEntity;
import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.mapping.DepartmentMapper;
import cn.teacheredu.mapping.NeedDealProcessMapper;
import cn.teacheredu.mapping.UserMapper;
import cn.teacheredu.service.NeedDealProcessService;
import cn.teacheredu.vo.DepartmentQueryVo;
import cn.teacheredu.vo.NeedDealProcessQueryVo;

@Service
public class NeedDealProcessServiceImpl implements NeedDealProcessService{
	private static Logger logger = LoggerFactory.getLogger(NeedDealProcessServiceImpl.class);
	@Autowired
	private NeedDealProcessMapper needDealProcessMapper;
	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public NeedDealProcessEntity getNeedDealProcessById(Integer id) throws Exception {
		return this.needDealProcessMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<NeedDealProcessEntity> getNeedDealProcessByVo(NeedDealProcessQueryVo vo) throws Exception {
		return this.needDealProcessMapper.selectByExample(vo);
	}

	@Override
	public int insertNeedDealProcessEntity(NeedDealProcessEntity needDealProcessEntity) throws Exception {
		this.needDealProcessMapper.insertSelective(needDealProcessEntity);
		return needDealProcessEntity.getId();
	}

	@Override
	public int updateNeedDealProcess(NeedDealProcessEntity needDealProcessEntity) throws Exception {
		return this.needDealProcessMapper.updateByPrimaryKeySelective(needDealProcessEntity);
	}

	@Override
	public int deteleNeedDealProcessById(Integer id) throws Exception {
		return this.needDealProcessMapper.deleteByPrimaryKey(id);
	}

	@Override
	public long getCountByVo(NeedDealProcessQueryVo vo) throws Exception {
		return this.needDealProcessMapper.countByExample(vo);
	}

	@Override
	public int insertNeedDealProcessEntity(ProcessEntity processEntity) throws Exception {
		if (processEntity != null) {
			NeedDealProcessEntity needDealProcessEntity = new NeedDealProcessEntity();
			needDealProcessEntity.setProcessId(processEntity.getId());
			needDealProcessEntity.setProcessName(processEntity.getTitle());
			needDealProcessEntity.setUserId(processEntity.getCurrStepUserId());
			needDealProcessEntity.setCreateUserId(processEntity.getCreateUserId());
			needDealProcessEntity.setCreateUserName(processEntity.getCreateUserName());
			needDealProcessEntity.setCreateTime(processEntity.getLastStepTime());
			needDealProcessEntity.setStartTime(processEntity.getStartTime());
			needDealProcessEntity.setType(processEntity.getType());
			needDealProcessEntity.setTimeLimit(processEntity.getTotalTime());
			this.needDealProcessMapper.insertSelective(needDealProcessEntity);
			return needDealProcessEntity.getId();
		} else {
			logger.warn("method:insertNeedDealProcessEntity process is null!");
			return 0;
		}
		
	}

	@Override
	public void saveProcessForDepartment(Integer dmType,String level, ProcessEntity processEntity) throws Exception {
		if (processEntity != null && dmType != null) {
			//查询部门ID
			DepartmentQueryVo vo = new DepartmentQueryVo();
			vo.setDmType(dmType.toString());
			vo.setLevel(level);
			List<DepartmentEntity> list = this.departmentMapper.selectByExample(vo);
			if (list != null && list.size() > 0) {
				//查询部门下所有的人员
				List<UserEntity> userList = this.userMapper.getUserListByDmId(list.get(0).getId());
				if (userList != null && userList.size() > 0) {
					NeedDealProcessEntity needDealProcessEntity = new NeedDealProcessEntity();
					needDealProcessEntity.setProcessId(processEntity.getId());
					needDealProcessEntity.setProcessName(processEntity.getTitle());
					needDealProcessEntity.setCreateUserId(processEntity.getCreateUserId());
					needDealProcessEntity.setCreateUserName(processEntity.getCreateUserName());
					needDealProcessEntity.setCreateTime(processEntity.getLastStepTime());
					needDealProcessEntity.setStartTime(processEntity.getStartTime());
					needDealProcessEntity.setType(2);//流程结束后的查阅
					needDealProcessEntity.setTimeLimit(processEntity.getTotalTime());
					for (UserEntity user : userList) {
						needDealProcessEntity.setId(null);
						needDealProcessEntity.setUserId(user.getId());
						this.needDealProcessMapper.insertSelective(needDealProcessEntity);
					}
				} 
			} else {
				logger.warn("method:saveProcessForDepartment dmList is null!");
			}
		} else {
			logger.warn("method:saveProcessForDepartment process or dmType is null!");
		}
		
	}

}
