package com.abn.recipe.service.dto;

import lombok.Builder;

@Builder
public record Pagination(
        Integer offset,
        Integer limit
) {}
