package com.faitmain.domain.order.service;

import com.faitmain.domain.order.domain.Order;
import com.faitmain.domain.order.domain.OrderCancel;
import com.faitmain.domain.order.domain.OrderPageProduct;
import com.faitmain.domain.user.domain.User;
import com.faitmain.global.common.Criterion;

import java.util.List;


public interface OrderService{


    /* 주문자 주소 정보 */
    User getBuyer( String buyerId );

    /* 주문 정보 */
    /* 주문 페이지로 전송할 상품 정보 메소드 */
    List<OrderPageProduct> getOrderPageProductList( List<OrderPageProduct> orderPageProductList );

    /* 주문 상품 리스트 */
    List<Order> getOrderList( Criterion criterion ) throws Exception;

    /* 주문 */
    void addOrder( Order order ) throws Exception;

    /* 주문 총 개수 */
    int getOrderTotal( Criterion criterion ) throws Exception;

    /* 주문 취소 */
    void cancelOrder( OrderCancel orderCancel ) throws Exception;



}
