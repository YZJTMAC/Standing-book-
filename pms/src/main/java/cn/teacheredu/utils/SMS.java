package cn.teacheredu.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * 调用范例 String result = SMS.senderMsg("18141906787", "您好，您的验证码：565345。10分钟内有效。");
 * @author Zhaojie
 *
 */
public class SMS {
	/**
	 * 发送短消息
	 * @param mobiles 手机号码，多个号码使用","分割
	 * @param content 短信内容
	 * @return 只有0表示提交成功
	 */
	public static String senderMsg(String mobiles,String content){
		String receive = null;
		if ("".equals(mobiles) || "".equals(content)) {
			return receive;
		}
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod();

		try {
			URI base = new URI("http://send.18sms.com/msg/HttpBatchSendSM", false);
			method.setURI(new URI(base, "HttpBatchSendSM", false));
			method.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler());
			method.getParams().setParameter("http.protocol.content-charset", "UTF-8");
			method.setRequestBody(new NameValuePair[] { new NameValuePair("account", "wx18505881266"),
					new NameValuePair("pswd", "Aa7tiuv5"), new NameValuePair("mobile", mobiles),
					new NameValuePair("needstatus", String.valueOf(false)),
					new NameValuePair("msg", URLEncoder.encode(content, "UTF-8")),
					new NameValuePair("product", ""), new NameValuePair("extno", "") });
			int result = client.executeMethod(method);
			if (result != 200) {
				throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
			} else {
				InputStream in = method.getResponseBodyAsStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				//boolean len = false;

				int len1;
				while ((len1 = in.read(buffer)) != -1) {
					baos.write(buffer, 0, len1);
				}

				receive = URLDecoder.decode(baos.toString(), "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return receive == null ? "" : receive.split(",").length > 1 ? receive.split(",")[1] : "";
	}

}
