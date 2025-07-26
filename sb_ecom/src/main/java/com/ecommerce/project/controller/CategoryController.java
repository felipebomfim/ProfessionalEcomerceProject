package com.ecommerce.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.project.payload.request.CategoryRequest;
import com.ecommerce.project.payload.response.CategoryResponseDTO;
import com.ecommerce.project.payload.response.GetAllCategoriesResponse;
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
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CategoryResponseDTO createCategory(@RequestBody @Valid CategoryRequest category) {
        CategoryResponseDTO response = categoryService.createCategory(category);
        return response;        
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponseDTO deleteCategory(@PathVariable Long categoryId){
        CategoryResponseDTO response = categoryService.deleteCategory(categoryId);
        return response;
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
