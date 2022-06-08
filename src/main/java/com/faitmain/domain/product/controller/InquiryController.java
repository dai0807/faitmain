package com.faitmain.domain.product.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.faitmain.domain.product.domain.Inquiry;
import com.faitmain.domain.product.service.InquiryService;
import com.faitmain.global.common.Page;
import com.faitmain.global.common.Search;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/inquiry/")
@Slf4j
public class InquiryController {
	
	@Autowired
	@Qualifier("inquiryServiceImpl")
	InquiryService inquiryService;

	public InquiryController() {
		log.info("Controller = {} ", InquiryController.class);
	}
	
	@GetMapping("addInquiry")
	public String addInquiry() throws Exception{
		
		log.info("/inquiry/addInquiry : GET");
				
		return "redirect:/inquiry/addInquiry.jsp";
	}
	
	@PostMapping("addInquiry")
	public String addInquiry(@ModelAttribute("inquiry") Inquiry inquiry) throws Exception{
		
		log.info("/inquiry/addInquiry : POST");
		
		inquiryService.addInquiry(inquiry);
		
		return "forward:/inquiry/listInquiryUser.jsp";
	}
	
	@GetMapping("getInquiry")
	public String getInquiry(@RequestParam("inquiryNumber") int inquiryNumber, Model model) throws Exception{
		
		log.info("/inquiry/getInquiry");
		
		Inquiry inquiry = inquiryService.getInquiry(inquiryNumber);
		
		model.addAttribute("inquiry", inquiry);
		
		return "forward:/inquiry/getInquiry.jsp";
	}
	
	@GetMapping("getInquiryList")
	public String getInquiryList(@ModelAttribute("search") Search search, @RequestParam("resultJsp") String resultJsp, Model model) throws Exception{
		
		log.info("/inquiry/getInquiryList");
		
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		
		search.setPageSize(10);
		
		Map<String, Object> map = inquiryService.getInquiryList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), 4, 10);
		
		log.info("resultPage : " + resultPage);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/inquiry/" + resultJsp + ".jsp";
	}
	
	@GetMapping("updateInquiry")
	public String updateInquiry(@RequestParam("inquiryNumber") int inquiryNumber, @RequestParam("resultJsp") String resultJsp, Model model) throws Exception{
		
		log.info("/inquiry/updateInquiry : GET");
		
		Inquiry inquiry = inquiryService.getInquiry(inquiryNumber);
		
		model.addAttribute("inquiry", inquiry);
		
		log.info("inquiry : " + inquiry);
		
		return "forward:/inquiry/" + resultJsp;
	}
	
	@PostMapping("updateInquiry")
	public String updateInquiry(@ModelAttribute("inquiry") Inquiry inquiry) throws Exception{
		
		log.info("/inquiry/updateInquiry : POST");
		
		inquiryService.updateInquiry(inquiry);
		
		return "redirect:/inquiry/getInquiry?inquiryNumber=" + inquiry.getInquiryNumber();
	}
	
	@GetMapping("deleteInquiry")
	public String deleteInquiry(@RequestParam("inquiryNumber") int inquiryNumber, @RequestParam("resultJsp") String resultJsp) throws Exception{
		
		log.info("/inquiry/deleteInquiry");
		
		inquiryService.deleteInquiry(inquiryNumber);
		
		return "redirect:/inquiry/listInquiry?resultJsp=" + resultJsp;
	}
	
}
