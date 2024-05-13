package com.cbxz.abn.repository;

public interface RecipeIngredientRepository {
    void deleteByRecipeId(Long recipeId);
    void deleteByIngredientId(Long ingredientId);
}
