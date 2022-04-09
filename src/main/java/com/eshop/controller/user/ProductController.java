package com.eshop.controller.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eshop.entities.Product;
import com.eshop.service.ProductService;

@Controller
@RequestMapping("/")
public class ProductController {
	@Autowired
	ProductService productService;

	@RequestMapping("products")
	public String allProduct(Model model, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 6);
		Page<Product> page = productService.findAll(pageable);
		model.addAttribute("pageProducts", page);
		return "site/product/products";
	}

	@RequestMapping("products/{slug}")
	public String productCategory(Model model, @RequestParam("p") Optional<Integer> p, @PathVariable String slug) {
		Pageable pageable = PageRequest.of(p.orElse(0), 6);
		Page<Product> page = productService.findByProductOfCategory(slug, pageable);
		model.addAttribute("pageProducts", page);
		return "site/product/products";
	}

	@RequestMapping("product-details/{slug}")
	public String viewDetail(Model model, @PathVariable("slug") String slug) {
		Product product = productService.findBySlug(slug);
		model.addAttribute("product", product);
		return "site/product/product-details";
	}
}
