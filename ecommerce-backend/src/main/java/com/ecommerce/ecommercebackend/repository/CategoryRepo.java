package com.ecommerce.ecommercebackend.repository;

import com.ecommerce.ecommercebackend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
    //Category findCategoryName(String categoryName);
}
