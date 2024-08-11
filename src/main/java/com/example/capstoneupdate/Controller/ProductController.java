package com.example.capstoneupdate.Controller;

import com.example.capstoneupdate.Model.Product;
import com.example.capstoneupdate.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //Get All Products
    @GetMapping("/get")
    public ResponseEntity getAllProducts() {
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }

    //Add product
    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        productService.addProduct(product);
        return ResponseEntity.status(201).body("Product added successfully");
    }

    //Update product
    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @Valid @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        boolean isUpdated = productService.updateProduct(id, product);
        if (isUpdated) {
            return ResponseEntity.status(201).body("Product updated successfully");
        }
        return ResponseEntity.status(404).body("Product not found");
    }

    //Delete product
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            return ResponseEntity.status(201).body("Product deleted successfully");
        }
        return ResponseEntity.status(404).body("Product not found");
    }

    //User buy
    @PostMapping("/buy/{userId}/{productId}/{merchantId}")
    public ResponseEntity buyProduct(@PathVariable int userId, @PathVariable int productId, @PathVariable int merchantId) {
        String result = productService.purchase(userId, productId, merchantId);
        if (result.equals("Purchase successful")) {
            return ResponseEntity.status(200).body(result);
        } else {
            return ResponseEntity.status(400).body(result);
        }
    }

    //Purchase History
    @GetMapping("/user/{userId}")
    public ResponseEntity getPurchaseHistory(@PathVariable Integer userId) {
        return ResponseEntity.status(200).body(productService.getPurchaseHistory(userId));
    }

}