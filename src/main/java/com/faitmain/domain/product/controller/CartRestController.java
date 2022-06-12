package com.faitmain.domain.product.controller;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faitmain.domain.product.domain.Cart;
import com.faitmain.domain.product.domain.Product;
import com.faitmain.domain.product.service.CartService;
import com.faitmain.domain.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cart/*")
@Slf4j
public class CartRestController {
	
	@Autowired
	@Qualifier("cartServiceImpl")
	private CartService cartService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
		
	public CartRestController() {
		log.info("Controller = {} ", CartRestController.class);
	}
	


	

	
	@GetMapping("json/deleteCart/{cartNumber}")
	public Map<String, Boolean> delete(@PathVariable int cartNumber) throws Exception{
		
		log.info("/cart/json/deleteCart");
		
		cartService.deleteCart(cartNumber);
		
		return Collections.singletonMap("success", true);
	}
}
