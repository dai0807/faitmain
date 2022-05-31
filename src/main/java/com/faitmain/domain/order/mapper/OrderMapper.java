package com.faitmain.domain.order.mapper;

import com.faitmain.domain.order.domain.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper{

    //insert
    void addOrder( Order order );

    //update
    void updateOrder( Order order );

    //select
    Order getOrder( int orderNumber ) throws Exception;





}
