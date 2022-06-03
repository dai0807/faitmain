package com.faitmain.domain.live.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faitmain.domain.live.domain.Live;
import com.faitmain.domain.live.domain.LiveChat;
import com.faitmain.domain.live.domain.LiveProduct;
import com.faitmain.domain.live.domain.LiveUserStatus;
import com.faitmain.domain.live.service.LiveService;

@RestController
@RequestMapping("/live/*")
public class LiveRestController {

	// Field
	@Autowired
	private LiveService liveService;
	
	public LiveRestController() {
		System.out.println(this.getClass());
	}
	
	
	// METHOD
	// LIVE
	@GetMapping("json/getLiveList")
	public Map<String, Object> getLiveList() throws Exception{
		System.out.println("/live/json/getLiveList : GET start...");
		
		Map<String, Object> map = liveService.getLiveList();
		
		System.out.println("/live/json/getLiveList : GET end...");
		return map;
	}
	
	@GetMapping("json/getLiveProduct")
	public Map<String, Object> getLiveProduct(@RequestBody LiveProduct liveProduct) throws Exception{
		System.out.println("/live/json/getLiveProduct : GET start...");
		
		Map<String, Object> map = liveService.getLiveProductList(liveProduct);
		
		System.out.println("/live/json/getLiveProduct : GET end...");
		
		return map;
	}
	
	@PostMapping("json/updateLive")
	public Live updateLive(@RequestBody Live live) throws Exception {
		System.out.println("/live/json/updateLive : POST start...");
		
		System.out.println("result = " + liveService.updateLive(live));
		
		System.out.println("/live/json/updateLive : POST end...");
		
		return live;
	}
	
	@PostMapping("json/sendMessage")
	public void sendMessage(@RequestBody LiveChat liveChat) throws Exception{
		System.out.println("/live/json/sendMessage : POST start...");
		
		System.out.println("result : " + liveService.addLiveChat(liveChat));
		
		System.out.println("/live/json/sendMessage : POST end...");
	}
	
	@PostMapping("json/updateLiveUserStatus")
	public void LiveUserStatus(@RequestBody LiveUserStatus liveUserStatus) throws Exception{
		System.out.println("/live/json/updateLiveUserStatus : POST start...");
		
		if(liveService.getLiveUserStatus(liveUserStatus) == null) {
			System.out.println("addLiveUserStatus start...");
			System.out.println("result : " + liveService.addLiveUserStatus(liveUserStatus));
			System.out.println(liveUserStatus);
			System.out.println("addLiveUserStatus end...");
		}else {
			System.out.println("updateLiveUserStatus start...");
			System.out.println("result : " + liveService.updateLiveUserStatus(liveUserStatus));
			System.out.println(liveUserStatus);
			System.out.println("updateLiveUserStatus end...");
		}
		
		System.out.println("/live/json/updateLiveUserStatus : POST end...");
	}
}
