package com.faitmain.domain.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faitmain.domain.product.domain.Cart;
import com.faitmain.domain.product.mapper.CartMapper;
import com.faitmain.global.common.Search;

import lombok.RequiredArgsConstructor;

@Service("cartServiceImpl")
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	@Autowired
	private CartMapper cartMapper;
	
	public CartServiceImpl(CartMapper cartMapper) {
		this.cartMapper = cartMapper;
	}
	
	@Override
	public void addCart(Cart cart) throws Exception {
		cartMapper.addCart(cart);
	}

	@Override
	public Cart getCart(Cart cart) throws Exception {
		return cartMapper.getCart(cart);
	}
	
	@Override
	public Map<String, Object> getCartList(Search search) throws Exception {
		List<Cart> list = cartMapper.getCartList(search);
		int totalCount = cartMapper.getTotalCount(search);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", list);
		resultMap.put("totalCount", new Integer(totalCount));
				
		return resultMap;
	}

	@Override
	public void updateCart(Cart cart) throws Exception {
		cartMapper.updateCart(cart);		
	}

	@Override
	public void deleteCart(int cartNumber) throws Exception {
		cartMapper.deleteCart(cartNumber);		
	}
	
}