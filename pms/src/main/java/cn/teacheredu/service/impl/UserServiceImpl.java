package cn.teacheredu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.mapping.UserMapper;
import cn.teacheredu.service.UserDepartmentService;
import cn.teacheredu.service.UserService;
import cn.teacheredu.utils.SystemConst;
import cn.teacheredu.vo.UserQueryVo;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserDepartmentService userDepartmentService;

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public UserEntity getUserEntityById(Integer userId) throws Exception {
		return this.userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public List<UserEntity> getUserEntities(UserQueryVo vo) throws Exception {
		return this.userMapper.selectByExample(vo);
	}

	@Override
	public int insertUserEntity(UserEntity userEntity) throws Exception {
		this.userMapper.insertSelective(userEntity);
		userDepartmentService.saveOrUpdate(userEntity.getId(), userEntity.getDmIdList());
		return userEntity.getId(); 
	}

	@Override
	public UserEntity findByUsername(String loginName) throws Exception {
		return this.userMapper.selectByLoginName(loginName);
	}

	@Override
	public long getCountByVo(UserQueryVo vo) throws Exception {
		return this.userMapper.countByExample(vo);
	}

	@Override
	public int deteleUserById(Integer userId) throws Exception {
		userDepartmentService.deteleUserDepartmentByUserId(userId);
		return this.userMapper.deleteByPrimaryKey(userId);
	}

	@Override
	public int updateUser(UserEntity user) throws Exception {
		int i =  this.userMapper.updateByPrimaryKeySelective(user);
		userDepartmentService.saveOrUpdate(user.getId(), user.getDmIdList());
		return i;
	}
	
	@Override
	public int updateMyUser(UserEntity user) throws Exception {
		int i =  this.userMapper.updateByPrimaryKeySelective(user);
		return i;
	}

	@Override
	public List<UserEntity> getUserListByDmId(Integer dmId) throws Exception {
		if (dmId == null) {
			return null;
		}
		return this.userMapper.getUserListByDmId(dmId);
	}

	@Override
	public void dealList(List<?> list, String column) throws Exception {
		if (list == null || column == null || list.size() > 100) {
			return;
		}
		for (Object object : list) {
			if (object != null) {
				Integer userId = (Integer) PropertyUtils.getProperty(object, column);
				if (userId != null) {
					UserEntity user = this.getUserEntityById(userId);
					@SuppressWarnings("unchecked")
					Map<String, Object> map = (Map<String, Object>) PropertyUtils.getProperty(object, "extInfo");
					if (map == null) {
						map = new HashMap<String, Object>();
					}
					map.put(SystemConst.USER_REALNAME, user == null ? "---" : user.getRealname());
					PropertyUtils.setProperty(object,"extInfo",map);
				}
			}
		}
	}

	@Override
	public void updateProcessNameByUserId(Integer userId, String name) throws Exception {
		if (userId != null && !"".equals(name)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId",userId);
			map.put("name", name);
			
			this.userMapper.updateProcessNameByUserId(map);
			this.userMapper.updateNeedProcessNameByUserId(map);
		}
	}

}
