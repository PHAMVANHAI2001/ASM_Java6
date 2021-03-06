package com.eshop.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.eshop.constant.SessionConstant;
import com.eshop.dto.EditProfileDto;
import com.eshop.dto.UpdateProductDto;
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
		}
		return null;
	}

//	@Override
//	public EditProfileDto doGetProfile(String username) {
//		User userRequest = userRepo.findByUsername(username);
//		EditProfileDto userMapper = modelMapper.map(userRequest, EditProfileDto.class);
//		return userMapper;
//	}

	@Override
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	// OK
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

	public User getCurrentUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		if (username == null) {
			return null;
		}
		User currentUser = userRepo.findByUsername(username);
		return currentUser;
	}

	@Override
	public void changePassword(String oldPassword, String newPassword, String confirmPassword) {
		User currentUser = session.get(SessionConstant.CURRENT_USER);
		String hashPass = currentUser.getPassword();
		boolean isCheckPassword = bcrypt.matches(oldPassword, hashPass);
		if (isCheckPassword) {
			if (newPassword.equals(oldPassword)) {
				System.out.println("M???t kh???u m???i kh??ng ???????c tr??ng v???i m???t kh???u c??!");
			} else {
				if (confirmPassword.equals(newPassword)) {
					System.out.println("?????i m???t kh???u th??nh c??ng!");
					String newHashPassword = bcrypt.encode(newPassword);
					currentUser.setPassword(newHashPassword);
					userRepo.saveAndFlush(currentUser);
				} else {
					System.out.println("M???t kh???u x??c nh???n sai!");
				}
			}
		} else {
			System.out.println("M???t kh???u c?? kh??ng ????ng!");
		}
	}

	@Override
	public void editProfile(User userResponse) {
		userRepo.saveAndFlush(userResponse);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return userRepo.existsByEmail(email);
	}

	@Override
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public List<User> getAdministrators() {
		// TODO Auto-generated method stub
		return userRepo.getAdministrators();
	}

	@Override
	public List<User> getCustomers() {
		// TODO Auto-generated method stub
		return userRepo.getCustomers();
	}

	@Override
	public void disabled(String username) {
		userRepo.disabled(username);
	}

	@Override
	public void enabled(String username) {
		userRepo.enabled(username);
	}
}
