package com.faitmain.domain.user.service;

 import com.faitmain.domain.user.domain.User;

public interface UserSerivce {

	
	//유저 가져오기
	public User getUser(String id)  throws Exception;
 
	
}
