package com.eshop.service;

import org.springframework.stereotype.Service;

import com.eshop.entities.Role;

@Service
public interface RoleService {
	Role findById(Integer id);
}
