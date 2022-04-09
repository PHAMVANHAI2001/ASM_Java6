package com.eshop.service.impl;

import java.util.Date;
import java.util.List;

import javax.mail.FetchProfile.Item;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.entities.Cart;
import com.eshop.entities.Product;
import com.eshop.entities.User;
import com.eshop.jpaRepository.CartRepository;
import com.eshop.service.CartService;
import com.eshop.service.UserService;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	CartRepository cartRepo;

	@Autowired
	UserService userService;

	@Override
	public List<Cart> findByUserId(Integer id) {
		return cartRepo.findByUserId(id);
	}

	@Override
	public List<Cart> findAll() {
		// TODO Auto-generated method stub
		return cartRepo.findAll();
	}

	@Override
	@Transactional
	public Cart updateCart(User userId, Product productId) {
		Cart cart = null;
		if (!cartRepo.existsCartByUserIdAndProductId(userId.getId(), productId.getId())) {
			cart = new Cart();
			cart.setUser(userId);
			cart.setProduct(productId);
			cart.setQuantity(1);
			cart.setCreatedDate(new Date());
			cartRepo.saveAndFlush(cart);
		} else {
			cart = cartRepo.findByProductId(productId.getId());
			cart.setQuantity(cart.getQuantity() + 1);
			cart.setCreatedDate(new Date());
			cartRepo.saveAndFlush(cart);
		}
		return cart;
	}

	@Override
	@Transactional
	public Cart updateCart(Cart cartId, Product productId, Integer quantity, Boolean isReplace) {
		Cart cart = null;
		User userId = userService.getCurrentUser();
		if (!cartRepo.existsCartByUserIdAndProductId(userId.getId(), productId.getId())) {
			cart = new Cart();
			cart.setUser(userId);
			cart.setProduct(productId);
			cart.setQuantity(quantity);
			cart.setCreatedDate(new Date());
			cartRepo.saveAndFlush(cart);
		} else if (quantity > 0) {
			if (isReplace) {
				cart = cartRepo.getById(cartId.getId());
				cart.setQuantity(quantity);
				cart.setCreatedDate(new Date());
				cartRepo.saveAndFlush(cart);
			} else {
				cart = cartRepo.findByProductId(productId.getId());
				cart.setQuantity(cart.getQuantity() + quantity);
				cart.setCreatedDate(new Date());
				cartRepo.saveAndFlush(cart);
			}
		}
		return cart;
	}

	@Override
	public Double getTotalPrice() {
		List<Cart> list = cartRepo.findByUserId(userService.getCurrentUser().getId());
		double totalPrice = 0;
		for (Cart item : list) {
			totalPrice += item.getProduct().getUnitPrice() * item.getQuantity();
		}
		return totalPrice;
	}

	@Override
	public Cart updateQuantity(Integer cartId, Integer quantity) {
		Cart cart = cartRepo.getById(cartId);
		if (cart == null) {
			throw new RuntimeException("No cart exists");
		}
		cart.setQuantity(quantity);
		return cartRepo.saveAndFlush(cart);
	}

	@Override
	public void deleteCart(Integer id) {
		cartRepo.deleteById(Integer.valueOf(id));
	}
	
	@Override
	public void deleteAll() {
		cartRepo.deleteAll();
	}
}
