package com.eshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eshop.entities.Authority;
import com.eshop.entities.User;

@Service
public interface AuthorityService {
	List<Authority> findByUser(User user);
	Authority save(Authority authority);
}
