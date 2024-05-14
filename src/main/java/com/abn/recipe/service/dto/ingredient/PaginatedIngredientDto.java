package com.abn.recipe.service.dto.ingredient;

import com.abn.recipe.service.dto.Pagination;
import lombok.Builder;

import java.util.List;

@Builder
public record PaginatedIngredientDto(long total,
                                     List<IngredientDto> ingredients,
                                     Pagination pagination) { }
