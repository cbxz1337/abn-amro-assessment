package com.abn.recipe.service.recipe;

import com.abn.recipe.service.dto.recipe.CreateRecipeDto;
import com.abn.recipe.service.dto.recipe.RecipeDto;
import com.abn.recipe.service.dto.recipe.UpdateRecipeDto;

public interface RecipeService {
    void deleteById(Long id);
    Long update(UpdateRecipeDto dto);
    Long createRecipe(CreateRecipeDto dto);
    RecipeDto getById(Long id);
}
