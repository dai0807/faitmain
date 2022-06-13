package com.faitmain.domain.cart.domain;

import com.faitmain.domain.web.domain.AttachImage;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Cart{

	/* 장바구니 -> 주문 */
	private int cartNumber;

	private String buyerId;

	private int ProductNumber;
	private int productOrderCount;


	/* PRODUCT */

	private String productName;

	private int productPrice;


	/* ADD */

	private int salePrice;

	private int totalPrice;

	private int point;

	private int totalPoint;


	/* 상품 이미지 */
	private List<AttachImage> imageList;


	/* METHOD */

	public void initSaleTotal(){
		this.salePrice = this.productPrice;
		this.totalPrice = this.salePrice * this.productOrderCount;
		this.point = ( int ) Math.floor( this.salePrice * 0.05 );
		this.totalPoint = this.point*this.productOrderCount;
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
