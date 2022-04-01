package com.eshop.jpaRepository;

import com.eshop.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    public List<Cart> findByUserId(int userId);
    public List<Cart> findByUserIdAndProductId(int userId, int slug);
    public Boolean existsShoppingCartByUserIdAndProductId(int userId, int productId);
    public Cart findByProductId(int productId);
    public Cart deleteByProductId(int productId);
}
