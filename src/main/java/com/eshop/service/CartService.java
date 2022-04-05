package com.eshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eshop.entities.Cart;
import com.eshop.entities.User;

@Service
public interface CartService {
	List<Cart> findByUserId(Integer id);
	List<Cart> findAll();
}
