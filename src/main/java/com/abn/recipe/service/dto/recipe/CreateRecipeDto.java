package com.abn.recipe.service.dto.recipe;

import lombok.Builder;

import java.util.List;

@Builder
public record CreateRecipeDto(
        String name,
        String instructions,
        Integer serves,
        List<Long> ingredients,
        Boolean isVegetarian
) { }
