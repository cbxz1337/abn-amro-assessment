package com.cbxz.abn.service.ingredient;

import com.cbxz.abn.domain.Ingredient;
import com.cbxz.abn.service.dto.Pagination;
import com.cbxz.abn.service.dto.ingredient.IngredientDto;
import com.cbxz.abn.service.dto.ingredient.PaginatedIngredientDto;

import java.util.List;

public interface IngredientService {
    List<Ingredient> findByIds(List<Long> ids);
    IngredientDto findById(Long id);
    PaginatedIngredientDto listIngredients(Pagination pagination);
    Long create(IngredientDto dto);
    void update(IngredientDto dto);
    void deleteById(Long id);
}
