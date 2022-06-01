package com.faitmain.domain.product.domain;

import lombok.Data;

@Data
public class Review {

	private int reviewNumber;
	private String reviewContent;
	private String reviewImage;
	private int rating;
	
}
