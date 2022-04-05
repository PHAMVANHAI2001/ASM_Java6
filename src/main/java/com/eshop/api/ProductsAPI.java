package com.eshop.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.entities.Product;
import com.eshop.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductsAPI {
	@Autowired
	ProductService productService;
	
	@GetMapping("/")
	public ResponseEntity<?> doGetAll(){
		List<Product> products = productService.findAll();
		return ResponseEntity.ok(products);
	}
}
