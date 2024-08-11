package com.example.capstoneupdate.Service;


import com.example.capstoneupdate.Model.Category;
import com.example.capstoneupdate.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    //Get all Category
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    //Add Category
    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    //Update Category
   public boolean updateCategory(Integer id,Category category){
        Category c = categoryRepository.getById(id);
        if(c == null){
            return false;
        }
        c.setCategoryName(category.getCategoryName());
        categoryRepository.save(c);
        return true;

   }

    //Delete Category
   public boolean deleteCategory(Integer id){
        Category c = categoryRepository.getById(id);
        if(c == null){
            return false;
        }
        categoryRepository.deleteById(id);
        return true;
   }
}