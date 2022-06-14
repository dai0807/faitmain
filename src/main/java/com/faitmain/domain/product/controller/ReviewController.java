package com.faitmain.domain.product.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.faitmain.domain.product.domain.Review;
import com.faitmain.domain.product.service.ReviewService;
import com.faitmain.global.common.Page;
import com.faitmain.global.common.Search;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/review/")
@Slf4j
public class ReviewController {
	
	@Value("${upload-path}")
	private String fileStorageLocation;	

	@Autowired
	@Qualifier("reviewServiceImpl")
	private ReviewService reviewService;

	public ReviewController() {
		log.info("Controller = {}", this.getClass());
	}
	
	@GetMapping("addReview")
	public String addReview() throws Exception{
		
		log.info("/review/addReview : GET");
				
		return "/product/addReview";
	}
	
	@PostMapping("addReview")
	public String addReview(@ModelAttribute("review") Review review, MultipartFile file) throws Exception{
		
		log.info("/review/addReview : POST");
		
		if(!file.isEmpty()) {
			 String fileName = storeFile(file);
			 review.setReviewImage(fileName);
		}
		
		reviewService.addReview(review);
//		@RequestParam("userId") String userId
		return "forward:/review/listReviewUser?userId=" + review.getUserId();
	}
	
	@GetMapping("getReview")
	public String getReview(@RequestParam("reviewNumber") int reviewNumber, Model model) throws Exception{
		log.info("/review/getReview");
		
		Review review = reviewService.getReview(reviewNumber);
		
		model.addAttribute("review", review);
		
		return "forward:/review/getReview.jsp";		
	}
	
	@GetMapping("getReviewList")
	public String getReviewList(@ModelAttribute("search") Search search,@RequestParam("resultJsp") String resultJsp, Model model) throws Exception{
		
		log.info("/review/getReviewList");
		
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		
		search.setPageSize(10);
		
		Map<String, Object> map = reviewService.getReviewList(search);
/*		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), 4, 10);
		
		log.info("resultPage : " + resultPage);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
*/		
		return "forward:/review/" + resultJsp + ".jsp";
	}
	
	@GetMapping("updateReview")
	public String updateReview(@RequestParam("reviewNumber") int reviewNumber, Model model) throws Exception{
		
		log.info("/review/updateReview : GET");
		
		Review review = reviewService.getReview(reviewNumber);
		
		model.addAttribute("review", review);
		
		log.info("review : " + review);		
		
		return "forward:/review/updateReview.jsp";
	}
	
	@PostMapping("updateReview")
	public String updateReview(@ModelAttribute("review") Review review) throws Exception{
		
		log.info("/review/updateReview : POST");
		
		reviewService.updateReview(review);
		
		return "redirect:/review/getReviewList?resultJsp=listReviewUser";
	}
	
	@GetMapping("deleteReview")
	public String deleteReview(@RequestParam("reviewNumber") int reviewNumber) throws Exception{
		
		log.info("/review/deleteReview");
		
		reviewService.deleteReview(reviewNumber);
		
		return "redirect:/inquiry/listInquiry?resultJsp=listReviewUser";	
	}
	
	public String storeFile(MultipartFile file) throws Exception{
		
		SimpleDateFormat sdf = new SimpleDateFormat("_yyyyMMddhhmmss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String timeStamp = sdf.format(timestamp);
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename()) + timeStamp;
		
		Path targetLocation = (Paths.get(fileStorageLocation).toAbsolutePath().normalize()).resolve(fileName); 
		Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
		
		return fileName;
	}
	
}
