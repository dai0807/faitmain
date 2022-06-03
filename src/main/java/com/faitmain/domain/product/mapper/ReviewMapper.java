package com.faitmain.domain.product.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.faitmain.domain.product.domain.Review;

@Mapper
public interface ReviewMapper {
	
	//INSERT - 리뷰 등록
	public void addReview(Review review) throws Exception;
	
	//SELECT - 리뷰 상세 조회
	public Review getReview(int reviewNumber) throws Exception;
	
	//SELECT - 리뷰 목록 조회
	public List<Review> getReviewList(Map<String, String> map) throws Exception;
	
	//UPDATE - 리뷰 수정
	public void updateReview(Review review) throws Exception;
	
	//DELETE - 리뷰 삭제
	public void deleteReview(int reviewNumber) throws Exception;
	
}