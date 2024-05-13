package com.cbxz.abn.controller.model.response;

import lombok.Builder;

import java.util.List;

@Builder
public record RecipeResponse(
        Long id,
        String name,
        String instructions,
        List<IngredientResponse> ingredients,
        Boolean isVegetarian,
        Integer serves
) {}
