package com.eshop.controller.Admin;

import com.eshop.entities.User;
import com.eshop.jpaRepository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class UserManagermentController {
    @Autowired
    UserRepo userRepo;

    @RequestMapping("/admin/edit-user")
    public String editUsers() {
        return "admin/user/edit-user";
    }

    @RequestMapping("/admin/list-user")
    public String listUsers(Model model) {
        List<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "admin/user/list-user";
    }

    @RequestMapping("/admin/list-user/delete/{userId}")
    public String deleteUser(Model model, @PathVariable("userId") Integer userId) {
        userRepo.deleteById(userId);
        List<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "admin/user/list-user";
    }

    @RequestMapping("/admin/edit-user/{id}")
    public String uploadUser(Model model, @PathVariable("id") Optional<Integer> id) {
        User uploadUser = userRepo.findById(id.get()).get();
        model.addAttribute("username", uploadUser.getUsername());
        model.addAttribute("password", uploadUser.getPassword());
        model.addAttribute("email", uploadUser.getEmail());
        model.addAttribute("phoneNumber", uploadUser.getPhoneNumber());
        model.addAttribute("address", uploadUser.getAddress());
        model.addAttribute("fullname", uploadUser.getFullname());
        model.addAttribute("isAdmin", uploadUser.getIsAdmin());
        model.addAttribute("photo", uploadUser.getPhoto());
        return "admin/user/edit-user";
    }
}
