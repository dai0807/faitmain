package com.faitmain.domain.order.controller;

import com.faitmain.domain.order.domain.Order;
import com.faitmain.domain.order.domain.OrderPage;
import com.faitmain.domain.order.service.OrderService;
import com.faitmain.domain.product.service.ProductService;
import com.faitmain.domain.user.domain.User;
import com.faitmain.domain.user.service.UserSerivce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class OrderController{


    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserSerivce userSerivce;

    @GetMapping( "/order/{id}" )
    public String orderPageGET( @PathVariable String id , OrderPage orderPage , Model model ){

        log.info( "id = {} " , id );
        log.info( "orderBundle = {} " , orderPage.getOrderBundle() );

        model.addAttribute( "orderList" , orderService.getProductInfo( orderPage.getOrderBundle() ) );
        model.addAttribute( "buyerInfo" , userSerivce.getBuyerInfo( id ) );

        return "view/order/order";
    }

    @PostMapping( "/order" )
    private String orderPagePOST( Order order , HttpServletRequest request ) throws Exception{

        log.info( "order ={}" , order );

        orderService.order( order );

        User user = new User();
        user.setId( order.getBuyerId() );

        HttpSession session = request.getSession();

        try {
            User userLogin = userSerivce.getUser( user.getId() );
            userLogin.setPassword( "" );
            session.setAttribute( "user" , userLogin );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return "redirect:/index";
    }

}











































