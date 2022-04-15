package com.eshop.service.impl;

import com.eshop.entities.Cart;
import com.eshop.entities.Order;
import com.eshop.entities.OrderDetail;
import com.eshop.entities.User;
import com.eshop.jpaRepository.OrderDetailRepository;
import com.eshop.jpaRepository.OrderRepository;
import com.eshop.service.CartService;
import com.eshop.service.OrderService;
import com.eshop.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;
    @Autowired
    OrderRepository orderRepo;
    @Autowired
    OrderDetailRepository orderDetailRepo;

    @Override
    @Transactional
    public Order save(User user, String address, String phoneNumber) {
        Order newOrder = new Order();
        newOrder.setOrderCode(RandomStringUtils.randomAlphanumeric(11));
        newOrder.setUser(user);
        newOrder.setCreatedDate(new Date());
        newOrder.setStatus(0);
        newOrder.setFullname(user.getFullname());
        newOrder.setAddress(address);
        newOrder.setEmail(user.getEmail());
        newOrder.setPhoneNumber(phoneNumber);
        newOrder.setTotalUnitPrice(cartService.getTotalPrice());
        orderRepo.save(newOrder);

        List<Cart> carts = cartService.findByUserId(user.getId());
        carts.forEach(cart -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(newOrder);
            orderDetail.setProduct(cart.getProduct());
            orderDetail.setQuantity(cart.getQuantity());
            orderDetail.setTotalUnitPrice(cart.getQuantity() * cart.getProduct().getUnitPrice());
            orderDetailRepo.save(orderDetail);
        });

        cartService.deleteAll();
        return newOrder;
    }

    @Override
    public List<Order> getOrder(Integer userId) {
        List<Order> currentOrder = orderRepo.findAllByUserId(userId);
        return currentOrder;
    }

	@Override
	public Double getTotalRevenue() {
		List<Order> orders = orderRepo.findAll();
		double totalRevenue = 0 ;
		for(Order order : orders) {
			totalRevenue += order.getTotalUnitPrice();
		}
		return totalRevenue;
	}

	@Override
	public Integer getTotalNewOrders() {
		List<Order> newOrders = orderRepo.findAllByStatus(0);
		return newOrders.size();
	}

	@Override
	public Integer getTotalOrders() {
		List<Order> newOrders = orderRepo.findAll();
		return newOrders.size();
	}

	@Override
	public Integer getOrdersDelivered() {
		List<Order> newOrders = orderRepo.findAllByStatus(1);
		return newOrders.size();
	}
}
