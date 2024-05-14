package com.abn.recipe.service.mapper;

import com.abn.recipe.domain.Ingredient;
import com.abn.recipe.domain.Recipe;
import com.abn.recipe.service.dto.recipe.CreateRecipeDto;
import com.abn.recipe.service.dto.recipe.RecipeDto;
import com.abn.recipe.service.dto.recipe.UpdateRecipeDto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RecipeMapper {

    public static RecipeDto toRecipeDto(Recipe recipe) {
        return new RecipeDto(
                recipe.getId(),
                recipe.getName(),
                recipe.getInstructions(),
                recipe.getServes(),
                Optional.ofNullable(
                        recipe.getIngredient())
                        .map(it -> it.stream().map(IngredientMapper::toIngredientDto).toList())
                        .orElse(Collections.emptyList()),
                recipe.getIsVegetarian(),
                recipe.getUpdated()
        );
    }

    public static Recipe toRecipe(CreateRecipeDto dto, List<Ingredient> ingredients) {
        return Recipe.builder()
                .name(dto.name())
                .isVegetarian(dto.isVegetarian())
                .serves(dto.serves())
                .instructions(dto.instructions())
                .ingredient(ingredients)
                .build();
    }

    public static Recipe toRecipe(UpdateRecipeDto dto, List<Ingredient> ingredients) {
        return Recipe.builder()
                .id(dto.id())
                .name(dto.name())
                .isVegetarian(dto.isVegetarian())
                .serves(dto.serves())
                .instructions(dto.instructions())
                .ingredient(ingredients)
                .build();
    }
}
