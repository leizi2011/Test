package com.cmcc.inter.datebase.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.cmcc.inter.tools.PropertiesHandle;

/**
 * @author iversoncl
 * @Date 2015年4月12日
 * @Project InterfaceFramework
 */
public class DBConnection {
	public Connection con = null;

	/**
	 * @Description:构造方法
	 * @author: iversoncl
	 * @time:2015年4月12日 下午8:12:59
	 */
	public DBConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String dbSource = PropertiesHandle.readValue("db_source");
			String userName = PropertiesHandle.readValue("db_userName");
			String passWord = PropertiesHandle.readValue("db_passWord");
			con = DriverManager.getConnection("jdbc:mysql://" + dbSource
					+ "&characterEncoding=UTF-8", userName, passWord);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description:查询数字
	 * @param query
	 * @return
	 * @author: iversoncl
	 * @time:2015年4月12日 下午8:13:23
	 */
	public Map<String, Object> queryData(String query) {
		Statement stmt;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			Map<String, Object> map = new HashMap<String, Object>();
			while (rs.next()) {
				for (int j = 1; j <= rs.getMetaData().getColumnCount(); j++) {
					map.put(rsmd.getColumnName(j), rs.getObject(j));
				}
			}
			return map;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * @Description:查询字符串
	 * @param query
	 * @return ArrayList<Object>
	 * @author: iversoncl
	 * @time:2015年4月12日 下午8:14:32
	 */
	public Object queryStringData(String query) {
		Statement stmt;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Description:查询字符串，返回字符串
	 * @param query
	 * @return String
	 * @author: iversoncl
	 * @time:2015年4月12日 下午8:14:54
	 */
	public String queryIt(String query) {
		Statement stmt;
		ResultSet rs = null;
		String s = new String();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				s = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * @Description:插入数据
	 * @param query
	 * @return int
	 * @author: iversoncl
	 * @time:2015年4月12日 下午8:15:24
	 */
	public int dbInsert(String query) {
		Statement stmt;
		int key = 0;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				key = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return key;

	}

	/**
	 * @Description:关闭数据库连接
	 * @author: iversoncl
	 * @time:2015年4月12日 下午8:15:36
	 */
	public void closeCon() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
