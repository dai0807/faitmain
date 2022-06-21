

package com.faitmain.domain.customer.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.faitmain.domain.customer.domain.Customer;
import com.faitmain.domain.customer.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/customer/*")
public class CustomerRestController {
	
	 @Autowired
	 @Qualifier("customerServiceImpl")
	private CustomerService customerService;
	
	public CustomerRestController() {
		log.info("Controller = {} ", CustomerRestController.class);
	}
	
	@GetMapping("json/FAQListConditionPayment")
	public JSONArray FAQListConditionPayment () throws Exception {
		
		
		JSONArray lol = new JSONArray();
		
		return lol;
	}
	
	@RequestMapping(value = "/json/getFAQCategoryCode", method = RequestMethod.GET, produces="application/json;")
	public @ResponseBody List<Customer> getFAQCategoryCode(String FAQCategoryCode) throws Exception{
		String result = "";
		List<Customer> boardList = customerService.getFAQList(FAQCategoryCode);
		
		return boardList;
	}
	
	
	
	@PostMapping("json/updateCustomerBoard")
	public int  getCustomerBoard(@ModelAttribute Customer customer) throws Exception{
		
		System.out.println("/customer/json/updateCustomerBoard : POST start ...");
		System.out.println("찍어보자!! Cutstomer ." + customer);

		
		
		
		int result =  customerService.updateCustomerBoard(customer) ; 
		System.out.println("result = " +  result ); // 수정이 되었으면 1 , 안됨  0  		
		System.out.println("/customer/json/updateCustomerBoard : POST end ...");
		
		
 		return result;
	}
	
//	/customer/json/uploadSummernoteImageFile	
	@PostMapping(value="json/uploadSummernoteImageFile", produces = "application/json")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {
		
		System.out.println("uploadSummernoteImageFile");
		
		JSONObject jsonObject = new JSONObject();
		
		String fileRoot = "C:\\summernote_image\\";											//저장될 외부 파일 경로
		String originalFileName = multipartFile.getOriginalFilename();						//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));  //파일 확장자
		
		String savedFileName = UUID.randomUUID() + extension;								//저장될 파일명
		
		File targetFile = new File(fileRoot + savedFileName);
		
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
			jsonObject.put("url", "/summernoteImage/"+savedFileName);
			jsonObject.put("responseCode", "success");
				
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
//			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		return jsonObject.toJSONString();
	}
	
	
	
}
	
	
	
	


