package com.faitmain.domain.product.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class Cart {

	private int cartNumber;

	private String userId;

	private int productNumber;

	private Date cartCookieLimit;

	private String cartCookieId;

	private String cartOptionContent;

	private int cartOptionNumber;

	private Product cartProduct;
	private int cartQuantity;

	@Builder
	public Cart(int cartNumber, int cartQuantity) {
		this.cartNumber = cartNumber;
		this.cartQuantity = cartQuantity;
	}
	
	public Cart() {
		
	}
}
