package com.cbxz.abn.service.mapper;

import com.cbxz.abn.domain.Ingredient;
import com.cbxz.abn.service.dto.ingredient.IngredientDto;

public class IngredientMapper {

    public static IngredientDto toIngredientDto(Ingredient ingredient) {
        return new IngredientDto(
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getUpdated()
        );
    }

    public static Ingredient toIngredient(IngredientDto ingredient) {
        return Ingredient.builder()
                .id(ingredient.id())
                .name(ingredient.name())
                .build();
    }
}
