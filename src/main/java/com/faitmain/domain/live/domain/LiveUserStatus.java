package com.faitmain.domain.live.domain;

import lombok.Data;

@Data
public class LiveUserStatus {
	private int liveNumer;
	private String id;
	private boolean alarmStatus; // true : alarm on / false : alarm off
	private boolean kickStatus;  // true : kick on / false : kick off
	private boolean dumbStatus;  // true : dumb on / false : dumb off
	
}
