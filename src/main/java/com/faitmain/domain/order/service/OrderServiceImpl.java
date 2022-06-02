package com.faitmain.domain.order.service;

import com.faitmain.domain.order.domain.Order;
import com.faitmain.domain.order.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl( OrderMapper orderMapper ){
        this.orderMapper = orderMapper;
    }

    @Override
    public int addOrder( Order order ){
        return orderMapper.addOrder( order );
    }

    @Override
    public int updateOrder( Order order ){
        return orderMapper.updateOrder( order );
    }

    @Override
    public Order getOrder( int OrderNumber ){
        return orderMapper.getOrder( OrderNumber );
    }

    @Override
    public List< Order > getOrderList(){
        return orderMapper.findAll();
    }
}
