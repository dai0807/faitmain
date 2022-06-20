package com.faitmain.global.util.security;
import lombok.extern.slf4j.Slf4j;

 import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

 @Slf4j
@Service
public class SecurityOauthUserService extends   DefaultOAuth2UserService {

//후처리	 
	@Override
	 public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
     	
        String provider = userRequest.getClientRegistration().getRegistrationId();
        System.out.println("provider : "+provider);
        log.info("getClientRegistaraton ={}", userRequest.getClientRegistration());
        log.info("getClientRegistaraton ={}", userRequest.getAccessToken());
        log.info("getAttrivutes ={}", super.loadUser(userRequest).getAttributes() );
         
        return super.loadUser(userRequest);
		
		
		
        
	
	}
	
	
}
