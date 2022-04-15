package com.eshop.jpaRepository;

import com.eshop.entities.Category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	Category findBySlug(String slug);
}
