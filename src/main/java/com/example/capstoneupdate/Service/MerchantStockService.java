package com.example.capstoneupdate.Service;
import com.example.capstoneupdate.Model.MerchantStock;
import com.example.capstoneupdate.Repository.MerchantStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {

    private final MerchantStockRepository merchantStockRepository;

    //Get Merchant Stock
   public List<MerchantStock> getAllMerchantStock(){
       return merchantStockRepository.findAll();
   }

    //Add Merchant Stock
    public void addMerchantStock(MerchantStock merchantStock){
       merchantStockRepository.save(merchantStock);
    }

    //Update Merchant Stock
    public boolean updateMerchantStock(Integer id,MerchantStock merchantStock){
       MerchantStock m = merchantStockRepository.getById(id);
       if(m != null){
           m.setProductId(merchantStock.getProductId());
           m.setMerchantId(merchantStock.getMerchantId());
           m.setStock(merchantStock.getStock());
           merchantStockRepository.save(m);
           return true;
       }
       return false;
    }

    //Delete Merchant Stock
    public boolean deleteMerchantStock(Integer id){
       MerchantStock m = merchantStockRepository.getById(id);
       if(m != null){
           merchantStockRepository.delete(m);
           return true;
       }
       return false;
    }



    // Add Stock to Existing MerchantStock
    public boolean addStock(int productId, int merchantId, int additionalStock) {
        for (MerchantStock stock : getAllMerchantStock()) {
            if (stock.getProductId() == productId && stock.getMerchantId() == merchantId) {
                stock.setStock(stock.getStock() + additionalStock);
                merchantStockRepository.save(stock);
                return true;
            }
        }
        return false;
    }



}
