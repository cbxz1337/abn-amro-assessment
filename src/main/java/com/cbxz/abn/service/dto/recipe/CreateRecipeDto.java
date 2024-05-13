package com.cbxz.abn.service.dto.recipe;

import java.util.List;

public record CreateRecipeDto(
        String name,
        String instructions,
        Integer serves,
        List<Long> ingredients,
        Boolean isVegetarian
) { }
