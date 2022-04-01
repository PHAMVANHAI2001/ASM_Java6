package com.eshop.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId")
    private User user;

    @Column(name = "OrderCode", nullable = false, length = 11)
    private String orderCode;

    @Column(name = "CreatedDate", nullable = false)
    private Date createdDate;

    @Column(name = "Status", nullable = false)
    private Integer status;

    @Column(name = "Fullname", nullable = false, length = 128)
    private String fullname;

    @Column(name = "Address", nullable = false)
    private String address;

    @Column(name = "Email", nullable = false, length = 128)
    private String email;

    @Column(name = "PhoneNumber", nullable = false, length = 10)
    private String phoneNumber;

    @Column(name = "TotalUnitPrice", nullable = false)
    private Double totalUnitPrice;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetails = new LinkedHashSet<>();

    //TODO Reverse Engineering! Migrate other columns to the entity
}