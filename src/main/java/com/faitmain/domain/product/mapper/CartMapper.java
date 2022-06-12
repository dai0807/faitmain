package com.faitmain.domain.product.mapper;

import com.faitmain.domain.product.domain.Cart;
import com.faitmain.global.common.Search;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper{

    /* 카트 추가 */
    int addCart( Cart cart ) throws Exception;

    /* 카트 제거 (주문) */
    int deleteCart( Cart cart );

    /* 카트 수량 수정 */
    int updateCart( Cart cart ) throws Exception;

    /* 카트 확인 */
    Cart getCart( Cart cart ) throws Exception;

    /* 카트 목록 */
    List<Cart> getCartList( Search search ) throws Exception;

}