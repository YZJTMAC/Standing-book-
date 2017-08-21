package cn.teacheredu.controller.invoice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.teacheredu.controller.Base2Controller;
import cn.teacheredu.entity.PaymentInvoiceEntity;
import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.form.QueryTermsForm;
import cn.teacheredu.service.InvoiceService;
import cn.teacheredu.service.PaymentInvoiceService;
import cn.teacheredu.utils.PageUtils;


@Controller
@RequestMapping(value = "/applyForInvoiceList")
public class ApplyForInvoiceListController extends Base2Controller{
	
	@Autowired
	public InvoiceService invoiceService;
	
	@Autowired
	public PaymentInvoiceService paymentInvoiceService;
	
	/**
	 * 发票申请明细页面
	 * @param modelMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public String showApplyForInvoiceListPage(ModelMap modelMap, HttpServletRequest request) throws Exception{
		modelMap.put("projectId", request.getParameter("projectId"));
		return "invoice/applyForInvoiceList";
	}
	@RequestMapping(value="/applyForInvoiceDataList",method=RequestMethod.GET)
	public String showApplyForInvoiceDataList(QueryTermsForm queryForm, ModelMap modelMap, HttpServletRequest request) throws Exception{
		UserEntity user = (UserEntity) modelMap.get("user");
		queryForm.setUserId(user.getId());
		PageUtils<PaymentInvoiceEntity> paymentInvoicList = this.paymentInvoiceService.getPaymentInvoiceList(queryForm);
		modelMap.put("paymentInvoiceList", paymentInvoicList);
		
		return "invoice/applyForInvoiceDataList";
	}
}
