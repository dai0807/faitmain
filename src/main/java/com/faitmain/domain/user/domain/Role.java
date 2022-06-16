package com.faitmain.domain.user.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
	
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER"),
	MEMBER("ROLE_MEMBER"),
	STORE("ROLE_STORE");

	
	private String value;

}
