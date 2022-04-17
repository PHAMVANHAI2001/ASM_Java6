package com.eshop.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.eshop.constant.SessionConstant;
import com.eshop.dto.EditProfileDto;
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

	@Override
	public EditProfileDto doGetProfile(String username) {
		User userReponse = userRepo.findByUsername(username);
		EditProfileDto userProfile = modelMapper.map(userReponse, EditProfileDto.class);
		return userProfile;
	}

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
		if(username == null) {
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
		if(isCheckPassword) {
			if(newPassword.equals(oldPassword)) {
				System.out.println("Mật khẩu mới không được trùng với mật khẩu cũ!");
			}else {
				if(confirmPassword.equals(newPassword)) {
					System.out.println("Đổi mật khẩu thành công!");
					String newHashPassword = bcrypt.encode(newPassword);
					currentUser.setPassword(newHashPassword);
					userRepo.saveAndFlush(currentUser);
				}else {
					System.out.println("Mật khẩu xác nhận sai!");
				}
			}
		}else {
			System.out.println("Mật khẩu cũ không đúng!");
		}
	}

	@Override
	public void editProfile(EditProfileDto user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean existsByEmail(String email) {
		return userRepo.existsByEmail(email);
	}

	@Override
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
}
