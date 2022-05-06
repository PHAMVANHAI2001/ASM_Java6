package com.eshop.jpaRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshop.entities.SinhVien;

public interface SinhVienJpaRepo extends JpaRepository<SinhVien, Integer> {
    List<SinhVien> findAll();
    SinhVien getById(Integer id);
}
