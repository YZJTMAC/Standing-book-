package cn.teacheredu.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.PaymentEntity;
import cn.teacheredu.entity.PaymentInvoiceEntity;
import cn.teacheredu.entity.ProcessEntity;
import cn.teacheredu.form.PaymentForm;
import cn.teacheredu.form.QueryTermsForm;
import cn.teacheredu.mapping.PaymentMapper;
import cn.teacheredu.service.AttachmentService;
import cn.teacheredu.service.PaymentInvoiceService;
import cn.teacheredu.service.PaymentService;
import cn.teacheredu.service.ProcessService;
import cn.teacheredu.utils.PageUtils;
import cn.teacheredu.utils.SystemConst;
import cn.teacheredu.vo.PaymentInvoiceQueryVo;
import cn.teacheredu.vo.PaymentQueryVo;
import cn.teacheredu.vo.ProcessEntityQueryVo;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentMapper paymentMapper;
	@Autowired
	private ProcessService processService;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private PaymentInvoiceService paymentInvoiceService;
	
	@Override
	public PaymentEntity getPaymentById(Integer paymentId) throws Exception {
		return this.paymentMapper.selectByPrimaryKey(paymentId);
	}

	@Override
	public List<PaymentEntity> getPaymentByVo(PaymentQueryVo vo) throws Exception {
		return this.paymentMapper.selectByExample(vo);
	}

	@Override
	public int insertPaymentEntity(PaymentEntity paymentEntity) throws Exception {
		this.paymentMapper.insertSelective(paymentEntity);
		return paymentEntity.getId();
	}

	@Override
	public long getCountByVo(PaymentQueryVo vo) throws Exception {
		return paymentMapper.countByExample(vo);
	}

	@Override
	public int updatePayment(PaymentEntity paymentEntity) throws Exception {
		return this.paymentMapper.updateByPrimaryKeySelective(paymentEntity);
	}

	@Override
	public int detelePaymentById(Integer paymentId) throws Exception {
		return this.paymentMapper.deleteByPrimaryKey(paymentId);
	}

	@Override
	public PageUtils<PaymentEntity> getPayMentList(QueryTermsForm form) throws Exception{
		PageUtils<PaymentEntity>  pageUtil= null;
		if (form.getUserId() == null){
			pageUtil = new PageUtils<PaymentEntity>(new ArrayList<PaymentEntity>(), 0, form.getPageSize(), form.getCurrPage());
		}else{
			PaymentQueryVo vo=new PaymentQueryVo();
			vo.setOrderProperty("id");
			vo.setOrderType(SystemConst.SQL_ORDERTYPE_DESC);
			vo.setPaged(true);
			vo.setPageSize(form.getPageSize());
			vo.setCurPage(form.getCurrPage());
			vo.setStartPosition((form.getCurrPage() - 1) * form.getPageSize());
			if(form.getProjectId()!=null){
				vo.setProjectId(form.getProjectId());
			}
			if (form.getType() == 1 ) { //交易日期
				vo.setFromDate(form.getStartDate());
				vo.setToDate(form.getEndDate());
				vo.setTraTime(new Date());//这时间没有什么用，就是为了区别查询条件是哪个
			} else if (form.getType() == 2 && StringUtils.isNotBlank(form.getValue())) { //通过开票状态查询
				vo.setHasInvoice(Integer.parseInt(form.getValue()));
			} else if(form.getType() == 3 && StringUtils.isNotBlank(form.getValue())){//汇款人
				vo.setRemitter(form.getValue().trim());  
			}
			List<PaymentEntity> list=this.getPaymentByVo(vo);
			
			//查询到款对应的流程信息
			ProcessEntityQueryVo vo2 = new ProcessEntityQueryVo();
			vo2.setTableName(SystemConst.TABLE_PAYMENT);
			
			for (PaymentEntity paymentEntity : list) {
				vo2.setObjectId(paymentEntity.getId());
				List<ProcessEntity> process = this.processService.getProcessByVo(vo2);
				if (process.size() > 0) {
					Map<String, Object> extInfo = new HashMap<String, Object>();
					extInfo.put("process", process.get(0));
					paymentEntity.setExtInfo(extInfo);
				}
			}
			
			long total = this.getCountByVo(vo);
			pageUtil = new PageUtils<PaymentEntity>(list, total, form.getPageSize(), form.getCurrPage());
		}
		return pageUtil;
	}

	@Override
	public void savePaymentAndProcess(PaymentForm form) throws Exception {
		List<PaymentEntity> list = form.getPaymentList();
		Date date = new Date();
		ProcessEntity processEntity = new ProcessEntity();
		processEntity.setCreateUserId(form.getUserId());
		processEntity.setStartTime(date);
		processEntity.setCreateTime(date);
		processEntity.setCurrStepUserId(form.getCurrStepUserId());
		processEntity.setLastStepUserId(form.getUserId());
		processEntity.setLastStepTime(date);
		processEntity.setTableName(SystemConst.TABLE_PAYMENT);
		processEntity.setType(form.getType()); //审批流程类型
		processEntity.setStatus(3);//进行中
		processEntity.setCreateUserName(form.getUserName());
		processEntity.setStepId(form.getStepId());
		for (int j = list.size()-1; j >= 0; j--) {//反着去循环可以确保下个待办人第一个看到的是表中的第一条
			// 1.保存到款信息
			PaymentEntity paymentEntity = list.get(j);
			int i = this.insertPaymentEntity(list.get(j));
			if (i > 0) {
				// 2 .一条到款信息就保存一条待办事项
				processEntity.setId(null);
				processEntity.setTitle(form.getProcessName() + "-" + paymentEntity.getNum());//商务或业务看到的流程标题后面会多一个序号
				processEntity.setObjectId(i);
				int k = this.processService.insertProcessEntity(processEntity);
				if (j == 0) {
					// 3. 有附件的话保存附件
					// 到款表是唯一特殊的，附件要属于多个流程，前期设计时没考虑到，
					// 现在暂且把附件只对应表中的第一条记录，所以j==0的时候保存一下即可
					this.attachmentService.updateAttachmentByIdAndName(form.getAttachIds(), SystemConst.TABLE_PROCESS, k);
				}
			}
		}
	}

	@Override
	public BigDecimal getRealPayTotalByProjectId(Integer projectId) throws Exception {
		BigDecimal total = BigDecimal.ZERO;
		if (projectId != null) {
			PaymentQueryVo vo = new PaymentQueryVo();
			vo.setProjectId(projectId);
			List<PaymentEntity> list = this.getPaymentByVo(vo);
			for (PaymentEntity payment : list) {
				total = total.add(payment.getAmount());
			}
		}
		return total;
	}

	@Override
	public void editHasInvoiceByInvoiceId(Integer invoiceId) throws Exception {
		if (invoiceId != null) {
			PaymentInvoiceQueryVo vo = new PaymentInvoiceQueryVo();
			vo.setInvoiceId(invoiceId);
			List<PaymentInvoiceEntity> list = this.paymentInvoiceService.getPaymentInvoiceByVo(vo);
			for (PaymentInvoiceEntity paymentInvoiceEntity : list) {
				if (paymentInvoiceEntity.getPaymentId() != null) { //非提前开票才需要修改payment字段
					this.paymentMapper.editHasInvoiceById(paymentInvoiceEntity.getPaymentId());
				}
			}
		}
	}

	@Override
	public List<PaymentEntity> getRepeatPayment(List<PaymentEntity> paymentEntityList)
			throws Exception {
		List<PaymentEntity> payList = new ArrayList<PaymentEntity>();
		PaymentEntity payment = new PaymentEntity();
		for(int i = 0; i < paymentEntityList.size(); i++){
			PaymentEntity paymentEntity = paymentEntityList.get(i);
			payment = paymentEntity;
			PaymentQueryVo vo=new PaymentQueryVo();
			vo.setSerialNumber(payment.getSerialNumber());
			List<PaymentEntity> payListByVo = this.getPaymentByVo(vo);
			if(payListByVo.size()<1){
				payment.setIsNew("Y");
				for(int j = 0; j < i; j++){
					if(payment.getSerialNumber().equals(paymentEntityList.get(j).getSerialNumber())){
						payment.setIsNew("N");
						break;
					}
				}
			}else{
				payment.setIsNew("N");
			}
			payList.add(payment);
			
		}
		return payList;
	}
	
}
