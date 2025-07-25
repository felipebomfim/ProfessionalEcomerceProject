package com.ecommerce.project.payload.reponse;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCategoriesResponse {
    private List<CategoryResponseDTO> content;
}