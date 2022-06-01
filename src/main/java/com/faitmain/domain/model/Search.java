package com.faitmain.domain.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Search{
	
	///Field

	private int currentPage;
	private String searchCondition;
	private String searchKeyword;
	private int pageSize;
	private int endRowNum;
	private int startRowNum;
	
 
	
}
