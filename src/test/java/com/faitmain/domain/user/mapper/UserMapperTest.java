package com.faitmain.domain.user.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.faitmain.domain.live.domain.Live;
import com.faitmain.domain.order.mapper.OrderMapper;
import com.faitmain.domain.product.mapper.ProductMapper;
import com.faitmain.domain.user.domain.User;
import com.faitmain.domain.user.service.UserSerivce;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserMapperTest {

	
	
    @Autowired
	private UserMapper usermapper;

	// @Test
	 @DisplayName("getUserTest   Test")
	public void getUserTest() throws Exception {
		System.out.println("getUserTest start");
		
		User user = new User();
		user.setId("store03@naver.com");
		System.out.println(user);
		user=usermapper.getUser(user) ;
		System.out.println("getUser  "+user);
		//assertThat(user.getName()).isEqualTo("이순신");

		
		System.out.println("getUserTest end");
	}
	
 
	 
		// @Test
	 @DisplayName("getUserUpdateTest   Test")
	public void getUserUpdateTest() throws Exception {
		System.out.println("getUserUpdateTest start");
		
		User user = new User();
		user.setId("store03@naver.com");
		user=usermapper.getUser(user) ;
		System.out.println(user);

		user.setAddress("업데이트 어드민주소");
		user.setPostalCode(54321);
		user.setPhoneNumber("01099999999");
		user.setStoreIntroduction("스토어 업데이트!!! ");
		int result = usermapper(user);
		System.out.println("getUser  "+user);
 
		
		System.out.println("getUserUpdateTest end");
	}
	
	 
}
//	Logger logger = LoggerFactory.getLogger(ProductMapperTest.class);

 