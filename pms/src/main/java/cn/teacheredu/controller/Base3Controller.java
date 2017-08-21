package cn.teacheredu.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import cn.teacheredu.entity.DepartmentEntity;
import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.utils.SystemConst;

/**
 * 继承此类的controller，在所有的requstmapping方法中都会自动把权限List、用户信息等信息放进model中
 * 适合使用layoutNew.vm作为layout的页面所对应的controller
 * @author Zhaojie
 *
 */
@Controller
public class Base3Controller {
	
	@ModelAttribute  
    public void populateModel(ModelMap modelMap ,HttpServletRequest request) throws Exception {  
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
