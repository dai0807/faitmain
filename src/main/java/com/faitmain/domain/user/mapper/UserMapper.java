package com.faitmain.domain.user.mapper;

import org.apache.ibatis.annotations.Mapper;
 
import com.faitmain.domain.user.domain.User;

 
@Mapper
public interface UserMapper {

	
	public User getUser(String id) ;
	
	
}
