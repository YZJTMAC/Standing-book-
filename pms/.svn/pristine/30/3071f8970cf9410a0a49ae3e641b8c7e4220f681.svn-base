package cn.teacheredu.controller.team;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.teacheredu.controller.Base3Controller;
import cn.teacheredu.entity.AttachmentEntity;
import cn.teacheredu.entity.CompanyEntity;
import cn.teacheredu.entity.DepartmentEntity;
import cn.teacheredu.entity.FundsEntity;
import cn.teacheredu.entity.InvoiceEntity;
import cn.teacheredu.entity.NeedDealProcessEntity;
import cn.teacheredu.entity.OrganizationEntity;
import cn.teacheredu.entity.PaymentEntity;
import cn.teacheredu.entity.PaymentInvoiceEntity;
import cn.teacheredu.entity.ProcessEntity;
import cn.teacheredu.entity.ProcessHistoryEntity;
import cn.teacheredu.entity.ProjectBudgetEntity;
import cn.teacheredu.entity.ProjectChangeEntity;
import cn.teacheredu.entity.ProjectEndEntity;
import cn.teacheredu.entity.ProjectEntity;
import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.service.AttachmentService;
import cn.teacheredu.service.CompanyService;
import cn.teacheredu.service.FundsService;
import cn.teacheredu.service.InvoiceService;
import cn.teacheredu.service.NeedDealProcessService;
import cn.teacheredu.service.OrganizationService;
import cn.teacheredu.service.PaymentInvoiceService;
import cn.teacheredu.service.PaymentService;
import cn.teacheredu.service.ProcessHistoryService;
import cn.teacheredu.service.ProcessService;
import cn.teacheredu.service.ProjectBudgetService;
import cn.teacheredu.service.ProjectChangeService;
import cn.teacheredu.service.ProjectEndService;
import cn.teacheredu.service.ProjectService;
import cn.teacheredu.utils.MyMathUtil;
import cn.teacheredu.utils.SystemConst;
import cn.teacheredu.vo.CompanyQueryVo;
import cn.teacheredu.vo.PaymentInvoiceQueryVo;
import cn.teacheredu.vo.ProjectChangeQueryVo;

@Controller
@RequestMapping(value = "/processDetail")
public class ProcessDetailController extends Base3Controller{
	@Autowired
	private ProcessService processService;
	@Autowired
	private ProcessHistoryService processHistoryService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private ProjectBudgetService projectBudgetService;
	@Autowired
	private FundsService fundsService;
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private PaymentInvoiceService paymentInvoiceService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private ProjectChangeService projectChangeService;
	@Autowired
	private NeedDealProcessService needDealProcessService;
	@Autowired
	private ProjectEndService projectEndService;
	@Autowired
	private CompanyService companyService;

	
	/**
	 * 流程表单展示、审批、查看页面
	 * type： 1 待办  2 已办 3 已发 4 待发
	 */
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public String viewNeedDealPage(Integer id, Integer needId,Integer type, ModelMap modelMap, HttpServletRequest request) throws Exception{
		UserEntity user = (UserEntity) modelMap.get("user");
		if (id == null || type == null) {
			modelMap.put("tipMsg", "缺少参数，进入页面失败。");
			return "tip";
		}
		modelMap.put("type", type);
		//查询该流程的信息
		ProcessEntity process = this.processService.getProcessById(id);
		if (process == null) {
			modelMap.put("tipMsg", "未查到流程相关信息，请联系管理员。");
			return "tip";
		}
		modelMap.put("process", process);
		//待办页面需多加点验证
		if (type == 1) { 
			NeedDealProcessEntity needProcess = this.needDealProcessService.getNeedDealProcessById(needId);
			if (needProcess == null || !needProcess.getProcessId().equals(id)) {
				modelMap.put("tipMsg", "未查到流程待办相关信息，请联系管理员。");
				return "tip";
			}
			modelMap.put("needProcess", needProcess);
			if (!process.getCurrStepUserId().equals(user.getId()) && needProcess.getType() != 2) {
				modelMap.put("tipMsg", "抱歉...您没有权限审批此流程.");
				return "tip";
			}
		}
		// 查询流程的审批记录及对应流程步骤
		List<ProcessHistoryEntity> phList = this.processHistoryService.getProcessHistoryByProcessId(id);
		ProcessHistoryEntity ph = null;
		for (Iterator<ProcessHistoryEntity> it = phList.iterator(); it.hasNext();) {
			ph = it.next();
			if (ph.getStepNote() != null) {
				modelMap.put("ph_"+ ph.getStepNote().toString(), ph);
			}
			if (ph.getType() != 1) {//只查审批
				it.remove();
			}
		}
		modelMap.put("phList", phList);
		
		// 流程的附件
		List<AttachmentEntity> atList = this.attachmentService.getAttachmentByProcessId(id);
		modelMap.put("atList", atList);
		
		// 下面开始查询流程对应的表单信息
		/**
		 * 项目确认表
		 */
		if (SystemConst.TABLE_PROJECT.equals(process.getTableName())) {
			ProjectEntity project = this.projectService.getProjectById(process.getObjectId());
			modelMap.put("project", project);
			String provincial = this.organizationService.getNameById(project.getProvincial());
			modelMap.put("provincial", provincial);
			String city = this.organizationService.getNameById(project.getCity());
			modelMap.put("city", city);
			String county = this.organizationService.getNameById(project.getCounty());
			modelMap.put("county", county);
			int len = provincial.length() + city.length() + county.length();
			if("市辖区".equals(city))len -= 3;
			else if("市辖区".equals(county))len -= 3;
			String name = project.getName().substring(len, project.getName().length() - 2);
			modelMap.put("name", name);
			ProjectBudgetEntity pb = this.projectBudgetService.getProjectBudgetByProjectId(project.getId());
			modelMap.put("pb", pb);
			//预算表计算
			//合作经费
			BigDecimal qb = MyMathUtil.div(MyMathUtil.addStringToBigDecimal(project.getProFundCity(),project.getProFundCounty(),project.getProFundOther(),project.getProFundProvincial()), new BigDecimal("100.00"), 4); 
			BigDecimal hb = MyMathUtil.div(MyMathUtil.addStringToBigDecimal(project.getLaterFundCity(),project.getLaterFundCounty(),project.getLaterFundOther(),project.getLaterFundProvincial()), new BigDecimal("100.00"), 4); 
			BigDecimal qj = MyMathUtil.mul(project.getTotalMoney(),qb);
			BigDecimal hj = MyMathUtil.mul(project.getTotalMoney(),hb);
			modelMap.put("hj", hj);
			modelMap.put("qj", qj);
			//项目收入
			BigDecimal xmsr = MyMathUtil.sub(project.getTotalMoney(), qj);
			modelMap.put("xmsr", xmsr);
			//会议成本
			BigDecimal hycb = MyMathUtil.addBigDecimal(pb.getExpertCostBudget(),pb.getTransportCostBudget(),pb.getAccomCostBudget(),pb.getFeteCostBudget(),pb.getOfficeCostBudget(),pb.getRentalCostBudget(),pb.getInvestCostBudget(),pb.getOtherCostBudget());
			modelMap.put("hycb", hycb);
			//劳务费用
			BigDecimal lwfy = MyMathUtil.addBigDecimal(pb.getExpertLabourBudget(),pb.getCounsellorLabourBudget());
			modelMap.put("lwfy", lwfy);
			//项目成本
			BigDecimal xmcb = MyMathUtil.addBigDecimal(hj,hycb,lwfy);
			modelMap.put("xmcb", xmcb);
			//毛利
			BigDecimal ml = MyMathUtil.sub(xmsr, xmcb);
			modelMap.put("ml", ml);
			//毛利率
			if(project.getTotalMoney().compareTo(new BigDecimal("0"))!=0){
				BigDecimal mll = MyMathUtil.mul(MyMathUtil.div(ml, xmsr, 4), new BigDecimal("100"));
				modelMap.put("mll", mll+"%");
			}else{
				modelMap.put("mll", "0.0000");
			}
			
			return "project/projectAudit";
		}
		
		/**
		 * 发票申请表
		 */
		if (SystemConst.TABLE_INVOICE.equals(process.getTableName())) {
			
			InvoiceEntity invoice=this.invoiceService.getInvoiceById(process.getObjectId());
			modelMap.put("invoice", invoice);
			ProjectEntity project = this.projectService.getProjectById(invoice.getProjectId());
			modelMap.put("projectEntity", project);
			PaymentInvoiceQueryVo vo=new PaymentInvoiceQueryVo();
			vo.setInvoiceId(process.getObjectId());
			List<PaymentInvoiceEntity> list= this.paymentInvoiceService.getPaymentInvoiceByVo(vo);
			BigDecimal invoiceMachineTotal = BigDecimal.ZERO;
			BigDecimal invoiceHundredTotal = BigDecimal.ZERO;
			BigDecimal invoiceFiftyTotal = BigDecimal.ZERO;
			for (int i = 0; i < list.size(); i++) {
				PaymentInvoiceEntity payInvoce=list.get(i);
				if(payInvoce.getPaymentId()!=null){
					PaymentEntity payment=this.paymentService.getPaymentById(payInvoce.getPaymentId());
					payInvoce.setPayment(payment);
				}
				invoiceMachineTotal = MyMathUtil.add(invoiceMachineTotal, payInvoce.getInvoiceMachine());
				invoiceHundredTotal = MyMathUtil.add(invoiceHundredTotal, payInvoce.getInvoiceHundred());
				invoiceFiftyTotal = MyMathUtil.add(invoiceFiftyTotal, payInvoce.getInvoiceFifty());
			}
			modelMap.put("invoiceMachineTotal", invoiceMachineTotal);
			modelMap.put("invoiceHundredTotal", invoiceHundredTotal);
			modelMap.put("invoiceFiftyTotal", invoiceFiftyTotal);
			modelMap.put("paymentInvoiceList", list);
			return "invoice/applyForInvoiceAudit";
			
		}
		
		/**
		 * 经费申请表
		 */
		if (SystemConst.TABLE_FUNDS.equals(process.getTableName())) {
			FundsEntity funds = this.fundsService.getFundsById(process.getObjectId());
			modelMap.put("funds", funds);
			ProjectEntity project = this.projectService.getProjectById(funds.getProjectId());
			modelMap.put("project", project);
			// 合作经费
			BigDecimal q_provinceb = MyMathUtil.div(new BigDecimal(project.getProFundProvincial()), new BigDecimal("100.00"), 4); //前期省级经费比例
			BigDecimal q_ctiyb = MyMathUtil.div(new BigDecimal(project.getProFundCity()), new BigDecimal("100.00"), 4);
			BigDecimal q_countyb = MyMathUtil.div(new BigDecimal(project.getProFundCounty()), new BigDecimal("100.00"), 4);
			BigDecimal q_otherb = MyMathUtil.div(new BigDecimal(project.getProFundOther()), new BigDecimal("100.00"), 4);
			
			BigDecimal h_provinceb = MyMathUtil.div(new BigDecimal(project.getLaterFundProvincial()), new BigDecimal("100.00"), 4); ////后期省级经费比例
			BigDecimal h_ctiyb = MyMathUtil.div(new BigDecimal(project.getLaterFundCity()), new BigDecimal("100.00"), 4);
			BigDecimal h_countyb = MyMathUtil.div(new BigDecimal(project.getLaterFundCounty()), new BigDecimal("100.00"), 4);
			BigDecimal h_otherb = MyMathUtil.div(new BigDecimal(project.getLaterFundOther()), new BigDecimal("100.00"), 4);
			
			modelMap.put("q_province", MyMathUtil.mul(project.getTotalMoney(), q_provinceb));
			modelMap.put("q_city", MyMathUtil.mul(project.getTotalMoney(), q_ctiyb));
			modelMap.put("q_county", MyMathUtil.mul(project.getTotalMoney(), q_countyb));
			modelMap.put("q_other", MyMathUtil.mul(project.getTotalMoney(), q_otherb));
			
			modelMap.put("h_province", MyMathUtil.mul(project.getTotalMoney(), h_provinceb));
			modelMap.put("h_city", MyMathUtil.mul(project.getTotalMoney(), h_ctiyb));
			modelMap.put("h_county", MyMathUtil.mul(project.getTotalMoney(), h_countyb));
			modelMap.put("h_other", MyMathUtil.mul(project.getTotalMoney(), h_otherb));
			
			BigDecimal qb = MyMathUtil.div(MyMathUtil.addStringToBigDecimal(project.getProFundCity(),project.getProFundCounty(),project.getProFundOther(),project.getProFundProvincial()), new BigDecimal("100.00"), 4); 
			BigDecimal hb = MyMathUtil.div(MyMathUtil.addStringToBigDecimal(project.getLaterFundCity(),project.getLaterFundCounty(),project.getLaterFundOther(),project.getLaterFundProvincial()), new BigDecimal("100.00"), 4); 
			BigDecimal qj = MyMathUtil.mul(project.getTotalMoney(),qb);
			BigDecimal hj = MyMathUtil.mul(project.getTotalMoney(),hb);
			// 后期合作经费总额
			modelMap.put("hj", hj);
			// 实收金额
			BigDecimal ssje = MyMathUtil.sub(project.getTotalMoney(), qj);
			modelMap.put("ssje", ssje);
			// 已支付经费金额
			BigDecimal yzf = this.fundsService.getTotalWonPayByProject(funds.getProjectId());
			modelMap.put("yzf", yzf);
			// 未支付经费金额
			BigDecimal wzf = MyMathUtil.sub(hj, yzf);
			wzf = MyMathUtil.sub(wzf, funds.getApplyAmount());
			modelMap.put("wzf", wzf);
			
			// 实际到款金额
			BigDecimal ydk = this.paymentService.getRealPayTotalByProjectId(funds.getProjectId());
			modelMap.put("ydk", ydk);
			
			// 经费金额=到款金额*后期经费比例
			modelMap.put("fj", MyMathUtil.mul(ydk, hb));
			
			return "funds/fundsAudit";
		}
		
		/**
		 * 信息变更表
		 */
		if (SystemConst.TABLE_PROJECTCHANGE.equals(process.getTableName())) {
			ProjectEntity project = this.projectService.getProjectById(process.getProjectId());
			modelMap.put("project", project);
			ProjectChangeQueryVo vo = new ProjectChangeQueryVo();
			vo.setProcessId(id);
			List<ProjectChangeEntity> pcList = this.projectChangeService.getProjectChangeByVo(vo);
			for (int i = 0; i < pcList.size(); i++) {
				ProjectChangeEntity projectChange = pcList.get(i);
				String columnValue = projectChange.getColumnValue();
				String reason = projectChange.getReason();
				String columnName = projectChange.getColumnName();
				modelMap.put("note", projectChange.getNote());
				if ("expected_num".equals(columnName)) {
					modelMap.put("en_columnValue", columnValue);
					modelMap.put("en_reason", reason);
				}
				if ("charge_standard".equals(columnName)) {
					modelMap.put("cs_columnValue", columnValue);
					modelMap.put("cs_reason", reason);
				}
				if ("pro_fund_provincial".equals(columnName)) {
					modelMap.put("pfp_columnValue", columnValue);
					modelMap.put("pfp_reason", reason);
				}
				if ("pro_fund_city".equals(columnName)) {
					modelMap.put("pfci_columnValue", columnValue);
					modelMap.put("pfci_reason", reason);
				}
				if ("pro_fund_county".equals(columnName)) {
					modelMap.put("pfco_columnValue", columnValue);
					modelMap.put("pfco_reason", reason);
				}
				if ("pro_fund_other".equals(columnName)) {
					modelMap.put("pfo_columnValue", columnValue);
					modelMap.put("pfo_reason", reason);
				}
				if ("later_fund_provincial".equals(columnName)) {
					modelMap.put("lfp_columnValue", columnValue);
					modelMap.put("lfp_reason", reason);
				}
				if ("later_fund_city".equals(columnName)) {
					modelMap.put("lfci_columnValue", columnValue);
					modelMap.put("lfci_reason", reason);
				}
				if ("later_fund_county".equals(columnName)) {
					modelMap.put("lfco_columnValue", columnValue);
					modelMap.put("lfco_reason", reason);
				}
				if ("later_fund_other".equals(columnName)) {
					modelMap.put("lfo_columnValue", columnValue);
					modelMap.put("lfo_reason", reason);
				}
				if ("start_date".equals(columnName)) {
					modelMap.put("sd_columnValue", columnValue.substring(0, 10));
					modelMap.put("sd_reason", reason);
				}
				if ("end_date".equals(columnName)) {
					modelMap.put("ed_columnValue", columnValue.substring(0, 10));
					modelMap.put("ed_reason", reason);
				}
				if ("collect_money_company".equals(columnName)) {
					modelMap.put("cmc_columnValue", columnValue);
					modelMap.put("cmc_reason", reason);
				}
				
			}
			return "project/changeProjectAudit";
		}
		
		/**
		 * 到款信息表
		 */
		if (SystemConst.TABLE_PAYMENT.equals(process.getTableName())) {
			
			PaymentEntity payment = this.paymentService.getPaymentById(process.getObjectId());
			modelMap.put("payment", payment);
			String provincial = this.organizationService.getNameById(payment.getProvince());
			modelMap.put("provincial", provincial);
			String city = this.organizationService.getNameById(payment.getCity());
			modelMap.put("city", city);
			String county = this.organizationService.getNameById(payment.getCounty());
			modelMap.put("county", county);
			ProjectEntity project = this.projectService.getProjectById(payment.getProjectId());
			modelMap.put("projectName", project == null ? "" : project.getName());
			modelMap.put("projectCompany", project == null ? "" : project.getCollectMoneyCompany());
			if(payment.getAdvancePay() != null && payment.getAdvancePay() == 1){
				Integer paymentId = payment.getId();
				PaymentInvoiceQueryVo vo = new PaymentInvoiceQueryVo();
				vo.setPaymentId(paymentId);
				List<PaymentInvoiceEntity> paymentInvoice = paymentInvoiceService.getPaymentInvoiceByVo(vo);
				modelMap.put("paymentInvoiceList", paymentInvoice);
				String idStr = "" ;
				String headerStr = "";
				for(PaymentInvoiceEntity pi: paymentInvoice){
					idStr += pi.getInvoiceId();
					headerStr += pi.getInvoiceHeader();
				}
				modelMap.put("idStr", idStr);
				modelMap.put("headerStr", headerStr);
			}
			if (type == 1) { //待办页面
				@SuppressWarnings("unchecked")
				List<DepartmentEntity> dmList = (List<DepartmentEntity>) modelMap.get("dmList");
				if (dmList.size() > 0) {
					DepartmentEntity departmentEntity = dmList.get(0);
					if ("2".equals(departmentEntity.getDmType().trim())) { //商务部的
						modelMap.put("isShangwu", true);
						//查询所有省份
						List<OrganizationEntity> provinceList = this.organizationService.getListByParentId(0);
						modelMap.put("provinceList", provinceList);
					} else { //省份负责人
						modelMap.put("isShangwu", false);
						//查询本省份所有的项目
						List<ProjectEntity> projectList = this.projectService.getProjectByProvince(payment.getProvince());
						modelMap.put("projectList", projectList);
						//查询所有公司
						List<CompanyEntity> companyList = this.companyService.getCompanyByVo(new CompanyQueryVo());
						modelMap.put("companyList", companyList);
					}
				}
				
			}
			return "payment/paymentAudit";
		}
		

		/**
		 * 项目完结表
		 */
		if (SystemConst.TABLE_PROJECTEND.equals(process.getTableName())) {
			List<ProjectEndEntity> projectEndList = this.projectEndService.getProjectEndByProcessId(process.getId());
			
			Integer total_expected_num = 0; //总计- 报名人数
			Integer total_real_num = 0; //总计- 上线人数
			Integer total_payfee_num = 0; //总计- 应缴费人数
			BigDecimal total_real_money_num = BigDecimal.ZERO; //总计- 实际项目总额
			Integer total_charge_num = 0; //总计- 收费人数
			BigDecimal total_pay_num = BigDecimal.ZERO; //总计- 已到款金额
			BigDecimal total_invoice_num = BigDecimal.ZERO; //总计- 已开票金额
			BigDecimal total_not_pay_num = BigDecimal.ZERO; //总计- 未到款金额
			BigDecimal total_later_pay_num = BigDecimal.ZERO; //总计- 应收款金额
			
			for (ProjectEndEntity projectEndEntity : projectEndList) {
				total_real_num += projectEndEntity.getRealNum();
				total_payfee_num += projectEndEntity.getPayCount();
				total_charge_num += projectEndEntity.getChargeCount();
				total_real_money_num = MyMathUtil.add(total_real_money_num, projectEndEntity.getRealTotalMoney());
				total_later_pay_num = MyMathUtil.add(total_later_pay_num, projectEndEntity.getLaterPay());
				
				ProjectEntity project = this.projectService.getProjectById(projectEndEntity.getProjectId());
				BigDecimal realTotalPay = this.paymentService.getRealPayTotalByProjectId(project.getId());//已到款金额
				BigDecimal realTotalInvoice = this.paymentInvoiceService.getTotalInvoiceByProjectId(project.getId());//已开票金额
				BigDecimal notTotalPay = MyMathUtil.sub(projectEndEntity.getRealTotalMoney(), realTotalPay);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("realTotalPay", realTotalPay);
				map.put("realTotalInvoice", realTotalInvoice);
				map.put("notTotalPay", notTotalPay);
				project.setExtInfo(map);
				projectEndEntity.setProject(project);
				
				total_pay_num = MyMathUtil.add(total_pay_num, realTotalPay);
				total_invoice_num = MyMathUtil.add(total_invoice_num, realTotalInvoice);
				total_not_pay_num = MyMathUtil.add(total_not_pay_num, notTotalPay);
				total_expected_num += project.getExpectedNum(); 
			}
			
			modelMap.put("total_expected_num", total_expected_num);
			modelMap.put("total_real_num", total_real_num);
			modelMap.put("total_payfee_num", total_payfee_num);
			modelMap.put("total_real_money_num", total_real_money_num);
			modelMap.put("total_charge_num", total_charge_num);
			modelMap.put("total_pay_num", total_pay_num);
			modelMap.put("total_invoice_num", total_invoice_num);
			modelMap.put("total_not_pay_num", total_not_pay_num);
			modelMap.put("total_later_pay_num", total_later_pay_num);
			
			modelMap.put("projectEndList", projectEndList);
			return "project/projectEndAudit";
			
		}
		
		modelMap.put("tipMsg", "出错了：没有查询到流程关联的表单，请联系管理员。");
		return "tip";
	}
	
	
	/**
	 * 待发事项重新编辑并发送
	 */
	@RequestMapping(value="/editAndSend",method=RequestMethod.GET)
	public String viewNeedDealPage(Integer id, ModelMap modelMap, HttpServletRequest request) throws Exception{
		ProcessEntity process = this.processService.getProcessById(id);
		if (process == null) {
			modelMap.put("tipMsg", "出错了：没有查询到流程信息，请联系管理员。");
			return "tip";
		}
		modelMap.clear();
		if (SystemConst.TABLE_PROJECT.equals(process.getTableName())) {
			return "redirect:/project/editAndSend?procId="+id;
		}
		
		modelMap.put("tipMsg", "出错了：没有查询到流程关联的表单信息，请联系管理员。");
		return "tip";
		
	}
	
}
