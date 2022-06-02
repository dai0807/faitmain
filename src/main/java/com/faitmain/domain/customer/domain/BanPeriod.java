package com.faitmain.domain.customer.domain;

import java.sql.Date;

public class BanPeriod {
	
	private int report_number;
	private String respondent_id;
	private String respondent_nickname;
	private String respondent_store_name;
	private int status_number;
	private int ban_period_number;
	private Date ban_end_date;
	
	public int getReport_number() {
		return report_number;
	}
	public void setReport_number(int report_number) {
		this.report_number = report_number;
	}
	public String getRespondent_id() {
		return respondent_id;
	}
	public void setRespondent_id(String respondent_id) {
		this.respondent_id = respondent_id;
	}
	public String getRespondent_nickname() {
		return respondent_nickname;
	}
	public void setRespondent_nickname(String respondent_nickname) {
		this.respondent_nickname = respondent_nickname;
	}
	public String getRespondent_store_name() {
		return respondent_store_name;
	}
	public void setRespondent_store_name(String respondent_store_name) {
		this.respondent_store_name = respondent_store_name;
	}
	public int getStatus_number() {
		return status_number;
	}
	public void setStatus_number(int status_number) {
		this.status_number = status_number;
	}
	public int getBan_period_number() {
		return ban_period_number;
	}
	public void setBan_period_number(int ban_period_number) {
		this.ban_period_number = ban_period_number;
	}
	public Date getBan_end_date() {
		return ban_end_date;
	}
	public void setBan_end_date(Date ban_end_date) {
		this.ban_end_date = ban_end_date;
	}
	
	@Override
	public String toString() {
		return "BanPeriod [report_number=" + report_number + ", respondent_id=" + respondent_id
				+ ", respondent_nickname=" + respondent_nickname + ", respondent_store_name=" + respondent_store_name
				+ ", status_number=" + status_number + ", ban_period_number=" + ban_period_number + ", ban_end_date="
				+ ban_end_date + "]";
	}
	
	
	
}
