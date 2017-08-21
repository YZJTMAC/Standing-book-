package cn.teacheredu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.MenuEntity;
import cn.teacheredu.mapping.MenuMapper;
import cn.teacheredu.service.MenuService;
import cn.teacheredu.utils.SpyMemcachedManager;
import cn.teacheredu.vo.MenuQueryVo;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private SpyMemcachedManager memcachedManager;
	
	@Override
	public MenuEntity getMenuById(Integer menuId) throws Exception {
		return this.menuMapper.selectByPrimaryKey(menuId);
	}

	@Override
	public List<MenuEntity> getMenuByVo(MenuQueryVo vo) throws Exception {
		return this.menuMapper.selectByExample(vo);
	}

	@Override
	public int insertMenuEntity(MenuEntity menuEntity) throws Exception {
		this.menuMapper.insertSelective(menuEntity);
		return menuEntity.getId();
	}

	@Override
	public long getCountByVo(MenuQueryVo vo) throws Exception {
		return this.menuMapper.countByExample(vo);
	}

	@Override
	public int updateMenu(MenuEntity menuEntity) throws Exception {
		return this.menuMapper.updateByPrimaryKeySelective(menuEntity);
	}

	@Override
	public int deteleMenuById(Integer menuId) throws Exception {
		return this.menuMapper.deleteByPrimaryKey(menuId);
	}

	@Override
	public List<MenuEntity> getMenuByRoleId(Integer roleId) throws Exception {
		List<MenuEntity> list = null;
		if (roleId != null) {
			list = this.menuMapper.getMenuByRoleId(roleId);
		}
		return list == null ? new ArrayList<MenuEntity>() : list;
	}

	@Override
	public Map<String, List<MenuEntity>> getMenuMapByRoleId(Integer roleId) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, List<MenuEntity>> menuMap = (Map<String, List<MenuEntity>>) memcachedManager.get("pms_roleMenu_"+roleId);
		if (menuMap == null) { //一个角色不应该什么权限都没有
			menuMap = new HashMap<String, List<MenuEntity>>();
			List<MenuEntity> clMenuList = new ArrayList<MenuEntity>();
			List<MenuEntity> cdMenuList = new ArrayList<MenuEntity>();
			
			List<MenuEntity> menuList = this.getMenuByRoleId(roleId);
			for (MenuEntity menu : menuList) {
				if (menu.getType() == 0) { //目录
					clMenuList.add(menu);
				} else if(menu.getType() == 1){ //菜单
					cdMenuList.add(menu);
				} else {
					continue;
				}
			}
			menuMap.put("catalog", clMenuList);
			menuMap.put("menu", cdMenuList);
			//分类菜单
			for (MenuEntity menu : cdMenuList) {
				List<MenuEntity> list = menuMap.get(menu.getParentId().toString());
				if (list == null) {
					list = new ArrayList<MenuEntity>();
					menuMap.put(menu.getParentId().toString(), list);
				}
				list.add(menu);
			}
			memcachedManager.set("pms_roleMenu_"+roleId, menuMap, 600);
			
		}
		return menuMap;
	}

	@Override
	public List<MenuEntity> getMenuListByUid(Integer uid) throws Exception {
		@SuppressWarnings("unchecked")
		List<MenuEntity> list = (List<MenuEntity>) memcachedManager.get("pms_menu_shortcut"+uid);
		if (list == null) {
			if (uid != null) {
				list = this.menuMapper.getMenuListByUid(uid);
			}
			memcachedManager.set("pms_menu_shortcut"+uid, list, 600);
		}
		return list;
	}

	@Override
	public List<String> getPsListByRoleId(Integer roleId) throws Exception {
		if (roleId == null) {
			return null;
		}
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) memcachedManager.get("pms_ps_list" + roleId);
		if (list == null) {
			list = this.menuMapper.getPsListByRoleId(roleId);
			memcachedManager.set("pms_ps_list" + roleId, list, 600);
		}
		return list;
	}
	
}
