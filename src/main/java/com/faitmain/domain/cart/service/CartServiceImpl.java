package com.faitmain.domain.cart.service;

import java.util.List;

import com.faitmain.domain.order.domain.OrderPageProduct;
import com.faitmain.domain.web.domain.AttachImage;
import com.faitmain.domain.web.mapper.AttachMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faitmain.domain.cart.domain.Cart;
import com.faitmain.domain.cart.mapper.CartMapper;

import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private AttachMapper attachMapper;

    @Override
    public int addCart( Cart cart ) throws Exception{
       
    	int i = 0;
    	
        for ( OrderPageProduct opp : cart.getOrderPageProductList() ) {        	
            cart.setProductNumber( opp.getProductNumber() ); 
            
        	 /* 장바구니 데이터 체크*/
            Cart checkCart = cartMapper.selectCart( cart );
            if ( checkCart == null ) {
            	log.info("장바구니에 추가");
            	cart.setProductOrderCount( opp.getProductOrderCount());
                i = cartMapper.addCart( cart );
            }else {
            	log.info("이미 장바구니에 있는 상품");;
            	i = 2;
            }   
            
        }
        
        return i;

    }

    @Override
    public List<Cart> getCartList( String buyerId ) throws Exception{

        List<Cart> cartList = cartMapper.selectCartList( buyerId );

        for ( Cart cart : cartList ) {
            /* 총합 정보 초기화 */
            cart.initSaleTotal();
            /* 이미지 정보 */
            cart.setProductMainImage( cart.getProductMainImage() );
        }
        return cartList;
    }

    @Override
    public Cart getCart( Cart cart ) throws Exception{

        return null;
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