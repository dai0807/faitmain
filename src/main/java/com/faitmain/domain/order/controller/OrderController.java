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
import com.faitmain.global.util.UserInfoSessionUpdate;
import com.faitmain.global.util.log.LogTrace;
import com.faitmain.global.util.log.TraceStatus;
import com.faitmain.global.util.security.SecurityUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private UserServiceImpl userService;

    @Autowired
    private LogTrace trace;

    @GetMapping( "/{buyerId}" )
    public String orderPage( @PathVariable String buyerId , OrderPage orderPage , Model model ){

        TraceStatus status = null;
        try {
            status = trace.begin( "OrderController.orderPage()" );
            model.addAttribute( "orderPageProductList" , orderService.getOrderPageProductList( status.getTraceId() , orderPage.getOrderPageProductList() ) );
            model.addAttribute( "buyer" , orderService.getBuyer( status.getTraceId() , buyerId ) );
            log.info( "orderPageProductList = {} " , orderPage.getOrderPageProductList() );
            log.info( "buyer = {} " , orderService.getBuyer( status.getTraceId() , buyerId ) );
            trace.end( status );
            return "order";
        } catch ( Exception e ) {
            trace.exception( status , e );
            throw e;
        }
    }


    /* IAMPORT 결제 로직 */
    @PostMapping( "/complete" )

    public String paymentComplete( Order order , Criterion criterion , Model model ) throws Exception{

        TraceStatus status = null;

        User user = userService.getUser( order.getBuyerId() );
        log.info( "user = {}" , user );


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


//            List<Order> orderList = orderService.getOrderList( criterion );
//
//            if ( !orderList.isEmpty() ) {
//                model.addAttribute( "orderList" , orderList );
//                model.addAttribute( "pageMaker" , new Page( criterion , orderService.getOrderTotal( criterion ) ) );
//            } else {
//                model.addAttribute( "listCheck" , "empty" );
//            }
            return "order/orderList";


        } catch ( Exception e ) {
            paymentService.paymentCancel( token , order.getImpUid() , amount , "결제 에러" );
            return "index";
        }
    }



    /* ************************* ADMIN *************************** */

    /* 주문현황 페이지*/
    @GetMapping( "/list" )
    public String orderList( Criterion criterion , Model model ){

        List<Order> list = orderService.getOrderList( criterion );
        log.info( "orderList = {}" , list );
        if ( !list.isEmpty() ) {
            model.addAttribute( "list" , list );
            model.addAttribute( "pageMaker" , new Page( criterion , orderService.getOrderTotal( criterion ) ) );
        } else {
            model.addAttribute( "listCheck" , "empty" );
        }
        log.info( "model = {}" , model );
        return "order/orderList";
    }

    /* 주문삭제 */
    @PostMapping( "/cancle" )
    public String orderCancel( OrderCancel orderCancel ) throws Exception{

        orderService.cancelOrder( orderCancel );
        return "redirect:admin/orderList?keyword=" + orderCancel.getKeyword() + "&PageAmount=" + orderCancel.getPageAmount() + "&pageNumber" + orderCancel.getPageNumber();
    }


    @GetMapping( "/pay" )
    public String pay( @AuthenticationPrincipal SecurityUserService securityUserService ){

        return "order/sample";
    }
}











































