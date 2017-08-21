package cn.teacheredu.controller.admin;

import java.util.ArrayList;
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

import cn.teacheredu.entity.DepartmentEntity;
import cn.teacheredu.entity.RoleEntity;
import cn.teacheredu.entity.UserDepartmentEntity;
import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.service.DepartmentService;
import cn.teacheredu.service.RoleService;
import cn.teacheredu.service.SystemLogService;
import cn.teacheredu.service.UserDepartmentService;
import cn.teacheredu.service.UserService;
import cn.teacheredu.utils.CommonUtils;
import cn.teacheredu.utils.Md5Encrypt;
import cn.teacheredu.utils.PageUtils;
import cn.teacheredu.utils.SystemConst;
import cn.teacheredu.vo.UserDepartmentQueryVo;
import cn.teacheredu.vo.UserQueryVo;


@Controller("admin_user")
@RequestMapping(value = "/admin")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private UserDepartmentService userDepartmentService;
	
	@RequestMapping("/user")
	public String departmentPage(ModelMap modelMap ,HttpServletRequest request) {
		return "admin/user";
	}
	
	@ResponseBody
	@RequestMapping(value="/user/list")
	public Map<String, Object> doUserList(Integer page, Integer limit,HttpServletResponse response,HttpServletRequest request,HttpSession httpSession) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		UserQueryVo vo = new UserQueryVo();
		vo.setCurPage(page);
		vo.setStartPosition((page - 1) * limit);
		vo.setPageSize(limit);
		vo.setPaged(true);
		List<UserEntity> list = this.userService.getUserEntities(vo);
		for (UserEntity user : list) {
			Integer roleId = user.getRoleId();
			//Integer dmId = user.getDmId();
			Map<String, Object> extInfo = user.getExtInfo();
			if (extInfo == null)
				extInfo = new HashMap<String, Object>();
			if (roleId != null && roleId != 0) {
				RoleEntity role = this.roleService.getRoleById(roleId);
				extInfo.put("roleName", role == null? "":role.getRolename());
			}
		
			List<DepartmentEntity> dmlist = this.departmentService.getDepartmentByUserId(user.getId());
			String dmName = "";
			for (DepartmentEntity departmentEntity2 : dmlist) {
				dmName += "-"+departmentEntity2.getDmName();
			}
			extInfo.put("dmName", dmName);
			
			user.setExtInfo(extInfo);
		}
		long total = this.userService.getCountByVo(vo);
		PageUtils<UserEntity> pageUtil = new PageUtils<UserEntity>(list, total, limit, page);
		map.put("page", pageUtil);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/user/selectOne")
	public Map<String, Object> doSelectUserById(Integer id,HttpServletResponse response,HttpServletRequest request,HttpSession httpSession) throws Exception {
		logger.info("/user/selectOne/"+id);
		Map<String,Object> map = new HashMap<String,Object>();
		if (id != null) {
			UserEntity user = this.userService.getUserEntityById(id);
			//用户所属部门的处理
			UserDepartmentQueryVo vo = new UserDepartmentQueryVo();
			vo.setUserId(id);
			List<UserDepartmentEntity> list = this.userDepartmentService.getUserDepartmentByVo(vo);
			List<Integer> dmIdList = new ArrayList<Integer>();
			for (UserDepartmentEntity userDepartmentEntity : list) {
				dmIdList.add(userDepartmentEntity.getDmId());
			}
			user.setDmIdList(dmIdList);
			map.put("user", user);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/user/delete" ,method=RequestMethod.POST)
	public Map<String, Object> doDeleteUserById(@RequestBody int[] ids,HttpServletResponse response,HttpServletRequest request,HttpSession httpSession) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		if (ids == null || ids.length == 0) {
			map.put("result", false);
			map.put("msg", "删除失败!请勾选要删除的记录");
		} else {

//			boolean flag = true;
//			//这里以后加上删除前的验证,有流程跟要删的人挂钩的话不能删或者提示管理员是不是真要删
			
//			if (flag) {
				//int uid = (Integer) httpSession.getAttribute(SystemConst.SESSION_LOGIN_USER_ID);
				//for (int id : ids) {
					//int a = this.userService.deteleUserById(id);
					//if (a > 0) {
						//this.systemLogService.saveSystemLog(3, "user_"+id, uid);
					//} 
				//}
				//map.put("result", true);
//			}
			//为了保证数据不出问题,暂时不允许删除用户
			map.put("result", false);
			map.put("msg", "抱歉!为了保证数据一致性,不允许删除用户,请联系管理员处理..或者你可以选择禁用此用户。");
		}
		return map;
	}
	@ResponseBody
	@RequestMapping(value="/user/save",method=RequestMethod.POST)
	public Map<String, Object> doSaveUser(@RequestBody UserEntity user,HttpServletResponse response,HttpServletRequest request,HttpSession httpSession) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		if (StringUtils.isBlank(user.getLoginName())) {
			map.put("result", false);
			map.put("msg", "用户名不能为空");
		} else if (StringUtils.isBlank(user.getMobile())){
			map.put("result", false);
			map.put("msg", "手机号不能为空");
		} else if (StringUtils.isBlank(user.getRealname())){
			map.put("result", false);
			map.put("msg", "真实姓名不能为空");
		} else if (!CommonUtils.isPhoneLegal(user.getMobile())){
			map.put("result", false);
			map.put("msg", "手机号格式不正确");
		} else if (StringUtils.isBlank(user.getPassword()) || user.getPassword().length() < 6){
			map.put("result", false);
			map.put("msg", "密码格式不正确，长度必须大于6");
		} else {
			user.setLoginName(user.getLoginName().trim());
			UserEntity userv = this.userService.findByUsername(user.getLoginName());
			if (userv != null) {
				map.put("result", false);
				map.put("msg", "用户名重复！");
			} else {
				int uid = (Integer) httpSession.getAttribute(SystemConst.SESSION_LOGIN_USER_ID);
				user.setPassword(Md5Encrypt.md5(user.getPassword()));
				user.setGenTime(new Date());
				int a = this.userService.insertUserEntity(user);
				if (a > 0) {
					this.systemLogService.saveSystemLog(1, "user_"+user.toString(), uid);
				}
				map.put("result", true);
			}
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/user/update",method=RequestMethod.POST)
	public Map<String, Object> doUpdateUser(@RequestBody UserEntity user, HttpServletResponse response,HttpServletRequest request,HttpSession httpSession) throws Exception {
		
		Map<String,Object> map = new HashMap<String,Object>();
		if (StringUtils.isBlank(user.getLoginName())) {
			map.put("result", false);
			map.put("msg", "用户名不能为空");
		} else if (StringUtils.isBlank(user.getMobile())){
			map.put("result", false);
			map.put("msg", "手机号不能为空");
		} else if (StringUtils.isBlank(user.getRealname())){
			map.put("result", false);
			map.put("msg", "真实姓名不能为空");
		} else if (!CommonUtils.isPhoneLegal(user.getMobile())){
			map.put("result", false);
			map.put("msg", "手机号格式不正确");
		}  else {
			if (user.getId() == null || user.getId() == 0) {
				map.put("result", false);
				map.put("msg", "修改失败");
			} else {
				user.setLoginName(user.getLoginName().trim());
				UserEntity userOld = this.userService.getUserEntityById(user.getId());
				if (!userOld.getLoginName().equals(user.getLoginName())) {
					UserEntity userv = this.userService.findByUsername(user.getLoginName());
					if (userv != null) {
						map.put("result", false);
						map.put("msg", "用户名重复！");
						return map;
					}
				}
 				int uid = (Integer) httpSession.getAttribute(SystemConst.SESSION_LOGIN_USER_ID);
				if (user.getAvailable() == 1) {
					user.setDeleteTime(new Date());
				} else {
					user.setUpdateTime(new Date());
				}
				int a = this.userService.updateUser(user);
				if (a > 0) {
					if (!userOld.getRealname().equals(user.getRealname())) {
						this.userService.updateProcessNameByUserId(user.getId(), user.getRealname());
					}
					this.systemLogService.saveSystemLog(2, "user_"+user.toString(), uid);
				}
				map.put("result", true);
			}
		}
		return map;
	}
	
	
	
}
