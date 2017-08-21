package cn.teacheredu.mapping;

import cn.teacheredu.entity.LoginLogEntity;
import cn.teacheredu.vo.LoginLogQueryVo;

import java.util.List;

public interface LoginLogMapper {
	
    long countByExample(LoginLogQueryVo vo) throws Exception;

    int deleteByPrimaryKey(Integer id) throws Exception;

    int insertSelective(LoginLogEntity record) throws Exception;

    List<LoginLogEntity> selectByExample(LoginLogQueryVo vo) throws Exception;

    LoginLogEntity selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(LoginLogEntity record) throws Exception;

}