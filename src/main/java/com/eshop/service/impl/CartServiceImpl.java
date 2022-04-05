package com.eshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.entities.Cart;
import com.eshop.jpaRepository.CartRepository;
import com.eshop.service.CartService;

@Service
public class CartServiceImpl implements CartService{
	@Autowired
	CartRepository cartRepo;

	@Override
	public List<Cart> findByUserId(Integer id) {
		return cartRepo.findByUserId(id);
	}

	@Override
	public List<Cart> findAll() {
		// TODO Auto-generated method stub
		return cartRepo.findAll();
	}


	

}
