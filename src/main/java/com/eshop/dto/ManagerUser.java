package com.eshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ManagerUser implements Serializable {
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String phoneNumber;
    private String isAdmin;
    private String address;
    private MultipartFile photo;
}
