package com.example.capstoneupdate.Service;


import com.example.capstoneupdate.Model.Product;
import com.example.capstoneupdate.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final UserService userService;
    private final ProductService productService;
    private List<Product> wishlist = new ArrayList<>();


    //Add item to wishlist
    public void addItemToWishlist(Integer userId, Integer productId) {
        Product product = productService.getProductById(productId);
        if (product != null && !wishlist.contains(product)) {
            wishlist.add(product);
        }
    }
//    public void addItemToWishlist(int userId, int productId) {
//        User user = userService.getUserById(userId);
//        if (user != null) {
//            Product product = productService.getProductById(productId);
//            if (product != null) {
//                user.getWishlist().add(product);
//            }
//        }
//    }

    //Get user wishlist
//    public ArrayList<Product> getUserWishlist(int userId) {
//        User user = userService.getUserById(userId);
//        if (user != null) {
//            return user.getWishlist();
//        }
//        return null;
//    }

//    public List<Product> getWishlist(Integer userId) {
//        User user = userService.getUserById(userId);
//        if (user != null) {
//            return user. // Return the user's wishlist
//        }
//        return new ArrayList<>(); 
//    }


    //Remove from user wishlist
//    public void removeItemFromWishlist(int userId, int productId) {
//        User user = userService.getUserById(userId);
//        if (user != null) {
//            ArrayList<Product> wishlist = user.getWishlist();
//            for (int i = 0; i < wishlist.size(); i++) {
//                if (wishlist.get(i).getId() == productId) {
//                    wishlist.remove(i);
//                    break;
//                }
//            }
//        }
//    }

    public void removeItemFromWishlist(Integer productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            wishlist.remove(product);
        }
    }
}
