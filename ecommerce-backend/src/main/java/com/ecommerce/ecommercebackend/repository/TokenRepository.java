package com.ecommerce.ecommercebackend.repository;

import com.ecommerce.ecommercebackend.model.AuthenticationToken;
import com.ecommerce.ecommercebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {
    AuthenticationToken findByUser(User user);
    AuthenticationToken findByToken(String token);
}
