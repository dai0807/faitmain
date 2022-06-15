package com.faitmain.domain.live.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faitmain.domain.live.domain.Live;
import com.faitmain.domain.live.domain.LiveChat;
import com.faitmain.domain.live.domain.LiveReservation;
import com.faitmain.domain.live.domain.LiveUserStatus;
import com.faitmain.domain.live.service.LiveService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/live/*")
public class LiveRestController {

	// Field
	@Autowired
	@Qualifier("liveServiceImpl")
	private LiveService liveService;

	// METHOD
	// LIVE
	@GetMapping("json/getLiveList")
	public Map<String, Object> getLiveList() throws Exception {
		System.out.println("/live/json/getLiveList : GET start...");

		Map<String, Object> map = liveService.getLiveList();

		System.out.println("/live/json/getLiveList : GET end...");
		return map;
	}

//	@GetMapping("json/getLiveProduct")
//	public Map<String, Object> getLiveProduct(@RequestBody LiveProduct liveProduct) throws Exception {
//		System.out.println("/live/json/getLiveProduct : GET start...");
//
//		Map<String, Object> map = liveService.getLiveProductList(liveProduct);
//
//		System.out.println("/live/json/getLiveProduct : GET end...");
//
//		return map;
//	}

	@PostMapping("json/updateLive")
	public Live updateLive(@RequestBody Live live) throws Exception {
		System.out.println("/live/json/updateLive : POST start...");

		System.out.println("result = " + liveService.updateLive(live));

		System.out.println("/live/json/updateLive : POST end...");

		return live;
	}

	@PostMapping("json/sendMessage")
	public void sendMessage(@RequestBody LiveChat liveChat) throws Exception {
		System.out.println("/live/json/sendMessage : POST start...");

		System.out.println("result : " + liveService.addLiveChat(liveChat));

		System.out.println("/live/json/sendMessage : POST end...");
	}

	@PostMapping("json/updateLiveUserStatus")
	public void addLiveUserStatus(@RequestBody LiveUserStatus liveUserStatus) throws Exception {
		System.out.println("/live/json/updateLiveUserStatus : POST start...");

		if (liveService.getLiveUserStatus(liveUserStatus) == null) {
			System.out.println("addLiveUserStatus start...");
			System.out.println("result : " + liveService.addLiveUserStatus(liveUserStatus));
			System.out.println(liveUserStatus);
			System.out.println("addLiveUserStatus end...");
		} else {
			System.out.println("updateLiveUserStatus start...");
			System.out.println("result : " + liveService.updateLiveUserStatus(liveUserStatus));
			System.out.println(liveUserStatus);
			System.out.println("updateLiveUserStatus end...");
		}

		System.out.println("/live/json/updateLiveUserStatus : POST end...");
	}

	// LIVE RESERVATION
	@GetMapping("json/getLiveReservationCal")
	public List<Map<String, Object>> getLiveReservationCal() throws Exception {
		log.info("/live/json/getLiveReservationCal : GET start...");

		List<LiveReservation> list = liveService.getLiveReservationCal();

		log.info("{}", list);

		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArr = new JSONArray();

		Map<String, Object> map = new HashMap<>();

		for (LiveReservation obj : list) {
			map.put("title", "예약 수 : " + obj.getTitle());
			map.put("start", obj.getReservationDate());

			jsonObj = new JSONObject(map);
			jsonArr.add(jsonObj);
		}

		log.info("jsonArrCheck: {}", jsonArr);

		log.info("/live/json/getLiveReservationCal : GET end...");
		return jsonArr;
	}

//	@PostMapping("json/deleteLiveReservation")
//	public Map<String, Object> deleteLiveReservation(@RequestBody LiveReservation liveReservation) throws Exception {
//		System.out.println("/live/json/deleteLiveReservation : POST start...");
//
//		System.out.println("deleteLiveReservation result : "
//				+ liveService.deleteLiveReservation(liveReservation.getLiveReservationNumber()));
//
//		Map<String, Object> map = liveService.getLiveReservationList(liveReservation.getReservationDate());
//
//		System.out.println("/live/json/deleteLiveReservation : POST end...");
//
//		return map;
//	}

	@GetMapping("json/addLiveReservation")
	public List<Integer> addLiveReservation(String date) throws Exception {
		log.info("addLiveReservation GET : start...");

		List<LiveReservation> list = liveService.getLiveReservationList(date);

		List<Integer> timeList = new ArrayList<>();
		boolean flag = true;

		for (int i = 0; i < 24; i++) {
			flag = true;
			for (LiveReservation obj : list) {
				if (i == obj.getReservationTime()) {
					flag = false;
				}
			}
			if (flag) {
				timeList.add(i);
			}
		}

		log.info("timeList = {}", timeList);

		log.info("addLiveReservation GET : end...");
		return timeList;
	}

}
