package com.eshop.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SinhVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Mobile", length = 10)
    private String mobile;

    @Column(name = "Address")
    private String address;

    @Column(name = "Coures")
    private String coures;

    @Column(name = "Score")
    private Double score;

    @Column(name = "\"Year\"")
    private Date year;
}