package com.faitmain.domain.user.service;

import com.faitmain.domain.user.domain.User;
import com.faitmain.domain.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//UserDetailsService 를 구현 함 
//UserDetailsService는 비지니스 메서드 사용 가능 
@Service
@RequiredArgsConstructor

public class LoginDetailService implements UserDetailsService{

    @Autowired
    UserMapper usermapper;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		System.out.println("id :: + " + id) ;
		User user = usermapper.getUser(id) ;
		if(user == null) {
            throw new UsernameNotFoundException( "유저없음" );

		}  
		
 	            LoginService loginDetail = new LoginService();
             loginDetail.setUser( user ); // 유저 디테일에 user 던짐
 	           System.out.println("유저 있음");
        
//		//	            	return loginDetail;
//         
		return   new LoginService(user);
	}

	
	
//    @Override
//    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException{
//    	//loadUserByUsername는 DB에 접근해서 사용자 정보를 가져오는 역활을 합니다. 
//    	// 회원 찾아주는 로직 
//        try {
//    	System.out.println("username :: + " + username) ;
//    	User user = usermapper.getUser( username );
//    	System.out.println("user :: + " + user) ;
//
//	        if (   user.getId() != null ) { // DB에 유저 정보를 확인하고 있다면 , loginDetail에 User를 담아서 반환하며 시큐리티 세션에 저장  
//	        	
//	        	
//	        
//			//	            LoginService loginDetail = new LoginService(user);
//			//	            loginDetail.setUser( user ); // 유저 디테일에 user 던짐
//			//	            	System.out.println("유저 있음");
//			//	       
//			//	            	return loginDetail;
//             
//	        	
//	        	return  new LoginService(user);
//	        	
//	        		}else {
//	        			
//		            	System.out.println("유저 없");
//	                    throw new UsernameNotFoundException( "유저없음" );
//        			
//	        		}
//	        
//	        
//        }catch ( Exception e ) {
//            throw null ;
//        }
//
//    }
    
    
}
