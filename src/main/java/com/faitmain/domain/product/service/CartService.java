package com.faitmain.domain.product.service;

import java.util.Map;

import com.faitmain.domain.product.domain.Cart;
import com.faitmain.global.common.Search;

public interface CartService {
	
	/* 장바구니 추가 */
	int addCart(Cart cart) throws Exception;
	
	//장바구니 조회
	public Cart getCart(Cart cart) throws Exception;
	
	//장바구니 목록 조회
	public Map<String, Object> getCartList(Search search) throws Exception; 
	
	//장바구니 수정
	public void updateCart(Cart cart) throws Exception;
	
	//장바구니 삭제
	public void deleteCart(int cartNumber) throws Exception;







}