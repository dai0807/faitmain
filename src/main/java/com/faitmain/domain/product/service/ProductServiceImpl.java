package com.faitmain.domain.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faitmain.domain.product.domain.Product;
import com.faitmain.domain.product.mapper.ProductMapper;
import com.faitmain.global.common.Image;
import com.faitmain.global.common.Search;

import lombok.RequiredArgsConstructor;

@Service("productServiceImpl")
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	public ProductServiceImpl(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}

	@Override
	public int addProduct(Product product) throws Exception {
		productMapper.addProduct(product);		
		return productMapper.getProductNumber();
	}

	@Override
	public void addProductImage(Image image) throws Exception {
		productMapper.addProductImage(image);		
	}

	@Override
	public Map<String, Object> getProduct(int productNumber) throws Exception {
		Product product = productMapper.getProduct(productNumber);
		List<Product> productOptions = productMapper.getProductOption(productNumber);		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mainProduct", product);
		map.put("productOptions", productOptions);
		
		return map;
	}

	@Override
	public Map<String, Object> getProductList(Map<String, Object> map) throws Exception {
		List<Product> list = productMapper.getProductList(map);
		int totalCount = productMapper.getTotalCount(map);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", list);
		resultMap.put("totalCount", new Integer(totalCount));
		
		return resultMap;
	}
	
	@Override
	public Map<String, Object> getProductListByStoreId(String storeId) throws Exception {
		List<Product> list = productMapper.getProductListByStoreId(storeId);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", list);
		return resultMap;
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		productMapper.updateProduct(product);
		
	}

	@Override
	public void updateProductImage(Image image) throws Exception {
		productMapper.updateProductImage(image);
		
	}

	@Override
	public void updateProductStatus(Product product) throws Exception {
		productMapper.updateProductStatus(product);
		
	}

	@Override
	public void deleteProduct(int productNumber) throws Exception {
		productMapper.deleteProduct(productNumber);
		
	}

	@Override
	public void deleteProductImage(int imageNumber) throws Exception {
		productMapper.deleteProductImage(imageNumber);		
	}

}