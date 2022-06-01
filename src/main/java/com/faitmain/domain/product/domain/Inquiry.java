package com.faitmain.domain.product.domain;

import java.sql.Date;

import com.faitmain.domain.user.domain.User;

import lombok.Data;

@Data
public class Inquiry {
	
	private int inquiryNumber;
	private String inquiryTitle;
	private String inquiryContent;
	private Date inquiryDate;
	private String userId;
	private String storeId;
	private Product inquiryProduct;
	private boolean inquiryReplyStatus;
	private String inquiryReplyCotent;
	private Date inquiryReplyDate;
	private boolean secret;

}