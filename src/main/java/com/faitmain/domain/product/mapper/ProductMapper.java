package com.faitmain.domain.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.faitmain.domain.product.domain.Product;

@Repository
@Mapper
public interface ProductMapper {
	
	public Product getProduct(int productNumber);
	
}
