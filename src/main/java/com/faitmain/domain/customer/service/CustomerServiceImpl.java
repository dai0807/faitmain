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

@Slf4j
@Service("customerServiceImpl")
@Transactional

public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	
	private CustomerMapper customerMapper;
	

	public void setCustomerMapper(CustomerMapper customerMapper) {
		this.customerMapper = customerMapper;
	}
	
	public CustomerServiceImpl() {
	}
	
	@Override
	public int addCustomerBoard(Customer customer) throws Exception {
		customerMapper.addCustomerBoard(customer);
		return customerMapper.addCustomerBoard(customer);
		
	}
	
	@Override
	public void addCustomerBoard(Image image) throws Exception {
		customerMapper.addCustomerBoardImage(image);
		
	}
	
	@Override
	public Customer getCustomerBoard(int boardNumber) throws Exception{ 
		return customerMapper.getCustomerBoard(boardNumber); 
	}
	  
	@Override
	public int updateCustomerBoard(Customer customer) throws Exception{ 
		 return customerMapper.updateCustomerBoard(customer);
	  
	  }
	  
	@Override
	public List<Customer> getCustomerBoardList() throws Exception {
		  return customerMapper.getCustomerBoardList(); 
	}
	  
	@Override
	public int deleteCustomerBoard(int boardNumber) throws Exception {
		  return customerMapper.deleteCustomerBoard(boardNumber);
	  
	}
	
	@Override
	public int updateBanStatus(BanStatus banStatus) throws Exception{ 
		  return customerMapper.updateBanStatus(banStatus); 
	}

	@Override
	public BanStatus updateBanStatus(int reportNumber) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
