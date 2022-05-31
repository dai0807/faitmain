package com.faitmain.domain.live.domain;

import lombok.Data;

@Data
public class Live {
	private int liveNumber;
	private String storeId;
	private String liveTitle;
	private String liveIntro;
	private String liveImage;
	private boolean chattingStatus; // true : chat on / false : chat off
	private boolean live_status;  // true : live on / false : live off
}
