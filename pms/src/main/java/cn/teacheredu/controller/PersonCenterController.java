package cn.teacheredu.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.teacheredu.entity.DepartmentEntity;
import cn.teacheredu.entity.RoleEntity;
import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.form.UserForm;
import cn.teacheredu.service.DepartmentService;
import cn.teacheredu.service.RoleService;
import cn.teacheredu.service.SystemLogService;
import cn.teacheredu.service.UserService;
import cn.teacheredu.utils.CommonUtils;
import cn.teacheredu.utils.Md5Encrypt;
import cn.teacheredu.utils.R;
import cn.teacheredu.utils.RDSMS;
import cn.teacheredu.utils.SystemConst;
import cn.teacheredu.utils.ValidCodeTool;

@Controller
@RequestMapping(value = "/personCenter")
public class PersonCenterController extends Base2Controller {

	private static Logger logger = LoggerFactory.getLogger(PersonCenterController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private RoleService roleService;

	/**
	 * 到“我的资料”页面
	 * 
	 * @param modelMap
	 * @param request
	 */
	@RequestMapping(value = "/toMymsg", method = RequestMethod.GET)
	public String toMymsg(ModelMap modelMap, HttpServletRequest request) throws Exception {
		UserEntity user = (UserEntity) modelMap.get("user");
		logger.info("=====/personConter/mymsg=====" + user.getLoginName());
		List<DepartmentEntity> dmlist = this.departmentService.getDepartmentByUserId(user.getId());
		String dmName = "";
		for (DepartmentEntity departmentEntity : dmlist) {
			dmName += departmentEntity.getDmName()+",";
		}
		modelMap.put("dmName", dmName.substring(0, dmName.length()-1));
		RoleEntity role = roleService.getRoleById(user.getRoleId());
		modelMap.put("roleName", role == null ? "" : role.getRolename());
		return "person/mymsg";
	}

	/**
	 * 修改个人资料
	 */
	@RequestMapping(value = "/updateMymsg", method = RequestMethod.POST)
	public @ResponseBody R updateMymsg(@RequestBody UserEntity myUser, ModelMap modelMap, HttpServletRequest request) throws Exception {

		UserEntity user = (UserEntity) modelMap.get("user");
		myUser.setUpdateTime(new Date());
		int updateUser = this.userService.updateMyUser(myUser);
		if (updateUser > 0) {
			systemLogService.saveSystemLog(2, "updateMymsg_" + user.toString(), user.getId());
			UserEntity userEntity = this.userService.getUserEntityById(myUser.getId());
			if (userEntity != null) {
				request.getSession().setAttribute(SystemConst.SESSION_LOGIN_USER, userEntity);
			}
			return R.ok();
		} else {
			return R.error("未知错误！");
		}
	}

	/**
	 * 到“账号安全”页面
	 * 
	 * @param modelMap
	 * @param request
	 */
	@RequestMapping(value = "/toSafecenter", method = RequestMethod.GET)
	public String toSafecenter(ModelMap modelMap, HttpServletRequest request) throws Exception {
		UserEntity user = (UserEntity) modelMap.get("user");
		logger.info("=====/personConter/safecenter=====" + user.getLoginName());
		return "person/safecenter";
	}

	/**
	 * 修改密码
	 * 
	 * @param password
	 * @param newPwd
	 * @param response
	 * @param request
	 * @param httpSession
	 */
	@RequestMapping(value = "/modifyPwd", method = RequestMethod.POST)
	public @ResponseBody R doModifyPwd(@RequestBody UserForm userForm, ModelMap modelMap) throws Exception {
		UserEntity user = (UserEntity) modelMap.get("user");
		String password = userForm.getPassword();
		String newPwd = userForm.getNewPwd();
		String againPwd = userForm.getAgainPwd();
		
		
		if (StringUtils.isBlank(newPwd) || newPwd.length() < 6) {
			return R.error("修改失败，新密码格式不正确，长度至少6位");
		} else {
			if (user == null) {
				return R.error("修改失败，未查到您的用户信息，重新登录再试.");
			} else if (!newPwd.equals(againPwd)) {
				return R.error("修改失败，两次输入的新密码不一致.");
			} else if (!user.getPassword().equals(Md5Encrypt.md5(password))) {
				return R.error("修改失败，原密码不正确.");
			} else {
				user.setPassword(Md5Encrypt.md5(newPwd));
				user.setUpdateTime(new Date());
				int updateMyUser = this.userService.updateMyUser(user);
				if (updateMyUser > 0) {
					this.systemLogService.saveSystemLog(2, "modify-password", user.getId());
				} else {
					return R.error("未知错误");
				}
			}
		}
		return R.ok();
	}
	
	/**
	 * 修改手机号-发送验证码
	 */
	@RequestMapping(value = "/modifyMobile", method = RequestMethod.POST)
	public @ResponseBody R modifyMobile(@RequestBody UserForm userForm, ModelMap modelMap, HttpServletRequest request) throws Exception{
		String phone = userForm.getMobile();
		if (!CommonUtils.isPhoneLegal(phone)) {
			return R.error("您输入的手机号码有误，请重新输入。");
		}
		String validCode = ValidCodeTool.getValidCode(6, false); // 获取验证码
		String result = RDSMS.SendSMS(phone, "【大视野教育集团】您好，您当前修改手机号的验证码为："+ validCode+"。有效期5分钟。");
		JsonNode node = new ObjectMapper().readTree(result).get("result");
		result = node.toString();
		if (!"0".equals(result)) {
			return R.error("短信服务调用失败，请联系管理员");
		}
		request.getSession().setAttribute(SystemConst.SESSION_VALIDCODE, validCode);
		//System.out.println("您的验证码为：" + validCode);
		return R.ok();
	}
	
	/**
	 * 修改手机号-提交验证码
	 */
	@RequestMapping(value = "/subValidCode", method = RequestMethod.POST)
	public @ResponseBody R subValidCode(@RequestBody UserForm userForm, ModelMap modelMap, HttpServletRequest request) throws Exception{
		UserEntity user = (UserEntity) modelMap.get("user");
		String phone = userForm.getMobile();
		String validCode = (String) request.getSession().getAttribute(SystemConst.SESSION_VALIDCODE);
		String validCode2 = userForm.getValidCode(); // 输入的验证码
		if (StringUtils.isBlank(validCode2) || !validCode2.equals(validCode)) {
			return R.error("验证码不正确！");
		}
		user.setMobile(phone);
		user.setUpdateTime(new Date());
		int updateUser = userService.updateMyUser(user);
		if (updateUser > 0) {
			this.systemLogService.saveSystemLog(2, "modify-phone", user.getId());
		}
		return R.ok();
	}
}
