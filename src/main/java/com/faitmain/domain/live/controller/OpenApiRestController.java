package com.faitmain.domain.live.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faitmain.domain.live.service.LiveService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/openapi/*")
public class OpenApiRestController {

	// Field
		@Autowired
		private LiveService liveService;

		@GetMapping("token")
		public void getAccessToken() throws Exception {
			log.info( "AccessToken = {} ", this.getClass() );
			
			JSONObject result = null;
			StringBuilder sb = new StringBuilder();
			
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
		         conn.setDoOutput(true);
		         
		         // 데이터 입력 스트림에 답기
		         BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		         while(br.ready()) {
		        	 sb.append(br.readLine());
		         }
		         conn.disconnect();
		         
		         result = (JSONObject) new JSONParser().parse(sb.toString());
		         
		         // REST API 호출 상태 출력하기
		         StringBuilder out = new StringBuilder();
		         out.append(result.get("status") + " : " + result.get("status_message")+"\n");
		         
		         // JSON데이터에서 "data"라는 JSONObject를 가져온다.
		         JSONObject data = (JSONObject)result.get("data");
		         String user_email = (String)data.get("user_email");
		         String token = (String)data.get("X-AUTH-TOKEN");
		         
		         System.out.println("data : " + data);
		         System.out.println("email : " + user_email);
		         System.out.println("token : " + token);
		        
		}
		
		@GetMapping("rooms")
		public void getRoomsList() throws Exception {
			log.info( "getRoomsList = {} ", this.getClass() );
			
			JSONObject result = null;
			StringBuilder sb = new StringBuilder();
			
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
		         
		         URL url = new URL("https://vchatcloud.com/openapi/v1/rooms");
		 
		         HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
		         conn.setSSLSocketFactory(sc.getSocketFactory());
		         
		         conn.setRequestMethod("GET");
		         
		         conn.setRequestProperty("Content-type", "application/json");
		         conn.setRequestProperty("accept", "*/*");
		         conn.setRequestProperty("api_key", "kmLueZ-chdq38-O7LGgP-Ggd14x-20220604144349");
		         conn.setRequestProperty("X-AUTH-TOKEN", "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJ2Y3h6dmN4ejE1OUBnbWFpbC5jb20iLCJleHAiOjE2NTQ1NjM1MjksImlhdCI6MTY1NDU0NTUyOSwiYXV0aG9yaXRpZXMiOiJbUk9MRV9VU0VSXSJ9.tWUKt57VES-oMlAV1NX7R5XT9e2IWdj_yJ5b5lkWjOg");
		         conn.setDoOutput(true);
		         
		         // 데이터 입력 스트림에 답기
		         BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		         while(br.ready()) {
		        	 sb.append(br.readLine());
		         }
		         conn.disconnect();
		         
		         result = (JSONObject) new JSONParser().parse(sb.toString());
		         
		         // REST API 호출 상태 출력하기
		         StringBuilder out = new StringBuilder();
		         out.append(result.get("status") + " : " + result.get("status_message")+"\n");
		         
		         // JSON데이터에서 "data"라는 JSONObject를 가져온다.
		         JSONArray data = (JSONArray)result.get("list");
		         JSONObject tmp;
		         
		         for(int i=0; i<data.size(); i++) {
		        	 tmp = (JSONObject)data.get(i);
		        	 System.out.println("data["+i+"] : "+tmp);
		         }
		         
		      
		}
		
		@GetMapping("create")
		public void createRoom() throws Exception {
			log.info( "createRoom = {} ", this.getClass() );
			
			JSONObject result = null;
			StringBuilder sb = new StringBuilder();
			
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
		         
		         URL url = new URL("https://vchatcloud.com/openapi/v1/rooms");
		 
		         HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
		         conn.setSSLSocketFactory(sc.getSocketFactory());
		         
		         conn.setRequestMethod("POST");
		         
		         conn.setRequestProperty("Content-type", "application/json");
		         conn.setRequestProperty("accept", "*/*");
		         conn.setRequestProperty("api_key", "kmLueZ-chdq38-O7LGgP-Ggd14x-20220604144349");
		         conn.setRequestProperty("X-AUTH-TOKEN", "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJ2Y3h6dmN4ejE1OUBnbWFpbC5jb20iLCJleHAiOjE2NTQ1NjM1MjksImlhdCI6MTY1NDU0NTUyOSwiYXV0aG9yaXRpZXMiOiJbUk9MRV9VU0VSXSJ9.tWUKt57VES-oMlAV1NX7R5XT9e2IWdj_yJ5b5lkWjOg");
		         conn.setDoOutput(true);
		         
		         
		         // 데이터 입력 스트림에 답기
		         BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		         while(br.ready()) {
		        	 sb.append(br.readLine());
		         }
		         conn.disconnect();
		         
		         result = (JSONObject) new JSONParser().parse(sb.toString());
		         
		         // REST API 호출 상태 출력하기
		         StringBuilder out = new StringBuilder();
		         out.append(result.get("status") + " : " + result.get("status_message")+"\n");
		         
		         // JSON데이터에서 "data"라는 JSONObject를 가져온다.
		         JSONArray data = (JSONArray)result.get("list");
		         JSONObject tmp;
		         
		         for(int i=0; i<data.size(); i++) {
		        	 tmp = (JSONObject)data.get(i);
		        	 System.out.println("data["+i+"] : "+tmp);
		         }
		}
		
}
