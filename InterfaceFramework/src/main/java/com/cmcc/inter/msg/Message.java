package com.cmcc.inter.msg;

import java.math.BigDecimal;

/**
 * 业务参数接口
 * 
 * @author wanglulu
 * 
 */
public interface Message {
	Message addItem(String key, String value);

	Message removeItem(String key);

	String getString(String key);

	int getInt(String key);

	long getLong(String key);

	double getDouble(String key);

	boolean getBoolean(String key);

	BigDecimal getBigDecimal(String key);

	Message getMessage(String key);

	Message[] getArray(String key);
}
