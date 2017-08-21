package cn.teacheredu.mapping;

import java.util.List;

import cn.teacheredu.entity.InvoiceEntity;
import cn.teacheredu.vo.InvoiceQueryVo;

/**
 * 发票申请表
 * @author teacheredu
 *
 */
public interface InvoiceMapper {
	long countByExample(InvoiceQueryVo vo) throws Exception;

	int deleteByPrimaryKey(Integer id) throws Exception;

	int insertSelective(InvoiceEntity record) throws Exception;

	List<InvoiceEntity> selectByExample(InvoiceQueryVo vo) throws Exception;

	InvoiceEntity selectByPrimaryKey(Integer id) throws Exception;

	int updateByPrimaryKeySelective(InvoiceEntity record) throws Exception;

}