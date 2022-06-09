package com.faitmain.domain.customer.service;


import java.util.Collections;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.intellij.lang.annotations.JdkConstants.TreeSelectionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faitmain.domain.customer.domain.BanStatus;
import com.faitmain.domain.customer.domain.Customer;
import com.faitmain.domain.customer.mapper.CustomerMapper;
import com.faitmain.domain.user.domain.User;
import com.faitmain.global.common.Image;

import lombok.RequiredArgsConstructor;



@Service("customerServiceImpl")				//비즈니스 로직을 처리하는 서비스 클래스를 나타내는 어노테이션(해당 어노테이션 사용하여 스프링의 MVC의 서비스임을 나타냄)
@RequiredArgsConstructor
@Transactional(readOnly = false)			// 선언적 트랜잭션, 적용된 범위에서는 트랜잭션 기능이 포함된 프록시 객체가 생성되어 자동으로 commit 혹은 rollback을 진행



public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerMapper customerMapper;	//데이터베이스에 접근하는 DAO bean을 선언 
	

	public Customer getCustomerBoard(int boardNumber) throws Exception {
		return customerMapper.getCustomerBoard(boardNumber);
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
	

	@Transactional
	public void addCustomerBoard(Customer customer) {
		 customerMapper.addCustomerBoard(customer);
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

	
	
	public int getViewCount(int boardNumber) {
		return customerMapper.getViewCount(boardNumber);
	}
	
	public void updateViewCount(int boardNumber, int temp) {
		customerMapper.updateViewCount(boardNumber, temp);
	}


	
	
}

