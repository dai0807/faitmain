package com.faitmain.domain.product.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.faitmain.domain.product.domain.Inquiry;
import com.faitmain.domain.product.domain.Product;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InquiryMapperTest {

	@Autowired
	private InquiryMapper inquiryMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Test
	@DisplayName("addInquiry Mapper Test")
	public void addInquiryTest() throws Exception{
		
		System.out.println("addInquiryTest start");
		
		Inquiry inquiry = new Inquiry();
		inquiry.setInquiryTitle("배송문의");
		inquiry.setInquiryContent("배송 언제쯤 될까요?");
		inquiry.setUserId("user01@naver.com");
		inquiry.setStoreId("store01@naver.com");
		
		Product product = productMapper.getProduct(10015);
		inquiry.setInquiryProduct(product);		
		inquiry.setSecret(false);
				
		System.out.println("addInquiryTest end");
	}
	
	@Test
	@DisplayName("getInquiry Mapper Test")
	public void getInquiryTest() throws Exception{
		
		System.out.println("getInquiryTest start");
				
		Inquiry inquiry = inquiryMapper.getInquiry(10007);
				
		System.out.println("getInquiryTest end");
	}
	
	@Test
	@DisplayName("getInquiryList Mapper Test")
	public void getInquiryListTest() throws Exception{
		
		System.out.println("getInquiryListTest start");
				
		Map<String, String> map = new HashMap<String, String>();
		map.put("startRowNum", 1+"");
		map.put("endRowNum", 5+"");
		
		List<Inquiry> inquiry = inquiryMapper.getInquiryList(map);
				
		System.out.println("getInquiryListTest end");
	}
	
	@Test
	@DisplayName("updateInquiry Mapper Test")
	public void updateInquiryTest() throws Exception{
		
		System.out.println("updateInquiryTest start");
		
		Inquiry inquiry = new Inquiry();
		inquiry.setInquiryNumber(10007);
		inquiry.setInquiryTitle("배송문의");
		inquiry.setInquiryContent("배송 언제쯤 될까요?");
		inquiry.setSecret(false);
		inquiry.setInquiryReplyStatus(true);
		inquiry.setInquiryReplyCotent("2~3일 걸립니다.");
		inquiryMapper.updateInquiry(inquiry);
				
		System.out.println("updateInquiryTest end");
	}
	
	@Test
	@DisplayName("deleteInquiry Mapper Test")
	public void deleteInquiryTest() throws Exception{
		
		System.out.println("deleteInquiryTest start");
		
		inquiryMapper.deleteInquiry(10007);
		
		System.out.println("deleteInquiry end");
	}
	
}