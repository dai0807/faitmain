package com.faitmain.domain.customer.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;



import com.faitmain.domain.customer.domain.Customer;
import com.faitmain.domain.customer.service.CustomerService;

import lombok.RequiredArgsConstructor;



@Controller							
@RequestMapping("/customer/")
@RequiredArgsConstructor
public class CustomerController{
	
	@Autowired
	@Qualifier("customerServiceImpl")
	private final CustomerService customerService;
	
	@GetMapping("/hello")
	public String Hello() {
		return "/view/customer/hello";
	}
	
	@GetMapping("/test")
	public String test(Model model) throws Exception {
		int a = customerService.customerCount();
		List<Customer> list = customerService.getCustomerBoardList();
		System.out.println(list);
		System.out.println("list : "+list.get(0).getBoardTitle());
		System.out.println("a : " + a);
		model.addAttribute("cnt", customerService.customerCount());
		model.addAttribute("test", customerService.getCustomerBoardList());
		
		// Spring이 "cnt"라는 Model 객체에 customerService.customerCount()를 통해 받은 data를 넣어 뷰(hello.html)쪽으로 넘겨준다는 뜻
		// 그렇다면 View(hello.html)에서는 ${}를 통해 값을 가져와 출력하게 된다.
		return "/view/customer/hello";
	}
	
	@GetMapping("/main")
	public String main(Model model) throws Exception {
		model.addAttribute("list", customerService.getCustomerBoardList());
		
		return "/view/customer/main";
	}
	
	@GetMapping("/viewCustomer")
	public String viewCustomerBoard(Model model, int boardNumber) {
		Customer customer = customerService.getCustomerBoard(boardNumber);
		System.out.println("customer :"+customer);
		
		model.addAttribute("halo", customerService.getCustomerBoard(boardNumber));
		
		return "/view/customer/viewCustomer";
		//return "/boards/view"; 란   "halo" 라는 변수를 가지고 있는 (예를 들어, [[${halo.title}]] 가 이에 해당한다.) boards/view 페이지를 반환해 사용자가 볼 수 있게 동작한다.
	}
	
//	@Autowired
//	@Qualifier("customerServiceImpl")
//	private CustomerService customerService;			//business logic을 처리하는 Service bean을 연결


	
//	
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
	
//	@RequestMapping("/customer/getCustomerBoardList.do")		
//	@GetMapping("getCustomerBoardList")							
//	public String getCustomerBoardList(Model model, Customer customer) throws Exception{
//		
//		System.out.println("/customer/getCustomerBoardList : GET start...");
//		
//		List<Customer> list = customerService.getCustomerBoardList();	
//		
//		System.out.println(list.get(0).getBoardNumber());
//		
//		model.addAttribute("list",list);						
//		
//		
//		return "view/customer/boardList";
//	}
//	
//	@GetMapping("addCustomerBoard")
//	public String addCustomerBoard()	throws Exception{
//		
//		System.out.println("/customer/addCustomerBoard : GET start");
//		
//		System.out.println("/customer/addCustomer : GET end");
//		
//		return null;
//	}
//	
//	@PostMapping("addCustomerBoard")
//	public String addCustomerBoard(@ModelAttribute("customer") Customer customer, @RequestParam("file") Multipart file, HttpSession session, 
//															RedirectAttributes model) throws Exception{
//		System.out.println("/customer/addCustomerBoard : POST start");
//		
//		System.out.println("/customer/addCustomerBoard : POST end");
//		return null;
//	}
//	
//	
//	
//	@GetMapping("updateCustomerBoard")
//	public String updateCustomerBoard() throws Exception{
//		
//		System.out.println("/customer/updateCustomerBoard : GET start..");
//		
//		System.out.println("/customer/updateCustomerBoard : GET end..");
//		
//		return null;
//	}
//	
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
//	
//	@PostMapping("deleteCustomerBoard")
//	public String deleteCustomerBoard(@ModelAttribute("customer") Customer customer, Model model) throws Exception{
//		
//		System.out.println("/customer/deleteCustomerBoard : POST start..");
//		
//		System.out.println("/customer/deleteCustomerBoard : POST end..");
//		
//		return null;
//	}
//	
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
	
	//@GetMapping("hello") : get요청이 hello로 들어오면 hello method를 호출하여서
	// hello.html 파일을 리턴하게 하는 어노테이션
	// 타임리프 : 백엔드 서버에서 HTML을 동적으로 렌더링하는 용도로 사용 
	
	
	
	
}
