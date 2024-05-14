package com.abn.recipe.unit.service;

import com.abn.recipe.domain.Recipe;
import com.abn.recipe.exception.NotFoundException;
import com.abn.recipe.repository.recipe.RecipeIngredientRepository;
import com.abn.recipe.repository.recipe.RecipeRepository;
import com.abn.recipe.service.dto.recipe.UpdateRecipeDto;
import com.abn.recipe.service.ingredient.IngredientService;
import com.abn.recipe.service.recipe.RecipeServiceImpl;
import com.abn.recipe.utils.TestDataBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private IngredientService ingredientService;

    @Mock
    private RecipeIngredientRepository recipeIngredientRepository;

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Test
    public void successDelete() {
        Mockito.when(recipeRepository.existsById(
                        Mockito.anyLong()))
                .thenReturn(true);
        recipeService.deleteById(1L);
        Mockito.verify(recipeRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    @Test
    public void successUpdate() {
        Mockito.when(recipeRepository.existsById(
                        Mockito.anyLong()))
                .thenReturn(true);
        Mockito.when(ingredientService.findByIds(
                        Mockito.anyList()))
                .thenReturn(List.of(TestDataBuilder.buildIngredient()));
        Mockito.when(recipeRepository.save(
                        Mockito.any()))
                .thenReturn(Recipe.builder().build());
        recipeService.update(
                UpdateRecipeDto.builder()
                        .id(10L)
                        .name(RandomStringUtils.random(10))
                        .ingredients(List.of(1L))
                        .build());
        Mockito.verify(
                recipeRepository,
                Mockito.times(1)).save(Mockito.any(Recipe.class)
        );
    }

    @Test
    public void notFoundForDelete() {
        Mockito.when(recipeRepository.existsById(Mockito.anyLong())).thenReturn(false);
        Assertions.assertThrows(NotFoundException.class, () -> recipeService.deleteById(1L));
    }

    @Test
    public void notFoundForUpdate() {
        Assertions.assertThrows(NotFoundException.class, () -> recipeService.update(
                UpdateRecipeDto.builder().build())
        );
    }
}
