package com.ecommerce.ecommercebackend.repository;

import com.ecommerce.ecommercebackend.model.Product;
import com.ecommerce.ecommercebackend.model.User;
import com.ecommerce.ecommercebackend.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    List<Wishlist> findAllByUserOrderByCreatedDateDesc(User user);
    boolean existsByUserAndProductId(User user, Integer productId);
    void deleteByUserAndProduct(User user, Product product);
}
