package com.faitmain.domain.customer.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.maven.shared.invoker.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.faitmain.domain.customer.domain.Customer;
import com.faitmain.domain.customer.service.CustomerService;
import com.faitmain.domain.user.domain.User;
import com.faitmain.global.common.Criterion;
import com.faitmain.global.common.Page;
import com.faitmain.global.util.UiUtils;
import com.faitmain.global.util.security.SecurityUserService;

@Controller
@RequestMapping("/customer/")
public class CustomerController extends UiUtils {

	@Autowired
	@Qualifier("customerServiceImpl")
	private CustomerService customerService;

	@GetMapping("openBoardIndex")
	public String openBoardIndex() throws Exception {
		return "customer/customerCenterIndex2";
	}

	@GetMapping("addNotice")
	public String openNotice() throws Exception{
		return "admin/noticeForm";
	}	

	@GetMapping("addLiveGuide")
	public String openGuide() throws Exception{
		return "admin/liveGuideForm";
	}
	
	@GetMapping("addFAQ")
	public String openFAQ() throws Exception{
		return "admin/faqForm";
	}
	
	@GetMapping("addReport")
	public String openReport() throws Exception{	
		return "customer/reportForm";
	}
	

	@PostMapping("addBoard")
	public String addBoard( @ModelAttribute Customer customer, Model model) throws Exception{
		
		SecurityUserService securityUserService = ( SecurityUserService ) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // principal 에 사용자 인증 정보 담음
		User user = (User) securityUserService.getUser();
		
		customerService.addCustomerBoard(customer);
		
		System.out.println(customer);
		
		String url =  null;
		
		List<Customer> list = customerService.getCustomerBoardList(customer.getBoardType());
		
		System.out.println(list);
		
		
		model.addAttribute("boardList", list);
		if(customer.getBoardType() == 'N') {
			url = "customer/noticeList";
		}else if(customer.getBoardType() == 'L') {
			url = "customer/liveGuideDetail";
		}else if(customer.getBoardType() == 'F') {
			url =  "customer/faqList";
		}
		
		return url;

	}


//	게시판 목록	(페이징 적용, 게시판 타입별, 라이브가이드 카테고리별 조회 적용)	
	@GetMapping("listBoard")
	public String openBoardList(@ModelAttribute Customer customer, Model model, Criterion criterion) throws Exception {
		
		System.out.println("=======list==========");
		String url =  null;
		List<Customer> boardList=null;
		if(customer.getBoardType() == 'N') {
			boardList = customerService.getCustomerBoardList(customer.getBoardType());
			url= "customer/noticeList";
			
			System.out.println(boardList);
			
//		model.addAttribute("boardList",customerService.getListPaging(criterion));
//		int total = customerService.getBoardTotalCount();
//		Page page = new Page(criterion, total);
//		model.addAttribute("page", page);
	
		
		}else if(customer.getBoardType() == 'F'){	
			boardList = customerService.getFAQList(customer.getFAQCategoryCode());
			System.out.println(customer.getFAQCategoryCode());
			
			url="customer/faqList";
		}		
		System.out.println(url);
		model.addAttribute("boardList", boardList);
		return url;
		
	}
	
	
	@GetMapping("detailBoard")
	public String openBoardDetail(@RequestParam(value="boardNumber", required=false) Integer boardNumber, Model model) throws Exception{
		
		model.addAttribute("customer", customerService.getCustomerBoard(boardNumber));
		
		return "customer/noticeDetail";
	}
	

	@GetMapping("detailGuide")
	public String openGuideDetail(@RequestParam(value="boardType", required=false)char boardType, Model model) throws Exception{
		System.out.println("=============");
		
		Customer customer = customerService.getLiveGuide(boardType);
		
		System.out.println(boardType);
		
		model.addAttribute("customer", customer);
		
		System.out.println(customer);
		
		String url = null;
		if(boardType == 'L') {
		 url = "customer/liveGuideDetail";
		}
		return url;
	}
	



//	@GetMapping("listFAQ")
//	public String openFAQList(Model model) throws Exception {
//		
//		List<Customer> boardList = customerService.getCustomerBoardList();
//		model.addAttribute("boardList", boardList);
////		model.addAttribute("boardList", customerService.getCustomerBoardList());
//				
//		return "customer/listFAQ";
//		
//	}

	
//	@GetMapping("detailBoard")
//	public String openBoardDetail(@RequestParam(value="boardNumber", required=false) Integer boardNumber, Model model) throws Exception {
//		
//
//		model.addAttribute("customer", customer);
//
//		
//		return "customer/noticeDetail";
//
//	}



	
	@PostMapping("deleteBoard")
	public String deleteBoard(@RequestParam(value = "boardNumber", required=false) Integer boardNumber, @RequestParam(value="boardType", required=false)char boardType, Customer customer) throws Exception {
		
		int isDeleted = customerService.deleteCustomerBoard(boardNumber);
		
		
		String url = null;
		
		if(boardType == 'N') {
			return "redirect:/customer/noticeList";
		}else if(boardType == 'L') {
			return "redirect:/customer/customerCenterIndex2";
		}
		return url;
	}		
		

//	@PostMapping("deleteNotice")
//	public String deleteNotice(@RequestParam(value = "boardNumber", required = false) Integer boardNumber) {
//		if (boardNumber == null) {
//			return "redirect:/customer/list";
//		}
//		try {
//			boolean isDeleted = customerService.deleteCustomerBoard(boardNumber);
//			if (isDeleted == false) {
//
//			}
//		} catch (DataAccessException e) {
//
//		} catch (Exception e) {
//
//		}
//		return "redirect:/customer/list";
//
//	}
	

	

}
