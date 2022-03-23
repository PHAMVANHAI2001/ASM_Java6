package com.eshop.jpaRepository;

import com.eshop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDAO extends JpaRepository<Order, Integer> {
    List<Order> findAllByStatus(int status);

}
