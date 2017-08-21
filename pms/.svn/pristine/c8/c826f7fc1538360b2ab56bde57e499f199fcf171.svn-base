package cn.teacheredu.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import cn.teacheredu.entity.PaymentEntity;
import cn.teacheredu.form.PaymentForm;
import cn.teacheredu.form.QueryTermsForm;
import cn.teacheredu.utils.PageUtils;
import cn.teacheredu.vo.PaymentQueryVo;

/**
 * 到款表
 * @author teacheredu
 *
 */
public interface PaymentService {

	PaymentEntity getPaymentById(Integer paymentId) throws Exception;

	List<PaymentEntity> getPaymentByVo(PaymentQueryVo vo) throws Exception;

	int insertPaymentEntity(PaymentEntity paymentEntity) throws Exception;

	long getCountByVo(PaymentQueryVo vo) throws Exception;

	int updatePayment(PaymentEntity paymentEntity) throws Exception;

	int detelePaymentById(Integer paymentId) throws Exception;
	
	/**
	 * 根据form查询到款表
	 * @param queryForm
	 * @return
	 */
	PageUtils<PaymentEntity> getPayMentList(QueryTermsForm queryForm) throws Exception;
	
	/**
	 * 提交到款分配表
	 * @param form
	 * @throws Exception
	 */
	void savePaymentAndProcess(PaymentForm form) throws Exception;
	
	
	/**
	 * 通过项目Id得到项目已到款金额
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	BigDecimal getRealPayTotalByProjectId(Integer projectId) throws Exception;
	
	/**
	 * 通过发票申请表ID，等流程结束时把到款信息设置为已开票的状态
	 * @param invoiceId
	 * @throws Exception
	 */
	void editHasInvoiceByInvoiceId(Integer invoiceId) throws Exception;
	/**
	 * 遍历paymentList，查询到款表导入进去的到款信息
	 * @param invoiceId
	 * @throws Exception
	 */
	List<PaymentEntity> getRepeatPayment(List<PaymentEntity> paymentEntity) throws Exception;

}
