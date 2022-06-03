package com.faitmain.domain.customer.domain;

import java.sql.Date;
import java.util.List;

import com.faitmain.global.common.Image;

import lombok.Data;

@Data
public class Customer{
	
	private int boardNumber;
	private String boardTitle;
	private String boardContent;

	private List<Image> boardImage;
	private Date regDate;
	private Date updateDate;
	private int FAQCategoryCode;
	private char boardType;
	private String customerId;
	private int totalCount;



	
	
}
