package com.abn.recipe.controller.model.request;

import com.abn.recipe.service.dto.search.SearchCriteria;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.List;

@Builder
public record RecipeSearchRequest(
        @Valid
        @Size(min = 1)
        List<SearchCriteria> filterRequests,
        @Valid
        @NotNull
        PaginationRequest pagination
) { }
