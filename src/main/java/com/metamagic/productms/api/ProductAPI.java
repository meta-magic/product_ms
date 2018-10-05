package com.metamagic.productms.api;

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

import com.metamagic.productms.dto.ResponseBean;
import com.metamagic.productms.dto.VersionInfo;
import com.metamagic.productms.entity.Product;
import com.metamagic.productms.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductAPI {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private VersionInfo version;
	
	@PostMapping(value = "/")
	public ResponseEntity<ResponseBean> save(@RequestBody Product product)
	{
		System.out.println("Save Product "+new Date());
		return new ResponseEntity<ResponseBean>(new ResponseBean(version.getVersion(),  
																productService.save(product)), 
												HttpStatus.OK);
	}
	
	@GetMapping(value = "/version")
	public ResponseEntity<ResponseBean> version(){
		System.out.println("Product version "+new Date());
		return new ResponseEntity<ResponseBean>(new ResponseBean(version.getVersion(),version), HttpStatus.OK);
	}


	@GetMapping(value = "/catalogue")
	public ResponseEntity<ResponseBean> catalogue(){
		System.out.println("Product catalogue "+new Date());
		return new ResponseEntity<ResponseBean>(new ResponseBean(version.getVersion(),  
				productService.findall()), HttpStatus.OK);
	}
	
	@GetMapping(value = "/catalogue/{id}")
	public ResponseEntity<ResponseBean> cataloguebyId(@PathVariable ("id") Long id)
	{
		System.out.println("Product cataloguebyId "+new Date());
		Object reviews = productService.findByIdWithReview(id);
		String v = version.getVersion();
		if(reviews !=null){
			v = v +" > Product Review";
		}
		return new ResponseEntity<ResponseBean>(new ResponseBean(v, reviews),
																HttpStatus.OK);
	}



	
	
	
}
