package com.eshop.service.impl;

import com.eshop.dto.EditProfile;
import com.eshop.dto.UserRegister;
import com.eshop.entities.User;
import com.eshop.jpaRepository.UserRepository;
import com.eshop.service.CookieService;
import com.eshop.service.ParamService;
import com.eshop.service.SessionService;
import com.eshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
	private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	CookieService cookieService;
	@Autowired
	ParamService paramService;
	@Autowired
	SessionService session;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public User doLogin(String username, String password) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			String hashPass = user.getPassword();
			boolean checkPassword = bcrypt.matches(password, hashPass);
//			sessionService.set("user", user);
			return checkPassword == true ? user : null;
		} else {
			return null;
		}
	}

	@Override
	public UserRegister doRegister(UserRegister register) {
		User newUser = modelMapper.map(register, User.class);
		boolean isExistUsernameOrEmail = userRepo.existsByUsernameOrEmail(register.getUsername(), register.getEmail());
		if (isExistUsernameOrEmail) {
			return null;
		} else {
			newUser.setAddress("");
			newUser.setPhoto("");
			newUser.setCreatedDate(new Date());
			userRepo.save(newUser);
			return register;
		}
	}

	@Override
	public EditProfile doGetProfile(EditProfile editProfile) {
		User userReponse = session.get("currentUser");
		editProfile = modelMapper.map(userReponse, EditProfile.class);
		return editProfile;
	}

	@Override
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}
}
