package com.faitmain.global.config;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.faitmain.domain.user.service.LoginDetailService;
import com.faitmain.domain.user.service.LoginFail;
import com.faitmain.domain.user.service.LoginSuccess;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	

    @Autowired
     LoginDetailService loginDetailService;

    @Autowired
    LoginFail loginFail;

    @Autowired
    LoginSuccess loginSuccess;

 
    
    @Override
    protected void configure( HttpSecurity http ) throws Exception{
    	System.out.println(" configure  :: + " ) ;

        http.csrf().disable();  // csrf 공격 방지 허용 

        http.authorizeRequests()
	        		.antMatchers("/user/getUser","/product/addProduct").authenticated() // 인증된 사람들만 접근 가능
//	        		.antMatchers("/user/getUser" ).hasAnyRole("user","store","storeX","admin")// 인증된 사람들만 접근 가능
//	        		.antMatchers("/user/getUserlist").hasRole("admin" ) //admin만 갈수 있음
	                .anyRequest().permitAll()  //모든 요청 허용 쌉가능
                .and()
	                .formLogin()
	                .loginPage("/user/login") // 인증 필요한 페이지 접근시 이동페이지 GET
	                 .loginProcessingUrl("/user/login")  //POST (security를 이용해 인증처리)  spring security에서 로그인
	    			.defaultSuccessUrl("/")				//  로그인 성공 시 이동 URL
	                 .successHandler( loginSuccess )
	                .failureHandler( loginFail )
	               // .failureUrl("/user")		//로그인 실패 시 /loginForm으로 이동
                .and()
	                .logout()
	                .logoutUrl("/user/logout")
	    			.invalidateHttpSession(true)  // 로그아웃시 세션 삭제 여부
	    			.logoutSuccessUrl("/");
	        
                /*
                .and()
                .rememberMe()
                *//* 쿠키값을 암호화 할 때 사용 *//*
                .key("rememberKey")
                *//* rememberMeParameter("remember-me")  로그인페이지의 체크박스 name과 일치해야합니다 이옵션을 쓰지 않으면 기본값은 "remember-me" *//*
                .rememberMeCookieName("rememberMeCookieName")
                .rememberMeParameter("remember-me")
                .tokenValiditySeconds(60 * 60 * 24 * 7)
                .userDetailsService(loginDetailService)
                */
        ;
        
        
        
        System.out.println("시큐리티컴피그 돌아돌아");
        
    }
    

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
    	// 비밀번호 암호화 할때 사용할 BCrypthPasswordEncoder 를 빈으로 등록 
        return new BCryptPasswordEncoder();
    }


 
    
    
}
