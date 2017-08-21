package cn.teacheredu.mapping;

import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.vo.UserQueryVo;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    long countByExample(UserQueryVo vo) throws Exception;

    int deleteByPrimaryKey(Integer id) throws Exception;

    int insertSelective(UserEntity record) throws Exception;

    List<UserEntity> selectByExample(UserQueryVo vo) throws Exception;

    UserEntity selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(UserEntity record) throws Exception;
    
    UserEntity selectByLoginName(String loginName) throws Exception;

	List<UserEntity> getUserListByDmId(Integer dmId) throws Exception;

	void updateProcessNameByUserId(Map<String, Object> map);
	void updateNeedProcessNameByUserId(Map<String, Object> map);

}