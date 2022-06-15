package com.faitmain.domain.cart.controller;

import java.util.Collections;
import java.util.Map;

import com.faitmain.domain.cart.domain.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import com.faitmain.domain.cart.service.CartService;
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





	@GetMapping("json/deleteCart/{cartNumber}")
	public Map<String, Boolean> delete(@PathVariable int cartNumber) throws Exception{
		
		log.info("/cart/json/deleteCart");
		
		cartService.deleteCart(cartNumber);
		
		return Collections.singletonMap("success", true);
	}
}
