package com.eshop.service;

import org.springframework.stereotype.Service;

import com.eshop.dto.UserProfile;
import com.eshop.entities.User;

@Service
public interface UserService {
	User findByUsername(String username);
    User doLogin(String username,String password);
    UserProfile doGetProfile(String username);
    User save(User user);
}
