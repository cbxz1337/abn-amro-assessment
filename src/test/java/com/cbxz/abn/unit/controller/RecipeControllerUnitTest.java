package com.cbxz.abn.unit.controller;

import com.cbxz.abn.controller.recipe.RecipeControllerImpl;
import com.cbxz.abn.service.dto.recipe.CreateRecipeDto;
import com.cbxz.abn.service.dto.recipe.PaginatedRecipeDto;
import com.cbxz.abn.service.dto.recipe.UpdateRecipeDto;
import com.cbxz.abn.service.recipe.RecipeService;
import com.cbxz.abn.service.search.service.RecipeSearchService;
import com.cbxz.abn.utils.TestDataBuilder;
import lombok.val;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecipeControllerUnitTest {

    @Mock
    private RecipeService recipeService;

    @Mock
    private RecipeSearchService recipeSearchService;

    @InjectMocks
    private RecipeControllerImpl recipeController;

    @Test
    public void successfulCreationOfRecipe() {
        when(recipeService.createRecipe(Mockito.any(CreateRecipeDto.class))).thenReturn(1L);
        val request = TestDataBuilder.buildCreateRecipeRequest();
        val response = recipeController.createRecipe(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.id().longValue(), 1L);
    }

    @Test
    public void successfulSearchForRecipe() {
        val request = TestDataBuilder.recipeSearchRequest();
        when(recipeSearchService.getPaginatedRecipeDtos(Mockito.any())).thenReturn(
                PaginatedRecipeDto.builder()
                        .total(10)
                        .recipes(List.of(TestDataBuilder.recipeResponseDto(1L)))
                        .build()
        );
        val response = recipeController.getRecipesByFilter(request);
        Assert.assertEquals(response.recipes().getFirst().id().longValue(), 1L);
    }
}
