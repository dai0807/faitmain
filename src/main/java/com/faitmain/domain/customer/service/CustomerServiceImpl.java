package com.faitmain.domain.customer.service;


import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.faitmain.domain.customer.domain.Customer;
import com.faitmain.domain.customer.mapper.CustomerMapper;

@Slf4j
@Service("customerServiceImpl")
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	@Qualifier("customerMapper")
	private CustomerMapper customerMapper;
	
	public void setCustomerMapper(CustomerMapper customerMapper) {
		this.customerMapper = customerMapper;
	}
	
	public CustomerServiceImpl() {
		log.info( "Service = {}", this.getClass() );
	}
	
	@Override
	public int addCustomerBoard(Customer customer) throws Exception {
		return customerMapper.addCustomerBoard(customer);
		
	}

	@Override
	public Customer getCustomerBoard(int boardNumber) throws Exception {
		return customerMapper.getCustomerBoard(boardNumber);
	}

	@Override
	public int updateCustomerBoard(Customer customer) throws Exception {
		return customerMapper.addCustomerBoard(customer);
		
	}

	@Override
	public List<Customer> getCustomerBoardList() throws Exception {
		return customerMapper.getCustomerBoardList();
	}

	@Override
	public int deleteCustomerBoard(int boardNumber) throws Exception {
		return customerMapper.deleteCustomerBoard(boardNumber);
		
	}
	
	public int processBanPeriod(int reportNumber) throws Exception{
		return customerMapper.processBanPeriod(reportNumber);
	}
}
