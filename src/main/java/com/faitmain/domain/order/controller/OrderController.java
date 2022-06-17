package com.faitmain.domain.order.controller;

import com.faitmain.domain.order.domain.Order;
import com.faitmain.domain.order.domain.OrderCancel;
import com.faitmain.domain.order.domain.OrderPage;
import com.faitmain.domain.order.service.OrderServiceImpl;
import com.faitmain.domain.order.service.PaymentServiceImpl;
import com.faitmain.domain.user.domain.User;
import com.faitmain.domain.user.service.UserServiceImpl;
import com.faitmain.global.common.Criterion;
import com.faitmain.global.common.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping( "/order" )
public class OrderController{

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private PaymentServiceImpl paymentService;

    @Autowired
    private UserServiceImpl userSerivce;


    @PostMapping( "/{buyerId}" )
    public String orderPage( @PathVariable String buyerId , OrderPage orderPage , Model model ){


        model.addAttribute( "orderPageProductList" , orderService.getOrderPageProductList( orderPage.getOrderPageProductList() ) );
        model.addAttribute( "buyer" , orderService.getBuyer( buyerId ) );

        log.info( "orderPageProductList = {} " , orderPage.getOrderPageProductList() );
        log.info( "buyer = {} " , orderService.getBuyer( buyerId ) );

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


    /* IAMPORT 결제 로직 */
    @PostMapping( "/complete" )
    public String paymentComplete( HttpSession session , Order order ) throws IOException{

        User user = new User();
        user.setId( order.getBuyerId() );
        try {
            userSerivce.getUser( user.getId() );
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        // 1. 아임포트 API 키와 SECRET키로 토큰을 생성
        String token = paymentService.getToken();
        log.info( "token = {}" , token );
        log.info( "order = {}" , order );

        /* 결제 완료된 금액 */
        int amount = paymentService.paymentInfo( order.getImpUid() , token );
        log.info( "amount = {}" , amount );

        try {
            log.info( "/* 결제 검증로직 시작 */" );


            int usingPoint = order.getUsingPoint();
            log.info( "/* 주문 시 사용한 포인트 */" );
            log.info( "usingPoint = {}" , usingPoint );

            if ( user  != null ) {
                int point = user.getTotalPoint();
                log.info( "point = {}" , point );


                if ( point < usingPoint ) {
                    log.info( "/* 사용된 포인트가 유저의 포인트보다 많을 때 */" );
                    paymentService.paymentCancel( token , order.getImpUid() , amount , "유저 포인트 오류" );
                    return "index";
                } else {

                    if ( usingPoint != 0 ) {
                        log.info( "/* 로그인 하지 않았는데 포인트가 사용되었을 때 */" );
                        paymentService.paymentCancel( token , order.getImpUid() , amount , "비회원 포인트사용 오류" );
                        return "index";
                    }
                }
            }

            orderService.addOrder( order );
            return "order/list";

        } catch ( Exception e ) {
            paymentService.paymentCancel( token , order.getImpUid() , amount , "결제 에러" );
            return "index";
        }
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


    @GetMapping( "/pay" )
    public String pay(){

        return "order/sample";
    }
}











































