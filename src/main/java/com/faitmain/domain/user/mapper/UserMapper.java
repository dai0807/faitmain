package com.faitmain.domain.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.faitmain.domain.user.domain.User;

 
@Mapper
@Repository

public interface UserMapper {

	//Select
	 User getUser(String id) ;
	
	
}
