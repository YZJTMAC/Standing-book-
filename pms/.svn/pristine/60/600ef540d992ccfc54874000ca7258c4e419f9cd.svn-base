package cn.teacheredu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.DepartmentEntity;
import cn.teacheredu.mapping.DepartmentMapper;
import cn.teacheredu.service.DepartmentService;
import cn.teacheredu.vo.DepartmentQueryVo;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentMapper departmentMapper;

	@Override
	public DepartmentEntity getDepartmentById(Integer dmId) throws Exception {
		return this.departmentMapper.selectByPrimaryKey(dmId);
	}

	@Override
	public List<DepartmentEntity> getDepartmentByVo(DepartmentQueryVo vo) throws Exception {
		return this.departmentMapper.selectByExample(vo);
	}

	@Override
	public int insertDepartmentEntity(DepartmentEntity departmentEntity) throws Exception {
		this.departmentMapper.insertSelective(departmentEntity);
		return departmentEntity.getId();
	}

	@Override
	public int updateDepartment(DepartmentEntity departmentEntity) throws Exception {
		return this.departmentMapper.updateByPrimaryKeySelective(departmentEntity);				
	}

	@Override
	public long getCountByVo(DepartmentQueryVo vo) throws Exception {
		return this.departmentMapper.countByExample(vo);
	}

	@Override
	public int deteleDepartmentById(Integer dmId) throws Exception {
		return this.departmentMapper.deleteByPrimaryKey(dmId);
	}

	@Override
	public List<DepartmentEntity> getDepartmentByUserId(Integer userId) throws Exception {
		if (userId == null) {
			return new ArrayList<DepartmentEntity>();
		} 
		List<DepartmentEntity> list = this.departmentMapper.getDepartmentByUserId(userId);
		return list;
	}

	@Override
	public List<DepartmentEntity> getLocalDepartmentList(String dmType) throws Exception {
		if (StringUtils.isBlank(dmType) || !StringUtils.isNumeric(dmType)) {
			return new ArrayList<DepartmentEntity>();
		}
		DepartmentQueryVo vo = new DepartmentQueryVo();
		vo.setDmType(dmType);
		List<DepartmentEntity> list = this.departmentMapper.selectByExample(vo);
		return list;
	}

	@Override
	public List<DepartmentEntity> getDepartmentListByProcince(Integer province) throws Exception {
		if (province == null) {
			return new ArrayList<DepartmentEntity>();
		}
		DepartmentQueryVo vo = new DepartmentQueryVo();
		vo.setProvince(province);
		List<DepartmentEntity> list = this.departmentMapper.selectByExample(vo);
		return list;
	}
}
