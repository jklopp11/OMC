package com.a.omc.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.a.omc.pojo.Login;
import com.a.omc.service.LoginService;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@Component
@Sharable
public class OMCServerHandler  extends ChannelInboundHandlerAdapter{
	
	@Autowired
	private  LoginService loginService;
	

	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
		System.out.println(loginService.selectByPrimaryKey(1));
		System.out.println(loginService);
		Login rms = (Login) msg;
		System.out.println(rms);
    }



}
