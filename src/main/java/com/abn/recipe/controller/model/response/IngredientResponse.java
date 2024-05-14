package com.abn.recipe.controller.model.response;

import lombok.Builder;

@Builder
public record IngredientResponse(
        Long id,
        String name
) { }
