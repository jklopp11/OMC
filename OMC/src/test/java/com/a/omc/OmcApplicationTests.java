package com.a.omc;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.a.omc.mapper.LoginMapper;
import com.a.omc.pojo.Login;
import com.a.omc.pojo.LoginExample;
import com.a.omc.service.LoginService;
import com.a.omc.service.LoginServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OmcApplicationTests {

	@Test
	public void contextLoads() {
		
	
	LoginService logins = new LoginServiceImpl();
	}

}
