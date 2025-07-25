package com.ecommerce.project.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.reponse.CategoryResponseDTO;
import com.ecommerce.project.payload.reponse.GetAllCategoriesResponse;
import com.ecommerce.project.payload.request.CategoryRequest;
import com.ecommerce.project.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public GetAllCategoriesResponse getAllCategories() {
        List<Category> categoriesList =  categoryRepository.findAll();
        List<CategoryResponseDTO> categoryDTOs = categoriesList.stream()
            .map(category -> modelMapper.map(category, CategoryResponseDTO.class))
            .toList();
        GetAllCategoriesResponse response = new GetAllCategoriesResponse(categoryDTOs);
        return response;
    }

    @Override
    public void createCategory(CategoryRequest categoryRequest) {
        Category categoryToBeSaved = new Category();
        categoryToBeSaved.setCategoryName(categoryRequest.getCategoryName());

        verifyIfCategoryNameAlreadyExists(categoryRequest.getCategoryName());
        
        categoryRepository.save(categoryToBeSaved);
    }

    private void verifyIfCategoryNameAlreadyExists(String categoryName) {
        List<Category> categoryList = categoryRepository.findByCategoryName(categoryName);

        if (!categoryList.isEmpty())
            throw new APIException("Category name '"+categoryName+"' already exists!");
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
    public CategoryResponseDTO updateCategory(CategoryRequest category, Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (categoryOptional.isPresent()) {
            Category existingCategory = categoryOptional.get();
            existingCategory.setCategoryName(category.getCategoryName());
            Category updatedCategory = categoryRepository.save(existingCategory);
            CategoryResponseDTO responseDTO = modelMapper.map(updatedCategory, CategoryResponseDTO.class);
            return responseDTO;
        } else {
            throw new ResourceNotFoundException("Category", "categoryId", categoryId);
        }
    }

}
