package com.faitmain.domain.live.domain;

import lombok.Data;

@Data
public class LiveUserStatus {
	private int liveNumber;
	private String id;
	private boolean alarmStatus; // false 0 : alarm off / true 1 : alarm on
	private boolean kickStatus;  // false 0 : kick off / true 1 : kick on
	private boolean dumbStatus;  // false 0 : dumb off / true 1 : dumb on
	
}
