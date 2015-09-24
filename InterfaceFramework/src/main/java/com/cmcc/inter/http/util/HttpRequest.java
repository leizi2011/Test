package com.cmcc.inter.http.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import com.cmcc.inter.tools.LogUtils;

/**
 * @author iversoncl
 * @Date 2015年7月7日
 * @Project InterfaceFramework
 */
public class HttpRequest {

	private static HttpClientBuilder httpClientBuilder;
	
	public static HttpResponseDao get(String url) {
		return getRequest(url, null, null);
	}
	
	public static HttpResponseDao get(String url,ArrayList<Header> headerList,
			CookieStore cookieStore) {
		return getRequest(url, headerList, cookieStore);
	}
	
	
	public static HttpResponseDao post(String url, List<NameValuePair> namePairs) {
		return postRequest(url, namePairs, null, null, null);
	}

	public static HttpResponseDao post(String url, List<NameValuePair> namePairs, HttpEntity input,
			ArrayList<Header> headerList, CookieStore cookieStore) {
		return post(url, namePairs, input, headerList, cookieStore);
	}
	
	/**
	 * @Description:get请求，自动识别https
	 * @param url
	 * @param list
	 * @return String
	 * @author: iversoncl
	 * @time:2015年7月7日 下午4:18:45
	 */
	private static  HttpResponseDao getRequest(String url, ArrayList<Header> headerList,
			CookieStore cookieStore) {
		HttpResponseDao hrd = new HttpResponseDao();
		httpClientBuilder = HttpClientBuilder.create();
		HttpClientContext localContext = HttpClientContext.create();
		if (cookieStore != null) {
			localContext.setCookieStore(cookieStore);
		}
		CloseableHttpClient closeableHttpClient = null;
		CloseableHttpResponse response = null;

		if (isHttp(url)) {
			closeableHttpClient = httpClientBuilder.build();
		} else {
			closeableHttpClient = HttpsUtil.createSSLClientDefault();
		}
		HttpGet httpGet = new HttpGet(url);
		if (headerList != null) {
			for (int i = 0; i < headerList.size(); i++) {
				httpGet.addHeader(headerList.get(i));
			}
		}
		LogUtils.info(HttpRequest.class, httpGet.getRequestLine().toString());
		try {
			response = closeableHttpClient.execute(httpGet, localContext);
			if (response != null) {
				hrd.setHeader(response.getAllHeaders());
				String result = EntityUtils.toString(response.getEntity());
				hrd.setEntity(result);
				LogUtils.info(HttpRequest.class, result);
			}
			hrd.setCookieStore(cookieStore);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
				closeableHttpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return hrd;
	}


	/**
		* @Description:post请求，自动识别https
		* @param url
		* @param namePairs
		* @param input
		* @param headerList
		* @param cookieStore
		* @return HttpResponseDao
		* @author: iversoncl
		* @time:2015年7月24日 上午9:39:09
	*/
	private static HttpResponseDao postRequest(String url, List<NameValuePair> namePairs, HttpEntity input,
			ArrayList<Header> headerList, CookieStore cookieStore) {
		HttpResponseDao hrd = new HttpResponseDao();
		httpClientBuilder = HttpClientBuilder.create();
		HttpClientContext localContext = HttpClientContext.create();
		if (cookieStore != null) {
			localContext.setCookieStore(cookieStore);
		}
		CloseableHttpClient closeableHttpClient = null;
		if (isHttp(url)) {
			closeableHttpClient = httpClientBuilder.build();
		} else {
			closeableHttpClient = HttpsUtil.createSSLClientDefault();
		}
		CloseableHttpResponse response = null;
		HttpPost httpPost = new HttpPost(url);
		if (headerList != null) {
			for (int i = 0; i < headerList.size(); i++) {
				httpPost.addHeader(headerList.get(i));
			}
		}
		if(input != null){
			httpPost.setEntity(input);
		}
		try {
			if (namePairs != null) {
				httpPost.setEntity(new UrlEncodedFormEntity(namePairs, "UTF-8"));
			}
			LogUtils.info(HttpRequest.class, httpPost.getRequestLine().toString());
			response = closeableHttpClient.execute(httpPost, localContext);
			if (response != null) {
				hrd.setHeader(response.getAllHeaders());
				String result = EntityUtils.toString(response.getEntity());
				hrd.setEntity(result);
				LogUtils.info(HttpRequest.class, result);
			}
			hrd.setCookieStore(cookieStore);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
				closeableHttpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return hrd;
	}
	

	/**
		* @Description:判断http还是https
		* @param url
		* @return boolean
		* @author: iversoncl
		* @time:2015年7月24日 上午9:43:32
	*/
	private static boolean isHttp(String url) {
		if (url != null) {
			if (url.substring(0, 4).equals("https")) {
				return false;
			} else {
				return true;
			}
		} else
			return true;
	}

}
