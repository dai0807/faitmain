package com.faitmain.domain.live.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Live {
	private int liveNumber;
	private String storeId;
	private String liveTitle;
	private String liveIntro;
	private String liveImage;
	private boolean chattingStatus; // true : chat on / false : chat off
	private boolean liveStatus;  // true : live on / false : live off
	

	@Builder
	  public Live(int liveNumber, String liveTitle) {
	    this.liveNumber = liveNumber;
	    this.liveTitle = liveTitle;
	  }
}
