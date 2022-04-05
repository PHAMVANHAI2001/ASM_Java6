package com.eshop.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eshop.dto.UserProfile;
import com.eshop.entities.Authority;
import com.eshop.entities.User;
import com.eshop.jpaRepository.UserRepository;
import com.eshop.service.AuthorityService;
import com.eshop.service.CookieService;
import com.eshop.service.ParamService;
import com.eshop.service.RoleService;
import com.eshop.service.SessionService;
import com.eshop.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	
	@Autowired
	UserRepository userRepo;
	@Autowired
	AuthorityService authorityService;
	@Autowired
	RoleService roleService;

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
			return checkPassword ? user : null;
		} else {
			return null;
		}
	}

	@Override
	public UserProfile doGetProfile(String username) {
		User userReponse = userRepo.findByUsername(username);
		UserProfile userProfile = modelMapper.map(userReponse, UserProfile.class);
		return userProfile;
	}

	@Override
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	//OK
	@Override
	@Transactional
	public User save(User user) {
		String hashPassword = bcrypt.encode(user.getPassword());
		user.setPassword(hashPassword);
		user.setEnabled(Boolean.TRUE);
		user.setCreatedDate(new Date());
		userRepo.saveAndFlush(user);
		Authority userAuthority = new Authority();
		userAuthority.setRole(roleService.findById(1));
		userAuthority.setUser(user);
		authorityService.save(userAuthority);
		return user;
	}
}
