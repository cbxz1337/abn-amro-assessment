package com.abn.recipe.service.dto.ingredient;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record IngredientDto(
        Long id,
        String name,
        LocalDateTime updated
) { }
