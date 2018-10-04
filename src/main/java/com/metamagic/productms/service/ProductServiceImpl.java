package com.metamagic.productms.service;

import java.util.Arrays;
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
import com.metamagic.productms.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private RestTemplate restTemplate;
	

	@Autowired 
	ProductRepository productRepo;
	
	@Override
	public Product save(Product product) {
		return productRepo.save(product);
	}

	@Override
	public List<Product> findall() {
		List<Product> products = productRepo.findAll();
		if(products.size()==0){
			this.addDefaultData();
		}
		return products;
	}

	@Override
	public Product findById(Long id) {
		return productRepo.findById(id);
	}
	
	@Override
	public ProductDto findByIdWithReview(Long id) {
		Product product = productRepo.findById(id);
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
}
