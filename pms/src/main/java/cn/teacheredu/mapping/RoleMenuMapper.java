package cn.teacheredu.mapping;

import cn.teacheredu.entity.RoleMenuEntity;
import cn.teacheredu.vo.RoleMenuQueryVo;

import java.util.List;
import java.util.Map;

public interface RoleMenuMapper {
    long countByExample(RoleMenuQueryVo vo) throws Exception;

    int deleteByPrimaryKey(Integer id) throws Exception;
    
    int deleteByRoleId(Integer roleId) throws Exception;

    int insertSelective(RoleMenuEntity record) throws Exception;

    List<RoleMenuEntity> selectByExample(RoleMenuQueryVo vo) throws Exception;

    RoleMenuEntity selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(RoleMenuEntity record) throws Exception;
    
    List<Integer> queryMenuIdList(Integer roleId) throws Exception;
    
    void saveRoleMenu(Map<String, Object> map) throws Exception;

}