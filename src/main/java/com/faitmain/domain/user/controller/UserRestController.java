package com.faitmain.domain.user.controller;

import com.faitmain.domain.user.domain.StoreApplicationDocument;
import com.faitmain.domain.user.domain.User;
import com.faitmain.domain.user.service.UserSerivce;
import com.faitmain.global.common.Search;
import com.faitmain.global.util.security.SecurityUserService;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
 import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping( "/user/*" )
public class UserRestController{

	@Value("${upload-path}")
	private String fileStorageLocation; 
	   
	@Autowired
	@Qualifier("userServiceImpl")	   
	private UserSerivce userSerivce;

	 @Autowired
	 private PasswordEncoder pwdEncoder;	   
	    
     @Autowired
    private SecurityUserService securityUserService;
     
 	private Authentication authentication;
//
//	 @Autowired
//	 private AuthenticationManager authenticationManager;
// 
	   public UserRestController() {
		 //   log.info(  "Controller {}" , this.getClass() );
		   
	   }

	   
	   
	   
	   
	   
	   
	   
	   
	   @GetMapping("/json/getUserlist")
	   public  List<User>  getUserList  (Model model  ,  @RequestParam ("order") String searchCondition  , Search search )throws Exception {
		
		   
		   log.info("getUserList  도착 !! ");
		   log.info("getUserList searchCondition ={}", searchCondition);

		   Map<String, Object> searchMap = new HashMap<>();
		   
		   if( searchCondition =="store") {
			   searchMap.put("searchCondition", searchCondition) ;
		   }else if(searchCondition =="user") {
			   searchMap.put("searchCondition", searchCondition) ;  
		   }
	   
		   List<User> userList = userSerivce.getlist(searchMap) ;
		   for(User user : userList) {
			   System.out.println("getUserlist : 유저 출력"+user);
		   }
		   
		   
		   Map<String, Object> returnuserList = new HashMap<String, Object>();
 		   
		   returnuserList.put("data", userList) ;
		   
		   
		   log.info("getUserList searchCondition ={} 끝");
	  
		   
		   return userList ;
		   
	   }
	   
	   
	   
	   
	   
	   
		// find Id Rest Control로 갈 운명 
	   @PostMapping("json/findId")
 		public String findId( @ModelAttribute("user") User user  ,Model model)throws Exception {
			
			log.info("###Stat###findId ={} ##" , user);
		     Map<String, Object> findIdmap = new HashMap<>();
		     	findIdmap.put( "phoneNumber" , user.getPhoneNumber() );
		        findIdmap.put( "findcondition" , "name" );
		        findIdmap.put( "findkeyword" , user.getName() );
	        
				  user = userSerivce.findGetIdPw(findIdmap) ;
					log.info("###Stat###user ={} ##" , user);
					
				  
				  if(user != null) {
						log.info("  ::id:: ={}  " ,user.getId());
					
						return user.getId()  ;
					}else {
						
						log.info("  ::id:: null 입니다.");
						return "입력하신 정보와 유효한 id가 존재하지 않습니다." ;

					}

	 				
	    	}				
			   	
		@PostMapping("/json/findPassword")		
  		public String findPw( @ModelAttribute("user") User user  ,Model model ,  HttpSession session , HttpServletRequest request)throws Exception {
			
			log.info("##findPw {} ##" , user);
			
			
			Map<String,Object> findPasswordmap = new HashMap<>();
			findPasswordmap.put("phoneNumber", user.getPhoneNumber());
			findPasswordmap.put( "findcondition" , "id" );
			findPasswordmap.put( "findkeyword" , user.getId() );
  			
			
			log.info("findPW {}" , userSerivce.findUser(findPasswordmap));
			
			if(   userSerivce.findUser(findPasswordmap) == 1  ) {
				

				log.info("입력한 유저가 존재합니다.") ;
			
 				
				return "입력한 유저가 존재합니다.";
			 
					
				
 				
				
				}else {
					log.info("입력하신 정보와 유효한 회원정보가가 존재하지 않습니다.");

					return "입력하신 정보와 유효한 회원정보가가 존재하지 않습니다." ;
	
				}
			

	 				
	    	}
		
		
	   
	   
	   @PostMapping(value = "json/uploadSummernoteImageFile", produces = "application/json")
		@ResponseBody
		public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {
			
			System.out.println("uploadSummernoteImageFile");
			
			JSONObject jsonObject = new JSONObject();
			
			String fileRoot = "C:\\summernote_image\\";	//저장될 파일 경로
					
			String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
	        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
			
	        // 랜덤 UUID+확장자로 저장될 savedFileName
	        String savedFileName = UUID.randomUUID() + extension;	
			
	        File targetFile = new File(fileRoot + savedFileName);
			
			try {
				InputStream fileStream = multipartFile.getInputStream();
	            FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
 
	            jsonObject.put("url", "/summernoteImage/" + savedFileName);  //JSONObjct에 정보 삽임 
	            jsonObject.put("responseCode", "success");
			}catch(IOException e) {
				FileUtils.deleteQuietly(targetFile);	// 실패시 저장된 파일 삭제

	            e.printStackTrace();
			}
			
			log.info(" 끝 = {} " ,jsonObject.toJSONString()) ;
			return jsonObject.toJSONString();
		}	
	   
	   //안씀 
	   @PostMapping( "json/login" )
	   public String RESTlongin(  User loginuser,  HttpSession session) throws Exception {
	      
		   log.info("json LostController 탔어용 login Page 도착");
		   log.info("받은 유저 user 출력  :: {}" , loginuser);
		   
 		   String result = "";
 		   result = userSerivce.getLogin(loginuser)+"" ; // id/ pw 값 있으면 1 없으면 0 ,,
		   log.info("받은 유저 result 출력  :: {}" , result);
 
 		   if(result.equals("1")) { //1 이면 로그인 된거임 
 			   User user = userSerivce.getUser(loginuser.getId()) ;   // 로그인 된 사람 정보 가져와서 회월탈퇴 값 있는지 검증 
 			   
			 			  System.out.println("너의 값은 무엇이냐" +user.getWithdrawalStatus()) ;
				 			   if(user.getWithdrawalStatus() == true) { // true 는 회원 탈퇴
				 				   result="withdraw" ; //
				 				   
				 			      return result;
				    
				 			   }
				 			   
 			   
 			   
 			   log.info("{}의 로그인이 완료 되었습니다  " , user.getId());
 			   session.setAttribute("user", user) ; // user 정보 로그인
 		   }else {
 			   log.info("로그인 실패");
 			   
 		   }
 		   
 		   
		   log.info("json login 끝 ");
		   
 
 		   
	      return result;
	   }
	      
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	 
////업데이트 유저  업데이트 유저 사진 XX 해야함 
//    @PostMapping( value = "json/updateUser" )
////    public int ajaxupdateUser( @RequestParam( "id" ) String id ,
////                               @RequestParam String userAdrress1 ,
////                               @RequestParam String userAdrress2 ,
////                               @RequestParam String userAdrress3 ,
////                               @RequestParam String nickname ,
////                               @RequestParam String phoneNumber ,
////                               @RequestParam String storeName ,
////
////							   HttpSession session ,
////							   HttpServletRequest request ) throws Exception{
//    public int ajaxupdateUser( User user ,
//    		 MultipartHttpServletRequest mRequest ,
//			 
//			   HttpSession session ,
//			   HttpServletRequest request ) throws Exception{
////        User user = new User();
////        user.setId( id );
////        user.setNickname( nickname );
////        user.setPhoneNumber( phoneNumber );
////		user.setUserAddress1( userAdrress1 );
////		user.setUserAddress2( userAdrress2 );
////		user.setUserAddress3( userAdrress3 );
//    	
//    	log.info("ajax Updtae에 옴  user 값은 = {}" , user) ;
//    	
//        //아직  checkDuplication 없음
//        int result = 0;
//        //log.info("updateUser :: user 출력   {} "  ,  user );
//        //	result = userSerivce.updateUser(user);
//        result = userSerivce.updateUser( user  , mRequest);
//        log.info( "updateUser :: result 출력  = {} " , result );
//        //	log.info("updateUser ::  user 세션 값 변경 전   {} "  ,  (User)request.getSession(true).getAttribute("user"));
//        user = userSerivce.getUser( user.getId() );
//        session.setAttribute( "user" , user );
//        log.info( "updateUser ::  user 세션 값 변경 후   {} " , request.getSession( true ).getAttribute( "user" ) );
//        return result;
//    }


    @PostMapping( value = "json/updateUser" )  
    public int ajaxupdateUser( User user ,  @AuthenticationPrincipal SecurityUserService securityUserService  ) throws Exception{
//       User user = new User();
//       user.setId( id );
//       user.setNickname( nickname );
//       user.setPhoneNumber( phoneNumber );
//		user.setUserAddress1( userAdrress1 );
//		user.setUserAddress2( userAdrress2 );
//		user.setUserAddress3( userAdrress3 );
   	
   	log.info("ajax Updtae에 옴  user 값은 = {}" , user) ;
   	
       //아직  checkDuplication 없음
       int result = 0;
       //log.info("updateUser :: user 출력   {} "  ,  user );
       //	result = userSerivce.updateUser(user);
       result = userSerivce.updateUser( user);
       log.info( "updateUser :: result 출력  = {} " , result );
       user = userSerivce.getUser( user.getId() );

      ///////////////////SecurityContextHolder 날림 ///////////////////////////////////
       SecurityContextHolder.clearContext();
       
       SecurityUserService  securityUser = new SecurityUserService(user);
		System.out.println("  #updateUserDetails : "+securityUser);

	      ///////////////////SecurityContextHolder 세팅 ///////////////////////////////////
   
        Authentication authRequest = new UsernamePasswordAuthenticationToken(securityUser, null , securityUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authRequest);       
		
		System.out.println("  #updateUserDetails : "+authRequest);
	
		
		
/*		
		public UsernamePasswordAuthenticationToken(Object principal, Object credentials,
				Collection<? extends GrantedAuthority> authorities) {
			super(authorities);
			this.principal = principal;
			this.credentials = credentials;
			super.setAuthenticated(true); // must use super, as we override
		}		
*/
	 
		
		
		
		
      //	       System.out.println(""+SecurityUser.getAuth() );
//	     Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getId() , user.getPassword()  )) ;
//	     SecurityContextHolder.getContext().setAuthentication(authentication)   ;
//	   
  
   		// authentication을 SecurityContextHolder.getContext().setAuthentication(...)를 set 

//		Authentication authentication =
//		   new UsernamePasswordAuthenticationToken(securityUser,  null , securityUser.getAuthorities() ) ;
//       		 SecurityContextHolder.getContext().setAuthentication(authentication) ; //SecurityContextHolder 안에 있는 컨텍스트에 접근 
 
		//UsernamePasswordAuthenticationToken은  Authentication의 구현체다  
		
//			Authentication authentication = new UsernamePasswordAuthenticationToken(securityUser, null , securityUser.getAuthorities());
//			SecurityContextHolder.getContext().setAuthentication(authentication); 		
// 
			
 
			
		
//		   Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getId(), user.getPassword()    ,  securityUserService.getAuth()   )) ;
//		     SecurityContextHolder.getContext().setAuthentication(authentication)   ;
//		      
       //	log.info("updateUser ::  user 세션 값 변경 전   {} "  ,  (User)request.getSession(true).getAttribute("user"));

       
//  2번째 눈물의 똥꾜쇼      
//   //authenticationManager를 통해 세션값을 다시 지정해 줬으니 알아서 DB에서 id,pw 가져와서 비교해서  매니져서 다시 일치하는지 확인함    
//       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getId(), user.getPassword()         )) ;
//       SecurityContextHolder.getContext().setAuthentication(authentication)   ;
//  
//       String encPwd =pwdEncoder.encode(user.getPassword());  //PW 암호화
//       user.setPassword(encPwd);
		
		
//		
//       System.out.println(""+SecurityUser.getAuth() );
//     Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(SecurityUser ,null ,  SecurityUser.getAuthorities()   )) ;
//     SecurityContextHolder.getContext().setAuthentication(authentication)   ;
//      
//     
//		Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
//				loginUser, null, loginUser.getAuthorities());
//		System.out.println("  #newAuthentication : "+newAuthentication);
//		
//		SecurityContextHolder.getContext().setAuthentication(newAuthentication);
//		 
       
 //=====================강제로 세션 만들다가 안됨 ,,눈물 흘린다. ㅇㅇㅇ어어어어어엉=================
       // Authentication 토큰 만들기 
       //UsernamePasswordAuthenticationToken 이 securityUser를 통해 토큰을 만들어 줄거임 
//       Authentication authentication =
//    		   new UsernamePasswordAuthenticationToken(securityUser,  null , securityUser.getAuthorities() ) ;
//       SecurityContext securityContext =SecurityContextHolder.getContext() ; //SecurityContextHolder 안에 있는 컨텍스트에 접근 
//       securityContext.setAuthentication(authentication);
//
//       session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext) ;       
//       	SecurityContextHolder.getContext().setAuthentication(authentication) ; // 강제로  Authentication에 저장 된 세션 값 바꾸는 거임 
// 직접    Authentication 바꾸는거 안됨   
       
       
       
       
       
       
//       
//     Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();   //principal 에 사용자 인증 정보 담음
//
//     //Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
//     System.out.println("====getMyInfo1==== "+ principal);
//     System.out.println("====getMyInfo1==== "+ principal.toString());
//     SecurityUserService securityUser = (SecurityUserService)principal ;
     
     log.info(   "securityUser , update 유저 끝 ={}", securityUser.getUser()  );
        
       
       
       
       
        
       
       
       return result;
   }


    
    

    // 아이디 중복 체크
    @GetMapping( value = "idCheck" )
    public int idCheck( @RequestParam( "id" ) String id ) throws Exception{
        //아직  checkDuplication 없음


        //log.info("중복체크 id  {} "  , id);
        Map<String, Object> map = new HashMap<>();
        map.put( "checkcondition" , "id" );
        map.put( "checkkeyword" , id );
        int cnt = userSerivce.getchechDuplication( map );
        //	log.info("id의 중복 검사 결과 {}"   +  cnt );
        // 숫자가 1 이면 중복 , 0이면 없음

        return cnt;
    }
    

    
    //스토어네임
    @GetMapping( value = "storeNameCheck" )
    public int storeNameCheck( @RequestParam( "storeName" ) String storeName ) throws Exception{
        //아직  checkDuplication 없음

        //	log.info("중복체크 닉네임 {} " ,  nickname);

        Map<String, Object> map = new HashMap<>();
        map.put( "checkcondition" , "store_name" );
        map.put( "checkkeyword" , storeName );

        int cnt = userSerivce.getchechDuplication( map );

        log.info( "nicknameCheck {}" , cnt );
        // 숫자가 1 이면 중복 , 0이면 없음
       
        
        log.info( "nicknameCheck {}" , cnt );
        log.info( "nicknameCheck {}" , cnt );
        log.info( "nicknameCheck {}" , cnt );
        log.info( "nicknameCheck {}" , cnt );
    
        
        return cnt;
        
        	
    }

    
    
    @GetMapping( value = "nicknameCheck" )
    public int nameCheck( @RequestParam( "nickname" ) String nickname ) throws Exception{
        //아직  checkDuplication 없음

        //	log.info("중복체크 닉네임 {} " ,  nickname);

        Map<String, Object> map = new HashMap<>();
        map.put( "checkcondition" , "nickname" );
        map.put( "checkkeyword" , nickname );

        int cnt = userSerivce.getchechDuplication( map );

        log.info( "nicknameCheck {}" , cnt );
        // 숫자가 1 이면 중복 , 0이면 없음
        return cnt;
    }

    //폰번호 검사
    @GetMapping( value = "phoneNumbereCheck" )
    public int phoneNumber( @RequestParam( "phoneNumber" ) String phoneNumber ) throws Exception{
        //아직  checkDuplication 없음

        log.info( "중복체크 phoneNumber {} " , phoneNumber );

        Map<String, Object> map = new HashMap<>();
        map.put( "checkcondition" , "phone_number" );
        map.put( "checkkeyword" , phoneNumber );

        int cnt = userSerivce.getchechDuplication( map );

        log.info( "nicknameCheck {} " , cnt );
        // 숫자가 1 이면 중복 , 0이면 없음
        return cnt;
    }


    @GetMapping( value = "storeNameheck" )
    public int storeName( @RequestParam( "storeName" ) String storeName ) throws Exception{
        //아직  checkDuplication 없음

        log.info( "중복체크 nicknameCheck {} " , storeName );

        Map<String, Object> map = new HashMap<>();
        map.put( "checkcondition" , "store_name" );
        map.put( "checkkeyword" , storeName );

        int cnt = userSerivce.getchechDuplication( map );

        System.out.println( "storeName  " + cnt );
        // 숫자가 1 이면 중복 , 0이면 없음
        return cnt;
    }


    // sms 휴대폰 문자보내기
    @GetMapping( value = "uphoneCheck" )
    public String sendSMS( @RequestParam( "phone" ) String userPhoneNumber , HttpSession session ) throws Exception{

        //인증번호 4자리 난수로  생성
        int randomNumber = ( int ) ( ( Math.random() * ( 9999 - 1000 + 1 ) ) + 1000 );
        //
        log.info( "  randomNumber 값 체크 ::  {} " , randomNumber );
        userSerivce.sendCertificationSms( userPhoneNumber , randomNumber );
        session.setAttribute( "userAuth" , randomNumber ); // 인증 세션  세션 시간 지정해 줘야함


        // 숫자가 1 이면 중복 , 0이면 없음


        return "ddd";
    }

    // 휴대폰 문자 인증
    @GetMapping( value = "smsCertificationRequest" )
    public String smsCertificationRequest( @RequestParam( "phone" ) String userPhoneNumber , @RequestParam( "phone2" ) String smsCertification , HttpSession session , HttpServletRequest request ) throws Exception{
        log.info( " 인증 하러옴  " );

        String result = "F";
        log.info( " userPhoneNumber {} :  smsCertification {} " , userPhoneNumber , smsCertification );
        log.info( " session.getAttributeNames{}" + session.getAttributeNames() );


        if ( request.getSession( true ).getAttribute( "userAuth" ) != null ) {
            log.info( "인증 세션 있음  " );
            log.info( " 세션 있음 {}  " , request.getSession( true ).getAttribute( "userAuth" ) );

            String sessionAuth = request.getSession( true ).getAttribute( "userAuth" ).toString(); // 세션에 있는 값 .toString()
            log.info( "userAuth의 인증 값은 {} " , sessionAuth );
            log.info( "입력받은 번호 값 {}  , 세션 인증값 {}" , smsCertification , sessionAuth );
            if ( sessionAuth.equals( smsCertification ) ) {
                result = "T";
                log.info( "인증완료 " );

            } else {
                result = "F";
                log.info( "인증값 틀림 " );

            }


        } else {
            log.info( "세션 없음 " );

        }


        log.info( "Result::  {}" , result );


        return result;
    }

    
    
     
  @PostMapping( "updatePassword" )
  public int updatePassword( @ModelAttribute( "user" ) User user  ) throws Exception{
	  
	  
		System.out.println("  #Authentication : "+ SecurityContextHolder.getContext().getAuthentication());
       

        int restult = userSerivce.updateUserPassword( user );
      
        
        
        
        user = userSerivce.getUser(user.getId()) ;
        
        System.out.println("업데이트 유저  :: " +  restult );

		SecurityContextHolder.clearContext();  // 없애고 
	    System.out.println("클리어  :: "      );
	    
	   
	    
	    
	    
		SecurityUserService securityUserService = new SecurityUserService(user);   //  디테일 섭스를 다시 만들어서 주입
		System.out.println("securityUserService :: " + securityUserService );
		


		
 //        authentication 에 토큰 새로 만들어서 주입  됨
      Authentication authentication =
		   new UsernamePasswordAuthenticationToken(securityUserService,  null , securityUserService.getAuthorities() ) ;
			SecurityContext securityContext =SecurityContextHolder.getContext() ; //SecurityContextHolder 안에 있는 컨텍스트에 접근 
			securityContext.setAuthentication(authentication);          //securityContext 에 setAuthentication 에 authentication 세팅

			// 무슨 짓을 해도..... 안되서 직접 인증 토큰 만들어서 주입해줌			
			// Authentication 만들기
			// AuthenticationManager 에서 인증을 인증에 성공하면 Authentication을 만들어서 컨텍스트 홀더에 주입
			// 그럼 우리도 SecurityContextHolder.clearContext()를 없애고 UsernamePasswordAuthenticationToken를 직접만들어서 setAuthentication에 set 
	//		Authentication newAuth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    //
			
		
			// 실패
			/// authenticationManager  안됨 

//			 Authentication request = new UsernamePasswordAuthenticationToken(securityUserService, null,securityUserService.getAuthorities());
//			 Authentication result = authenticationManager.authenticate(request);
//			 SecurityContextHolder.getContext().setAuthentication(result);
			    
 			
			
        log.info( "uauthRequest :: = {}" , authentication );

 log.info( "##POST ##updatePassword {} ##" , user );

 log.info( "update Password 결과 {}" , restult );


		
		
//		Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
//				securityUserService, null, securityUserService.getAuthorities());
	

//		   Authentication authRequest = new UsernamePasswordAuthenticationToken(securityUserService, null , securityUserService.getAuthorities());
//	         SecurityContextHolder.getContext().setAuthentication(authRequest);        
//        log.info( "uauthRequest :: = {}" , authRequest );

	         

        
        
        
   //1번 눈물      
//    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getId(), user.getPassword()      )) ;
//System.out.println("ddd");
//    SecurityContextHolder.getContext().setAuthentication(authentication)   ;

//       
  
      
 //1번 눈물      
//  Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(securityUserService, null, securityUserService.getAuthorities()      )) ;
//System.out.println("ddd");
//  SecurityContextHolder.getContext().setAuthentication(authentication)   ;

//         
      
      // 2번
//		Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
//		securityUserService, null, securityUserService.getAuthorities());
//
//SecurityContextHolder.getContext().setAuthentication(newAuthentication);
//System.out.println("  #newAuthentication : "+newAuthentication);
//  
 
 
 //3번     
//    Authentication authentication =
//		   new UsernamePasswordAuthenticationToken(securityUser,  null , securityUser.getAuthorities() ) ;
//SecurityContext securityContext =SecurityContextHolder.getContext() ; //SecurityContextHolder 안에 있는 컨텍스트에 접근 
//securityContext.setAuthentication(authentication);
//      
//   
      
 //4번 
//    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getId(), user.getPassword()      )) ;
//System.out.println("ddd");
//    SecurityContextHolder.getContext().setAuthentication(authentication)   ;
   
      
        
//        user = userSerivce.getUser(user.getId()) ;
//      
//        System.out.println("업데이트 유저  :: " +  restult );
//
//		SecurityContextHolder.clearContext();
//	    System.out.println("클리어  :: "      );
//		SecurityUserService securityUserService = new SecurityUserService(user);
//		System.out.println("securityUserService :: " + securityUserService );
//		Authentication  authentication = new UsernamePasswordAuthenticationToken(
//				securityUserService, null, securityUserService.getAuthorities());
//		 
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		System.out.println("  #authentication : "+authentication);
// 아 제발 그만 하고 싶어 진짜 그만 하고 싶 펑 , 내가 너만 6시간째 ,,,, 해야하니
 

      return restult;


  }
    
 

    //스토어 권한 업데이트
    @PostMapping( "json/updateStoreApplicationDocument")
    public String updateStoreApplicationDocument(  StoreApplicationDocument storeApplicationDocument ) throws Exception{
        log.info( " updateStoreApplicationDocument 에 들어옴 ");

        log.info( "  들어온 값 storeApplicationDocument {}" , storeApplicationDocument );
        System.out.println(storeApplicationDocument) ; 
        String returnResult = "";
        int result = userSerivce.updateStoreApplicationDocument( storeApplicationDocument );
        log.info( "##  결과  {} ##" , result );

        //신청서 심사 UPDATE가 성공 적일떄
        if ( result == 1 ) {

            //스토어 정보 가져오기
            storeApplicationDocument = userSerivce.getStoreApplicationDocument( storeApplicationDocument.getStoreApplicationDocumentNumber() );
            if ( storeApplicationDocument.getExaminationStatus().equals( "A" ) ) {  //A 승인일때

                Map<String, Object> map = new HashMap<>();
                map.put( "role" , "store" );
                map.put( "id" , storeApplicationDocument.getId() );
                log.info( "map 값은 :  {}" , map );
                result = 0;
                result = userSerivce.updateUserStore( map );
                log.info( "##신청서 승인된 User , Role 권한 상승  결과  {} ##" , result );

            }

            returnResult = storeApplicationDocument.getExaminationStatus(); // 스토어 신청서 상태 리턴
            System.out.println("결과 ={} " +returnResult ) ; 

             
        } else {
            returnResult = "error";  // result 값이 1이 아니면 update 실패 한거
        }

        return returnResult;
    }


}