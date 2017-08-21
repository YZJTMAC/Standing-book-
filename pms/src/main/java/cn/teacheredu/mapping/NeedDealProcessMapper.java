package cn.teacheredu.mapping;

import cn.teacheredu.entity.NeedDealProcessEntity;
import cn.teacheredu.vo.NeedDealProcessQueryVo;

import java.util.List;

public interface NeedDealProcessMapper {
	
    long countByExample(NeedDealProcessQueryVo vo);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(NeedDealProcessEntity record);

    List<NeedDealProcessEntity> selectByExample(NeedDealProcessQueryVo vo);

    NeedDealProcessEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NeedDealProcessEntity record);
    
    int updateFinishedById(Integer id);

}