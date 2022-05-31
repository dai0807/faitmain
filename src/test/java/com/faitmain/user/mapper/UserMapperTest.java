package com.faitmain.user.mapper;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.faitmain.domain.user.domain.User;
import com.faitmain.domain.user.service.UserSerivce;
 

@SpringBootTest
public class UserMapperTest {
	
	
 	
	@Autowired
	@Qualifier("userSeriveImpl")
	private UserSerivce userserivce;
	
	
	@Test
	public void tesUser() throws Exception{
 		User user = userserivce.getUser("user01@naver.com");
		System.out.println(user);
		assertNotNull(user);
	}
	

}
