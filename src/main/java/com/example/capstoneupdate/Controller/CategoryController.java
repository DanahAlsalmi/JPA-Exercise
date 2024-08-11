package com.example.capstoneupdate.Controller;
import com.example.capstoneupdate.Model.Category;
import com.example.capstoneupdate.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryService categoryService;
    //Get all categories
    @GetMapping("/categories")
    public ResponseEntity getAllCategories() {
        return ResponseEntity.status(200).body(categoryService.getAllCategories());
    }

    //Add category
    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            String msg =errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(201).body("Category added successfully");
    }

    //Update category
    @PutMapping("/update/{categoryId}")
    public ResponseEntity updateCategory(@PathVariable Integer categoryId, @Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            String msg =errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        boolean isCategoryUpdated = categoryService.updateCategory(categoryId, category);
        if (isCategoryUpdated) {
            return ResponseEntity.status(201).body("Category updated successfully");
        }
        return ResponseEntity.status(404).body("Category not found");
    }

    //Delete category
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable Integer categoryId) {
        boolean isCategoryDeleted = categoryService.deleteCategory(categoryId);
        if (isCategoryDeleted) {
            return ResponseEntity.status(201).body("Category deleted successfully");
        }
        return ResponseEntity.status(404).body("Category not found");
    }
}