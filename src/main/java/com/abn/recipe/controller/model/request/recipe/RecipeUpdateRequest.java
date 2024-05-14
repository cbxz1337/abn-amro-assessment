package com.abn.recipe.controller.model.request.recipe;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.util.List;

@Builder
public record RecipeUpdateRequest(
        @NotNull Long id,
        @NotBlank String name,
        @NotNull @Positive Integer serves,
        @NotBlank String instructions,
        @NotEmpty List<Long> ingredients,
        @NotNull Boolean isVegetarian
) {
}
