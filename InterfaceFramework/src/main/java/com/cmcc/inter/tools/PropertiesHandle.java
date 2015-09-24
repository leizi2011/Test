package com.cmcc.inter.tools;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


/**
 * @author: iversoncl
 * @project: Neprotect
 * @Date: 2013-5-9
 */
public class PropertiesHandle {
		 
		 /**
			* @Description:读取配置文件信息
			* @param key
			* @return String
			* @author: iversoncl
			* @time:2015年4月12日 下午8:18:28
		*/
		public static String readValue(String key) {
		  Properties props = new Properties();
		        try {
		         InputStream in = new BufferedInputStream (new FileInputStream("src/main/resources/config.properties"));
		         props.load(in);
		         String value = props.getProperty (key);
		         System.out.println(value);
		            return value;
		        } catch (Exception e) {
		         e.printStackTrace();
		         return null;
		        }
		 }
		 
		
}
