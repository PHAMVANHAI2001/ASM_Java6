package com.eshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eshop.entities.Product;

@Service
public interface ProductService {
	List<Product> findAll();

	Page<Product> findAll(Pageable pageable);

	Product findById(Integer Id);

	List<Product> findByCategorySlug(String slug);
	
	Page<Product> findByProductOfCategory(String category, Pageable pageable);
	
	Product findBySlug(String slug);
	
	List<Product> searchProductsByKeyword(String keyword);
	
	void deleteLogical(Integer productId);
	
	void activeProduct(Integer productId);
	
	Product save(Product product);
	
	Boolean existsBySlug(String slug);
}
