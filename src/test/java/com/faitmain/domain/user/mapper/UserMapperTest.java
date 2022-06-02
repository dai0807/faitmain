package com.faitmain.domain.user.mapper;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

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
		int result = usermapper.updateUser(user);
		System.out.println("result:: "+ result +" getUser  "+user);
 
		
		assertThat(user.getAddress()).isEqualTo("업데이트 어드민주소");

		System.out.println("getUserUpdateTest end");
	}
	
	 
	 	//@Test
		 @DisplayName("updatUserStore   Test")
		public void updatUserStore() throws Exception {
			System.out.println("updatUserStore start");
			
			User user = new User();
			user.setId("store03@naver.com");
			user=usermapper.getUser(user) ;
			System.out.println("store 권한 올리기전 "+ user.getRole());
			user.setRole("store");
			int result = usermapper.updatUserStore(user);
			System.out.println("result:: "+ result);
			user=usermapper.getUser(user) ;
			System.out.println("나와라  변경된 롤 ! "+  user.getRole());

			
			System.out.println("updatUserStore end");
		}
		 
		 	//@Test
			 @DisplayName("findUser   Test")
			public void findUser() throws Exception {
				System.out.println("findUser start");
			
				HashMap<String,Object> map = new HashMap<>() ;
				map.put("phoneNumber", "01012345678");
				map.put("findcondition", "name");
				map.put("findkeyword", "태지원");
				
				int result = usermapper.findUser(map) ;
				System.out.println("findUse의 결과는  "+ result);
				System.out.println("findUser end");
			}
		
			//	@Test
				 @DisplayName("addUser   Test")
				public void addUser() throws Exception {
					System.out.println("addUser start");
				
					User user = new User();
					user.setId("test033@naver.com");
					user.setPassword("test01");
					user.setGender(null);
					user.setAddress("구리시 ");
					user.setPostalCode(12354);
					user.setNickname("테스트1");
					user.setName("양지원");
					user.setPhoneNumber("0106511987");
					
					user.setJoinPath("HOME");
					user.setStorelogoImage(null);
					user.setBankAccountCopyImage(null);
					user.setBankName(null);
					user.setBankName(null);

					user.setRole("user");
					user.setStoreName(null);
 					System.out.println(user);
					int result = usermapper.addUser(user);
					System.out.println(result+ " : addUser end");
				}
				 
					 	
	 @Test
	 @DisplayName("getchechDuplicationCount   Test")
		public void getchechDuplicationCount() throws Exception {
			System.out.println("getchechDuplicationCount start");
				
			Map<String,Object> hashmap = new HashMap<>() ;
			hashmap.put("checkcondition", "phone_number") ;
			hashmap.put("checkkeyword", "0106541987") ;
			
			System.out.println("있음 : "+usermapper.getchechDuplicationCount(hashmap));
 
			
			HashMap<String,Object> hashmap1 = new HashMap<>() ;
			hashmap1.put("checkcondition", "id") ;
			hashmap1.put("checkkeyword", "us05@naver.com") ;
			
			System.out.println("없음 : "+usermapper.getchechDuplicationCount(hashmap1));
 
					
					
			
	 }
					 		 	
	 
}
//	Logger logger = LoggerFactory.getLogger(ProductMapperTest.class);

 