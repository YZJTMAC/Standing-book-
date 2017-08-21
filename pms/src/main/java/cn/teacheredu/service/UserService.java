package cn.teacheredu.service;

import java.util.List;

import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.vo.UserQueryVo;


public interface UserService {

	UserEntity getUserEntityById(Integer userId) throws Exception;
	
	List<UserEntity> getUserEntities(UserQueryVo vo) throws Exception;
	
	int insertUserEntity(UserEntity userEntity) throws Exception;
	
	
	UserEntity findByUsername(String loginName) throws Exception;

	long getCountByVo(UserQueryVo vo)throws Exception;
	
	
	int deteleUserById(Integer userId) throws Exception;
	
	int updateUser(UserEntity user) throws Exception;
	
	int updateMyUser(UserEntity user) throws Exception;
	
	/**
	 * 通过部门ID筛选用户，即查询某个部门下所有的人员
	 * @param dmId 部门ID
	 * @return
	 * @throws Exception
	 */
	List<UserEntity> getUserListByDmId(Integer dmId) throws Exception;
	
	/**
	 * 给集合对象赋值用户真实姓名等信息
	 * @param list 集合对象
	 * @param column  对象中user_id所对应的属性名
	 * @throws Exception
	 */
	void dealList(List<?> list,String column) throws Exception;
	
	/**
	 * 如果用户修改了真实姓名，需要把流程表的用户姓名同步更新
	 * @param userId
	 * @param name
	 * @throws Exception
	 */
	void updateProcessNameByUserId(Integer userId, String name) throws Exception;
}
