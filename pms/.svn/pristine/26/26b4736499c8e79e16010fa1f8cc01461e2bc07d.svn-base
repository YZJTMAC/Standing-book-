package cn.teacheredu.controller.funds;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
import cn.teacheredu.entity.DepartmentEntity;
import cn.teacheredu.entity.FundsEntity;
import cn.teacheredu.entity.ProcessEntity;
import cn.teacheredu.entity.ProcessStepEntity;
import cn.teacheredu.entity.ProjectEntity;
import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.form.CommonForm;
import cn.teacheredu.service.AttachmentService;
import cn.teacheredu.service.FundsService;
import cn.teacheredu.service.PaymentService;
import cn.teacheredu.service.ProcessService;
import cn.teacheredu.service.ProcessStepService;
import cn.teacheredu.service.ProjectService;
import cn.teacheredu.service.SystemLogService;
import cn.teacheredu.utils.CommonUtils;
import cn.teacheredu.utils.MyMathUtil;
import cn.teacheredu.utils.R;
import cn.teacheredu.utils.SystemConst;

@Controller
@RequestMapping(value = "/funds")
public class FundsController extends Base3Controller {

	private static Logger logger = LoggerFactory.getLogger(FundsController.class);

	@Autowired
	private FundsService fundsService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private ProcessService processService;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private ProcessStepService processStepService;

	/**
	 * @param fundsId
	 * @param map
	 * @return 跳转至经费申请页面
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	@RequestMapping(value = "/addFundsPage", method = RequestMethod.GET)
	public String addFundsPage(Integer projectId, ModelMap modelMap, HttpServletRequest request) throws Exception {
		UserEntity user = (UserEntity) modelMap.get("user");
		@SuppressWarnings("unchecked")
		List<String> psList = (List<String>) modelMap.get("psList");
		if (!CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_CREATE_FUNDS, psList)) {
			modelMap.put("tipMsg", "抱歉...你没有申请经费的权限");
			return "tip";
		}
		logger.info("=====/funds/addFunds=====" + user.getLoginName());
		ProjectEntity projectEntity = projectService.getProjectById(projectId);
		if (projectEntity == null) {
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
			modelMap.put("tipMsg", "抱歉，只有这个项目的发起人和他的领导才可以申请这个项目的经费。");
			return "tip";
		}
//		if (projectEntity.getStatus() >= 2) {
//			modelMap.put("tipMsg", "本项目已结束或待结束状态，不可以申请经费了。");
//			return "tip";
//		}
		modelMap.put("project", projectEntity);
		
		// 金额计算
		BigDecimal q_provinceb = MyMathUtil.div(new BigDecimal(projectEntity.getProFundProvincial()), new BigDecimal("100.00"), 4); //前期省级经费比例
		BigDecimal q_ctiyb = MyMathUtil.div(new BigDecimal(projectEntity.getProFundCity()), new BigDecimal("100.00"), 4);
		BigDecimal q_countyb = MyMathUtil.div(new BigDecimal(projectEntity.getProFundCounty()), new BigDecimal("100.00"), 4);
		BigDecimal q_otherb = MyMathUtil.div(new BigDecimal(projectEntity.getProFundOther()), new BigDecimal("100.00"), 4);
		
		BigDecimal h_provinceb = MyMathUtil.div(new BigDecimal(projectEntity.getLaterFundProvincial()), new BigDecimal("100.00"), 4); ////后期省级经费比例
		BigDecimal h_ctiyb = MyMathUtil.div(new BigDecimal(projectEntity.getLaterFundCity()), new BigDecimal("100.00"), 4);
		BigDecimal h_countyb = MyMathUtil.div(new BigDecimal(projectEntity.getLaterFundCounty()), new BigDecimal("100.00"), 4);
		BigDecimal h_otherb = MyMathUtil.div(new BigDecimal(projectEntity.getLaterFundOther()), new BigDecimal("100.00"), 4);
		
		modelMap.put("q_province", MyMathUtil.mul(projectEntity.getTotalMoney(), q_provinceb));
		modelMap.put("q_city", MyMathUtil.mul(projectEntity.getTotalMoney(), q_ctiyb));
		modelMap.put("q_county", MyMathUtil.mul(projectEntity.getTotalMoney(), q_countyb));
		modelMap.put("q_other", MyMathUtil.mul(projectEntity.getTotalMoney(), q_otherb));
		
		modelMap.put("h_province", MyMathUtil.mul(projectEntity.getTotalMoney(), h_provinceb));
		modelMap.put("h_city", MyMathUtil.mul(projectEntity.getTotalMoney(), h_ctiyb));
		modelMap.put("h_county", MyMathUtil.mul(projectEntity.getTotalMoney(), h_countyb));
		modelMap.put("h_other", MyMathUtil.mul(projectEntity.getTotalMoney(), h_otherb));
		
		BigDecimal qb = MyMathUtil.div(MyMathUtil.addStringToBigDecimal(projectEntity.getProFundCity(),projectEntity.getProFundCounty(),projectEntity.getProFundOther(),projectEntity.getProFundProvincial()), new BigDecimal("100.00"), 4); 
		BigDecimal hb = MyMathUtil.div(MyMathUtil.addStringToBigDecimal(projectEntity.getLaterFundCity(),projectEntity.getLaterFundCounty(),projectEntity.getLaterFundOther(),projectEntity.getLaterFundProvincial()), new BigDecimal("100.00"), 4); 
		BigDecimal qj = MyMathUtil.mul(projectEntity.getTotalMoney(),qb);
		BigDecimal hj = MyMathUtil.mul(projectEntity.getTotalMoney(),hb);
		// 后期合作经费总额
		modelMap.put("hj", hj);
		// 应收金额
		BigDecimal ssje = MyMathUtil.sub(projectEntity.getTotalMoney(), qj);
		modelMap.put("ssje", ssje);
		// 已支付经费金额
		BigDecimal yzf = this.fundsService.getTotalWonPayByProject(projectId);
		modelMap.put("yzf", yzf);
		// 未支付经费金额
		BigDecimal wzf = MyMathUtil.sub(hj, yzf);
		modelMap.put("wzf", wzf);
		
		// 实际到款金额
		BigDecimal ydk = this.paymentService.getRealPayTotalByProjectId(projectId);
		modelMap.put("ydk", ydk);
		
		// 已到款可支付经费金额=后期合作经费综合/应收总额*实际到款金额     
		if (projectEntity.getTotalMoney().compareTo(new BigDecimal("0")) != 0) {
			modelMap.put("fj", MyMathUtil.mul(MyMathUtil.div(hj, ssje, 4), ydk));
		} else {
			modelMap.put("fj", new BigDecimal("0.00"));
		}
		
		Date now = new Date();
		modelMap.put("title", "经费申请表-" + user.getRealname() + "-" + DateFormatUtils.format(now, "yyyy.MM.dd HH.mm"));
		return "funds/addFunds";
	}

	/**
	 * 经费申请-提交
	 * 
	 * @param fEntity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ajaxAddFunds", method = RequestMethod.POST)
	public @ResponseBody R ajaxAddFunds(@RequestBody FundsEntity fEntity, ModelMap modelMap) throws Exception {
		UserEntity user = (UserEntity) modelMap.get("user");
		String validateFunds = this.validateFunds(fEntity);
		if (!"success".equals(validateFunds)) {
			return R.error(validateFunds);
		}
		
		ProjectEntity projectEntity = projectService.getProjectById(fEntity.getProjectId());
		if (projectEntity == null) {
			return R.error("未查到项目信息");
		}
		BigDecimal hb = MyMathUtil.div(MyMathUtil.addStringToBigDecimal(projectEntity.getLaterFundCity(),projectEntity.getLaterFundCounty(),projectEntity.getLaterFundOther(),projectEntity.getLaterFundProvincial()), new BigDecimal("100.00"), 4); 
		BigDecimal hj = MyMathUtil.mul(projectEntity.getTotalMoney(),hb);
		// 已支付经费金额 + 正在申请的经费金额
		BigDecimal money = this.fundsService.getTotalFundsByProject(fEntity.getProjectId());
		// 当前申请的金额 加上money 不能大于项目后期合作经费
		BigDecimal total = MyMathUtil.add(money, fEntity.getApplyAmount());
		if (total.compareTo(hj) == 1) {
			return R.error("您申请的金额加上以前申请的金额已经大于项目合作经费的总额");
		}
		
		fEntity.setCreateTime(new Date());
		int fundsEntity = fundsService.insertFundsEntity(fEntity);
		if (fundsEntity > 0) {
			systemLogService.saveSystemLog(1, "funds_" + fEntity.toString(), user.getId());
			return R.ok().put("objectId", fundsEntity).put("projectId", fEntity.getProjectId());
		} else {
			return R.error("未知错误！");
		}
	}
	

	/**
	 * 添加流程-提交
	 * @param common
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ajaxAddProcess", method = RequestMethod.POST)
	public @ResponseBody R ajaxAddProcess(@RequestBody CommonForm common, ModelMap modelMap) throws Exception {
		UserEntity user = (UserEntity) modelMap.get("user");
		if (common == null || common.getObjectId() == null || common.getProjectId() == null) {
			return R.error("未获取到项目ID参数,请联系管理员做数据处理");
		} else {
			// 流程表添加一条数据，先算出当前待办人
			if (StringUtils.isBlank(common.getProcessName())) {
				return R.error("未获取到流程标题参数，请联系管理员做数据处理");
			}
			@SuppressWarnings("unchecked")
			List<DepartmentEntity> dmList = (List<DepartmentEntity>) modelMap.get("dmList");
			if (dmList == null) {
				return R.error("未得到您的部门信息，请联系管理员做数据处理");
			}
			DepartmentEntity departmentEntity = dmList.get(0);
			ProcessStepEntity stepEntity = this.processStepService.getProcessStepByDefineInfo(SystemConst.TABLE_FUNDS, departmentEntity.getId(), null);
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
			processEntity.setTableName(SystemConst.TABLE_FUNDS);
			processEntity.setType(stepEntity.getType() == null ? 1 : stepEntity.getType()); //审批流程类型
			processEntity.setStatus(3);// 进行中
			processEntity.setCreateUserName(user.getRealname());
			processEntity.setStepId(stepEntity.getId());
			int processId = processService.insertProcessEntity(processEntity);
			if (processId > 0) {
				// 有附件的话保存附件
				attachmentService.updateAttachmentByIdAndName(common.getAttachIds(), SystemConst.TABLE_PROCESS, processId);
			} else {
				return R.error("未知错误");
			}
			
		}
		return R.ok();
	}
	
	/**
	 * 后台表单验证
	 * 
	 * @param fEntity
	 * @return
	 */
	private String validateFunds(FundsEntity fEntity) {
		if (fEntity.getProjectId() == null) {
			return "未关联项目";
		}
		if (fEntity.getType() == 0) {
			return "您没有选择申请类型";
		}
		if (fEntity.getApplyAmount() == null) {
			return "本次申请经费的金额不能为空";
		}
		if (fEntity.getType() == 2) {
			if (StringUtils.isBlank(fEntity.getInvoiceContent())) {
				return "发票内容不能为空";
			}
			if (fEntity.getInvoiceAmount() == null) {
				return "发票金额不能为空";
			}
		}
		if (StringUtils.isBlank(fEntity.getRecName())) {
			return "收款账户名称不能为空";
		}
		if (StringUtils.isBlank(fEntity.getRecBank())) {
			return "开户银行不能为空";
		}
		if (StringUtils.isBlank(fEntity.getRecAccount())) {
			return "收款账号不能为空";
		}
		if (StringUtils.isBlank(fEntity.getRecBankNum())) {
			return "银行行号不能为空";
		}
		return "success";
	}
}
