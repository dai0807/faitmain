package com.faitmain.domain.product.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Cart {

    public int ProductNumber;
    private int cartNumber;

	private String userId;
	
	private int cartQuantity;
	
	private Product cartProduct;

	//Product	
	private List<Product> product;
	
	//추가
	private int totalPrice;
	
	//사용할지 안할지 모름//
	private Date cartCookieLimit;

	private String cartCookieId;

	private String cartOptionContent;

	private int cartOptionNumber;	
	//////////////////////////////
	
	@Builder
	public Cart(int cartNumber, int cartQuantity) {
		this.cartNumber = cartNumber;
		this.cartQuantity = cartQuantity;
	}
	
	public Cart() {
		
	}
}
