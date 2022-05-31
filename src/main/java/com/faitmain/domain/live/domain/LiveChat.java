package com.faitmain.domain.live.domain;

import java.util.Date;

import lombok.Data;

@Data
public class LiveChat {
	private int liveChatNumber;
	private String writer;
	private String chattingMessage;
	private Date sendDate;
}
