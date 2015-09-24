package com.cmcc.inter.http.util;
import org.apache.http.Header;
import org.apache.http.client.CookieStore;


/**
 * @author iversoncl
 * @Date 2015年7月21日
 * @Project InterfaceFramework
 */
public class HttpResponseDao {

	private Header[] header;
	
	private CookieStore cookieStore;
	
	private String entity;
	
	public Header[] getHeader() {
		return header;
	}

	public void setHeader(Header[] header) {
		this.header = header;
	}

	public CookieStore getCookieStore() {
		return cookieStore;
	}

	public void setCookieStore(CookieStore cookieStore) {
		this.cookieStore = cookieStore;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	
}
