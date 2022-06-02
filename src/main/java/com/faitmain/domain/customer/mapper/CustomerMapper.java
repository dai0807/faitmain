
package com.faitmain.domain.customer.mapper;


import java.util.List;

import com.faitmain.domain.customer.domain.BanPeriod;
import com.faitmain.domain.customer.domain.Customer;

public interface CustomerMapper {

	//CustomerBoard
	public int addCustomerBoard(Customer customer) throws Exception;
	
	public Customer getCustomerBoard(int boardNumber) throws Exception;
	
	public int updateCustomerBoard(Customer customer) throws Exception;
	
	public List<Customer> getCustomerBoardList() throws Exception;

	public int deleteCustomerBoard(int boardNumber) throws Exception;

	//BanPeriod
	public int processBanPeriod(int reportNumber) throws Exception;
}

