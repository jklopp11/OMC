package com.a.omc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.a.omc.server.OMCServer;

@SpringBootApplication

@ComponentScan(basePackages = {"com.a.omc.*"})
@MapperScan("com.a.omc.mapper")
public class OmcApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(OmcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		OMCServer echoServer = new OMCServer();
        echoServer.start(8960);
	}

}
