package com.faitmain.domain.product.service;

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
	public int addCart( Cart cart) throws Exception{

		/* 장바구니 데이터 체크*/
		Cart checkCart = cartMapper.getCart( cart );

		if ( checkCart != null ) {
			return 2;
		}

		/* 장바구니 등록 & 에러 시 0 반환 */
		try {
			return cartMapper.addCart( cart );
		} catch ( Exception e ) {
			return 0;
		}

	}

	@Override
	public Cart getCart(Cart cart) throws Exception {
		return cartMapper.getCart(cart);
	}

	@Override
	public Map<String, Object> getCartList( Search search ) throws Exception{
		return null;
	}

	@Override
	public void updateCart( Cart cart ) throws Exception{

	}

	@Override
	public void deleteCart( int cartNumber ) throws Exception{

	}



	
	/*@Override
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
	public int deleteCart(int cartNumber) throws Exception {
		cartMapper.deleteCart( cartNumber );
		return cartNumber;
	}*/
	
}