package com.cmcc.inter.socket.util;

import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * @author iversoncl
 * @Date 2015年7月22日
 * @Project InterfaceFramework
 */
public class TCPClient {

	private static String ip = "114.113.202.176";
	private static int port = 3014;

	public void createClient() {
		ConnectFuture future;
		NioSocketConnector  connector;
		try {
			connector = new NioSocketConnector();
			connector.setHandler(new TCPClientHandler());
			connector.getFilterChain().addLast("codec",
					(IoFilter) new TextLineCodecFactory());
			connector.setConnectTimeoutMillis(60000L);  
			connector.setConnectTimeoutCheckInterval(10000);
			future = connector.connect(new InetSocketAddress(ip, port));
			future.awaitUninterruptibly();
			future.getSession().getCloseFuture().awaitUninterruptibly();  
	        connector.dispose();  	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
