package cn.teacheredu.controller.payment;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.teacheredu.controller.Base3Controller;
import cn.teacheredu.entity.PaymentEntity;
import cn.teacheredu.entity.ProcessStepEntity;
import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.form.PaymentForm;
import cn.teacheredu.service.PaymentInvoiceService;
import cn.teacheredu.service.PaymentService;
import cn.teacheredu.service.ProcessStepService;
import cn.teacheredu.utils.CommonUtils;
import cn.teacheredu.utils.FilePathUtil;
import cn.teacheredu.utils.ImportExcelUtil;
import cn.teacheredu.utils.R;
import cn.teacheredu.utils.SystemConst;
import cn.teacheredu.vo.PaymentQueryVo;

import com.alibaba.druid.support.json.JSONUtils;

@Controller
@RequestMapping(value = "/payment")
public class PaymentController extends Base3Controller{
	private static Logger logger = LoggerFactory.getLogger(PaymentController.class);
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private PaymentInvoiceService paymentInvoiceService;
	@Autowired
	private ProcessStepService processStepService;
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addPaymentPage(ModelMap modelMap, HttpServletRequest request) throws Exception{
		UserEntity user = (UserEntity) modelMap.get("user");
		@SuppressWarnings("unchecked")
		List<String> psList =  (List<String>) modelMap.get("psList");
		if (!CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_CREATE_PAYMENT, psList)) {
			modelMap.put("tipMsg", "抱歉...你没有上传到款信息的权限");
			return "tip";
		}
		logger.info("=====/payment/addPage=====" + user.getLoginName());
		Date now = new Date();
		modelMap.put("title", "到款分配表-"+user.getRealname()+"-"+DateFormatUtils.format(now, "yyyy.MM.dd HH.mm"));
		
		
		return "payment/addPayment";
		
	}
	
	/**
	 * 已到款开发票，查询到款信息
	 */
	@RequestMapping(value="/noInvoicePayment",method=RequestMethod.GET)
	public String paymentTablePage(Integer projectId, ModelMap modelMap, HttpServletRequest request) throws Exception{
		UserEntity user = (UserEntity) modelMap.get("user");
		logger.info("=====/payment/select-noInvoicePayment=====" + user.getLoginName());
		if (projectId != null) {
			// 先查询本项目所有的到款信息（非提前开发票汇款的，因为提前开发票的到款信息已开过发票了）
			PaymentQueryVo vo = new PaymentQueryVo();
			vo.setProjectId(projectId);
			vo.setAdvancePay((byte)2);
			List<PaymentEntity> paymentList = this.paymentService.getPaymentByVo(vo);
			
			// 去除已经申请了发票的到款信息,和正在申请发票的到款信息（也就是payment—invoice表存在的到款信息，不管流程有没有结束）
			// 如果发票申请流程正常结束了证明这些到款信息已经开过发票
			// 如果发票申请流程被退回了，需要删除payment—invoice表关于这个流程的所有申请发票信息。 这是个特殊情况。不删除的话，业务
			// 人员就无法再选择这些到款记录来继续申请了。因为系统现在没有做保存待发、重新编辑发送的功能，有流程被退回了是需要发起人重新填表申请的。
			// 上面说的删除payment-invoice的数据，是在ProcessDealController的ajaxDealProcess方法115行开始。因为被退回的表单也要有查询的，
			// 所以这里说的删除其实是假删（available = 1）
			
			// 那么下面查询需要从paymentList中移除的记录的时候，需要传available = 0，查出那些确实已开了发票的到款信息和正在申请发票的到款信息
			List<Integer> paymentIdList = this.paymentInvoiceService.selectInvoiceByProjectId(projectId);
			Iterator<PaymentEntity> iterator = paymentList.iterator();
			while (iterator.hasNext()) {
				PaymentEntity paymentEntity = (PaymentEntity) iterator.next();
				if (paymentIdList.contains(paymentEntity.getId())) {
					iterator.remove();
				}
			} 
			modelMap.put("paymentList", paymentList);
		}
		
		return "payment/paymentTable";
		
	}
	
	/**
	 * 导入到款表信息
	 * @param args
	 * @param modelMap
	 * @param request
	 */
	@RequestMapping(value="/addPaymentDataList",method=RequestMethod.GET)
	public String addPaymentDataListPage(String args, ModelMap modelMap, HttpServletRequest request) throws Exception{
		if (StringUtils.isNotBlank(args)) {
			System.out.println("[PaymentController]addPaymentDataList:"+args);
			File resultFile = new File(FilePathUtil.getRealFilePath(args));
			if (!resultFile.exists()) {
				modelMap.put("error", "未能获取到导入文件");
			} else {
				InputStream in = new FileInputStream(resultFile);
				List<List<String>> list = null;
				try {
					list = new ImportExcelUtil().getBankListByExcel(in, args.substring(args.lastIndexOf(".")));
				} catch (Exception e) {
					modelMap.put("error", "解析Excel内容时出错了："+e.getMessage());
					return "payment/addPaymentDataList";
				} finally {
					if (in != null) {
						in.close();
					}
				}
				if (list != null) {
					if (list.size() > 52) {
						modelMap.put("error", "请每次导入少于50条的数据！");
					} else if (list.size() > 2) {
						// 验证表头
						List<String> header = list.get(0);
						if(!CommonUtils.listToString(header).equals("序号,年度,月份,流水号,交易日期,汇款人名称,汇款人开户行名称或账号,到账银行,转账形式,汇款留言（备注）,金额")){
							modelMap.put("error", "表头不正确，请使用正确的模板来导入！");
						} else {
							String[] pattern = new String[]{"yyyy-MM-dd HH:mm:ss","yyyy/MM/dd HH:mm:ss","yyyy-MM-dd","yyyy/MM/dd"};
							List<PaymentEntity>  paymentList = new ArrayList<PaymentEntity>();
							for (int i = 2; i < list.size(); i++) {
								List<String> data = list.get(i);
								//不用判断data的size，不会数组越界，错误的行在解析的时候已经过滤掉了
								try {
									Integer num = Integer.valueOf(data.get(0));
									Integer year = Integer.valueOf(data.get(1));
									Byte month = Byte.valueOf(data.get(2));
									String serialNumber = data.get(3); 
									Date traTime = DateUtils.parseDate(data.get(4), pattern);
									String remitter = data.get(5);
									String remitterAccount = data.get(6);
									String payBank = data.get(7);
									String transferForm = data.get(8);
									String note = data.get(9);
									BigDecimal amount = new BigDecimal(data.get(10));
									
									PaymentEntity paymentEntity = new PaymentEntity();
									paymentEntity.setNum(num);
									paymentEntity.setYear(year);
									paymentEntity.setMonth(month);
									paymentEntity.setSerialNumber(serialNumber);
									paymentEntity.setTraTime(traTime);
									paymentEntity.setRemitter(remitter);
									paymentEntity.setRemitterAccount(remitterAccount);
									paymentEntity.setPayBank(payBank);
									paymentEntity.setTransferForm(transferForm);
									paymentEntity.setAmount(amount);
									paymentEntity.setNote(note);
									paymentList.add(paymentEntity);
								} catch (Exception e) {
									modelMap.put("error", "第"+(i+1)+"行数据有问题，转换的时候出现异常："+e.getMessage());
									return "payment/addPaymentDataList";
								}
								
							}
							List<PaymentEntity> paymentList1 = paymentService
									.getRepeatPayment(paymentList);
							modelMap.put("paymentList", paymentList1);
						}
					} else {
						modelMap.put("error", "导入的文件系统解析结果是空文件，请联系管理员");
					}
				}
			}
		}
		
		return "payment/addPaymentDataList";
		
	}

	/**
	 * 刷新到款导入表数据格式
	 */
	@RequestMapping(value = "/displyPaymentDataLits", method = RequestMethod.POST)
	public String displyPaymentDataLits(String params, ModelMap modelMap,
			HttpServletRequest request) throws Exception {
		params = params.substring(params.indexOf("["), params.indexOf("]") + 1);
		Object obj = JSONUtils.parse(params);
		StringBuffer strObj = new StringBuffer(obj.toString());
		List<PaymentEntity> paymentLists = new ArrayList<PaymentEntity>();
		PaymentEntity payment = new PaymentEntity();

		int i = 0; // 遍歷當前字符串（也是截取字符串的尾字符下標值）
		int j = 0; // 截取字符串的首字符下標值
		StringBuffer colName = new StringBuffer();
		StringBuffer value = new StringBuffer();

		boolean flag = true;
		while (!(']' == strObj.charAt(i))) {
			switch (strObj.charAt(i)) {
			case '[':
				j++;
				break;
			case '{':
				j++;
				payment = new PaymentEntity();
				break;// 創建一個對象
			case '}':
				value = new StringBuffer(strObj.substring(j, i));
				PaymentAddUtil(payment, colName + "", value + "");
				flag = false;
				j = i + 1;
				paymentLists.add(payment);
				break;// list中添加當前對象
			case '=':
				colName = new StringBuffer(strObj.substring(j, i));
				j = i + 1;
				break;
			case ',':
				if (flag) {
					if ("traTime".equals(colName + "")) {
						value = new StringBuffer(strObj.substring(j - 1, i));
						PaymentAddUtil(payment, colName + "", value + "");
					} else {
						value = new StringBuffer(strObj.substring(j, i));
						PaymentAddUtil(payment, colName + "", value + "");
					}
				}
				flag = true;
				j = i + 1;
				break;
			case ' ':
				j++;
				break;
			}
			i++;
		}

		List<PaymentEntity> paymentList = new ArrayList<PaymentEntity>();
		paymentList = paymentService.getRepeatPayment(paymentLists);
		modelMap.put("paymentList", paymentList);

		return "payment/addPaymentDataList";
	}

	public PaymentEntity PaymentAddUtil(PaymentEntity payment, String colName,
			String str) {
		if ("".equals(str) || "".equals(colName)) {
			return payment;
		} else if ("num".equals(colName)) {
			payment.setNum(Integer.valueOf(str));
		} else if ("year".equals(colName)) {
			payment.setYear(Integer.valueOf(str));
		} else if ("month".equals(colName)) {
			payment.setMonth(Byte.valueOf(str));
		} else if ("serialNumber".equals(colName)) {
			payment.setSerialNumber(str);
		} else if ("traTime".equals(colName)) {
			try {
				String[] pattern = new String[] { "yyyy-MM-dd HH:mm:ss",
						"yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd", "yyyy/MM/dd" };
				payment.setTraTime(DateUtils.parseDate(str, pattern));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else if ("remitter".equals(colName)) {
			payment.setRemitter(str);
		} else if ("remitterAccount".equals(colName)) {
			payment.setRemitterAccount(str);
		} else if ("payBank".equals(colName)) {
			payment.setPayBank(str);
		} else if ("transferForm".equals(colName)) {
			payment.setTransferForm(str);
		} else if ("amount".equals(colName)) {
			payment.setAmount(new BigDecimal(str));
		} else if ("note".equals(colName)) {
			payment.setNote(str);
		}
		return payment;
	}

	@RequestMapping(value = "/ajaxAddPayment", method = RequestMethod.POST)
	public @ResponseBody R ajaxAddPayment(@RequestBody PaymentForm paymentForm, ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserEntity user = (UserEntity) model.get("user");
		if (paymentForm.getPaymentList() == null || paymentForm.getPaymentList().size() == 0) {
			return R.error("没有检测到提交的到款信息");
		}
		//流程表添加一条数据，先算出当前待办人
		if (StringUtils.isBlank(paymentForm.getProcessName())) {
			return R.error("未获取到流程标题参数");
		}
		
		ProcessStepEntity stepEntity = this.processStepService.getProcessStepByDefineInfo(SystemConst.TABLE_PAYMENT, null, "1");
		if(stepEntity == null || StringUtils.isBlank(String.valueOf(stepEntity.getDmId())) || StringUtils.isBlank(String.valueOf(stepEntity.getUserId()))){
			return R.error("未获取到您的流程步骤配置信息，请联系管理员");
		}

		List<PaymentEntity> paymentList1 = paymentService.getRepeatPayment(paymentForm.getPaymentList());
		for (PaymentEntity p : paymentList1) {
			if ("N".equals(p.getIsNew())) {
				return R.error("到款记录存在重复的流水账号，请检查数据");
			}
		}
		paymentForm.setUserId(user.getId());
		paymentForm.setUserName(user.getRealname());
		paymentForm.setCurrStepUserId(stepEntity.getUserId());
		paymentForm.setType(stepEntity.getType() == null ? 1 : stepEntity.getType()); //审批流程类型
		paymentForm.setStepId(stepEntity.getId());
		
		this.paymentService.savePaymentAndProcess(paymentForm);
		
		return R.ok();
	}
	
}
