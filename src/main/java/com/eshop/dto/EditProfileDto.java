package com.eshop.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditProfileDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1381151869578685256L;
	@Id
	private String username;
	@NotBlank(message = "{NotBlank.user.email}")
	@Email(message = "{Email.user.email}")
	private String email;
	@NotEmpty(message = "{NotBlank.user.fullname}")
	private String fullname;
	@NotBlank(message = "{NotBlank.user.phoneNumber}")
	private String phoneNumber;
	@NotBlank(message = "{NotBlank.user.address}")
	private String address;
	private String photo;
	private MultipartFile filePhoto;

}
