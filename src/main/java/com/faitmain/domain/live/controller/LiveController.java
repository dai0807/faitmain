package com.faitmain.domain.live.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Controller
@RequestMapping( "/live/" )
public class LiveController {
   
   // Field
   @Autowired
   private LiveService liveService;
   
   public LiveController() {
	   log.info( "Controller = {} ", this.getClass() );
   }
   
   // Index
   @GetMapping( "/" )
   public String main() throws Exception{
       log.info( "log = {} " , this.getClass().getName() );
       return "index";
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
	   
	  //諛⑹넚吏꾪뻾 �쑀臾� 寃�利�
	  User user = (User) session.getAttribute("user");
	  
	  //諛⑹넚�쓣 �븳踰덉씠�씪�룄 �븳�쟻�씠 �엳�떎硫� >> UPDATE
	  if(liveService.getLiveByStoreId(user.getId()) != null ) {
		  
	      System.out.println("/live/updateLive : POST start...");
	      
	      int result = liveService.updateLive(live);
	      
	      System.out.println("Controller updateLive result : " + result);
	      
	      System.out.println("/live/updateLive : POST end...");
	      
	      //updateLive �썑 諛⑹넚�솕硫댁쑝濡� �떎�떆 �씠�룞
	      live = liveService.getLive(live.getLiveNumber());
	      
	      model.addAttribute("live", live);
	      
	      return "redirect:/live/transLive.jsp";
	      
	    //諛⑹넚�쓣 �븳踰덈룄 �븳�쟻 �뾾�떎硫� >> INSERT
	  	}else {
      
		  System.out.println("/live/addLive : POST start...");
   
		  int result = liveService.addLive(live);
      
		  System.out.println("Controller addLive result : " + result);
      
		  System.out.println("/live/addLive : POST end...");
      
		  //addLive �썑 諛⑹넚 �떆�옉�쓣 �쐞�븳 getLive 
		  live = liveService.getLive(live.getLiveNumber());
      
		  model.addAttribute("live", live);
      
		  System.out.println("諛⑹넚 �뜲�씠�꽣 : " + live);
      
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
      

}
