package com.faitmain.domain.product.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.faitmain.domain.product.domain.Cart;

@Mapper
public interface CartMapper {

	//INSERT - 장바구니 등록
	public void addCart(Cart cart) throws Exception;
	
	//SELECT - 장바구니 목록 조회
	public List<Cart> getCartList(Map<String, String> map) throws Exception;
	
	//UPDATE - 장바구니 수정
	public void updateCart(Cart cart) throws Exception;
	
	//DELETE - 장바구니 삭제
	public void deleteCart(int cartNumber) throws Exception;
	
}