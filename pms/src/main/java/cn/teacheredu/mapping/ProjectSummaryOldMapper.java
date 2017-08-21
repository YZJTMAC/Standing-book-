package cn.teacheredu.mapping;

import cn.teacheredu.entity.ProjectSummaryOldEntity;
import cn.teacheredu.vo.ProjectSummaryOldQueryVo;

import java.util.List;

public interface ProjectSummaryOldMapper {
	
    long countByExample(ProjectSummaryOldQueryVo vo) throws Exception;

    int deleteByPrimaryKey(Integer id) throws Exception;

    int insertSelective(ProjectSummaryOldEntity record) throws Exception;

    List<ProjectSummaryOldEntity> selectByExample(ProjectSummaryOldQueryVo vo) throws Exception;

    ProjectSummaryOldEntity selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(ProjectSummaryOldEntity record) throws Exception;

}