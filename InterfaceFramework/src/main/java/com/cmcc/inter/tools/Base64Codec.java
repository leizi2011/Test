package com.cmcc.inter.tools;

import java.io.UnsupportedEncodingException;

/**
 * @author iversoncl
 * @Date 2015年4月11日
 * @Project InterfaceFramework
 */
public class Base64Codec {

	/**
	 * @Description:base64编码
	 * @param s
	 * @return
	 * @throws UnsupportedEncodingException
	 * @author: iversoncl
	 * @time:2015年4月12日 下午7:44:24
	 */
	@SuppressWarnings("restriction")
	public static String Base64Encode(String s)
			throws UnsupportedEncodingException {
		if (s == null)
			return null;
		String codeStr = (new sun.misc.BASE64Encoder()).encode(s.getBytes("UTF-8"));
		codeStr = codeStr.replaceAll("\r", "");
		codeStr = codeStr.replaceAll("\n", "");
		return codeStr;
	}
}
