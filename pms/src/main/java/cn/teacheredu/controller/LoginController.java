package cn.teacheredu.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.teacheredu.controller.config.SysConfig;
import cn.teacheredu.entity.DepartmentEntity;
import cn.teacheredu.entity.LoginLogEntity;
import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.service.DepartmentService;
import cn.teacheredu.service.LoginLogService;
import cn.teacheredu.service.MenuService;
import cn.teacheredu.service.SystemLogService;
import cn.teacheredu.service.UserService;
import cn.teacheredu.utils.CommonUtils;
import cn.teacheredu.utils.Md5Encrypt;
import cn.teacheredu.utils.RDSMS;
import cn.teacheredu.utils.SystemConst;
import cn.teacheredu.utils.ValidCodeTool;

/**
 * 登录、修改密码
 * @author Zhaojie
 *
 */
@Controller
@RequestMapping(value = "/")
public class LoginController{

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private LoginLogService loginLogService;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private SysConfig sysConfig;
	/**
	 * 用户退出
	 * @param response
	 * @param request
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("/layout")
	public String doLayout(HttpServletResponse response,HttpServletRequest request,HttpSession httpSession) {
		httpSession.invalidate();
		return "redirect:/index";
	}
	
	/**
	 * 用户登录，验证用户名和密码
	 * @param username
	 * @param password
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public Map<String, Object> doLogin(@RequestParam("username") String username,@RequestParam("password") String password, ModelMap modelMap ,HttpServletRequest request) {
		logger.info("login=====>"+username);
		Map<String,Object> map = new HashMap<String,Object>();
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			map.put("result", false);
			map.put("msg", "用户名或密码不能为空!");
		} else {
			try {
				UserEntity user = userService.findByUsername(username);
				if (user == null || !user.getPassword().equals(Md5Encrypt.md5(password))) {
					map.put("result", false);
					map.put("msg","用户名或密码错误!");
				} else { // 密码验证已通过
					if (user.getAvailable() == 1) {
						map.put("result", false);
						map.put("msg","您的账户已被禁用!");
						return map;
					}
					if (!sysConfig.needPhoneValid) { //不需要手机验证即可登录
						LoginLogEntity loginLogEntity = new LoginLogEntity();
						loginLogEntity.setUid(user.getId());
						loginLogEntity.setName(user.getLoginName());
						loginLogEntity.setLoginTime(new Date());
						loginLogEntity.setLoginIp(CommonUtils.getIpAddress(request));
						loginLogEntity.setSucceed(1);
						this.loginLogService.insertLoginLogEntity(loginLogEntity);
						HttpSession httpSession = request.getSession();
						httpSession.setAttribute(SystemConst.SESSION_LOGIN_USER, user);
						httpSession.setAttribute(SystemConst.SESSION_LOGIN_USER_ID, user.getId());
						httpSession.setAttribute(SystemConst.SESSION_LOGIN_ROLE_ID, user.getRoleId());
						List<DepartmentEntity> dmList = this.departmentService.getDepartmentByUserId(user.getId());
						httpSession.setAttribute(SystemConst.SESSION_LOGIN_DM, dmList);
						List<String> psList = this.menuService.getPsListByRoleId(user.getRoleId());
						httpSession.setAttribute(SystemConst.SESSION_LOGIN_USER_PS, psList);
						
						map.put("result", true);
						map.put("success", true);
					} else { //需要手机短信验证
						String phone = user.getMobile();
						if (!CommonUtils.isPhoneLegal(phone)) {
							map.put("result", false);
							map.put("msg","您预留的手机号码有误，暂时不能登录系统，请联系管理员。");
						} else {
							String validCode = ValidCodeTool.getValidCode(6, false);
							String result = RDSMS.SendSMS(phone, "【大视野教育集团】您好，您当前登录的验证码："+ validCode+"。有效期5分钟。");
					        JsonNode node = new ObjectMapper().readTree(result).get("result");
					        result = node.toString();
							if ("0".equals(result)) {
								//登录日志表添加一条记录
								LoginLogEntity loginLogEntity = new LoginLogEntity();
								loginLogEntity.setUid(user.getId());
								loginLogEntity.setName(user.getLoginName());
								loginLogEntity.setLoginTime(new Date());
								loginLogEntity.setLoginIp(CommonUtils.getIpAddress(request));
								loginLogEntity.setVerificationCode(validCode);
								loginLogEntity.setSucceed(0);
								Integer logId = this.loginLogService.insertLoginLogEntity(loginLogEntity);
								map.put("logId", logId);
								map.put("phone", "手机"+phone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
								map.put("result", true);
								map.put("msg","短信验证码已发送到您的手机(" + phone + "),填写验证码后即可登录系统。");
							} else {
								map.put("result", false);
								map.put("msg","抱歉！短信发送服务出现异常，无法登录系统，请联系管理员。");
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				map.put("result", false);
				map.put("msg","出错了："+e.getMessage());
			}
		}
		return map;
	}
	
	// 重发验证码
	@ResponseBody
	@RequestMapping(value="/sendCode",method=RequestMethod.POST)
	public Map<String, Object> doSendCode(@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("logId") Integer logId, ModelMap modelMap ,HttpServletRequest request) {
		logger.info("sendCode=====>"+username);
		Map<String,Object> map = new HashMap<String,Object>();
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || logId == null || logId <= 0) {
			map.put("result", false);
			map.put("msg", "发送失败，建议刷新页面，然后重新输入用户名和密码再试！");
		} else {
			try {
				UserEntity user = userService.findByUsername(username);
				if (user == null || !user.getPassword().equals(Md5Encrypt.md5(password))) {
					map.put("result", false);
					map.put("msg", "发送失败，建议刷新页面，然后重新输入用户名和密码再试！");
				} else { // 密码验证已通过
					String phone = user.getMobile();
					if (!CommonUtils.isPhoneLegal(phone)) {
						map.put("result", false);
						map.put("msg", "发送失败，建议刷新页面，然后重新输入用户名和密码再试！");
					} else {
						String validCode = ValidCodeTool.getValidCode(6, false);
						String result = RDSMS.SendSMS(phone, "【大视野教育集团】您好，您当前登录的验证码："+ validCode+"。有效期5分钟。");
				        JsonNode node = new ObjectMapper().readTree(result).get("result");
				        result = node.toString();
						if ("0".equals(result)) {
							//登录日志表添加一条记录
							LoginLogEntity loginLogEntity = this.loginLogService.getLoginLogById(logId);
							if (loginLogEntity == null) {
								map.put("result", false);
								map.put("msg", "发送失败，建议刷新页面，然后重新输入用户名和密码再试！");
							}
							loginLogEntity.setVerificationCode(validCode);
							this.loginLogService.updateLoginLog(loginLogEntity);
							map.put("result", true);
						} else {
							map.put("result", false);
							map.put("msg","发送失败！短信发送服务出现异常，请联系管理员。");
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				map.put("result", false);
				map.put("msg","出错了："+e.getMessage());
			}
		}
		return map;
	}
	
	/**
	 * 用户登录  验证手机验证码
	 * @param code
	 * @param logId
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/login_real",method=RequestMethod.POST)
	public Map<String, Object> doRealLogin(@RequestParam("code") String code, @RequestParam("logId") Integer logId,ModelMap modelMap ,HttpServletRequest request) {
		logger.info("realLogin=====>"+logId);
		Map<String,Object> map = new HashMap<String,Object>();
		if (StringUtils.isBlank(code) || logId == null || logId < 1) {
			map.put("result", false);
			map.put("msg", "手机验证码校验失败!请联系管理员！");
		} else {
			try {
				LoginLogEntity loginLogEntity = this.loginLogService.getLoginLogById(logId);
				if (loginLogEntity == null) {
					map.put("result", false);
					map.put("msg", "手机验证码校验失败,重新获取再试一下吧！");
				} else {
					Date date = DateUtils.addMinutes(loginLogEntity.getLoginTime(), 5);
					if (!code.equals(loginLogEntity.getVerificationCode())) {
						map.put("result", false);
						map.put("msg", "您输入的手机验证码错误！");
					} else if (new Date().after(date)) {
						map.put("result", false);
						map.put("msg", "手机验证码已失效，请重新获取！");
					} else {
						UserEntity user = userService.findByUsername(loginLogEntity.getName());
						if (user == null) {
							map.put("result", false);
							map.put("msg","未查到您的用户信息，请联系管理员！");
						} else {
							loginLogEntity.setSucceed(1);
							this.loginLogService.updateLoginLog(loginLogEntity);
							HttpSession httpSession = request.getSession();
							httpSession.setAttribute(SystemConst.SESSION_LOGIN_USER, user);
							httpSession.setAttribute(SystemConst.SESSION_LOGIN_USER_ID, user.getId());
							httpSession.setAttribute(SystemConst.SESSION_LOGIN_ROLE_ID, user.getRoleId());
							List<DepartmentEntity> dmList = this.departmentService.getDepartmentByUserId(user.getId());
							httpSession.setAttribute(SystemConst.SESSION_LOGIN_DM, dmList);
							List<String> psList = this.menuService.getPsListByRoleId(user.getRoleId());
							httpSession.setAttribute(SystemConst.SESSION_LOGIN_USER_PS, psList);
							map.put("result", true);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				map.put("result", false);
				map.put("msg","出错了："+e.getMessage());
			}
		}
		return map;
	}
	
	/**
	 * 修改密码功能
	 * @param password
	 * @param newPassword
	 * @param response
	 * @param request
	 * @param httpSession
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/updatePassword")
	public Map<String, Object> doRoleList(String password, String newPassword,HttpServletResponse response,HttpServletRequest request,HttpSession httpSession) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		if (StringUtils.isBlank(newPassword) || newPassword.length() < 6) {
			map.put("result", false);
			map.put("msg", "修改失败，新密码格式不正确，长度至少6位");
		} else {
			UserEntity user = (UserEntity) request.getSession().getAttribute(SystemConst.SESSION_LOGIN_USER);
			if (user == null) {
				map.put("result", false);
				map.put("msg", "修改失败，未查到您的用户信息，重新登录再试.");
			} else if (!user.getPassword().equals(Md5Encrypt.md5(password))){
				map.put("result", false);
				map.put("msg", "修改失败，原密码不正确.");
			} else {
				user.setPassword(Md5Encrypt.md5(newPassword));
				user.setUpdateTime(new Date());
				int a = this.userService.updateUser(user);
				if (a > 0) {
					this.systemLogService.saveSystemLog(2, "change-password", user.getId());
				}
				map.put("result", true);
			}
		}
		return map;
	}
}
