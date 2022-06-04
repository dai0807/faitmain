package com.faitmain.domain.customer.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.faitmain.domain.customer.domain.Customer;
import com.faitmain.domain.user.domain.User;
import com.faitmain.domain.user.mapper.UserMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerMapperTest{
	
	@Autowired
	private CustomerMapper customerMapper;
	
//	@Autowired
//	private UserMapper userMapper;
	
//	@Test
//	@DisplayName("addCustomerBoard")
	public void addCustomerBoardTest() throws Exception{
		System.out.println("addCustomerBoardTest start");
		
		Customer customer = new Customer();
		User user = new User();
		user.setId("admin@naver.com");
		
		//customer.setBoardNumber(5);
		customer.setBoardTitle("TITLE 1");
		customer.setBoardContent("CONTENT 1");
		customer.setFAQCategoryCode(1);
		customer.setBoardType('c');
		customer.setCustomerId(user);
		
		System.out.println(customer);
		
		int result = customerMapper.addCustomerBoard(customer);
		System.out.println("result = " + result);
		
		customer = customerMapper.getCustomerBoard(7);
		System.out.println(customer);
		
		//assertThat(customer.getBoardNumber()).isEqualTo(5);
		assertThat(customer.getBoardTitle()).isEqualTo("TITLE 1");
		assertThat(customer.getBoardContent()).isEqualTo("CONTENT 1");
		assertThat(customer.getFAQCategoryCode()).isEqualTo(1);
		assertThat(customer.getBoardType()).isEqualTo('c');
		assertThat(customer.getCustomerId().getId()).isEqualTo("admin@naver.com");
		
		System.out.println("addCustomerBoardTest end");
	}
	
	//@Test
	public void updateCustomerBoardTest() throws Exception{
		System.out.println("updateCustomerBoardTest start");
		
		Customer customer = new Customer();
		User user = new User();
		user.setId("admin@naver.com");
		
		customer.setBoardNumber(15);
		customer.setBoardTitle("TITLE correction");
		customer.setBoardContent("CONTENT correction");
		customer.setFAQCategoryCode(3);
		customer.setBoardType('r');
		customer.setCustomerId(user);
		
		System.out.println(customer);
		
		int result = customerMapper.updateCustomerBoard(customer);
		System.out.println("result = " + result);
		
		Customer updateCustomerBoard = customerMapper.getCustomerBoard(15);
		System.out.println(updateCustomerBoard);
		
		//assertThat(customer.getBoardNumber()).isEqualTo(5);
		assertThat(updateCustomerBoard.getBoardTitle()).isEqualTo("TITLE correction");
		assertThat(updateCustomerBoard.getBoardContent()).isEqualTo("CONTENT correction");
		assertThat(updateCustomerBoard.getFAQCategoryCode()).isEqualTo(3);
		assertThat(updateCustomerBoard.getBoardType()).isEqualTo('r');
		assertThat(updateCustomerBoard.getCustomerId().getId()).isEqualTo("admin@naver.com");
		
		System.out.println("updateCustomerBoardTest end");
	}
	
	//@Test
	public void getCustomerBoardTest() throws Exception{
		System.out.println("getCustomerBoardTest start");
		
		Customer getCustomerBoard = customerMapper.getCustomerBoard(19);
		
		assertThat(getCustomerBoard.getBoardTitle()).isEqualTo("TITLE correction");
		assertThat(getCustomerBoard.getBoardContent()).isEqualTo("CONTENT correction");
		assertThat(getCustomerBoard.getFAQCategoryCode()).isEqualTo(4);
		assertThat(getCustomerBoard.getBoardType()).isEqualTo('N');
		assertThat(getCustomerBoard.getCustomerId().getId()).isEqualTo("admin@naver.com");
		
		System.out.println("getCustomerBoardTest end");
				
	}
	
	@Test
	public void getCustomerBoardListTest() throws Exception{
		System.out.println("getCustomerBoardListTest start");
		
		System.out.println("getCustomerBoardListTest end");
	}
	


}
