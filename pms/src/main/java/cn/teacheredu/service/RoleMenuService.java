package cn.teacheredu.service;

import java.util.List;

import cn.teacheredu.entity.RoleMenuEntity;
import cn.teacheredu.vo.RoleMenuQueryVo;


public interface RoleMenuService {

	RoleMenuEntity getRoleMenuById(Integer id) throws Exception;
	
	List<RoleMenuEntity> getRoleMenuByVo(RoleMenuQueryVo vo) throws Exception;
	
	int insertRoleMenuEntity(RoleMenuEntity roleMenuEntity) throws Exception;
	
	int updateRoleMenu(RoleMenuEntity roleMenuEntity) throws Exception;
	
	int deteleRoleMenuById(Integer id) throws Exception;
	
	int deteleRoleMenuByRoleId(Integer roleId) throws Exception;
	
	long getCountByVo(RoleMenuQueryVo vo) throws Exception;
	
	void saveOrUpdate(Integer roleId, List<Integer> menuIdList) throws Exception;
	
	public List<Integer> queryMenuIdList(Integer roleId) throws Exception;
	
}
