package com.faitmain.domain.live.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.faitmain.domain.live.domain.Live;
import com.faitmain.domain.live.domain.LiveProduct;
import com.faitmain.domain.live.domain.LiveUserStatus;
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
	

//live
	public int addLive(Live live)  throws Exception {
		return liveMapper.addLive(live);
	}
	
	public int updateLive(Live live) throws Exception {
		return liveMapper.updateLive(live);
	}
	
	public Live getLive(int liveNumber) throws Exception {
		return liveMapper.getLive(liveNumber);
	}
	
	public Map<String, Object> getLiveList() throws Exception {
		return liveMapper.getLiveList();
	}
	
	//liveChat
	public int addLiveChat(String writer, String message) throws Exception {
		return liveMapper.addLiveChat(writer, message);
	}
	
	public Map<String, Object> getLiveChatList(int liveNumber, String writer) throws Exception {
		return liveMapper.getLiveChatList(liveNumber, writer);
	}
	
	
	//liveProduct
	public int addLiveProduct(int livenumber, int liveReservationNumber, int productNumber, String liveProductMainImage) throws Exception {
		return liveMapper.addLiveProduct(livenumber, liveReservationNumber, productNumber, liveProductMainImage);
	}
	
	public LiveProduct getLiveProduct(int liveProductNumber) throws Exception {
		return liveMapper.getLiveProduct(liveProductNumber);
	}
	
	public Map<String, Object> getLiveProductList(LiveProduct liveProduct) throws Exception {
		return liveMapper.getLiveProductList(liveProduct);
	}
	
	public int deleteLiveProduct(int liveNumber) throws Exception {
		return liveMapper.deleteLiveProduct(liveNumber);
	}
	
	
	//liveReservation
	public int addLiveReservation(String storeId, String reservationDate) throws Exception {
		return liveMapper.addLiveReservation(storeId, reservationDate);
	}
	
	
	//liveUserStatus
	public int addLiveUserStatus(int liveNumber, String id) throws Exception {
		return liveMapper.addLiveUserStatus(liveNumber, id);
	}
	
	public int updateLiveUserStatus(LiveUserStatus liveUserStatus) throws Exception {
		return liveMapper.updateLiveUserStatus(liveUserStatus);
	}
	
	public LiveUserStatus getLiveUserStatus(int liveNumber, String id) throws Exception {
		return liveMapper.getLiveUserStatus(liveNumber, id);
	}
	
	public Map<String, Object> getLiveUserStatusList(int liveNumber) throws Exception {
		return liveMapper.getLiveUserStatusList(liveNumber);
	}
	
	}
