package cn.teacheredu.service;

import java.util.List;
import java.util.Map;

import cn.teacheredu.entity.MenuEntity;
import cn.teacheredu.vo.MenuQueryVo;



public interface MenuService {

	MenuEntity getMenuById(Integer menuId) throws Exception;
	
	List<MenuEntity> getMenuByVo(MenuQueryVo vo) throws Exception;
	
	int insertMenuEntity(MenuEntity menuEntity) throws Exception;
	
	long getCountByVo(MenuQueryVo vo) throws Exception;
	
	int updateMenu(MenuEntity menuEntity) throws Exception;
	
	int deteleMenuById(Integer menuId) throws Exception;
	
	List<MenuEntity> getMenuByRoleId(Integer roleId) throws Exception;
	
	/**
	 * 通过角色ID，得到关于此角色的菜单信息
	 * @param roleId 角色ID
	 * @return 返回的map，封装了多种List，供菜单显示用，具体看代码逻辑
	 * @throws Exception
	 */
	Map<String, List<MenuEntity>> getMenuMapByRoleId(Integer roleId) throws Exception;
	
	/**
	 * 通过用户ID查询其在主页创建的菜单快捷方式
	 * @param uid 用户ID
	 * @return Menu集合
	 * @throws Exception
	 */
	List<MenuEntity> getMenuListByUid(Integer uid) throws Exception;
	
	/**
	 * 通过角色ID，得到其拥有的权限code的list
	 * @param roleId
	 * @return  code集合（menu表的perms列）
	 * @throws Exception
	 */
	List<String> getPsListByRoleId(Integer roleId) throws Exception;
}
