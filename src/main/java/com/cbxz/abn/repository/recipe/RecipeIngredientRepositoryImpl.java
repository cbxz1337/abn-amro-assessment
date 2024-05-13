package com.cbxz.abn.repository.recipe;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class RecipeIngredientRepositoryImpl implements RecipeIngredientRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final String DELETE_BY_RECIPE_ID = """
            delete from recipe_ingredient where recipe_id = :recipe_id;
            """;

    private static final String DELETE_BY_INGREDIENT_ID = """
            delete from recipe_ingredient where recipe_id = :ingredient_id;
            """;

    @Override
    public void deleteByRecipeId(Long recipeId) {
        jdbcTemplate.update(
                DELETE_BY_RECIPE_ID,
                Map.of("recipe_id", recipeId)
        );
    }

    @Override
    public void deleteByIngredientId(Long ingredientId) {
        jdbcTemplate.update(
                DELETE_BY_INGREDIENT_ID,
                Map.of("ingredient_id", ingredientId)
        );
    }
}
