package com.eshop.jpaRepository;

import com.eshop.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    public List<Cart> findByUserId(Integer userId);
    public List<Cart> findByUserIdAndProductId(Integer userId, Integer slug);
    public Boolean existsCartByUserIdAndProductId(Integer userId, Integer productId);
    public Cart findByProductId(Integer productId);
    public Cart deleteByProductSlug(String slug);
}
