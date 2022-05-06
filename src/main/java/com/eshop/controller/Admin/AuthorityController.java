package com.eshop.controller.Admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eshop.entities.Authority;
import com.eshop.entities.User;
import com.eshop.jpaRepository.UserRepository;
import com.eshop.service.AuthorityService;

@Controller 
@RequestMapping("/dashboard/")
public class AuthorityController {
	@Autowired 
	AuthorityService authorityService;
	
	@GetMapping("authority")
	public String doGetAuthority(Model model) {
		List<Authority> authorities = authorityService.findAll();
		model.addAttribute("authorities", authorities);
		return "site/admin/authority/manager-authorities";
	}
}
