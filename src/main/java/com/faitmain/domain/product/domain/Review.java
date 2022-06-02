package com.faitmain.domain.product.domain;

import java.sql.Date;

import com.faitmain.domain.order.domain.Order;

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
	
}
