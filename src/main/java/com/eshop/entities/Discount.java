// Generated with g9.

package com.eshop.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Discount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    @Column(name = "SaleOff", nullable = false)
    private Integer saleOff;

    @Column(name = "StartDate", nullable = false)
    private Instant startDate;

    @Column(name = "EndDate", nullable = false)
    private Instant endDate;

    @Column(name = "IsActive", nullable = false)
    private Boolean isActive = false;

    @Column(name = "CreatedDate", nullable = false)
    private Instant createdDate;

    @OneToMany(mappedBy = "discount")
    private Set<Product> products = new LinkedHashSet<>();

}
