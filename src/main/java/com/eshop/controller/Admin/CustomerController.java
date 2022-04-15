package com.eshop.controller.Admin;

import com.eshop.entities.User;
import com.eshop.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eshop.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/")
public class CustomerController {
	@Autowired 
	UserService userService;
	@Autowired
	AuthorityService authorityService;
	@RequestMapping("customer")
	public String getPageCustomer(Model model) {
//		List<AuthorityController> users = authorityService.getAllCustomer();
//		model.addAttribute("users", users);
		return "site/admin/customer/manager-customer";
	}
}
