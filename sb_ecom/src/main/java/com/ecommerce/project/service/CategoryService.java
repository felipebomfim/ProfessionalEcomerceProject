package com.ecommerce.project.service;

import com.ecommerce.project.payload.reponse.CategoryResponseDTO;
import com.ecommerce.project.payload.reponse.GetAllCategoriesResponse;
import com.ecommerce.project.payload.request.CategoryRequest;

public interface CategoryService {
    
    GetAllCategoriesResponse getAllCategories();

    void createCategory(CategoryRequest category);

    String deleteCategory(Long categoryId);

    CategoryResponseDTO updateCategory(CategoryRequest category, Long categoryId);
}
