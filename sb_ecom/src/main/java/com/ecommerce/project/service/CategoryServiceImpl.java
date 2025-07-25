package com.ecommerce.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        Category categoryToBeSaved = new Category();
        categoryToBeSaved.setCategoryName(category.getCategoryName());
        categoryRepository.save(categoryToBeSaved);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (categoryOptional.isEmpty())
            throw new ResourceNotFoundException("Category", "categoryId", categoryId);

        categoryRepository.delete(categoryOptional.get());
        return "Category with categoryId: " + categoryId + " deleted successfully!!";
    }

    @Override
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Category updateCategory(Category category, Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (categoryOptional.isPresent()) {
            Category existingCategory = categoryOptional.get();
            existingCategory.setCategoryName(category.getCategoryName());
            Category updatedCategory = categoryRepository.save(existingCategory);
            return updatedCategory;
        } else {
            throw new ResourceNotFoundException("Category", "categoryId", categoryId);
        }
    }

}
