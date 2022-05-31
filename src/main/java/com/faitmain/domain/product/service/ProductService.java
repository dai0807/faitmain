package com.faitmain.domain.product.service;

import com.faitmain.domain.product.domain.Product;

public interface ProductService {

	// 상품확인
	public Product getProduct(int productNumber) throws Exception;
	
}
