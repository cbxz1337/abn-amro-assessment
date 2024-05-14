package com.cbxz.abn.controller.mapper;

import com.cbxz.abn.controller.model.request.ingredient.CreateIngredientRequest;
import com.cbxz.abn.controller.model.request.ingredient.UpdateIngredientRequest;
import com.cbxz.abn.controller.model.response.IngredientResponse;
import com.cbxz.abn.controller.model.response.PaginatedIngredientResponse;
import com.cbxz.abn.service.dto.ingredient.IngredientDto;
import com.cbxz.abn.service.dto.ingredient.PaginatedIngredientDto;

public class IngredientMapper {
    public static IngredientResponse toIngredientResponse(IngredientDto ingredient) {
        return IngredientResponse.builder()
                .name(ingredient.name())
                .id(ingredient.id())
                .build();
    }

    public static IngredientDto toIngredientDto(CreateIngredientRequest request) {
        return IngredientDto.builder()
                .name(request.name())
                .build();
    }

    public static IngredientDto toIngredientDto(UpdateIngredientRequest request) {
        return IngredientDto.builder()
                .id(request.id())
                .name(request.name())
                .build();
    }

    public static PaginatedIngredientResponse toIngredientPaginatedResponse(PaginatedIngredientDto dto) {
        return PaginatedIngredientResponse.builder()
                .total(dto.total())
                .pagination(dto.pagination())
                .ingredients(dto.ingredients().stream().map(IngredientMapper::toIngredientResponse).toList())
                .build();
    }
}
