package com.faitmain.global.config;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.faitmain.global.util.security.SecurityUserDetailService;
import com.faitmain.global.util.security.SecurityLoginFail;
 import com.faitmain.global.util.security.SecurityLoginSuccess;

import lombok.extern.slf4j.Slf4j;

 
@Slf4j
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	

    @Autowired
	SecurityUserDetailService securityUserDetailService;

    @Autowired
	SecurityLoginFail securityLoginFail;

    @Autowired
	SecurityLoginSuccess securityLoginSuccess;

 
    
    @Override
    protected void configure( HttpSecurity http ) throws Exception{
     	log.info("   configure  :: + \" "    );
        http.csrf().disable();  // csrf 공격 방지 허용 

        http.authorizeRequests()
	        		//.antMatchers("/user/getUser","/product/addProduct").authenticated() // 인증된 사람들만 접근 가능
					.antMatchers("/user/test" ).authenticated()// 인증된 사람 전부다 접근 가능 // 비회원 말고 갈수 있는 곳은 여기에 명시 
	        		.antMatchers("/product/addProduct" ).hasAnyRole("store")// 인증된 사람들만 접근 가능

	        		.antMatchers("/user/getUser" ).hasAnyRole("user","store","storeX","admin")// 인증된 사람들만 접근 가능
//	        		.antMatchers("/user/getUserlist").hasRole("admin" ) //admin만 갈수 있음
	                .anyRequest().permitAll()  //모든 요청 허용 쌉가능
	                
                .and()
	                .formLogin()
	                .loginPage("/user/login") // 인증 필요한 페이지 접근시 이동페이지 GET
	                 .loginProcessingUrl("/user/login")  //POST (security를 이용해 인증처리)  spring security에서 로그인
	    			.defaultSuccessUrl("/")				//  로그인 성공 시 이동 URL
	                 .successHandler( securityLoginSuccess )
	                 .usernameParameter("id")//아이디 파라미터명 설정
	                 .passwordParameter("password")//패스워드 파라미터명 설정	                 
	                 .failureHandler( securityLoginFail )
	               // .failureUrl("/user")		//로그인 실패 시 /loginForm으로 이동
	                 
                .and()
	                .logout()
	                .logoutUrl("/user/logout")
	    			.invalidateHttpSession(true)  // 로그아웃시 세션 삭제 여부
	    			.logoutSuccessUrl("/")
	    		.and()
	    			.exceptionHandling().accessDeniedPage("/user/accessDenied");  // 권한 상관없이 갈시 
 // 참고 사이트  https://www.baeldung.com/spring-security-custom-access-denied-page        		
	        
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
    	// httpsecurity를 통해 http 요청에 대한 웹 기반 보안을 구성
		// authrizerequest() => httpservletrequest에 따라 접근(access)를 제한
		// antMatchers() 메서드로 특정 경로를 지정, permitAll(), hasRole()로 역할에 따른 접근설정을 잡아줌
		// permitall은 권한없이 접근이 가능함
		// anyRequest().authenricated()는 모든 요청에 대해 인증된 사용자만 접근하도록 설정(여기에선 설정 안했음)
		
		// 로그인 설정
		// form 기반으로 인증하도록 하며, 로그인 정보는 기본적으로 httpSession을 이용함
		// login 경로로 접근하면 Spring security에서 제공하는 로그인 form을 사용하도록 함 
		// .loginpage => 기본 제공되는 form 말고 커스텀 로그인 폼을 사용하고 싶으면 loginpage()메서드 사용
		// 이때 커스텀 로그인 form의 action 경로와 loginpage()의 parameter 경로가 일치해야 인증 처리 가능(login.html)
		// defaultSuccessurl => 성공했을 때 이동되는 페이지로, 컨트롤러에서 URL 매핑이 되어 있어야 함
		// .usernameParameter => 로그인 form에서 아이디는 name=username인 input을 기본으로 인식하는데,
		// usernameparameter를 통해 파라미터명을 변경할 수 있음 (여기에서는 변경하지 않았음) 
		
		// 로그아웃 설정
		// 로그아웃을 지원하는 메서드로, websecurityconfigureradapter를 사용할 때 자동적용
		// 기본적으로는 /logout에 접근하면 http session을 제거함
		// logoutrequestmatcher 를 사용하면 기본 url(/logout)이 아닌 다른 url로 로그아웃 url을 재정의함
		// invalidate -> http 세션 초기화하는 작업
		// deleteCookies("KEY명") 로그아웃 시 특정 쿠키를 제거하고 싶을 때 사용
		
		// exceptionhandlign().accessdeniedpage -> 예외가 발생했을 때 exceptionhandling으로 핸들링 가능
		// 접근 권한 없을 때 로그인 페이지로 이동하도록 명시 
        
        
     	log.info("  시큐리티컴피그 돌아돌아  "  );
       
         
    }
    

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//    	// 비밀번호 암호화 할때 사용할 BCrypthPasswordEncoder 를 빈으로 등록 
//		//   BCryptPasswordEncoder는 spring security에서 제공하는 비밀번호 암호화 객체
//		// Service에서 비밀번호를 암호화할 수 있도록 Bean 으로 등록 
//        return new BCryptPasswordEncoder();
//    }
    
    @Bean
	public PasswordEncoder passwordEncoder() {
        // 단방향 암호화 알고리즘 사용 
    	// 비밀번호 암호화 할때 사용할 BCrypthPasswordEncoder 를 빈으로 등록 
		//   BCryptPasswordEncoder는 spring security에서 제공하는 비밀번호 암호화 객체
		// Service에서 비밀번호를 암호화할 수 있도록 Bean 으로 등록 
        return new BCryptPasswordEncoder();
    }
    
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 		auth.userDetailsService( securityUserDetailService ).passwordEncoder(passwordEncoder());
 	// spring security에서 모든 인증은 authenticationmanager를 통해 이뤄지고, 이를 생성하기 위해 builder 사용
 		// 로그인 처리, 즉 인증을 위해서는 Userdetailservice를 통해 필요한 정보를 가져오는데,
 		// 여기에서는 loginDetailService에서 이를 처리함
 		// userDetailsService 서비스 클래스에서는 userdetailservice 인터페이스를 implement했고, loaduserbyusername() 메서드를 구현
 		// 비밀번호 암호화를 위해 passwordencoder를 사용함
 		
 
 		
	}
    
    
 // 스프링 시큐리티는 적용하되 HTTP로 거르는 방법   
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());  // static 리소스들의 기본 위치들을 스프링 시큐리티에서 제외 
        // 무시하는 거 설정    
        //				web.ignoring().mvcMatchers("/user")  ///user뿐 아니라, /user/ , user/acouunt/~~~ ㄷ등 허용
        //    			web.ignoring().antMatcher("/user")   /acount라는 URL가 정확하게 일치하는 경우에만 허용
       // 참고 https://ohtaeg.tistory.com/11 
        
       


    }
    
    
	
//   
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        String password = passwordEncoder().encode("1111");
//
//        auth.inMemoryAuthentication().withUser("user").password(password).roles("USER");
//        auth.inMemoryAuthentication().withUser("manager").password(password).roles("MANAGER");
//        auth.inMemoryAuthentication().withUser("admin").password(password).roles("ADMIN");
//    }
//    
 
    
}
