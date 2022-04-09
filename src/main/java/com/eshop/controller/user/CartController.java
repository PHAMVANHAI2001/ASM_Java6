package com.eshop.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eshop.constant.SessionConstant;
import com.eshop.entities.OrderDetail;
import com.eshop.entities.Product;
import com.eshop.entities.User;
import com.eshop.service.CartService;
import com.eshop.service.OrderService;
import com.eshop.service.ProductService;
import com.eshop.service.UserService;

@Controller
@RequestMapping("/")
public class CartController {
	@Autowired
	CartService cartService;
	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;
	@Autowired 
	OrderService orderService;

	@RequestMapping("cart")
	public String getCart(Model model,HttpSession session) {
		if (cartService.findAll().size() < 1) {
			return "site/cart/cartEmpty";
		}
		User currentUser = (User) session.getAttribute(SessionConstant.CURRENT_USER);
		model.addAttribute("customerInfo", currentUser);
		return "site/cart/cart";
	}

	@RequestMapping("cart/add/{productSlug}")
	public String updateCart(Model model, @PathVariable String productSlug, HttpSession session) {
		Product productBuy = productService.findBySlug(productSlug);
		User currentUser = (User) session.getAttribute(SessionConstant.CURRENT_USER);
		cartService.updateCart(currentUser, productBuy);
		return "redirect:/home";
	}

	@RequestMapping("cart/remove/{cartId}")
	public String removeCart(Model model, @PathVariable String cartId) {
		cartService.deleteCart(Integer.valueOf(cartId));
		if (cartService.findAll().size() < 1) {
			return "site/cart/cartEmpty";
		}
		return "redirect:/cart";
	}
	
	@PostMapping("cart/order")
	public String orderCart(@ModelAttribute("customerInfo") User customerInfo, Model model, HttpSession session) {
		User currentUser = (User) session.getAttribute(SessionConstant.CURRENT_USER);
		
		orderService.save(currentUser, customerInfo.getAddress(), customerInfo.getPhoneNumber());
		return "";
	}
	
	@RequestMapping("order-history")
	public String getOrderHistory() {
		return "site/order/order-history";
	}
}
