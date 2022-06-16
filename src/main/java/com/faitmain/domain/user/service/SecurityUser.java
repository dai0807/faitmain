package com.faitmain.domain.user.service;

import com.faitmain.domain.user.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
@Component

public class SecurityUser implements UserDetails{

	// 안만들어도 상관없지만 Warning이 발생함
	 private static final long serialVersionUID = 1L;
	
    private Collection<? extends GrantedAuthority> auth;

	//private ArrayList<GrantedAuthority> authorities; 
    private User user;
//	private String username; // ID
//	private String password; // PW
     
    public SecurityUser() {
    	
    }    
    
    public SecurityUser(User user) {
		System.out.println("LoginService :: " +user);
		this.user=user;
    }
    
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
 		
		auth.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
            	System.out.println(" 권한 :: " + user.getRole());
                return "ROLE_"+user.getRole();  // 권한 부여
            }
        });
		
		//role 부여 깔끔하게 리펙토링 제발 부탁 
		
		System.out.println("권한 :   "  + auth );
		
		return auth;
	}
    
    
    
    
 
    
    
//    //권한인데 이게 뭐지?   private Collection<? extends GrantedAuthority> authorities; 와 한몸 
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> roles = new ArrayList<>();
//        roles.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//            	System.out.println("뾰로롱 권한 :: " + user.getRole());
//                return user.getRole();
//            }
//        });
        
        
//        for(GrantedAuthority grant :roles) {  // 쓰레기값 나옴  
//        	System.out.println("Role :: " + grant)  ;
//        }
//        
//        return roles;
//    }

    
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//    	    List<GrantedAuthority> authorities = new ArrayList<>();   
//    	    authorities.add(new SimpleGrantedAuthority(user.getRole()));
//        
////        for(GrantedAuthority grant :roles) {  // 쓰레기값 나옴  
////        	System.out.println("Role :: " + grant)  ;
////        }
//        
//        return  authorities ;
//    }

 
    
    
    @Override
    public String getPassword(){
        return user.getPassword();
    }

    //인증할때 id
    @Override
    public String getUsername(){
        return user.getId();
    }
   


	@Override
	public boolean isAccountNonExpired() {
		// 계정 만료 판단 로직 만료면 false 리턴
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// 계정 잠김 판단 로직 잠김이면 false 리턴
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// 비밀번호가 만료 검사 만료 됐으면 false
		return true;
	}
    @Override
    public boolean isEnabled(){
        return true;
    }
}
