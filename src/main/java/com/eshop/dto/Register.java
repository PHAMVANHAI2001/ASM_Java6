package com.eshop.dto;

import com.eshop.annotationCustom.ValidateConfirmPassword;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ValidateConfirmPassword
public class Register implements Serializable {
    @Id
    @NotBlank(message = "{NotBlank.user.username}")
    private String username;
    @NotBlank(message = "{NotBlank.user.fullname}")
    private String fullname;
    @NotBlank(message = "{NotBlank.user.password}")
    private String password;
    @NotBlank(message = "{NotBlank.user.confirmPassword}")
    private String confirmPassword;
    @NotBlank(message = "{NotBlank.user.email}")
    @Email(message = "{Email.user.email}")
    private String email;
    @NotBlank(message = "{NotBlank.user.phoneNumber}")
    private String phoneNumber;
}
