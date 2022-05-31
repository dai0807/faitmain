package com.faitmain.domain.live.service;

import com.faitmain.domain.live.domain.Live;

public interface LiveService {

	public int addPLive(Live live)  throws Exception;
	
	public int updateLive(String writer, String message) throws Exception;
	
	public int addLiveChat() throws Exception;
	
}

