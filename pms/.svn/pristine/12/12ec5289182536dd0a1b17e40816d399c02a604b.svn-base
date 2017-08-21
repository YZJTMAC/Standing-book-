package cn.teacheredu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.RoleEntity;
import cn.teacheredu.mapping.RoleMapper;
import cn.teacheredu.service.RoleMenuService;
import cn.teacheredu.service.RoleService;
import cn.teacheredu.utils.SpyMemcachedManager;
import cn.teacheredu.vo.RoleQueryVo;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleMenuService roleMenuService;
	@Autowired
	private SpyMemcachedManager memcachedManager;
	
	@Override
	public RoleEntity getRoleById(Integer roleId) throws Exception {
		return this.roleMapper.selectByPrimaryKey(roleId);
	}

	@Override
	public List<RoleEntity> getRoleByVo(RoleQueryVo vo) throws Exception {
		return this.roleMapper.selectByExample(vo);
	}

	@Override
	public int insertRoleEntity(RoleEntity roleEntity) throws Exception {
		this.roleMapper.insertSelective(roleEntity);
		roleMenuService.saveOrUpdate(roleEntity.getId(), roleEntity.getMenuIdList());
		return roleEntity.getId();
	}

	@Override
	public long getCountByVo(RoleQueryVo vo) throws Exception {
		return this.roleMapper.countByExample(vo);
	}

	@Override
	public int updateRole(RoleEntity roleEntity) throws Exception {
		memcachedManager.delete("pms_roleMenu_"+roleEntity.getId());
		memcachedManager.delete("pms_ps_list"+roleEntity.getId());
		int i = this.roleMapper.updateByPrimaryKeySelective(roleEntity);
		roleMenuService.saveOrUpdate(roleEntity.getId(), roleEntity.getMenuIdList());
		return i;
	}

	@Override
	public int deteleRoleById(Integer roleId) throws Exception {
		roleMenuService.deteleRoleMenuByRoleId(roleId);
		return this.roleMapper.deleteByPrimaryKey(roleId);
	}

}
