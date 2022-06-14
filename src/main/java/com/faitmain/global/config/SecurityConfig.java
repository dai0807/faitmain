//package com.faitmain.global.config;
//
//import com.faitmain.domain.user.service.LoginDetailService;
//import com.faitmain.domain.user.service.LoginFail;
//import com.faitmain.domain.user.service.LoginSuccess;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
////@EnableWebSecurity
////@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//
//
//    @Autowired
//    LoginDetailService loginDetailService;
//
//    @Autowired
//    LoginFail loginFail;
//
//    @Autowired
//    LoginSuccess loginSuccess;
//
//    @Bean
//    public BCryptPasswordEncoder encodePwd(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure( HttpSecurity http ) throws Exception{
//        http.csrf().disable();
//
//        http.authorizeRequests()
//                /* 주석처리안되어있으면갈떄마다검증받아야함 */
////                .antMatchers("/admin/**").hasRole("ADMIN")
////                .antMatchers("/user/**").hasAnyRole("ADMIN, USER")
//                .anyRequest().permitAll()
//
//                .and()
//                .formLogin()
//                .loginPage("/") // 인증 필요한 페이지 접근시 이동페이지
//                .loginProcessingUrl("/user/json/login")
//                .successHandler( loginSuccess )
//                .failureHandler( loginFail )
//
//                .and()
//                .logout()
//                .logoutSuccessUrl("/index")
//
//                /*
//                .and()
//                .rememberMe()
//                *//* 쿠키값을 암호화 할 때 사용 *//*
//                .key("rememberKey")
//                *//* rememberMeParameter("remember-me")  로그인페이지의 체크박스 name과 일치해야합니다 이옵션을 쓰지 않으면 기본값은 "remember-me" *//*
//                .rememberMeCookieName("rememberMeCookieName")
//                .rememberMeParameter("remember-me")
//                .tokenValiditySeconds(60 * 60 * 24 * 7)
//                .userDetailsService(loginDetailService)
//                */
//        ;
//    }
//}
