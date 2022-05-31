package com.faitmain.domain.live.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.faitmain.domain.live.domain.Live;

@Mapper
public interface LiveMapper {
	
	//live
	public int addLive(Live live);
	public int updateLive(String writer, String message);
	

	
	//liveChat
	public int addLiveChat();	
	
	//liveReservation
	
	
	//liveProduct
	
	
	//liveUserStatus
	
}
