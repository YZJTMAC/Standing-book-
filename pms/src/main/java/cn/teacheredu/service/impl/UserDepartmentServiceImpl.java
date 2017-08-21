package cn.teacheredu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.UserDepartmentEntity;
import cn.teacheredu.mapping.UserDepartmentMapper;
import cn.teacheredu.service.UserDepartmentService;
import cn.teacheredu.vo.UserDepartmentQueryVo;

@Service
public class UserDepartmentServiceImpl implements UserDepartmentService {

	@Autowired
	private UserDepartmentMapper userDepartmentMapper;
	
	@Override
	public UserDepartmentEntity getUserDepartmentById(Integer udId) throws Exception {
		return this.userDepartmentMapper.selectByPrimaryKey(udId);
	}

	@Override
	public List<UserDepartmentEntity> getUserDepartmentByVo(UserDepartmentQueryVo vo) throws Exception {
		return this.userDepartmentMapper.selectByExample(vo);
	}

	@Override
	public int insertUserDepartmentEntity(UserDepartmentEntity udEntity) throws Exception {
		this.userDepartmentMapper.insertSelective(udEntity);
		return udEntity.getId();
	}

	@Override
	public long getCountByVo(UserDepartmentQueryVo vo) throws Exception {
		return this.userDepartmentMapper.countByExample(vo);
	}

	@Override
	public int updateUserDepartment(UserDepartmentEntity udEntity) throws Exception {
		return this.userDepartmentMapper.updateByPrimaryKeySelective(udEntity);
	}

	@Override
	public int deteleUserDepartmentById(Integer udId) throws Exception {
		return this.userDepartmentMapper.deleteByPrimaryKey(udId);
	}
	
	@Override
	public int deteleUserDepartmentByUserId(Integer userId) throws Exception {
		return this.userDepartmentMapper.deleteByUserId(userId);
	}

	@Override
	public void saveOrUpdate(Integer userId, List<Integer> dmIdList) throws Exception {
		//先删除用户与部门的关系
		this.userDepartmentMapper.deleteByUserId(userId);
				
		if(dmIdList == null || dmIdList.size() == 0){
			return;
		}
		
		//保存角色与菜单关系
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("dmIdList", dmIdList);
		this.userDepartmentMapper.saveUserDepartment(map);
		
	}

}
