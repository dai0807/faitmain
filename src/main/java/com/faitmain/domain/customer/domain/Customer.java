package com.faitmain.domain.customer.domain;

import java.sql.Date;

import lombok.Data;


@Data
public class Customer{
	
	private int boardNumber;
	private String boardTitle;
	private String boardContent;
	private Date regDate;
	private Date updateDate;
	private int FAQCategoryCode;
	private char boardType;
	private String customerId;
//	private int viewCount;
	
	
}
