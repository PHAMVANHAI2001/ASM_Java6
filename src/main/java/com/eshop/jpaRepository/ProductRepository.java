package com.eshop.jpaRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eshop.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findBySlug(String slug);
    List<Product> findByCategorySlug(String slug);
    Page<Product> findByCategorySlug(String slug, Pageable pageable);
    List<Product> findByNameContainingAllIgnoreCase(String name);
    Boolean existsBySlug(String slug);
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE Product SET Available = 2 WHERE Id = ?", nativeQuery = true)
    void deleteLogical(Integer Id);
    @Query(value = "UPDATE Product SET Available = 0 WHERE Id = ?", nativeQuery = true)
    void activeProduct(Integer Id);
}
