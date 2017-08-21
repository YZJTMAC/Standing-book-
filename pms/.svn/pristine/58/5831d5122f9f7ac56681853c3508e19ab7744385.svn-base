package cn.teacheredu.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.alibaba.druid.util.Base64;

/**
 * 功能：md5加密处理类
 * 版本：1.0
 * 日期：2017-3-29
 * 作者：zzj
 **/
public class Md5Encrypt {
	
	/**
	 * 功能：计算字符串的md5值
	 * 
	 * @param src
	 * @return
	 */
	public static String md5(String src) {			
		return digest(src, "MD5");			
	}
	
	/**
	 * 功能：根据指定的散列算法名，得到字符串的散列结果。
	 * 并进行base64编码
	 * 
	 * @param src
	 * @param name
	 * @return
	 */
	private static String digest(String src, String name){
		try {
			MessageDigest alg = MessageDigest.getInstance(name);
			byte[] result = alg.digest(src.getBytes());
			//System.out.println(ByteArrayUtil.toHexString(result));
			return Base64.byteArrayToBase64(result);
		} catch (NoSuchAlgorithmException ex) {
			return null;
		}	
	}
	
	public static void main(String[] args) {
		System.out.println(md5("delete100031149076183934a9169f-c659-4e61-9b67-9fafc0c4dd8c"));
	}
}
