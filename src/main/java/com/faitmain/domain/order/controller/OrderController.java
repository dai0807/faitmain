package com.faitmain.domain.order.controller;

import com.faitmain.domain.order.domain.Order;
import com.faitmain.domain.order.domain.OrderCancel;
import com.faitmain.domain.order.domain.OrderPage;
import com.faitmain.domain.order.domain.OrderPageProduct;
import com.faitmain.domain.order.service.OrderService;
import com.faitmain.domain.order.service.OrderServiceImpl;
import com.faitmain.domain.user.domain.User;
import com.faitmain.domain.user.service.UserSerivce;
import com.faitmain.domain.user.service.UserServiceImpl;
import com.faitmain.global.common.Criterion;
import com.faitmain.global.common.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequestMapping( "/order" )
public class OrderController{

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private UserServiceImpl userSerivce;


    @PostMapping( "/{buyerId}" )
    public String orderPage( @PathVariable String buyerId , OrderPage orderPage , Model model ){

        log.info( "buyerId = {} " , buyerId );
        log.info( "orderPageProductList = {} " , orderPage.getOrderPageProductList() );

        model.addAttribute( "orderPageProductList" , orderService.getOrderPageProductList( orderPage.getOrderPageProductList() ) );
        model.addAttribute( "buyer" , orderService.getBuyer( buyerId ) );

        return "order";
    }

    @PostMapping( "/add" )
    private String orderAdd( Order order , HttpServletRequest request ) throws Exception{

        log.info( "insertOrder ={}" , order );

        orderService.addOrder( order );
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
        return "redirect:index";
    }

    /* ************************* ADMIN *************************** */

    /* 주문현황 페이지*/
    @GetMapping( "/list" )
    public String orderList( Criterion criterion , Model model ) throws Exception{

        List<Order> orderList = orderService.getOrderList( criterion );
        if ( !orderList.isEmpty() ) {
            model.addAttribute( "orderList" , orderList );
            model.addAttribute( "pagemMaker" , new Page( criterion , orderService.getOrderTotal( criterion ) ) );
        } else {
            model.addAttribute( "listCheck" , "empty" );
        }
        return "admin/orderList";
    }

    /* 주문삭제 */
    @PostMapping( "/cancle" )
    public String orderCancel( OrderCancel orderCancel ) throws Exception{

        orderService.cancelOrder( orderCancel );
        return "redirect:admin/orderList?keyword=" + orderCancel.getKeyword() + "&PageAmount=" + orderCancel.getPageAmount() + "&pageNumber" + orderCancel.getPageNumber();
    }
}











































