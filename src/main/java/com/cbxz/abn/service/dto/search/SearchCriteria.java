package com.cbxz.abn.service.dto.search;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record SearchCriteria(
        @NotNull RecipeSearchKey key,
        @NotNull Operation operator,
        @NotBlank String value,
        @NotNull JoinType joinType
) { }
