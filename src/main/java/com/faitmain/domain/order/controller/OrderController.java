package com.faitmain.domain.order.controller;

import com.faitmain.domain.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping( "/order" )
public class OrderController{

    private final OrderService orderService;

    @Autowired
    public OrderController( OrderService orderService ){
        this.orderService = orderService;
    }

    @GetMapping( "/getOrder" )
    public String getOrder(){

        log.info( "CONTROLLER = {}" , this.getClass() );
        return "view/order/getOrder";
    }


    @GetMapping( "/payment" )
    public String payment(){

        log.info( " CONTROLLER = {} " , this.getClass() );
        return "view/order/payment";
    }

    @GetMapping( "/orderList" )
    public String testORder(){
        return "view/order/getOrderList";
    }
}
