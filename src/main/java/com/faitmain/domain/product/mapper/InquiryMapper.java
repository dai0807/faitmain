package com.faitmain.domain.product.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.faitmain.domain.product.domain.Inquiry;

@Mapper
public interface InquiryMapper {

	//INSERT - 문의 등록
	public void addInquiry(Inquiry inquiry) throws Exception;
	
	//SELECT - 문의 상세 조회
	public Inquiry getInquiry(int inquiryNumber) throws Exception;
	
	//SELECT - 문의 목록 조회
	public List<Inquiry> getInquiryList(Map<String, String> map) throws Exception;
	
	//SELECT - 문의 count
	public int getTotalCount(Map<String, String> map) throws Exception;
	
	//UPDATE - 문의 수정
	public void updateInquiry(Inquiry inquiry) throws Exception;
	
	//DELETE - 문의 삭제
	public void deleteInquiry(int inquiryNumber) throws Exception;
	
}