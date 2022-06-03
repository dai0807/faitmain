package com.faitmain.domain.order.service;

import com.faitmain.domain.order.domain.Order;

import java.util.List;
import java.util.Map;

public interface OrderService{

    //주문추가
    void addOrder( Order order ) throws Exception;

    //주문상태업데이트
    int updateOrder( Order order ) throws Exception;

    //주문확인
    Order getOrder( int OrderNumber ) throws Exception;

    //주문조회
    Map< String, Object > getOrderList( ) throws Exception;
}
