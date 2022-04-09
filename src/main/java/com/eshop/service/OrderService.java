package com.eshop.service;

import org.springframework.stereotype.Service;

import com.eshop.entities.Order;
import com.eshop.entities.User;

@Service
public interface OrderService {
	Order save(User user,String address,String phoneNumber);
}
