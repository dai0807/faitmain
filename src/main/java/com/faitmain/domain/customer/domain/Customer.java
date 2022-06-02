package com.faitmain.domain.customer.domain;

import java.sql.Date;

public class Customer{
	
	private int customer_board_number;
	private String customer_board_title;
	private String customer_board_content;
	private Date reg_date;
	private Date update_date;
	private int customer_FAQ_category_code;
	private char customer_board_type;
	private String customer_id;
	private int customer_count;
	
	public int getCustomer_board_number() {
		return customer_board_number;
	}

	public void setCustomer_board_number(int customer_board_number) {
		this.customer_board_number = customer_board_number;
	}
	public String getCustomer_board_title() {
		return customer_board_title;
	}
	public void setCustomer_board_title(String customer_board_title) {
		this.customer_board_title = customer_board_title;
	}
	public String getCustomer_board_content() {
		return customer_board_content;
	}
	public void setCustomer_board_content(String customer_board_content) {
		this.customer_board_content = customer_board_content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	public int getCustomer_FAQ_category_code() {
		return customer_FAQ_category_code;
	}
	public void setCustomer_FAQ_category_code(int customer_FAQ_category_code) {
		this.customer_FAQ_category_code = customer_FAQ_category_code;
	}
	public char getCustomer_board_type() {
		return customer_board_type;
	}
	public void setCustomer_board_type(char customer_board_type) {
		this.customer_board_type = customer_board_type;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public int getCustomer_count() {
		return customer_count;
	}
	public void setCustomer_count(int customer_count) {
		this.customer_count = customer_count;
	}
	
	@Override
	public String toString() {
		return "Customer [customer_board_number=" + customer_board_number + ", customer_board_title="
				+ customer_board_title + ", customer_board_content=" + customer_board_content + ", reg_date=" + reg_date
				+ ", update_date=" + update_date + ", customer_FAQ_category_code=" + customer_FAQ_category_code
				+ ", customer_board_type=" + customer_board_type + ", customer_id=" + customer_id + ", customer_count="
				+ customer_count + "]";
	}
}
