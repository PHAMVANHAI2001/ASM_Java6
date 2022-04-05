package com.eshop.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eshop.entities.Cart;
import com.eshop.entities.Product;
import com.eshop.service.CartService;

@Controller
@RequestMapping("/")
public class CartController {
	@Autowired
	CartService cartService;
	
	@RequestMapping("cart")
	public String getCart() {
		if(cartService.findAll().size() < 1) {
			return "site/cart/cartEmpty";
		}
		return "site/cart/cart";
	}
	
	@RequestMapping("cart/add/{slug}")
	public String addCart(Model model, @PathVariable String slug) {
//		String username = sessionService.get("username", null);
//		if (username == null) {
//			return "redirect:site/user/login";
//		}
//		User user = userRepo.findByUsername(username);
//		Product productAdd = productDAO.findBySlug(slug);
//		if (!cartDAO.existsShoppingCartByUserIdAndProductId(user.getId(), productAdd.getId())) {
//			Cart cart = new Cart();
//			cart.setUser(user);
//			cart.setProduct(productAdd);
//			cart.setQuantity(1);
//			cart.setCreatedDate(new Date());
//			cartDAO.save(cart);
//		} else {
//			Cart cart = cartDAO.findByProductId(productAdd.getId());
//			cart.setQuantity(cart.getQuantity() + 1);
//			cart.setCreatedDate(new Date());
//			cartDAO.flush();
//		}
//		List<Cart> cartList = cartDAO.findByUserId(user.getId());
//		model.addAttribute("cartList", cartList);
//		model.addAttribute("totalUnitPrice", getTotalPrice(cartList));
		return "site/user/shopping-cart";
	}
}
