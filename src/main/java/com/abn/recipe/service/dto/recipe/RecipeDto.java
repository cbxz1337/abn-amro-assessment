package com.abn.recipe.service.dto.recipe;

import com.abn.recipe.service.dto.ingredient.IngredientDto;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record RecipeDto(
        Long id,
        String name,
        String instructions,
        Integer serves,
        List<IngredientDto> ingredients,
        Boolean isVegetarian,
        LocalDateTime updated
) { }
