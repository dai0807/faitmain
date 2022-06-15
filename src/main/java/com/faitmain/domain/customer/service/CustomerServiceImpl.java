package com.faitmain.domain.customer.service;


import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faitmain.domain.customer.domain.Customer;
import com.faitmain.domain.customer.mapper.CustomerMapper;





@Service									//비즈니스 로직을 처리하는 서비스 클래스를 나타내는 어노테이션(해당 어노테이션 사용하여 스프링의 MVC의 서비스임을 나타냄)
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerMapper customerMapper;	//데이터베이스에 접근하는 DAO bean을 선언 
	
	@Override
	public boolean registerCustomerBoard(Customer params) throws Exception {
		int queryResult = 0;													//queryResult : Query를 실행한 횟수
		
		if(params.getBoardNumber() == 0) {									//'0' -> int default
			queryResult = customerMapper.addCustomerBoard(params);
		}else {
			queryResult = customerMapper.updateCustomerBoard(params);
		}
		
		return(queryResult == 1) ? true : false ;
		
	}
	
	@Override
	public Customer getCustomerBoard(int boardNumber) throws Exception {
		return customerMapper.getCustomerBoard(boardNumber);
	}
	
	

//	@Override
//	public int addCustomerBoard(Customer customer) throws Exception {
//		customerMapper.addCustomerBoard(customer);
//		return customerMapper.addCustomerBoard(customer);
//		
//	}
	
//	@Override
//	public int updateCustomerBoard(Customer customer) throws Exception{ 
//		 return customerMapper.updateCustomerBoard(customer);
//	  
//	}

	@Override
	public boolean deleteCustomerBoard(int boardNumber) throws Exception {
		int queryResult = 0;
		
		Customer customer = customerMapper.getCustomerBoard(boardNumber);
		
		if(customer != null && "N".equals(customer.getDeleteYn())) {		// 삭제여부 - 'Y' : 삭제 x, 'N' : 삭제
			queryResult = customerMapper.deleteCustomerBoard(boardNumber);
		}
		
		return (queryResult == 1) ? true : false;
	}
	
	@Override
	public List<Customer> getCustomerBoardList() throws Exception {
				
		List<Customer> list = Collections.emptyList();
	
		int boardTotalCount = customerMapper.getBoardTotalCount();
		
		if(boardTotalCount > 0) {
			list = customerMapper.getCustomerBoardList();
		}
		
		return list; 
	}
	
	
	
		  

	
	
}

