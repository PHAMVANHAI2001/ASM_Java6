package com.eshop.controller.Admin;

import com.eshop.entities.Order;
import com.eshop.jpaRepository.OrderDAO;
import com.eshop.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    SessionService sessionService;
    @Autowired
    OrderDAO orderDAO;

    @RequestMapping("/admin")
    public String admin(Model model) {
//        String username = sessionService.get("username", null);
//        if (username == null) {
//            return "redirect:/user/login";
//        }
        List<Order> list = orderDAO.findAll();
        List<Order> newOrders = orderDAO.findAllByStatus(0);
        List<Order> orderDelivered = orderDAO.findAllByStatus(1);
        List<Order> totalOrder = orderDAO.findAll();
        long totalRevenue = 0;
        for(Order order : list) {
            totalRevenue += order.getTotalUnitPrice();
        }
        model.addAttribute("totalRevenue", totalRevenue);
        model.addAttribute("newOrders", newOrders.size());
        model.addAttribute("orderDelivered", orderDelivered.size());
        model.addAttribute("totalOrder", totalOrder.size());
        return "admin/layout/main";
    }


}
