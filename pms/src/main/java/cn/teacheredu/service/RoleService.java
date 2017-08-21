package cn.teacheredu.service;

import java.util.List;

import cn.teacheredu.entity.RoleEntity;
import cn.teacheredu.vo.RoleQueryVo;



public interface RoleService {

	RoleEntity getRoleById(Integer roleId) throws Exception;
	
	List<RoleEntity> getRoleByVo(RoleQueryVo vo) throws Exception;
	
	int insertRoleEntity(RoleEntity roleEntity) throws Exception;
	
	long getCountByVo(RoleQueryVo vo) throws Exception;
	
	int updateRole(RoleEntity roleEntity) throws Exception;
	
	int deteleRoleById(Integer roleId) throws Exception;
}
