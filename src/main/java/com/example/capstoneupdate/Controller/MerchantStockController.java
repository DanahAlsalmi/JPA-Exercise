package com.example.capstoneupdate.Controller;
import com.example.capstoneupdate.Model.MerchantStock;
import com.example.capstoneupdate.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant-stock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;

    //Get All merchant stock
    @GetMapping("/get")
    public ResponseEntity getMerchantStock() {
        return ResponseEntity.status(200).body(merchantStockService.getAllMerchantStock());
    }

    //Add merchant stock
    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(200).body("Merchant Stock Added Successfully");
    }

    //Update merchant stock
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable Integer id ,@Valid @RequestBody MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        boolean isUpdated = merchantStockService.updateMerchantStock(id,merchantStock);
        if (isUpdated) {
            return ResponseEntity.status(200).body("Merchant Stock Updated");
        }
        return ResponseEntity.status(404).body("Merchant Stock Not Found");
    }

    //Delete merchant stock
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable Integer id) {
        boolean isDeleted = merchantStockService.deleteMerchantStock(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body("Merchant Stock Deleted");
        }
        return ResponseEntity.status(404).body("Merchant Stock Not Found");
    }

    // Add stock to existing Merchant Stock
    @PutMapping("/add-stock/{productId}/{merchantId}/{additionalStock}")
    public ResponseEntity addStock(@PathVariable int productId, @PathVariable int merchantId, @PathVariable int additionalStock) {
        boolean isAdded = merchantStockService.addStock(productId, merchantId, additionalStock);
        if (isAdded) {
            return ResponseEntity.status(200).body("Stock added successfully");
        } else{
            return ResponseEntity.status(400).body("Invalid product ID or merchant ID");
        }
    }
}