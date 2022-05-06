package com.eshop.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eshop.entities.SinhVien;
import com.eshop.jpaRepository.SinhVienJpaRepo;

@Controller
public class SinhVienController {
	@Autowired
	SinhVienJpaRepo repo;
	@RequestMapping("/sinhvien")
	public String getSinhVien(Model model) {
		List<SinhVien> sinhvien = repo.findAll();
		model.addAttribute("sinhvien", sinhvien);
		model.addAttribute("sinhvienRequest", new SinhVien());
		return "site/sinhvien/sinhvien";
	}
	
	@RequestMapping("/sinhvien/edit")
	public String getSinhVien(Model model, @RequestParam("sinhvienId") String id) {
		SinhVien sv = repo.getById(Integer.valueOf(id));
		model.addAttribute("sinhvienRequest", sv);
		List<SinhVien> sinhvien = repo.findAll();
		model.addAttribute("sinhvien", sinhvien);
		return "site/sinhvien/sinhvien";
	}
}

