package com.faitmain.domain.live.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faitmain.domain.product.service.ProductService;
import com.faitmain.domain.live.domain.Live;
import com.faitmain.domain.live.domain.LiveUserStatus;
import com.faitmain.domain.live.service.LiveService;
import com.faitmain.domain.user.domain.User;

@Slf4j
@Controller
@RequestMapping( "/live/" )
public class LiveController {
   
   // Field
   @Autowired
   private LiveService liveService;
   
   @Autowired
   @Qualifier("productServiceImpl")
   private ProductService productService;
   
   public LiveController() {
	   log.info( "Controller = {} ", this.getClass() );
   }
   
   @GetMapping( "liveRoom" )
   public String getLiveRoomList( Model model ) throws Exception {
      
      System.out.println("/live/getLiveRoomList : GET start...");
      log.info( "Controller = {} ", "/live/liveRoomList : GET start..." );
      
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
	         conn.setRequestProperty("X-AUTH-TOKEN", "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJ2Y3h6dmN4ejE1OUBnbWFpbC5jb20iLCJleHAiOjE2NTQ2NzAxMDksImlhdCI6MTY1NDY1MjEwOSwiYXV0aG9yaXRpZXMiOiJbUk9MRV9VU0VSXSJ9.YHpIHG-4HyD-Iib4SRGjwxGkxsyaS5p69E5vLT46Elg");
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
	         System.out.println("data : " + data);
	         model.addAttribute("json", data);
      
	         	log.info( "Controller = {} ", "/live/liveRoomList : GET end..." );
      
      			return "view/live/liveList";
   }
   
   @GetMapping( "live" )
   public String getLive( ) throws Exception {
      
      System.out.println("/live/getLive : GET start...");
      log.info( "Controller = {} ", "/live/getLive : GET start..." );
     
      
      log.info( "Controller = {} ", "/live/getLive : GET end..." );
      
      return "view/live/live";
   }
   
   @GetMapping( "addLiveView" )
   public String addLiveView( HttpSession session,
		   					  Model model ) throws Exception {
      
      System.out.println("/live/addLiveView : GET start...");
      log.info( "Controller = {} ", "/live/addLiveView : GET start..." );
      
	   User user = (User)session.getAttribute("user");
	   
	   Map<String, Object> map = productService.getProductListByStoreId(user.getId());
	      
	   model.addAttribute("map", map);
	   
	   System.out.println(map);
     
      
      log.info( "Controller = {} ", "/live/addLiveView : GET end..." );
      
      return "view/live/addLiveView";
   }
   
   @GetMapping( "addLive" )
   public String addLive( ) throws Exception {
      
      System.out.println("/live/addLive : GET start...");
      log.info( "Controller = {} ", "/live/addLive : GET start..." );
      
     
      
      log.info( "Controller = {} ", "/live/addLiveView : GET end..." );
      
      return "view/live/addLive";
   }
   
   @GetMapping( "getLiveList" )
   public String getLiveList( Model model ) throws Exception {
      
      System.out.println("/live/getLiveList : GET start...");
      
      Map<String, Object> map = liveService.getLiveList();
      
      model.addAttribute(map);
      
      System.out.println("/live/getLiveList : GET end...");
      
      return "forward:/live/main.jsp";
   }
   
   @GetMapping( "getLiveProductList" )
   public String getLiveProductLivst( HttpSession session,
		   							  Model model ) throws Exception {
	   
	   System.out.println("/live/getLiveList : GET start...");
	      
	   User user = (User)session.getAttribute("user");
	   
//	   Map<String, Object> map = productService.getProductListByStoreId(user.getId());
//	      
//	   model.addAttribute(map);
	      
	   System.out.println("/live/getLiveList : GET end...");
	   
	   return "forward://live/getLiveProductList.jsp";
   }
   
   
   @GetMapping("watchLive")
   public String watchLive( @RequestParam( "liveNumber" ) int liveNumber,
		   												  Model model) throws Exception {
	   
	      System.out.println("/live/watchLive : GET start...");
	      
	      Live live = liveService.getLive(liveNumber);
	     
	      System.out.println("Controller watchLive liveInfo : " + live);
	     
	      model.addAttribute("live", live);
	      
	      System.out.println("/live/watchLive : GET end...");
	      
	      return "forward:/live/watchLive.jsp";
	   
   }
   
   @GetMapping("updateLive")
   public String updateLive( ) throws Exception {
	   
	      System.out.println("/live/updateLive : Get start...");
	     
	      System.out.println("/live/updateLive : Get end...");
	      
	      return "forward:/live/updateLiveView.jsp";
	   
   }
   
   @PostMapping("updateLive")
   public String updateLive( @ModelAttribute( "live" ) Live live,
		   											   Model model ) throws Exception {
	   
	      System.out.println("/live/updateLive : POST start...");
	      
	      int result = liveService.updateLive(live);
	      
	      System.out.println("Controller updateLive result : " + result);
	      
	      System.out.println("/live/updateLive : POST end...");
	      
	      //�젙蹂대�寃� �썑 諛⑹넚�솕硫댁쑝濡� �떎�떆 �씠�룞
	      live = liveService.getLive(live.getLiveNumber());
	      
	      model.addAttribute("live", live);
	      
	      return "forward:/live/transLive.jsp";
	   
   }
   
   @GetMapping("updateLiveStatusCode")
   public String updateLiveStatusCode( @RequestParam( "liveNumber" ) int liveNumber,
		   															 Model model ) throws Exception {
	   	  //諛⑹넚 醫낅즺 updateLiveStatusCode
	      System.out.println("/live/updateLiveStatusCode : GET start...");
	      
	      liveService.updateLiveStatusCode(liveNumber);
	      
	      System.out.println("/live/updateLiveStatusCode : GET end...");
	      
	      
	      //諛⑹넚 醫낅즺 �썑 liveList濡� �씠�룞
	      System.out.println("/live/getLiveList : GET start...");
	      
	      Map<String, Object> map = liveService.getLiveList();
	      
	      model.addAttribute(map);
	      
	      System.out.println("/live/getLiveList : GET end...");
	      
	      return "forward:/live/updateLive.jsp";
	   
   }
   
   @GetMapping("addLiveUserStatus")
   public void addLiveUserStatus( @ModelAttribute( "liveUsrStatus") LiveUserStatus liveUserStatus ) throws Exception {
	      
	      System.out.println("/live/addSanctionUser : GET start...");
	      
	      liveService.addLiveUserStatus(liveUserStatus);
	      
	      System.out.println("/live/addSanctionUser : GET end...");
	   	
   }
   
   @GetMapping("getAlarmList")
   public String getAlarmList( @RequestParam ("liveNumber") int liveNumber,
		   													Model model ) throws Exception {
	   
	   System.out.println("/live/getAlarmList : GET start...");
	 
	   Map<String, Object> map = liveService.getLiveUserStatusList(liveNumber);
	   
	   model.addAttribute(map);   
	   
	   System.out.println("/live/getAlarmList : GET end...");
	   
	   return "forward:/live/getAlarmList.jsp";
   }
   
   @GetMapping("getPremeiumLiveCal")
   public String getPremieumLiveCal() {
	   log.info("getPremieumLiveCal() : GET start... ");
	   
	   
	   log.info("getPremieumLiveCal() : GET end... ");
	   return "/view/live/premieumLiveCal";
   }
   
   @GetMapping("getPremeiumLiveList")
   public String getPremieumLiveList() {
	   log.info("getPremieumLiveList() : GET start... ");
	   
	   
	   log.info("getPremieumLiveList() : GET end... ");
	   return "/view/live/premieumLiveList";
   }

}
