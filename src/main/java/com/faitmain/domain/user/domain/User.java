package com.faitmain.domain.user.domain;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
	

	@ToString
	public class User {
		  private int userNumber ;
	  	   private String id;
		   private String password;
		    private String gender;
		    private String address;
		    private int  postalCode;
		    private String nickName ;
		    private String phoneNumber;
		    private String role;
		    private Data regDate ;
		    private String joinPath;
		    private int totalPoint ;
		    private String storeName ;
		    private String storelogoImage ;
		    private String storeIntroduction ;
		    private String bankAccountCopyImage ;
		    private String bankAccountNumber ;
		    private String bankName ;
		    private UserBan userBan ; 
		
	}

	
