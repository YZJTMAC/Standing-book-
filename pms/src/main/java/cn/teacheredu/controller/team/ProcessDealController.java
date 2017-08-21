package cn.teacheredu.controller.team;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.teacheredu.controller.Base3Controller;
import cn.teacheredu.entity.DepartmentEntity;
import cn.teacheredu.entity.PaymentEntity;
import cn.teacheredu.entity.PaymentInvoiceEntity;
import cn.teacheredu.entity.ProcessEntity;
import cn.teacheredu.entity.ProcessHistoryEntity;
import cn.teacheredu.entity.ProcessStepEntity;
import cn.teacheredu.entity.ProjectChangeEntity;
import cn.teacheredu.entity.ProjectEndEntity;
import cn.teacheredu.entity.ProjectEntity;
import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.form.PaymentDealForm;
import cn.teacheredu.service.DepartmentService;
import cn.teacheredu.service.FundsService;
import cn.teacheredu.service.NeedDealProcessService;
import cn.teacheredu.service.PaymentInvoiceService;
import cn.teacheredu.service.PaymentService;
import cn.teacheredu.service.ProcessHistoryService;
import cn.teacheredu.service.ProcessService;
import cn.teacheredu.service.ProcessStepService;
import cn.teacheredu.service.ProjectChangeService;
import cn.teacheredu.service.ProjectEndService;
import cn.teacheredu.service.ProjectService;
import cn.teacheredu.service.SystemLogService;
import cn.teacheredu.utils.MyMathUtil;
import cn.teacheredu.utils.R;
import cn.teacheredu.utils.SystemConst;
import cn.teacheredu.vo.ProjectChangeQueryVo;

@Controller
@RequestMapping(value = "/processDeal")
public class ProcessDealController extends Base3Controller {
	@Autowired
	private ProcessService processService;
	@Autowired
	private ProcessHistoryService processHistoryService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private NeedDealProcessService needDealProcessService;
	@Autowired
	private ProjectChangeService projectChangeService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private ProjectEndService projectEndService;
	@Autowired
	private PaymentInvoiceService paymentInvoiceService;
	@Autowired
	private FundsService fundsService;
	@Autowired
	private ProcessStepService processStepService;

	@RequestMapping(value = "/needDeal", method = RequestMethod.POST)
	public @ResponseBody R ajaxDealProcess(@RequestBody ProcessHistoryEntity ph, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserEntity user = (UserEntity) model.get("user");

		Integer processId = ph.getProcessId();
		ProcessEntity process = this.processService.getProcessById(processId);
		if (processId == null || process == null)
			return R.error("未查到流程的相关信息，请联系管理员");
		if (ph.getType() != 2 && !user.getId().equals(process.getCurrStepUserId()))
			return R.error("您暂时没有权限审批此流程，请联系管理员");
		if (ph.getNeedId() == null)
			return R.error("未查到流程待办信息，请联系管理员");
		
		ProcessStepEntity stepNow = this.processStepService.getProcessStepById(process.getStepId());
		ProcessStepEntity stepNext = null;
		if (stepNow != null) {
			stepNext = this.processStepService.getProcessStepById(stepNow.getNextStepId());
			if (!"1".equals(stepNow.getEndStatus()) && (stepNext == null || StringUtils.isBlank(String.valueOf(stepNext.getDmId())) || StringUtils.isBlank(String.valueOf(stepNext.getUserId())) || StringUtils.isBlank(String.valueOf(stepNext.getType())))) {
				return R.error("未查到流程步骤配置信息，请联系管理员");
			}
		}
		
		Date date = new Date();
		ph.setUserId(user.getId());
		ph.setDealTime(date);
		ph.setStepNote(stepNow == null ? null : stepNow.getStepNote()); //设置历史记录对应步骤
		
		this.processHistoryService.insertProcessHistoryEntity(ph);
		systemLogService.saveSystemLog(1, ph.toString(), user.getId());


		process.setLastStepUserId(user.getId());
		process.setLastStepTime(date);

		if ("不同意".equals(ph.getDealResult())) {
			process.setCurrStepUserId(0);
			process.setStatus(2); // 流程被退回
			process.setStepId(0); 
			if (SystemConst.TABLE_INVOICE.equals(process.getTableName())) { // 发票申请表被退回了，需要假删payment-invoice表数据，以便业务员能再次选择这些到款重新填表提交
				this.paymentInvoiceService.editAvailableByInvoiceId(process.getObjectId());
			}
			if (SystemConst.TABLE_FUNDS.equals(process.getTableName())) { // 经费申请表被退回了，需要假删funds表数据，以便查询已支付和正在申请经费之和结果是正确的
				this.fundsService.editAvailableById(process.getObjectId());
			}
			// if (SystemConst.TABLE_PROJECTEND.equals(process.getTableName())){
			//
			// }
		} else {
			// 计算下一个待办人
			if (SystemConst.TABLE_PROJECT.equals(process.getTableName())) { // 项目确认表
				
				if (stepNow != null) {
					if ("1".equals(stepNow.getEndStatus())) {//流程结束
						process.setCurrStepUserId(0);
						process.setStatus(4);
						process.setStepId(0);
						// 项目状态置为进行中
						ProjectEntity project = this.projectService.getProjectById(process.getProjectId());
						if (project != null) {
							project.setStatus((byte) 1);
							project.setUrl(ph.getDealOpinion());
							this.projectService.updateProject(project);
						}
					} else {//流程未结束
						process.setType(stepNext.getType());
						process.setCurrStepUserId(stepNext.getUserId());
						process.setStepId(stepNext.getId());
						
						this.needDealProcessService.insertNeedDealProcessEntity(process);
					}
				} else {
					process.setCurrStepUserId(-1); //流程步骤配置有问题的情况
					process.setStepId(-1);
				}
				
			} else if (SystemConst.TABLE_PROJECTCHANGE.equals(process.getTableName())) { // 信息变更表

				if (stepNow != null) {
					if ("1".equals(stepNow.getEndStatus())) {//流程结束
						process.setCurrStepUserId(0); // 当前处理人为0结束
						process.setStatus(4); // 正常结束
						process.setStepId(0); // 当前步骤为0结束
						
						// 项目信息更改
						ProjectEntity project = this.projectService.getProjectById(process.getProjectId());
						ProjectChangeQueryVo vo = new ProjectChangeQueryVo();
						vo.setProcessId(processId);
						List<ProjectChangeEntity> pcList = this.projectChangeService.getProjectChangeByVo(vo);
						Integer expected_num = null;
						BigDecimal charge_standard = null;
						for (int i = 0; i < pcList.size(); i++) {
							ProjectChangeEntity pcEntity = pcList.get(i);
							String columnName = pcEntity.getColumnName(); // 被修改的列名
							String columnValue = pcEntity.getColumnValue(); // 修改后的内容
							if (columnValue != null && !"".equals(columnValue)) {
								// 向projectEntity实体类中添加属性，更新项目确认表
								if ("expected_num".equals(columnName)) {
									expected_num = Integer.parseInt(columnValue);
									project.setExpectedNum(expected_num);
								}
								if ("charge_standard".equals(columnName)) {
									charge_standard = new BigDecimal(columnValue);
									project.setChargeStandard(charge_standard);
								}
								if ("pro_fund_provincial".equals(columnName)) {
									project.setProFundProvincial(columnValue);
								}
								if ("pro_fund_city".equals(columnName)) {
									project.setProFundCity(columnValue);
								}
								if ("pro_fund_county".equals(columnName)) {
									project.setProFundCounty(columnValue);
								}
								if ("pro_fund_other".equals(columnName)) {
									project.setProFundOther(columnValue);
								}
								if ("later_fund_provincial".equals(columnName)) {
									project.setLaterFundProvincial(columnValue);
								}
								if ("later_fund_city".equals(columnName)) {
									project.setLaterFundCity(columnValue);
								}
								if ("later_fund_county".equals(columnName)) {
									project.setLaterFundCounty(columnValue);
								}
								if ("later_fund_other".equals(columnName)) {
									project.setLaterFundOther(columnValue);
								}
								if ("start_date".equals(columnName)) {
									project.setStartDate(DateUtils.parseDate(columnValue, new String[] { "yyyy-MM-dd HH:mm:ss" }));
								}
								if ("end_date".equals(columnName)) {
									project.setEndDate(DateUtils.parseDate(columnValue, new String[] { "yyyy-MM-dd HH:mm:ss" }));
								}
								if ("collect_money_company".equals(columnName)) {
									project.setCollectMoneyCompany(columnValue);
								}
							}
						}
						// 有改过报名人数或者收费标准，需要重新计算项目总额
						if (expected_num != null || charge_standard != null) {
							BigDecimal totalMoney = MyMathUtil.mul(new BigDecimal(project.getExpectedNum()), project.getChargeStandard());
							project.setTotalMoney(totalMoney);
						}
						this.projectService.updateProject(project);
					} else {//流程未结束
						process.setType(stepNext.getType());
						process.setCurrStepUserId(stepNext.getUserId());
						process.setStepId(stepNext.getId());
						
						this.needDealProcessService.insertNeedDealProcessEntity(process);
					}
				} else {
					process.setCurrStepUserId(-1); //流程步骤配置有问题的情况
					process.setStepId(-1);
				}
			} else if (SystemConst.TABLE_FUNDS.equals(process.getTableName())) { // 经费申请表

				if (stepNow != null) {
					if ("1".equals(stepNow.getEndStatus())) {//流程结束
						process.setCurrStepUserId(0); // 当前处理人为0结束
						process.setStatus(4); // 正常结束
						process.setStepId(0);
						
						// 把经费申请记录置为已支付
						this.fundsService.editWonPayById(process.getObjectId(),"1");
					} else {//流程未结束
						process.setType(stepNext.getType());
						process.setCurrStepUserId(stepNext.getUserId());
						process.setStepId(stepNext.getId());
						
						this.needDealProcessService.insertNeedDealProcessEntity(process);
					}
				} else {
					process.setCurrStepUserId(-1); //流程步骤配置有问题的情况
					process.setStepId(-1);
				}
			} else if (SystemConst.TABLE_INVOICE.equals(process.getTableName())) { // 发票申请表

				if (stepNow != null) {
					if ("1".equals(stepNow.getEndStatus())) {//流程结束
						process.setCurrStepUserId(0); // 当前处理人为0结束
						process.setStatus(4); // 正常结束
						process.setStepId(0);
						
						// 把本表单中所有的paymentInvoice的result属性置为已开状态
						this.paymentInvoiceService.editResultByInvoiceId(process.getObjectId());
						// 把payment表中对应的到款信息设置成已开发票的状态
						this.paymentService.editHasInvoiceByInvoiceId(process.getObjectId());
					} else {//流程未结束
						process.setType(stepNext.getType());
						process.setCurrStepUserId(stepNext.getUserId());
						process.setStepId(stepNext.getId());
						
						this.needDealProcessService.insertNeedDealProcessEntity(process);
					}
				} else {
					process.setCurrStepUserId(-1); //流程步骤配置有问题的情况
					process.setStepId(-1);
				}
			} else if (SystemConst.TABLE_PROJECTEND.equals(process.getTableName())) { // 项目完结单

				if (stepNow != null) {
					if ("1".equals(stepNow.getEndStatus())) {//流程结束
						process.setCurrStepUserId(0); // 当前处理人为0结束
						process.setStatus(4); // 正常结束
						process.setStepId(0);
						
						// 置project的status的值为已结束
						List<ProjectEndEntity> projectEndEntities = this.projectEndService.getProjectEndByProcessId(processId);
						for (ProjectEndEntity projectEndEntity : projectEndEntities) {
							this.projectService.editProjectStatus((byte) 2, projectEndEntity.getProjectId());
						}
					} else {//流程未结束
						process.setType(stepNext.getType());
						process.setCurrStepUserId(stepNext.getUserId());
						process.setStepId(stepNext.getId());
						
						this.needDealProcessService.insertNeedDealProcessEntity(process);
					}
				} else {
					process.setCurrStepUserId(-1); //流程步骤配置有问题的情况
					process.setStepId(-1);
				}
			}

		}
		this.processService.updateProcess(process);
		systemLogService.saveSystemLog(2, process.toString(), user.getId());
		return R.ok();
	}

	/**
	 * 处理到款流程
	 * 
	 * @param form
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/dealPayment", method = RequestMethod.POST)
	public @ResponseBody R ajaxDealPaymentProcess(@RequestBody PaymentDealForm form, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserEntity user = (UserEntity) model.get("user");
		@SuppressWarnings("unchecked")
		List<DepartmentEntity> dmList = (List<DepartmentEntity>) model.get("dmList");
		if (dmList == null || dmList.size() == 0) {
			return R.error("未查到您所属部门的信息");
		}
		
		Integer processId = form.getProcessId();
		ProcessEntity process = this.processService.getProcessById(processId);
		if (process == null)
			return R.error("未查到流程的相关信息，请联系管理员");
		if (form.getType() != 2 && !user.getId().equals(process.getCurrStepUserId()))
			return R.error("您暂时没有权限审批此流程，请联系管理员");
		if (form.getNeedId() == null)
			return R.error("未查到流程待办信息，请联系管理员");

		ProcessStepEntity stepNext = null;
		DepartmentEntity departmentEntity = dmList.get(0);
		if ("2".equals(departmentEntity.getDmType())) {
			if (form.getProvincial() == null || form.getProvincial() == 0) {
				return R.error("请选择到款信息所属省份..");
			}
			List<DepartmentEntity> list = this.departmentService.getDepartmentListByProcince(form.getProvincial());
			if (list.size() != 1) {
				return R.error("您选择的省份对应的部门信息配置有误，请联系管理员");
			}
			
			DepartmentEntity departmentEntity2 = list.get(0);
			stepNext = this.processStepService.getProcessStepByDefineInfo(SystemConst.TABLE_PAYMENT, departmentEntity2.getId(), null);
			if (stepNext == null || StringUtils.isBlank(String.valueOf(stepNext.getUserId())) || StringUtils.isBlank(String.valueOf(stepNext.getType()))) {
				return R.error("未获取到您的流程步骤配置信息，请联系管理员");
			}
		}
		
		ProcessStepEntity stepNow = this.processStepService.getProcessStepById(process.getStepId());
		
		// 流程历史表新增一条数据,并且流程待办表的最后一个字段置为已办
		ProcessHistoryEntity ph = new ProcessHistoryEntity();
		ph.setProcessId(processId);
		ph.setDealOpinion(form.getDealOpinion());
		ph.setType(form.getType().byteValue());
		ph.setDealResult(form.getDealResult());
		ph.setUserId(user.getId());
		ph.setNeedId(form.getNeedId());
		Date date = new Date();
		ph.setDealTime(date);
		ph.setStepNote(stepNow == null ? null : stepNow.getStepNote()); //设置历史记录对应步骤
		this.processHistoryService.insertProcessHistoryEntity(ph);
		systemLogService.saveSystemLog(1, ph.toString(), user.getId());
		
		process.setLastStepUserId(user.getId());
		process.setLastStepTime(date);

		PaymentEntity payment = this.paymentService.getPaymentById(process.getObjectId());
		if (payment == null) {
			return R.error("未查到相关的到款信息，请联系管理员处理数据");
		}

		if ("2".equals(departmentEntity.getDmType())) { // 商务
			payment.setProvince(form.getProvincial());
			payment.setCity(form.getCity());
			payment.setCounty(form.getCounty());
			process.setCurrStepUserId(stepNext.getUserId());
			process.setStepId(stepNext.getId());
			process.setType(stepNext.getType());
			this.needDealProcessService.insertNeedDealProcessEntity(process);
		} else if ("4".equals(departmentEntity.getDmType())) { // 省份部门
			payment.setAdvancePay(form.getAdvancePay().byteValue());
			payment.setProjectId(form.getProjectId());
			payment.setCompany(form.getCompany());
			payment.setNoteYw(form.getNoteYw());
			if (form.getAdvancePay() == 1) { // 提前开票到款
				payment.setHasInvoice(1);
			} else {
				payment.setHasInvoice(2);
			}

			process.setCurrStepUserId(0); // 当前处理人为0结束
			process.setStepId(0); // 当前步骤为0结束
			process.setStatus(4); // 正常结束
			process.setProjectId(form.getProjectId());// 把流程对应上项目
			String paymentInvoiceIdList = form.getPaymentInvoiceIdList();
			if (!"".equals(paymentInvoiceIdList)) {
				String[] piList = paymentInvoiceIdList.split(",");
				for (String piId : piList) {
					PaymentInvoiceEntity paymentInvoice = paymentInvoiceService
							.getPaymentInvoiceById(Integer.valueOf(piId));
					paymentInvoice.setPaymentId(payment.getId());
					this.paymentInvoiceService
							.updatePaymentInvoice(paymentInvoice);
				}
			}

		} else {
			return R.error("抱歉，您没有权限审批此流程，请联系管理员!");
		}
		this.paymentService.updatePayment(payment);
		this.processService.updateProcess(process);
		return R.ok();
	}
}
