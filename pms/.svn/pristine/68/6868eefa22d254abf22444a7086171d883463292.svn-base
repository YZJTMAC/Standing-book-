package cn.teacheredu.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.teacheredu.entity.MenuEntity;
import cn.teacheredu.entity.RoleEntity;
import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.service.MenuService;
import cn.teacheredu.service.RoleMenuService;
import cn.teacheredu.service.RoleService;
import cn.teacheredu.service.SystemLogService;
import cn.teacheredu.service.UserService;
import cn.teacheredu.utils.PageUtils;
import cn.teacheredu.utils.SystemConst;
import cn.teacheredu.vo.MenuQueryVo;
import cn.teacheredu.vo.RoleQueryVo;
import cn.teacheredu.vo.UserQueryVo;


@Controller
@RequestMapping(value = "/admin")
public class RoleController {

	private static Logger logger = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleMenuService roleMenuService;
	
	@RequestMapping("/role")
	public String departmentPage(ModelMap modelMap ,HttpServletRequest request) {
		return "admin/role";
	}
	
	@ResponseBody
	@RequestMapping(value="/role/list")
	public Map<String, Object> doRolePageList(Integer page, Integer limit,HttpServletResponse response,HttpServletRequest request,HttpSession httpSession) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		RoleQueryVo vo = new RoleQueryVo();
		vo.setCurPage(page);
		vo.setStartPosition((page - 1) * limit);
		vo.setPageSize(limit);
		vo.setPaged(true);
		List<RoleEntity> list = this.roleService.getRoleByVo(vo);
		for (RoleEntity role : list) {
			role.setMenuIdList(this.roleMenuService.queryMenuIdList(role.getId()));
		}
		long total = this.roleService.getCountByVo(vo);
		PageUtils<RoleEntity> pageUtil = new PageUtils<RoleEntity>(list, total, limit, page);
		map.put("page", pageUtil);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/role/list2")
	public Map<String, Object> doRoleList(HttpServletResponse response,HttpServletRequest request,HttpSession httpSession) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		RoleQueryVo vo = new RoleQueryVo();
		vo.setPaged(false);
		List<RoleEntity> list = this.roleService.getRoleByVo(vo);
		map.put("roleList", list);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/role/selectOne")
	public Map<String, Object> doSelectRoleById(Integer id,HttpServletResponse response,HttpServletRequest request,HttpSession httpSession) throws Exception {
		logger.info("/role/selectOne/"+id);
		Map<String,Object> map = new HashMap<String,Object>();
		if (id != null) {
			RoleEntity role = this.roleService.getRoleById(id);
			role.setMenuIdList(this.roleMenuService.queryMenuIdList(id));
			map.put("role", role);//角色信息
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/role/delete" ,method=RequestMethod.POST)
	public Map<String, Object> doDeleteRoleById(@RequestBody int[] ids,HttpServletResponse response,HttpServletRequest request,HttpSession httpSession) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		if (ids == null || ids.length == 0) {
			map.put("result", false);
			map.put("msg", "删除失败!请勾选要删除的记录");
		} else {
			UserQueryVo vo = new UserQueryVo();
			boolean flag = true;
			for (int id : ids) {
				vo.setRoleId(id);
				List<UserEntity> userList = this.userService.getUserEntities(vo);
				if (userList != null && userList.size() > 0) {
					map.put("result", false);
					map.put("msg", "删除失败，该角色下还存在用户呢！");
					flag = false;
					break;
				}
			}
			if (flag) {
				int uid = (Integer) httpSession.getAttribute(SystemConst.SESSION_LOGIN_USER_ID);
				for (int id : ids) {
					int a = this.roleService.deteleRoleById(id);
					if (a > 0) {
						this.systemLogService.saveSystemLog(3, "role_"+id, uid);
					} 
				}
				map.put("result", true);
			}
		}
		return map;
	}
	@ResponseBody
	@RequestMapping(value="/role/save",method=RequestMethod.POST)
	public Map<String, Object> doSaveRole(@RequestBody RoleEntity role,HttpServletResponse response,HttpServletRequest request,HttpSession httpSession) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		if (StringUtils.isBlank(role.getRolename())) {
			map.put("result", false);
			map.put("msg", "角色名称不能为空");
		} else {
			int uid = (Integer) httpSession.getAttribute(SystemConst.SESSION_LOGIN_USER_ID);
			role.setGenTime(new Date());
			int a = this.roleService.insertRoleEntity(role);
			if (a > 0) {
				this.systemLogService.saveSystemLog(1, "role_"+role.toString(), uid);
			}
			map.put("result", true);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/role/update",method=RequestMethod.POST)
	public Map<String, Object> doUpdateDepartment(@RequestBody RoleEntity role, HttpServletResponse response,HttpServletRequest request,HttpSession httpSession) throws Exception {
		
		Map<String,Object> map = new HashMap<String,Object>();
		if (StringUtils.isBlank(role.getRolename())) {
			map.put("result", false);
			map.put("msg", "角色名称不能为空");
		} else {
			if (role.getId() == null || role.getId() == 0) {
				map.put("result", false);
				map.put("msg", "修改失败");
			} else {
				int uid = (Integer) httpSession.getAttribute(SystemConst.SESSION_LOGIN_USER_ID);

				int a = this.roleService.updateRole(role);
				if (a > 0) {
					this.systemLogService.saveSystemLog(2, "role_"+role.toString(), uid);
				}
				map.put("result", true);
			}
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/role/menu")
	public Map<String, Object> doSelectRoleMenu(HttpServletResponse response,HttpServletRequest request,HttpSession httpSession) throws Exception {
		
		Map<String,Object> map = new HashMap<String,Object>();
		MenuQueryVo vo = new MenuQueryVo();
		vo.setOrderProperty("order_num");
		vo.setOrderType("asc");
		List<MenuEntity> menuList = this.menuService.getMenuByVo(vo);
		for (MenuEntity menuEntity : menuList) {
			menuEntity.setIcon(null);
			menuEntity.setUrl(null);
		}
		map.put("menuList", menuList);
		return map;
	}
	
}
