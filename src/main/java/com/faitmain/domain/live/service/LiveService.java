package com.faitmain.domain.live.service;

import java.util.Map;

import com.faitmain.domain.live.domain.Live;
import com.faitmain.domain.live.domain.LiveProduct;
import com.faitmain.domain.live.domain.LiveUserStatus;

public interface LiveService {
	
	//live
	public int addLive(Live live)  throws Exception;
	
	public int updateLive(Live live) throws Exception;
	
	public Live getLive(int liveNumber) throws Exception;
	
	public Map<String, Object> getLiveList() throws Exception;
	
	//liveChat
	public int addLiveChat(String writer, String message) throws Exception;
	
	public Map<String, Object> getLiveChatList(int liveNumber, String writer) throws Exception;
	
	
	//liveProduct
	public int addLiveProduct(int livenumber, int liveReservationNumber, int productNumber, String liveProductMainImage) throws Exception;
	
	public LiveProduct getLiveProduct(int liveProductNumber) throws Exception;
	
	public Map<String, Object> getLiveProductList(LiveProduct liveProduct) throws Exception;
	
	public int deleteLiveProduct(int liveNumber) throws Exception;
	
	
	//liveReservation
	public int addLiveReservation(String storeId, String reservationDate) throws Exception;
	
	
	//liveUserStatus
	public int addLiveUserStatus(int liveNumber, String id) throws Exception;
	
	public int updateLiveUserStatus(LiveUserStatus liveUserStatus) throws Exception;
	
	public LiveUserStatus getLiveUserStatus(int liveNumber, String id) throws Exception;
	
	public Map<String, Object> getLiveUserStatusList(int liveNumber) throws Exception;
	
}

