package cn.teacheredu.utils;

import java.util.Calendar;
import java.util.Date;

public class MyDateTool {
	
	/**
	 * 取得本月第一刻时间
	 * @return
	 */
	public static Date getFirstDayOfMonth() {
		Calendar c = Calendar.getInstance();    
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
	}
	
	/**
	 * 取得本月最后一刻时间
	 * @return
	 */
	public static Date getLastDayOfMonth() {
		Calendar ca = Calendar.getInstance();    
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        ca.set(Calendar.HOUR_OF_DAY, 23);
        ca.set(Calendar.MINUTE, 59);
        ca.set(Calendar.SECOND, 59);
	    return ca.getTime();
	}
	
}
