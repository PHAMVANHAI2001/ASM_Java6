package com.eshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eshop.entities.Product;
import com.eshop.jpaRepository.ProductRepository;
import com.eshop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductRepository repo;
	
	@Override
	public List<Product> findAll() {
		return repo.findAll();
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public Product findById(Integer Id) {
		Optional<Product> optional = repo.findById(Id); // fix noi NullPoiterException
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	public List<Product> findByCategorySlug(String slug) {
		return repo.findByCategorySlug(slug);
	}
	
	

	@Override
	public Product findBySlug(String slug) {
		// TODO Auto-generated method stub
		return repo.findBySlug(slug);
	}

	@Override
	public Page<Product> findByProductOfCategory(String category, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findByCategorySlug(category, pageable);
	}
}
