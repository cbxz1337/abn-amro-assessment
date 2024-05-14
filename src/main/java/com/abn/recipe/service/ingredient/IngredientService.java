package com.abn.recipe.service.ingredient;

import com.abn.recipe.domain.Ingredient;
import com.abn.recipe.service.dto.Pagination;
import com.abn.recipe.service.dto.ingredient.IngredientDto;
import com.abn.recipe.service.dto.ingredient.PaginatedIngredientDto;

import java.util.List;

public interface IngredientService {
    List<Ingredient> findByIds(List<Long> ids);
    IngredientDto findById(Long id);
    PaginatedIngredientDto listIngredients(Pagination pagination);
    Long create(IngredientDto dto);
    void update(IngredientDto dto);
    void deleteById(Long id);
}
