package com.eshop.controller.Admin;

import com.eshop.entities.Order;
import com.eshop.jpaRepository.OrderRepository;
import com.eshop.service.OrderService;
import com.eshop.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AdminController {
	@Autowired
	OrderService orderService;

	@RequestMapping("/dashboard")
    public String admin(Model model) {
		double totalRevenue = orderService.getTotalRevenue();
    	model.addAttribute("totalRevenue", totalRevenue);
        return "site/admin/layout/main";
    }

}
