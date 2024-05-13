package com.cbxz.abn.controller.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

@Builder
public record PaginationRequest(
        @NotNull
        @PositiveOrZero
        Integer offset,
        @NotNull
        @Positive
        Integer limit
) { }
