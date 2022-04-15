package com.eshop.controller.user;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eshop.constant.SessionConstant;
import com.eshop.dto.RegisterDto;
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
    
    @Autowired
	ModelMapper modelMapper;
 
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
	
	@PostMapping("login")
	public String doPostLogin(Model model, @ModelAttribute("userRequest") User userRequest) {
		User userReponse = userService.doLogin(userRequest.getUsername(), userRequest.getPassword());
		if (userReponse != null) {
			return "redirect:/home";
		} else {
			model.addAttribute("messageFailed", "Đăng nhập thất bại");
			return "site/user/login";
		}
	}
	
	@RequestMapping("logout")
	public String doGetLogout(HttpSession session) {
		session.removeAttribute(SessionConstant.CURRENT_USER);
		return "redirect:/home";
	}
	
	@RequestMapping("search/")
	public String searchByKeyword(Model model, @RequestParam("keyword") String keyword) {
		List<Product> products = productService.searchProductsByKeyword(keyword);
		model.addAttribute("products", products);
		return "site/search/page-search";
	}
	
	@GetMapping("register")
	public String doGetRegister(Model model) {
		model.addAttribute("userRegister", new RegisterDto());
		return "site/user/register";
	}
	
	@PostMapping("register")
	public String doPostRegister(Model model,@Validated @ModelAttribute("userRegister") RegisterDto registerDto,
			BindingResult errors, RedirectAttributes redirectAttribute) {
		try {
			if (errors.hasErrors()) {
				redirectAttribute.addFlashAttribute("failedMessage", "Đăng ký thất bại");
			}else {
				User userMapper = modelMapper.map(registerDto, User.class);
				User userReponse = userService.save(userMapper);
				if (userReponse != null) {
					redirectAttribute.addFlashAttribute("succeedMessage", "Đăng ký thành công");
				} else {
					redirectAttribute.addFlashAttribute("failedMessage", "Đăng ký thất bại");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "redirect:/register";
	}
}
