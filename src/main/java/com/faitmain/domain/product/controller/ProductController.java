package com.faitmain.domain.product.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.faitmain.domain.product.domain.Product;
import com.faitmain.domain.product.service.ProductService;
import com.faitmain.domain.user.domain.User;
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
		return "view/product/addProduct";
		
	}
	
	@PostMapping("addProduct")
	public String addProduct(@ModelAttribute Product product, MultipartHttpServletRequest mRequest) throws Exception{
		
		log.info("/product/addProduct : POST");
		
		User user = new User();
		user.setId("store01@naver.com");
		product.setStore(user);
		productService.addProduct(product, mRequest);
				
		return "index";
		
	}
	
	@GetMapping("getProduct")
	public String getProduct( @RequestParam("productNumber") int productNumber, Model model ) throws Exception {
		
		log.info("/product/getProduct");
		
		Map<String, Object> map = productService.getProduct(productNumber);
		
		model.addAttribute("mainProduct", map.get("mainProduct"));
		model.addAttribute("productOptions", map.get("productOptions"));
		
		return "forward:/product/getProduct.jsp";
	}
	
	@GetMapping("getProductList")
	public String getProductList(@ModelAttribute Search search, @RequestParam("resultJsp") String resultJsp, 
								 @RequestParam(value = "searchStatus", required = false) String searchStatus,
								 @RequestParam(value = "searchCategory", required = false) String searchCategory,
								 @RequestParam(value = "beforeDate", required = false) String beforeDate, 
								 @RequestParam(value = "afterDate", required = false) String afterDate, Model model) throws Exception{
		
		log.info("/product/getProductList");
		
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(10);
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchCondition", search.getSearchCondition());
		searchMap.put("searchKeyword", search.getSearchKeyword());
		searchMap.put("endRowNum",  search.getEndRowNum());
		searchMap.put("startRowNum",  search.getStartRowNum());
		searchMap.put("searchStatus", searchStatus);
		searchMap.put("searchCategory", searchCategory);
		searchMap.put("beforeDate", beforeDate);
		searchMap.put("afterDate", afterDate);
		
		Map<String, Object> map = productService.getProductList(searchMap);
				
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), 4, 10);
		
		log.info("resultPage : " + resultPage);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		model.addAttribute("searchStatus", searchStatus);
		model.addAttribute("searchCategory", searchCategory);
		model.addAttribute("beforeDate", beforeDate);
		model.addAttribute("afterDate", afterDate);		
		
		return "forward:/product/listProduct.jsp";
	}
	
	@GetMapping("updateProduct")
	public String updateProduct(@RequestParam("productNumber") int productNumber, Model model) throws Exception{
		
		log.info("/product/updateProduct : GET");
		
		Map<String, Object> map = productService.getProduct(productNumber);
		
		model.addAttribute("mainProduct", map.get("mainProduct"));
		model.addAttribute("productOptions", map.get("productOptions"));
		
		return "forward:/product/updateProduct.jsp";
	}
	
	@PostMapping("updateProduct")
	public String updateProduct(@ModelAttribute("product") Product product) throws Exception{
		
		log.info("/product/updateProduct : POST");
		
		productService.updateProduct(product);
		
		return "redirect:/product/getProduct?productNumber=" + product.getProductNumber();
	}
	
	@GetMapping("deleteProduct")
	public String deleteProduct(@RequestParam("productNumber") int productNumber, @RequestParam("resultJsp") String resultJsp) throws Exception{
		
		log.info("/product/deleteProduct");
		
		productService.deleteProduct(productNumber);
		
		return "redirect:/inquiry/listInquiry?resultJsp=" + resultJsp;
	}
	
	
}
