package com.eshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eshop.entities.Category;

@Service
public interface CategoryService {
	List<Category> findAll();
	
	Category findBySlug(String slug);
}
