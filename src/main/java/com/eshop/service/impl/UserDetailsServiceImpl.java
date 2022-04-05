package com.eshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eshop.entities.Authority;
import com.eshop.entities.User;
import com.eshop.service.AuthorityService;
import com.eshop.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserService userService;

	@Autowired
	AuthorityService authorityService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userRequest = userService.findByUsername(username);
		if (userRequest == null) {
			System.out.println("Không tìm thấy người dùng có username: " + username);
			throw new UsernameNotFoundException("Không tìm thấy người dùng có username: " + username);
		}
		String password = userRequest.getPassword();
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		List<Authority> authorities = authorityService.findByUser(userRequest);
		for (Authority auth : authorities) {
			GrantedAuthority authority = new SimpleGrantedAuthority(auth.getRole().getName());
			grantList.add(authority);
		}
		UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(username,password,grantList);
		return userDetails;
	}

}
