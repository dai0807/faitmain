package com.faitmain.domain.product.service;

import java.util.Map;

import com.faitmain.domain.product.domain.Review;
import com.faitmain.global.common.Search;

public interface ReviewService {
	
	//리뷰 등록
	public void addReview(Review review) throws Exception;
	
	//리뷰 상세 조회
	public Review getReview(int reviewNumber) throws Exception;
		
	//리뷰 목록 조회
	public Map<String, Object> getReviewList(Search search) throws Exception;
		
	//리뷰 수정
	public void updateReview(Review review) throws Exception;
		
	//리뷰 삭제
	public void deleteReview(int reviewNumber) throws Exception;
	
}