package com.cbxz.abn.service.dto.recipe;

import lombok.Builder;

import java.util.List;

@Builder
public record UpdateRecipeDto(
        Long id,
        String name,
        String instructions,
        Integer serves,
        List<Long> ingredients,
        Boolean isVegetarian) {
}
