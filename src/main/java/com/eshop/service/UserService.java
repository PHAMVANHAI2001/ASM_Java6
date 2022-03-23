package com.eshop.service;

import com.eshop.dto.EditProfile;
import com.eshop.dto.Register;
import com.eshop.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User doLogin(String username,String password, boolean isRemenber);
    Register doRegister(Register register);
    EditProfile doGetProfile(EditProfile editProfile);
}
