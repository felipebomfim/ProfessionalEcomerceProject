package com.ecommerce.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.project.payload.reponse.CategoryResponseDTO;
import com.ecommerce.project.payload.reponse.GetAllCategoriesResponse;
import com.ecommerce.project.payload.request.CategoryRequest;
import com.ecommerce.project.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CategoryController {
    
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    
    @GetMapping("/public/categories")
    @ResponseStatus(HttpStatus.OK)
    public GetAllCategoriesResponse getAllCategories(){
        GetAllCategoriesResponse response = categoryService.getAllCategories();
        return response;
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<String> createCategory(@RequestBody @Valid CategoryRequest category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category created successfully.", HttpStatus.ACCEPTED);        
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
        String status = categoryService.deleteCategory(categoryId);
        if (status == "Category not found")
            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(status);
    }

    @PutMapping("/admin/categories/{categoryId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CategoryResponseDTO updateCategory(
        @RequestBody @Valid CategoryRequest category, 
        @PathVariable Long categoryId
    ) {
        CategoryResponseDTO response = categoryService.updateCategory(category, categoryId);
        return response;
    }
    
}
