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
public class ManagerProduct implements Serializable {
    private String name;
    private String slug;
    private MultipartFile image;
    private MultipartFile imagePreview1;
    private MultipartFile imagePreview2;
    private MultipartFile imagePreview3;
    private String description;
    private int quantity;
    private float unitPrice;
    private int categoryId;
    private int discountId;
}
