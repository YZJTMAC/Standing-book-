package cn.teacheredu.utils;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class RDUtils {
	/************
	 * 获取时间戳
	 * 
	 * @return
	 */
	public static String getTimestemp() {
		return new SimpleDateFormat("MMddHHmmss").format(new Date());
	}

	/************
	 * 加密处理
	 * 
	 * @param userName
	 * @param password
	 * @param timestemp
	 * @return
	 */
	public static String getKey(String userName, String password, String timestemp) {
		String key = "";
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(userName.getBytes());
			mdTemp.update(password.getBytes());
			mdTemp.update(timestemp.getBytes());
			key = bytesToHexString(mdTemp.digest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}

	/**********
	 * 字节数组转16进制字符串
	 * 
	 * @param src
	 * @return
	 */
	public static String bytesToHexString(byte src[]) {
		String resultString = "";
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0)
			return null;
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 255;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2)
				stringBuilder.append(0);
			stringBuilder.append(hv);
		}

		resultString = stringBuilder.toString();
		stringBuilder = null;
		return resultString;
	}
	/**
	 * POST发送消息
	 * 
	 * @param Content
	 *            发送的内容
	 * @param _Header
	 *            HTTP头信息
	 * @return HTTPResponse应答
	 */
	public static String SendMessage(byte[] Content, Hashtable<String,String> _Header, String serverAddress) {
		return SendMessage(Content, _Header, serverAddress, null, 0, null, null);
	}

	public static String SendMessage(byte[] Content, Hashtable<String,String> _Header, String serverAddress, String proxyHost, int proxyPort, String userName, String password) {
		String result = null;
		if (serverAddress == null)
			return result;
		HttpPost httpPost = new HttpPost(serverAddress);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		try {
			if (_Header != null) {
				Enumeration<String> _List = _Header.keys();
				String keyString = "";
				try {
					while (_List.hasMoreElements()) {
						keyString = _List.nextElement().toString();
						httpPost.addHeader(keyString, _Header.get(keyString));
					}
				} catch (NoSuchElementException e) {
					e.printStackTrace();
				}
			}
			httpPost.addHeader("Connection", "Keep-Alive");
			httpPost.setEntity(new ByteArrayEntity(Content));
			
			CloseableHttpResponse response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			InputStream _InputStream = entity.getContent();
			result = GetResponseString(_InputStream, "utf-8");
			EntityUtils.consume(entity);
			response.close();
		} catch (Exception e) {
			System.out.println("请求失败，原因：" + e.getMessage() + "请求地址：" + serverAddress);
		} finally {
			httpPost.releaseConnection();
		}
		return result;
	}

	
	/**
	 * 解析返回的流
	 * 
	 * @param _InputStream
	 * @return
	 */
	public static String GetResponseString(InputStream _InputStream, String Charset) {
		String response = "error:";
		try {
			if (_InputStream != null) {
				StringBuffer buffer = new StringBuffer();
				InputStreamReader isr = new InputStreamReader(_InputStream, Charset);
				Reader in = new BufferedReader(isr);
				int ch;
				while ((ch = in.read()) > -1) {
					buffer.append((char) ch);
				}
				response = buffer.toString();
				buffer = null;
			} else {
				response = response + "timeout";
			}
		} catch (Exception e) {
			System.out.println("获取响应错误，原因：" + e.getMessage());
			response = response + e.getMessage();
		}
		return response;
	}

}
