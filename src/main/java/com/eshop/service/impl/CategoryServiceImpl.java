package com.eshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.entities.Category;
import com.eshop.jpaRepository.CategoryRepository;
import com.eshop.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryRepository categoryRepo;
	
	@Override
	public List<Category> findAll() {
		return categoryRepo.findAll();
	}

	@Override
	public List<Category> findBySlug(String slug) {
		return categoryRepo.findBySlug(slug);
	}

	
	
}
