package cn.teacheredu.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import cn.teacheredu.entity.DepartmentEntity;
import cn.teacheredu.entity.MenuEntity;
import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.service.MenuService;
import cn.teacheredu.utils.SystemConst;

/**
 * 适合使用layout.vm作为layout的页面所对应的controller来继承
 * @author Zhaojie
 *
 */
@Controller
public class Base2Controller {
	
	@Autowired
	private MenuService menuService;
	
	
	@ModelAttribute  
    public void populateModel(ModelMap modelMap ,HttpServletRequest request) throws Exception {  
		Integer roleId = (Integer) request.getSession().getAttribute(SystemConst.SESSION_LOGIN_ROLE_ID);
		if (roleId != null) {
			Map<String, List<MenuEntity>> menuMap = this.menuService.getMenuMapByRoleId(roleId);
			modelMap.put("menuMap", menuMap);
		}
		UserEntity user = (UserEntity) request.getSession().getAttribute(SystemConst.SESSION_LOGIN_USER);
		if (user != null) {
			modelMap.put("user", user);
			@SuppressWarnings("unchecked")
			List<DepartmentEntity> dmList = (List<DepartmentEntity>) request.getSession().getAttribute(SystemConst.SESSION_LOGIN_DM);
			modelMap.put("dmList", dmList);
			@SuppressWarnings("unchecked")
			List<String> psList =  (List<String>) request.getSession().getAttribute(SystemConst.SESSION_LOGIN_USER_PS);
			modelMap.put("psList", psList);
		}
    }
	  
    /*@InitBinder  
    public void initBinder(WebDataBinder binder) {  
        System.out.println("============应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器"); 
        	例如  binder.setDisallowedFields("name");
    }*/ 
}
