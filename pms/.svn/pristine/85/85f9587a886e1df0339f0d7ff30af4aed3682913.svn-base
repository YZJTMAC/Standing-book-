package cn.teacheredu.utils;

import java.net.URLEncoder;
import java.util.Hashtable;

/**
 * 调用范例：String result2 = RDSMS.SendSMS("18141906787", "【网络培训平台】您好，用户名zj12384的密码已经初始化为：xy675@#。建议您登录平台后尽快修改密码。");
 * @author Zhaojie
 *
 */
public class RDSMS {
	
	/**
	 * 短信发送
	 * @param mobiles	要发送给哪个手机号，多个手机号用逗号隔开
	 * @param content	发送的内容
	 * @return  json格式的字符串，0代表提交成功    例如：{"result":0,"body":null,"description":null}
	 */
	public static String SendSMS(String mobiles, String content) {
		try{	
			String Timestemp = RDUtils.getTimestemp();// 时间戳
			String Key = RDUtils.getKey("dslxjy", "240231", Timestemp);// 加密
			String serverAddress = "http://www.youxinyun.com:3070/Platform_Http_Service/servlet/SendSms";// 请求的URL
			// （可选）预约时间（不预约的话填写,时间格式yyyymmddhhmmss 20160617234758）
			String SchTime = "";
			// 1~5的整数 从低到高
			int Priority = 5;
			// （可选）批次小号 字符串32位以内
			String PackID = "";
			// （可选）批次号 字符串32位以内。例：群发短信3万，每一个包1000。为这个3万个分配一个PacksID
			// ，每1000包分配一个PackID
			String PacksID = "";
			// （可选）
			String ExpandNumber = "";
			// 短信唯一标识 长整型数字。短信ID，用来匹配状态报告（必须数字类型）
			Long SMSID = System.currentTimeMillis();
			StringBuffer _StringBuffer = new StringBuffer();
			_StringBuffer.append("UserName=dslxjy&");
			_StringBuffer.append("Key=" + Key + "&");
			_StringBuffer.append("Timestemp=" + Timestemp + "&");
			_StringBuffer.append("Content=" + URLEncoder.encode(content, "utf-8") + "&");
			_StringBuffer.append("CharSet=utf-8&");
			_StringBuffer.append("Mobiles=" + mobiles + "&");
			_StringBuffer.append("SchTime=" + SchTime + "&");
			_StringBuffer.append("Priority=" + Priority + "&");
			_StringBuffer.append("PackID=" + PackID + "&");
			_StringBuffer.append("PacksID=" + ExpandNumber + "&");
			_StringBuffer.append("ExpandNumber=" + PacksID + "&");
			_StringBuffer.append("SMSID=" + SMSID);
			Hashtable<String,String> _Header = new Hashtable<String,String>();
			_Header.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			// post请求
			return RDUtils.SendMessage(_StringBuffer.toString().getBytes("utf-8"), _Header, serverAddress);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
