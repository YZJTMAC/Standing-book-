package cn.teacheredu.mapping;

import cn.teacheredu.entity.RoleEntity;
import cn.teacheredu.vo.RoleQueryVo;

import java.util.List;

public interface RoleMapper {
    long countByExample(RoleQueryVo vo) throws Exception;

    int deleteByPrimaryKey(Integer id) throws Exception;

    int insertSelective(RoleEntity record) throws Exception;

    List<RoleEntity> selectByExample(RoleQueryVo vo) throws Exception;

    RoleEntity selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(RoleEntity record) throws Exception;

}