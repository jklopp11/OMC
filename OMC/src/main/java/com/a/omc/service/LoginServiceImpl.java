package com.a.omc.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a.omc.mapper.LoginMapper;
import com.a.omc.pojo.Login;
import com.a.omc.pojo.LoginExample;
@Service
public class LoginServiceImpl implements LoginService {
	
	
	@Resource
	private LoginMapper loginMapper;
	
	@Override
	public long countByExample(LoginExample example) {
		return loginMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(LoginExample example) {
		return loginMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return loginMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Login record) {
		return loginMapper.insert(record);
	}

	@Override
	public int insertSelective(Login record) {
		return loginMapper.insertSelective(record);
	}

	@Override
	public List<Login> selectByExample(LoginExample example) {
		return loginMapper.selectByExample(example);
	}

	@Override
	public Login selectByPrimaryKey(Integer id) {
		Login selectByPrimaryKey = loginMapper.selectByPrimaryKey(id);
		return selectByPrimaryKey;
	}

	@Override
	public int updateByExampleSelective(Login record, LoginExample example) {
		return loginMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(Login record, LoginExample example) {
		return loginMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(Login record) {
		return loginMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Login record) {
		return loginMapper.updateByPrimaryKey(record);
	}

}
