package com.eshop.api;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.service.CartService;
import com.eshop.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/carts")
public class CartAPI {
	@Autowired 
	CartService cartService;
	@Autowired 
	UserService userService;
	
	@GetMapping("/{cartId}/{quantity}")
	public ResponseEntity<?> updateQuantity (@PathVariable("cartId") Integer cartId,
			@PathVariable("quantity") Integer quantity) {
		DecimalFormat df = new DecimalFormat("###,###,###");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cart", cartService.updateQuantity(cartId,quantity));
		map.put("totalPrice", df.format(cartService.getTotalPrice()).replaceAll(",", "."));
		System.out.println(map.get("cart"));
		return ResponseEntity.ok(map);
	}
}
