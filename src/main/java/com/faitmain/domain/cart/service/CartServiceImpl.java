package com.faitmain.domain.cart.service;

import java.util.List;

import com.faitmain.domain.web.domain.AttachImage;
import com.faitmain.domain.web.mapper.AttachMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faitmain.domain.cart.domain.Cart;
import com.faitmain.domain.cart.mapper.CartMapper;

import lombok.RequiredArgsConstructor;

@Service("cartServiceImpl")
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	@Autowired
	private CartMapper cartMapper;

	@Autowired
	private AttachMapper attachMapper;
	
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
	public List<Cart> getCartList( String buyerId ) throws Exception{
		List<Cart> cartList = cartMapper.getCartList( buyerId );
		for ( Cart cart : cartList ) {
			/* 총합 정보 초기화 */
			cart.initSaleTotal();
			/* 이미지 정보 */
			int productNumber = cart.getProductNumber();
			List<AttachImage> imageList = attachMapper.getAttachList( productNumber );
			cart.setImageList( imageList );
		}
		return cartList;
	}

	/* 카트 수량 수정 */
	@Override
	public int updateProductOrderCount( Cart cart ) throws Exception{
		return cartMapper.updateCart( cart );
	}

	/* 카트 삭제 */
	@Override
	public int deleteCart( int cartNumber ){
		return cartMapper.deleteCart( cartNumber );
	}


}