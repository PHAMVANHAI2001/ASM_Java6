// Generated with g9.

package com.eshop.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(indexes={@Index(name="Order_OrderCode_IX", columnList="OrderCode", unique=true)})
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "OrderCode", nullable = false, length = 11)
    private String orderCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UserId", nullable = false)
    private User user;

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

}
