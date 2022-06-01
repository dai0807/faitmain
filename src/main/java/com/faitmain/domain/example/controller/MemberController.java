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


//    @RequestMapping( value = "findAll", method = RequestMethod.POST )
//    public ResponseEntity< ? > findAll(){
//        Response response = new Response();
//        response.setResultCode( "50001" );
//        response.setRes( memberService.findAll() );
//        return new ResponseEntity<>( response , HttpStatus.OK );
//    }
}
