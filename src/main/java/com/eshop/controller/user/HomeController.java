package com.eshop.controller.user;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eshop.constant.SessionConstant;
import com.eshop.entities.Product;
import com.eshop.entities.User;
import com.eshop.jpaRepository.ProductRepository;
import com.eshop.service.ProductService;
import com.eshop.service.UserService;

@Controller
public class HomeController {
    @Autowired
    ProductRepository productDAO;
    
    @Autowired
    ProductService productService;
    
    @Autowired
    UserService userService;
 
    @RequestMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String paginate(Model model, @RequestParam("p") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.orElse(0), 8);
        Page<Product> page = productService.findAll(pageable);
        model.addAttribute("page", page);
        return "site/home/home";
    }
    
	@GetMapping("login")
	public String doGetLogin(Model model) {
		model.addAttribute("userRequest", new User());
		return "site/user/login";
	}
	
//	@PostMapping("login")
//	public String doPostLogin(Model model, @ModelAttribute("userRequest") User userRequest, HttpSession session) {
//		User userReponse = userService.doLogin(userRequest.getUsername(), userRequest.getPassword());
//		if (userReponse != null) {
//			session.setAttribute(SessionConstant.CURRENT_USER, userReponse);
//			return "redirect:/home";
//		} else {
//			model.addAttribute("messageFailed", "Đăng nhập thất bại");
//			return "redirect:/login";
//		}
//	}
	
	@RequestMapping("logout")
	public String doGetLogout(HttpSession session) {
		session.removeAttribute("currentUser");
		return "redirect:/home";
	}
}
