package com.faitmain.domain.customer.domain;

import java.sql.Date;

import lombok.Data;


@Data
public class BanStatus {
	
	private int reportNumber;
	private String respondentId;
	private String respondentNickname;
	private String respondentStoreName;
	private int statusNumber;
	private int periodNumber;
	private Date endDate;
	
	
	
	
}
