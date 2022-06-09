package com.faitmain.domain.order.controller;

import com.faitmain.domain.order.domain.Order;
import com.faitmain.domain.order.domain.OrderPage;
import com.faitmain.domain.order.service.OrderService;
import com.faitmain.domain.product.domain.Product;
import com.faitmain.domain.product.service.ProductService;
import com.faitmain.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class OrderController{


    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping( "/order/{id}" )
    public void orderPageGET( @PathVariable String id , OrderPage orderPage , Model model ){

        log.info( "id = {} " , id );
        log.info( "orderBundle = {} " , orderPage.getOrderBundle() );

    }



}











































