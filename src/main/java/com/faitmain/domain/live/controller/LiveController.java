package com.faitmain.domain.live.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faitmain.domain.live.domain.Live;
import com.faitmain.domain.live.service.LiveService;

@Controller
@RequestMapping("/live/")
public class LiveController {
   
   // Field
   @Autowired
   private LiveService liveService;
   
   public LiveController() {
      System.out.println(this.getClass());
   }
   
   @GetMapping("getLiveList")
   public String getLiveList(Model model) throws Exception {
      
      System.out.println("/live/getLiveList : GET start...");
      
      Map<String, Object> map = liveService.getLiveList();
      
      model.addAttribute(list);
      
      System.out.println("/live/getLiveList : GET end...");
      
      return "forward:/live/main.jsp";
   }
   
   @GetMapping("getLiveProductList")
   public String getLiveProductLivst(Model model) throws Exception {
	   
	   System.out.println("/live/getLiveList : GET start...");
	      
	   Map<String, Object> map = liveService.getLiveList();
	      
	   model.addAttribute(map);
	      
	   System.out.println("/live/getLiveList : GET end...");
	   
	   return "forward://live/getLiveProductList.jsp";
   }
   
   @GetMapping("addLive")
   public String addLive() throws Exception {
      
      System.out.println("/live/addLive : GET start...");
      
      
      System.out.println("/live/addLive : GET end...");
      return "forward:/live/addLiveView.jsp";
   }
   
   @PostMapping("addLive")
   public String addLive(@ModelAttribute("live") Live live) throws Exception {
      
      System.out.println("/live/addLive : POST start...");
      
      int result = liveService.addLive(live);
      System.out.println("Controller addLive result : " + result);
      
      System.out.println("/live/addLive : POST end...");
      return "redirect:/live/addLive.jsp";
   }
   
   @GetMapping("watchLive")
   public String watchLive(  ) throws Exception {
	   
   }

   

}
