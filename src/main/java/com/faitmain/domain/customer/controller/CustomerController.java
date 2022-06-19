package com.faitmain.domain.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.faitmain.domain.customer.domain.Customer;
import com.faitmain.domain.customer.service.CustomerService;
import com.faitmain.global.util.UiUtils;

@Controller
@RequestMapping("/customer/")
public class CustomerController extends UiUtils {

	@Autowired
	@Qualifier("customerServiceImpl")
	private CustomerService customerService;

	@GetMapping("noticeIndex")

	public String openBoardNoticeIndex() {
		return "/customer/noticeIndex";

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
	
	@GetMapping("detailLiveGuide")
	public String openLive() throws Exception{
		return "customer/liveGuideDetail";
	}
	

	@PostMapping("insert")
	public String addCustomerBoard(@RequestParam(value = "boardType", required = false)Integer boardTyep, Customer customer, Model model) throws Exception {

		String url =  null;
		customerService.addCustomerBoard(customer);

		List<Customer> list = customerService.getCustomerBoardList(customer.getBoardType()+"");
		model.addAttribute("boardList", list);
		
		if(customer.getBoardType() == 'N') {
			url = "customer/noticeList";
		}else if(customer.getBoardType() == 'L') {
			url = "customer/liveGuideDetail";
		}else if(customer.getBoardType() == 'F') {
			url =  "customer/faqList";
		}else if(customer.getBoardType() == 'R') {
			url = "customer/noticeIndex";
		}
		
		return url;

	}



	
	@GetMapping("listBoard")
	public String openBoardList(@RequestParam(value= "boardType", required=false) String boardType, Model model) throws Exception {
		System.out.println("=======list==========");
		System.err.println(boardType);
		
		List<Customer> boardList = customerService.getCustomerBoardList(boardType);
		
		model.addAttribute("boardList", boardList);
		System.out.println(boardList);
		String url =  null;
//		model.addAttribute("boardList", customerService.getCustomerBoardList());
		if(boardType.equals("N")) {
			url= "customer/noticeList";
		}else if(boardType.equals("F")){
			url= "customer/faqList";
		}
		System.out.println(url);
		return url;
		
	}
	
	
	@GetMapping("detailGuide")
	public String openGuideDetail(@RequestParam(value="boardType", required=false)String boardType, Model model) throws Exception{
		
		Customer customer = customerService.getLiveGuide(boardType);
		
		model.addAttribute("liveguide", customer);
		
		return "customer/liveGuideDetail";
	}
	

//	@PostMapping("register")
//	public String registerBoard(final Customer params, Model model)  throws Exception {
//		System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
//	//z	System.out.println(params);
//			
//		List<Customer> boardList = customerService.getCustomerBoardList();
//		Map<String, Object> Map = new HashMap<String, Object>();		
//		Map.put("map", boardList) ;
//  	//	System.out.println("map 들어갔니 ={}  "   + Map  );
//		
//		try {
//				boolean isRegistered = customerService.registerCustomerBoard(params);
//				if(isRegistered == false) {
//					return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/customer/list", Method.GET, null, model);
//				}
//			}catch(DataAccessException e) {
//				return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/customer/list", Method.GET, null, model);
//			
//			}catch(Exception e) {
//				return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/customer/list", Method.GET, null, model);
//			}
//			
//			return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/customer/list", Method.GET, Map,  model);
//			/*if(register == false) {
//				//게시글 "등록실패"
//				System.out.println("등록 실패");*/
//		
//			//boolean register = customerService.registerCustomerBoard(params);
//			//System.out.println(customer);
//		
//		//return "redirect:/list";
//	}
//	



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

	
	@GetMapping("detailBoard")
	public String openBoardDetail(@RequestParam(value="boardNumber", required=false) Integer boardNumber, Model model) throws Exception {
		
		
		if(boardNumber == null) {
			return "redirect:/customer/noticeList";
		}
		return null;
	}

	
	

//	@PostMapping("delete")
//	public String deleteBoard(@RequestParam(value = "boardNumber", required = false) Integer boardNumber, Model model) {
//		if(boardNumber == null) {
//			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/customer/list", Method.GET, null, model);
//			//return "redirect: /customer/list";
//		}
//		try {
//			boolean isDeleted = customerService.deleteCustomerBoard(boardNumber);
//			if(isDeleted == false) {
//				return showMessageWithRedirect("게시글 삭제에 실패하였습니다.", "/customer/list", Method.GET, null, model);
//			}
//		}catch(DataAccessException e) {
//			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/customer/list", Method.GET, null, model);
//		}catch(Exception e) {
//			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/customer/list", Method.GET, null, model);
//		}
//		return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/customer/list", Method.GET, null, model);
//	}

	
	@PostMapping("deleteBoard")
	public String deleteBoard(@RequestParam(value = "boardNumber", required=false) Integer boardNumber, @RequestParam(value = "boardType", required=false) String boardType) throws Exception {
		
			
		boolean isDeleted = customerService.deleteCustomerBoard(boardNumber);
			
			return "redirect:/customer/listBoard?boardType="+boardType;
	}		
		

	@PostMapping("deleteNotice")
	public String deleteNotice(@RequestParam(value = "boardNumber", required = false) Integer boardNumber) {
		if (boardNumber == null) {
			return "redirect:/customer/list";
		}
		try {
			boolean isDeleted = customerService.deleteCustomerBoard(boardNumber);
			if (isDeleted == false) {

			}
		} catch (DataAccessException e) {

		} catch (Exception e) {

		}
		return "redirect:/customer/list";

	}

}
