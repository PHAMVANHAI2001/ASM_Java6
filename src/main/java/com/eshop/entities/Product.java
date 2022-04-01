package com.eshop.entities;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Product", indexes = {
        @Index(name = "UQ__Product__BC7B5FB6D9799138", columnList = "Slug", unique = true)
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Slug", nullable = false)
    private String slug;

    @Lob
    @Column(name = "Image", nullable = false)
    private String image;

    @Lob
    @Column(name = "ImagePreview1", nullable = false)
    private String imagePreview1;

    @Lob
    @Column(name = "ImagePreview2", nullable = false)
    private String imagePreview2;

    @Lob
    @Column(name = "ImagePreview3", nullable = false)
    private String imagePreview3;

    @Lob
    @Column(name = "Description", nullable = false)
    private String description;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Column(name = "UnitPrice", nullable = false)
    private Double unitPrice;

    @Column(name = "CreatedDate", nullable = false)
    private Date createdDate;

    @Column(name = "Available", nullable = false)
    private Integer available;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CategoryId", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DiscountId")
    private Discount discount;

    @OneToMany(mappedBy = "product")
    private Set<OrderDetail> orderDetails = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<Cart> carts = new LinkedHashSet<>();

}