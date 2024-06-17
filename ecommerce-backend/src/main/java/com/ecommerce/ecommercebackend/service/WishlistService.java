package com.ecommerce.ecommercebackend.service;

import com.ecommerce.ecommercebackend.dto.ProductDto;
import com.ecommerce.ecommercebackend.model.Product;
import com.ecommerce.ecommercebackend.model.User;
import com.ecommerce.ecommercebackend.model.Wishlist;
import com.ecommerce.ecommercebackend.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    ProductService productService;
    public void createWishlist(Wishlist wishlist) {
        wishlistRepository.save(wishlist);
    }

    public List<ProductDto> getWishlistForUser(User user) {
        final List<Wishlist> wishlists = wishlistRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<ProductDto> productDtos = new ArrayList<>();
        for(Wishlist wishlist:wishlists){
            productDtos.add(productService.getProductDto(wishlist.getProduct()));
        }
        return productDtos;
    }

    public boolean isProductInWishlist(User user, Integer productId) {
        return wishlistRepository.existsByUserAndProductId(user, productId);
    }

    @Transactional
    public void removeProductFromWishlist(User user, Product product) {
        wishlistRepository.deleteByUserAndProduct(user, product);
    }
}
