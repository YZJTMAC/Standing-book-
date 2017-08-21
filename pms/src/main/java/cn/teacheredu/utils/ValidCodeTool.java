package cn.teacheredu.utils;

import java.util.Random;

public class ValidCodeTool {
	public static final char[] chars={'1','2','3','4','5','6','7','8','9','Q','W','E','R','T','Y','U','I',
	        'P','A','S','D','F','G','H','J','K','L','Z','X','C','V','B','N','M'};
	public static Random random=new Random();
	/**
	 * 获取一个指定长度的随机验证码（没有字母O和数字0）
	 * @param length 范围：4-8
	 * @param hasChar 是否含字母 
	 * @return
	 */
	public static String getValidCode(int length,boolean hasChar) {
		if (length < 4 || length > 8) {
			return null;
		}
		StringBuffer sb=new StringBuffer();
        for(int i=1;i<=length;i++){
        	if (hasChar) {
        		sb.append(chars[random.nextInt(chars.length)]);
			} else {
				sb.append(chars[random.nextInt(9)]);
			}
        }
        return sb.toString();
	}
	public static void main(String[] args) {
		System.out.println(getValidCode(6,true));
		System.out.println(getValidCode(6,true));
		System.out.println(getValidCode(6,false));
		System.out.println(getValidCode(6,false));		
	}

}
