package com.faitmain.domain.user.domain;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
 

@Getter
@Setter
@ToString

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
		    private Data regDate ;
		    private String joinPath;
		    
		    private int bookNumber;
		    private int totalPoint ;
 		    private String storelogoImage ;
		    private String storeIntroduction ;
		    private String bankAccountCopyImage ;
		    private String bankName ;
		    private String bankAccountNumber ;
		    private String role;
		    private boolean withdrawalStatus;

		    
		    private UserBan userBan ; 
		
	}

	/*
	 * <result property="userNumber" 					column="user_number" 				jdbcType="INTEGER"/>
		<result property="id" 					column="id" 						jdbcType="VARCHAR"/>
		<result property="password"				column="password" 					jdbcType="VARCHAR" />
		<result property="address" 				column="address" 					jdbcType="VARCHAR"/>
		<result property="postalCode"			column="postal_code" 				jdbcType="INTEGER" />
 		<result property="nickname"				column="nickname" 					jdbcType="VARCHAR" />
 		<result property="storeName" 			column="store_name" 				jdbcType="VARCHAR"/>
 		<result property="name" 				column="name" 						jdbcType="VARCHAR"/>
 	
 		<result property="phoneNumber"			column="phone_number" 				jdbcType="VARCHAR" />
 		<result property="regDate"				column="reg_date" 					jdbcType="DATE" />
		<result property="joinPath" 			column="join_path" 					jdbcType="VARCHAR"/>
		
 		<result property="bookNumber"			column="book_number" 				jdbcType="INTEGER" />
		<result property="totalPoint" 			column="total_point" 				jdbcType="INTEGER"/>
		<result property="storelogoImage" 		column="store_logo_image" 			jdbcType="VARCHAR"/>
 		<result property="storeIntroduction"	column="store_introduction" 		jdbcType="VARCHAR" /> 	
 		<result property="bankAccountCopyImage" column="bank_account_copy_image" 	jdbcType="VARCHAR"/> 			
 		<result property="bankName" 			column="bank_name" 					jdbcType="VARCHAR"/>
 		<result property="bankAccountNumber" 	column="bank_account_number" 		jdbcType="VARCHAR"/>
 		<result property="role"					column="role" 						jdbcType="VARCHAR" />
 		<result property="withdrawalStatus"		column="withdrawal_status" 			jdbcType="Boolean" />  		 
	 * 
	 * */
