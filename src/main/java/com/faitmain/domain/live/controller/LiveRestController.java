package com.faitmain.domain.live.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import lombok.extern.slf4j.Slf4j;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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

@Slf4j
@RestController
@RequestMapping("/openapi/*")
public class LiveRestController {

	// Field
	@Autowired
	private LiveService liveService;

	@GetMapping("token")
	public void getAccessToken() throws Exception {
		log.info( "AccessToken = {} ", this.getClass() );
		
		 TrustManager[] trustCerts = new TrustManager[]{
	                new X509TrustManager() {
	                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	                        return null;
	                    }
	                    public void checkClientTrusted(
	                        java.security.cert.X509Certificate[] certs, String authType) {
	                    }
	                    public void checkServerTrusted(
	                        java.security.cert.X509Certificate[] certs, String authType) {
	                    }
	                }
	            };
	 
	         SSLContext sc = SSLContext.getInstance("TLSv1.2");
	         sc.init(null, trustCerts, new java.security.SecureRandom());
	         
	         URL url = new URL("https://vchatcloud.com/openapi/token");
	 
	         HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
	         conn.setSSLSocketFactory(sc.getSocketFactory());
	         
	         conn.setRequestMethod("GET");
	         
	         conn.setRequestProperty("Content-type", "application/json");
	         conn.setRequestProperty("accept", "*/*");
	         conn.setRequestProperty("api_key", "kmLueZ-chdq38-O7LGgP-Ggd14x-20220604144349");
	         
	         int responseCode = conn.getResponseCode();
	         System.out.println("Sending 'GET' request to URL : " + url);
	         System.out.println("Response Code : " + responseCode);
	         
	         Charset charset = Charset.forName("UTF-8");
	         BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),charset));
	         String inputLine;
	         StringBuffer response = new StringBuffer();
	         while((inputLine = in.readLine()) != null) {
	        	 response.append(inputLine);
	         }
	         in.close();
	         
	         System.out.println("response : " + response.toString());
		
//		String url = "https://vchatcloud.com/openapi/token";
//		
//		CloseableHttpClient httpClient = HttpClients.createDefault();
//		
//		HttpGet httpGet = new HttpGet(url);
//		
//		httpGet.addHeader("Content-type", "application/json");
//		httpGet.addHeader("accept", "*");
//		httpGet.addHeader("api_key", "kmLueZ-chdq38-O7LGgP-Ggd14x-20220604144349");
//		
//		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
//		
//		System.out.println("Get Response Status");
//		System.out.println(httpResponse.getStatusLine().getStatusCode());
//		String json = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
//		
//		System.out.println(json);
//		
//		httpClient.close();
		 
	}

	// METHOD
	// LIVE
	@GetMapping("json/getLiveList")
	public Map<String, Object> getLiveList() throws Exception {
		System.out.println("/live/json/getLiveList : GET start...");

		Map<String, Object> map = liveService.getLiveList();

		System.out.println("/live/json/getLiveList : GET end...");
		return map;
	}

	@GetMapping("json/getLiveProduct")
	public Map<String, Object> getLiveProduct(@RequestBody LiveProduct liveProduct) throws Exception {
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
	public Map<String, Integer> getLiveReservationCal() throws Exception {
		System.out.println("/live/json/getLiveReservationCal : GET start...");

		System.out.println("/live/json/getLiveReservationCal : GET end...");
		return null;
	}

	@PostMapping("json/deleteLiveReservation")
	public Map<String, Object> deleteLiveReservation(@RequestBody LiveReservation liveReservation) throws Exception {
		System.out.println("/live/json/deleteLiveReservation : POST start...");

		System.out.println("deleteLiveReservation result : "
				+ liveService.deleteLiveReservation(liveReservation.getLiveReservationNumber()));

		Map<String, Object> map = liveService.getLiveReservationList(liveReservation.getReservationDate());

		System.out.println("/live/json/deleteLiveReservation : POST end...");

		return map;
	}

}
