package cn.teacheredu.mapping;

import java.util.List;

import cn.teacheredu.entity.PaymentEntity;
import cn.teacheredu.vo.PaymentQueryVo;

public interface PaymentMapper {
	long countByExample(PaymentQueryVo vo) throws Exception;

	int deleteByPrimaryKey(Integer id) throws Exception;

	int insertSelective(PaymentEntity record) throws Exception;

	List<PaymentEntity> selectByExample(PaymentQueryVo vo) throws Exception;

	PaymentEntity selectByPrimaryKey(Integer id) throws Exception;

	int updateByPrimaryKeySelective(PaymentEntity record) throws Exception;
	
	int editHasInvoiceById(Integer id) throws Exception;

}