package cn.teacheredu.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.teacheredu.entity.FundsEntity;
import cn.teacheredu.vo.FundsQueryVo;

/**
 * 经费申请表
 * @author teacheredu
 *
 */
public interface FundsMapper {
	long countByExample(FundsQueryVo vo) throws Exception;

	int deleteByPrimaryKey(Integer id) throws Exception;

	/**
	 * 插入数据.
	 * @param record
	 */
	int insertSelective(FundsEntity record) throws Exception;

	List<FundsEntity> selectByExample(FundsQueryVo vo) throws Exception;

	FundsEntity selectByPrimaryKey(Integer id) throws Exception;

	int updateByPrimaryKeySelective(FundsEntity record) throws Exception;

	void editAvailableById(Integer id) throws Exception;

	void editWonPayById(@Param(value="id") Integer id, @Param(value="status") String status) throws Exception;
	

}