package cn.teacheredu.controller.payment;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.teacheredu.controller.Base2Controller;
import cn.teacheredu.entity.PaymentEntity;
import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.form.QueryTermsForm;
import cn.teacheredu.service.PaymentService;
import cn.teacheredu.utils.PageUtils;


@Controller
@RequestMapping(value = "/payMentList")
public class PayMentListController extends Base2Controller{
	
	@Autowired
	private PaymentService paymentService;
	
	/**
	 * 项目到款明细页面
	 * @param modelMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public String showApplyForInvoiceListPage(ModelMap modelMap, HttpServletRequest request) throws Exception{
		
		modelMap.put("projectId", request.getParameter("projectId"));
		return "payment/paymentList";
	}
	@RequestMapping(value="/paymentDataList",method=RequestMethod.GET)
	public String showApplyForInvoiceDataList(QueryTermsForm queryForm, ModelMap modelMap, HttpServletRequest request) throws Exception{
		UserEntity user = (UserEntity) modelMap.get("user");
		queryForm.setUserId(user.getId());
		PageUtils<PaymentEntity> paymentList = this.paymentService.getPayMentList(queryForm);
		modelMap.put("paymentList", paymentList);
		
		return "payment/paymentDataList";
	}
}