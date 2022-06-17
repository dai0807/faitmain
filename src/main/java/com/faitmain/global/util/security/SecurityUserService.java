package com.faitmain.global.util.security;

import com.faitmain.domain.user.domain.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Slf4j
@Service
public class SecurityUserService implements UserDetails{

	// UserDetails : 인증된 핵심 사요자 정보(권한,비밀번호,사용자명,각종상태)를 제공하는  interface
	// 안만들어도 상관없지만 Warning이 발생함
    private static final long serialVersionUID = 1L;
    private Collection<? extends GrantedAuthority> auth;

	//private ArrayList<GrantedAuthority> authorities; 
    private User user;
//	private String username; // ID
//	private String password; // PW
     
    public SecurityUserService() {
    	
    }    
    
    public SecurityUserService( User user) {
		System.out.println("SecurityUserService :: " +user);
		this.user=user;
    }
    
    //ROLE
    //GrantedAuthority는 Authentication 클래스 
    //Authentication 클래스에 getAuthorities() 메소드를 통하여, 인증받은 사용자의 authorities를 조회 가능
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                  return "ROLE_"+user.getRole();  // 권한 부여
            }
        });
		log.info("  ROLE 권한: ={}  " ,auth) ;
		return auth;
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
    
//    SecurityContextHolder
//    SecurityContext를 보관하는 저장소
//    SecurityContext에는 Authentication 인스턴스가 저장된다.
//    Authentication에는 principal, credentials, authorities가 저장된다.
 // 참고 : https://gregor77.github.io/2021/04/21/spring-security-02/  
    
}
