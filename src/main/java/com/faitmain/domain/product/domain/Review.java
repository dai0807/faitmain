package com.faitmain.domain.product.domain;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
public class Review {

	private int reviewNumber;
	private String reviewContent;
	private String reviewImage;
	private int rating;
	private String userId;
	private int orderNumber;
	private Product orderProduct;
	private Date reviewRegDate;
	
	
	@Builder
	public Review(int reviewNumber, String reviewContent) {
		this.reviewNumber = reviewNumber;
		this.reviewContent = reviewContent;
	}


	public Review() {
	}
	
}