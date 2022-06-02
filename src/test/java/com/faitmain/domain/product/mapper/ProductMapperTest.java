package com.faitmain.domain.product.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.faitmain.domain.product.domain.Product;
import com.faitmain.domain.user.domain.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductMapperTest {
	
	@Autowired
	private ProductMapper productMapper;
	
	//insert
//	@Test
	@DisplayName("addProduct Mapper Test")
	public void addProductTest() throws Exception{
		
		System.out.println("addProductTest start");
		
		Product product = new Product();
		
		product.setProductName("오레오마카롱");
		product.setPrice(3800);
		
		User store = new User();
		store.setId("store01@naver.com");
		
		product.setStore(store);
		product.setProductMainImage("oreo.jpg");
		product.setCategoryCode("01");
		product.setDeliveryCharge(3000);
		product.setProductQuantity(30);
		
		productMapper.addProduct(product);
		
		System.out.println("insert 완료");
		
		Product resultProduct = productMapper.getProduct(10016);
		assertThat(resultProduct.getProductNumber()).isEqualTo(10016);
		System.out.println("addProductTest end");
		
	}
	
//	get
//	@Test
	@DisplayName("getProduct Mapper Test")
	public void getProductTest() throws Exception{
		
		System.out.println("getProductTest start");
		
		Product product = productMapper.getProduct(10000);
		
		assertThat(product.getPrice()).isEqualTo(5000);
		
		System.out.println("getProductTest end");
		
	}
	
}