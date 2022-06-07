package com.faitmain.domain.customer.service;


import java.util.List;

import com.faitmain.domain.customer.domain.BanStatus;
import com.faitmain.domain.customer.domain.Customer;
import com.faitmain.global.common.Image;

public interface CustomerService {

	//INSERT
	public int addCustomerBoard(Customer customer) throws Exception;
	
	public void addCustomerBoard(Image image) throws Exception;
	
	public Customer getCustomerBoard(int boardNumber) throws Exception;
	
	public int updateCustomerBoard(Customer customer) throws Exception;
	
	public int deleteCustomerBoard(int boardNumber) throws Exception;

	public List<Customer> getCustomerBoardList() throws Exception;
	
	public BanStatus updateBanStatus(int reportNumber) throws Exception;

	public int updateBanStatus(BanStatus banStatus) throws Exception;


	
	
	/*
	 * public void increaseViewCount(int customer_board_number, HttpSession session)
	 * throws Exception;
	 */
}
