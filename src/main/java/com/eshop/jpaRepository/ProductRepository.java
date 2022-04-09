package com.eshop.jpaRepository;

import com.eshop.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findBySlug(String slug);
    List<Product> findByCategorySlug(String slug);
    Page<Product> findByCategorySlug(String slug, Pageable pageable);
    List<Product> findByNameContainingAllIgnoreCase(String name);
}
