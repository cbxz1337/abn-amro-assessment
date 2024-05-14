package com.abn.recipe.controller.model.request.ingredient;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CreateIngredientRequest(
        @NotBlank String name
) { }
