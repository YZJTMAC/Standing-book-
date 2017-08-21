package cn.teacheredu.service;

import java.util.List;

import cn.teacheredu.entity.InvoiceEntity;
import cn.teacheredu.vo.InvoiceQueryVo;

/**
 * 发票申请表
 * @author teacheredu
 *
 */
public interface InvoiceService {

	InvoiceEntity getInvoiceById(Integer invoiceId) throws Exception;
	
	List<InvoiceEntity> getInvoiceByVo(InvoiceQueryVo vo) throws Exception;
	
	int insertInvoiceEntity(InvoiceEntity invoiceEntity) throws Exception;
	
	long getCountByVo(InvoiceQueryVo vo) throws Exception;
	
	int updateInvoice(InvoiceEntity invoiceEntity) throws Exception;
	
	int deteleInvoiceById(Integer invoiceId) throws Exception;
	
	
}
