package cn.teacheredu.controller.team;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.teacheredu.controller.Base2Controller;
import cn.teacheredu.entity.NeedDealProcessEntity;
import cn.teacheredu.entity.ProcessEntity;
import cn.teacheredu.entity.ProcessHistoryEntity;
import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.form.QueryTermsForm;
import cn.teacheredu.service.ProcessHistoryService;
import cn.teacheredu.service.ProcessService;
import cn.teacheredu.utils.CommonUtils;
import cn.teacheredu.utils.PageUtils;
import cn.teacheredu.utils.SystemConst;

@Controller
@RequestMapping(value = "/process")
public class ProcessController extends Base2Controller{
	@Autowired
	private ProcessService processService;
	@Autowired
	private ProcessHistoryService processHistoryService;
	
	
	/**
	 * 已发事项页面 
	 */
	@RequestMapping(value="/alreadySend",method=RequestMethod.GET)
	public String addProjectPage(ModelMap modelMap, HttpServletRequest request) throws Exception{
		@SuppressWarnings("unchecked")
		List<String> psList =  (List<String>) modelMap.get("psList");
		if (!CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_TEAM_ALREADYSEND, psList)) {
			modelMap.put("tipMsg", "抱歉...你没有进入已发事项的权限");
			return "tip";
		}
		return "process/alreadySend";
	}
	
	/**
	 * 已发事项页面数据列表
	 */
	@RequestMapping(value="/alreadySendDataList",method=RequestMethod.GET)
	public String getAlreadySendDataList(QueryTermsForm queryForm, ModelMap modelMap, HttpServletRequest request) throws Exception{
		UserEntity user = (UserEntity) modelMap.get("user");
		queryForm.setUserId(user.getId());
		PageUtils<ProcessEntity> alreadySend = this.processService.getAlreadySendByForm(queryForm);
		modelMap.put("alreadySend", alreadySend);
		return "process/alreadySendDataList";
	}
	
	/**
	 * 待办事项页面 
	 */
	@RequestMapping(value="/needDeal",method=RequestMethod.GET)
	public String getNeedDeal(ModelMap modelMap, HttpServletRequest request) throws Exception{
		@SuppressWarnings("unchecked")
		List<String> psList =  (List<String>) modelMap.get("psList");
		if (!CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_TEAM_NEEDDEAL, psList)) {
			modelMap.put("tipMsg", "抱歉...你没有进入待办事项的权限");
			return "tip";
		}
		return "process/needDeal";
	}
	
	/**
	 * 待办事项页面数据列表
	 */
	@RequestMapping(value="/needDealDataList",method=RequestMethod.GET)
	public String getNeedDealDataList(QueryTermsForm queryForm, ModelMap modelMap, HttpServletRequest request) throws Exception{
		UserEntity user = (UserEntity) modelMap.get("user");
		queryForm.setUserId(user.getId());
		PageUtils<NeedDealProcessEntity> needDeal = this.processService.getNeedDealByForm(queryForm);
		modelMap.put("needDeal", needDeal);
		return "process/needDealDataList";
	}
	
	/**
	 * 待Fa事项页面 
	 */
	@RequestMapping(value="/needSend",method=RequestMethod.GET)
	public String getNeedSend(ModelMap modelMap, HttpServletRequest request) throws Exception{
		@SuppressWarnings("unchecked")
		List<String> psList =  (List<String>) modelMap.get("psList");
		if (!CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_TEAM_NEEDSEND, psList)) {
			modelMap.put("tipMsg", "抱歉...你没有进入待发事项的权限");
			return "tip";
		}
		return "process/needSend";
	}
	
	/**
	 * 待发事项页面数据列表
	 */
	@RequestMapping(value="/needSendDataList",method=RequestMethod.GET)
	public String getNeedSendDataList(QueryTermsForm queryForm, ModelMap modelMap, HttpServletRequest request) throws Exception{
		UserEntity user = (UserEntity) modelMap.get("user");
		queryForm.setUserId(user.getId());
		PageUtils<ProcessEntity> needSend = this.processService.getNeedSendByForm(queryForm);
		modelMap.put("needSend", needSend);
		return "process/needSendDataList";
	}
	
	
	/**
	 * 已办事项页面 
	 */
	@RequestMapping(value="/alreadyDeal",method=RequestMethod.GET)
	public String getAlreadyDeal(ModelMap modelMap, HttpServletRequest request) throws Exception{
		@SuppressWarnings("unchecked")
		List<String> psList =  (List<String>) modelMap.get("psList");
		if (!CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_TEAM_ALREADYDEAL, psList)) {
			modelMap.put("tipMsg", "抱歉...你没有进入已办事项的权限");
			return "tip";
		}
		return "process/alreadyDeal";
	}
	
	/**
	 * 已办事项页面数据列表
	 */
	@RequestMapping(value="/alreadyDealDataList",method=RequestMethod.GET)
	public String getAlreadyDealDataList(QueryTermsForm queryForm, ModelMap modelMap, HttpServletRequest request) throws Exception{
		UserEntity user = (UserEntity) modelMap.get("user");
		queryForm.setUserId(user.getId());
		PageUtils<ProcessHistoryEntity> alreadyDeal = this.processHistoryService.getAlreadyDealByForm(queryForm);
		modelMap.put("alreadyDeal", alreadyDeal);
		return "process/alreadyDealDataList";
	}
	
}
