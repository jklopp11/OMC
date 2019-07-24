package com.a.omc.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.a.omc.pojo.Login;
import com.a.omc.service.LoginService;

@RestController
public class LoginController {
	
	@Resource
	private LoginService loginService;
	
	@GetMapping(value = "/user/query/{id}")
    public Login selectByPrimaryKey(@PathVariable("id") Integer id){
		System.out.println(loginService);
		System.out.println(loginService.selectByPrimaryKey(id));
        return loginService.selectByPrimaryKey(id);
    }
}
