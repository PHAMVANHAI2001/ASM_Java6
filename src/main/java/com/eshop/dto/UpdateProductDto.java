package com.eshop.dto;

import java.io.Serializable;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;

import com.eshop.entities.Category;
import com.eshop.entities.Discount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductDto implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2355729860031846376L;
    @Id
    @NotBlank(message = "{NotBlank.product.slug}")
    private String slug;
	@NotBlank(message = "{NotBlank.product.name}")
	private String name;
	private String image;
    private MultipartFile imageFile;
    @NotBlank(message = "{NotBlank.product.description}")
    private String description;
    @NotNull(message = "{NotNull.product.quantity}")
    @Min(value = 1, message = "{Min.product.quantity}")
    @PositiveOrZero(message = "{PositiveOrZero.product.quantity}")
    private Integer quantity;
    @NotNull(message = "{NotNull.product.unitPrice}")
    @Min(value = 1, message = "{Min.product.unitPrice}")
    @PositiveOrZero(message = "{PositiveOrZero.product.unitPrice}")
    private Double unitPrice;
    @NotNull(message = "{NotNull.product.category}")
    private Category category;
    private Discount discount;
}
