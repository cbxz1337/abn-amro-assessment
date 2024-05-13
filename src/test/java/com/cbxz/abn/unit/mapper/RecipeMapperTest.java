package com.cbxz.abn.unit.mapper;

import com.cbxz.abn.domain.Ingredient;
import com.cbxz.abn.service.dto.ingredient.IngredientDto;
import com.cbxz.abn.service.mapper.RecipeMapper;
import com.cbxz.abn.utils.TestDataBuilder;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RecipeMapperTest {

    @Test
    void toRecipeDto() {
        val recipe = TestDataBuilder.buildRecipe(List.of(Ingredient.builder().id(1L).build()));
        val result = RecipeMapper.toRecipeDto(recipe);
        assertThat(result.id()).isEqualTo(recipe.getId());
        assertThat(result.name()).isEqualTo(recipe.getName());
        assertThat(result.instructions()).isEqualTo(recipe.getInstructions());
        assertThat(result.isVegetarian()).isEqualTo(recipe.getIsVegetarian());
        assertThat(result.ingredients().stream().map(IngredientDto::id).toList())
                .isEqualTo(recipe.getIngredient().stream().map(Ingredient::getId).toList());
    }

    @Test
    void toRecipe() {
        val recipe = TestDataBuilder.buildCreateRecipeDto();
        val result = RecipeMapper.toRecipe(recipe, List.of(TestDataBuilder.buildIngredient()));
        assertThat(result.getName()).isEqualTo(recipe.name());
        assertThat(result.getInstructions()).isEqualTo(recipe.instructions());
        assertThat(result.getIsVegetarian()).isEqualTo(recipe.isVegetarian());
    }
}