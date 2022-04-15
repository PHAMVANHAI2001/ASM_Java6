package com.eshop.jpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eshop.entities.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer>{
	
}
