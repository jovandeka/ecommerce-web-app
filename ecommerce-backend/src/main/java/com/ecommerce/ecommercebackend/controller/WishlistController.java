package com.ecommerce.ecommercebackend.controller;

import antlr.Token;
import com.ecommerce.ecommercebackend.common.ApiResponse;
import com.ecommerce.ecommercebackend.dto.ProductDto;
import com.ecommerce.ecommercebackend.model.Product;
import com.ecommerce.ecommercebackend.model.User;
import com.ecommerce.ecommercebackend.model.Wishlist;
import com.ecommerce.ecommercebackend.service.AuthentificationService;
import com.ecommerce.ecommercebackend.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    @Autowired
    WishlistService wishlistService;

    @Autowired
    AuthentificationService authentificationService;

    //save product in wishlist
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishlist(@RequestBody Product product, @RequestParam("token") String token){
    //auth the token
    authentificationService.authenticate(token);
    //find the user
    User user = authentificationService.getUser(token);
    //save the product
    Wishlist wishlist = new Wishlist(user, product);
    wishlistService.createWishlist(wishlist);
    ApiResponse apiResponse = new ApiResponse(true, "Added to wishlist");
    return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    //get all wishlist products for a user
    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishlist(@PathVariable("token") String token){
        authentificationService.authenticate(token);
        User user = authentificationService.getUser(token);
        List<ProductDto> productDtos = wishlistService.getWishlistForUser(user);
        return new ResponseEntity<>(productDtos,HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkWishlist(@RequestParam("token") String token, @RequestParam("id") Integer productId) {
        authentificationService.authenticate(token);
        User user = authentificationService.getUser(token);
        boolean isInWishlist = wishlistService.isProductInWishlist(user, productId);
        return new ResponseEntity<>(isInWishlist, HttpStatus.OK);
    }

    // Remove product from wishlist
    @PostMapping("/remove")
    public ResponseEntity<ApiResponse> removeFromWishlist(@RequestBody Product product, @RequestParam("token") String token) {
        authentificationService.authenticate(token);
        User user = authentificationService.getUser(token);
        wishlistService.removeProductFromWishlist(user, product);
        ApiResponse apiResponse = new ApiResponse(true, "Removed from wishlist");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
