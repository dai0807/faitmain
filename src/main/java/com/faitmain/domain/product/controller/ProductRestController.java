package com.faitmain.domain.product.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.faitmain.domain.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/product/*")
@Slf4j
public class ProductRestController {
	
	@Value("${upload-path}")
	private String fileStorageLocation;
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public ProductRestController() {
		log.info("Controller = {} ", ProductRestController.class);
	}
	
//	/product/json/uploadSummernoteImageFile
	@PostMapping("json/uploadSummernoteImageFile")
	@ResponseBody
	public ResponseEntity<?> uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) throws Exception{
		System.out.println("uploadSummernoteImageFile");
		
		if(multipartFile != null && !multipartFile.isEmpty()) {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss_");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String timeStamp = sdf.format(timestamp);
			
			String fileName =  timeStamp + multipartFile.getOriginalFilename();
			
			Path targetLocation = (Paths.get(fileStorageLocation).toAbsolutePath().normalize()).resolve(fileName);
					
			Files.copy(multipartFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
			Resource resource = new UrlResource(targetLocation.toUri());
			System.out.println("resource" + resource);
			HttpHeaders header = new HttpHeaders();
			header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
//			
			header.setContentType(MediaType.IMAGE_JPEG);
//			System.out.println("resource2 : " + ResponseEntity.ok().body(resource));
			return ResponseEntity.ok().headers(header).body(resource);
//			jsonObject.addProperty("url", targetLocation.toString());
//			jsonObject.addProperty("responseCode", "success");
//			result = fileName;
//			result = targetLocation.toString();
			
		}else {
//			jsonObject.addProperty("responseCode", "error");
			return ResponseEntity.badRequest().build();
		}
		
//		System.out.println("url" + jsonObject.toString());
//		result = jsonObject.toString();
//		return null;
	}
	
}