/*
package com.faitmain.domain.order.controller;

import com.faitmain.domain.order.domain.Payment;
import com.faitmain.domain.order.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping( "/payment" )
public class PaymentController{

    @Autowired
    PaymentServiceImpl paymentService;

    @ResponseBody
    @PostMapping( "/add" )
    public String paymentAdd( @ResponseBody Payment payment ){

        */
/* 결제 정보 검증 후 저장하기 *//*

        try {
            paymentService.paymentInfo( "33" , "erer" );
            return "";
        } catch ( Exception e ) {
            return "";
        }
    }

    @ResponseBody
    @PostMapping( "/complete" )
    public String complete( @ResponseBody Payment payment ){

    }
}
*/
