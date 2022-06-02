package com.faitmain.domain.customer.mapper;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.faitmain.domain.customer.domain.Customer;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CustomerMapperTest{
	
	private static final Logger log = LoggerFactory.getLogger(CustomerMapperTest.class);
	
	@Autowired
	private CustomerMapper customerMapper;
	
	@Test
	public void testAddCustomerBoard() throws Exception {
		
		Customer customer = new Customer();
		
		
		customer.setCustomer_board_title("mapper test");
		customer.setCustomer_board_content("mapper test");
		customer.setCustomer_id("mapper test");
		
		customerMapper.addCustomerBoard(customer);
	}
	
//	@Test
//	public void testGetCustomerBoardList() throws Exception {
//		
//		List<Customer> list = customerMapper.getCustomerBoardList();
//		
//		for(int i=0; i<list.size(); i++) {
//			log.info("" + list.get(i));
//		}
//	}
	

}
