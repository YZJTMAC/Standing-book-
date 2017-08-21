package cn.teacheredu.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.HttpServletRequest;

public class CommonUtils {
	
	public static final String SPOT = "...";
	 /** 
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 
     * 此方法中前三位格式有： 
     * 13+任意数 
     * 15+除4的任意数 
     * 18+任意数 
     * 17+除9的任意数 
     * 147 
     */  
	public static boolean isPhoneLegal(String str) throws PatternSyntaxException {
		if (str == null) {
			return false;
		}
		String regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147))\\d{8}$";  
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(str);  
        return m.matches(); 
    }
	
	/** 
	   * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址, 
	   * 
	   * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？ 
	   * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。 
	   * 
	   * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 
	   * 192.168.1.100 
	   * 
	   * 用户真实IP为： 192.168.1.110 
	   * 
	   * @param request 
	   * @return 
	   */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/** 
	 * @Description:把list转换为一个用逗号分隔的字符串 
	 */  
	public static String listToString(List<? extends Object> list) {  
	    StringBuilder sb = new StringBuilder();  
	    if (list != null && list.size() > 0) {  
	        for (int i = 0; i < list.size(); i++) {  
	            if (i < list.size() - 1) {  
	                sb.append(list.get(i) + ",");  
	            } else {  
	                sb.append(list.get(i));  
	            }  
	        }  
	    }  
	    return sb.toString();  
	} 
	
	/**
	 * 根据code和他的权限集合判断是否有权限
	 * @param funcode 对应menu表的perms
	 * @param fun 对应menuservice.getPsListByRoleId
	 * @return true or false
	 */
	public static boolean hasPriByFunCode(String funcode, List<String> fun) {
		if (fun == null || "".equals(funcode) || funcode == null) {
			return false;
		}
		if (fun.contains(funcode)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 截取字符串，末尾带...
	 * @param str
	 * @param length
	 * @return
	 */
	public static String substring(String str, int length) {
		if (str == null) {
			return "";
		}
		if (str.length() <= length) {
			return str;
		}
		return str.substring(0, length) + SPOT;
	}
	
	
	/**
     *  获取两个日期相差的月数
     * @param d1    较大的日期
     * @param d2    较小的日期
     * @return  如果d1>d2返回 月数差 否则返回0
     */
	public static int getMonthDiff(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        if(c1.getTimeInMillis() < c2.getTimeInMillis()) return 0;
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        //int day1 = c1.get(Calendar.DAY_OF_MONTH);
        //int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值 假设 d1 = 2015-8-16  d2 = 2011-9-30
        int yearInterval = year1 - year2;
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if(month1 < month2) yearInterval --;
        // 获取月数差值
        int monthInterval =  (month1 + 12) - month2  ;
        //if(day1 < day2) monthInterval --;
        monthInterval %= 12;
        if(monthInterval==0){
        	monthInterval=1;
        }
        return yearInterval * 12 + monthInterval;
    }
	
	public static String parseMoney(BigDecimal bd){
		DecimalFormat df = new DecimalFormat(",###,###.00");
		String result = df.format(bd);
	    return ".00".equals(result) ? "0.00" : result;
	}
    
	public static void main(String[] args) {
		System.out.println(isPhoneLegal("18141906787"));
		System.out.println(substring("", 3));
	}
}
