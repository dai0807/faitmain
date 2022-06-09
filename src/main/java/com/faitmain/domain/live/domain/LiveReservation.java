package com.faitmain.domain.live.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class LiveReservation {
	private int liveReservationNumber;
	private String storeId;
	private Date reservationDate;
	private String title;
	private String start;
	private String end;
}
