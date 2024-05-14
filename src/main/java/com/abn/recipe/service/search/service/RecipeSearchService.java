package com.abn.recipe.service.search.service;

import com.abn.recipe.controller.model.request.RecipeSearchRequest;
import com.abn.recipe.service.dto.recipe.PaginatedRecipeDto;

public interface RecipeSearchService {
    PaginatedRecipeDto getPaginatedRecipeDtos(RecipeSearchRequest searchRequest);
}
