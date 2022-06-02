package com.faitmain.domain.order.service;

import com.faitmain.domain.order.domain.Order;

import java.util.List;

public interface OrderService{

    void addOrder( Order order ) throws Exception;

    int updateOrder( Order order ) throws Exception;

    Order getOrder( int OrderNumber ) throws Exception;

    List< Order > getOrderList() throws Exception;


}
