package com.faitmain.global.util.security;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.faitmain.domain.user.domain.User;
import com.faitmain.domain.user.mapper.UserMapper;
import com.faitmain.domain.user.service.UserSerivce;
import com.faitmain.global.util.security.userinfo.NaverUserInfo;
import com.faitmain.global.util.security.userinfo.OAuth2UserInfo;

 @Slf4j
@Service
@RequiredArgsConstructor

public class SecurityOauthUserService extends   DefaultOAuth2UserService {

//후처리	 
//	    @Autowired
//	    @Qualifier( "userServiceImpl" )
//	    private UserSerivce userSerivce;

//		
	    @Autowired
	    UserMapper usermapper;
	    
//		 @Autowired
//		   PasswordEncoder pwdEncoder;	
	 
	@Override
	 public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
     	
        String provider = userRequest.getClientRegistration().getRegistrationId();
        System.out.println("provider : "+provider);
        log.info("getClientRegistaraton ={}", userRequest.getClientRegistration());
        log.info("getClientRegistaraton ={}", userRequest.getAccessToken());
        log.info("getAttrivutes ={}", super.loadUser(userRequest).getAttributes() );
         
        
        
        OAuth2UserInfo oAuth2UserInfo = null;	//추가
        String provider2 = userRequest.getClientRegistration().getRegistrationId();    
        
        log.info("provider2 ={}", provider2 );
        if(provider.equals("naver")){
            oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
        }
        
        String providerId = oAuth2UserInfo.getProviderId();	//수정
        String username = provider+"_"+providerId;  			
   
        
        log.info("providerId ={}   ,username ={}  ", providerId , username);
        
  

      
        
       
		User user = null;
	 
			user =  usermapper.getUser(providerId );
	 
        System.out.println("user" + user);

		if(user == null) {
	           System.out.println("유저 없음");
	           User user2 = new User() ; 
	           user2.setId(providerId)   	;
	           user2.setPassword(providerId);   	
	           user2.setRole( "user" );
	           
	           int result = usermapper.addUser( user2 );

	           log.info( "restut {} = 1일때 회원가입 완료" , result );

	           user =user2 ;
		}  
        
		return new SecurityUserService( user  ,oAuth2UserInfo);

        
 		
		
		
        
	
	}
	
	
}
