package com.eshop.controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eshop.service.OrderService;
import com.eshop.service.StatsService;

@Controller
public class DashboardController {
	@Autowired
	OrderService orderService;
	@Autowired 
	StatsService statsService;
	
	@RequestMapping("/dashboard")
    public String admin(Model model) {
		String[][] chartData = statsService.getTotalPriceLast12Months();
		model.addAttribute("chartData",chartData);
		double totalRevenue = orderService.getTotalRevenue();
    	model.addAttribute("totalRevenue", totalRevenue);
    	model.addAttribute("totalNewOrders", orderService.getTotalNewOrders());
    	model.addAttribute("totalOrders", orderService.getTotalOrders());
    	model.addAttribute("totalOrdersDelivered", orderService.getOrdersDelivered());
        return "site/admin/layout/main";
    }

}
