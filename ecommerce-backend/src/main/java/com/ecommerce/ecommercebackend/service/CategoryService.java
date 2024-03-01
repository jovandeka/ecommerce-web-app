package com.ecommerce.ecommercebackend.service;

import com.ecommerce.ecommercebackend.model.Category;
import com.ecommerce.ecommercebackend.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;
    public void createCategory(Category category){
        categoryRepo.save(category);
    }
}