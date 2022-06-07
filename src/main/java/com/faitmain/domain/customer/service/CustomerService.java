package com.faitmain.domain.customer.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.faitmain.domain.customer.domain.BanStatus;
import com.faitmain.domain.customer.domain.Customer;
import com.faitmain.global.common.Image;


public interface CustomerService {

	public int customerCount();
	
	public List<Customer> getCustomerBoardList() throws Exception;
	
	public Customer getCustomerBoard(int boardNumber); 
	
//	//INSERT BOARD
//	public int addCustomerBoard(Customer customer) throws Exception;
//	
//	//INSERT IMAGE
//	public void addCustomerBoard(Image image) throws Exception;
//	
//	//GET BOARD DETAIL
//	public Customer getCustomerBoard(int boardNumber) throws Exception;
//	
//	//UPDATE BOARD
//	public int updateCustomerBoard(Customer customer) throws Exception;
//	
//	//DELETE BOARD
//	public int deleteCustomerBoard(int boardNumber) throws Exception;
//
//	//GET BOARD LIST
//	List<Customer> getCustomerBoardList() throws Exception;
//	
//	//UPDATE BANSTATUS
//	public BanStatus updateBanStatus(int reportNumber) throws Exception;
//
//	
//	public int updateBanStatus(BanStatus banStatus) throws Exception;


	
	
	/*
	 * public void increaseViewCount(int customer_board_number, HttpSession session)
	 * throws Exception;
	 */
}
