
package com.faitmain.domain.customer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.faitmain.domain.customer.domain.Customer;
import com.faitmain.domain.customer.service.CustomerService;
import com.faitmain.domain.user.domain.User;

import lombok.RequiredArgsConstructor;



@Controller							
@RequestMapping("/customer/")
@RequiredArgsConstructor
public class CustomerController{
	
	@Autowired
	@Qualifier("customerServiceImpl")
	private final CustomerService customerService;
	
	
	@GetMapping("/main")
	public String main(Model model) throws Exception {
		model.addAttribute("list", customerService.getCustomerBoardList());
		
		return "/view/customer/main";
	}
	
	@GetMapping("/getCustomer")
	public String viewCustomerBoard(Model model, int boardNumber) {
		int temp = customerService.getViewCount(boardNumber);
		customerService.updateViewCount(boardNumber, (temp+1) );
		model.addAttribute("halo", customerService.getCustomerBoard(boardNumber));
		
		return "/view/customer/getCustomer";
		//return "/boards/view"; 란   "halo" 라는 변수를 가지고 있는 (예를 들어, [[${halo.title}]] 가 이에 해당한다.) boards/view 페이지를 반환해 사용자가 볼 수 있게 동작한다.
	}
	
	@GetMapping("/addCustomer")
	public String addCustomerBoardForm() {
		return "/view/customer/addCustomer";
	}
	//@GetMapping("/addCustomer")를 통해 localhost:8080/customer/addCustomer address로 이동하면 templates>view>customer folder에 있는
	// addCustomer.html로 이동하는 입력페이지로의 이동 mapping을 한다. 
	
	@PostMapping("/addCustomer")
	public String addCustomerBoard(@ModelAttribute("customer") Customer customer,
								   @ModelAttribute("user") User user) {
		customer.setCustomerId(user);
		customerService.addCustomerBoard(customer);
		return "redirect:/customer/main"; 
	}
	//PostMapping("/addCustomer"):localhost:8080/customer/addCustomer address로 post 방식으로 이동하면 customerService -> mapper interface ->mapper.xml의 
	//과정을 거쳐 CustomerBoardMapper.xml에 작성한 query에서 insert문을 보낸다.
	//그 후 업로드 버튼을 누르면 업로드를 시행하고, 다시 main page로 redirect한다. 
	
	@GetMapping("/updateCustomer")
	public String updateCustomerBoardForm(Model model, int boardNumber) {
		model.addAttribute("update", customerService.getCustomerBoard(boardNumber));
		
		return "/view/customer/updateCustomer";
	}
	
	@PostMapping("/updateCustomer")
	public String updateCustomerBoard(Customer customer) {
		customerService.updateCustomerBoard(customer);
		return "redirect:/customer/main";
	}
	
	@GetMapping("/deleteCustomer")
	public String deleteCustomerBoard(int boardNumber) {
		customerService.deleteCustomerBoard(boardNumber);
		return "redirect:/customer/main";
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

//package com.faitmain.domain.customer.controller;
//
//import java.util.List;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.faitmain.domain.customer.domain.BanStatus;
//import com.faitmain.domain.customer.domain.Customer;
//import com.faitmain.domain.customer.service.CustomerService;
//
//import lombok.extern.slf4j.Slf4j;
//import retrofit2.http.Multipart;
//
//@Slf4j
//@Controller
//@RequestMapping("/customer/")
//public class CustomerController{
//	
//	@Autowired
//	@Qualifier("customerServiceImpl")
//	private CustomerService customerService;
//
//	public CustomerController() {
//		log.info("Controller = {}", this.getClass());
//		
//		System.out.println(this.getClass());
//	}
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
//	
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
//	
//	
//	
//	
//	
//	
//	
//}

