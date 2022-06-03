package com.faitmain.domain.order.mapper;

import com.faitmain.domain.order.domain.Order;
import com.faitmain.global.common.Search;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper{

    //insert
    int addOrder( Order order );

    //update
    int updateOrder( Order order );

    //selectOne
    Order getOrder( int orderNumber );

    //selectList
    List< Order> getOrderList();

    //paging
    int getTotalCount( Search search );

}
