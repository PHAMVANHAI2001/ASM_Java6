package com.eshop.controller.Admin;

import com.eshop.entities.Category;
import com.eshop.jpaRepository.CategoryDAO;
import com.eshop.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoryController {
    @Autowired
    ParamService paramService;

    @Autowired
    CategoryDAO dao;

    @RequestMapping("/admin/category")
    public String category(){
        return "admin/category/edit-category";
    }

    @PostMapping("/admin/category/add")
    public String addCategory(Model model){
        String name = paramService.getString("name","");
        String slug = paramService.getString("slug","");
        Category newCategory = new Category();
        newCategory.setName(name);
        newCategory.setSlug(slug);
        dao.save(newCategory);
        return "admin/category/edit-category";
    }

    @RequestMapping("/admin/category/edit/{id}")
    public String editCategory(Model model, @PathVariable("id") Integer id){
        Category category = dao.findById(id).get();
        model.addAttribute("name",category.getName());
        model.addAttribute("slug",category.getSlug());
        category.setName(paramService.getString("name",""));
        category.setSlug(paramService.getString("slug",""));
        dao.save(category);
        return "admin/category/edit-category";
    }

    @RequestMapping("/admin/category/delete/{id}")
    public String deleteCategory(Model model, @PathVariable("id") Integer id){
        Category category = dao.findById(id).get();
        dao.deleteById(id);
        return "redirect:/admin/category";
    }
}
