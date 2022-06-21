package com.faitmain.domain.live.domain;

import lombok.Data;

@Data
public class LiveUserStatus {
	private int liveNumber;
	private String id;
	private int alarmStatus; // false 0 : alarm off / true 1 : alarm on
	private int kickStatus; // false 0 : kick off / true 1 : kick on
	private int dumbStatus; // false 0 : dumb off / true 1 : dumb on

}
