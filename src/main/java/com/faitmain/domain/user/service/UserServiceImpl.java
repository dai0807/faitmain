package com.faitmain.domain.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faitmain.domain.user.domain.User;
import com.faitmain.domain.user.mapper.UserMapper;

@Service("userSeriveImpl")
public class UserServiceImpl implements UserSerivce {

	@Autowired
	private UserMapper userMapper ;
	
	@Override
	public User getUser(String id) throws Exception {
 		return userMapper.getUser(id);
	}

}
