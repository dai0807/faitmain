package com.faitmain.domain.product.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.faitmain.domain.product.domain.Product;
import com.faitmain.domain.product.service.ProductService;


@Transactional
@SpringBootTest
public class ProductMapperTest {
	
	Logger logger = LoggerFactory.getLogger(ProductMapperTest.class);
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
//	ProductMapper productMapper;
	
	@Test
	public void testGetProduct() throws Exception{
		Product product = productService.getProduct(10000);
		System.out.println(product);
		assertNotNull(product);
	}

}
