package com.faitmain.domain.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.faitmain.domain.product.domain.Product;
import com.faitmain.domain.product.mapper.ProductMapper;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public Product getProduct(int productNumber) throws Exception {
		return productMapper.getProduct(productNumber);
	}

}
