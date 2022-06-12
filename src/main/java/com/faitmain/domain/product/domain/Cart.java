package com.faitmain.domain.product.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Cart{

	/* 장바구니 -> 주문 */
	private int cartNumber;

	private String userId;

	private int ProductNumber;
	private int productOrderCount;





	/* PRODUCT */

	private String productName;

	private int productPrice;


	/* ADD */

	private int salePrice;

	private int totalPrice;


	/* METHOD */

	public void initSaleTotal(){
		this.salePrice = this.productPrice;
		this.totalPrice = this.salePrice * this.productOrderCount;
	}


	//////////////////////////////

	@Builder
	public Cart( int cartNumber , int cartQuantity ){
		this.cartNumber = cartNumber;
		this.productOrderCount = cartQuantity;
	}

	public Cart(){

	}
}
