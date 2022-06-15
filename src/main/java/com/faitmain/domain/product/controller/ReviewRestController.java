package com.faitmain.domain.product.controller;

import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.faitmain.domain.product.domain.Inquiry;
import com.faitmain.domain.product.domain.Review;
import com.faitmain.domain.product.service.InquiryService;
import com.faitmain.domain.product.service.ReviewService;
import com.faitmain.global.common.Search;

import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping("/review/*")
@Slf4j
public class ReviewRestController {
	
	///Field
	@Autowired
	@Qualifier("reviewServiceImpl")
	private ReviewService reviewService;

	@GetMapping("json/getReview/{reviewNumber}")
	public Review getInquiry(@PathVariable int reviewNumber) throws Exception{
		
		log.info("/json/getReview/ = {}", reviewNumber);
		
		return reviewService.getReview(reviewNumber);
	}
	
}
