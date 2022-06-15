package com.faitmain.domain.user.service;

import com.faitmain.domain.user.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginDetailService implements UserDetailsService{

    @Autowired
    UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException{
    	System.out.println("username :: + " + username) ;
    	
        User user1 = new User();
        user1.setId( username );
        	System.out.println(user1.getId());
        User user = null;
        try {
        	
            user = userService.getUser( username );
            System.out.println(user);
            
        } catch ( Exception e ) {
            throw new RuntimeException( e );
        }

        if ( user != null ) { // DB에 유저 정보를 확인하고 있다면 , loginDetail에 User를 담아서 반환하며 시큐리티 세션에 저장  
            LoginService loginDetail = new LoginService();
            loginDetail.setUser( user ); // 유저 디테일에 user 던짐
            	System.out.println("성공! ");
            return loginDetail;
        } else {
            throw new UsernameNotFoundException( "유저없음" );
        }
    }
}
