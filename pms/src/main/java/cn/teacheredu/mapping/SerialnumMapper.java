package cn.teacheredu.mapping;

import cn.teacheredu.entity.SerialnumEntity;
import cn.teacheredu.vo.SerialnumQueryVo;

import java.util.List;

public interface SerialnumMapper {
	
    long countByExample(SerialnumQueryVo vo) throws Exception;

    int deleteByPrimaryKey(Integer id) throws Exception;

    int insertSelective(SerialnumEntity record) throws Exception;

    List<SerialnumEntity> selectByExample(SerialnumQueryVo vo) throws Exception;

    SerialnumEntity selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(SerialnumEntity record) throws Exception;

}