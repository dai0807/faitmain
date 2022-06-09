package com.faitmain.domain.customer.service;


import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faitmain.domain.customer.domain.BanStatus;
import com.faitmain.domain.customer.domain.Customer;
import com.faitmain.domain.customer.mapper.CustomerMapper;
import com.faitmain.global.common.Image;

import edu.emory.mathcs.backport.java.util.Collections;
import lombok.RequiredArgsConstructor;

@Slf4j
@Service("customerServiceImpl")
@Transactional
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerMapper customerMapper;
	


	
	public CustomerServiceImpl() {
	}
	
	@Override
	public boolean registerCustomerBoard(Customer customer) throws Exception {
		int queryResult = 0;													//queryResult : Query를 실행한 횟수 
		
		if(customer.getBoardNumber() == 0) {									//'0' -> int default
			queryResult = customerMapper.addCustomerBoard(customer);
		}else {
			queryResult = customerMapper.updateCustomerBoard(customer);
		}
		
		return(queryResult == 1) ? true : false ;
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
	public Customer getCustomerBoard(int boardNumber) throws Exception{ 
		return customerMapper.getCustomerBoard(boardNumber); 
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
	  
	@Override
	public boolean deleteCustomerBoard(int boardNumber) throws Exception {
		int queryResult = 0;
		
		Customer customer = customerMapper.getCustomerBoard(boardNumber);
		
		if(customer != null && "N".equals(customer.getDelete_yn())) {		// 삭제여부 - 'Y' : 삭제 x, 'N' : 삭제
			queryResult = customerMapper.deleteCustomerBoard(boardNumber);
		}
		
		return (queryResult == 1) ? true : false;
	  
	}
	
//	@Override
//	public int updateBanStatus(BanStatus banStatus) throws Exception{ 
//		  return customerMapper.updateBanStatus(banStatus); 
//	}
//
//	@Override
//	public BanStatus updateBanStatus(int reportNumber) throws Exception {
//		
//		return null;
//	}
//
//	@Override
//	public void addCustomerBoard(Image image) throws Exception {
//		customerMapper.addCustomerBoardImage(image);
//		
//	}

	
	
}

