package com.eshop.service;

import com.eshop.entities.Authority;
import com.eshop.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorityService {
	List<Authority> findByUser(User user);
}
