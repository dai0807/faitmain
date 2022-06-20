package com.faitmain.domain.customer.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.faitmain.domain.customer.domain.BanStatus;
import com.faitmain.domain.customer.domain.Customer;
import com.faitmain.domain.user.domain.User;
import com.faitmain.global.common.Criterion;
import com.faitmain.global.common.Image;


public interface CustomerService {
	
//	게시판 등록
	public int addCustomerBoard(Customer customer) throws Exception;
	
//	게시판 수정
	public int updateCustomerBoard(Customer customer) throws Exception;
	
//	게시판 상세조회	
	public Customer getCustomerBoard(int boardNumber) throws Exception;

//	라이브가이드 상세조회
	public Customer getLiveGuide(char boardType) throws Exception;

//	게시판 삭제	
	public int deleteCustomerBoard(int boardNumber) throws Exception;

//	게시판 리스트 조회	
	public List<Customer> getCustomerBoardList(char boardType) throws Exception;

//	자주묻는질문 리스트 조회
	public List<Customer> getFAQCategoryCode(String FAQCategoryCode) throws Exception;
	
//	게시판 총 개수
	public int getBoardTotalCount() throws Exception;
	
// 게시판 목록(페이징 적용)
	public List<Customer> getListPaging(Criterion criterion) throws Exception;
	

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
