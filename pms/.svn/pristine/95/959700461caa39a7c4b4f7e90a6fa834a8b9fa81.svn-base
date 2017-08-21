package cn.teacheredu.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.teacheredu.entity.ProjectYearSummaryEntity;

public interface ProjectYearSummaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectYearSummaryEntity record);

    int insertSelective(ProjectYearSummaryEntity record);

    ProjectYearSummaryEntity selectByPrimaryKey(Integer id);

    List<ProjectYearSummaryEntity> selectByProjectId(Integer projectId);

    List<ProjectYearSummaryEntity> selectYearSummaryValue(@Param("projectId")Integer projectId, @Param("year")Integer year);
    
    int updateByPrimaryKeySelective(ProjectYearSummaryEntity record);

    int updateByPrimaryKey(ProjectYearSummaryEntity record);
}