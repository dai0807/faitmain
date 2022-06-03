package com.faitmain.domain.user.domain;


import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
 


	@Data
	public class User {
		  private int userNumber ;
	  	   private String id;
		   private String password;
		    private String gender;
		    private String address;
		    private int  postalCode;
		    private String nickname ;
		    private String storeName ;
		    private String name;

		    private String phoneNumber;
		    private Date regDate ;
		    private String joinPath;
		    
		    private int bookNumber;
		    private int totalPoint ;
 		    private String storelogoImage ;
		    private String storeIntroduction ;
		    private String bankAccountCopyImage ;
		    private String bankName ;
		    private String bankAccountNumber ;
		    private String role;
		    private boolean withdrawalStatus;   // on 탈퇴 , off 탈퇴 상퇴 x 

		    private int storeApplicationDocumentNumber ;

		    
		    private UserBan userBan ; 
		
	}

 
