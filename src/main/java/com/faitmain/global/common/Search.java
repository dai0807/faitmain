package com.faitmain.global.common;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Data
public class Search{
	
	private int currentPage;
	private int pageSize;
	private String searchCondition;
	private String searchKeyword;
	private String orderName;
	
	@Getter(AccessLevel.NONE)
	private int endRowNum;
	
	@Getter(AccessLevel.NONE)
	private int startRowNum;
	
	public int getEndRowNum() {
		return getCurrentPage()*getPageSize();
	}
	
	public int getStartRowNum() {
		return (getCurrentPage()-1)*getPageSize()+1;
	}
}
