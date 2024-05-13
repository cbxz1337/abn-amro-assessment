package com.cbxz.abn.controller.model.request.ingredient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UpdateIngredientRequest(
        @NotNull Long id,
        @NotBlank String name
) {
}
