package com.faitmain.domain.product.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
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
import com.google.gson.JsonObject;

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
	@PostMapping(value = "json/uploadSummernoteImageFile", produces = "application/json")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {
		
		System.out.println("uploadSummernoteImageFile");
		
		JSONObject jsonObject = new JSONObject();
		
		String fileRoot = "C:\\summernote_image\\";	//저장될 파일 경로
				
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
		
        // 랜덤 UUID+확장자로 저장될 savedFileName
        String savedFileName = UUID.randomUUID() + extension;	
		
        File targetFile = new File(fileRoot + savedFileName);
		
		try {
			InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
//            jsonObject.addProperty("url", "/summernoteImage/"+fileName);
//            jsonObject.addProperty("responseCode", "good");
            jsonObject.put("url", "/summernoteImage/" + savedFileName);
            jsonObject.put("responseCode", "success");
		}catch(IOException e) {
			FileUtils.deleteQuietly(targetFile);	// 실패시 저장된 파일 삭제
//            jsonObject.addProperty("responseCode", "fail");
            e.printStackTrace();
		}
		return jsonObject.toJSONString();
	}
	
}