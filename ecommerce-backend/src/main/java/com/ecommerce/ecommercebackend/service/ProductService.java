package com.ecommerce.ecommercebackend.service;

import com.ecommerce.ecommercebackend.dto.ProductDto;
import com.ecommerce.ecommercebackend.model.Category;
import com.ecommerce.ecommercebackend.model.Product;
import com.ecommerce.ecommercebackend.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;

    public void createProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setName(productDto.getName());
        product.setCategory(category);
        product.setPrice(productDto.getPrice());
        productRepo.save(product);
    }

    public ProductDto getProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setDescription(product.getDescription());
        productDto.setImageURL(product.getImageURL());
        productDto.setName(product.getName());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setPrice(product.getPrice());
        productDto.setId(product.getId());
        return productDto;
    }
    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepo.findAll();

        List<ProductDto> productDtos= new ArrayList<>();
        for(Product product : allProducts){
            productDtos.add(getProductDto(product));
        }
        return productDtos;
    }

    public void updateProduct(ProductDto productDto, Integer productId) throws Exception {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if(!optionalProduct.isPresent()){
            throw new Exception("product does not exist");
        }
        Product product = optionalProduct.get();

        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        productRepo.save(product);
    }
}
