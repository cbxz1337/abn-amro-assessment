package com.abn.recipe.controller.model.response;

import com.abn.recipe.service.dto.Pagination;
import lombok.Builder;

import java.util.List;

@Builder
public record PaginatedIngredientResponse(
        long total,
        Pagination pagination,
        List<IngredientResponse> ingredients
) { }
