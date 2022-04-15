package com.eshop.jpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eshop.entities.Order;

public interface StatsRepository extends JpaRepository<Order, Integer>{
	@Query(value = "{CALL sp_getTotalPricePerMonth(:month, :year)}", nativeQuery = true)
	String getTotalPricePerMonth(@Param("month") String month,@Param("year") String year);
}
