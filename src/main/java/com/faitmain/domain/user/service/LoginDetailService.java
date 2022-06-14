//package com.faitmain.domain.user.service;
//
//import com.faitmain.domain.user.domain.User;
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class LoginDetailService implements UserDetailsService{
//
//    @Autowired
//    UserServiceImpl userService;
//
//    @Override
//    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException{
//
//        User user1 = new User();
//        user1.setId( username );
//
//        User user = null;
//        try {
//            user = userService.getUser( user1.getId() );
//        } catch ( Exception e ) {
//            throw new RuntimeException( e );
//        }
//
//        if ( user != null ) {
//            LoginService loginDetail = new LoginService();
//            loginDetail.setUser( user );
//
//            return loginDetail;
//        } else {
//            throw new UsernameNotFoundException( "유저없음" );
//        }
//    }
//}
