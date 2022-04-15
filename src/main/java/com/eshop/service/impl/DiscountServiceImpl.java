package com.eshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.entities.Discount;
import com.eshop.jpaRepository.DiscountRepository;
import com.eshop.service.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService{
	@Autowired 
	DiscountRepository discountRepo;
	@Override
	public List<Discount> findAll() {
		// TODO Auto-generated method stub
		return discountRepo.findAll();
	}

}
