package com.faitmain.domain.user.service;

import com.faitmain.domain.user.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
public class LoginService implements UserDetails{

	// 안만들어도 상관없지만 Warning이 발생함
	 private static final long serialVersionUID = 1L;
	
    private Collection<? extends GrantedAuthority> authorities;

  
    private User user;
//	private String username; // ID
//	private String password; // PW
     
    public LoginService() {
    	
    }    
    
    public LoginService(User user) {
		System.out.println("LoginService :: " +user);
		this.user=user;
    }
    
    //권한인데 이게 뭐지?
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
            	System.out.println("뾰로롱 권한 :: " + user.getRole());
                return user.getRole();
            }
        });
        return roles;
    }

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
