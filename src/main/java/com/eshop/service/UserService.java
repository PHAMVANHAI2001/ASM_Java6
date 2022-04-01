package com.eshop.service;

import com.eshop.dto.EditProfile;
import com.eshop.dto.UserRegister;
import com.eshop.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
	User findByUsername(String username);
    User doLogin(String username,String password);
    UserRegister doRegister(UserRegister register);
    EditProfile doGetProfile(EditProfile editProfile);
}
