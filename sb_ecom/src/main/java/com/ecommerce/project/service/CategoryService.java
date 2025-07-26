package com.ecommerce.project.service;

import com.ecommerce.project.payload.request.CategoryRequest;
import com.ecommerce.project.payload.response.CategoryResponseDTO;
import com.ecommerce.project.payload.response.GetAllCategoriesResponse;

public interface CategoryService {
    
    GetAllCategoriesResponse getAllCategories();

    CategoryResponseDTO createCategory(CategoryRequest category);

    CategoryResponseDTO deleteCategory(Long categoryId);

    CategoryResponseDTO updateCategory(CategoryRequest category, Long categoryId);
}
