package com.faitmain.domain.live.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.faitmain.domain.live.domain.Live;
import com.faitmain.domain.live.mapper.LiveMapper;

@Service("liveServiceImpl")
public class LiveServiceImpl implements LiveService {

	@Autowired
	@Qualifier("LiveMapper")
	private LiveMapper liveMapper;
	public void setLiveMapper(LiveMapper liveMapper) {
		this.liveMapper = liveMapper;
	}
	
	public LiveServiceImpl() {
		System.out.println(this.getClass());
	}
	
	public int addPLive(Live live)  throws Exception {
		return liveMapper.addLive(live);
	}
	
	public int updateLive(String writer, String message) throws Exception {
		return liveMapper.updateLive(writer, message);
	}
	
	public int addLiveChat() throws Exception;
	
}
