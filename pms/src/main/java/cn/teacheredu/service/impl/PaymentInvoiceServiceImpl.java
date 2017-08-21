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

import cn.teacheredu.entity.PaymentInvoiceEntity;
import cn.teacheredu.entity.ProcessEntity;
import cn.teacheredu.form.QueryTermsForm;
import cn.teacheredu.mapping.PaymentInvoiceMapper;
import cn.teacheredu.mapping.ProcessMapper;
import cn.teacheredu.service.PaymentInvoiceService;
import cn.teacheredu.utils.MyMathUtil;
import cn.teacheredu.utils.PageUtils;
import cn.teacheredu.utils.SystemConst;
import cn.teacheredu.vo.PaymentInvoiceQueryVo;
import cn.teacheredu.vo.ProcessEntityQueryVo;

@Service
public class PaymentInvoiceServiceImpl implements PaymentInvoiceService {

	@Autowired
	private PaymentInvoiceMapper paymentInvoiceMapper;
	@Autowired
	private ProcessMapper processMapper;
	
	@Override
	public PaymentInvoiceEntity getPaymentInvoiceById(Integer piId) throws Exception {
		return this.paymentInvoiceMapper.selectByPrimaryKey(piId);
	}

	@Override
	public List<PaymentInvoiceEntity> getPaymentInvoiceByVo(PaymentInvoiceQueryVo vo) throws Exception {
		return this.paymentInvoiceMapper.selectByExample(vo);
	}

	@Override
	public int insertPaymentInvoiceEntity(PaymentInvoiceEntity piEntity) throws Exception {
		this.paymentInvoiceMapper.insertSelective(piEntity);
		return piEntity.getId();
	}

	@Override
	public long getCountByVo(PaymentInvoiceQueryVo vo) throws Exception {
		return this.paymentInvoiceMapper.countByExample(vo);
	}

	@Override
	public int updatePaymentInvoice(PaymentInvoiceEntity piEntity) throws Exception {
		return this.paymentInvoiceMapper.updateByPrimaryKeySelective(piEntity);
	}

	@Override
	public int detelePaymentInvoiceById(Integer piId) throws Exception {
		return this.paymentInvoiceMapper.deleteByPrimaryKey(piId);
	}

	@Override
	public PageUtils<PaymentInvoiceEntity> getPaymentInvoiceList(QueryTermsForm form) throws Exception {
		PageUtils<PaymentInvoiceEntity>  pageUtil= null;
		if (form.getUserId() == null){
			pageUtil = new PageUtils<PaymentInvoiceEntity>(new ArrayList<PaymentInvoiceEntity>(), 0, form.getPageSize(), form.getCurrPage());
		}else{
			PaymentInvoiceQueryVo vo=new PaymentInvoiceQueryVo();
			vo.setOrderProperty("id");
			vo.setOrderType(SystemConst.SQL_ORDERTYPE_DESC);
			vo.setPaged(true);
			vo.setPageSize(form.getPageSize());
			vo.setCurPage(form.getCurrPage());
			vo.setStartPosition((form.getCurrPage() - 1) * form.getPageSize());
			vo.setProjectId(form.getProjectId());
			vo.setAvailable(0);
			if (form.getType() == 1 ) { //通过开票日期查询
				vo.setFromDate(form.getStartDate());
				vo.setToDate(form.getEndDate());
				vo.setInvoiceDate(new Date());//这时间没有什么用，就是为了区别查询条件是哪个
			} else if (form.getType() == 2 && StringUtils.isNotBlank(form.getValue())) { //通过回款状态查询
				vo.setPaymentState(Integer.parseInt(form.getValue()));
			} else if(form.getType() == 3 && StringUtils.isNotBlank(form.getValue())){//发票抬头
				vo.setInvoiceHeader(form.getValue().trim());  
			}
			List<PaymentInvoiceEntity> list=this.getPaymentInvoiceByVo(vo);
			
			//计算发票总金额和查询发票申请对应的流程
			ProcessEntityQueryVo vo2 = new ProcessEntityQueryVo();
			vo2.setProjectId(form.getProjectId());
			vo2.setTableName(SystemConst.TABLE_INVOICE);
			for (PaymentInvoiceEntity pi : list) {
				BigDecimal total = MyMathUtil.addBigDecimal(pi.getInvoiceMachine(),pi.getInvoiceHundred(),pi.getInvoiceFifty());
				pi.setInvoiceMachine(total);
				vo2.setObjectId(pi.getInvoiceId());
				List<ProcessEntity> process = this.processMapper.selectByExample(vo2);
				if (process.size() > 0) {
					Map<String, Object> extInfo = new HashMap<String, Object>();
					extInfo.put("process", process.get(0));
					pi.setExtInfo(extInfo);
				}
			}
			
			long total = this.getCountByVo(vo);
			pageUtil = new PageUtils<PaymentInvoiceEntity>(list, total, form.getPageSize(), form.getCurrPage());
		}
		return pageUtil;
	}

	@Override
	public BigDecimal getTotalInvoiceByProjectId(Integer projectId) throws Exception {
		BigDecimal total = BigDecimal.ZERO;
		if (projectId != null) {
			PaymentInvoiceQueryVo vo = new PaymentInvoiceQueryVo();
			vo.setProjectId(projectId);
			vo.setResult((byte)1);
			List<PaymentInvoiceEntity> list = this.getPaymentInvoiceByVo(vo);
			for (PaymentInvoiceEntity paymentInvoice : list) {
				BigDecimal b = MyMathUtil.addBigDecimal(paymentInvoice.getInvoiceMachine(),paymentInvoice.getInvoiceHundred(),paymentInvoice.getInvoiceFifty());
				total = total.add(b);
			}
		}
		return total;
	}

	@Override
	public boolean editResultByInvoiceId(Integer invoiceId) throws Exception {
		if (invoiceId == null) {
			return false;
		}
		int i = this.paymentInvoiceMapper.editResultByInvoiceId(invoiceId);
		if (i > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean editAvailableByInvoiceId(Integer invoiceId) throws Exception {
		if (invoiceId == null) {
			return false;
		}
		int i = this.paymentInvoiceMapper.editAvailableByInvoiceId(invoiceId);
		if (i > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Integer> selectInvoiceByProjectId(Integer projectId) throws Exception {
		if (projectId == null) {
			return new ArrayList<Integer>();
		}
		return this.paymentInvoiceMapper.selectInvoiceByProjectId(projectId);
	}

}
