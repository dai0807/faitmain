package com.faitmain.domain.order.service;

import com.faitmain.domain.order.domain.Order;
import com.faitmain.domain.order.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderMapper orderMapper;


    @Override
    public void addOrder( Order order ){
        orderMapper.addOrder( order );

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
        return orderMapper.getOrderList();
    }
}
