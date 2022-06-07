//package com.faitmain.domain.customer.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.faitmain.domain.customer.domain.Customer;
//import com.faitmain.domain.customer.service.CustomerService;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@RestController
//@RequestMapping("/customer/*")
//public class CustomerRestController {
//	
//	@Autowired
//	private CustomerService customerService;
//	
//	public CustomerRestController() {
//		log.info("Controller = {} ", CustomerRestController.class);
//	}
//	
//	//Method
//	// Customer Board
//	@GetMapping("json/getCustomerBoardList")
//	public List<Customer> getCustomerBoardList() throws Exception{
//		
//		System.out.println("/customer/json/getCustomerBoardList : GET start...");
//		
//		List<Customer> list = customerService.getCustomerBoardList();
//		
//		System.out.println("/customer/json/getCustomerBoardList : GET end...");
//		return list;
//	}
//	
//	@GetMapping("json/getCustomerBoard")
//	public Customer getCustomerBoard(@RequestBody Customer customer) throws Exception{
//		
//		System.out.println("/customer/json/getCustomerBoard : GET start ...");
//		
//		int boardNumber = 0;
//		System.out.println("result = " + customerService.getCustomerBoard(boardNumber));
//		
//		System.out.println("/customer/json/getCustomerBoard : GET end ...");
//		
//		return customer;
//	}
//}
