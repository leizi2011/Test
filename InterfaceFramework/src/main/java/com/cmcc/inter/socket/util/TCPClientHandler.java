package com.cmcc.inter.socket.util;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.cmcc.inter.tools.LogUtils;

/**
 * @author iversoncl
 * @Date 2015年7月22日
 * @Project InterfaceFramework
 */

public class TCPClientHandler extends IoHandlerAdapter {

	CharsetDecoder cd = Charset.forName("UTF-8").newDecoder();
	IoHandlerAdapter w = new IoHandlerAdapter();

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		LogUtils.info(TCPClientHandler.class,"服务端与客户端创建连接...");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		LogUtils.info(TCPClientHandler.class,"服务端与客户端连接打开..." + session.getId());		
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		if (message instanceof IoBuffer) {
			LogUtils.info(TCPClientHandler.class,"服务端返回信息成功，地址为:" + session.getRemoteAddress());
			LogUtils.info(TCPClientHandler.class,"内容为:" + message.toString());
		}
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		LogUtils.info(TCPClientHandler.class,"服务端发送信息成功...");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		LogUtils.info(TCPClientHandler.class,"服务端与客户端连接断开...");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		LogUtils.info(TCPClientHandler.class,"服务端进入空闲状态...");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		LogUtils.err(TCPClientHandler.class,"服务端发送异常...");
	}

}
