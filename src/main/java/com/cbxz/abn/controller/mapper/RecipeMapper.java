package com.cbxz.abn.controller.mapper;

import com.cbxz.abn.controller.model.request.recipe.CreateRecipeRequest;
import com.cbxz.abn.controller.model.request.recipe.RecipeUpdateRequest;
import com.cbxz.abn.controller.model.response.RecipeResponse;
import com.cbxz.abn.controller.model.response.SearchKeyResponse;
import com.cbxz.abn.controller.model.response.SearchKeyTypeResponse;
import com.cbxz.abn.service.dto.recipe.CreateRecipeDto;
import com.cbxz.abn.service.dto.recipe.RecipeDto;
import com.cbxz.abn.service.dto.recipe.UpdateRecipeDto;
import com.cbxz.abn.service.dto.search.RecipeSearchKey;

import java.util.Arrays;
import java.util.List;

public class RecipeMapper {
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
                .ingredients(recipe.ingredients().stream().map(IngredientMapper::toIngredientResponse).toList())
                .build();
    }

    public static List<SearchKeyResponse> keyResponseList(RecipeSearchKey[] recipeSearchKeys) {
        return Arrays.stream(recipeSearchKeys).map(
                it -> SearchKeyResponse.builder()
                        .key(it)
                        .type(SearchKeyTypeResponse.valueBy(it.getType()))
                        .build()
        ).toList();
    }
}
