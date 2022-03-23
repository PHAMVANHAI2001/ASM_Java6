package com.eshop.service.impl;

import com.eshop.dto.EditProfile;
import com.eshop.dto.Register;
import com.eshop.entities.User;
import com.eshop.jpaRepository.UserRepo;
import com.eshop.service.CookieService;
import com.eshop.service.ParamService;
import com.eshop.service.SessionService;
import com.eshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    CookieService cookieService;
    @Autowired
    ParamService paramService;
    @Autowired
    SessionService sessionService;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public User doLogin(String username, String password, boolean isRemenber) {
        User user = userRepo.findByUsernameAndPassword(username, password);
        if (user != null) {
            sessionService.set("username", username);
            if (isRemenber == true) {
                cookieService.add("username", username, 365 * 24); // tạo cookie tên username tồn tại 365 ngày
            } else {
                cookieService.remove("username");
            }
            return user;
        }
        return null;
    }

    @Override
    public Register doRegister(Register register) {
        User newUser = modelMapper.map(register, User.class);
        boolean isExistUsernameOrEmail = userRepo.existsByUsernameOrEmail(register.getUsername(), register.getEmail());
        if (isExistUsernameOrEmail) {
            return null;
        } else {
            newUser.setAddress("");
            newUser.setPhoto("");
            newUser.setCreatedDate(new Date());
            newUser.setIsAdmin(false);
            userRepo.save(newUser);
            return register;
        }
    }

    @Override
    public EditProfile doGetProfile(EditProfile editProfile) {
        String username = sessionService.get("username", null);
        User user = userRepo.findByUsername(username);
        editProfile = modelMapper.map(user, EditProfile.class);
        System.out.println(editProfile.getPhoto());
        return editProfile;
    }
}
