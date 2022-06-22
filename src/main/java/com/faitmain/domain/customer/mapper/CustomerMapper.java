package com.faitmain.domain.customer.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.faitmain.domain.customer.domain.BanStatus;
import com.faitmain.domain.customer.domain.Customer;

import com.faitmain.domain.user.domain.User;
import com.faitmain.global.common.Image;
import com.faitmain.global.common.Paging;


@Mapper
public interface CustomerMapper {



	//CustomerBoard
	
//	게시판 등록 
	public int addCustomerBoard(Customer customer) throws Exception;
	
//	게시판 상세조회
	public Customer getCustomerBoard(int boardNumber) throws Exception;
	
//	라이브가이드 상세조회
	public Customer getLiveGuide(char boardType) throws Exception;
	
//	게시판 수정
	public int updateCustomerBoard(Customer customer) throws Exception;
	
//	게시판 리스트 조회
	public List<Customer> getCustomerBoardList(char boardType) throws Exception;
	
//	게시판 삭제
	public int deleteCustomerBoard(int boardNumber) throws Exception;
	
//	게시판 총 개수
	public int getBoardTotalCount() throws Exception;
	
//	FAQ 리스트 조회(카테고리이용)	
	public List<Customer> getFAQList(String FAQCategoryCode) throws Exception;
	
// 게시판 목록(페이징 적용)
	public List<Customer> getListPaging(Paging paging) throws Exception;
	
	

	//INSERT
//	public void addCustomerBoardImage(Image image) throws Exception;
	
	//UPDATE
//	public void updateCustomerBoardImage(Image image) throws Exception;
	
	//SELECT
//	public Image getCustomerBoardImage(int boardNumber) throws Exception;
	
	//BanStatus
//	public int updateBanStatus(BanStatus banStatus) throws Exception;
	
	
	
}

