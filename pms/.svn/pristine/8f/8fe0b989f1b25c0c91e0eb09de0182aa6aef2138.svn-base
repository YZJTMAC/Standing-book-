package cn.teacheredu.controller;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.teacheredu.utils.R;

/**
 * Spring MVC 统一异常处理类 
 * 普通controller不需要继承此类
 * @author Zhaojie
 *
 */
@ControllerAdvice
public class BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(BaseController.class);
	  
//    @ExceptionHandler(Exception.class)  
//    public String processException(HttpServletRequest request, Exception ex) {  
//    	logger.error(ExceptionUtils.getFullStackTrace(ex));
//    	request.setAttribute("ex", ex);
//        return "error"; //返回一个逻辑视图名  
//    }
    
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public R handleException(Exception ex) {
        logger.error(ExceptionUtils.getFullStackTrace(ex));  // 记录错误信息
        String msg = "出错了:" + ex.getLocalizedMessage();
        return R.error(msg);
    }
}
