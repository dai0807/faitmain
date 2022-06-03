package com.faitmain.domain.order.service;

import com.faitmain.domain.order.domain.Order;

import java.util.List;
import java.util.Map;

public interface OrderService{

    void addOrder( Order order ) throws Exception;

    int updateOrder( Order order ) throws Exception;

    Order getOrder( int OrderNumber ) throws Exception;


    Map< String, Object > getOrderList( );
}
