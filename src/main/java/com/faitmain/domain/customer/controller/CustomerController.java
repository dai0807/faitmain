package com.faitmain.domain.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



import com.faitmain.domain.customer.domain.Customer;
import com.faitmain.domain.customer.service.CustomerService;

@Controller
@RequestMapping("/customer/")
public class CustomerController{
	
	@Autowired
	@Qualifier("customerServiceImpl")
	private CustomerService customerService;
	
	@GetMapping("noticeIndex")
	public String openBoardNoticeIndex(){
		return "view/customer/noticeIndex";
	}

	
	@GetMapping("addNotice")
	public String openBoardWrite(@RequestParam(value = "boardNumber", required = false) Integer boardNumber, Model model) throws Exception {
		
		if(boardNumber == null) {
			model.addAttribute("customer", new Customer());
		}else {
			Customer customer = customerService.getCustomerBoard(boardNumber);
			if(customer == null) {
				return "redirect: /customer/listNotice";
			}
			model.addAttribute("customer", customer);
		}
		
		return "view/customer/addNotice";
	}
	
	@PostMapping("registerNotice")
	public String registerBoard(final Customer customer) throws Exception {
		System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
		System.out.println(customer);

			boolean register = customerService.registerCustomerBoard(customer);
			//System.out.println(customer);
			
			if(register == false) {
				//게시글 "등록실패"
				System.out.println("등록 실패");
		}
		
		return "redirect:/customer/listNotice";
	}
	
	@GetMapping("listNotice")
	public String openBoardList(Model model) throws Exception {

		model.addAttribute("boardList", customerService.getCustomerBoardList());
		
		System.out.println("테스트" + model);
		
		return "view/customer/listNotice";
		
	}
	
	@GetMapping("detailNotice")
	public String openBoardDetail(@RequestParam(value="boardNumber", required = false) Integer boardNumber, Model model) throws Exception {
		
		
		if(boardNumber == null) {
			return "redirect:/customer/listNotice";
		}
		
		Customer customer = customerService.getCustomerBoard(boardNumber);
		
		if(customer == null || "Y".equals(customer.getDeleteYn())) {
			
			return "redirect:/customer/listNotice";
		}
		
		System.out.println(customer);
		model.addAttribute("customer", customer);
		
		return "view/customer/detailNotice";
	}
	
	@PostMapping("deleteNotice")
	public String deleteBoard(@RequestParam(value = "boardNumber", required = false) Integer boardNumber) throws Exception  {
		if(boardNumber == null) {
			return "redirect: /customer/listNotice";
		}
		
		boolean delete = customerService.deleteCustomerBoard(boardNumber);
		if(delete == false){
			
		}
		
		return "redirect:/customer/listNotice";
	}
	
//	@GetMapping("getCustomerBoard")
//	public Customer getCustomerBoard() throws Exception{
//		
//		System.out.println("/customer/getCustomerBoard : GET start...");
//		log.info("Controller = {}", "/customer/addCustomerBoard : GET start");
//		
//		
//		log.info("Controller = {}", "/customer/addCustomerBoard : GET end");
//		System.out.println("/customer/getCustomerBoard : GET end...");
//		
//		return null;
//	}
	
//	@GetMapping("getCustomerBoardList")
//	public String getCustomerBoadList(Model model) throws Exception{
//		
//		System.out.println("/customer/getCustomerBoardList : GET start...");
//		
//		List<Customer> list = customerService.getCustomerBoardList();
//		
//		model.addAttribute(list);
//		
//		return null;
//	}
	
//	@GetMapping("addCustomerBoard")
//	public String addCustomerBoard()	throws Exception{
//		
//		System.out.println("/customer/addCustomerBoard : GET start");
//		
//		System.out.println("/customer/addCustomer : GET end");
//		
//		return null;
//	}
	
//	@PostMapping("addCustomerBoard")
//	public String addCustomerBoard(@ModelAttribute("customer") Customer customer, @RequestParam("file") Multipart file, HttpSession session, 
//															RedirectAttributes model) throws Exception{
//		System.out.println("/customer/addCustomerBoard : POST start");
//		
//		System.out.println("/customer/addCustomerBoard : POST end");
//		return null;
//	}
//	
	
	
}
	
//	@PostMapping("updateCustomerBoard")
//	public String updateCustomerBoard(@ModelAttribute("customer") Customer customer,@RequestParam("file") Multipart file, Model model) throws Exception{
//		
//		System.out.println("/customer/updateCustomerBoard : POST start..");
//		
//		int result = customerService.updateCustomerBoard(customer);
//		
//		System.out.println("Controller updateCustomerBoard result : " + result);
//		
//		System.out.println("/customer/updateCustomerBoard : POST end..");
//		
//		customer = customerService.getCustomerBoard(customer.getBoardNumber());
//		
//		model.addAttribute("customer", customer);
//		
//		return null;
//	}
	
//	@PostMapping("deleteCustomerBoard")
//	public String deleteCustomerBoard(@ModelAttribute("customer") Customer customer, Model model) throws Exception{
//		
//		System.out.println("/customer/deleteCustomerBoard : POST start..");
//		
//		System.out.println("/customer/deleteCustomerBoard : POST end..");
//		
//		return null;
//	}
	
//	@PostMapping("updateBanStatus")
//	public String updateBanStatus(@ModelAttribute("customer") Customer customer,Model model) throws Exception{
//		
//		System.out.println("/customer/updateBanStatus : POST start..."  );
//		
//		int reportNumber = 0;
//		BanStatus banStatus = customerService.updateBanStatus(reportNumber);
//		
//		System.out.println("Controller updateBanStatus result : " + banStatus);
//		
//		System.out.println("/customer/updateBanStatus : POST end..."  );
//		
//		banStatus = customerService.updateBanStatus(banStatus.getReportNumber());
//		
//		model.addAttribute("customer", customer);
//		
//		return null;
//	}
