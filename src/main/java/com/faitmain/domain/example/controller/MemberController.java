package com.faitmain.domain.example.controller;

import com.faitmain.domain.example.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping( value = "/api/v1/app/" )
public class MemberController{

    @Autowired
    MemberService memberService;


//    @RequestMapping( value = "getOrderList", method = RequestMethod.POST )
//    public ResponseEntity< ? > getOrderList(){
//        Response response = new Response();
//        response.setResultCode( "50001" );
//        response.setRes( memberService.getOrderList() );
//        return new ResponseEntity<>( response , HttpStatus.OK );
//    }
}
