package com.faitmain.domain.user.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faitmain.domain.live.controller.LiveController;
import com.faitmain.domain.live.service.LiveService;
import com.faitmain.domain.user.domain.User;
import com.faitmain.domain.user.service.UserSerivce;
 
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping( "/user" )
public class UserController{
	

 
	   
	   // Field
	   @Autowired
	   private UserSerivce userSerivce;
	   
	   public UserController() {
		   log.info( "Controller = {} " , this.getClass() );
	   }
	   
	 
	   @GetMapping( "/login" )
	   public String longin( Model model ) throws Exception {
	      
		   
		   log.info( " login Page로 이동"  );
		   
	      return "forward:/user/login.jsp";
	   }
	   
	   
	   @PostMapping( "/login" )
	   public String longin( Model model , @RequestBody User loginuser ,  HttpSession session) throws Exception {
	      
 		   int result = 0;
 		  result = userSerivce.getLogin(loginuser) ; // id/ pw 값 있으면 1 없으면 0 ,,
 		   
 		   if(result == 1) {
 			   User user = userSerivce.getUser(loginuser) ;  
 			   log.info("User 출력 " + user);
 			   session.setAttribute("user", user) ; // user 정보 u로그인 
 		   }
		   
 		   
	      return "forward:/user/login.jsp";
	   }
	      
	   
	   
	   
	   @GetMapping("/logout")
	   public String logout(HttpSession session) {
		 log.info("get :: logout    " );

		   session.invalidate() ; 
		      
	   return "forward:/live/main.jsp";
	   }
	   
	   @GetMapping("/selectRegisterType")
	   public String selectRegisterType(Model model) {
		 
			log.info("get :: selectRegisterType    " );
	      
	   return "forward:/user/selectRegisterType.jsp";
	   }
	   
	   
	   
	   
}
