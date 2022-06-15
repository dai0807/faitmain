package com.faitmain.domain.order.controller;

import com.faitmain.domain.order.domain.Payment;
import com.faitmain.domain.order.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping( "/payment" )
public class PaymentController{

    @Autowired
    PaymentServiceImpl paymentService;

    @ResponseBody
    @PostMapping( "/add" )
    public String paymentAdd( @RequestBody Payment payment ){
        /* 결제정보 검증 후 저장하기 */
        try {
            paymentService.addPaymentInfo( payment );
            return "ok";
        } catch ( Exception e ) {
            return "ng";
        }
    }

    @ResponseBody
    @PostMapping( "/complete" )
    public String complete( @RequestBody Payment payment ){

        /* 결제 정보 검증 및 저장하기 */
        /* 액세스 토큰 발급 받기 */
        return null;
    }
}
