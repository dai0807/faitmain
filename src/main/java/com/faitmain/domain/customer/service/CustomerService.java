package com.faitmain.domain.customer.service;


import java.util.List;

import com.faitmain.domain.customer.domain.Customer;

public interface CustomerService {

	public int addCustomerBoard(Customer customer) throws Exception;
	
	public Customer getCustomerBoard(int boardNumber) throws Exception;
	
	public int updateCustomerBoard(Customer customer) throws Exception;
	
	public int deleteCustomerBoard(int boardNumber) throws Exception;

	public List<Customer> getCustomerBoardList() throws Exception;
	
	public int processBanPeriod(int reportNumber) throws Exception;
	
	/*
	 * public void increaseViewCount(int customer_board_number, HttpSession session)
	 * throws Exception;
	 */
}
