package com.faitmain.domain.product.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.faitmain.domain.product.domain.Product;

@Mapper
public interface ProductMapper {
	
	Product getProduct(int productNumber);
	
}
