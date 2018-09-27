package com.metamagic.productms.service;

import java.util.List;

import com.metamagic.productms.dto.ProductDto;
import com.metamagic.productms.entity.Product;

public interface ProductService {

	public Product save(Product product);
	
	public List<Product> findall();
	
	public Product findById(Long id);
	
	public ProductDto findByIdWithReview(Long id);
		
}
