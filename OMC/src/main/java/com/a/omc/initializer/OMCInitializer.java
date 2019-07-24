package com.a.omc.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.a.omc.handler.OMCServerHandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
@Component
public class OMCInitializer extends ChannelInitializer<Channel> {

	@Override
	protected void initChannel(Channel sc) throws Exception {
		sc.pipeline().addLast(new ObjectEncoder());
		sc.pipeline().addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
		sc.pipeline().addLast(new OMCServerHandler());
	}
}
