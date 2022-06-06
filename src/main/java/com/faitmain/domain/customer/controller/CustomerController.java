package com.faitmain.domain.customer.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faitmain.domain.customer.domain.BanStatus;
import com.faitmain.domain.customer.domain.Customer;
import com.faitmain.domain.customer.service.CustomerService;

import lombok.extern.slf4j.Slf4j;
import retrofit2.http.Multipart;

@Slf4j
@Controller
@RequestMapping("/customer/")
public class CustomerController{
	
	@Autowired
	@Qualifier("customerServiceImpl")
	private CustomerService customerService;

	public CustomerController() {
		log.info("Controller = {}", this.getClass());
		
		System.out.println(this.getClass());
	}
	
	@GetMapping("getCustomerBoard")
	public Customer getCustomerBoard() throws Exception{
		
		System.out.println("/customer/getCustomerBoard : GET start...");
		log.info("Controller = {}", "/customer/addCustomerBoard : GET start");
		
		
		log.info("Controller = {}", "/customer/addCustomerBoard : GET end");
		System.out.println("/customer/getCustomerBoard : GET end...");
		
		return null;
	}
	
	@GetMapping("getCustomerBoardList")
	public String getCustomerBoadList(Model model) throws Exception{
		
		System.out.println("/customer/getCustomerBoardList : GET start...");
		
		List<Customer> list = customerService.getCustomerBoardList();
		
		model.addAttribute(list);
		
		return null;
	}
	
	@GetMapping("addCustomerBoard")
	public String addCustomerBoard()	throws Exception{
		
		System.out.println("/customer/addCustomerBoard : GET start");
		
		System.out.println("/customer/addCustomer : GET end");
		
		return null;
	}
	
	@PostMapping("addCustomerBoard")
	public String addCustomerBoard(@ModelAttribute("customer") Customer customer, @RequestParam("file") Multipart file, HttpSession session, 
															RedirectAttributes model) throws Exception{
		System.out.println("/customer/addCustomerBoard : POST start");
		
		System.out.println("/customer/addCustomerBoard : POST end");
		return null;
	}
	
	
	
	@GetMapping("updateCustomerBoard")
	public String updateCustomerBoard() throws Exception{
		
		System.out.println("/customer/updateCustomerBoard : GET start..");
		
		System.out.println("/customer/updateCustomerBoard : GET end..");
		
		return null;
	}
	
	@PostMapping("updateCustomerBoard")
	public String updateCustomerBoard(@ModelAttribute("customer") Customer customer,@RequestParam("file") Multipart file, Model model) throws Exception{
		
		System.out.println("/customer/updateCustomerBoard : POST start..");
		
		int result = customerService.updateCustomerBoard(customer);
		
		System.out.println("Controller updateCustomerBoard result : " + result);
		
		System.out.println("/customer/updateCustomerBoard : POST end..");
		
		customer = customerService.getCustomerBoard(customer.getBoardNumber());
		
		model.addAttribute("customer", customer);
		
		return null;
	}
	
	@PostMapping("deleteCustomerBoard")
	public String deleteCustomerBoard(@ModelAttribute("customer") Customer customer, Model model) throws Exception{
		
		System.out.println("/customer/deleteCustomerBoard : POST start..");
		
		System.out.println("/customer/deleteCustomerBoard : POST end..");
		
		return null;
	}
	
	@PostMapping("updateBanStatus")
	public String updateBanStatus(@ModelAttribute("customer") Customer customer,Model model) throws Exception{
		
		System.out.println("/customer/updateBanStatus : POST start..."  );
		
		int reportNumber = 0;
		BanStatus banStatus = customerService.updateBanStatus(reportNumber);
		
		System.out.println("Controller updateBanStatus result : " + banStatus);
		
		System.out.println("/customer/updateBanStatus : POST end..."  );
		
		banStatus = customerService.updateBanStatus(banStatus.getReportNumber());
		
		model.addAttribute("customer", customer);
		
		return null;
	}
	
	
	
	
	
	
	
}
