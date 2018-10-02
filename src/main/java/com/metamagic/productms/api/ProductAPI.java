package com.metamagic.productms.api;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.productms.dto.ProductDto;
import com.metamagic.productms.entity.Product;
import com.metamagic.productms.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductAPI {

	@Autowired
	private ProductService productService;
	
	@PostMapping(value = "/")
	public ResponseEntity<Product> save(@RequestBody Product product)
	{
		System.out.println("Save Product "+new Date());
		return new ResponseEntity<Product>(productService.save(product), HttpStatus.OK);
	}
	
	@GetMapping(value = "/catalogue")
	public ResponseEntity<Collection<Product>> catalogue(){
		System.out.println("Product catalogue "+new Date());
		return new ResponseEntity<Collection<Product>>(productService.findall(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/catalogue/{id}")
	public ResponseEntity<ProductDto> cataloguebyId(@PathVariable ("id") Long id)
	{
		System.out.println("Product cataloguebyId "+new Date());
		return new ResponseEntity<ProductDto>(productService.findByIdWithReview(id), HttpStatus.OK);
	}


	
	
	
}
