package com.faitmain.domain.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.faitmain.domain.user.domain.StoreApplicationDocument;
import com.faitmain.domain.user.domain.User;
import com.faitmain.domain.user.service.UserSerivce;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping( "/user/*" )
public class UserRestController {

	   @Autowired
	   @Qualifier("userServiceImpl")	   
	   private UserSerivce userSerivce;
	   
	   public UserRestController() {
		    log.info(  "Controller {}" , this.getClass() );
		   
	   }
	
	// 중복 체크 4개 나 있음 !!!

		@PostMapping( value="json/updateUser"  )
		public int ajaxupdateUser( @RequestParam ("id") String id ,
				@RequestParam("address") String address,
				@RequestParam("postalCode") int postalCode,
				@RequestParam("nickname") String nickname,
				@RequestParam("phoneNumber") String phoneNumber) throws Exception {
			
			User user = new User();
			user.setId(id);
			user.setAddress(address);
			user.setPostalCode(postalCode);
			user.setNickname(nickname);
			user.setPhoneNumber(phoneNumber) ;
			
		//아직  checkDuplication 없음 
			int result = 0 ;

			log.info("updateUser :: user 출력   {} "  ,  user );
		
		//	result = userSerivce.updateUser(user);
 			
			result = userSerivce.updateUser(user) ;
			log.info("updateUser :: result 출력   {} "  ,  result );
		
			
			return result ;
		}
//		
	   
	   
	   
		@GetMapping( value="idCheck" )
		public int idCheck( @RequestParam ("id") String  id ) throws Exception {
		//아직  checkDuplication 없음 
			

			log.info("중복체크 id  {} "  , id);
			Map<String, Object> map = new HashMap<>();
			map.put("checkcondition" , "id") ;
			map.put("checkkeyword" , id) ;
			int cnt = userSerivce.getchechDuplication(map) ;
			log.info("id의 중복 검사 결과 {}"   +  cnt );		
			// 숫자가 1 이면 중복 , 0이면 없음 
			
			return cnt ;
		}
		
		
		@GetMapping( value="nicknameCheck")
		public int nameCheck( @RequestParam ("nickname") String  nickname ) throws Exception {
		//아직  checkDuplication 없음 
						
			log.info("중복체크 닉네임 {} " ,  nickname);
			
			Map<String, Object> map = new HashMap<>();
			map.put("checkcondition" , "nickname") ;
			map.put("checkkeyword" , nickname) ;

			int cnt = userSerivce.getchechDuplication(map) ;

			log.info("nicknameCheck {}",     cnt );		
			// 숫자가 1 이면 중복 , 0이면 없음 
			 return cnt ;
		}	
	
		@GetMapping( value="phoneNumbereCheck")
		public int phoneNumber( @RequestParam ("phoneNumber") String  phoneNumber ) throws Exception {
		//아직  checkDuplication 없음 
						
			log.info("중복체크 nicknameCheck {} " ,  phoneNumber);
			
			Map<String, Object> map = new HashMap<>();
			map.put("checkcondition" , "phone_number") ;
			map.put("checkkeyword" , phoneNumber) ;

			int cnt = userSerivce.getchechDuplication(map) ;

			log.info("nicknameCheck {} "   ,  cnt );		
			// 숫자가 1 이면 중복 , 0이면 없음 
			 return cnt ;
		}		
	
	
		@GetMapping( value="storeNameheck")
		public int storeName( @RequestParam ("storeName") String  storeName ) throws Exception {
		//아직  checkDuplication 없음 
						
			log.info("중복체크 nicknameCheck {} " ,  storeName);
			
			Map<String, Object> map = new HashMap<>();
			map.put("checkcondition" , "store_name") ;
			map.put("checkkeyword" , storeName) ;

			int cnt = userSerivce.getchechDuplication(map) ;

			System.out.println("storeName  "   +  cnt );		
			// 숫자가 1 이면 중복 , 0이면 없음 
			 return cnt ;
		}		
	
		
		// 휴대폰 문자보내기
		@GetMapping( value="uphoneCheck")
		public  String sendSMS(@RequestParam("phone") String userPhoneNumber ,  HttpSession session) throws Exception {
			
			//인증번호 4자리 난수로  생성 
			int randomNumber = (int)((Math.random()* (9999 - 1000 + 1)) + 1000);
			//
			log.info("  randomNumber 값 체크 ::  {} " ,  randomNumber);
			userSerivce.sendCertificationSms(userPhoneNumber,randomNumber);
			 session.setAttribute("userAuth", randomNumber); // 인증 세션  세션 시간 지정해 줘야함 
			 

 
 			// 숫자가 1 이면 중복 , 0이면 없음 
			
			
			
			return "ddd" ;
		}		
		
		// 휴대폰 문자보내기
		@GetMapping( value="smsCertificationRequest")
		public  String smsCertificationRequest(@RequestParam("phone") String userPhoneNumber ,  @RequestParam("phone2") String smsCertification ,  HttpSession session ,HttpServletRequest request) throws Exception {
			log.info(" 인증 하러옴  "  );

			String result = "F";
			log.info(" userPhoneNumber {} :  smsCertification {} ",userPhoneNumber,smsCertification  );
			log.info(" session.getAttributeNames{}" + session.getAttributeNames());

			
		 
				if( request.getSession(true).getAttribute("userAuth") != null ) {
					log.info("인증 세션 있음  "  );
					log.info(" 세션 있음 {}  " , request.getSession(true).getAttribute("userAuth")   );
 
					String sessionAuth = request.getSession(true).getAttribute("userAuth").toString() ; // 세션에 있는 값 .toString()
					log.info("userAuth의 인증 값은 {} " , sessionAuth) ;
					log.info("입력받은 번호 값 {}  , 세션 인증값 {}" , smsCertification,sessionAuth) ;
		 						if   (      sessionAuth.equals(smsCertification)             ) {
		 							result = "T";
									log.info("인증완료 ") ;
		
								}else {
									result = "F" ;
											log.info("인증값 틀림 ") ;
											
								}
				
					
				}else {
					log.info("세션 없음 ");
					
				}
				
				
				log.info("Result::  {}" , result);
			
			
			
			return result ;
		}		
		
		//스토어 권한 업데이트
	    @PostMapping("/updateStoreApplicationDocument")
	    public String updateStoreApplicationDocument(@RequestBody StoreApplicationDocument storeApplicationDocument ) throws Exception {
	    	
	    	log.info("  들어온 값 storeApplicationDocument {}" , storeApplicationDocument) ;
	    	String returnResult ="" ; 
 			 int result = userSerivce.updateStoreApplicationDocument(storeApplicationDocument);
				log.info("##kakaoUser 결과  {} ##" , result);
				
				//신청서 심사 UPDATE가 성공 적일떄 
			if(result ==1 ) {
				
				//스토어 정보 가져오기 
				storeApplicationDocument = userSerivce.getStoreApplicationDocument(storeApplicationDocument.getStoreApplicationDocumentNumber()) ;
					if(storeApplicationDocument.getExaminationStatus().equals("A")) {  //A 승인일때 
						
						Map<String,Object> map = new HashMap<>();
						map.put("role", "store") ;
						map.put("id", storeApplicationDocument.getId()) ;
						log.info("map 값은 :  {}" ,map);
						result = 0 ;
			 			   result = userSerivce.updatUserStore(map);
							log.info("##신청서 승인된 User , Role 권한 상승  결과  {} ##" , result);
						
					}
					
					returnResult=storeApplicationDocument.getExaminationStatus() ; // 스토어 신청서 상태 리턴 
				
			}else {
				returnResult="error" ;  // result 값이 1이 아니면 update 실패 한거
			}
				
	        return returnResult ;
	    }
 	 
				
	
}
