package cn.teacheredu.controller.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.teacheredu.controller.Base3Controller;
import cn.teacheredu.entity.DepartmentEntity;
import cn.teacheredu.entity.InvoiceEntity;
import cn.teacheredu.entity.PaymentEntity;
import cn.teacheredu.entity.PaymentInvoiceEntity;
import cn.teacheredu.entity.ProcessEntity;
import cn.teacheredu.entity.ProcessStepEntity;
import cn.teacheredu.entity.ProjectEntity;
import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.form.CommonForm;
import cn.teacheredu.form.InvoiceForm;
import cn.teacheredu.service.AttachmentService;
import cn.teacheredu.service.InvoiceService;
import cn.teacheredu.service.PaymentInvoiceService;
import cn.teacheredu.service.PaymentService;
import cn.teacheredu.service.ProcessService;
import cn.teacheredu.service.ProcessStepService;
import cn.teacheredu.service.ProjectService;
import cn.teacheredu.service.SystemLogService;
import cn.teacheredu.utils.CommonUtils;
import cn.teacheredu.utils.MyMathUtil;
import cn.teacheredu.utils.R;
import cn.teacheredu.utils.SystemConst;
import cn.teacheredu.vo.PaymentInvoiceQueryVo;

@Controller
@RequestMapping(value = "/invoice")
public class InvoiceController extends Base3Controller{
	
	@Autowired
	public InvoiceService invoiceService;
	
	@Autowired
	public PaymentInvoiceService paymentInvoiceService;
	
	@Autowired
	private SystemLogService systemLogService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private ProcessService processService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ProcessStepService processStepService;
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Integer projectId, Integer type, String ids, Integer le, ModelMap modelMap, HttpServletRequest request) throws  Exception{
		@SuppressWarnings("unchecked")
		List<String> psList=(List<String>) modelMap.get("psList");
		UserEntity user = (UserEntity) modelMap.get("user");
		if (!CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_APPLYFOR_INVOICE, psList)) {
			modelMap.put("tipMsg", "抱歉...你没有申请发票的权限");
			return "tip";
		}
		
		ProjectEntity projectEntity = projectService.getProjectById(projectId);
		if (projectEntity == null || type == null || type > 2 || type < 1) {
			modelMap.put("tipMsg", "参数有误，进入页面失败。");
			return "tip";
		}
		@SuppressWarnings("unchecked")
		List<DepartmentEntity> dmList = (List<DepartmentEntity>) modelMap.get("dmList");
		if (dmList == null || dmList.size() == 0) {
			modelMap.put("tipMsg", "未查询到您的部门信息，进入页面失败。");
			return "tip";
		}
		if (!user.getId().equals(projectEntity.getUserId()) && !user.getId().toString().equals(dmList.get(0).getDirectorId())) {
			modelMap.put("tipMsg", "抱歉，只有这个项目的发起人和他的领导才可以申请这个项目的发票。");
			return "tip";
		}
//		if (projectEntity.getStatus() >= 2) {
//			modelMap.put("tipMsg", "本项目已结束或待结束状态，不可以申请发票了。");
//			return "tip";
//		}
		
		if (type == 1) {
			String[] payIds = ids.split("_");
			//去重并转换为int
			Set<Integer> set = new LinkedHashSet<Integer>();
			for (int i = 0; i < payIds.length; i++) {
				set.add(Integer.parseInt(payIds[i]));
			}
			if (set.size() != le) {
				modelMap.put("tipMsg", "参数有误，进入页面失败。");
				return "tip";
			} else {
				List<PaymentEntity> paymentList = new ArrayList<PaymentEntity>();
				for (Integer paymentId : set) {
					PaymentEntity payment = this.paymentService.getPaymentById(paymentId);
					if (payment == null) {
						modelMap.put("tipMsg", "未查到某些到款信息，进入页面失败，请联系管理员。");
						return "tip";
					} else if(!projectId.equals(payment.getProjectId()) || payment.getAdvancePay() == 1){
						modelMap.put("tipMsg", "部分到款信息有误，进入页面失败，请联系管理员。");
						return "tip";
					} else {
						paymentList.add(payment);
					}
				}
				modelMap.put("paymentList", paymentList);
			}
		}
		
		
		modelMap.put("projectEntity", projectEntity);
		Date now = new Date();
		modelMap.put("title", "发票申请表-"+user.getRealname()+"-"+DateFormatUtils.format(now, "yyyy.MM.dd HH.mm"));
		modelMap.put("type",  type);//1:已到款开发票 2：未到款开发票
		return "invoice/applyForInvoice";
	}
	
	
	
	
	@RequestMapping(value="/ajaxAddInvoice",method=RequestMethod.POST)
	public @ResponseBody R ajaxAddInvoice(@RequestBody InvoiceForm invoice, ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		UserEntity user = (UserEntity) model.get("user");
		String validResult = this.validateInvoice(invoice);
		if (!"success".equals(validResult)) {
			return R.error(validResult);
		}
		
		InvoiceEntity myinvoice=new InvoiceEntity();
		BeanUtils.copyProperties(invoice, myinvoice);
		myinvoice.setUid(user.getId());
		myinvoice.setCreateTime(new Date());
		int invoiceId=invoiceService.insertInvoiceEntity(myinvoice);
		this.systemLogService.saveSystemLog(1, myinvoice.toString(),user.getId());
		if (invoiceId > 0) {
			List<PaymentInvoiceEntity> paymentInvoiceList = invoice.getPaymentInvoiceList();
			int size = paymentInvoiceList.size();
			for (int i = 0; i < size; i++) {
				PaymentInvoiceEntity payInvoice=paymentInvoiceList.get(i);
				payInvoice.setInvoiceId(invoiceId);
				payInvoice.setProjectId(myinvoice.getProjectId());
				this.paymentInvoiceService.insertPaymentInvoiceEntity(payInvoice);
				this.systemLogService.saveSystemLog(1, payInvoice.toString(), user.getId());
			}
			return R.ok().put("objectId", invoiceId).put("projectId", myinvoice.getProjectId());
		}else{
			return R.error("未知原因");
		}
		
	}
	@RequestMapping(value="/ajaxAddProcess",method=RequestMethod.POST)
	public @ResponseBody R ajaxAddProcess(@RequestBody CommonForm common,ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		UserEntity user = (UserEntity) model.get("user");
		if (common == null || common.getObjectId() == null || common.getProjectId() == null) {
			return R.error("未获取到项目ID参数,请联系管理员做数据处理");
		} else {
			//流程表添加一条数据，先算出当前待办人
			if (StringUtils.isBlank(common.getProcessName())) {
				return R.error("未获取到流程标题参数，请联系管理员做数据处理");
			}
			@SuppressWarnings("unchecked")
			List<DepartmentEntity> dmList = (List<DepartmentEntity>) model.get("dmList");
			if (dmList == null || dmList.size() == 0) {
				return R.error("未得到您的部门信息，请联系管理员做数据处理");
			}
			DepartmentEntity departmentEntity = dmList.get(0);
			ProcessStepEntity stepEntity = this.processStepService.getProcessStepByDefineInfo(SystemConst.TABLE_INVOICE, departmentEntity.getId(), null);
			if(stepEntity == null || StringUtils.isBlank(String.valueOf(stepEntity.getUserId()))){
				return R.error("未获取到您的流程步骤配置信息，请联系管理员做数据处理");
			}
			
			ProcessEntity processEntity = new ProcessEntity();
			processEntity.setCreateUserId(user.getId());
			processEntity.setStartTime(new Date());
			processEntity.setCreateTime(new Date());
			processEntity.setCurrStepUserId(stepEntity.getUserId());
			processEntity.setLastStepUserId(user.getId());
			processEntity.setLastStepTime(new Date());
			processEntity.setTitle(common.getProcessName());
			processEntity.setProjectId(common.getProjectId());
			processEntity.setObjectId(common.getObjectId());
			processEntity.setTableName(SystemConst.TABLE_INVOICE);
			processEntity.setType(stepEntity.getType() == null ? 1 : stepEntity.getType()); //审批流程类型
			processEntity.setStatus(3);//进行中
			processEntity.setCreateUserName(user.getRealname());
			processEntity.setStepId(stepEntity.getId());
			
			int processId = this.processService.insertProcessEntity(processEntity);
			//有附件的话保存附件
			this.attachmentService.updateAttachmentByIdAndName(common.getAttachIds(), SystemConst.TABLE_PROCESS, processId);
		}
		return R.ok();
	}
	
	private String validateInvoice(InvoiceForm invoice) throws Exception {
		if (invoice.getProjectId()==null)
			return "未关联项目";
		if (invoice.getType() == null)
			return "您没有选择发票类型";
		if ( StringUtils.isBlank(invoice.getPostName()))
			return "邮寄姓名不能为空";
		if (StringUtils.isBlank(invoice.getPostMobile()))
			return "电话号码不能为空";
		if (StringUtils.isBlank(invoice.getPostAddr()))
			return "地址不能为空";
		List<Integer> paymentIdList = new ArrayList<Integer>();
		if (invoice.getType() == 1) {
			paymentIdList = this.paymentInvoiceService.selectInvoiceByProjectId(invoice.getProjectId());
		}
		List<PaymentInvoiceEntity> paymentInvoiceList=invoice.getPaymentInvoiceList();
		String str=null;
		for (int i = 0; i < paymentInvoiceList.size(); i++) {
			PaymentInvoiceEntity payInvoice=paymentInvoiceList.get(i);
			if (invoice.getType() == 1) {
				if (payInvoice.getPaymentId() == null) {
					str="未能查询到部分到款信息";
					break;
				}
				if (paymentIdList.contains(payInvoice.getPaymentId())) {
					str="表中部分到款信息已经申请过发票或正在申请发票。";
					break;
				}
			}
			
			if (StringUtils.isBlank(payInvoice.getInvoiceHeader())){
				str="发票抬头不能为空";
				break;
			}
			if (StringUtils.isBlank(payInvoice.getInvoiceItem())){
				str="发票项目不能为空";
				break;
			}
			if (payInvoice.getCount()== null){
				str="数量不能为空";
				break;
			}
			if (payInvoice.getUnitPrice()==null){
				str="单价不能为空";
				break;
			}
			if (payInvoice.getInvoiceMachine()== null){
				str="机打发票金额不能为空";
				break;
			}	
			
			if (!validateIsEquels(payInvoice.getInvoiceHundred(),payInvoice.getInvoiceFifty(),payInvoice.getInvoiceMachine(),payInvoice.getUnitPrice(),payInvoice.getCount())){
				
				str="后台验证:发票金额不对等";
				break;
			}	
			
		}
		if(str!=null){
			return str;
		}
		
		return "success";
	}
	public Boolean validateIsEquels(BigDecimal hundred,BigDecimal fifty,BigDecimal InvoiceMachine,BigDecimal unitPrice,Integer count){
		BigDecimal total1 = MyMathUtil.addBigDecimal(hundred,fifty,InvoiceMachine);
		BigDecimal total2 = MyMathUtil.mul(unitPrice, new BigDecimal(count));
		return total1.compareTo(total2) == 0 ? true : false;
	}
	
	// 查询项目下的开票信息paymentInvoiceList
	// paymentInvoiceList中的发票信息需要是未到款发票
	//
	@RequestMapping(value="/queryPaymentInvoice")
	public String queryPaymentInvoice(ModelMap model, Integer projectId, String paymentInvoiceIdList, HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(projectId!=null&&projectId!=0){
			PaymentInvoiceQueryVo vo = new PaymentInvoiceQueryVo();
			vo.setProjectId(projectId);
			vo.setResult((byte)1);
			List<PaymentInvoiceEntity> paymentInvoiceList = new ArrayList<PaymentInvoiceEntity>();
			List<PaymentInvoiceEntity> paymentInvoiceListAll = paymentInvoiceService.getPaymentInvoiceByVo(vo);
			for(PaymentInvoiceEntity pi: paymentInvoiceListAll){
				if(pi.getPaymentId()==null||pi.getPaymentId()==0)
					paymentInvoiceList.add(pi);
			}
			model.put("paymentInvoice", paymentInvoiceList);
		}else if(paymentInvoiceIdList!=null&&paymentInvoiceIdList!=""){
			String[] piList = paymentInvoiceIdList.split(",");
			List<PaymentInvoiceEntity> paymentInvoiceList = new ArrayList<PaymentInvoiceEntity>();
			for(String piId: piList){
				PaymentInvoiceEntity paymentInvoice = paymentInvoiceService.getPaymentInvoiceById(Integer.valueOf(piId));
				paymentInvoiceList.add(paymentInvoice);
			}
			model.put("paymentInvoice", paymentInvoiceList);
		}
		return "invoice/invoiceTable";
	}
	
	@RequestMapping(value="/queryProjectcompany")
	public @ResponseBody String queryProjectCompany(ModelMap model,@RequestParam("projectId") Integer projectId, HttpServletRequest request,HttpServletResponse response) throws Exception{

		ProjectEntity project = projectService.getProjectById(projectId);
		String company = project.getCollectMoneyCompany();
		String projectJson = "{\"company\":\""+company+"\"}";
		return projectJson;
	}
	
	
}
