package com.faitmain.domain.product.controller;

import javax.swing.filechooser.FileSystemView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.faitmain.domain.product.domain.Product;
import com.faitmain.domain.product.service.ProductService;

import retrofit2.http.Multipart;

@Controller
@RequestMapping("/product/")
public class ProductController {

	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	public ProductController() {
		System.out.println(this.getClass());
	}
	
	@GetMapping("addProduct")
	public String addProduct() throws Exception{		
		
		System.out.println("/product/addProduct : GET");
		
		return "forward:/product/addProductView.jsp";
		
	}
	
	@PostMapping("addProduct")
	public String addProduct(@ModelAttribute("product") Product product, @RequestParam("file") Multipart file ) throws Exception{
		
		System.out.println("/product/addProduct : POST");
		
		int lastProductNumber = productService.addProduct(product);
		
		return "forward:/product/listProductStore.jsp";
		
	}
	
//	private 
}
