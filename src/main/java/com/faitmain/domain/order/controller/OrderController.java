package com.faitmain.domain.order.controller;

import com.faitmain.domain.order.domain.Order;
import com.faitmain.domain.order.domain.OrderCancel;
import com.faitmain.domain.order.domain.OrderPage;
import com.faitmain.domain.order.domain.OrderPageProduct;
import com.faitmain.domain.order.service.OrderService;
import com.faitmain.domain.order.service.OrderServiceImpl;
import com.faitmain.domain.order.service.PaymentServiceImpl;
import com.faitmain.domain.user.domain.User;
import com.faitmain.domain.user.service.UserSerivce;
import com.faitmain.domain.user.service.UserServiceImpl;
import com.faitmain.global.common.Criterion;
import com.faitmain.global.common.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> paymentComplete( HttpSession session , Order order, int totalPirce, User user ) throws IOException{

        // 1. 아임포트 API 키와 SECRET키로 토큰을 생성
        String token = paymentService.getToken();
        log.info( "token = {}" , token );

        /* 결제 완료된 금액 */
        int amount = paymentService.paymentInfo( order.getImpUid() , token );

        try {
            /* 주문 시 사용한 포인트 */
            int usingPoint = order.getUsingPoint();
            if ( user != null ) {
                int point = user.getTotalPoint();

                /* 사용된 포인트가 유저의 포인트보다 많을 때 */
                if ( point < usingPoint ) {
                    paymentService.paymentCancel( token , order.getImpUid() , amount , "유저 포인트 오류" );
                    return new ResponseEntity<String>( " 유저 포인트 오류" , HttpStatus.BAD_REQUEST );
                } else {
                    /* 로그인 하지 않았는데 포인트가 사용되었을 때 */
                    if ( usingPoint != 0 ) {
                        paymentService.paymentCancel( token , order.getImpUid() , amount , "비회원 포인트사용 오류" );
                        return new ResponseEntity<String>( "비회원 포인트 사용 오류 " , HttpStatus.BAD_REQUEST );
                    }
                }
            }
            /* 실제 계산 금액 가져오기 */
            order.getOrderPriceInfo();
            int orderFinalSalePrice = order.getOrderFinalSalePrice();

            /* 계산 된 금액과 실제 금액이 다를 때 */
            if ( orderFinalSalePrice != amount ) {
                paymentService.paymentCancel( token , order.getImpUid() , amount , "결제 금액 오류" );
                return new ResponseEntity<String>( "겸제 금액 오류" , HttpStatus.BAD_REQUEST );
            }

            orderService.addOrder( order );
            return new ResponseEntity<>( "주문이 완료되었습니다" , HttpStatus.OK );

        } catch ( Exception e ) {
            paymentService.paymentCancel( token , order.getImpUid() , amount , "결제 에러" );
            return new ResponseEntity<String>( "결제 에러" , HttpStatus.BAD_REQUEST );
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











































