package com.faitmain.domain.product.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faitmain.domain.product.domain.Review;
import com.faitmain.domain.product.mapper.ReviewMapper;

import lombok.RequiredArgsConstructor;

@Service("reviewServiceImpl")
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewMapper reviewMapper;
	
	public ReviewServiceImpl(ReviewMapper reviewMapper) {
		this.reviewMapper = reviewMapper;
	}
	
	@Override
	public void addReview(Review review) throws Exception {
		reviewMapper.addReview(review);		
	}

	@Override
	public Review getReview(int reviewNumber) throws Exception {
		return reviewMapper.getReview(reviewNumber);
	}

	@Override
	public List<Review> getReviewList(Map<String, String> map) throws Exception {
		List<Review> list = reviewMapper.getReviewList(map);
		return list;
	}

	@Override
	public void updateReview(Review review) throws Exception {
		reviewMapper.updateReview(review);		
	}

	@Override
	public void deleteReview(int reviewNumber) throws Exception {
		reviewMapper.deleteReview(reviewNumber);		
	}	

}