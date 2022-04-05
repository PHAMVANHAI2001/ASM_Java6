package com.eshop.entities;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
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
@Table(name = "\"User\"", indexes = {
        @Index(name = "UQ__User__536C85E43114959E", columnList = "Username", unique = true),
        @Index(name = "UQ__User__A9D10534F7B53C32", columnList = "Email", unique = true)
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Username", nullable = false, length = 50)
    private String username;

    @Column(name = "Password", nullable = false, length = 128)
    private String password;

    @Column(name = "Fullname", nullable = false, length = 128)
    private String fullname;

    @Column(name = "Email", nullable = false, length = 128)
    private String email;

    @Column(name = "Address")
    private String address;

    @Column(name = "PhoneNumber", length = 10)
    private String phoneNumber;

    @Column(name = "Photo", length = 128)
    private String photo;

    @Column(name = "CreatedDate", nullable = false)
    private Date createdDate;

    @Column(name = "Enabled", nullable = false)
    private Boolean enabled = false;

    @OneToMany(mappedBy = "user")
    private Set<Order> orders = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Authority> authorities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Cart> carts = new LinkedHashSet<>();

}