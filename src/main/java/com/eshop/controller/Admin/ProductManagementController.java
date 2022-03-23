package com.eshop.controller.Admin;

import com.eshop.entities.Product;
import com.eshop.jpaRepository.ProductDAO;
import com.eshop.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProductManagementController {
    @Autowired
    ProductDAO productDAO;

    @Autowired
    ParamService paramService;

    @RequestMapping("/admin/list-product")
    public String listProduct(Model model) {
        List<Product> products = productDAO.findAll();
        model.addAttribute("products", products);
        return "admin/product/list-product";
    }

    @RequestMapping("/admin/edit-product")
    public String addProduct() {
        return "admin/product/edit-product";
    }

    @RequestMapping("/admin/edit-product/{slug}")
    public String editProduct(Model model, @PathVariable("slug") String slug) {
        if (productDAO.findBySlug(slug) != null) {
            Product editProduct = productDAO.findBySlug(slug);
            model.addAttribute("editProduct", editProduct);
//            editProduct.setName(paramService.getString("name", ""));
//            editProduct.setSlug(paramService.getString("slug", ""));
//            editProduct.setQuantity(paramService.getInt("quantity", 0));
//            editProduct.setUnitPrice(paramService.getDouble("unitPrice", 0));
//            editProduct.setDescription(paramService.getString("description", ""));
        }
        return "admin/product/edit-product";
    }
}
