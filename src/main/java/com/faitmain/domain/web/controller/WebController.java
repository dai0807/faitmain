package com.faitmain.domain.web.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@AllArgsConstructor
public class WebController{


    @GetMapping( "/" )
    public String main() throws Exception{
        log.info( "log = {} " , log );
        return "index";
    }

    @GetMapping( "view/error" )
    public String register(){
        log.info( "log = {} " , log );
        return "view/404";
    }

    @GetMapping( "view/loginin" )
    public String login(){
        log.info( "log = {} " , log );
        return "view/login";
    }

    @GetMapping( "view/admin/admin" )
    public String admin(){
        System.out.println( "WebController.admin" );
        return "view/admin/admin";
    }
}
