package com.cmcc.inter.data.util;

/**
 * @author iversoncl
 * @Date 2015年7月8日
 * @Project InterfaceFramework
 */
public class ExcelUtil {
	
	    public static String getPostfix(String path) {
	        if (path == null || ExcelCommon.EMPTY.equals(path.trim())) {
	            return ExcelCommon.EMPTY;
	        }
	        if (path.contains(ExcelCommon.POINT)) {
	            return path.substring(path.lastIndexOf(ExcelCommon.POINT) + 1, path.length());
	        }
	        return ExcelCommon.EMPTY;
	    }
}
