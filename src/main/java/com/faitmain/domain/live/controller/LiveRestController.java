package com.faitmain.domain.live.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faitmain.domain.live.domain.Live;
import com.faitmain.domain.live.domain.LiveProduct;
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
	public Live updateLive(@RequestBody Live live) {
		System.out.println("/live/json/updateLive : POST start...");
		
		Live getLive = liveService.getLive(live.getLiveNumber());
		
		System.out.println("result = " + liveService.updateLive(live));
		
		System.out.println("/live/json/updateLive : POST end...");
		
		return ;
	}
}
