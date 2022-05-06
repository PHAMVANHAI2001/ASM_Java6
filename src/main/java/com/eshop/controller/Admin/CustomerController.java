package com.eshop.controller.Admin;

import com.eshop.entities.User;
import com.eshop.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eshop.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/dashboard/")
public class CustomerController {
	@Autowired
	UserService userService;
	@Autowired
	AuthorityService authorityService;

	@RequestMapping("customer")
	public String getPageCustomer(Model model) {
		List<User> customers = userService.getCustomers();
		model.addAttribute("users", customers);
		return "site/admin/customer/manager-customer";
	}

	@RequestMapping("customer/disabled")
	public String doPostDisabled(@RequestParam("username") String username) {
		try {
			userService.disabled(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/dashboard/customer";
	}

	@RequestMapping("customer/enabled")
	public String doPostEnabled(@RequestParam("username") String username) {
		try {
			userService.enabled(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/dashboard/customer";
	}
}
