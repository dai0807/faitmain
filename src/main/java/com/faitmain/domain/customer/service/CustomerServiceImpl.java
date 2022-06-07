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

import lombok.RequiredArgsConstructor;


@Service("customerServiceImpl")				//비즈니스 로직을 처리하는 서비스 클래스를 나타내는 어노테이션(해당 어노테이션 사용하여 스프링의 MVC의 서비스임을 나타냄)
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerMapper customerMapper;	//데이터베이스에 접근하는 DAO bean을 선언 
	
	public int customerCount() {
		return customerMapper.customerCount();		//게시글 수 반환
	}
	
	public List<Customer> getCustomerBoardList() throws Exception{
		return customerMapper.getCustomerBoardList();	//게시글 리스트 반환
	}
	
	public Customer getCustomerBoard(int boardNumber) {
		return customerMapper.getCustomerBoard(boardNumber);
	}
//	
//
//	public CustomerServiceImpl(CustomerMapper customerMapper) {
//		this.customerMapper = customerMapper;
//	}
//	
//	public CustomerServiceImpl() {
//	}
//	
//	@Override
//	public int addCustomerBoard(Customer customer) throws Exception {
//		customerMapper.addCustomerBoard(customer);
//		return customerMapper.addCustomerBoard(customer);
//		
//	}
//	
//	@Override
//	public void addCustomerBoard(Image image) throws Exception {
//		customerMapper.addCustomerBoardImage(image);
//		
//	}
//	
//	@Override
//	public Customer getCustomerBoard(int boardNumber) throws Exception{ 
//		return customerMapper.getCustomerBoard(boardNumber); 
//	}
//	  
//	@Override
//	public int updateCustomerBoard(Customer customer) throws Exception{ 
//		 return customerMapper.updateCustomerBoard(customer);
//	  
//	  }
//	  
//	@Override
//	public List<Customer> getCustomerBoardList() throws Exception {
//		  return customerMapper.getCustomerBoardList(); 		//사용자 요청을 처리하기 위한 비즈니스 로직을 구현, 데이터를 조회하도록 CustomerMapper class의 getCustomerBoardList 메소드 호출
//	}
//	  
//	@Override
//	public int deleteCustomerBoard(int boardNumber) throws Exception {
//		  return customerMapper.deleteCustomerBoard(boardNumber);
//	  
//	}
//	
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

	
	
}
