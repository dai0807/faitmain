package com.faitmain.domain.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.faitmain.domain.user.domain.StoreApplicationDocument;
import com.faitmain.domain.user.domain.User;
import com.faitmain.domain.user.service.ApiService;
import com.faitmain.domain.user.service.UserSerivce;
 
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping( "/user/*" )
public class UserController{
	
	//리스트 제외한 , Controller 1차완성 

 
	   
	   // Field
	   @Autowired
	   @Qualifier("userServiceImpl")
	   private UserSerivce userSerivce;
	   
	   @Autowired
	   @Qualifier("apiServiceImpl")
	   private ApiService apiServiceImpl;
	   
	   
	   
	   public UserController() {
		   log.info( "Controller = {} " , this.getClass() );
	   }
	   
	 
	   @GetMapping( "login" )
	   public String longin( Model model ) throws Exception {
	      
		   
		   log.info( " 컨트롤러 탐 login Page로 이동"  );
		   
	      return "view/user/login";
	   }
	   
	   
	   @PostMapping( "login" )
	   public RedirectView longin( Model model , @ModelAttribute("user") User loginuser,  HttpSession session) throws Exception {
	      
		   log.info("Post login Page 도착");
		   log.info("user 출력  :: {}" , loginuser);
		   
 		   int result = 0;
 		  result = userSerivce.getLogin(loginuser) ; // id/ pw 값 있으면 1 없으면 0 ,,
 		   
 		   if(result == 1) {
 			   User user = userSerivce.getUser(loginuser.getId()) ;  
 			   log.info("{}의 로그인이 완료 되었습니다  " , user.getId());
 			   session.setAttribute("user", user) ; // user 정보 u로그인 
 		   }else {
 			   log.info("로그인 실패");
 			   
 		   }
		   log.info("Post login 끝 ");
 		   
	      return new RedirectView("/");
	   }
	      
	   
	   
	   
	   @GetMapping("logout")
	   public RedirectView logout(HttpSession session ,HttpServletRequest request) throws Exception {
		   
		   
		 log.info("get :: logout    " );
		log.info("  {} 의 로그아웃  " , ((User) request.getSession(true).getAttribute("user")).getId()   );

		   session.invalidate() ; 
		      
		      return new RedirectView("/");
	   }
	   
	   
	   @GetMapping("selectRegisterType")
	   public String selectRegisterType(Model model)  throws Exception {
		 
			log.info("get :: selectRegisterType    " );
	      
	   return "view/user/selectRegisterType";
	   }
	   
	   @PostMapping("addUser")
	   public String addUser(@ModelAttribute("user") User user) throws Exception{
		   
		   log.info("addUser::들어온 user 결과  ::{}" ,user );
		   int result = userSerivce.addUser(user) ;
		   log.info("restut {}" , result);
		   
		   return("redirect:/live/main.jsp");
	   }
	   
	   
	   // 최대한 ,,, 써머노트 구현해보자
	   @PostMapping("addStore")
	   public String addStore(@ModelAttribute("user") User user , @ModelAttribute("storeApplicationDocument")StoreApplicationDocument storeApplicatDoc ) throws Exception{
		   
		   log.info("addStore::들어온 user 결과  ::{}" ,user );
		   log.info("addStore::들어온 storeApplicationDocument 결과  ::{}" ,storeApplicatDoc );

		   int addStoreresult = userSerivce.addUser(user) ;
		   log.info("addStoreresult {}" , addStoreresult);
		   
		   int storeApplicatDocresult = userSerivce.AddStoreApplicationDocument(storeApplicatDoc) ;
		   log.info("addStoreresult {}" , storeApplicatDocresult);
		   
		   
		   return("redirect:/live/main.jsp");
	   }
	   
	   @PostMapping("updateUser")
	   public String updateUser(@ModelAttribute("user") User user  ) throws Exception{
		   
		   log.info("addStore::들어온 user 결과  ::{}" ,user );
 
		   int addStoreresult = userSerivce.addUser(user) ;
		   log.info("addStoreresult {}" , addStoreresult);
		   
		   
 		   
		   
		   return("redirect:/live/main.jsp");
	   }	
	   
		@GetMapping("kakaoLogin")
		public String kakaoLogin(@RequestParam(value = "code", required = false) String code , Model model , HttpSession session) throws Exception {
		// 사용자 로그인 및  동의 후 , 인가 코드를 발급받아 302 redirect를 통해  ,  이 메소드 도착함    
			
			log.info("##kakaoLogin## 페이지 도착 " );
			
			   log.info("##code {} ##" , code); // 코드 출력 

			// 사용자 정보를 가져오기 위하여 code로  access 토큰 가져오기 ! 
			   String access_Token = apiServiceImpl.getKaKaoAccessToken(code);
 			   log.info("##access_Token 가져옴 ::  {} ##" , access_Token);
			
			
 			// 카카오 getUserInfo 에서 access_Token를 통하여  userInfo 가져오기 
				Map<String, Object> userInfo = apiServiceImpl.getKakoUserInfo(access_Token);
	 				log.info("##access_Token {} ##" , access_Token);
	 			 log.info("##email {} ##" , userInfo.get("email"));
 
	 			String kakaouserId =  (String) userInfo.get("email");		   
	 			User user = new User () ;
	 			user.setId(kakaouserId);
	 			user.setPassword("12345"); // 카카오 로그인시 비밀번호
				if(   	userSerivce.getLogin(user) == 0 ) {  // 카카로 로그인 ID가 우리 사이트에 존재 x
	 				log.info("로그인한 카카오 아이디가 존재 하지 않습니다. ");
	
 					model.addAttribute("kakaouserId",kakaouserId);
 					
 					
 					return "view/user/kakaoAdd"; // 추가 kakao로그인 화면 
 					

 				}else {  
 					// 카카오 로그인시 ID가 우리 사이트에 존재 할때 
 					
 					// Model 과 View 연결					
 					 user = userSerivce.getUser(user.getId());
 					session.setAttribute("user", user );
 					
 				} // 존재 할때 
  				
				  return("redirect:/");
		 
	    	}	   
		
		
		//kakao회원 추가 가입 
		@PostMapping("kakaoaddUser")
  		public String kakaoaddUser(@RequestParam(value = "code", required = false) String code , Model model , @ModelAttribute("user") User kuser , HttpSession session )throws Exception {
			   log.info("##code {} ##" , code);
			   log.info("##kuser {} ##" , kuser);

 			
			
	 			kuser.setPassword("12345") ; //  패스워드 고정 
				kuser.setJoinPath("kakao") ; // 카카오는 K로 고정 , 자사는 H 로 고정 
	
				log.info("##kuser {} ##" , kuser);
				log.info("회원가입이 완료 되었습니다. ");
			
				 int result = userSerivce.addUser(kuser);
				log.info("##kakaoUser 결과  {} ##" , result);
	
				session.setAttribute("user", kuser );

				  return("redirect:/live/main.jsp");
	 				

	 				
	    	}
		
		//이것은 네이버 로그인 하고 구현하기 
		@PostMapping("naverUser")
  		public String naverUser(@RequestParam(value = "code", required = false) String code , Model model , @ModelAttribute("user") User kuser , HttpSession session )throws Exception {
			 

				  return("redirect:/live/main.jsp");
	 				

	 				
	    	}				
		
	   
		//UpdatePassword
		@PostMapping("updatePassword")
  		public String updatePassword( @ModelAttribute("user") User user  )throws Exception {
			log.info("##updatePassword {} ##" , user);
			
			 int result = userSerivce.updateUserPassword(user);
			log.info("##kakaoUser 결과  {} ##" , result);
			
			
			log.info("User Password 바뀐 결과 {}" , userSerivce.getUser(user.getId()).getPassword() );
			
			
			return("redirect:/live/main.jsp");

	 				
	    	}				
		
		// find Id Rest Control로 갈 운명 
		@PostMapping("findId")
  		public String findId( @ModelAttribute("user") User user  ,Model model)throws Exception {
			
			log.info("##findId {} ##" , user);
			
			
			Map<String,Object> map = new HashMap<>();
			map.put("phoneNumber", user.getPhoneNumber());
			map.put("name", user.getName() );
 			
			
			log.info("findId " + userSerivce.findUser(map));
			
			if(   userSerivce.findUser(map) == 1  ) {
				
				String findUserId = userSerivce.findGetId(map);
				model.addAttribute("findUserId", findUserId);

				
				return("forward:/user/findIdView.jsp");

				
				
			}else {
				return("forward:/main");

			}
			

	 				
	    	}				
			   		
		//find PW Rest Control로 갈 운명 
		@PostMapping("findPw")
  		public String findPw( @ModelAttribute("user") User user  ,Model model)throws Exception {
			
			log.info("##findPw {} ##" , user);
			
			
			Map<String,Object> map = new HashMap<>();
			map.put("phoneNumber", user.getPhoneNumber());
			map.put("id", user.getId() );
 			
			
			log.info("findPW {}" , userSerivce.findUser(map));
			
			if(   userSerivce.findUser(map) == 1  ) {
				
 				model.addAttribute("user", user);

				
				return("forward:/user/updatePassword.jsp");

				
				
				}else {
					return("forward:/main");
	
				}
			

	 				
	    	}
		
		//유저 상세 정보
		//
		@GetMapping("getUser")
		   public String getUser( Model model , @RequestParam("id") String id) throws Exception {
			   log.info(" GestUSer에 옴 출력하라 id {}" ,id );

			   User user = userSerivce.getUser(id) ;
			   if(user.getRole().equals("Store") || user.getRole().equals("StoreX")) {
				   //롤이 스토어면 스토어 넘버 가져와서 user에 넣어라 
				   int storeNumber = userSerivce.getStoreApplicationDocumenNumber(user.getId());
 				   user.setStoreApplicationDocumentNumber(storeNumber);
			   } 
			
			   log.info(" 출력하라 getUSer {}" ,user );
			   model.addAttribute("user",user) ;
			   
		      return "forward:/user/getUser.jsp";
		   }
		   
		//스토어 신청서 상세 보기 
		@GetMapping("getStoreApplicationDocument")
		   public String getStoreApplicationDocument( Model model , @RequestParam("storeApplicationDocumentNumber") int storeApplicationDocumentNumber) throws Exception {
			   log.info(" getStoreApplicationDocument 에 옴 출력하라   {}" ,storeApplicationDocumentNumber );

 			  StoreApplicationDocument storeApplicationDocument = userSerivce.getStoreApplicationDocument(storeApplicationDocumentNumber);
 			  log.info("StoreApplicationDocument {}" ,storeApplicationDocument );
 			  
 			  model.addAttribute("StoreApplicationDocument" , storeApplicationDocument);
 			  
		      return "forward:/user/getStoreApplicationDocument.jsp";
		   }
		   		
		//스토어 재 신청 Add 
		@PostMapping("addStoreApplicationDocument")
		   public String addStoreApplicationDocument( Model model , @ModelAttribute("storeApplicationDocument") StoreApplicationDocument storeApplicationDocument) throws Exception {
			   log.info(" addStoreApplicationDocument 에 옴 출력하라   {}" ,storeApplicationDocument );
			
			   int result =  userSerivce.AddStoreApplicationDocument(storeApplicationDocument)  ;
			   log.info("결과 에  출력하라   {}" , result );
			   
			   if(result ==1) {
				   
				   int addStoreApplicationNumber = userSerivce.getStoreApplicationDocumenNumber(storeApplicationDocument.getId()) ;
				   storeApplicationDocument.setStoreApplicationDocumentNumber(addStoreApplicationNumber);
				  
				   
			   }
			   
			  			   
 
	 		model.addAttribute("StoreApplicationDocument" , storeApplicationDocument);

			   
			   
		      return "forward:/user/getStoreApplicationDocument.jsp";
		   }
		
		
		   		
		
}
