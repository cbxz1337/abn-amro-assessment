package com.cbxz.abn.service.dto.recipe;

import com.cbxz.abn.controller.model.request.PaginationRequest;
import lombok.Builder;

import java.util.List;

@Builder
public record PaginatedRecipeDto(
        long total,
        PaginationRequest pagination,
        List<RecipeDto> recipes
) {}
