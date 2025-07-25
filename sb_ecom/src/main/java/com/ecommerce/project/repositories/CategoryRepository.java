package com.ecommerce.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.project.model.Category;


public interface CategoryRepository extends JpaRepository<Category, Long>{
    List<Category> findByCategoryName(String categoryName);
}
