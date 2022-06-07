
package com.faitmain.domain.customer.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.faitmain.domain.customer.domain.BanStatus;
import com.faitmain.domain.customer.domain.Customer;
import com.faitmain.global.common.Image;

@Mapper
public interface CustomerMapper {
	
	public int customerCount();
	
	public List<Customer> getCustomerBoardList();
	
	public Customer getCustomerBoard(int boardNumber);

//	//CustomerBoard
//	
//	//INSERT 
//	public int addCustomerBoard(Customer customer) throws Exception;
//	
//	//INSERT
//	public void addCustomerBoardImage(Image image) throws Exception;
//	
//	//UPDATE
//	public int updateCustomerBoard(Customer customer) throws Exception;
//	
//	//UPDATE
//	public void updateCustomerBoardImage(Image image) throws Exception;
//	
//	//SELECT Detail
//	public Customer getCustomerBoard(int boardNumber) throws Exception;
//	
//	//SELECT
//	public Image getCustomerBoardImage(int boardNumber) throws Exception;
//	
//	//SELECT List
//	public List<Customer> getCustomerBoardList() throws Exception;
//	
//	//SELECT
//	public int deleteCustomerBoard(int boardNumber) throws Exception;
//	
//	//DELETE
//	public int DeleteCustomerBoard(Customer customer) throws Exception;
//	
//	
//
//	//BanStatus
//	public int updateBanStatus(BanStatus banStatus) throws Exception;
}

