package com.faitmain.domain.customer.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.faitmain.domain.customer.domain.BanStatus;
import com.faitmain.domain.customer.domain.Customer;
import com.faitmain.domain.user.domain.User;
import com.faitmain.global.common.Image;


public interface CustomerService {


	//INSERT, UPDATE 두가지 모두 등록하는 기능, BoardNumber 유무 기준으로 실행

	public boolean registerCustomerBoard(Customer params) throws Exception;
	
//	public int addCustomerBoard(Customer customer) throws Exception;
//	
//	public int updateCustomerBoard(Customer customer) throws Exception;
	
	public Customer getCustomerBoard(int boardNumber) throws Exception;
	
	public boolean deleteCustomerBoard(int boardNumber) throws Exception;
	

	public List<Customer> getCustomerBoardList() throws Exception;
	

//	public BanStatus updateBanStatus(int reportNumber) throws Exception;
//	
//	public int updateBanStatus(BanStatus banStatus) throws Exception;
//	
//	public void addCustomerBoard(Image image) throws Exception;
	
	
	
	
	

	
	
	/*
	 * public void increaseViewCount(int customer_board_number, HttpSession session)
	 * throws Exception;
	 */
}
