package cn.teacheredu.mapping;

import java.util.List;

import cn.teacheredu.entity.PaymentInvoiceEntity;
import cn.teacheredu.vo.PaymentInvoiceQueryVo;

/**
 * 到款发票对应表
 * @author teacheredu
 *
 */
public interface PaymentInvoiceMapper {
	long countByExample(PaymentInvoiceQueryVo vo) throws Exception;

	int deleteByPrimaryKey(Integer id) throws Exception;

	int insertSelective(PaymentInvoiceEntity record) throws Exception;

	List<PaymentInvoiceEntity> selectByExample(PaymentInvoiceQueryVo vo) throws Exception;

	PaymentInvoiceEntity selectByPrimaryKey(Integer id) throws Exception;

	int updateByPrimaryKeySelective(PaymentInvoiceEntity record) throws Exception;
	
	int editResultByInvoiceId(Integer invoiceId) throws Exception;
	
	int editAvailableByInvoiceId(Integer invoiceId) throws Exception;
	
	List<Integer> selectInvoiceByProjectId(Integer projectId) throws Exception;
}