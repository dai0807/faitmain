package com.faitmain.domain.product.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Cart {

	/* 장바구니 -> 주문 */
    private int cartNumber;

	private String userId;
	private int productOrderCount;

	private int ProductNumber;

	private Product cartProduct;

	//추가
	private int totalPrice;



	/* 상품상세 -> 주문 */
	private List<Product> product;















	/***********************************/




	//사용할지 안할지 모름//
	private Date cartCookieLimit;

	private String cartCookieId;

	private String cartOptionContent;

	private int cartOptionNumber;	
	//////////////////////////////
	
	@Builder
	public Cart(int cartNumber, int cartQuantity) {
		this.cartNumber = cartNumber;
		this.productOrderCount = cartQuantity;
	}
	
	public Cart() {
		
	}
}
