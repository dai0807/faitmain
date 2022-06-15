package com.faitmain.domain.user.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccess implements AuthenticationSuccessHandler{

    @Override
    public void onAuthenticationSuccess( HttpServletRequest request , HttpServletResponse response , Authentication authentication ) throws IOException, ServletException{
       System.out.println("로그인 성공이야? 너 뭐야? 이거 인증 핸들러야 ");
       
    	
    	response.sendRedirect( "/index" );
    }
}
