package com.abn.recipe.unit.controller;

import com.abn.recipe.controller.recipe.RecipeControllerImpl;
import com.abn.recipe.service.dto.recipe.CreateRecipeDto;
import com.abn.recipe.service.dto.recipe.PaginatedRecipeDto;
import com.abn.recipe.service.recipe.RecipeService;
import com.abn.recipe.service.search.service.RecipeSearchService;
import com.abn.recipe.utils.TestDataBuilder;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecipeControllerUnitTest {

    @Mock
    private RecipeService recipeService;

    @Mock
    private RecipeSearchService recipeSearchService;

    @InjectMocks
    private RecipeControllerImpl recipeController;

    private final long id = 1L;

    @Test
    public void successfulCreationOfRecipe() {
        when(recipeService.createRecipe(Mockito.any(CreateRecipeDto.class))).thenReturn(id);
        val request = TestDataBuilder.buildCreateRecipeRequest();
        val response = recipeController.createRecipe(request);
        assertThat(response).isNotNull();
        assertThat(response.id().longValue()).isEqualTo(id);
    }

    @Test
    public void successfulSearchForRecipe() {
        val request = TestDataBuilder.recipeSearchRequest();
        when(recipeSearchService.getPaginatedRecipeDtos(Mockito.any())).thenReturn(
                PaginatedRecipeDto.builder()
                        .total(10)
                        .recipes(List.of(TestDataBuilder.recipeResponseDto(id)))
                        .build()
        );
        val response = recipeController.getRecipesByFilter(request);
        assertThat(response.recipes().getFirst().id().longValue()).isEqualTo(id);
    }
}
