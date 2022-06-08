package com.faitmain.domain.product.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.faitmain.domain.product.service.CartService;
import com.faitmain.global.common.Page;
import com.faitmain.global.common.Search;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/cart/")
@Slf4j
public class CartController {

	@Autowired
	@Qualifier("cartServiceImpl")
	private CartService cartService;
	
	public CartController() {
		log.info("Controller = {} ", CartController.class);
	}
	
	@GetMapping("getCartList")
	public String getCartList(@ModelAttribute Search search, @RequestParam("userId") String userId, Model model) throws Exception {
		
		log.info("/cart/getCartList");
		
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		
		search.setSearchKeyword(userId);
		search.setPageSize(10);
		
		Map<String, Object> map = cartService.getCartList(search);
				
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), 4, 10);
		
		log.info("resultPage : " + resultPage);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/cart/listCart.jsp";
		
	}
}
