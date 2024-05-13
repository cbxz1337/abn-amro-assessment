package com.cbxz.abn.controller.model.response;

import com.cbxz.abn.service.dto.Pagination;
import lombok.Builder;

import java.util.List;

@Builder
public record PaginatedIngredientResponse(
        long total,
        Pagination pagination,
        List<IngredientResponse> ingredients
) { }
