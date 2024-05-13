package com.cbxz.abn.controller.model.request.ingredient;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CreateIngredientRequest(
        @NotBlank String name
) { }
