package com.eshop.jpaRepository;

import com.eshop.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailDAO extends JpaRepository<OrderDetail, Integer> {

}
