package com.eshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.entities.Role;
import com.eshop.jpaRepository.RoleRepository;
import com.eshop.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleRepository roleRepo;

	@Override
	public Role findById(Integer id) {
		return roleRepo.findById(id).get();
	}

}
