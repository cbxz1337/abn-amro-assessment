package com.abn.recipe.repository.recipe;

import com.abn.recipe.domain.RecipeIngredient;
import com.abn.recipe.domain.RecipeIngredientId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, RecipeIngredientId>,
        JpaSpecificationExecutor<RecipeIngredient> {
    void deleteByRecipeId(Long recipeId);
    void deleteByIngredientId(Long ingredientId);
}
