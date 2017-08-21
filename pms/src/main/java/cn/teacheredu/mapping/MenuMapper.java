package cn.teacheredu.mapping;

import cn.teacheredu.entity.MenuEntity;
import cn.teacheredu.vo.MenuQueryVo;

import java.util.List;

public interface MenuMapper {
    long countByExample(MenuQueryVo vo) throws Exception;

    int deleteByPrimaryKey(Integer id) throws Exception;

    int insertSelective(MenuEntity record) throws Exception;

    List<MenuEntity> selectByExample(MenuQueryVo vo) throws Exception;

    MenuEntity selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(MenuEntity record) throws Exception;
    
    List<MenuEntity> getMenuByRoleId(Integer roleId) throws Exception;

	List<MenuEntity> getMenuListByUid(Integer id) throws Exception;

	List<String> getPsListByRoleId(Integer roleId) throws Exception;


}