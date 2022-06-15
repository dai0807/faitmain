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
    public ResponseEntity<String > paymentComplete( HttpSession session , Order order  ) throws IOException{

        // 1. 아임포트 API 키와 SECRET키로 토큰을 생성
        String token = paymentService.getToken();
        log.info( "token = {}" , token );

        // 2. 토큰으로 결제 완료된 주문정보를 가져옴
        int amount = paymentService.paymentInfo( order.getImpUid() , token );


        // 3. 로그인하지 않았는데 사용포인트가 0 이상일경우 결제 취소

        // 4. 로그인 사용자가 현재포인트보다 사용포인트가 많을 경우 결제 취소

        // 5. DB에서 실제 계산되어야 할 가격가져오기

        // 6. 결제 완료된 금액과 실제 계산되어야 할 금액이 다를경우 결제 취소

        // 7. 결제에러시 결제 취소


        return new ResponseEntity<>( HttpStatus.OK);
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











































