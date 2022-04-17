package com.eshop.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eshop.entities.Product;
import com.eshop.jpaRepository.ProductRepository;
import com.eshop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
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
		Optional<Product> optional = repo.findById(Id); // fix loi NullPoiterException
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

	@Override
	public List<Product> searchProductsByKeyword(String keyword) {
		return repo.findByNameContainingAllIgnoreCase(keyword);
	}

	@Override
	@Transactional
	public void deleteLogical(Integer productId) {
		repo.deleteLogical(productId);
	}

	@Override
	public void activeProduct(Integer productId) {
		repo.activeProduct(productId);
	}

	@Override
	@Transactional
	public Product save(Product product) {
		if (repo.existsBySlug(product.getSlug())) {
			Product productUpdate = repo.findBySlug(product.getSlug());
			productUpdate.setName(product.getName());
			productUpdate.setUnitPrice(product.getUnitPrice());
			productUpdate.setQuantity(product.getQuantity());
			productUpdate.setCategory(product.getCategory());
			productUpdate.setDiscount(product.getDiscount());
			productUpdate.setDescription(product.getDescription());
			productUpdate.setImage(product.getImage());
			return repo.saveAndFlush(productUpdate);
		}else{
			product.setAvailable(0);
			product.setCreatedDate(new Date());
			product.setImagePreview1(null);
			product.setImagePreview2(null);
			product.setImagePreview3(null);
			return repo.saveAndFlush(product);
		}
	}

	@Override
	public Boolean existsBySlug(String slug) {
		return repo.existsBySlug(slug);
	}
}
