package com.faitmain.domain.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.faitmain.domain.customer.constant.Method;
import com.faitmain.domain.customer.domain.Customer;
import com.faitmain.domain.customer.service.CustomerService;
import com.faitmain.global.util.UiUtils;

@Controller
@RequestMapping("/customer/")
public class CustomerController extends UiUtils{
	
	@Autowired
	@Qualifier("customerServiceImpl")
	private CustomerService customerService;
	
	@GetMapping("noticeIndex")
	public String openBoardNoticeIndex(){
		return "/customer/noticeIndex";
	}

	
	@GetMapping("addNotice")
	public String openBoardWrite(@RequestParam(value = "boardNumber", required = false) Integer boardNumber, Model model) throws Exception {
		
		if(boardNumber == null) {
			model.addAttribute("customer", new Customer());
			
		}else {
			Customer customer = customerService.getCustomerBoard(boardNumber);
			if(customer == null) {
				return "redirect:/customer/list";
			}
			model.addAttribute("customer", customer);
		}
		
		return "/admin/addNotice";
	}
	
	@PostMapping("registerNotice")
	public String registerBoard(final Customer params, Model model){
		System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
		System.out.println(params);
			try {
				boolean isRegistered = customerService.registerCustomerBoard(params);
				if(isRegistered == false) {
					return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/customer/list", Method.GET, null, model);
				}
			}catch(DataAccessException e) {
				return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/customer/list", Method.GET, null, model);
			
			}catch(Exception e) {
				return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/customer/list", Method.GET, null, model);
			}
			
			return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/customer/list", Method.GET, null, model);
			/*if(register == false) {
				//게시글 "등록실패"
				System.out.println("등록 실패");*/
		
			//boolean register = customerService.registerCustomerBoard(params);
			//System.out.println(customer);
		
		//return "redirect:/list";
	}
	
	


	@GetMapping("list")
	public String openBoardList(Model model) throws Exception {
		
		List<Customer> boardList = customerService.getCustomerBoardList();
		model.addAttribute("boardList", boardList);
//		model.addAttribute("boardList", customerService.getCustomerBoardList());
				
		return "/customer/list";
		
	}
	
	@GetMapping("detail")
	public String openBoardDetail(@RequestParam(value="boardNumber", required = false) Integer boardNumber, Model model) throws Exception {
		
		
		if(boardNumber == null) {
			return "redirect: /customer/list";
		}
		
		Customer customer = customerService.getCustomerBoard(boardNumber);
		
		if(customer == null || "Y".equals(customer.getDeleteYn())) {
			
			return "redirect: /customer/list";
		}
		
		model.addAttribute("customer", customer);
		
		return "customer/detail";
	}
	
	@PostMapping("delete")
	public String deleteNotice(@RequestParam(value = "boardNumber", required = false) Integer boardNumber, Model model) {
		if(boardNumber == null) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/customer/list", Method.GET, null, model);
			//return "redirect: /customer/list";
		}
		try {
			boolean isDeleted = customerService.deleteCustomerBoard(boardNumber);
			if(isDeleted == false) {
				return showMessageWithRedirect("게시글 삭제에 실패하였습니다.", "/customer/list", Method.GET, null, model);
			}
		}catch(DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/customer/list", Method.GET, null, model);
		}catch(Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/customer/list", Method.GET, null, model);
		}
		return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/customer/list", Method.GET, null, model);
	}
	
//	@PostMapping("delete")
//	public String deleteNotice(@RequestParam(value = "boardNumber", required = false) Integer boardNumber) {
//		if(boardNumber == null) {
//			return "redirect:/customer/list";
//		}
//		try {
//			boolean isDeleted = customerService.deleteCustomerBoard(boardNumber);
//			if(isDeleted == false) {
//				
//			}
//		}catch(DataAccessException e) {
//			
//		}catch(Exception e) {
//			
//		}
//		return "redirect:/customer/list";
//	}
	
		
//		boolean delete = customerService.deleteCustomerBoard(boardNumber);
//		if(delete == false){
//			
//		}
//		
//		return "redirect:/list";
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
	
	
//}
	
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
