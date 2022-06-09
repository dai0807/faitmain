package com.faitmain.domain.product.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class Cart {

	private int cartNumber;
	private String userId;
	private Product product;
	private int cartQuantity;
	
	@Builder
	public Cart(int cartNumber, int cartQuantity) {
		this.cartNumber = cartNumber;
		this.cartQuantity = cartQuantity;
	}
	
	public Cart() {
		
	}
}
