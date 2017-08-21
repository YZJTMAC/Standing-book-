package cn.teacheredu.controller.team;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.teacheredu.controller.Base2Controller;
import cn.teacheredu.utils.CommonUtils;
import cn.teacheredu.utils.SystemConst;


@Controller
@RequestMapping(value = "/processList")
public class ProcessListController extends Base2Controller{
	
	
	/**
	 * 项目信息-已发事项页面 
	 */
	@RequestMapping(value="/alreadySend",method=RequestMethod.GET)
	public String addProjectPage(ModelMap modelMap, HttpServletRequest request) throws Exception{
		@SuppressWarnings("unchecked")
		List<String> psList =  (List<String>) modelMap.get("psList");
		if (!CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_TEAM_ALREADYSEND, psList)) {
			modelMap.put("tipMsg", "抱歉...你没有进入已发事项的权限");
			return "tip";
		}
		return "process/alreadySendList";
	}
	/**
	 * 项目信息-待Fa事项页面 
	 */
	@RequestMapping(value="/needSend",method=RequestMethod.GET)
	public String getNeedSend(ModelMap modelMap, HttpServletRequest request) throws Exception{
		@SuppressWarnings("unchecked")
		List<String> psList =  (List<String>) modelMap.get("psList");
		if (!CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_TEAM_NEEDSEND, psList)) {
			modelMap.put("tipMsg", "抱歉...你没有进入待发事项的权限");
			return "tip";
		}
		return "process/needSendList";
	}
}
