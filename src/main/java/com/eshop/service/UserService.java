package com.eshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eshop.dto.EditProfileDto;
import com.eshop.entities.User;

@Service
public interface UserService {
	User findByUsername(String username);
    User doLogin(String username,String password);
//    EditProfileDto doGetProfile(String username);
    User save(User user);
    User getCurrentUser();
    void changePassword(String oldPassword, String newPassword, String confirmPassword);
    void editProfile(User user);
    Boolean existsByEmail(String email);
    User findByEmail(String email);
    
    List<User> getAdministrators();
    
    List<User> getCustomers();
    
    void disabled(String username);
    
    void enabled(String username);
}
