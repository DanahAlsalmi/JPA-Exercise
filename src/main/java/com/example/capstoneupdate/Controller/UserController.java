package com.example.capstoneupdate.Controller;
import com.example.capstoneupdate.Model.Product;
import com.example.capstoneupdate.Model.User;
import com.example.capstoneupdate.Service.ProductService;
import com.example.capstoneupdate.Service.UserService;
import com.example.capstoneupdate.Service.WishlistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final WishlistService wishlistService;
    private final UserService userService;
    private final ProductService productService;

    //Get All Users
    @GetMapping("/users")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    //Add users
    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body("User added successfully");
    }

    //Update User
    @PutMapping("/update/{userId}")
    public ResponseEntity updateUser(@PathVariable Integer userId, @Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = userService.updateUser(userId, user);
        if (isUpdated) {
            return ResponseEntity.status(200).body("User updated successfully");
        }
        return ResponseEntity.status(400).body("User Not found");
    }

    //Delete User
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable Integer userId) {
        boolean isDeleted = userService.deleteUser(userId);
        if (isDeleted) {
            return ResponseEntity.status(200).body("User deleted successfully");
        }
        return ResponseEntity.status(400).body("User Not found");
    }

    //Get user By ID
    @GetMapping("/{userId}")
    public ResponseEntity getUserById(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.status(200).body(user);
        } else {
            return ResponseEntity.status(404).body("User Not found");
        }
    }

    //Prime Membership
    @PostMapping("/subscribe-prime/{userId}")
    public ResponseEntity subscribeToPrime(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }
        if (user.is_prime()) {
            return ResponseEntity.status(400).body("User is already a Prime member");
        }
        if (user.getBalance() < 100) {
            return ResponseEntity.status(400).body("Insufficient balance");
        }

        User subscribedUser = userService.subscribeToPrime(userId);
        return ResponseEntity.status(200).body(subscribedUser);
    }

    //Cancel Prime membership
    @PutMapping("/cancel-prime/{userId}")
    public ResponseEntity cancelPrimeSubscription(@PathVariable Integer userId) {
        boolean success = userService.canclePrime(userId);
        if (success) {
            return ResponseEntity.status(200).body("Prime subscription cancelled successfully.");
        } else {
            return ResponseEntity.status(404).body("Prime subscription not found");
        }
    }

    //Add to Wishlist
    @PostMapping("/add-wishlist/{userId}/{productId}")
    public ResponseEntity addItemToWishlist(@PathVariable Integer userId, @PathVariable int productId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.status(404).body("Product not found");
        }
        wishlistService.addItemToWishlist(userId, productId);
        return ResponseEntity.status(200).body("Product added to wishlist");
    }

//    //Get user wishlist
    @GetMapping("/wishlist/{userId}")
    public ResponseEntity getUserWishlist(@PathVariable Integer userId) {
        List<Product> wishlist = wishlistService.getWishlist(userId);
        if (wishlist != null) {
            return ResponseEntity.status(200).body(wishlist);
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    //Remove from wishlist
    @DeleteMapping("/wishlist/{userId}/{productId}")
    public ResponseEntity removeItemFromWishlist(@PathVariable Integer userId, @PathVariable int productId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.status(404).body("Product not found");
        }
        wishlistService.removeItemFromWishlist(productId);
        return ResponseEntity.status(200).body("Product removed from wishlist");
    }
}