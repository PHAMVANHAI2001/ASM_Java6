package com.eshop.controller.Admin;

import com.eshop.dto.ManagerUser;
import com.eshop.entities.User;
import com.eshop.jpaRepository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin")
public class UserControllerAPI {
    @Autowired
    UserRepo userRepo;
    @Autowired
    private ServletContext app;

    @RequestMapping("/user/save")
    public ResponseEntity editUser(Model model, @ModelAttribute ManagerUser managerUser) throws IOException {
        if (userRepo.existsByUsername(managerUser.getUsername())) {
            User updateUser = userRepo.findByUsername(managerUser.getUsername());
            updateUser.setPassword(managerUser.getPassword());
            updateUser.setFullname(managerUser.getFullname());
            updateUser.setPhoneNumber(managerUser.getPhoneNumber());
            updateUser.setAddress(managerUser.getAddress());
            if (managerUser.getIsAdmin() != null) {
                updateUser.setIsAdmin(true);
            } else {
                updateUser.setIsAdmin(false);
            }
            this.uploadPhoto(updateUser, managerUser.getPhoto());
            userRepo.save(updateUser);
            model.addAttribute("messageFailed", "Cập nhật thông tin người dùng thành công");
        } else {
            if (userRepo.existsByEmail(managerUser.getEmail())) {
                throw new RuntimeException("Email đã tồn tại");
            }else{
                User newUser = new User();
                newUser.setUsername(managerUser.getUsername());
                newUser.setPassword(managerUser.getPassword());
                newUser.setFullname(managerUser.getFullname());
                newUser.setEmail(managerUser.getEmail());
                newUser.setPhoneNumber(managerUser.getPhoneNumber());
                newUser.setAddress(managerUser.getAddress());
                if (managerUser.getIsAdmin() != null) {
                    newUser.setIsAdmin(true);
                } else {
                    newUser.setIsAdmin(false);
                }
                this.uploadPhoto(newUser, managerUser.getPhoto());
                newUser.setCreatedDate(new Date());
                userRepo.save(newUser);
                model.addAttribute("messageSuccess", "Thêm mới người dùng thành công");
            }
        }
        return null;
    }

    private void uploadPhoto(User user, MultipartFile filePhoto) throws IOException {
        if (filePhoto.getOriginalFilename() != null && filePhoto.getOriginalFilename().length() > 0) {
            try {
                String path = app.getRealPath("/assets/images/avatar/");
                String name = user.getUsername() + "." + Objects.requireNonNull(filePhoto.getContentType()).split("/")[1];
                user.setPhoto(name);
                filePhoto.transferTo(new File(path + name));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
