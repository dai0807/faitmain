package com.faitmain.domain.customer.domain;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.faitmain.domain.user.domain.User;
import com.faitmain.global.common.Image;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Customer{
	
	private int boardNumber;
	private String boardTitle;
	private String boardContent;
//	private List<Image> boardImage;
	private Date boardRegDate;
	private Date boardUpdateDate;
	private int FAQCategoryCode;
	private char boardType;
	private User customerId;
//	private int totalCount;
	private int viewCount;
	private String deleteYn;
//	private String highlightNoticeYn;
//	private Image image;


	
	
}
