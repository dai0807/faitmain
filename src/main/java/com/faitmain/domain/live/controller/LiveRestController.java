package com.faitmain.domain.live.controller;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faitmain.domain.live.domain.Live;
import com.faitmain.domain.live.domain.LiveChat;
import com.faitmain.domain.live.domain.LiveProduct;
import com.faitmain.domain.live.domain.LiveReservation;
import com.faitmain.domain.live.domain.LiveUserStatus;
import com.faitmain.domain.live.service.LiveService;
import com.faitmain.domain.user.domain.User;

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
	
	@GetMapping("json/liveManageTab")
	public JSONArray getLiveUserList( HttpServletRequest req, HttpSession session,  Model model ) throws Exception {

		log.info("Controller = {} ", "/live/getLiveUserList : GET start...");

		log.info("getLiveUserList = {} ", this.getClass());
		
		User user = (User) session.getAttribute("user");
		
		
		

		JSONObject result = null;
		StringBuilder sb = new StringBuilder();

		TrustManager[] trustCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}
		} };

		SSLContext sc = SSLContext.getInstance("TLSv1.2");
		sc.init(null, trustCerts, new java.security.SecureRandom());

		URL url = new URL("https://vchatcloud.com/openapi/v1/users/" + liveService.getLiveByStoreId(user.getId()).getRoomId());

		System.out.println("유우우우우우우우우ㅏㄹ엘    " + url);

		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setSSLSocketFactory(sc.getSocketFactory());
		conn.setRequestMethod("GET");
		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("API_KEY", "cjnipw-Z5WmzV-1fC64X-AaOxWY-20220610111801");
		conn.setRequestProperty("X-AUTH-TOKEN", getToken());
		conn.setDoOutput(true);

		// 데이터 입력 스트림에 답기
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		while (br.ready()) {
			sb.append(br.readLine());
		}
		conn.disconnect();

		result = (JSONObject) new JSONParser().parse(sb.toString());

		// REST API 호출 상태 출력하기
		StringBuilder out = new StringBuilder();
		out.append(result.get("status") + " : " + result.get("status_message") + "\n");

		// JSON데이터에서 "data"라는 JSONObject를 가져온다.
		JSONArray data = (JSONArray) result.get("list");
		System.out.println("옴뇸뇸" + data);
		JSONObject tmp;
		for (int i = 0; i < data.size(); i++) {
			tmp = (JSONObject) data.get(i);
			System.out.println("data[" + i + "] : " + tmp);
		}
		System.out.println("data : " + data);
		
		log.info("Controller = {} ", "/live/getLiveUserList : GET end...");
		
		return data;
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
		
		for(LiveReservation obj : list) {
			map.put("title", "예약 수 : " + obj.getTitle());
			map.put("start", obj.getReservationDate());
			
			jsonObj = new JSONObject(map);
			jsonArr.add(jsonObj);
		}
		
		log.info("jsonArrCheck: {}", jsonArr);
		
		log.info("/live/json/getLiveReservationCal : GET end...");
		return jsonArr;
	}
	
	//라이브 채팅방 유저목록 조회 요청 구현중
	@PostMapping("json/getLiveUserList")
	public Map<String, Object> getLiverUserList() throws Exception {
		
		Map<String, Object> map = new HashMap<>();
		
		return map;
	}
	
	public String getToken() throws Exception {

		log.info("getToken Method start...");

		JSONObject result = null;
		StringBuilder sb = new StringBuilder();

		TrustManager[] trustCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}
		} };

		SSLContext sc = SSLContext.getInstance("TLSv1.2");
		sc.init(null, trustCerts, new java.security.SecureRandom());

		URL url = new URL("https://vchatcloud.com/openapi/token");

		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setSSLSocketFactory(sc.getSocketFactory());

		conn.setRequestMethod("GET");
		conn.setRequestProperty("API_KEY", "cjnipw-Z5WmzV-1fC64X-AaOxWY-20220610111801");

		// 데이터 입력 스트림에 담기
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		while (br.ready()) {
			sb.append(br.readLine());
		}
		conn.disconnect();

		log.info("data from v.chatServer : {}", br);

		result = (JSONObject) new JSONParser().parse(sb.toString());

		// REST API 호출 상태 출력하기
		StringBuilder out = new StringBuilder();
		out.append(result.get("status") + " : " + result.get("status_message") + "\n");

		// JSON데이터에서 "data"라는 JSONObject를 가져온다.
		JSONObject data = (JSONObject) result.get("data");
		String dataa = (String) data.get("X-AUTH-TOKEN");
		long Code = (long) result.get("result_cd");

		log.info("X-AUTH-TOKEN : {}", dataa);
		log.info("result_cd : {}", Code);
		log.info("getToken Method end...");

		return dataa;
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

	
	
	

}
