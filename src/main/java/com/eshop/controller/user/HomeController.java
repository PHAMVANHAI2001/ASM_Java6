package com.eshop.controller.user;

import com.eshop.entities.Product;
import com.eshop.jpaRepository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    ProductDAO productDAO;
 
    @RequestMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String paginate(Model model, @RequestParam("p") Optional<Integer> p) {
        // trang thứ 2
        // mỗi trang 5 sản phẩm
        Pageable pageable = PageRequest.of(p.orElse(0), 8);
        Page<Product> page = productDAO.findAll(pageable);
        model.addAttribute("page", page);
        return "site/layout/index";
    }
}
