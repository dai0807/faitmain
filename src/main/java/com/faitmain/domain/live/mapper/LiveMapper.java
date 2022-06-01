package com.faitmain.domain.live.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.faitmain.domain.live.domain.Live;
import com.faitmain.domain.live.domain.LiveChat;
import com.faitmain.domain.live.domain.LiveProduct;
import com.faitmain.domain.live.domain.LiveReservation;
import com.faitmain.domain.live.domain.LiveUserStatus;

@Mapper
public interface LiveMapper {
	
	//live
	public int addLive(Live live)  throws Exception;
	
	public int updateLive(Live live) throws Exception;
	
	public Live getLive(int liveNumber) throws Exception;
	
	public Map<String, Object> getLiveList() throws Exception;
	
	//liveChat
	public int addLiveChat(LiveChat liveChat) throws Exception;
	
	public Map<String, Object> getLiveChatList(int liveNumber, String writer) throws Exception;
	
	
	//liveProduct
	public int addLiveProduct(LiveProduct liveProduct) throws Exception;
	
	public LiveProduct getLiveProduct(int liveProductNumber) throws Exception;
	
	public Map<String, Object> getLiveProductList(LiveProduct liveProduct) throws Exception;
	
	public int deleteLiveProduct(int liveNumber) throws Exception;
	
	
	//liveReservation
	public int addLiveReservation(LiveReservation liveReservation) throws Exception;
	
	
	//liveUserStatus
	public int addLiveUserStatus(LiveUserStatus liveUserStatus) throws Exception;
	
	public int updateLiveUserStatus(LiveUserStatus liveUserStatus) throws Exception;
	
	public LiveUserStatus getLiveUserStatus(LiveUserStatus liveUserStatus) throws Exception;
	
	public List<LiveUserStatus> getLiveUserStatusList(int liveNumber) throws Exception;
	
}
