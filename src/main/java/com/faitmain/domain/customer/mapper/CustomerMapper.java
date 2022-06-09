
package com.faitmain.domain.customer.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.faitmain.domain.customer.domain.BanStatus;
import com.faitmain.domain.customer.domain.Customer;
import com.faitmain.domain.user.domain.User;
import com.faitmain.global.common.Image;

@Mapper
public interface CustomerMapper {


	//CustomerBoard
	
	//INSERT 
	public int addCustomerBoard(Customer customer) throws Exception;
	
	//SELECT Detail
	public Customer getCustomerBoard(int boardNumber) throws Exception;
	
	//UPDATE
	public int updateCustomerBoard(Customer customer) throws Exception;
	
	//SELECT List
	public List<Customer> getCustomerBoardList() throws Exception;
	
	//DELETE
	public int deleteCustomerBoard(int boardNumber) throws Exception;
	//delete_yn column 이용해서 실제로 데이터 삭제하지않고 column 값을 Y or N 으로 지정하여 N일 경우에만 데이터가 노출되게끔
	
	public int getBoardTotalCount() throws Exception;
	
	//INSERT
//	public void addCustomerBoardImage(Image image) throws Exception;
	
	//UPDATE
//	public void updateCustomerBoardImage(Image image) throws Exception;
	
	//SELECT
//	public Image getCustomerBoardImage(int boardNumber) throws Exception;
	
	//BanStatus
//	public int updateBanStatus(BanStatus banStatus) throws Exception;
	
	
	
	
	
	
	
	

}

