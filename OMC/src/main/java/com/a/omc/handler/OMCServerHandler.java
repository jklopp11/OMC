package com.a.omc.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.a.omc.pojo.Login;
import com.a.omc.pojo.LoginExample;
import com.a.omc.pojo.LoginExample.Criteria;
import com.a.omc.service.LoginService;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@Component
@Sharable
public class OMCServerHandler extends ChannelInboundHandlerAdapter {

	@Autowired
	private LoginService loginService;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		Login login = (Login) msg;
		if (login.getUser() != null && login.getUser() != "") {
			LoginExample example = new LoginExample();
			Criteria createCriteria = example.createCriteria();
			createCriteria.andUserEqualTo(login.getUser());
			List<Login> selectByExample = loginService.selectByExample(example);
			for (Login selectLogin : selectByExample) {
				if (selectLogin.getPassword().equals(login.getPassword())) {
					System.out.println("登陆成功！");
					// 回复消息
					// copiedBuffer：创建一个新的缓冲区，内容为里面的参数，通过 ChannelHandlerContext 的 write
					// 方法将消息异步发送给客户端
					String respMsg = "I am Server , 消息接收 success!";
					ByteBuf respByteBuf = Unpooled.copiedBuffer(respMsg.getBytes());
					ctx.write(respByteBuf);
				} else {
					System.out.println("密码错误！");
					String respMsg = "I am Server , 消息接收 failed!";
					ByteBuf respByteBuf = Unpooled.copiedBuffer(respMsg.getBytes());
					ctx.write(respByteBuf);
				}
			}
		} else {
			System.out.println("找不到该用户！");
			String respMsg = "I am Server , 消息接收 failed!";
			ByteBuf respByteBuf = Unpooled.copiedBuffer(respMsg.getBytes());
			ctx.write(respByteBuf);
		}

	}

}
