package com.cbxz.abn.utils;

import com.cbxz.abn.controller.model.request.PaginationRequest;
import com.cbxz.abn.controller.model.request.RecipeSearchRequest;
import com.cbxz.abn.controller.model.request.ingredient.CreateIngredientRequest;
import com.cbxz.abn.controller.model.request.ingredient.UpdateIngredientRequest;
import com.cbxz.abn.controller.model.request.recipe.CreateRecipeRequest;
import com.cbxz.abn.controller.model.request.recipe.RecipeUpdateRequest;
import com.cbxz.abn.domain.Ingredient;
import com.cbxz.abn.domain.Recipe;
import com.cbxz.abn.service.dto.ingredient.IngredientDto;
import com.cbxz.abn.service.dto.recipe.CreateRecipeDto;
import com.cbxz.abn.service.dto.recipe.RecipeDto;
import com.cbxz.abn.service.dto.search.JoinType;
import com.cbxz.abn.service.dto.search.Operation;
import com.cbxz.abn.service.dto.search.RecipeSearchKey;
import com.cbxz.abn.service.dto.search.SearchCriteria;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;

public class TestDataBuilder {

    public static Recipe buildRecipe(List<Ingredient> ingredients) {
        return Recipe.builder()
                .name("NameOfDish")
                .serves(4)
                .isVegetarian(true)
                .instructions("instructions")
                .ingredient(ingredients)
                .build();
    }

    public static RecipeDto buildRecipeDto() {
        return RecipeDto.builder()
                .name("NameOfDish")
                .serves(4)
                .isVegetarian(true)
                .instructions("instructions")
                .build();
    }

    public static CreateRecipeDto buildCreateRecipeDto() {
        return CreateRecipeDto.builder()
                .name("NameOfDish")
                .serves(4)
                .isVegetarian(true)
                .instructions("instructions")
                .build();
    }

    public static Recipe buildRecipe() {
        return Recipe.builder()
                .name("NameOfDish")
                .serves(4)
                .isVegetarian(true)
                .instructions("instructions")
                .build();
    }

    public static Ingredient buildIngredient() {
        return Ingredient.builder()
                .name("Name")
                .build();
    }

    public static IngredientDto buildIngredientDto() {
        return IngredientDto.builder()
                .name("Name")
                .build();
    }

    public static UpdateIngredientRequest buildUpdateIngredientRequest(Long id) {
        return UpdateIngredientRequest.builder()
                .id(id)
                .name("Ingredient 1")
                .build();
    }

    public static CreateIngredientRequest buildIngredientRequest() {
        return CreateIngredientRequest.builder()
                .name("Name")
                .build();
    }

    public static RecipeUpdateRequest buildRecipeUpdateRequest() {
        return RecipeUpdateRequest.builder()
                .id(1337L)
                .name("Name")
                .serves(4)
                .isVegetarian(true)
                .ingredients(List.of(1L, 2L, 3L))
                .instructions("instructions")
                .build();
    }

    public static RecipeUpdateRequest buildRecipeUpdateRequest(Long id, List<Long> ingredientIds) {
        return RecipeUpdateRequest.builder()
                .id(id)
                .name("Name")
                .serves(4)
                .isVegetarian(true)
                .ingredients(ingredientIds)
                .instructions("instructions")
                .build();
    }

    public static CreateRecipeRequest buildCreateRecipeRequest() {
        return CreateRecipeRequest.builder()
                .ingredients(List.of(1L, 2L))
                .serves(5)
                .isVegetarian(true)
                .instructions(RandomStringUtils.random(10))
                .build();
    }

    public static RecipeSearchRequest recipeSearchRequest(Recipe recipe, String ingredientName) {
        return RecipeSearchRequest.builder()
                .filterRequests(List.of(
                        SearchCriteria.builder()
                                .key(RecipeSearchKey.NAME)
                                .operator(Operation.INCLUDES)
                                .value(recipe.getName().substring(0, recipe.getName().length() - 1))
                                .joinType(JoinType.AND)
                                .build(),
                        SearchCriteria.builder()
                                .key(RecipeSearchKey.INGREDIENT_NAME)
                                .operator(Operation.INCLUDES)
                                .value(recipe.getName().substring(0, ingredientName.length() - 1))
                                .joinType(JoinType.AND)
                                .build()
                )).pagination(
                        new PaginationRequest(0, 10)
                ).build();
    }

    public static RecipeSearchRequest recipeSearchRequest() {
        return RecipeSearchRequest.builder()
                .filterRequests(List.of(
                        SearchCriteria.builder()
                                .key(RecipeSearchKey.NAME)
                                .operator(Operation.INCLUDES)
                                .value(RandomStringUtils.random(2))
                                .joinType(JoinType.AND)
                                .build()
                )).pagination(
                        new PaginationRequest(0, 10)
                ).build();
    }

    public static RecipeDto recipeResponseDto(Long id) {
        return RecipeDto.builder()
                .name(RandomStringUtils.random(10))
                .id(id)
                .serves(10)
                .build();
    }

    public static IngredientDto ingredientResponseDto(Long id) {
        return IngredientDto.builder()
                .name(RandomStringUtils.random(10))
                .id(id)
                .build();
    }
}
