package com.a.omc.server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.a.omc.initializer.OMCInitializer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

@Component
public class OMCServer {

	@Autowired
	private OMCInitializer omcInitializer;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private EventLoopGroup bossGroup = new NioEventLoopGroup();
	private EventLoopGroup workerGroup = new NioEventLoopGroup();

	

	/**
	 * 启动netty服务
	 */
	public void start(int port) {
		try {
			ServerBootstrap b = new ServerBootstrap(); // Netty 用于启动 NIO 服务端的辅助启动类
			b.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, 1024)
			.childOption(ChannelOption.SO_KEEPALIVE, true)
			.childOption(ChannelOption.TCP_NODELAY, true)
					.childHandler(omcInitializer);
			
			
			
			// 服务器启动辅助类配置完成后，调用 bind 方法绑定监听端口，调用 sync 方法同步等待绑定操作完成
			ChannelFuture f = b.bind(port).sync().addListener(new GenericFutureListener<Future<? super Void>>() {

				@Override
				public void operationComplete(Future<? super Void> future) throws Exception {
					if (future.isSuccess()) {
						System.out.println("端口绑定成功！");
					} else {
						System.out.println("端口绑定失败！");
					}

				}
			});
			System.out.println(Thread.currentThread().getName() + ",服务器开始监听端口，等待客户端连接.........");
			// 下面会进行阻塞，等待服务器连接关闭之后 main 方法退出，程序结束
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 优雅退出，释放NIO线程组
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
