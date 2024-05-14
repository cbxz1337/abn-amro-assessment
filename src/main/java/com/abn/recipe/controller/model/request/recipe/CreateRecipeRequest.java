package com.abn.recipe.controller.model.request.recipe;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.util.List;


@Builder
public record CreateRecipeRequest(
        @NotNull @Positive Integer serves,
        @NotBlank String name,
        @NotBlank String instructions,
        @NotEmpty List<Long> ingredients,
        @NotNull Boolean isVegetarian
) {}
