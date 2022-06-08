package com.faitmain.domain.live.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faitmain.domain.live.controller.LiveController;
import com.faitmain.domain.live.domain.Live;
import com.faitmain.domain.live.domain.LiveChat;
import com.faitmain.domain.live.domain.LiveProduct;
import com.faitmain.domain.live.domain.LiveReservation;
import com.faitmain.domain.live.domain.LiveUserStatus;
import com.faitmain.domain.live.mapper.LiveMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("liveServiceImpl")
@Transactional
@RequiredArgsConstructor
public class LiveServiceImpl implements LiveService {

	@Autowired
	private LiveMapper liveMapper;
	
	public LiveServiceImpl(LiveMapper liveMapper) {
		this.liveMapper = liveMapper;
	}
	
//	public void setLiveMapper(LiveMapper liveMapper) {
//		this.liveMapper = liveMapper;
//	}
	
//	public LiveServiceImpl() {
//		System.out.println(this.getClass());
//	}
	

	//live
	public int addLive(Live live)  throws Exception {
		return liveMapper.addLive(live);
	}
	
	public int updateLive(Live live) throws Exception {
		return liveMapper.updateLive(live);
	}
	
	public int updateLiveStatusCode(int liveNumber) throws Exception {
		Live live = liveMapper.getLive(liveNumber);
		return liveMapper.updateLiveStatusCode(live);
	}
	
	public Live getLive(int liveNumber) throws Exception {
		return liveMapper.getLive(liveNumber);
	}
	
	public Live getLiveByStoreId(String storeId) throws Exception {
		return liveMapper.getLiveByStoreId(storeId);
	}
	
	public Map<String, Object> getLiveList() throws Exception {
		log.info("Impl getLiveList start...");
		List<Live> list = liveMapper.getLiveList();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("liveList", list);
		log.info("Impl getLiveList end...");
		return map;
	}
	
	//liveChat
	public int addLiveChat(LiveChat liveChat) throws Exception {
		
		
		return liveMapper.addLiveChat(liveChat);
	}
	
	public Map<String, Object> getLiveChatList(LiveChat liveChat) throws Exception {
		
		List<LiveChat> list = liveMapper.getLiveChatList(liveChat);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", list);
		
		return map;
	}
	
	
	//liveProduct
	public int addLiveProduct(LiveProduct liveProduct) throws Exception {
		return liveMapper.addLiveProduct(liveProduct);
	}
	
	public LiveProduct getLiveProduct(int liveProductNumber) throws Exception {
		return liveMapper.getLiveProduct(liveProductNumber);
	}
	
	public Map<String, Object> getLiveProductList(LiveProduct liveProduct) throws Exception {
		
		List<LiveProduct> list = liveMapper.getLiveProductList(liveProduct);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", list);
		
		return map;
	}
	
	public int deleteLiveProduct(int liveNumber) throws Exception {
		return liveMapper.deleteLiveProduct(liveNumber);
	}
	
	
	//liveReservation
	public int addLiveReservation(LiveReservation liveReservation) throws Exception {
		return liveMapper.addLiveReservation(liveReservation);
	}
	
	public int deleteLiveReservation(int liveReservationNumber) throws Exception{
		return liveMapper.deleteLiveReservation(liveReservationNumber);
	}
	
	public Map<String, Object> getLiveReservationList(String reservationDate) throws Exception{
		
		List<LiveReservation> list = liveMapper.getLiveReservationList(reservationDate);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", list);
		
		return map;
	}
	
	//liveUserStatus
	public int addLiveUserStatus(LiveUserStatus liveUserStatus) throws Exception {
		
			liveUserStatus.getId();//수정해야댐 수정해야댐 수정해야댐 무지성으로 걍 박아놓은거임
		return liveMapper.addLiveUserStatus(liveUserStatus);
	}
	
	public int updateLiveUserStatus(LiveUserStatus liveUserStatus) throws Exception {
		return liveMapper.updateLiveUserStatus(liveUserStatus);
	}
	
	public LiveUserStatus getLiveUserStatus(LiveUserStatus liveUserStatus) throws Exception {
		return liveMapper.getLiveUserStatus(liveUserStatus);
	}
	
	public Map<String, Object> getLiveUserStatusList(int liveNumber) throws Exception {
		
		List<LiveUserStatus> list = liveMapper.getLiveUserStatusList(liveNumber);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", list);
		
		return map;
	}
	
}
