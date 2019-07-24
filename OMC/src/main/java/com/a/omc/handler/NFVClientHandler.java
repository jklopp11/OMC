package com.a.omc.handler;

import com.a.omc.pojo.Login;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NFVClientHandler extends ChannelInboundHandlerAdapter {
	
	//当客户端和服务端 TCP 链路建立成功之后，Netty 的 NIO 线程会调用 channelActive 方法
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Login login = new Login();
		login.setUser("yiy");
		login.setPassword("123");
		login.setType("msg");
		ctx.writeAndFlush(login);
		System.out.println(login);
	}

	//当服务端返回应答消息时，channelRead 方法被调用，从 Netty 的 ByteBuf 中读取并打印应答消息
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println(Thread.currentThread().getName() + ",Server return Message：" + body);
        ctx.close();
	}

	//当发生异常时，打印异常 日志，释放客户端资源
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println(cause.getMessage());
        ctx.close();
	}
}
