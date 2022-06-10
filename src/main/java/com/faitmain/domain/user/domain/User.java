package com.faitmain.domain.user.domain;


import lombok.Data;

import java.sql.Date;


@Data
public class User {

	private Integer userNumber;

	private String id;

	private String password;

	private String gender;

	private String userAddress1;

	private String userAddress2;

	private String userAddress3;

	private String nickname;

	private String phoneNumber;

	private String name;

	private Date regDate;

	private String joinPath;

	private Integer bookNumber;

	private Integer totalPoint;

	private String storeLogoImage;

	private String storeIntroduction;

	private String role;

	private String storeName;

	private Boolean withdrawalStatus;

	private int storeApplicationDocumentNumber;

}


/*@Data
public class User{
    private int userNumber;
    private String id;
    private String password;
    private String gender;
    private String address;
    private int postalCode;
    private String nickname;
    private String storeName;
    private String name;

    private String phoneNumber;
    private Date regDate;
    private String joinPath;

    private int bookNumber;
    private int totalPoint;
    private String storelogoImage;
    private String storeIntroduction;
    private String bankAccountCopyImage;
    private String bankName;
    private String bankAccountNumber;
    private String role;
    private boolean withdrawalStatus;   // on 탈퇴 , off 탈퇴 상퇴 x




    private UserBan userBan;

}*/

 
