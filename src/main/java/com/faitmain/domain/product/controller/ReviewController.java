package com.faitmain.domain.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faitmain.domain.product.service.ReviewService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/review/")
@Slf4j
public class ReviewController {

	@Autowired
	@Qualifier("reviewServiceImpl")
	ReviewService reviewService;

	public ReviewController() {
		System.out.println(this.getClass());
	}
	
	@GetMapping("addReview")
	public String addReview() throws Exception{
		
		log.info("/review/addReview : GET");
				
		return "redirect:/review/addReview.jsp";
	}
	
}
