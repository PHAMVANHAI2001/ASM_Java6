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
		product.setAvailable(0);
		product.setCreatedDate(new Date());
		return repo.saveAndFlush(product);
	}

	private void uploadFile(Product product, MultipartFile photoFile) {
		if (photoFile.getOriginalFilename() != null && !photoFile.getOriginalFilename().isBlank()) {
			try {
				String path = new File("src/main/resources/static/assets/user/img/avatar/").getAbsolutePath();

				String fileName = product.getSlug() + "." + photoFile.getContentType().split("/")[1];
				product.setImage(fileName);
				photoFile.transferTo(new File(path + "/" + fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Boolean existsBySlug(String slug) {
		// TODO Auto-generated method stub
		return repo.existsBySlug(slug);
	}
}
