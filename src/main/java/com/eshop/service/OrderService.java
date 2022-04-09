package com.eshop.service;

import com.eshop.entities.Order;
import com.eshop.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
	Order save(User user,String address,String phoneNumber);
	List<Order> getOrder(Integer userId);
}
