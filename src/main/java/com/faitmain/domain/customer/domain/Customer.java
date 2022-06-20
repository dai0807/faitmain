package com.faitmain.domain.customer.domain;


import java.sql.Time;
import java.sql.Timestamp;
import com.faitmain.domain.user.domain.User;
import lombok.Data;

@Data
public class Customer{
	
	private int boardNumber;
	private String boardTitle;
	private String boardContent;
	private Timestamp boardRegDate;
	private Time boardUpdateDate;
	private String FAQCategoryCode;
	private char boardType;
	private User customerId;
//	private int totalCount;
	private int viewCount;
	private String deleteYn;



	
	
}
