package com.eshop.service;

import java.util.List;

import com.eshop.entities.Discount;

public interface DiscountService {
	List<Discount> findAll();
	Discount findById(Integer id);
}
