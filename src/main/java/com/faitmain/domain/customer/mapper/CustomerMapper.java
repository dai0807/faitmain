
package com.faitmain.domain.customer.mapper;

import java.util.List;

import com.faitmain.domain.customer.domain.Customer;

public interface CustomerMapper {

	public void addCustomerBoard(Customer customer);
	
	public List<Customer> getCustomerBoardList();
}

