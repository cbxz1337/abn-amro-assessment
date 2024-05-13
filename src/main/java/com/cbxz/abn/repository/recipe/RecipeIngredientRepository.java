package com.cbxz.abn.repository.recipe;

public interface RecipeIngredientRepository {
    void deleteByRecipeId(Long recipeId);
    void deleteByIngredientId(Long ingredientId);
}
