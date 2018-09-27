package com.metamagic.productms.dto;

import com.metamagic.productms.entity.Product;

public class ProductDto extends Product{

	private Object reviews;

	public ProductDto(Long productId, String category, String name, String description, Double price,Object reviews) {
		super(productId, category, name, description, price);
		this.reviews = reviews;
		
	}

	public Object getReviews() {
		return reviews;
	}

	
	
	
}
