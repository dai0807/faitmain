package com.faitmain.domain.customer.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	public String addBoard( @RequestParam(value = "boardType", required = false) char boardType, @RequestParam(value = "categoryCode", required = false) String FAQCategoryCode, Customer customer, Model model) throws Exception{
		
		SecurityUserService securityUserService = ( SecurityUserService ) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // principal 에 사용자 인증 정보 담음
		User user = (User) securityUserService.getUser();
		
		String url =  null;
		
		System.out.println("ddd" + boardType);
		System.out.println("ddd" + FAQCategoryCode);

		customer.setBoardType(boardType);
		customer.setFAQCategoryCode(FAQCategoryCode);
		
		System.out.println(FAQCategoryCode);
		
		customerService.addCustomerBoard(customer);

		List<Customer> list = customerService.getCustomerBoardList(customer.getBoardType());

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
	public String openBoardList(@RequestParam(value= "boardNumber", required=false) Integer boardNumber, @RequestParam(value= "boardType", required=false) char boardType, 
											@RequestParam(value="FAQCategoryCode", required=false) String FAQCategoryCode, Model model, Criterion criterion) throws Exception {
		
		System.out.println("=======list==========");
		System.out.println(boardType);
		
		List<Customer> boardList = customerService.getCustomerBoardList(boardType);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("boardList",boardNumber);
		System.out.println(boardList);
		
		model.addAttribute("boardList",customerService.getListPaging(criterion));
		int total = customerService.getBoardTotalCount();
		Page page = new Page(criterion, total);
		model.addAttribute("page", page);
		
		String url =  null;
		model.addAttribute("boardList", customerService.getCustomerBoardList(boardType));
		if(boardType == 'N') {
			url= "customer/noticeList";
		}else if(boardType == 'F'){	
			
			if(FAQCategoryCode.equals("0")) {
				url= "customer/faqList?FAQCategoryCode="+FAQCategoryCode;
			}else if(FAQCategoryCode.equals("1")) {
				url= "customer/faqList?FAQCategoryCode="+FAQCategoryCode;
			}else if(FAQCategoryCode.equals("2")) {
				url= "customer/faqList?FAQCategoryCode="+FAQCategoryCode;
			}else if(FAQCategoryCode.equals("3")) {
				url= "customer/faqList?FAQCategoryCode="+FAQCategoryCode;
			}else if(FAQCategoryCode.equals("4")) {
				url= "customer/faqList?FAQCategoryCode="+FAQCategoryCode;
			}
		}		
		System.out.println(url);
		return url;
		
	}
	
	
	@GetMapping("detailBoard")
	public String openBoardDetail(@RequestParam(value="boardNumber", required=false) Integer boardNumber, @RequestParam(value="boardType", required=false) char boardType, Model model) throws Exception{
		
		model.addAttribute("customer",boardNumber);
		
		String url = null;
		
		Customer customer = customerService.getLiveGuide(boardType);
		model.addAttribute("customer", customer);
		
		if(boardType == 'L') {
		 url = "customer/liveGuideDetail?boardNumber="+boardNumber;
		}else if(boardType == 'N') {
			url = "customer/noticeDetail?boardNumber="+boardNumber;
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
	public String deleteBoard(@RequestParam(value = "boardNumber", required=false) Integer boardNumber, @RequestParam(value = "boardType", required=false) char boardType) throws Exception {
		
		int isDeleted = customerService.deleteCustomerBoard(boardNumber);
		
		String url = null;
		
		if(boardType == 'N') {
			return "redirect:/customer/listBoard";
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
