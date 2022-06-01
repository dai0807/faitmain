package com.faitmain.domain.model;

import lombok.Data;

@Data
public class Search{
	private int currentPage;
	private int pageSize;
	private String searchCondition;
	private String searchKeyword;
}
