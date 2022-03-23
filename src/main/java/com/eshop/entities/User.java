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
@Table(indexes={@Index(name="User_Username_IX", columnList="Username", unique=true), @Index(name="User_Email_IX", columnList="Email", unique=true)})
public class User implements Serializable {
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

    @Column(name = "IsAdmin", nullable = false)
    private Boolean isAdmin = false;


    @Column(name = "CreatedDate", nullable = false)
    private Date createdDate;

}
