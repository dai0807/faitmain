package com.faitmain.domain.product.service;

import java.util.List;
import java.util.Map;

import com.faitmain.domain.product.domain.Cart;

public interface CartService {
	
	//장바구니 등록
	public void addCart(Cart cart) throws Exception;
	
	//장바구니 목록 조회
	public List<Cart> getCartList(Map<String, String> map) throws Exception; 
	
	//장바구니 수정
	public void updateCart(Cart cart) throws Exception;
	
	//장바구니 삭제
	public void deleteCart(int cartNumber) throws Exception;
	
}