package com.faitmain.domain.web.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faitmain.domain.live.service.LiveService;
import com.faitmain.domain.product.service.ProductService;

@Slf4j
@Controller
@AllArgsConstructor
public class WebController{

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Autowired
	@Qualifier("liveServiceImpl")
	private LiveService liveService;
	
    @GetMapping( "/" )
    public String main( Model model ) throws Exception{
        log.info( "log = {} " , this.getClass().getName() );
        
        Map<String, Object> map = new HashMap<String, Object>();
        
        map.put("orderName", "product_name DESC");
		map.put("startRowNum", 1);
		map.put("endRowNum", 5);
		
		map = productService.getProductList(map);
        log.info("after getProductList");

		map.put("liveList", liveService.getLiveList().get("liveList"));
		log.info("after getLiveList");
		
        model.addAttribute("map", map);
        
        System.out.println(model);
        return "index";
    }
    
    @GetMapping("/myPage")
    public String getMyPage() {
    	log.info("getMyPage : GET start...");
    	
    	log.info("getMyPage : GET end...");
    	return "myPage";
    }
    

    @GetMapping( "view/error" )
    public String register(){
        log.info( "log = {} " , log );
        return "view/404";
    }

    @GetMapping( "view/loginin" )
    public String login(){
        log.info( "log = {} " , log );
        return "view/login";
    }

    @GetMapping( "view/admin/main" )
    public String admin(){
        System.out.println( "WebController.admin" );
        return "view/common/admin/main";
    }
}
