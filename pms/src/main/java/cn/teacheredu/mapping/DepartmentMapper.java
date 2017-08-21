package cn.teacheredu.mapping;

import java.util.List;
import cn.teacheredu.entity.DepartmentEntity;
import cn.teacheredu.vo.DepartmentQueryVo;

public interface DepartmentMapper {
    
    int deleteByPrimaryKey(Integer id) throws Exception;

    int insertSelective(DepartmentEntity record) throws Exception;

    long countByExample(DepartmentQueryVo vo) throws Exception;
    
    List<DepartmentEntity> selectByExample(DepartmentQueryVo vo) throws Exception;

    DepartmentEntity selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(DepartmentEntity record) throws Exception;

	List<DepartmentEntity> getDepartmentByUserId(Integer userId) throws Exception;

}