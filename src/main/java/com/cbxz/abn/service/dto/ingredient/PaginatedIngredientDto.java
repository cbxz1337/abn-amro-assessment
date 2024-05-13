package com.cbxz.abn.service.dto.ingredient;

import com.cbxz.abn.service.dto.Pagination;
import lombok.Builder;

import java.util.List;

@Builder
public record PaginatedIngredientDto(long total,
                                     List<IngredientDto> ingredients,
                                     Pagination pagination) { }
