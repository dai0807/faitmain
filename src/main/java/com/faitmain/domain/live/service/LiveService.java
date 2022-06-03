package com.faitmain.domain.live.service;

import java.util.Map;

import com.faitmain.domain.live.domain.Live;
import com.faitmain.domain.live.domain.LiveChat;
import com.faitmain.domain.live.domain.LiveProduct;
import com.faitmain.domain.live.domain.LiveReservation;
import com.faitmain.domain.live.domain.LiveUserStatus;

public interface LiveService {
	
	//live
	public int addLive(Live live)  throws Exception;
	
	public int updateLive(Live live) throws Exception;
	
	public int updateLiveStatusCode(int liveNumver) throws Exception;
	
	public Live getLive(int liveNumber) throws Exception;
	
	public Live getLiveByStoreId(String storeId) throws Exception;
	
	public Map<String, Object> getLiveList() throws Exception;
	
	//liveChat
	public int addLiveChat(LiveChat liveChat) throws Exception;
	
	public Map<String, Object> getLiveChatList(LiveChat liveChat) throws Exception;
	
	
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
	
	public Map<String, Object> getLiveUserStatusList(int liveNumber) throws Exception;
	
}

