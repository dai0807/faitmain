package com.faitmain.domain.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.view.RedirectView;

import com.faitmain.domain.product.domain.Product;
import com.faitmain.domain.product.service.ProductService;
import com.faitmain.domain.user.domain.User;
import com.faitmain.global.common.MiniProjectPage;
import com.faitmain.global.common.Page;
import com.faitmain.global.common.Search;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/product/")
@Slf4j
public class ProductController {

	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	public ProductController() {
		log.info("Controller = {} ", ProductController.class);
	}
	
	@GetMapping("addProduct")
	public String addProduct() throws Exception{		
		
		log.info("/product/addProduct : GET");
//		view/common/admin/main
		return "/product/addProduct";
		
	}
	
	@PostMapping("addProduct")
	public String addProduct(@ModelAttribute Product product, MultipartHttpServletRequest mRequest) throws Exception{
		
		log.info("/product/addProduct = {}", "POST");
		
		User user = new User();
		user.setId("store01@naver.com");
		product.setStore(user);
		productService.addProduct(product, mRequest);
		
		log.info("productNumber 확인 = {} ", product.getProductGroupNumber());
				
		return "redirect:/product/getProduct?productNumber=" + product.getProductGroupNumber();
		
	}
	
	@GetMapping("getProduct")
	public String getProduct( @RequestParam("productNumber") int productNumber, Model model ) throws Exception {
		
		log.info("/product/getProduct");
		
		Product product = productService.getProduct(productNumber);
		
		log.info("product = {}", product);
		
		model.addAttribute("product", product);
		
		return "/product/getProduct";
	}
	
	@RequestMapping(value="getProductList")
	public String getProductList(@ModelAttribute Search search, @RequestParam("resultJsp") String resultJsp, Model model) throws Exception{
		
		log.info("/product/getProductList");
		
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(10);
		
		Map<String, Object> searchMap = new HashMap<String, Object>();		
		searchMap.put("searchKeyword", search.getSearchKeyword());
		
		if(resultJsp.equals("listProductStore")) {
			searchMap.put("searchStore",  "store01@naver.com");
		}
		
		searchMap.put("endRowNum",  search.getEndRowNum());
		searchMap.put("startRowNum",  search.getStartRowNum());
		searchMap.put("searchKeyword", search.getSearchKeyword());
		searchMap.put("searchStatus", search.getSearchStatus());
		searchMap.put("searchCategory", search.getSearchCategory());
		searchMap.put("searchOrderName", search.getOrderName());
		
		System.out.println("Search : " + search);
		
		Map<String, Object> map = productService.getProductList(searchMap);
				
		MiniProjectPage resultPage = new MiniProjectPage( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), 4, 10);
		
		log.info("resultPage : " + resultPage);
		
		log.info("list : " + ((List<Product>)map.get("list")).get(0));
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);	
		/**/
		return "/product/" + resultJsp;
	}
	
	@GetMapping("updateProduct")
	public String updateProduct(@RequestParam("productNumber") int productNumber, Model model) throws Exception{
		
		log.info("/product/updateProduct = {}", "GET");
		
		Product product = productService.getProduct(productNumber);
		
		model.addAttribute("product", product);
		
		return "/product/updateProduct";
	}
	
	@PostMapping("updateProduct")
	public String updateProduct(@ModelAttribute("product") Product product, MultipartHttpServletRequest mRequest) throws Exception{
		
		log.info("/product/updateProduct = {}", "POST");
		
		User user = new User();
		user.setId("store01@naver.com");
		product.setStore(user);
		productService.updateProduct(product, mRequest);
		
		return "redirect:/product/getProduct?productNumber=" + product.getProductNumber();
	}
	
	@GetMapping("deleteProduct")
	public String deleteProduct(@RequestParam("productNumber") int productNumber, @RequestParam("resultJsp") String resultJsp) throws Exception{
		
		log.info("/product/deleteProduct");
		
		productService.deleteProduct(productNumber);
		
		return "redirect:/product/" + resultJsp;
	}
	
	
}
