package com.faitmain.domain.live.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faitmain.domain.live.domain.Live;
import com.faitmain.domain.live.domain.LiveUserStatus;
import com.faitmain.domain.live.service.LiveService;
import com.faitmain.domain.user.domain.User;

@Controller
@RequestMapping( "/live/" )
public class LiveController {
   
   // Field
   @Autowired
   private LiveService liveService;
   
   public LiveController() {
      System.out.println(this.getClass());
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
   public String getLiveProductLivst( Model model ) throws Exception {
	   
	   System.out.println("/live/getLiveList : GET start...");
	      
	   Map<String, Object> map = liveService.getLiveList();
	      
	   model.addAttribute(map);
	      
	   System.out.println("/live/getLiveList : GET end...");
	   
	   return "forward://live/getLiveProductList.jsp";
   }
   
   @GetMapping( "addLive" )
   public String addLive() throws Exception {
      
      System.out.println("/live/addLive : GET start...");
      
      
      System.out.println("/live/addLive : GET end...");
      return "forward:/live/addLiveView.jsp";
   }
   
   @PostMapping("addLive")
   public String addLive( @ModelAttribute( "live" ) Live live,
		   											HttpSession session,
		   											RedirectAttributes model ) throws Exception {
	   
	  //방송진행 유무 검증
	  User user = (User) session.getAttribute("user");
	  
	  //방송을 한번이라도 한적이 있다면 >> UPDATE
	  if(liveService.getLiveByStoreId(user.getId()) != null ) {
		  
	      System.out.println("/live/updateLive : POST start...");
	      
	      int result = liveService.updateLive(live);
	      
	      System.out.println("Controller updateLive result : " + result);
	      
	      System.out.println("/live/updateLive : POST end...");
	      
	      //updateLive 후 방송화면으로 다시 이동
	      live = liveService.getLive(live.getLiveNumber());
	      
	      model.addAttribute("live", live);
	      
	      return "redirect:/live/transLive.jsp";
	      
	    //방송을 한번도 한적 없다면 >> INSERT
	  	}else {
      
		  System.out.println("/live/addLive : POST start...");
   
		  int result = liveService.addLive(live);
      
		  System.out.println("Controller addLive result : " + result);
      
		  System.out.println("/live/addLive : POST end...");
      
		  //addLive 후 방송 시작을 위한 getLive 
		  live = liveService.getLive(live.getLiveNumber());
      
		  model.addAttribute("live", live);
      
		  System.out.println("방송 데이터 : " + live);
      
		  return "redirect:/live/transLive.jsp";
	  	}
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
	      
	      //정보변경 후 방송화면으로 다시 이동
	      live = liveService.getLive(live.getLiveNumber());
	      
	      model.addAttribute("live", live);
	      
	      return "forward:/live/transLive.jsp";
	   
   }
   
   @GetMapping("updateLiveStatusCode")
   public String updateLiveStatusCode( @RequestParam( "liveNumber" ) int liveNumber,
		   															 Model model ) throws Exception {
	   	  //방송 종료 updateLiveStatusCode
	      System.out.println("/live/updateLiveStatusCode : GET start...");
	      
	      liveService.updateLiveStatusCode(liveNumber);
	      
	      System.out.println("/live/updateLiveStatusCode : GET end...");
	      
	      
	      //방송 종료 후 liveList로 이동
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
      

}
