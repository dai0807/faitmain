package com.faitmain.domain.order.mapper;

import com.faitmain.domain.order.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper{

    //insert
    int addOrder( Order order );

    //update
    int updateOrder( Order order );

    //select
    Order getOrder( int orderNumber );


    List< Order> findAll();

}
