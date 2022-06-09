package com.faitmain.domain.order.controller;

import com.faitmain.domain.order.domain.Order;
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
@RequestMapping( "/order" )
public class OrderController{


    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;




    @GetMapping( "/getOrder" )
    public String getOrder(){

        log.info( "CONTROLLER = {}" , this.getClass() );
        return "view/order/getOrder";
    }


    @GetMapping( "/payment" )
    public String papay(){
        return "view/order/payment";
    }

    @GetMapping( "/createOrder" )
    public String payment(){

        log.info( " CONTROLLER = {} " , this.getClass() );
        return "view/order/createOrder";
    }

    @GetMapping( "/orderList" )
    public String testORder(){
        return "view/order/getOrderList";
    }


    /*============================================================================*/

    @PostMapping( "/{id}" )
    public String ready( @PathVariable User user , @RequestParam Product product , Order order, Model model ) throws Exception{

        productService.getProduct( product.getProductNumber() );
        orderService.addOrder( order );
        model.addAttribute( "order" , order );
        return "getOrder";
    }
}
