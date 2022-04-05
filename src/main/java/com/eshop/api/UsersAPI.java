package com.eshop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UsersAPI {
	@Autowired
	UserService userService;

	@GetMapping("/profile")
	public ResponseEntity<?> getProfile(Authentication auth, HttpRequest request) {
		String username = "";
		if (auth.isAuthenticated()) {
			username = auth.getName();
		} else {
			throw new RuntimeException("Username: " + username + " này không tồn tại");
		}
		return null;
	}
}
