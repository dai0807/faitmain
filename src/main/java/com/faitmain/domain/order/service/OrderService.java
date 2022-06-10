package com.faitmain.domain.order.service;

import com.faitmain.domain.order.domain.Order;
import com.faitmain.domain.order.domain.OrderCancle;
import com.faitmain.domain.order.domain.OrderPageProduct;
import com.faitmain.domain.user.domain.User;
import com.faitmain.global.common.Criterion;

import java.util.List;

public interface OrderService{


    /* 주문자 주소 정보 */
    User getBuyerInfo( String id );

    /* 주문정보 */
    List<OrderPageProduct> getProductInfo( List<OrderPageProduct> orderBundle );

    /* 주문 상품 리스트 */
    List<Order> getOrderList( Criterion criterion ) throws Exception;

    /* 주문 */
    void order( Order order ) throws Exception;

    /* 주문 총 개수 */
    int getOrderTotal( Criterion criterion ) throws Exception;

    /* 주문 취소 */
    void orderCancle( OrderCancle orderCancle ) throws Exception;



}
