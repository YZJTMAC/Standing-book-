package cn.teacheredu.mapping;

import java.util.List;
import java.util.Map;

import cn.teacheredu.entity.UserDepartmentEntity;
import cn.teacheredu.vo.UserDepartmentQueryVo;

public interface UserDepartmentMapper {
	long countByExample(UserDepartmentQueryVo vo) throws Exception;

	int deleteByPrimaryKey(Integer id) throws Exception;

	int insertSelective(UserDepartmentEntity record) throws Exception;

	List<UserDepartmentEntity> selectByExample(UserDepartmentQueryVo vo) throws Exception;

	UserDepartmentEntity selectByPrimaryKey(Integer id) throws Exception;

	int updateByPrimaryKeySelective(UserDepartmentEntity record) throws Exception;

	int deleteByUserId(Integer userId) throws Exception;

	void saveUserDepartment(Map<String, Object> map) throws Exception;

}