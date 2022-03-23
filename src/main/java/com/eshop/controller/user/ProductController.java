package com.eshop.controller.user;

import com.eshop.entities.Product;
import com.eshop.jpaRepository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductDAO productDAO;

    @RequestMapping("/product-list")
    public String allProduct(Model model, @RequestParam("p") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.orElse(0), 6);
        Page<Product> page = productDAO.findAll(pageable);
        model.addAttribute("pageProducts", page);
        return "product/product-list";
    }

    @RequestMapping("/product-list/{slug}")
    public String productCategory(Model model, @PathVariable String slug) {
        List<Product> products = productDAO.findByCategorySlug(slug);
        model.addAttribute("products", products);
        return "product/product-list";
    }

    @RequestMapping("/product-detail/{slug}")
    public String viewDetail(Model model, @PathVariable("slug") String slug) {
        Product product = productDAO.findBySlug(slug);
        model.addAttribute("product", product);
        return "product/product-detail";
    }
}
