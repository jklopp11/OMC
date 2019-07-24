package com.a.omc.client;

import org.springframework.stereotype.Component;

import com.a.omc.handler.NFVClientHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
@Component
public class NFVClient {
	public static void main(String[] args) throws Exception {
		new NFVClient().connect("localhost",8960);
	}

	public void connect(String host,int port) throws Exception {
		// 配置客户端 NIO 线程组/池
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<Channel>() {
						@Override
						public void initChannel(Channel ch) throws Exception {
							ch.pipeline().addLast(new ObjectEncoder());
							ch.pipeline().addLast(new ObjectDecoder(Integer.MAX_VALUE,
		                            ClassResolvers.cacheDisabled(null)));
							ch.pipeline().addLast(new NFVClientHandler());
						}
					});

			// connect：发起异步连接操作，调用同步方法 sync 等待连接成功
			ChannelFuture channelFuture = b.connect(host, port).sync();
			System.out.println(Thread.currentThread().getName() + ",客户端发起异步连接..........");

			// 等待客户端链路关闭
			channelFuture.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 优雅退出，释放NIO线程组
			group.shutdownGracefully();
		}

	}

}
