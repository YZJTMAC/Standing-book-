package cn.teacheredu.service;

import java.math.BigDecimal;
import java.util.List;

import cn.teacheredu.entity.PaymentInvoiceEntity;
import cn.teacheredu.form.QueryTermsForm;
import cn.teacheredu.utils.PageUtils;
import cn.teacheredu.vo.PaymentInvoiceQueryVo;

/**
 * 到款发票对应表
 * @author teacheredu
 *
 */
public interface PaymentInvoiceService {

	PaymentInvoiceEntity getPaymentInvoiceById(Integer piId) throws Exception;
	
	List<PaymentInvoiceEntity> getPaymentInvoiceByVo(PaymentInvoiceQueryVo vo) throws Exception;
	
	int insertPaymentInvoiceEntity(PaymentInvoiceEntity piEntity) throws Exception;
	
	long getCountByVo(PaymentInvoiceQueryVo vo) throws Exception;
	
	int updatePaymentInvoice(PaymentInvoiceEntity piEntity) throws Exception;
	
	int detelePaymentInvoiceById(Integer piId) throws Exception;
	
	/**
	 * 通过form查询发票
	 * @param form
	 * @return
	 * @throws Exception
	 */
	PageUtils<PaymentInvoiceEntity> getPaymentInvoiceList(QueryTermsForm form) throws Exception;
	
	/**
	 * 通过projectId得到已开票金额
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	BigDecimal getTotalInvoiceByProjectId(Integer projectId) throws Exception;
	
	/**
	 * 在流程正常结束时把本流程下所有的申请发票记录的结果置为已开
	 * @param invoiceId  发票申请表标识
	 * @return
	 * @throws Exception
	 */
	boolean editResultByInvoiceId(Integer invoiceId) throws Exception;
	
	/**
	 * 在流程非正常结束时(被退回)把本流程下所有的申请发票记录删除 （假删）
	 * 为什么要删除？如果不删除业务员重新申请的时候，就看不到这些到款信息了，详见PaymentController的paymentTablePage方法
	 * @param invoiceId  发票申请表标识
	 * @return
	 * @throws Exception
	 */
	boolean editAvailableByInvoiceId(Integer invoiceId) throws Exception;
	
	/**
	 * 查询项目中已经申请过发票的到款信息ID和正在申请发票的到款信息ID
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	List<Integer> selectInvoiceByProjectId(Integer projectId) throws Exception;
	
}
