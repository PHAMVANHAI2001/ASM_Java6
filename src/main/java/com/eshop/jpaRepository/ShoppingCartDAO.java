package com.eshop.jpaRepository;

import com.eshop.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartDAO extends JpaRepository<ShoppingCart, Integer> {
    public List<ShoppingCart> findByUserId(int userId);
    public List<ShoppingCart> findByUserIdAndProductId(int userId, int slug);
    public Boolean existsShoppingCartByUserIdAndProductId(int userId, int productId);
    public ShoppingCart findByProductId(int productId);
    public ShoppingCart deleteByProductId(int productId);
}
