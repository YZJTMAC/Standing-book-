package cn.teacheredu.controller.funds;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.teacheredu.controller.Base2Controller;
import cn.teacheredu.entity.FundsEntity;
import cn.teacheredu.entity.FundsPayEntity;
import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.form.QueryTermsForm;
import cn.teacheredu.service.FundsPayService;
import cn.teacheredu.service.FundsService;
import cn.teacheredu.service.UserService;
import cn.teacheredu.service.impl.UserServiceImpl;
import cn.teacheredu.utils.CommonUtils;
import cn.teacheredu.utils.PageUtils;
import cn.teacheredu.utils.R;
import cn.teacheredu.utils.SystemConst;
import cn.teacheredu.vo.FundsQueryVo;


@Controller
@RequestMapping(value = "/applyForFundsList")
public class ApplyForFundsListController extends Base2Controller{
	
	@Autowired
	private FundsService fundsService;
	@Autowired
	private FundsPayService fundsPayService;
	@Autowired
	private UserService userService;
	
	/**
	 * 经费申请明细页面
	 * @param modelMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public String showApplyForInvoiceListPage(ModelMap modelMap, HttpServletRequest request) throws Exception{
		modelMap.put("projectId", request.getParameter("projectId"));
		return "funds/applyForFundsList";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/applyForFundsDataList",method=RequestMethod.GET)
	public String showApplyForInvoiceDataList(QueryTermsForm queryForm, ModelMap modelMap, HttpServletRequest request) throws Exception{
		UserEntity user = (UserEntity) modelMap.get("user");
		queryForm.setUserId(user.getId());
		PageUtils<FundsEntity> fundsList = this.fundsService.getFundsList(queryForm);
		for(FundsEntity fund : fundsList.getList()){
			double payAmount = fundsPayService.sumPayByFundId(fund.getId());
			fund.setPayAmount(new BigDecimal(payAmount));
		}
		modelMap.put("fundsList", fundsList);
		modelMap.put("operator",user.getRealname());
		List<String> psList =  (List<String>) modelMap.get("psList");
		if (!CommonUtils.hasPriByFunCode(SystemConst.PAY_FUND, psList)) {
			modelMap.put("canPay",0);
		}else{
			modelMap.put("canPay",1);
		}
		return "funds/applyForFundsDataList";
	}
	@RequestMapping(value="/payFund")
	public @ResponseBody R ajaxPayFund(String fundId, String amount, ModelMap modelMap) throws Exception{
		String message = "";
		double pay = Double.valueOf(amount);
		int id = Integer.parseInt(fundId);
		FundsQueryVo vo = new FundsQueryVo();
		vo.setId(id);
		FundsEntity fund = fundsService.getFundsById(id);
		if(fund!=null){
			BigDecimal applyAmount = fund.getApplyAmount();
			double sumAmount = fundsPayService.sumPayByFundId(id);
			if(pay<=0){
				return R.error(1, "本次的付款金额不能小于 0 ，请重新输入");
			}else if((sumAmount+pay)>applyAmount.doubleValue()){
				return R.error(2, "本次的付款金额不能大于未付款金额，请重新输入");
			}else if((sumAmount+pay) <= applyAmount.doubleValue()){
				FundsPayEntity fundsPay = new FundsPayEntity();
				fundsPay.setFundsId(id);
				fundsPay.setPayTime(new Date());
				fundsPay.setPayMoney(new BigDecimal(amount));
				UserEntity user = (UserEntity)modelMap.get("user");
				fundsPay.setPayOperatorId(user.getId());
				fundsPayService.insertFundsPay(fundsPay);
				if((sumAmount+pay) == applyAmount.intValue()){
					fundsService.editWonPayById(id, "3");
					message = "已完成";
				}else{
					fundsService.editWonPayById(id, "2");
					double paying = sumAmount+pay;
					message = "已付款("+paying+")";
				}
			}
		}
		return R.ok(message);
	}
	
	@RequestMapping(value="/fundsPayTable")
	public String ajaxfundsPayTable(String fundId, ModelMap modelMap, HttpServletRequest request) throws Exception{
		Integer fid = Integer.valueOf(fundId);
		List<FundsPayEntity> fundPay = fundsPayService.getFundPay(fid);
		for(FundsPayEntity fp:fundPay){
			Integer userId = fp.getPayOperatorId();
			fp.setPayOperatorRealName(userService.getUserEntityById(userId).getRealname());
		}
		modelMap.put("funds", fundPay);
		return "funds/fundsPayTable";
	}
}
