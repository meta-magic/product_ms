package com.metamagic.productms.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.metamagic.productms.dto.ProductDto;
import com.metamagic.productms.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private RestTemplate restTemplate;

	
 	

	@Override
	public List<Product> findall() {
		List<Product> products = this.products();
		return products;
	}

	@Override
	public Product findById(Long id) {
		List<Product> products = this.products();
		for (Iterator iterator = products.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			if(product.getProductId().equals(id)){
				return product;
			}
		}
		return products.get(1); 
	}
	
	@Override
	public ProductDto findByIdWithReview(Long id) {
		Product product = this.findById(id);
		Object reviews = this.getProduct(id);
		
		return new ProductDto(product.getProductId(), product.getCategory(), product.getName(), product.getDescription(), product.getPrice(), reviews);
	}

	private Object getProduct(Long id){
		try{
			org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity request = new HttpEntity(headers);
			ResponseEntity response = this.restTemplate.exchange("http://productreviewservice/productreviewms/productreview/"+id, HttpMethod.GET, request,
					Object.class);
			
			return response.getBody();
		}catch(Exception e){
			e.printStackTrace();
			System.err.println("Enable to retrive reviews "+e.getMessage());
		}
		return null;
	}
	
	private void addDefaultData(){
		this.save(new Product(Long.valueOf(1), "2", "Apple", "Iphone 10, 32 GB", 90000.00));
		this.save(new Product(Long.valueOf(2), "2", "Sony", "Vivo Mobile", 20000.00));
		
	}

	private List<Product> products(){
		List<Product> products = new ArrayList<>();
		products.add(new Product(Long.valueOf(2), "2", "Sony", "Vivo Mobile", 20000.00));
		products.add(new Product(Long.valueOf(1), "2", "Apple", "Iphone 10, 32 GB", 90000.00));
		return products;
	}

	@Override
	public Product save(Product product) {
		return new Product(Long.valueOf(1), "2", "Apple", "Iphone 10, 32 GB", 90000.00);
	}

	
}
