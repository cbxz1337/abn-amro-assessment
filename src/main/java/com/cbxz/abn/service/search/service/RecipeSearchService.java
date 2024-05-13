package com.cbxz.abn.service.search.service;

import com.cbxz.abn.controller.model.request.RecipeSearchRequest;
import com.cbxz.abn.service.dto.recipe.PaginatedRecipeDto;

public interface RecipeSearchService {
    PaginatedRecipeDto getPaginatedRecipeDtos(RecipeSearchRequest searchRequest);
}
