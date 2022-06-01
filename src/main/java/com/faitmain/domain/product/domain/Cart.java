package com.faitmain.domain.product.domain;

import lombok.Data;

@Data
public class Cart {

	private int cartNumber;
	private String userId;
	private Product cartProduct;
	private int cartQuantity;	
	
}
