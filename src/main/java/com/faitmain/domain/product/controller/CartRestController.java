package com.faitmain.domain.product.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faitmain.domain.product.domain.Cart;
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
	
	@PostMapping("json/addCart")
	public Map<String, Boolean> addCart(@RequestBody Cart cart) throws Exception{
		
		log.info("/cart/json/addCart");
		
		boolean result = false;
		
		Cart prevCart = cartService.getCart(cart);
		
		if(prevCart == null) {
			cartService.addCart(cart);
			result = true;
		}		
		
		return Collections.singletonMap("success", result);
	}
	
	@PostMapping("json/updateCart")
	public Map<String, Boolean> updateCart(@RequestBody Cart cart) throws Exception{
		
		log.info("/cart/json/updateCart");
		
		boolean result = false;
		
		int quantity = productService.getProductQuantity(cart.getCartProduct().getProductNumber());
		
		if(cart.getCartQuantity() <= quantity) {
			cartService.updateCart(cart);
			result = true;
		}
		
		return Collections.singletonMap("success", result);		
	}
	
	@GetMapping("json/deleteCart/{cartNumber}")
	public Map<String, Boolean> delete(@PathVariable int cartNumber) throws Exception{
		
		log.info("/cart/json/deleteCart");
		
		cartService.deleteCart(cartNumber);
		
		return Collections.singletonMap("success", true);
	}
}
