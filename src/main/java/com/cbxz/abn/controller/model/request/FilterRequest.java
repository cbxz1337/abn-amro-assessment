package com.cbxz.abn.controller.model.request;

import com.cbxz.abn.service.dto.search.Operation;
import com.cbxz.abn.service.dto.search.RecipeSearchKey;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public record FilterRequest(
        @NotNull RecipeSearchKey key,
        @NotNull Operation operation,
        @NotBlank String filterValue
) {}

