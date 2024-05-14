package com.abn.recipe.service.dto.recipe;

import com.abn.recipe.controller.model.request.PaginationRequest;
import lombok.Builder;

import java.util.List;

@Builder
public record PaginatedRecipeDto(
        long total,
        PaginationRequest pagination,
        List<RecipeDto> recipes
) {}
