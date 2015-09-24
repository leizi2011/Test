package com.cmcc.inter.http.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import com.cmcc.inter.data.util.TransferData;
import com.cmcc.inter.tools.StringTools;


/**
 * @author iversoncl
 * @Date 2015年7月23日
 * @Project InterfaceFramework
 */
public class RequestDataUtil {

	
	/**
		* @Description:组装get请求url
		* @param host
		* @param params
		* @return String
		* @author: iversoncl
		* @time:2015年7月23日 下午2:32:35
	*/
	public static String getReqUrl(String target, String... params){
		String url = null;
		if(params != null){
			Object[] paramName = TransferData.getParamName();
			int parmNameNum = paramName.length -3;
			Map<String, String> kv = new HashMap<String, String>();
			for(int i = 0; i < parmNameNum; i++){
				String key =paramName[i + 1].toString();
				kv.put(key, params[i]);
			}
			url = String.format("%s?%s", target, StringTools.joinStr("&", kv));
		} else {
			url = target;
		}
		
		return url;
	}
	
	/**
		* @Description:组装post请求参数
		* @param params
		* @return List<NameValuePair>
		* @author: iversoncl
		* @time:2015年7月24日 上午9:36:48
	*/
	public static List<NameValuePair> postNameValue(String... params){
		List<NameValuePair> namePairs = new ArrayList<NameValuePair>();
		Object[] paramName = TransferData.getParamName();
		int parmNameNum = paramName.length -3;
		Map<String, String> kv = new HashMap<String, String>();
		for(int i = 0; i < parmNameNum; i++){
			String key = paramName[i + 1].toString();
			kv.put(key, params[i]);
			namePairs.add(new BasicNameValuePair(
					key, params[i]));
		}
		
		return namePairs;
	}
	
	/**
		* @Description:组装一个header
		* @param headerName
		* @param headerValue
		* @return Header
		* @author: iversoncl
		* @time:2015年7月24日 上午9:37:35
	*/
	public static Header getHeader(String headerName,String headerValue) {
		
		return new BasicHeader(headerName, headerValue);
	}
	
	/**
		* @Description:字符串转化String请求实体
		* @param input
		* @return StringEntity
		* @author: iversoncl
		* @time:2015年7月24日 上午9:42:34
	*/
	public StringEntity getStringEntity(String input){
		try {
			return new StringEntity(input);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
		* @Description:字符串转化byte请求实体
		* @param input
		* @return ByteArrayEntity
		* @author: iversoncl
		* @time:2015年7月24日 上午9:43:07
	*/
	public ByteArrayEntity getByteArrayEntity(String input){
		return new ByteArrayEntity(input.getBytes());
	}
	
}
