package cn.teacheredu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.RoleMenuEntity;
import cn.teacheredu.mapping.RoleMenuMapper;
import cn.teacheredu.service.RoleMenuService;
import cn.teacheredu.vo.RoleMenuQueryVo;


@Service
public class RoleMenuServiceImpl implements RoleMenuService{

	@Autowired
	private RoleMenuMapper roleMenuMapper;

	@Override
	public RoleMenuEntity getRoleMenuById(Integer id) throws Exception {
		return this.roleMenuMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<RoleMenuEntity> getRoleMenuByVo(RoleMenuQueryVo vo) throws Exception {
		return this.roleMenuMapper.selectByExample(vo);
	}

	@Override
	public int insertRoleMenuEntity(RoleMenuEntity roleMenuEntity) throws Exception {
		this.roleMenuMapper.insertSelective(roleMenuEntity);
		return roleMenuEntity.getId();
	}

	@Override
	public int updateRoleMenu(RoleMenuEntity roleMenuEntity) throws Exception {
		return this.roleMenuMapper.updateByPrimaryKeySelective(roleMenuEntity);
	}

	@Override
	public int deteleRoleMenuById(Integer id) throws Exception {
		return this.roleMenuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deteleRoleMenuByRoleId(Integer roleId) throws Exception {
		return this.roleMenuMapper.deleteByRoleId(roleId);
	}

	@Override
	public long getCountByVo(RoleMenuQueryVo vo) throws Exception {
		return this.roleMenuMapper.countByExample(vo);
	}

	@Override
	public void saveOrUpdate(Integer roleId, List<Integer> menuIdList) throws Exception {
		//先删除角色与菜单关系
		this.roleMenuMapper.deleteByRoleId(roleId);
				
		if(menuIdList.size() == 0){
			return;
		}
		
		//保存角色与菜单关系
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("roleId", roleId);
		map.put("menuIdList", menuIdList);
		this.roleMenuMapper.saveRoleMenu(map);
	}

	@Override
	public List<Integer> queryMenuIdList(Integer roleId) throws Exception {
		return this.roleMenuMapper.queryMenuIdList(roleId);
	}
	
}
