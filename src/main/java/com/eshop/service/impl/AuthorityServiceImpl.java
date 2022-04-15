package com.eshop.service.impl;

import com.eshop.entities.Authority;
import com.eshop.entities.User;
import com.eshop.jpaRepository.AuthorityRepository;
import com.eshop.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService{
	@Autowired 
	AuthorityRepository authorityRepo;
	
	@Override
	public List<Authority> findByUser(User user) {
		return authorityRepo.findByUser(user);
	}

	@Override
	public Authority save(Authority authority) {
		return authorityRepo.saveAndFlush(authority);
	}

	@Override
	public List<Authority> getAllCustomer() {
		return authorityRepo.findAllByRoleId(1);
	}

}
