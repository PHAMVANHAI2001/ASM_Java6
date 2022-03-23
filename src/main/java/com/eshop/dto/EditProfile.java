package com.eshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditProfile implements Serializable {
    @NotBlank(message = "{NotBlank.user.email}")
    @Email(message = "{Email.user.email}")
    private String email;
    @NotBlank(message = "{NotBlank.user.fullname}")
    private String fullname;
    @NotBlank(message = "{NotBlank.user.phoneNumber}")
    private String phoneNumber;
    @NotBlank(message = "{NotBlank.user.address}")
    private String address;
    private String photo;
    private MultipartFile filePhoto;
}
