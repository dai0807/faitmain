package com.faitmain.domain.example.controller;

import com.faitmain.domain.example.domain.Response;
import com.faitmain.domain.example.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( value = "/api/v1/app/" )
public class WebRestController{

    @Autowired
    MemberService memberService;

    @RequestMapping( value = "findAll", method = RequestMethod.POST )
    public ResponseEntity< ? > findAll(){
        Response response = new Response();
        response.setResultCode( "50001" );
        response.setRes( memberService.findAll() );
        return new ResponseEntity<>( response , HttpStatus.OK );
    }
}
