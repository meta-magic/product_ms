package com.metamagic.productms.repository;

import java.util.List;

import com.metamagic.productms.entity.Product;

public interface ProductRepository {

	public Product save(Product product);

	public List<Product>  findAll();

	public Product findById(Long productId);
}
