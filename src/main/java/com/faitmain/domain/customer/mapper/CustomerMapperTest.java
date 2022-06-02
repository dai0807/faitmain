package com.faitmain.domain.customer.mapper;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.faitmain.domain.customer.domain.Customer;


public class CustomerMapperTest{
	
	private static final Logger log = LoggerFactory.getLogger(CustomerMapperTest.class);
	
	@Autowired
	private CustomerMapper customerMapper;
	
	@Test
	public void testAddCustomerBoard() {
		
		Customer customer = new Customer();
		
		
		customer.setCustomer_board_title("mapper test");
		customer.setCustomer_board_content("mapper test");
		customer.setCustomer_id("mapper test");
		
	}
	
	@Test
	public void testGetCustomerBoardList() throws Exception {
		
		List<Customer> list = customerMapper.getCustomerBoardList();
		
		for(int i=0; i<list.size(); i++) {
			log.info("" + list.get(i));
		}
	}
	

}
