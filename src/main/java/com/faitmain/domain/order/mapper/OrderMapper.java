package com.faitmain.domain.order.mapper;

import com.faitmain.domain.order.domain.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper{

    void insertOrder( Order order );
}
