package com.faitmain.domain.user.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LoginSuccess implements AuthenticationSuccessHandler{

    @Override
    public void onAuthenticationSuccess( HttpServletRequest request , HttpServletResponse response , Authentication authentication ) throws IOException, ServletException{
        
    	log.info("onAuthenticationSuccess ::   로그인 성공 " );
     	
    	response.sendRedirect( "/index" );
    }
}
