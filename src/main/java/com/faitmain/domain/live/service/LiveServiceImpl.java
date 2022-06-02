package com.faitmain.domain.live.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faitmain.domain.live.domain.Live;
import com.faitmain.domain.live.domain.LiveChat;
import com.faitmain.domain.live.domain.LiveProduct;
import com.faitmain.domain.live.domain.LiveReservation;
import com.faitmain.domain.live.domain.LiveUserStatus;
import com.faitmain.domain.live.mapper.LiveMapper;

import lombok.RequiredArgsConstructor;

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
	
	public Live getLive(int liveNumber) throws Exception {
		return liveMapper.getLive(liveNumber);
	}
	
	public List getLiveList() throws Exception {
		return liveMapper.getLiveList();
	}
	
	//liveChat
	public int addLiveChat(LiveChat liveChat) throws Exception {
		return liveMapper.addLiveChat(liveChat);
	}
	
	public List getLiveChatList(int liveNumber, String writer) throws Exception {
		return liveMapper.getLiveChatList(liveNumber, writer);
	}
	
	
	//liveProduct
	public int addLiveProduct(LiveProduct liveProduct) throws Exception {
		return liveMapper.addLiveProduct(liveProduct);
	}
	
	public LiveProduct getLiveProduct(int liveProductNumber) throws Exception {
		return liveMapper.getLiveProduct(liveProductNumber);
	}
	
	public List getLiveProductList(LiveProduct liveProduct) throws Exception {
		return liveMapper.getLiveProductList(liveProduct);
	}
	
	public int deleteLiveProduct(int liveNumber) throws Exception {
		return liveMapper.deleteLiveProduct(liveNumber);
	}
	
	
	//liveReservation
	public int addLiveReservation(LiveReservation liveReservation) throws Exception {
		return liveMapper.addLiveReservation(liveReservation);
	}
	
	
	//liveUserStatus
	public int addLiveUserStatus(LiveUserStatus liveUserStatus) throws Exception {
		return liveMapper.addLiveUserStatus(liveUserStatus);
	}
	
	public int updateLiveUserStatus(LiveUserStatus liveUserStatus) throws Exception {
		return liveMapper.updateLiveUserStatus(liveUserStatus);
	}
	
	public LiveUserStatus getLiveUserStatus(LiveUserStatus liveUserStatus) throws Exception {
		return liveMapper.getLiveUserStatus(liveUserStatus);
	}
	
	public List<LiveUserStatus> getLiveUserStatusList(int liveNumber) throws Exception {
		return liveMapper.getLiveUserStatusList(liveNumber);
	}
	
}
