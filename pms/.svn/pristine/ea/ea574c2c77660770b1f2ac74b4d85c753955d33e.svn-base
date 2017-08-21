package cn.teacheredu.service;

import java.util.List;

import cn.teacheredu.entity.DepartmentEntity;
import cn.teacheredu.vo.DepartmentQueryVo;



public interface DepartmentService {

	DepartmentEntity getDepartmentById(Integer dmId) throws Exception;
	
	List<DepartmentEntity> getDepartmentByVo(DepartmentQueryVo vo) throws Exception;
	
	int insertDepartmentEntity(DepartmentEntity departmentEntity) throws Exception;
	
	long getCountByVo(DepartmentQueryVo vo) throws Exception;
	
	int updateDepartment(DepartmentEntity departmentEntity) throws Exception;
	
	int deteleDepartmentById(Integer dmId) throws Exception;
	
	/**
	 * 通过用户ID，查询其部门信息，关联user_department表。
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<DepartmentEntity> getDepartmentByUserId(Integer userId) throws Exception;
	
	/**
	 * 通过dm_type查询某一类部门
	 * dm_type 部门类型，0 研发 1 教务 2 商务 3 财务 4 办事处、分公司、子公司 5 总经理办公室 6 分管部门
	 * @return
	 * @throws Exception
	 */
	List<DepartmentEntity> getLocalDepartmentList(String dmType) throws Exception;
	
	
	/**
	 * 通过省份ID查询类型为4的部门
	 * @return
	 * @throws Exception
	 */
	List<DepartmentEntity> getDepartmentListByProcince(Integer province) throws Exception;
}
