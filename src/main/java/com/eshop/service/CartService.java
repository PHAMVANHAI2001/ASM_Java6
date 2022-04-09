package com.eshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eshop.entities.Cart;
import com.eshop.entities.Product;
import com.eshop.entities.User;

@Service
public interface CartService {
	List<Cart> findByUserId(Integer id);
	List<Cart> findAll();
	void deleteCart(Integer id);
	Cart updateQuantity(Integer cartId, Integer quantity);
	Cart updateCart(Cart cartId, Product productId, Integer quantity, Boolean isReplace);
	Cart updateCart(User userId, Product productId);
	Double getTotalPrice();
	void deleteAll();
}
