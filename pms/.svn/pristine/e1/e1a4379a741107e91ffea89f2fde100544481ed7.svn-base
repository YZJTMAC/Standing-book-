package cn.teacheredu.service;

import java.util.List;

import cn.teacheredu.entity.UserDepartmentEntity;
import cn.teacheredu.vo.UserDepartmentQueryVo;

public interface UserDepartmentService {

	UserDepartmentEntity getUserDepartmentById(Integer udId) throws Exception;

	List<UserDepartmentEntity> getUserDepartmentByVo(UserDepartmentQueryVo vo) throws Exception;

	int insertUserDepartmentEntity(UserDepartmentEntity udEntity) throws Exception;

	long getCountByVo(UserDepartmentQueryVo vo) throws Exception;

	int updateUserDepartment(UserDepartmentEntity udEntity) throws Exception;

	int deteleUserDepartmentById(Integer udId) throws Exception;
	
	void saveOrUpdate(Integer userId, List<Integer> dmIdList) throws Exception;

	int deteleUserDepartmentByUserId(Integer userId) throws Exception;
}
