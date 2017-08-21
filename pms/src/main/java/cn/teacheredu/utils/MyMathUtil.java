package cn.teacheredu.utils;

import java.math.BigDecimal;

/**
 * 数学运算工具类
 * 
 * @author Zhaojie
 *
 */
public class MyMathUtil {

	/**
	 * 求和
	 * @param nums 若干个可以被转换为bigdecimal字符串
	 * @return
	 */
	public static BigDecimal addStringToBigDecimal(String... nums) {
		int len$ = nums.length;
		if (len$ == 0) {
			return BigDecimal.ZERO;
		}
		BigDecimal result = new BigDecimal("0.00");
		for (int i$ = 0; i$ < len$; ++i$) {
			String num = nums[i$];
			BigDecimal b = new BigDecimal(num);
			if (b != null) {
				result = result.add(b);
			}
		}
		return result.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 求bigdecimal之和
	 * @param nums 若干个BigDecimal值
	 * @return
	 */
	public static BigDecimal addBigDecimal(BigDecimal... nums) {
		int len$ = nums.length;
		if (len$ == 0) {
			return BigDecimal.ZERO;
		}
		BigDecimal result = new BigDecimal("0.00");
		for (int i$ = 0; i$ < len$; ++i$) {
			BigDecimal num = nums[i$];
			if (num != null) {
				result = result.add(num);
			}
		}
		return result.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
     * 提供精确的除法运算方法div  四舍五入
     * @param value1 被除数（接收null值，会自动转成0.00）
     * @param value2 除数
     * @param scale 保留几位小数
     * @throws IllegalAccessException（除数为0异常）
     */
    public static BigDecimal div(BigDecimal value1,BigDecimal value2,int scale) throws IllegalAccessException{
        //如果精确范围小于0，抛出异常信息
        if(scale<0){         
            throw new IllegalAccessException("精确度不能小于0");
        }
        if (value1 == null) {
			return BigDecimal.ZERO;
		}
        if (value2 == null || value2.equals(BigDecimal.ZERO)) {
			throw new ArithmeticException("被除数不能为0");
		}
        return value1.divide(value2, scale, BigDecimal.ROUND_HALF_UP);
    }
    

    /**
     * 减法 
     * @param value1 被减数 （接收null值，会自动转成0.00）
     * @param value2 减数（接收null值，会自动转成0.00）
     * @return 结果默认保留小数点后两位小数 四舍五入  
     */
    public static BigDecimal sub(BigDecimal value1,BigDecimal value2){
    	if (value1 == null) {
			value1 = BigDecimal.ZERO;
		}
    	if (value2 == null) {
			value2 = BigDecimal.ZERO;
		}
    	return value1.subtract(value2).setScale(2, BigDecimal.ROUND_HALF_UP);    
    }
    
    /**
     * 乘法 
     * @param value1 被乘数 （接收null值，会自动转成0.00）
     * @param value2 乘数（接收null值，会自动转成0.00）
     * @return 结果默认保留小数点后两位小数 四舍五入  
     */
    public static BigDecimal mul(BigDecimal value1,BigDecimal value2){
    	if (value1 == null) {
			value1 = BigDecimal.ZERO;
		}
    	if (value2 == null) {
			value2 = BigDecimal.ZERO;
		}
    	return value1.multiply(value2).setScale(2, BigDecimal.ROUND_HALF_UP);    
    }
    
    /**
     * 加法 
     * @param value1 被加数 （接收null值，会自动转成0.00）
     * @param value2 加数（接收null值，会自动转成0.00）
     * @return 结果默认保留小数点后两位小数 四舍五入  
     */
    public static BigDecimal add(BigDecimal value1,BigDecimal value2){
    	if (value1 == null) {
			value1 = BigDecimal.ZERO;
		}
    	if (value2 == null) {
			value2 = BigDecimal.ZERO;
		}
    	return value1.add(value2);
    }
    
    /*BigDecimal.setScale()方法用于格式化小数点
    setScale(1)表示保留一位小数，默认用四舍五入方式 
    setScale(1,BigDecimal.ROUND_DOWN)直接删除多余的小数位，如2.35会变成2.3 
    setScale(1,BigDecimal.ROUND_UP)进位处理，2.35变成2.4 
    setScale(1,BigDecimal.ROUND_HALF_UP)四舍五入，2.35变成2.4

    setScaler(1,BigDecimal.ROUND_HALF_DOWN)四舍五入，2.35变成2.3，如果是5则向下舍

    setScaler(1,BigDecimal.ROUND_CEILING)接近正无穷大的舍入

    setScaler(1,BigDecimal.ROUND_FLOOR)接近负无穷大的舍入，数字>0和ROUND_UP作用一样，数字<0和ROUND_DOWN作用一样

    setScaler(1,BigDecimal.ROUND_HALF_EVEN)向最接近的数字舍入，如果与两个相邻数字的距离相等，则向相邻的偶数舍入。*/
	
}
