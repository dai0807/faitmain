package com.faitmain.global.common;

import lombok.Data;

@Data
public class Search{
	private int currentPage;
	private int pageSize;
	private String searchCondition;
	private String searchKeyword;
}
