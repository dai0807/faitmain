package com.faitmain.domain.user.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.faitmain.domain.product.domain.Product;
import com.faitmain.domain.product.mapper.ProductMapperTest;
import com.faitmain.domain.product.service.ProductService;
import com.faitmain.domain.user.domain.User;
   
@WebAppConfiguration
@SpringBootTest
public class UserMapperTest {

	
//	
//    @Autowired
//	private UserSerivce userserivce;
//
//    @BeforeEach
//    public void setUp(){
//    }
//
//    @Test
//    @DisplayName( " 조회" )
//     
//    public void getUserTest() throws Exception{
//
//    	User user = userserivce.getUser("user01@naver.com");
//		System.out.println(user);
//		assertNotNull(user);
//    	
//    }
}
//	Logger logger = LoggerFactory.getLogger(ProductMapperTest.class);

 