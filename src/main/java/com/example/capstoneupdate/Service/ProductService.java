package com.example.capstoneupdate.Service;
import com.example.capstoneupdate.Model.MerchantStock;
import com.example.capstoneupdate.Model.Product;
import com.example.capstoneupdate.Model.User;
import com.example.capstoneupdate.Repository.MerchantStockRepository;
import com.example.capstoneupdate.Repository.ProductRepository;
import com.example.capstoneupdate.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final MerchantStockRepository merchantStockRepository;

    private final ArrayList<String> purchaseHistory = new ArrayList<>();


    //Get Products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //Add Product
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    //Update product
    public boolean updateProduct(Integer id, Product product) {
        Product p = productRepository.getById(id);
        if (p != null) {
            p.setProductName(product.getProductName());
            p.setProductPrice(product.getProductPrice());
            p.setProductCategoryId(product.getProductCategoryId());
            productRepository.save(p);
            return true;
        }
        return false;
    }

    //Delete product
    public boolean deleteProduct(Integer id) {
        Product p = productRepository.getById(id);
        if (p != null) {
            productRepository.delete(p);
            return true;
        }
        return false;
    }

    // Get product by ID
    public Product getProductById(Integer id) {
        return productRepository.getById(id);
    }

    //User Buy
    public String purchase(Integer userId, int productId, int merchantId) {
        // Validate user ID
        User user = userRepository.getById(userId);
        if (user == null) {
            return "Invalid user ID";
        }

        if ("Admin".equalsIgnoreCase(user.getRole())) {
            return "Admins cannot make purchases";
        }

        // Validate product ID
        Product product = productRepository.getById(productId);
        if (product == null) {
            return "Invalid product ID";
        }

        // Validate merchant ID and check stock availability
        MerchantStock merchantStock = merchantStockRepository.getById(merchantId);
        if (merchantStock == null) {
            return "Invalid merchant ID or product not available with this merchant";
        }

        // Check stock availability
        if (merchantStock.getStock() <= 0) {
            return "Product out of stock";
        }

        // Check user balance
        double productPrice = product.getProductPrice();
        if (user.getBalance() < productPrice) {
            return "Insufficient balance";
        }

        // Deduct stock and balance
        merchantStock.setStock(merchantStock.getStock() - 1);
        merchantStockRepository.save(merchantStock);

        user.setBalance(user.getBalance() - productPrice);
        userRepository.save(user);

        // Save to History
        String purchaseRecord = "User ID: " + userId +
                ", Product ID: " + productId +
                ", Merchant ID: " + merchantId +
                ", Quantity: " + 1 +
                ", Total Price: " + productPrice +
                ", Date: " + LocalDateTime.now().toString();
        purchaseHistory.add(purchaseRecord);

        return "Purchase successful";
    }


    //history
    public List<String> getPurchaseHistory(Integer userId) {
        List<String> userHistory = new ArrayList<>();
        String user = "User ID: " + userId + ",";

        for (String record : purchaseHistory) {
            if (record.startsWith(user)) {
                userHistory.add(record);
            }
        }
        return userHistory;
    }

}
