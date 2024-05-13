package com.cbxz.abn.service.recipe;

import com.cbxz.abn.service.dto.recipe.CreateRecipeDto;
import com.cbxz.abn.service.dto.recipe.RecipeDto;
import com.cbxz.abn.service.dto.recipe.UpdateRecipeDto;

public interface RecipeService {
    void deleteById(Long id);
    Long update(UpdateRecipeDto dto);
    Long createRecipe(CreateRecipeDto dto);
    RecipeDto getById(Long id);
}
