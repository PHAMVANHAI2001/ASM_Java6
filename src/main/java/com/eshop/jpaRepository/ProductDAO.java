package com.eshop.jpaRepository;

import com.eshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {
    Product findBySlug(String slug);
    List<Product> findByCategorySlug(String slug);
    @Query("select p from Product p where p.id <> ?1")
    List<Product> findAllByIdNot(Integer id);
}
