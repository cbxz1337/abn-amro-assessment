package com.cbxz.abn.controller;

import com.cbxz.abn.controller.model.request.ingredient.CreateIngredientRequest;
import com.cbxz.abn.controller.model.request.ingredient.UpdateIngredientRequest;
import com.cbxz.abn.controller.model.request.recipe.CreateRecipeRequest;
import com.cbxz.abn.controller.model.request.recipe.RecipeUpdateRequest;
import com.cbxz.abn.controller.model.response.IngredientResponse;
import com.cbxz.abn.controller.model.response.PaginatedIngredientResponse;
import com.cbxz.abn.controller.model.response.RecipeResponse;
import com.cbxz.abn.service.dto.ingredient.PaginatedIngredientDto;
import com.cbxz.abn.service.dto.recipe.CreateRecipeDto;
import com.cbxz.abn.service.dto.ingredient.IngredientDto;
import com.cbxz.abn.service.dto.recipe.RecipeDto;
import com.cbxz.abn.service.dto.recipe.UpdateRecipeDto;

public class ApiMapper {

    public static CreateRecipeDto toCreateRecipeDto(CreateRecipeRequest request) {
        return new CreateRecipeDto(
                request.name(),
                request.instructions(),
                request.serves(),
                request.ingredients(),
                request.isVegetarian()
        );
    }

    public static UpdateRecipeDto toUpdateRecipeDto(RecipeUpdateRequest request) {
        return new UpdateRecipeDto(
                request.id(),
                request.name(),
                request.instructions(),
                request.serves(),
                request.ingredients(),
                request.isVegetarian()
        );
    }

    public static RecipeResponse toRecipeResponse(RecipeDto recipe) {
        return RecipeResponse.builder()
                .id(recipe.id())
                .name(recipe.name())
                .isVegetarian(recipe.isVegetarian())
                .instructions(recipe.instructions())
                .serves(recipe.serves())
                .ingredients(recipe.ingredients().stream().map(ApiMapper::toIngredientResponse).toList())
                .build();
    }

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
                .ingredients(dto.ingredients().stream().map(ApiMapper::toIngredientResponse).toList())
                .build();
    }
}
