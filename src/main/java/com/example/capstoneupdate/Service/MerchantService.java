package com.example.capstoneupdate.Service;
import com.example.capstoneupdate.Model.Merchant;
import com.example.capstoneupdate.Repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {
    private final MerchantRepository merchantRepository;

    //Get Merchant
    public List<Merchant> getAllMerchants() {
        return merchantRepository.findAll();
    }

    //Add Merchant
    public void addMerchant(Merchant merchant) {
        merchantRepository.save(merchant);
    }

    //Update Merchant
    public boolean updateMerchant(Integer id ,Merchant merchant) {
        Merchant m=merchantRepository.getById(id);
        if (m==null){
            return false;
        }
        m.setName(merchant.getName());
        merchantRepository.save(m);
        return true;
    }

    //Delete Merchant
    public boolean deleteMerchant(Integer id) {
        Merchant m=merchantRepository.getById(id);
        if (m==null){
            return false;
        }
        merchantRepository.delete(m);
        return true;
    }
}
