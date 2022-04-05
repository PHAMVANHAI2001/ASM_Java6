package com.eshop.entities;

import java.time.Instant;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Discount {
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
    private Date createdDate;

    @JsonIgnore
    @OneToMany(mappedBy = "discount")
    private Set<Product> products = new LinkedHashSet<>();

    //TODO Reverse Engineering! Migrate other columns to the entity
}