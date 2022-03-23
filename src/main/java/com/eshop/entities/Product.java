// Generated with g9.

package com.eshop.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(indexes={@Index(name="Product_Slug_IX", columnList="Slug", unique=true)})
public class Product implements Serializable {
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
    private Instant createdDate;

    @Column(name = "Available", nullable = false)
    private Integer available;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "CategoryId", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DiscountId")
    private Discount discount;

    @OneToMany(mappedBy = "product")
    private Set<ShoppingCart> shoppingCarts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<OrderDetail> orderDetails = new LinkedHashSet<>();

}
