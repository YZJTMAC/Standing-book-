package cn.teacheredu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.teacheredu.entity.CompanyEntity;
import cn.teacheredu.entity.DepartmentEntity;
import cn.teacheredu.entity.MenuEntity;
import cn.teacheredu.entity.NeedDealProcessEntity;
import cn.teacheredu.entity.ProcessEntity;
import cn.teacheredu.entity.ProcessHistoryEntity;
import cn.teacheredu.entity.ProjectEntity;
import cn.teacheredu.entity.ShortcutEntity;
import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.service.CompanyService;
import cn.teacheredu.service.DepartmentService;
import cn.teacheredu.service.MenuService;
import cn.teacheredu.service.ProcessHistoryService;
import cn.teacheredu.service.ProcessService;
import cn.teacheredu.service.ProjectService;
import cn.teacheredu.service.ShortcutService;
import cn.teacheredu.service.UserService;
import cn.teacheredu.utils.CommonUtils;
import cn.teacheredu.utils.PageUtils;
import cn.teacheredu.utils.R;
import cn.teacheredu.utils.SpyMemcachedManager;
import cn.teacheredu.utils.SystemConst;
import cn.teacheredu.vo.ShortcutQueryVo;

@SuppressWarnings("unused")
@Controller
public class IndexController extends Base2Controller{
	
	private static Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private MenuService menuService;
	@Autowired
	private ShortcutService shortcutService;
	@Autowired
	private SpyMemcachedManager memcachedManager;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private ProcessService processService;
	@Autowired
	private ProcessHistoryService processHistoryService;
	@Autowired
	private CompanyService companyService;
//	@Autowired
//	private UserService userService;
//	@Autowired
//	private DepartmentService departmentService;
	
	/**
	 * 访问主页
	 * @param modelMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String indexPage(ModelMap modelMap, HttpServletRequest request) throws Exception {
		UserEntity user = (UserEntity) request.getSession().getAttribute(SystemConst.SESSION_LOGIN_USER);
//		if (user == null) {
//			try {
//				user = userService.findByUsername(request.getParameter("name") == null ? "gdyw" :request.getParameter("name") );
//				HttpSession httpSession = request.getSession();
//				httpSession.setAttribute(SystemConst.SESSION_LOGIN_USER, user);
//				httpSession.setAttribute(SystemConst.SESSION_LOGIN_USER_ID, user.getId());
//				httpSession.setAttribute(SystemConst.SESSION_LOGIN_ROLE_ID, user.getRoleId());
//				List<DepartmentEntity> dmList = this.departmentService.getDepartmentByUserId(user.getId());
//				httpSession.setAttribute(SystemConst.SESSION_LOGIN_DM, dmList);
//				List<String> psList = this.menuService.getPsListByRoleId(user.getRoleId());
//				httpSession.setAttribute(SystemConst.SESSION_LOGIN_USER_PS, psList);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		System.out.print("======>Session本次访问时间："+DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
//		System.out.println("======>创建时间："+DateFormatUtils.format(request.getSession().getCreationTime(),"yyyy-MM-dd HH:mm:ss"));
//		System.out.println("======>Session上次访问时间："+DateFormatUtils.format(request.getSession().getLastAccessedTime(),"yyyy-MM-dd HH:mm:ss"));
//		modelMap.put("user", user);
		if (user == null) {
			return "login";
		} else if ("admin".equals(user.getLoginName())) {
			return "admin/index";//后台管理首页
		} else{
			logger.info("=====index-start=====" + user.getLoginName() + "=====" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
			// 1.查询用户设置的快捷菜单
			List<MenuEntity> shortcutList = this.menuService.getMenuListByUid(user.getId());
			modelMap.put("scList", shortcutList);
			
			// 2.查询我的项目
			PageUtils<ProjectEntity> proList = null;
			// 得到权限list
			@SuppressWarnings("unchecked")
			List<String> psList =  (List<String>) request.getSession().getAttribute(SystemConst.SESSION_LOGIN_USER_PS);
			modelMap.put("psList", psList);
			boolean hasOtherPri = CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_QUERY_OTHERPRIVINCE, psList);
			boolean hasOtherPer = CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_QUERY_OTHERPERSION, psList);
			boolean hasCompany = CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_QUERY_COMPANY, psList);
			if (hasOtherPri) { //只要其给角色配了查询其他省份项目这个权限，那就查询全部的
				proList = this.projectService.getMyProjectForIndexPage(user.getId(), null, null,1);
			} else if (hasOtherPer) { //只要给角色配置了查询其他人的权限，那就是查询本部门所在省份的项目，一般用于办事处负责人
				//此处有两种类型，一种是分管大区的部门的用户 一种是普通办事处、分公司、子公司类型的部门
				@SuppressWarnings("unchecked")
				List<DepartmentEntity> dmList = (List<DepartmentEntity>) request.getSession().getAttribute(SystemConst.SESSION_LOGIN_DM);
				if (dmList.size() > 0 && dmList.get(0).getProvince() < 0) {
					proList = this.projectService.getMyProjectForIndexPage(user.getId(), dmList, null, 4);
				} else {
					proList = this.projectService.getMyProjectForIndexPage(user.getId(), dmList, null, 2);
				}
			} else if (hasCompany) { //查询指定公司的项目
				List<String> companyList = new ArrayList<String>();//定义一个list存放用户可以查询哪些公司的项目
				for (String perms : psList) {
					if (perms.contains(SystemConst.PERMISSION_QUERY_COMPANY) && !perms.equals(SystemConst.PERMISSION_QUERY_COMPANY)) {
						CompanyEntity companyEntity = this.companyService.getCompanyById(Integer.parseInt(perms.substring(SystemConst.PERMISSION_QUERY_COMPANY.length()+1)));
						if (companyEntity != null)
							companyList.add(companyEntity.getName());
					}
				}
				proList = this.projectService.getMyProjectForIndexPage(user.getId(), null, companyList, 5);
			} else { //只查询自己发起的项目 （配不配权限起码都得查询自己发起的）
				proList = this.projectService.getMyProjectForIndexPage(user.getId(), null, null, 3);
			} 
			modelMap.put("proList", proList);
			
			// 3.查询待办事项
			if (CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_TEAM_NEEDDEAL, psList)) {
				PageUtils<NeedDealProcessEntity> needDealList = this.processService.getNeedProcessByUserId(user.getId(), 1, 6);
				modelMap.put("needDealList", needDealList);
			}
			
			// 4.查询已办事项
			if (CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_TEAM_ALREADYDEAL, psList)) {
				PageUtils<ProcessHistoryEntity> alreadyDealList = this.processHistoryService.getAleadyDoneByUserId(user.getId(), 1, 6);
				modelMap.put("alreadyDealList", alreadyDealList);
			}
			
			// 5.查询待发事项
			if (CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_TEAM_NEEDSEND, psList)) {
				PageUtils<ProcessEntity> needSend = this.processService.getNeedSendProcessByUserId(user.getId(), -1, 1, 6);
				modelMap.put("needSend", needSend);
			}
			
			// 6.查询已发事项
			if (CommonUtils.hasPriByFunCode(SystemConst.PERMISSION_TEAM_ALREADYSEND, psList)) {
				PageUtils<ProcessEntity> alreadySend = this.processService.getNeedSendProcessByUserId(user.getId(), -2, 1, 6);
				modelMap.put("alreadySend", alreadySend);
			}
			
			// 7.查询消息
			// 先不在这查询，考虑使用ajax页面加载完后异步请求获取消息
			logger.info("=====index-end=====" + user.getLoginName() + "=====" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
			return "index";//前台主页
		}
	}
	
	/**
	 * 自定义主页快捷工具
	 * @param menuId
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/index/editShortcut",method=RequestMethod.POST)
	public @ResponseBody R editShortcut(Integer menuId, ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception{
    	if (menuId == null) {
			return R.error("添加失败，ID为空");
		} else {
			
			Integer uid = (Integer) request.getSession().getAttribute(SystemConst.SESSION_LOGIN_USER_ID);
			memcachedManager.delete("pms_menu_shortcut"+uid);
			ShortcutQueryVo vo = new ShortcutQueryVo();
			vo.setUid(uid);
			vo.setMenuId(menuId);
			List<ShortcutEntity> scList = this.shortcutService.getShortcutByVo(vo);
			if (scList != null && scList.size() > 0) {
				this.shortcutService.deteleShortcutById(scList.get(0).getId());
				return R.ok("删除成功");
			} else {
				vo.setMenuId(null);
				scList = this.shortcutService.getShortcutByVo(vo);
				if (scList.size() > 6) {
					return R.error("添加失败，最多只能有7个。");
				}
				ShortcutEntity shortcutEntity = new ShortcutEntity();
				shortcutEntity.setUid(uid);
				shortcutEntity.setMenuId(menuId);
				this.shortcutService.insertShortcutEntity(shortcutEntity);
				return R.ok("添加成功");
			}
			
			
		}
		
	}
	

}
